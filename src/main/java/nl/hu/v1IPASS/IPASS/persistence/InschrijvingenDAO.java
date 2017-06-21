package nl.hu.v1IPASS.IPASS.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import nl.hu.v1IPASS.IPASS.model.Inschrijvingen;
import nl.hu.v1IPASS.IPASS.model.Lid;

//alle functies worden toegelicht in de IPASSService. De functies die IPASSService gebruikt worden hieronder aangemaakt.
public class InschrijvingenDAO extends BaseDAO{
	public boolean checkInschrijving(int Bondsnummer, int WedstrijdId){
		boolean ingeschreven = false;
		String query = "select WedstrijdId from Inschrijvingen where Bondsnummer = "+Bondsnummer;
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while(dbResultSet.next()){
				int WedstrijdIdDB = dbResultSet.getInt("WedstrijdId");
				if(WedstrijdIdDB == WedstrijdId){
					ingeschreven = true;
				}
			}	
		} catch (SQLException sqle) {sqle.printStackTrace(); }
		return ingeschreven;
	}
	
	public Inschrijvingen findInschrijvingOnBondsnummerWedstrijdId(int Bondsnummer, int WedstrijdId){
		Inschrijvingen Inschrijvingen = null;
		String query = "Select * From Inschrijvingen Where Bondsnummer ="+Bondsnummer+ " AND WedstrijdId = " +WedstrijdId ;
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while(dbResultSet.next()){
				int Bondsnummer2 = dbResultSet.getInt("Bondsnummer");
				int WedstrijdId2 = dbResultSet.getInt("WedstrijdId");
				int InschrijvingId = dbResultSet.getInt("InschrijvingId");
				int Score= dbResultSet.getInt("Score");
				boolean Gewonnen = dbResultSet.getBoolean("Gewonnen");
				Inschrijvingen = new Inschrijvingen(Bondsnummer2, WedstrijdId2, InschrijvingId, Score, Gewonnen);
			}	
		} catch (SQLException sqle) {sqle.printStackTrace(); }
		return Inschrijvingen;
	}
	
	public ArrayList<Inschrijvingen> findAllInschrijvingen(){
		ArrayList<Inschrijvingen> InschrijvingenLijst = new ArrayList<Inschrijvingen>();
		String query = "Select * From Inschrijvingen";
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while(dbResultSet.next()){
				int Bondsnummer = dbResultSet.getInt("Bondsnummer");
				int WedstrijdId = dbResultSet.getInt("WedstrijdId");
				int InschrijvingId = dbResultSet.getInt("InschrijvingId");
				int Score= dbResultSet.getInt("Score");
				boolean Gewonnen = dbResultSet.getBoolean("Gewonnen");
				Inschrijvingen newInschrijvingen = new Inschrijvingen(Bondsnummer, WedstrijdId, InschrijvingId, Score, Gewonnen);
				InschrijvingenLijst.add(newInschrijvingen);
			}	
		} catch (SQLException sqle) {sqle.printStackTrace(); }
		return InschrijvingenLijst;
	}
	
	public ArrayList<Integer> findInschrijvingWedstrijd(int WedstrijdId){
		ArrayList<Integer> InschrijvingenLijst = new ArrayList<Integer>();
		String query = "Select * From Inschrijvingen Where WedstrijdId="+WedstrijdId+"AND score IS NULL";
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while(dbResultSet.next()){
				int Bondsnummer = dbResultSet.getInt("Bondsnummer");
				InschrijvingenLijst.add(Bondsnummer);
			}	
		} catch (SQLException sqle) {sqle.printStackTrace(); }
		return InschrijvingenLijst;
	}
	
	public void insert(Inschrijvingen Inschrijvingen){
		String query = "INSERT INTO Inschrijvingen (Bondsnummer, WedstrijdId, Score, Gewonnen) VALUES ("
				+Inschrijvingen.getBondsnummer()
				+ ", "
				+ Inschrijvingen.getWedstrijdId()
				+", null, null)";
		System.out.println(query);
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
		} catch (SQLException sqle) {sqle.printStackTrace();}
	}
	
	public ArrayList<Integer> averrageScore(int Bondsnummer, int Jaar){
		ArrayList<Integer> Score = new ArrayList<Integer>();
		String query = "SELECT ceiling(AVG(I.score)) AS Averagge_score, EXTRACT(MONTH FROM W.Datum) AS Maand, I.Bondsnummer"
						+" FROM Inschrijvingen I INNER JOIN Wedstrijd W ON W.wedstrijdid = I.wedstrijdid"
						+" WHERE EXTRACT(YEAR FROM W.Datum) = "+ Jaar +" AND Bondsnummer ="+ Bondsnummer
						+" GROUP BY Maand, I.Bondsnummer";
		System.out.println("lol "+query);
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while(dbResultSet.next()){
				Score.add(dbResultSet.getInt("Maand"));
				Score.add(dbResultSet.getInt("Averagge_score"));
			}	
		} catch (SQLException sqle) {sqle.printStackTrace(); }
		return Score;
	}
	
	public void update(Inschrijvingen Inschrijvingen){
		String query = "UPDATE Inschrijvingen SET score ="+Inschrijvingen.getScore()+" , gewonnen ="+ Inschrijvingen.isGewonnen() +" Where InschrijvingId =" + Inschrijvingen.getInschrijvingId();
		System.out.println(query);
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
		} catch (SQLException sqle) {sqle.printStackTrace();}
	}
	
	public int aantalGewonnenWedstrijden(int Bondsnummer){
		int gewonnen = 0;
		String query = "select L.Bondsnummer, count(I.wedstrijdid) AS Aantal_wedstrijden from Lid L INNER JOIN Inschrijvingen I ON L.Bondsnummer = I.Bondsnummer Where Gewonnen = true AND I.Bondsnummer = "+ Bondsnummer +" Group By L.Bondsnummer";
		System.out.println(query);
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			while(dbResultSet.next()){
			gewonnen = dbResultSet.getInt("Aantal_wedstrijden");
			}
			
		} catch (SQLException sqle) {sqle.printStackTrace(); }
		return gewonnen;
	}
	
	public int aantalVerlorenWedstrijden(int Bondsnummer){
		int Verloren = 0;
		String query = "select L.Bondsnummer, count(I.wedstrijdid) AS Aantal_wedstrijden from Lid L INNER JOIN Inschrijvingen I ON L.Bondsnummer = I.Bondsnummer Where Gewonnen = false AND I.score IS NOT NULL Group By L.Bondsnummer";
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			while(dbResultSet.next()){
			Verloren = dbResultSet.getInt("Aantal_wedstrijden");
			}
		} catch (SQLException sqle) {sqle.printStackTrace(); }
		return Verloren;
	}
	
	public int aantalNogTeSpelen(int Bondsnummer){
		int nogTeSpelen = 0;
		String query = "select L.Bondsnummer, count(I.wedstrijdid) AS Aantal_wedstrijden from Lid L INNER JOIN Inschrijvingen I ON L.Bondsnummer = I.Bondsnummer INNER JOIN Wedstrijd W ON W.WedstrijdId = I.WedstrijdId Where CURRENT_DATE <= W.Datum Group By L.Bondsnummer";
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			while(dbResultSet.next()){
			nogTeSpelen = dbResultSet.getInt("Aantal_wedstrijden");
			}
		} catch (SQLException sqle) {sqle.printStackTrace(); }
		return nogTeSpelen;
	}
	
	public void Delete(int Bondsnummer, int WedstrijdId){
		String query = "DELETE FROM Inschrijvingen WHERE Bondsnummer="+Bondsnummer+" AND WedstrijdId=" + WedstrijdId;
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
		} catch (SQLException sqle) {sqle.printStackTrace(); }
	}
}
