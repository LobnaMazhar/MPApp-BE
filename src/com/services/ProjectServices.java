package com.services;

import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.models.ItemModel;
import com.models.PRVModel;
import com.models.ProjectModel;
import com.models.ProjectsInSitesModel;

@Path("/")
public class ProjectServices {

	@POST
	@Path("/getProjects")
	@Produces(MediaType.TEXT_PLAIN)
	public String getProjects() {
		ArrayList<ProjectModel> projects = ProjectModel.getProjects();
		JSONArray json = new JSONArray();
		for (int i = 0; i < projects.size(); ++i) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("projectID", projects.get(i).getProjectID());
			jsonObj.put("projectName", projects.get(i).getProjectName());
			json.add(jsonObj);
		}
		return json.toJSONString();
	}
	
	@POST
	@Path("/getProjectName")
	@Produces(MediaType.TEXT_PLAIN)
	public String getProjectName(@FormParam("projectID")int projectID) {
		String projectName = ProjectModel.getProjectName(projectID);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("projectName", projectName);
		return jsonObj.toJSONString();
	}
	
	@POST
	@Path("/getProjectID")
	@Produces(MediaType.TEXT_PLAIN)
	public String getProjectID(@FormParam("projectName") String projectName) {
		int projectID = ProjectModel.getProjectID(projectName);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("projectID", projectID);
		return jsonObj.toJSONString();
	}
	
	@POST
	@Path("/addProject")
	@Produces(MediaType.TEXT_PLAIN)
	public String addProject(@FormParam("projectName") String projectName) {
		boolean added = ProjectModel.addProject(projectName);
		JSONObject json = new JSONObject();
		json.put("added", added);
		return json.toJSONString();
	}
	
	@POST
	@Path("/deleteProject")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteProject(@FormParam("projectID") int projectID){
		boolean deleted = ProjectModel.deleteProject(projectID);
		JSONObject json = new JSONObject();
		json.put("deleted", deleted);
		return json.toJSONString();
	}
	
	@POST
	@Path("/editProject")
	@Produces(MediaType.TEXT_PLAIN)
	public String editProject(@FormParam("projectID") int projectID, @FormParam("projectName") String projectName){
		boolean edited = ProjectModel.editProject(projectID, projectName);
		JSONObject json = new JSONObject();
		json.put("edited", edited);
		return json.toJSONString();
			
	}
	
	@POST
	@Path("/getProjectsInPRVs")
	@Produces(MediaType.TEXT_PLAIN)
	public String getProjectsInPRVs() {
		ArrayList<ProjectModel> projectsInPRVs = ProjectModel.getProjectsInPRVs();
		JSONArray json = new JSONArray();
		for (int i = 0; i < projectsInPRVs.size(); ++i) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("projectID", projectsInPRVs.get(i).getProjectID());
			jsonObj.put("projectName", projectsInPRVs.get(i).getProjectName());	
			json.add(jsonObj);	
		}
		return json.toJSONString();
	}
	
	@POST
	@Path("/getProjectsInSiteBySiteID")
	@Produces(MediaType.TEXT_PLAIN)
	public String getProjectsInSiteBySiteID(@FormParam("siteID") String siteID) {
		ArrayList<ProjectModel> projectsInSite = ProjectModel.getProjectsInSiteBySiteID(siteID);
		JSONArray json = new JSONArray();
		for (int i = 0; i < projectsInSite.size(); ++i) {
			JSONObject jsonObj = new JSONObject();
			
			jsonObj.put("projectID", projectsInSite.get(i).getProjectID());
			jsonObj.put("projectName", projectsInSite.get(i).getProjectName());
			
			json.add(jsonObj);
		}
		return json.toJSONString();
	}
}
