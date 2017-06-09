package nl.hu.v1IPASS.IPASS.webservices;

import java.sql.Date;

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
	@Produces("application/json")
	public String findAllWedstrijden(){
		for(Wedstrijd W : service.findAllWedstrijd()){
			job.add("Minimumleeftijd", W.getMinimumleeftijd());
			job.add("Typeboog", W.getTypeboog());
			job.add("Begintijd", (JsonValue) W.getBegintijd());
			job.add("Eindtijd", (JsonValue) W.getEindtijd());
			job.add("Datum", (JsonValue) W.getDatum());
			job.add("Naam", W.getNaam());
			job.add("WedstrijdId", W.getWedstrijdId());
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@POST
	@Path("/insert")
	@Produces("application/json")
	public void insert(@FormParam("Minimumleeftijd") int Minimumleeftijd,
						@FormParam("Typeboog") String Typeboog,
						@FormParam("Begintijd") Date Begintijd,
						@FormParam("Eindtijd") Date Eindtijd,
						@FormParam("Datum") Date Datum,
						@FormParam("Naam") String Naam){
		Wedstrijd Wedstrijd = new Wedstrijd(Minimumleeftijd, Typeboog, Begintijd, Eindtijd, Datum, Naam);
		service.insertWedstrijd(Wedstrijd);
	}
	
	@DELETE
	@Path("/Delete/{WedstrijdId}")
	public void delete(@PathParam("WedstrijdId") int WedstrijdId){
		service.deleteWedstrijd(WedstrijdId);
	}
}
