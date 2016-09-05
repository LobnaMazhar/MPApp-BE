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
import com.models.VendorModel;

@Path("/")
public class VendorServices {
	
	@POST
	@Path("/getVendors")
	@Produces(MediaType.TEXT_PLAIN)
	public String getVendors() {
		ArrayList<VendorModel> vendors = VendorModel.getVendors();
		JSONArray json = new JSONArray();
		for (int i = 0; i < vendors.size(); ++i) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("vendorID", vendors.get(i).getVendorID());
			jsonObj.put("vendorName", vendors.get(i).getVendorName());
			json.add(jsonObj);
		}
		return json.toJSONString();
	}
	
	@POST
	@Path("/getVendorNameByID")
	@Produces(MediaType.TEXT_PLAIN)
	public String getVendorName(@FormParam("vendorID") int vendorID) {
		String vendorName = VendorModel.getVendorName(vendorID);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("vendorName", vendorName);
		return jsonObj.toJSONString();
	}
	
	@POST
	@Path("/getVendorsWithProjectAndRegion")
	@Produces(MediaType.TEXT_PLAIN)
	public String getVendorsWithProjectAndRegion(@FormParam("projectID") int projectID, @FormParam("regionID") int regionID) {
		ArrayList<VendorModel> vendors = VendorModel.getVendorsWithProjectAndRegion(projectID, regionID);
		JSONArray json = new JSONArray();
		for (int i = 0; i < vendors.size(); ++i) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("vendorID", vendors.get(i).getVendorID());
			jsonObj.put("vendorName", vendors.get(i).getVendorName());
			json.add(jsonObj);
		}
		return json.toJSONString();
	}
}
