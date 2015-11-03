package com.donalwebapp.service;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.donalwebapp.entities.Users;


@Path("/Users")
@Stateless
@LocalBean
public class UsersWS {

	@EJB(beanName="online")
	private Users usersDAO;
	
	public UsersWS(){
		
	}
	
	public UsersWS(final Users usersDAO){
		this.usersDAO = usersDAO;
	}
	
	@GET
	@Path("/{id}&{pw}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response authenticateUser(@PathParam("id") final String userId, @PathParam("pw") final String password){
		String[] userType = {"invalid"};
//		final List<Users> users = usersDAO.get
		
		return Response.status(200).entity(userType).build();
	}
}
