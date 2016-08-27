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
	@Path("/getProjectsInSite")
	@Produces(MediaType.TEXT_PLAIN)
	public String getProjectsInSite(@FormParam("siteID") int siteID) {
		ArrayList<ProjectsInSitesModel> projectsInSite = ProjectsInSitesModel.getProjectsInSite(siteID);
		JSONArray json = new JSONArray();
		for (int i = 0; i < projectsInSite.size(); ++i) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("projectInSiteID", projectsInSite.get(i).getProjectsInSitesID());
			jsonObj.put("projectInSiteSiteID", projectsInSite.get(i).getProjectsInSitesSiteID());
			jsonObj.put("projectsInSitesProjectID", projectsInSite.get(i).getProjectsInSitesProjectID());
			
			String projectName = ProjectModel.getProjectName(projectsInSite.get(i).getProjectsInSitesProjectID());
			jsonObj.put("projectName", projectName);	
			json.add(jsonObj);
		}
		return json.toJSONString();
	}
	
	@POST
	@Path("/getSitesInProject")
	@Produces(MediaType.TEXT_PLAIN)
	public String getSitesInProject(@FormParam("projectID") int projectID) {
		ArrayList<ProjectsInSitesModel> sitesInProject = ProjectsInSitesModel.getSitesInProject(projectID);
		JSONArray json = new JSONArray();
		for (int i = 0; i < sitesInProject.size(); ++i) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("projectInSiteID", sitesInProject.get(i).getProjectsInSitesID());
			jsonObj.put("projectsInSitesSiteID", sitesInProject.get(i).getProjectsInSitesSiteID());
			jsonObj.put("projectsInSitesProjectID", sitesInProject.get(i).getProjectsInSitesProjectID());
			
			String siteID = sitesInProject.get(i).getProjectsInSitesSiteID();
			jsonObj.put("siteID", siteID);
			json.add(jsonObj);
		}
		return json.toJSONString();
	}
	
	@POST
	@Path("/addProjectToSite")
	@Produces(MediaType.TEXT_PLAIN)
	public String addProjectToSite(@FormParam("projectID") int projectID, @FormParam("siteID") int siteID) {
		boolean added = ProjectsInSitesModel.addProjectToSite(projectID, siteID);
		JSONObject json = new JSONObject();
		json.put("added", added);
		return json.toJSONString();
	}
	
	@POST
	@Path("/checkDuplicateSiteIDSameProject")
	@Produces(MediaType.TEXT_PLAIN)
	public String checkDuplicateSiteIDSameProject(@FormParam("siteID") String siteID, @FormParam("projectID") int projectID) {
		boolean duplicate = ProjectsInSitesModel.checkDuplicateSiteIDinSameProject(siteID, projectID);
		JSONObject json = new JSONObject();
		json.put("duplicate", duplicate);
		return json.toJSONString();
	}
}
