package com.donalwebapp.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.donalwebapp.admin.AdminDAO;
import com.donalwebapp.entities.Users;


@Path("/Users")
@Stateless
@LocalBean
public class UsersWS {

	@EJB(beanName="online")
	private AdminDAO adminDAO;
	
	public UsersWS(){
		
	}
	
	public UsersWS(final AdminDAO adminDAO){
		this.adminDAO = adminDAO;
	}
	
	@GET
	@Path("/{id}&{pw}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response authenticateUser(@PathParam("id") final String userId, @PathParam("pw") final String password){
		String[] userType = {"invalid"};
		final List<Users> users = adminDAO.getAllUsers();
		for(final Users user : users){
			if(user.getId().equalsIgnoreCase(userId) && user.getPassword().equals(password)){
				userType[0] = user.getUserType();
				break;
			}
		}
		
		return Response.status(200).entity(userType).build();
	}
}
