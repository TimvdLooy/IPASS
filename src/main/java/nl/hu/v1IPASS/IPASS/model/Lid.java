package nl.hu.v1IPASS.IPASS.model;

//POJO voor Lid
public class Lid {
	private String Email;
	private int Telefoonnummer;
	private int Leeftijd;
	private String Achternaam;
	private String Voornaam;
	private int Bondsnummer;
	private boolean Actief;
	private String Wachtwoord;
	private String role;
	
	public Lid(String Email, int Telefoonnummer, int Leeftijd, String Achternaam, String Voornaam, int Bondsnummer, boolean Actief, String Wachtwoord, String role){
		this.Email = Email;
		this.Telefoonnummer = Telefoonnummer;
		this.Leeftijd = Leeftijd;
		this.Achternaam = Achternaam;
		this.Voornaam = Voornaam;
		this.Bondsnummer = Bondsnummer;
		this.Actief = Actief;
		this.Wachtwoord = Wachtwoord;
		this.role = role;
	}
	
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public int getTelefoonnummer() {
		return Telefoonnummer;
	}
	public void setTelefoonnummer(int telefoonnummer) {
		Telefoonnummer = telefoonnummer;
	}
	public int getLeeftijd() {
		return Leeftijd;
	}
	public void setLeeftijd(int leeftijd) {
		Leeftijd = leeftijd;
	}
	public String getAchternaam() {
		return Achternaam;
	}
	public void setAchternaam(String achternaam) {
		Achternaam = achternaam;
	}
	public String getVoornaam() {
		return Voornaam;
	}
	public void setVoornaam(String voornaam) {
		Voornaam = voornaam;
	}
	public int getBondsnummer() {
		return Bondsnummer;
	}
	public void setBondsnummer(int bondsnummer) {
		Bondsnummer = bondsnummer;
	}
	public boolean isActief() {
		return Actief;
	}
	public void setActief(boolean actief) {
		Actief = actief;
	}
	public String getWachtwoord() {
		return Wachtwoord;
	}
	public void setWachtwoord(String wachtwoord) {
		Wachtwoord = wachtwoord;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
