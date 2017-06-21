package nl.hu.v1IPASS.IPASS.webservices;

import java.security.Key;
import java.util.Calendar;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import nl.hu.v1IPASS.IPASS.persistence.LidDAO;

//Bouwen van de webtoken
@Path("/authentication")
public class AuthenticationResource {
 final static public Key key = MacProvider.generateKey();
 @POST
 @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
 public Response authenticateUser(@FormParam("Bondsnummer") String Bondsnummer,
 @FormParam("Wachtwoord") String password) {
 try {
 // Authenticate the user against the database
 LidDAO dao = new LidDAO();
 String role = dao.findGebruikerRole(Bondsnummer, password);

 if (role == null) { throw new IllegalArgumentException("No user found!"); }

 // Issue a token for the user
 Calendar expiration = Calendar.getInstance();
 expiration.add(Calendar.MINUTE, 30);

 String token = Jwts.builder()
 .setSubject(Bondsnummer)
 .claim("role", role)
 .setExpiration(expiration.getTime())
 .signWith(SignatureAlgorithm.HS512, key)
 .compact();
 // Return the token on the response
 return Response.ok(token).build();
 } catch (JwtException | IllegalArgumentException e) {
 return Response.status(Response.Status.UNAUTHORIZED).build();
 }
 }
} 