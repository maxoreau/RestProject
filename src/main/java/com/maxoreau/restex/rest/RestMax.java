package com.maxoreau.restex.rest;

import java.util.List;

//package com.mkyong.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

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
	
	@GET
	@Path("nom-{nom}-prenom-{prenom}-numero-{numero}")
	public String createContact(@PathParam("nom") String nom, @PathParam("prenom") String prenom, @PathParam("numero") String numero) {
		daoGenerique<Contact> dao = new ContactDaoInDatabase();
		dao.create(new Contact(prenom, nom, numero));
		return "le contact a bien ete ajoute";
	}
	
	
	@GET
	public Response getAll() {
		String output = "Bonjour, voici la liste des contacts : ";
		daoGenerique<Contact> dao = new ContactDaoInDatabase();
		
		for (Contact contact : dao.getAll()) {
			output += contact.toString();
		}
			
		return Response.status(200).entity(output).build();
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
	@Path("/all")
	public List<Contact> getAllJson() {
		String output = "Bonjour, voici la liste des contacts : ";
		daoGenerique<Contact> dao = new ContactDaoInDatabase();
		
		for (Contact contact : dao.getAll()) {
			output += contact.toString();
		}
			
		return dao.getAll();
	}

	
	

	
}