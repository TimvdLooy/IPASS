package nl.hu.v1IPASS.IPASS.model;

import java.sql.Date;
import java.sql.Time;

//POJO voor Wedstrijd
public class Wedstrijd {
	private int Minimumleeftijd;
	private String Typeboog;
	private Time Begintijd;
	private Time Eindtijd;
	private Date Datum;
	private String Naam;
	private int WedstrijdId;
	private String Beschrijving;
	
	//Constructor voor het ophalen van wedstrijden.
	public Wedstrijd (int Minimumleeftijd, String Typeboog, Time Begintijd, Time Eindtijd, Date Datum, String Naam, int WedstrijdId, String Beschrijving){
		this.Minimumleeftijd = Minimumleeftijd;
		this.Typeboog = Typeboog;
		this.Begintijd = Begintijd;
		this.Eindtijd = Eindtijd;
		this.Datum = Datum;
		this.Naam = Naam;
		this.WedstrijdId = WedstrijdId;
		this.Beschrijving = Beschrijving;
	}
	
	//Constructor voor het aanmaken van nieuwe wedstrijden. WedstrijdId wordt door postgres gegenereerd.
	public Wedstrijd (int Minimumleeftijd, String Typeboog, Time Begintijd, Time Eindtijd, Date Datum, String Naam, String Beschrijving){
		this.Minimumleeftijd = Minimumleeftijd;
		this.Typeboog = Typeboog;
		this.Begintijd = Begintijd;
		this.Eindtijd = Eindtijd;
		this.Datum = Datum;
		this.Naam = Naam;
		this.Beschrijving = Beschrijving;
	}
	
	public int getMinimumleeftijd() {
		return Minimumleeftijd;
	}
	public void setMinimumleeftijd(int minimumleeftijd) {
		Minimumleeftijd = minimumleeftijd;
	}
	public String getTypeboog() {
		return Typeboog;
	}
	public void setTypeboog(String typeboog) {
		Typeboog = typeboog;
	}
	public Time getBegintijd() {
		return Begintijd;
	}
	public void setBegintijd(Time begintijd) {
		Begintijd = begintijd;
	}
	public Time getEindtijd() {
		return Eindtijd;
	}
	public void setEindtijd(Time eindtijd) {
		Eindtijd = eindtijd;
	}
	public Date getDatum() {
		return Datum;
	}
	public void setDatum(Date datum) {
		Datum = datum;
	}
	public String getNaam() {
		return Naam;
	}
	public void setNaam(String naam) {
		Naam = naam;
	}
	public int getWedstrijdId() {
		return WedstrijdId;
	}
	public void setWedstrijdId(int wedstrijdId) {
		WedstrijdId = wedstrijdId;
	}

	public String getBeschrijving() {
		return Beschrijving;
	}

	public void setBeschrijving(String beschrijving) {
		Beschrijving = beschrijving;
	}
}
