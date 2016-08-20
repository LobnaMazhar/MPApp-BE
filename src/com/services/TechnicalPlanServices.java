package com.services;

import java.util.ArrayList;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.models.ItemModel;
import com.models.TechnicalPlanModel;

@Path("/")
public class TechnicalPlanServices {
	
	@POST
	@Path("/getTechnicalPlans")
	@Produces(MediaType.TEXT_PLAIN)
	public String getTechnicalPlans() {
		ArrayList<TechnicalPlanModel> technicalPlans = TechnicalPlanModel.getTechnicalPlans();
		JSONArray json = new JSONArray();
		for (int i = 0; i < technicalPlans.size(); ++i) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("technicalPlanID", technicalPlans.get(i).getTechnicalPlanID());
			jsonObj.put("technicalPlanName", technicalPlans.get(i).getTechnicalPlanName());
			json.add(jsonObj);
		}
		return json.toJSONString();
	}
}
