package com.maxoreau.restex.rest;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.annotate.JsonClass;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.maxoreau.restex.dao.ContactDaoInDatabase;
import com.maxoreau.restex.dao.daoGenerique;
import com.maxoreau.restex.models.Contact;


@Path("/carnet")
public class RestMax {
	
//	@GET
//	public Response getMsg() {
//		String output = "hello sans path";
//
//		return Response.status(200).entity(output).build();
//	}
//	
//	@GET
//	@Path("/un")
//	public Response getMsgUn() {
//		String output = "hello";
//
//		return Response.status(200).entity(output).build();
//
//	}
	
	@POST
	@Consumes(value ={"application/x-www-form-urlencoded", "application/json"})
	public String createContact(@FormParam("contact") String contact) {
		ObjectMapper mapper = new ObjectMapper();
		daoGenerique<Contact> dao = new ContactDaoInDatabase();
		try {
			Contact c = mapper.readValue(contact, Contact.class);
			dao.create(c);
			System.out.println("contact créé");
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "le contact a bien ete ajoute";
	}
	
	@GET
	@Path("{name}")
	public Response getByNom(@PathParam ("name") String nom) {
		String output = "Un contact particuler : ";
		daoGenerique<Contact> dao = new ContactDaoInDatabase();
		
		if (dao.readByName(nom).isEmpty()) {
			output = ("aucun contact avec le nom : " + nom);
		} else {
			for (Contact contact : dao.readByName(nom)) {
				output += contact.toString();
			}			
		}
		
		return Response.status(200).entity(output).build();
	}
	
	
	@GET
	public Response getAll() {
		String output = "Bonjour, voici la liste des contacts : ";
		daoGenerique<Contact> dao = new ContactDaoInDatabase();
		System.out.println("fonction getAll() appelée");
		for (Contact contact : dao.getAll()) {
			output += contact.toString();
		}
			
		return Response.status(200).entity(output).build();
	}
	
	@GET
	@Path("/json")
	@Produces("application/json")
	public String getAllJson() {
		ObjectMapper mapper = new ObjectMapper();
		String jsonOutput = null;
		daoGenerique<Contact> dao = new ContactDaoInDatabase();
		System.out.println("fonction getAllJson() appelée");
		
		try {
			jsonOutput = mapper.writeValueAsString(dao.getAll());
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		return jsonOutput;
	}
	
//	@PUT

	
	

	
}