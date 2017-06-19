package nl.hu.v1IPASS.IPASS.webservices;

import java.util.ArrayList;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import nl.hu.v1IPASS.IPASS.model.IPASSService;
import nl.hu.v1IPASS.IPASS.model.Inschrijvingen;
import nl.hu.v1IPASS.IPASS.model.Lid;
import nl.hu.v1IPASS.IPASS.model.ServiceProvider;

@Path("/Inschrijvingen")
public class InschrijvingRescources {
	private ServiceProvider sp;
	IPASSService service = sp.getIPASSService();
	JsonArrayBuilder jab = Json.createArrayBuilder();
	JsonObjectBuilder job = Json.createObjectBuilder();
	
	@GET
	@Produces("application/json")
	public String findAllInschrijvingen(){
		for(Inschrijvingen I: service.findAllInschrijvingen()){
			job.add("Bondsnummer", I.getBondsnummer());
			job.add("WedstrijdId", I.getWedstrijdId());
			job.add("InschrijvingId", I.getInschrijvingId());
			job.add("Score", I.getScore());
			job.add("Gewonnen", I.isGewonnen());
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@POST
	@RolesAllowed("Admin")
	@Path("findInschrijvingen/{WedstrijdId}")
	@Produces("application/json")
	public String findInschrijvingWedstrijd(@PathParam("WedstrijdId") int WedstrijdId){
		for(Lid L: service.findInschrijvingenWedstrijd(WedstrijdId)){
			job.add("Bondsnummer", L.getBondsnummer());
			job.add("Voornaam", L.getVoornaam());
			job.add("Achternaam", L.getAchternaam());
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@POST
	@RolesAllowed("User")
	@Path("/Insert/{Bondsnummer}/{WedstrijdId}")
	@Produces("application/json")
	public void insert(@PathParam("Bondsnummer") int Bondsnummmer,
						@PathParam("WedstrijdId") int WedstrijdId){
		Inschrijvingen Inschrijving = new Inschrijvingen(Bondsnummmer, WedstrijdId);
		service.insertInschrijvingen(Inschrijving);
	}
	
	@POST
	@RolesAllowed("User")
	@Path("/averageScore/{Bondsnummer}/{Jaar}")
	@Produces("application/json")
	public String averageScore(@PathParam("Bondsnummer") int Bondsnummer,
								@PathParam("Jaar") int Jaar){
		ArrayList<Integer> score = service.averrageScore(Bondsnummer, Jaar);
		int index = 0;
		int maand = 1;
		System.out.println("size = "+score.size());
		if(score.size() != 0){
		for(int i=1; i<score.size(); i+=2){
			if(score.get(index) != maand){
				for(int j = maand; j<score.get(index); j++){
					job.add("Score", 0);
					job.add("Maand", j);
					jab.add(job);
				}
				maand = score.get(index);
			}
			job.add("Score", score.get(i));
			job.add("Maand", score.get(index));
			System.out.println("index = " + index);
			index+=2;
			maand++;
			System.out.println("i = " + i);
			jab.add(job);
			if(score.get(score.size()-2) != 12 && score.size()-1 == i){
				for(int p = score.get(score.size()-2)+1; p<13; p++){
					job.add("Score", 0);
					job.add("Maand", p);
					jab.add(job);
				}
			}
		}
		}
		else{
			for(int i=0; i<13; i++){
				job.add("Score", 0);
				job.add("Maand", i);
				jab.add(job);
			}
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@PUT
	@RolesAllowed("Admin")
	@Path("/Update/{Bondsnummer}/{WedstrijdId}")
	@Produces("application/json")
	public void update(@FormParam("scoren") int scoren,
						@PathParam("Bondsnummer") int Bondsnummer,
						@FormParam("selectWhoWon") boolean WinVerlies,
						@PathParam("WedstrijdId") int WedstrijdId){
		IPASSService service = sp.getIPASSService();
		Inschrijvingen Inschrijving = service.findInschrijvingOnBondsnummerWedstrijdId(Bondsnummer, WedstrijdId);
		System.out.println(WinVerlies);
		System.out.println(scoren);
		System.out.println(Bondsnummer);
		System.out.println(WedstrijdId);
		Inschrijving.setGewonnen(WinVerlies);
		Inschrijving.setScore(scoren);
		service.updateInschrijvingen(Inschrijving);
	}
	
	@POST
	@RolesAllowed("User")
	@Path("/GewonnenVerloren/{Bondsnummer}")
	@Produces("application/json")
	public String aantalGewonnenVerloren(@PathParam("Bondsnummer") int Bondsnummer){
		job.add("Gewonnen", service.aantalGewonnenWedstrijden(Bondsnummer));
		job.add("Verloren", service.aantalVerlorenWedstrijden(Bondsnummer));
		job.add("NogTeSpelen", service.aantalNogTeSpelen(Bondsnummer));
		jab.add(job);
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@POST
	@RolesAllowed("User")
	@Path("/checkInschrijving/{Bondsnummer}/{WedstrijdId}")
	@Produces("application/json")
	public String checkInschrijving(@PathParam("Bondsnummer") int Bondsnummer,
										@PathParam("WedstrijdId") int WedstrijdId){
		boolean ingeschreven = service.checkInschrijving(Bondsnummer, WedstrijdId);
		job.add("Ingeschreven", ingeschreven);
		jab.add(job);
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@DELETE
	@RolesAllowed("User")
	@Path("/Delete/{Bondsnummer}/{WedstrijdId}")
	public void Delete(@PathParam("Bondsnummer") int Bondsnummer,
							@PathParam("WedstrijdId") int WedstrijdId){
		service.deleteInschrijving(Bondsnummer, WedstrijdId);
	}
}
