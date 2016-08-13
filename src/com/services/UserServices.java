package com.services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import com.models.UserModel;

@Path("/")
public class UserServices {

	@POST
	@Path("/login")
	@Produces(MediaType.TEXT_PLAIN)
	public String login(@FormParam("userName") String name,
			@FormParam("userPassword") String password) {
		UserModel user = UserModel.login(name, password);
		JSONObject json = new JSONObject();
		json.put("userID", user.getUserID());
		json.put("userName", user.getUserName());
		return json.toJSONString();
	}
	
	@POST
	@Path("/addUser")
	@Produces(MediaType.TEXT_PLAIN)
	public String addUser(@FormParam("userName") String name,
			@FormParam("userPassword") String password) {
		UserModel user = UserModel.addUser(name, password);
		JSONObject json = new JSONObject();
		json.put("userName", user.getUserName());
		return json.toJSONString();
	}
}
