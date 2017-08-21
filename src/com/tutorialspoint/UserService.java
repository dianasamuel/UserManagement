package com.tutorialspoint;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import rx.Observable;  
@Path("/UserService") 

public class UserService 
{
	UserDao userDao = new UserDao();
	private static final String SUCCESS_RESULT = "{\"result\": \"success\"}";
	private static final String FAILURE_RESULT = "{\"result\": \"failure\"}";
	
	
	@GET
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsersJSON()
	{
		return userDao.getAllUsers();
	}
	
	@GET
	@Path("/users/{userid}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("userid") int userid) 
	{
		return userDao.getUser(userid);
	}
	
	@POST
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String createUser(@FormParam("id") int id,
			@FormParam("name") String name,
		      @FormParam("profession") String profession,
		      @Context HttpServletResponse servletResponse) throws IOException 
	{
		User user = new User(id, name, profession);
		int result = userDao.addUser(user);
		if(result==1)
			return SUCCESS_RESULT;
		return FAILURE_RESULT;
	}
	
	@PUT
	@Path("/users")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateUser(@FormParam("id") int id, @FormParam("name") String name,
			@FormParam("profession") String profession, @Context HttpServletResponse servletResponse)
			throws IOException {
		User user = new User(id, name, profession);
		int result = userDao.updateUser(user);
		if (result == 1) {
			return SUCCESS_RESULT;
		}
		return FAILURE_RESULT;
	}
	
	@DELETE
	@Path("/users/{userid}")
	@Produces(MediaType.APPLICATION_XML)
	public String deleteUser(@PathParam("userid") int userid) {
		int result = userDao.deleteUser(userid);
		if (result == 1) {
			return SUCCESS_RESULT;
		}
		return FAILURE_RESULT;
	}

	@OPTIONS
	@Path("/users")
	@Produces(MediaType.APPLICATION_XML)
	public String getSupportedOperations() {
		return "<operations>GET, PUT, POST, DELETE</operations>";
	}
	   
	@GET
	@Path("/diana")
	@Produces(MediaType.APPLICATION_JSON)
	public User getDiana()
	{
		return new User(1, "Diana","Software Engineer");
	}
	
	
	@GET
	@Path("/user")
	@Produces(MediaType.TEXT_PLAIN)
	public String getUser()
	{
		return "Diana";
	}
	
	@GET
	@Path("/usersplain")
	@Produces(MediaType.TEXT_PLAIN)
	public String getUsers()
	{
	//	return userDao.getAllUsers();
		 String s = "";
		 List<User> users = userDao.getAllUsers();
		 for(User u : users)
		 {
			 s += u;
		 }
		 return s;
	}
	
	
	@GET
	@Path("/usersrx")
	@Produces(MediaType.APPLICATION_JSON)
	public Observable<List<User>> getRXUsers()
	{
		return Observable.just(userDao.getAllUsers());
	}

}


