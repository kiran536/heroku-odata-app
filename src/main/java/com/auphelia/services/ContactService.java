package main.java.com.auphelia.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import main.java.com.auphelia.models.Contact;

@Path("/contact")
public class ContactService {

	@GET()
	@Produces("application/json; charset=UTF-8")
	public Contact sayHello() {
		Contact c = new Contact("Massi", "AZAMOUM", "rue jean-talon", "Montréal", "QC", "H2L 1X7");
		
		return c;
	}
}
