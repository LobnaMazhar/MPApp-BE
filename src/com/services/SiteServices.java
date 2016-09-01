package com.services;

import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.models.ProjectsInSitesModel;
import com.models.ScenarioModel;
import com.models.SiteModel;

@Path("/")
public class SiteServices {

	@POST
	@Path("/addSite")
	@Produces(MediaType.TEXT_PLAIN)
	public String addSite(@FormParam("siteID") String siteID,
			@FormParam("date") int date, @FormParam("regionID") int regionID,
			@FormParam("projectID") int projectID,
			@FormParam("scenarioID") int scenarioID,
			@FormParam("vendorID") int vendorID,
			@FormParam("Rollout/Expansion") int rollout_expansion) {
		boolean addedSite = SiteModel.addSite(siteID, regionID, vendorID);
		boolean addedProjectToSite = ProjectsInSitesModel.addProjectToSite(
				siteID, projectID, date, scenarioID, rollout_expansion);
		JSONObject json = new JSONObject();
		json.put("added", addedSite && addedProjectToSite);
		return json.toJSONString();
	}

	@POST
	@Path("/checkIfSiteExists")
	@Produces(MediaType.TEXT_PLAIN)
	public String checkIfSiteExists(@FormParam("siteID") String siteID) {
		boolean exists = SiteModel.checkIfSiteExists(siteID);
		JSONObject json = new JSONObject();
		json.put("exists", exists);
		return json.toJSONString();
	}

	@POST
	@Path("/getSiteByID")
	@Produces(MediaType.TEXT_PLAIN)
	public String getSiteByID(@FormParam("siteID") String siteID) {
		SiteModel site = SiteModel.getSiteByID(siteID);

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("siteID", site.getSiteID());
		jsonObj.put("regionID", site.getRegionID());
		jsonObj.put("vendorID", site.getVendorID());

		return jsonObj.toJSONString();
	}
}
