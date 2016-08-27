package com.services;

import java.util.ArrayList;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.models.RegionModel;

@Path("/")
public class RegionServices {
	
	@POST
	@Path("/getRegions")
	@Produces(MediaType.TEXT_PLAIN)
	public String getRegions() {
		ArrayList<RegionModel> regions = RegionModel.getRegions();
		JSONArray json = new JSONArray();
		for (int i = 0; i < regions.size(); ++i) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("regionID", regions.get(i).getRegionID());
			jsonObj.put("regionName", regions.get(i).getRegionName());
			json.add(jsonObj);
		}
		return json.toJSONString();
	}
}
