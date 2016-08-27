package com.services;

import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.models.PRVModel;
import com.models.ProjectModel;

@Path("/")
public class PRVServices {
	
	@POST
	@Path("/addProjectToTechnicalPlan")
	@Produces(MediaType.TEXT_PLAIN)
	public String addProjectToTechnicalPlan(@FormParam("projectID") int projectID, @FormParam("regionID") int regionID, @FormParam("vendorID") int vendorID, @FormParam("yearTarget") int yearTarget) {
		boolean added = PRVModel.addPRV(projectID, regionID, vendorID, yearTarget);
		JSONObject json = new JSONObject();
		json.put("added", added);
		return json.toJSONString();
	}
	
	@POST
	@Path("/getPRVs")
	@Produces(MediaType.TEXT_PLAIN)
	public String getPRVs() {
		ArrayList<PRVModel> prvs = PRVModel.getPRVs();
		JSONArray json = new JSONArray();
		for (int i = 0; i < prvs.size(); ++i) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("prvID", prvs.get(i).getPrvID());
			jsonObj.put("prvProjectID", prvs.get(i).getPrvProjectID());
			jsonObj.put("prvRegionID", prvs.get(i).getPrvRegionID());
			jsonObj.put("prvVendorID", prvs.get(i).getPrvVendorID());
			jsonObj.put("prvYearTarget", prvs.get(i).getPrvYearTarget());
			
			String projectName = ProjectModel.getProjectName(prvs.get(i).getPrvProjectID());
			jsonObj.put("projectName", projectName);	
			
			json.add(jsonObj);	
		}
		return json.toJSONString();
	}
	

	@POST
	@Path("/getPRVsByProjectID")
	@Produces(MediaType.TEXT_PLAIN)
	public String getPRVsByProjectID(@FormParam("projectID") int projectID) {
		ArrayList<PRVModel> prvs = PRVModel.getPRVsByProjectID(projectID);
		JSONArray json = new JSONArray();
		for (int i = 0; i < prvs.size(); ++i) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("prvID", prvs.get(i).getPrvID());
			jsonObj.put("prvProjectID", prvs.get(i).getPrvProjectID());
			jsonObj.put("prvRegionID", prvs.get(i).getPrvRegionID());
			jsonObj.put("prvVendorID", prvs.get(i).getPrvVendorID());
			jsonObj.put("prvYearTarget", prvs.get(i).getPrvYearTarget());
			
	//		String projectName = ProjectModel.getProjectName(prvs.get(i).getPrvProjectID());
	//		jsonObj.put("projectName", projectName);	
			json.add(jsonObj);	
		}
		return json.toJSONString();
	}
}
