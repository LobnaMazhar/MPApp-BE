package com.services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import com.models.BOMModel;
import com.models.ItemModel;

@Path("/")
public class BOMServices {
	
	@POST
	@Path("/addBOM")
	@Produces(MediaType.TEXT_PLAIN)
	public String addItem(@FormParam("siteID") int siteID,
			@FormParam("date") String date, @FormParam("regionID") int regionID) {
		int bomID = BOMModel.addBOM(siteID, date, regionID);
		JSONObject json = new JSONObject();
		json.put("bomID", bomID);
		return json.toJSONString();
	}
}
