package com.services;

import java.util.ArrayList;

import javax.ws.rs.FormParam;
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
	
	@POST
	@Path("/getRegionByID")
	@Produces(MediaType.TEXT_PLAIN)
	public String getRegionByID(@FormParam("regionID") int regionID) {
		RegionModel regionObject = RegionModel.getRegion(regionID);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("regionID", regionObject.getRegionID());
		jsonObj.put("regionName", regionObject.getRegionName());
		return jsonObj.toJSONString();
	}
	
	@POST
	@Path("/getRegionNameByID")
	@Produces(MediaType.TEXT_PLAIN)
	public String getRegionName(@FormParam("regionID") int regionID) {
		String regionName = RegionModel.getRegionName(regionID);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("regionName", regionName);
		return jsonObj.toJSONString();
	}
	
	@POST
	@Path("/getRegionNameForSite")
	@Produces(MediaType.TEXT_PLAIN)
	public String getRegionNameForSite(@FormParam("siteID") String siteID) {
		String regionName = RegionModel.getRegionNameForSite(siteID);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("regionName", regionName);
		return jsonObj.toJSONString();
	}
	
	@POST
	@Path("/getRegionsWithProject")
	@Produces(MediaType.TEXT_PLAIN)
	public String getRegionsWithProject(@FormParam("projectID") int projectID) {
		ArrayList<RegionModel> regions = RegionModel.getRegionsWithProject(projectID);
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
