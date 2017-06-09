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
	
	public int averrageScore(Lid lid){
		return InschrijvingenDAO.averrageScore(lid);
	}
	
	public void updateInschrijvingen(Inschrijvingen Inschrijvingen){
		InschrijvingenDAO.update(Inschrijvingen);
	}
}
