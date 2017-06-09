package nl.hu.v1IPASS.IPASS.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import nl.hu.v1IPASS.IPASS.model.Inschrijvingen;
import nl.hu.v1IPASS.IPASS.model.Lid;

public class InschrijvingenDAO extends BaseDAO{
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
	
	public void insert(Inschrijvingen Inschrijvingen){
		String query = "INSERT INTO Inschrijvingen (Bondsnummer, WedstrijdId, Score, Gewonnen) VALUES ("
				+Inschrijvingen.getBondsnummer()
				+ ", "
				+ Inschrijvingen.getWedstrijdId()
				+"null, null)";
		System.out.println(query);
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
		} catch (SQLException sqle) {sqle.printStackTrace();}
	}
	
	public int averrageScore(Lid Lid){
		int Score = 0;
		String query = "Select ceilling(avg(Score)) AS Score From Inschrijvingen Where Bondsnummer = "+ Lid.getBondsnummer() +" Group By Bondsnummer";
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while(dbResultSet.next()){
				int WedstrijdId = dbResultSet.getInt("Score");
				Score = WedstrijdId;
			}	
		} catch (SQLException sqle) {sqle.printStackTrace(); }
		return Score;
	}
	
	public void update(Inschrijvingen Inschrijvingen){
		String query = "UPDATE Inschrijvingen SET Bondsnummer=" + Inschrijvingen.getBondsnummer()
		+ ", WedstrijdId=" + Inschrijvingen.getWedstrijdId() + ", InschrijvingId="
		+ Inschrijvingen.getInschrijvingId() + ", Score=" + Inschrijvingen.getScore()
		+ ", Gewonnen=" + Inschrijvingen.isGewonnen()+ " Where InschrijvingId = " + Inschrijvingen.getInschrijvingId();
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
		} catch (SQLException sqle) {sqle.printStackTrace();}
	}
}
