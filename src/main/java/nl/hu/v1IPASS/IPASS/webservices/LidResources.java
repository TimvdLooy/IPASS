package nl.hu.v1IPASS.IPASS.webservices;
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
import nl.hu.v1IPASS.IPASS.model.Lid;
import nl.hu.v1IPASS.IPASS.model.ServiceProvider;

@Path ("/Lid")
public class LidResources {
	private ServiceProvider sp;
	IPASSService service = sp.getIPASSService();
	JsonArrayBuilder jab = Json.createArrayBuilder();
	JsonObjectBuilder job = Json.createObjectBuilder();
	
	@GET
	@Produces("application/json")
	public String getAllLid(){
		for(Lid l: service.findAllLid()){
			job.add("Email", l.getEmail());
			job.add("Telefoonnummer", l.getTelefoonnummer());
			job.add("Leeftijd", l.getLeeftijd());
			job.add("Achternaam", l.getAchternaam());
			job.add("Voornaam", l.getVoornaam());
			job.add("Bondsnummer", l.getBondsnummer());
			job.add("Actief", l.isActief());
			jab.add(job);
		}
		JsonArray array = jab.build();
		System.out.println("doe het");
		return array.toString();
	}
	
	@POST
	@Path("/Insert")
	@Produces("application/json")	public void insert(@FormParam("Email") String Email,
						@FormParam("Telefoonnummer") int Telefoonnummer,
						@FormParam("Leeftijd") int Leeftijd,
						@FormParam("Achternaam") String Achternaam,
						@FormParam("Voornaam") String Voornaam,
						@FormParam("Bondsnummer") int Bondsnummer,
						@FormParam("Actief") boolean Actief){
		IPASSService service = sp.getIPASSService();
		Lid Lid = new Lid(Email, Telefoonnummer, Leeftijd, Achternaam, Voornaam, Bondsnummer, Actief, null);
		service.insertLid(Lid);
	}
	
	@PUT
	@Path("/Update/{Bondsnummer}")
	@Produces("applciation/json")
	public void update(@PathParam("Bondsnummer") int Bondsnummer,
						@FormParam("Wachtwoord") String Wachtwoord){
		IPASSService service = sp.getIPASSService();
		Lid Lid = service.findLidOnBondsnummer(Bondsnummer);
		service.updateLid(Lid);
	}
	
	@DELETE
	@Path("Delete/{Bondsnummer}")
	public void deleteLid(@PathParam("Bondsnummer") int Bondsnummer){
		IPASSService service = sp.getIPASSService();
		service.delete(service.findLidOnBondsnummer(Bondsnummer));
	}
	
}
