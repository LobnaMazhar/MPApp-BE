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
}
