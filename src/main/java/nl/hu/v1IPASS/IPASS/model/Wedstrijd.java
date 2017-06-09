package nl.hu.v1IPASS.IPASS.model;

import java.sql.Date;

public class Wedstrijd {
	private int Minimumleeftijd;
	private String Typeboog;
	private Date Begintijd;
	private Date Eindtijd;
	private Date Datum;
	private String Naam;
	private int WedstrijdId;
	
	public Wedstrijd (int Minimumleeftijd, String Typeboog, Date Begintijd, Date Eindtijd, Date Datum, String Naam, int WedstrijdId){
		this.Minimumleeftijd = Minimumleeftijd;
		this.Typeboog = Typeboog;
		this.Begintijd = Begintijd;
		this.Eindtijd = Eindtijd;
		this.Datum = Datum;
		this.Naam = Naam;
		this.WedstrijdId = WedstrijdId;
	}
	
	public Wedstrijd (int Minimumleeftijd, String Typeboog, Date Begintijd, Date Eindtijd, Date Datum, String Naam){
		this.Minimumleeftijd = Minimumleeftijd;
		this.Typeboog = Typeboog;
		this.Begintijd = Begintijd;
		this.Eindtijd = Eindtijd;
		this.Datum = Datum;
		this.Naam = Naam;
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
	public Date getBegintijd() {
		return Begintijd;
	}
	public void setBegintijd(Date begintijd) {
		Begintijd = begintijd;
	}
	public Date getEindtijd() {
		return Eindtijd;
	}
	public void setEindtijd(Date eindtijd) {
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
}
