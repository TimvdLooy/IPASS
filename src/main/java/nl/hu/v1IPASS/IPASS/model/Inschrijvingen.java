package nl.hu.v1IPASS.IPASS.model;

//POJO voor inschrijvingen
public class Inschrijvingen {
	private int Bondsnummer;
	private int WedstrijdId;
	private int InschrijvingId;
	private int Score;
	private boolean Gewonnen;
	
	// constructor voor het ophalen van inschrijvinngen
	public Inschrijvingen(int Bondsnummer, int WedstrijdId, int InschrijvingId, int Score, boolean Gewonnen){
		this.Bondsnummer = Bondsnummer;
		this.WedstrijdId = WedstrijdId;
		this.InschrijvingId = InschrijvingId;
		this.Score = Score;
		this.Gewonnen = Gewonnen;
	}
	
	//constructor voor het aanmaken van nieuwe inschrijving. Score en gewonnen wordt later pas toegevoegd door de admin.
	public Inschrijvingen(int Bondsnummer, int WedstrijdId){
		this.Score = 0;
		this.Gewonnen = false;
		this.Bondsnummer = Bondsnummer;
		this.WedstrijdId = WedstrijdId;
	}

	public int getBondsnummer() {
		return Bondsnummer;
	}
	public void setBondsnummer(int bondsnummer) {
		Bondsnummer = bondsnummer;
	}
	public int getWedstrijdId() {
		return WedstrijdId;
	}
	public void setWedstrijdId(int wedstrijdId) {
		WedstrijdId = wedstrijdId;
	}
	public int getInschrijvingId() {
		return InschrijvingId;
	}
	public void setInschrijvingId(int inschrijvingId) {
		InschrijvingId = inschrijvingId;
	}

	public int getScore() {
		return Score;
	}

	public void setScore(int score) {
		Score = score;
	}

	public boolean isGewonnen() {
		return Gewonnen;
	}

	public void setGewonnen(boolean gewonnen) {
		Gewonnen = gewonnen;
	}
}
