package nl.hu.v1IPASS.IPASS.model;

import nl.hu.v1IPASS.IPASS.persistence.LidDAO;
import nl.hu.v1IPASS.IPASS.persistence.WedstrijdDAO;

import java.util.ArrayList;

import nl.hu.v1IPASS.IPASS.persistence.InschrijvingenDAO;

// Het doorgeefluik van de applicatie
public class IPASSService {
	//Oproepen van alle DOA's
	private static LidDAO LidDAO= new LidDAO();
	private static WedstrijdDAO WedstrijdDAO = new WedstrijdDAO();
	private static InschrijvingenDAO InschrijvingenDAO = new InschrijvingenDAO();
	
	//Vind alle leden
	public ArrayList<Lid> findAllLid(){
		return LidDAO.findAllLid();
	}
	
	//update gegeven lid
	public void updateLid(Lid Lid){
		LidDAO.update(Lid);
	}
	
	//Lid vinden op een bondsnummer.
	public Lid findLidOnBondsnummer(int Bondsnummer){
		return LidDAO.findOnBondsnummer(Bondsnummer);
	}
	
	//het aanmaken van een nieuw lid
	public void insertLid(Lid lid){
		LidDAO.insert(lid);
	}
	
	//Het verwijderen van een aanwezig lid
	public void delete(Lid lid){
		LidDAO.delete(lid);
	}
	
	//vind alle wedstrijden
	public ArrayList<Wedstrijd> findAllWedstrijd(){
		return WedstrijdDAO.findAllWedstrijd();
	}
	
	//nieuwe wedstrijd toevoegen
	public void insertWedstrijd(Wedstrijd Wedstrijd){
		WedstrijdDAO.insert(Wedstrijd);
	}
	
	//Verwijderen van een wedstrijd
	public void deleteWedstrijd(int Wedstrijd){
		WedstrijdDAO.delete(Wedstrijd);
	}
	
	//vind all inschrijvingen
	public ArrayList<Inschrijvingen> findAllInschrijvingen(){
		return InschrijvingenDAO.findAllInschrijvingen();
	}
	
	//aanmaken van een nieuwe inschrijving.
	public void insertInschrijvingen(Inschrijvingen Inschrijvingen){
		InschrijvingenDAO.insert(Inschrijvingen);
	}
	
	
	//Gemiddelde scoren van een lid op een jaar. (voor statistieken)
	public ArrayList<Integer> averrageScore(int Bondsnummer, int Jaar){
		return InschrijvingenDAO.averrageScore(Bondsnummer, Jaar);
	}
	
	//Update van inschrijving, voor het invoeren van de scoren en of gewonnen
	public void updateInschrijvingen(Inschrijvingen Inschrijvingen){
		InschrijvingenDAO.update(Inschrijvingen);
	}
	
	//vind alle wedstrijden
	public Wedstrijd findWedstrijd(int WedstrijdId){
		return WedstrijdDAO.findWedstrijd(WedstrijdId);
	}
	
	//tel het aantal gewonnen wedstrijden
	public int aantalGewonnenWedstrijden(int Bondsnummer){
		return InschrijvingenDAO.aantalGewonnenWedstrijden(Bondsnummer);
	}
	
	//tel het aantal wedstrijden die verloren zijn
	public int aantalVerlorenWedstrijden(int Bondsnummer){
		return InschrijvingenDAO.aantalVerlorenWedstrijden(Bondsnummer);
	}
	
	//tel het aantal wedstrijden die nog niet gebeurd zijn
	public int aantalNogTeSpelen(int Bondsnummer){
		return InschrijvingenDAO.aantalNogTeSpelen(Bondsnummer);
	}
	
	//controleer of het lid al is ingeschreven voor een wedstrijd.
	public boolean checkInschrijving(int Bondsnummer, int WedstrijdId){
		return InschrijvingenDAO.checkInschrijving(Bondsnummer, WedstrijdId);
	}
	
	//Verwijderen van een inschrijving
	public void deleteInschrijving(int Bondsnummer, int WedstrijdId){
		InschrijvingenDAO.Delete(Bondsnummer, WedstrijdId);
	}
	
	//Vind alle ingeschreven leden voor een wedstrijd
	public ArrayList<Lid> findInschrijvingenWedstrijd(int WedstrijdId){
		ArrayList<Lid> ledenLijst = new ArrayList<Lid>();
		for(int i : InschrijvingenDAO.findInschrijvingWedstrijd(WedstrijdId)){
			ledenLijst.add(LidDAO.findOnBondsnummer(i));
		}
		return ledenLijst;
	}
	
	//vind alle inschrijvingen van een wedstrijd
	public Inschrijvingen findInschrijvingOnBondsnummerWedstrijdId(int Bondsnummer, int WedstrijdId){
		return InschrijvingenDAO.findInschrijvingOnBondsnummerWedstrijdId(Bondsnummer, WedstrijdId);
	}
	
	//controleer welke wedstrijden het lid oud genoeg voor is
	public ArrayList<Wedstrijd> findWedstrijdenLeeftijd(int Leeftijd){
		return WedstrijdDAO.findWedstrijdenLeeftijd(Leeftijd);
	}
}
