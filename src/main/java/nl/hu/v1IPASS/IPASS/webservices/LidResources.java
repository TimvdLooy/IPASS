package nl.hu.v1IPASS.IPASS.webservices;
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
		return array.toString();
	}
	
	@POST
	@RolesAllowed({"User","Admin"})
	@Path("/vindLid")
	@Produces("application/json")
	public String findLid(@FormParam("Bondsnummer") int Bondsnummer){
		Lid lid = service.findLidOnBondsnummer(Bondsnummer);
		job.add("Email", lid.getEmail());
		job.add("Telefoonnummer", lid.getTelefoonnummer());
		job.add("Leeftijd", lid.getLeeftijd());
		job.add("Achternaam", lid.getAchternaam());
		job.add("Voornaam", lid.getVoornaam());
		job.add("Bondsnummer", lid.getBondsnummer());
		job.add("Actief", lid.isActief());
		job.add("role", lid.getRole());
		jab.add(job);
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@POST
	@RolesAllowed("Admin")
	@Path("/Insert")
	@Produces("application/json")	public void insert(@FormParam("email") String Email,
						@FormParam("Telefoonnummer") int Telefoonnummer,
						@FormParam("Leeftijd") int Leeftijd,
						@FormParam("Achternaam") String Achternaam,
						@FormParam("Voornaam") String Voornaam,
						@FormParam("Bondsnummer") int Bondsnummer){
		IPASSService service = sp.getIPASSService();
		String role = "User";
		Lid Lid = new Lid(Email, Telefoonnummer, Leeftijd, Achternaam, Voornaam, Bondsnummer, false, null, role);
		service.insertLid(Lid);
	}
	
	@PUT
	@Path("/Update")
	@Produces("application/json")
	public void update(@FormParam("Bondsnummer") int Bondsnummer,
						@FormParam("Wachtwoord") String Wachtwoord){
		System.out.println("we tried");
		IPASSService service = sp.getIPASSService();
		Lid Lid = service.findLidOnBondsnummer(Bondsnummer);
		if(Lid.isActief() == false){
			Lid.setWachtwoord(Wachtwoord);
			service.updateLid(Lid);
		}
		else{
			System.out.println("poging tot wachtwoord verandering van actief account");
		}
	}
	
	@DELETE
	@RolesAllowed("Admin")
	@Path("Delete/{Bondsnummer}")
	public void deleteLid(@PathParam("Bondsnummer") int Bondsnummer){
		IPASSService service = sp.getIPASSService();
		service.delete(service.findLidOnBondsnummer(Bondsnummer));
	}
	
}
