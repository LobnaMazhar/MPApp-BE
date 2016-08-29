package com.services;

import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.models.ProjectModel;
import com.models.ProjectsInSitesModel;

@Path("/")
public class ProjectsInSitesServices {

	@POST
	@Path("/addProjectToSite")
	@Produces(MediaType.TEXT_PLAIN)
	public String addProjectToSite(@FormParam("siteID") String siteID, @FormParam("projectID") int projectID, @FormParam("date") int date, @FormParam("scenarioID") int scenarioID){
		boolean added = ProjectsInSitesModel.addProjectToSite(siteID, projectID, date, scenarioID);
		JSONObject json = new JSONObject();
		json.put("added", added);
		return json.toJSONString();
	}
}
