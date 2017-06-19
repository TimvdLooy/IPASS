package nl.hu.v1IPASS.IPASS.model;

import nl.hu.v1IPASS.IPASS.persistence.LidDAO;
import nl.hu.v1IPASS.IPASS.persistence.WedstrijdDAO;

import java.util.ArrayList;

import nl.hu.v1IPASS.IPASS.persistence.InschrijvingenDAO;


public class IPASSService {
	private static LidDAO LidDAO= new LidDAO();
	private static WedstrijdDAO WedstrijdDAO = new WedstrijdDAO();
	private static InschrijvingenDAO InschrijvingenDAO = new InschrijvingenDAO();
	
	public ArrayList<Lid> findAllLid(){
		return LidDAO.findAllLid();
	}
	
	public void updateLid(Lid Lid){
		LidDAO.update(Lid);
	}
	
	public Lid findLidOnBondsnummer(int Bondsnummer){
		return LidDAO.findOnBondsnummer(Bondsnummer);
	}
	
	public void insertLid(Lid lid){
		LidDAO.insert(lid);
	}
	
	public void delete(Lid lid){
		LidDAO.delete(lid);
	}
	
	public ArrayList<Wedstrijd> findAllWedstrijd(){
		return WedstrijdDAO.findAllWedstrijd();
	}
	
	public void insertWedstrijd(Wedstrijd Wedstrijd){
		WedstrijdDAO.insert(Wedstrijd);
	}
	
	public void deleteWedstrijd(int Wedstrijd){
		WedstrijdDAO.delete(Wedstrijd);
	}
	
	public ArrayList<Inschrijvingen> findAllInschrijvingen(){
		return InschrijvingenDAO.findAllInschrijvingen();
	}
	
	public void insertInschrijvingen(Inschrijvingen Inschrijvingen){
		InschrijvingenDAO.insert(Inschrijvingen);
	}
	
	public ArrayList<Integer> averrageScore(int Bondsnummer, int Jaar){
		return InschrijvingenDAO.averrageScore(Bondsnummer, Jaar);
	}
	
	public void updateInschrijvingen(Inschrijvingen Inschrijvingen){
		InschrijvingenDAO.update(Inschrijvingen);
	}
	
	public Wedstrijd findWedstrijd(int WedstrijdId){
		return WedstrijdDAO.findWedstrijd(WedstrijdId);
	}
	
	public int aantalGewonnenWedstrijden(int Bondsnummer){
		return InschrijvingenDAO.aantalGewonnenWedstrijden(Bondsnummer);
	}
	
	public int aantalVerlorenWedstrijden(int Bondsnummer){
		return InschrijvingenDAO.aantalVerlorenWedstrijden(Bondsnummer);
	}
	
	public int aantalNogTeSpelen(int Bondsnummer){
		return InschrijvingenDAO.aantalNogTeSpelen(Bondsnummer);
	}
	
	public boolean checkInschrijving(int Bondsnummer, int WedstrijdId){
		return InschrijvingenDAO.checkInschrijving(Bondsnummer, WedstrijdId);
	}
	
	public void deleteInschrijving(int Bondsnummer, int WedstrijdId){
		InschrijvingenDAO.Delete(Bondsnummer, WedstrijdId);
	}
	
	public ArrayList<Lid> findInschrijvingenWedstrijd(int WedstrijdId){
		ArrayList<Lid> ledenLijst = new ArrayList<Lid>();
		for(int i : InschrijvingenDAO.findInschrijvingWedstrijd(WedstrijdId)){
			ledenLijst.add(LidDAO.findOnBondsnummer(i));
		}
		return ledenLijst;
	}
	
	public Inschrijvingen findInschrijvingOnBondsnummerWedstrijdId(int Bondsnummer, int WedstrijdId){
		return InschrijvingenDAO.findInschrijvingOnBondsnummerWedstrijdId(Bondsnummer, WedstrijdId);
	}
	
	public ArrayList<Wedstrijd> findWedstrijdenLeeftijd(int Leeftijd){
		return WedstrijdDAO.findWedstrijdenLeeftijd(Leeftijd);
	}
}
