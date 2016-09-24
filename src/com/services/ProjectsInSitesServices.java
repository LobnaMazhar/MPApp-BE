package com.services;

import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.models.DateModel;
import com.models.ProjectModel;
import com.models.ProjectsInSitesModel;
import com.models.RegionModel;
import com.models.SiteModel;

@Path("/")
public class ProjectsInSitesServices {

	@POST
	@Path("/addProjectToSite")
	@Produces(MediaType.TEXT_PLAIN)
	public String addProjectToSite(@FormParam("siteID") String siteID, @FormParam("projectID") int projectID, @FormParam("date") int date, @FormParam("scenarioID") int scenarioID, @FormParam("Rollout/Expansion") int rollout_expansion){
		boolean added = ProjectsInSitesModel.addProjectToSite(siteID, projectID, date, scenarioID, rollout_expansion);
		JSONObject json = new JSONObject();
		json.put("added", added);
		return json.toJSONString();
	}
	
	@POST
	@Path("/checkSiteAndProjectExists")
	@Produces(MediaType.TEXT_PLAIN)
	public String checkDuplicateSiteIDSameProject(@FormParam("siteID") String siteID, @FormParam("projectID") int projectID) {
		boolean exists = ProjectsInSitesModel.checkDuplicateSiteIDinSameProject(siteID, projectID);
		JSONObject json = new JSONObject();
		json.put("exists", exists);
		return json.toJSONString();
	}
	
	@POST
	@Path("/getSites")
	@Produces(MediaType.TEXT_PLAIN)
	public String getRolloutSitesIDs(@FormParam("rollout/expansion") int rollout_expansion) {
		ArrayList<ProjectsInSitesModel> sites = ProjectsInSitesModel.getSites(rollout_expansion);
		JSONArray json = new JSONArray();
		for (int i = 0; i < sites.size(); ++i) {
			JSONObject jsonObj = new JSONObject();
			
			jsonObj.put("projectsInSitesID", sites.get(i).getProjectsInSitesID());
			jsonObj.put("siteID", sites.get(i).getProjectsInSitesSiteID());
			jsonObj.put("projectID", sites.get(i).getProjectsInSitesProjectID());			
			jsonObj.put("date", sites.get(i).getProjectsInSitesDate());
			
			String projectName = ProjectModel.getProjectName(sites.get(i).getProjectsInSitesProjectID());
			jsonObj.put("projectName", projectName);
			
			String monthName = DateModel.getMonthName(sites.get(i).getProjectsInSitesDate());
			jsonObj.put("monthName", monthName);
			
			String regionName = RegionModel.getRegionNameForSite(sites.get(i).getProjectsInSitesSiteID());
			jsonObj.put("regionName", regionName);
			
			json.add(jsonObj);
		}
		return json.toJSONString();
	}
}
