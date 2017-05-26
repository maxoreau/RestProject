package com.maxoreau.zorglub.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.maxoreau.zorglub.dao.ContactDaoInDatabase;
import com.maxoreau.zorglub.dao.daoGenerique;
import com.maxoreau.zorglub.models.Contact;


@Path("/contacts")
public class RestMax {
	private static daoGenerique<Contact> dao;
	
	static  {
		dao = new ContactDaoInDatabase();
	}
		
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void createContact(Contact jsonClient) {
		dao.create(jsonClient);
	}
	
	@GET
	@Path("{string}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Contact> searchByString(@PathParam("string") String string) {
		return dao.readByName(string);
	}
	
	@GET
	@Path("id-{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Contact searchById(@PathParam("id") int id) {
		return dao.readById(id);
	}
		
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Contact> getAllJson() {
		return dao.getAll();
			
	}
	
	@PUT
	@Path("{name}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateContact(Contact contact) {
		dao.update(contact);
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteContact(int contactId) {
		dao.delete(contactId);
	}	
	
}