package nl.hu.v1IPASS.IPASS.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import nl.hu.v1IPASS.IPASS.model.Lid;
import nl.hu.v1IPASS.IPASS.model.Lid;

public class LidDAO extends BaseDAO{
	
	public ArrayList<Lid> findAllLid(){
		ArrayList<Lid> lidLijst = new ArrayList<Lid>();
		String query = "Select * From Lid";
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while(dbResultSet.next()){
				String Email = dbResultSet.getString("Email");
				int Telefoonnummer = dbResultSet.getInt("Telefoonnummer");
				int Leeftijd = dbResultSet.getInt("Leeftijd");
				String Achternaam = dbResultSet.getString("Achternaam");
				String Voornaam = dbResultSet.getString("Voornaam");
				int Bondsnummer = dbResultSet.getInt("Bondsnummer");
				boolean Actief = dbResultSet.getBoolean("Actief");
				String Wachtwoord = dbResultSet.getString("Wachtwoor");
				Lid newLid = new Lid(Email, Telefoonnummer, Leeftijd, Achternaam, Voornaam, Bondsnummer, Actief, Wachtwoord);
				lidLijst.add(newLid);
			}	
		} catch (SQLException sqle) {sqle.printStackTrace(); }
		return lidLijst;
	}
	
	public Lid findOnBondsnummer(int Bondsnummer){
		Lid lid = null;
		String query = "Select * From Lid Where Bondsnummer = "+Bondsnummer;
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while(dbResultSet.next()){
				String Email = dbResultSet.getString("Email");
				int Telefoonnummer = dbResultSet.getInt("Telefoonnummer");
				int Leeftijd = dbResultSet.getInt("Leeftijd");
				String Achternaam = dbResultSet.getString("Achternaam");
				String Voornaam = dbResultSet.getString("Voornaam");
				boolean Actief = dbResultSet.getBoolean("Actief");
				String Wachtwoord = dbResultSet.getString("Wachtwoor");
				Lid newLid = new Lid(Email, Telefoonnummer, Leeftijd, Achternaam, Voornaam, Bondsnummer, Actief, Wachtwoord);
				lid=newLid;
			}	
		} catch (SQLException sqle) {sqle.printStackTrace(); }
		return lid;
	}
	
	public void insert(Lid Lid){
		String query = "INSERT INTO Lid (Email, Telefoonnummer, Leeftijd, Achternaam, Voornaam, Bondsnummer, Actief, Wachtwoord) VALUES ('"
				+Lid.getEmail()
				+ "', "
				+ Lid.getTelefoonnummer()
				+", "
				+Lid.getLeeftijd()
				+", '"
				+Lid.getAchternaam()
				+"', '"
				+Lid.getVoornaam()
				+"', "
				+Lid.getBondsnummer()
				+", "
				+Lid.isActief()
				+", '"
				+Lid.getWachtwoord()
				+"')";
		System.out.println(query);
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
		} catch (SQLException sqle) {sqle.printStackTrace();}
	}
	
	public void delete(Lid Lid){
		String query = "Delete From Lid where Bondsnummer = "+ Lid.getBondsnummer();
		System.out.println(query);
		try (Connection con = super.getConnection()){
		Statement stmt = con.createStatement();
		ResultSet dbResultSet = stmt.executeQuery(query);
		} catch (SQLException sqle) {sqle.printStackTrace();}
	}
	
	public void update(Lid Lid){
		String query = "UPDATE Lid SET Wachtwoord='" + Lid.getWachtwoord() + "' Where Bondsnummer = " + Lid.getBondsnummer();
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
		} catch (SQLException sqle) {sqle.printStackTrace();}
	}
}
