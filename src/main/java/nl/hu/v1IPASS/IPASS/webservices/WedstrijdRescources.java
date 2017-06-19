package nl.hu.v1IPASS.IPASS.webservices;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import nl.hu.v1IPASS.IPASS.model.IPASSService;
import nl.hu.v1IPASS.IPASS.model.ServiceProvider;
import nl.hu.v1IPASS.IPASS.model.Wedstrijd;

@Path("/Wedstrijd")
public class WedstrijdRescources {
	private ServiceProvider sp;
	IPASSService service = sp.getIPASSService();
	JsonArrayBuilder jab = Json.createArrayBuilder();
	JsonObjectBuilder job = Json.createObjectBuilder();
	
	@GET
	@RolesAllowed("Admin")
	@Produces("application/json")
	public String findAllWedstrijden(){
		for(Wedstrijd W : service.findAllWedstrijd()){
			SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
			String begintijd = localDateFormat.format(W.getBegintijd());
			String eindtijd = localDateFormat.format(W.getEindtijd());
			job.add("Minimumleeftijd", W.getMinimumleeftijd());
			job.add("Typeboog", W.getTypeboog());
			job.add("Begintijd", begintijd);
			job.add("Eindtijd", eindtijd);
			job.add("Datum", W.getDatum().toString());
			job.add("Naam", W.getNaam());
			job.add("WedstrijdId", W.getWedstrijdId());
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@GET
	@RolesAllowed("User")
	@Path("/Leeftijd/{Leeftijd}")
	@Produces("application/json")
	public String findWedstrijdLeeftijd(@PathParam("Leeftijd") int Leeftijd){
		for(Wedstrijd W : service.findWedstrijdenLeeftijd(Leeftijd)){
			SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
			String begintijd = localDateFormat.format(W.getBegintijd());
			String eindtijd = localDateFormat.format(W.getEindtijd());
			job.add("Minimumleeftijd", W.getMinimumleeftijd());
			job.add("Typeboog", W.getTypeboog());
			job.add("Begintijd", begintijd);
			job.add("Eindtijd", eindtijd);
			job.add("Datum", W.getDatum().toString());
			job.add("Naam", W.getNaam());
			job.add("WedstrijdId", W.getWedstrijdId());
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@POST
	@RolesAllowed("Admin")
	@Path("/insert")
	@Produces("application/json")
	public void insert(@FormParam("Minimumleeftijd") int Minimumleeftijd,
						@FormParam("TypeBoog") String Typeboog,
						@FormParam("Begintijd") Time Begintijd,
						@FormParam("Eindtijd") Time Eindtijd,
						@FormParam("Datum") Date Datum,
						@FormParam("Wedstrijdnaam") String Naam,
						@FormParam("Wedstrijdbeschrijving") String Beschrijving){
		Wedstrijd Wedstrijd = new Wedstrijd(Minimumleeftijd, Typeboog, Begintijd, Eindtijd, Datum, Naam, Beschrijving);
		service.insertWedstrijd(Wedstrijd);
	}
	
	@DELETE
	@RolesAllowed("Admin")
	@Path("/Delete/{WedstrijdId}")
	public void delete(@PathParam("WedstrijdId") int WedstrijdId){
		service.deleteWedstrijd(WedstrijdId);
		System.out.println("Wedstrijd " + WedstrijdId + " verwijderd");
	}
	
	@GET
	@RolesAllowed("User")
	@Path("/find/{WedstrijdId}")
	@Produces("application/json")
	public String findWedstrijd(@PathParam("WedstrijdId") int WedstrijdId){
		Wedstrijd W = service.findWedstrijd(WedstrijdId);
		SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
		String begintijd = localDateFormat.format(W.getBegintijd());
		String eindtijd = localDateFormat.format(W.getEindtijd());
		if(W.getMinimumleeftijd() != 0){ job.add("Minimumleeftijd", W.getMinimumleeftijd());}
		job.add("Typeboog", W.getTypeboog());
		job.add("Begintijd", begintijd);
		job.add("Eindtijd", eindtijd);
		job.add("Datum", W.getDatum().toString());
		job.add("Naam", W.getNaam());
		job.add("WedstrijdId", W.getWedstrijdId());
		if(W.getBeschrijving() != null){job.add("Wedstrijdbeschrijving", W.getBeschrijving());}
		jab.add(job);
		JsonArray array = jab.build();
		return array.toString();
		
	}
}
