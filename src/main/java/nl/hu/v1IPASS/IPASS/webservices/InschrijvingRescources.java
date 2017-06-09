package nl.hu.v1IPASS.IPASS.webservices;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
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
	@Path("/Insert/{Bondsnummer}/{WedstrijdId}")
	@Produces("application/json")
	public void insert(@PathParam("Bondsnummer") int Bondsnummmer,
						@PathParam("WedstrijdId") int WedstrijdId){
		Inschrijvingen Inschrijving = new Inschrijvingen(Bondsnummmer, WedstrijdId);
		service.insertInschrijvingen(Inschrijving);
	}
	
	@POST
	@Path("/averageScore/{Bondsnummer}")
	@Produces("application/json")
	public String averageScore(@PathParam("Bondsnummer") int Bondsnummer){
		Lid Lid = service.findLidOnBondsnummer(Bondsnummer);
		int data = service.averrageScore(Lid);
		job.add("Score", data);
		jab.add(job);
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@PUT
	@Path("/Update/{Bondsnummer}/{WedstrijdId}/{Score}/{Gewonnen}/{InschrijvingId}")
	@Produces("applciation/json")
	public void update(@PathParam("Bondsnummer") int Bondsnummer,
						@PathParam("WedstrijdId") int WedstrijdId,
						@PathParam("Score") int Score,
						@PathParam("Gewonnen") boolean Gewonnen,
						@PathParam("InschrijvingId") int InschrijvingId){
		IPASSService service = sp.getIPASSService();
		Inschrijvingen Inschrijving = new Inschrijvingen(Bondsnummer, WedstrijdId, InschrijvingId, Score, Gewonnen);
		service.updateInschrijvingen(Inschrijving);
	}
}
