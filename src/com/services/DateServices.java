package com.services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import com.models.DateModel;
import com.models.ItemModel;

@Path("/")
public class DateServices {
	
	@POST
	@Path("/getMonthName")
	@Produces(MediaType.TEXT_PLAIN)
	public String getMonthName(@FormParam("monthID") int monthID) {
		String monthName = DateModel.getMonthName(monthID);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("monthName", monthName);
		
		return jsonObj.toJSONString();
	}
}
