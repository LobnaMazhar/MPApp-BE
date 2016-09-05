package com.services;

import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.models.PRVMModel;
import com.models.ProjectModel;

@Path("/")
public class PRVMServices {

	@POST
	@Path("/addPRVM")
	@Produces(MediaType.TEXT_PLAIN)
	public String addPRVM(@FormParam("projectID") int projectID,
			@FormParam("regionID") int regionID,
			@FormParam("vendorID") int vendorID,
			@FormParam("monthID") int monthID,
			@FormParam("phasing") int phasing,
			@FormParam("yearTarget") int yearTarget) {
		boolean added = PRVMModel.addPRVM(projectID, regionID, monthID,
				vendorID, phasing, yearTarget);
		JSONObject json = new JSONObject();
		json.put("added", added);
		return json.toJSONString();
	}

	@POST
	@Path("/getPRVMs")
	@Produces(MediaType.TEXT_PLAIN)
	public String getPRVMs() {
		ArrayList<PRVMModel> prvms = PRVMModel.getPRVMs();
		JSONArray json = new JSONArray();
		for (int i = 0; i < prvms.size(); ++i) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("prvmID", prvms.get(i).getPrvmID());
			jsonObj.put("prvmProjectID", prvms.get(i).getPrvmProjectID());
			jsonObj.put("prvmRegionID", prvms.get(i).getPrvmRegionID());
			jsonObj.put("prvmVendorID", prvms.get(i).getPrvmVendorID());
			jsonObj.put("prvmMonthID", prvms.get(i).getPrvmMonthID());
			jsonObj.put("prvmPhasing", prvms.get(i).getPrvmPhasing());
			jsonObj.put("prvmYearTarget", prvms.get(i).getPrvmYearTarget());

			String projectName = ProjectModel.getProjectName(prvms.get(i)
					.getPrvmProjectID());
			jsonObj.put("projectName", projectName);

			json.add(jsonObj);
		}
		return json.toJSONString();
	}

	@POST
	@Path("/getPRVMsByProjectID")
	@Produces(MediaType.TEXT_PLAIN)
	public String getPRVMsByProjectID(@FormParam("projectID") int projectID) {
		ArrayList<PRVMModel> prvms = PRVMModel.getPRVMsByProjectID(projectID);
		JSONArray json = new JSONArray();
		for (int i = 0; i < prvms.size(); ++i) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("prvmID", prvms.get(i).getPrvmID());
			jsonObj.put("prvmProjectID", prvms.get(i).getPrvmProjectID());
			jsonObj.put("prvmRegionID", prvms.get(i).getPrvmRegionID());
			jsonObj.put("prvmVendorID", prvms.get(i).getPrvmVendorID());
			jsonObj.put("prvmMonthID", prvms.get(i).getPrvmMonthID());
			jsonObj.put("prvmPhasing", prvms.get(i).getPrvmPhasing());
			jsonObj.put("prvmYearTarget", prvms.get(i).getPrvmYearTarget());

			String projectName = ProjectModel.getProjectName(prvms.get(i)
					.getPrvmProjectID());
			jsonObj.put("projectName", projectName);

			json.add(jsonObj);
		}
		return json.toJSONString();
	}

	@POST
	@Path("/getPhasing")
	@Produces(MediaType.TEXT_PLAIN)
	public String getPhasing(@FormParam("projectID") int projectID,
			@FormParam("regionID") int regionID,
			@FormParam("vendorID") int vendorID) {
		ArrayList<PRVMModel> prvms = PRVMModel.getPhasing(projectID, regionID,
				vendorID);
		JSONArray json = new JSONArray();
		for (int i = 0; i < prvms.size(); ++i) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("monthID", prvms.get(i).getPrvmMonthID());
			jsonObj.put("monthPhase", prvms.get(i).getPrvmPhasing());
			json.add(jsonObj);
		}
		return json.toJSONString();
	}

	@POST
	@Path("/getMonthPhasing")
	@Produces(MediaType.TEXT_PLAIN)
	public String getMonthPhasing(@FormParam("projectID") int projectID,
			@FormParam("regionID") int regionID,
			@FormParam("vendorID") int vendorID,
			@FormParam("monthID") int monthID) {
		int monthPhase = PRVMModel.getMonthPhasing(projectID, regionID, vendorID, monthID);
		JSONObject json = new JSONObject();
		json.put("monthPhase", monthPhase);

		return json.toJSONString();
	}

	@POST
	@Path("/editMonthlyPhasing")
	@Produces(MediaType.TEXT_PLAIN)
	public String editPhasing(@FormParam("projectID") int projectID,
			@FormParam("regionID") int regionID,
			@FormParam("vendorID") int vendorID,
			@FormParam("monthID") int monthID,
			@FormParam("monthPhase") int monthPhasing) {
		boolean edited = PRVMModel.editPhasing(projectID, regionID, vendorID,
				monthID, monthPhasing);
		JSONObject json = new JSONObject();
		json.put("edited", edited);
		return json.toJSONString();
	}

	@POST
	@Path("/editYearTarget")
	@Produces(MediaType.TEXT_PLAIN)
	public String editYearTarget(@FormParam("projectID") int projectID,
			@FormParam("regionID") int regionID,
			@FormParam("vendorID") int vendorID,
			@FormParam("yearTarget") int yearTarget) {
		boolean edited = PRVMModel.editYearTarget(projectID, regionID,
				vendorID, yearTarget);
		JSONObject json = new JSONObject();
		json.put("edited", edited);
		return json.toJSONString();
	}

	@POST
	@Path("/checkIfPRVExists")
	@Produces(MediaType.TEXT_PLAIN)
	public String checkIfPRVExists(@FormParam("projectID") int projectID,
			@FormParam("regionID") int regionID,
			@FormParam("vendorID") int vendorID) {
		boolean exists = PRVMModel.checkIfPRVExists(projectID, regionID,
				vendorID);
		JSONObject json = new JSONObject();
		json.put("exists", exists);
		return json.toJSONString();
	}

	@POST
	@Path("/checkEnoughPhasing")
	@Produces(MediaType.TEXT_PLAIN)
	public String checkEnoughPhasing(@FormParam("projectID") int projectID,
			@FormParam("regionID") int regionID,
			@FormParam("vendorID") int vendorID,
			@FormParam("monthID") int monthID) {

		int phasingMonthID = PRVMModel.checkEnoughPhasing(projectID, regionID,
				vendorID, monthID);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("monthID", phasingMonthID);
		return jsonObj.toJSONString();
	}

	@POST
	@Path("/updatePhasing")
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePhasing(@FormParam("projectID") int projectID,
			@FormParam("regionID") int regionID,
			@FormParam("vendorID") int vendorID,
			@FormParam("monthID") int monthID) {
		boolean updated = PRVMModel.updatePhasing(projectID, regionID,
				vendorID, monthID);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("updated", updated);
		return jsonObj.toJSONString();
	}
}
