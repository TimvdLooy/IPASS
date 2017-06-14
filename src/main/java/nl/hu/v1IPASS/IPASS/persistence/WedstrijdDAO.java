package nl.hu.v1IPASS.IPASS.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import nl.hu.v1IPASS.IPASS.model.Wedstrijd;

public class WedstrijdDAO extends BaseDAO{
	public ArrayList<Wedstrijd> findAllWedstrijd(){
		ArrayList<Wedstrijd> WedstrijdLijst = new ArrayList<Wedstrijd>();
		String query = "Select * From Wedstrijd";
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while(dbResultSet.next()){
				int Minimumleeftijd = dbResultSet.getInt("Minimumleeftijd");
				String Typeboog = dbResultSet.getString("Typeboog");
				Time Begintijd = dbResultSet.getTime("Begintijd");
				Time Eindtijd = dbResultSet.getTime("Eindtijd");
				Date Datum = dbResultSet.getDate("Datum");
				String Naam = dbResultSet.getString("Naam");
				int WedstrijdId = dbResultSet.getInt("WedstrijdId");
				String Beschrijving = dbResultSet.getString("Wedstrijdbeschrijving");
				Wedstrijd newWedstrijd = new Wedstrijd(Minimumleeftijd, Typeboog, Begintijd, Eindtijd, Datum, Naam, WedstrijdId, Beschrijving);
				WedstrijdLijst.add(newWedstrijd);
			}	
		} catch (SQLException sqle) {sqle.printStackTrace(); }
		return WedstrijdLijst;
	}
	
	public void insert(Wedstrijd Wedstrijd){
		String query = "INSERT INTO Wedstrijd (Minimumleeftijd, Typeboog, Begintijd, Eindtijd, Datum, Naam, Wedstrijdbeschrijving) VALUES ("
				+Wedstrijd.getMinimumleeftijd()
				+ ", '"
				+ Wedstrijd.getTypeboog()
				+"', TO_TIMESTAMP('"
				+Wedstrijd.getBegintijd()
				+"','HH24:MI:SS'), TO_TIMESTAMP('"
				+Wedstrijd.getEindtijd()
				+"','HH24:MI:SS'), TO_DATE('"
				+Wedstrijd.getDatum()
				+"','YYYY-MM-DD'), '"
				+Wedstrijd.getNaam()
				+"', '"
				+Wedstrijd.getBeschrijving()
				+"')";
		System.out.println(query);
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
		} catch (SQLException sqle) {sqle.printStackTrace();}
	}
	
	public void delete(int WedstrijdId){
		String query = "Delete From Wedstrijd where WedstrijdId = "+ WedstrijdId;
		System.out.println(query);
		try (Connection con = super.getConnection()){
		Statement stmt = con.createStatement();
		ResultSet dbResultSet = stmt.executeQuery(query);
		} catch (SQLException sqle) {sqle.printStackTrace();}
	}
}
