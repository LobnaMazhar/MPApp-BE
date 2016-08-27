package com.services;

import java.util.ArrayList;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
}
