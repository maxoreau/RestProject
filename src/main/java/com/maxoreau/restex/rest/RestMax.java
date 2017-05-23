package com.maxoreau.restex.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
	}
	
	@GET
	@Path("{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Contact> getByNom(String nom) {
		daoGenerique<Contact> dao = new ContactDaoInDatabase();
		return dao.readByName(nom);
	}
	
	
//	@GET
//	@Path("/string")
//	public Response getAll() {
//		String output = "Bonjour, voici la liste des contacts : ";
//		daoGenerique<Contact> dao = new ContactDaoInDatabase();
//		System.out.println("fonction getAll() appelée");
//		for (Contact contact : dao.getAll()) {
//			output += contact.toString();
//		}
//			
//		return Response.status(200).entity(output).build();
//	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Contact> getAllJson() {
		daoGenerique<Contact> dao = new ContactDaoInDatabase();
		return dao.getAll();
			
	}
	
//	@PUT
//	@Path("{name}")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public void updateContact() {
//	
//	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteContact(int contactId) {
		daoGenerique<Contact> dao = new ContactDaoInDatabase();
		dao.delete(contactId);
	}
	

	
	

	
}