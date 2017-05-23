package com.maxoreau.restex.rest;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import com.maxoreau.restex.dao.ContactDaoInDatabase;
import com.maxoreau.restex.dao.daoGenerique;
import com.maxoreau.restex.models.Contact;


@Path("/contacts")
public class RestMax {
		
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void createContact(Contact jsonClient) {
		daoGenerique<Contact> dao = new ContactDaoInDatabase();
		dao.create(jsonClient);
		
//		ObjectMapper mapper = new ObjectMapper();
//		daoGenerique<Contact> dao = new ContactDaoInDatabase();
//		try {
//			Contact c = mapper.readValue(jsonClient, Contact.class);
//			dao.create(c);
//			System.out.println("contact créé");
//		} catch (JsonParseException e) {
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		return "le contact a bien ete ajoute";
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
	@Path("/string")
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
	@Produces(MediaType.APPLICATION_JSON)
	public List<Contact> getAllJson() {
		daoGenerique<Contact> dao = new ContactDaoInDatabase();
		return dao.getAll();
		
//		ObjectMapper mapper = new ObjectMapper();
//		String jsonOutput = null;
//		daoGenerique<Contact> dao = new ContactDaoInDatabase();
//		System.out.println("fonction getAllJson() appelée");
//		
//		try {
//			jsonOutput = mapper.writeValueAsString(dao.getAll());
//		} catch (JsonGenerationException e) {
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
			
		
	}
	
//	@PUT
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteContact(int contactId) {
		daoGenerique<Contact> dao = new ContactDaoInDatabase();
		dao.delete(contactId);
	}
	

	
	

	
}