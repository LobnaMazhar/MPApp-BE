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
import com.models.ScenarioModel;

@Path("/")
public class ScenarioServices {
	
	@POST
	@Path("/getScenarios")
	@Produces(MediaType.TEXT_PLAIN)
	public String getScenarios() {
		ArrayList<ScenarioModel> scenarios = ScenarioModel.getScenarios();
		JSONArray json = new JSONArray();
		for (int i = 0; i < scenarios.size(); ++i) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("scenarioID", scenarios.get(i).getScenarioID());
			jsonObj.put("scenarioNumber", scenarios.get(i).getScenarioNumber());
			json.add(jsonObj);
		}
		return json.toJSONString();
	}
	
	@POST
	@Path("/addScenario")
	@Produces(MediaType.TEXT_PLAIN)
	public String addScenario(@FormParam("scenarioNumber") int scenarioNumber) {
		boolean added = ScenarioModel.addScenario(scenarioNumber);
		JSONObject json = new JSONObject();
		json.put("added", added);
		return json.toJSONString();
	}
	
	@POST
	@Path("/deleteScenario")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteScenario(@FormParam("scenarioID") int scenarioID){
		boolean deleted = ScenarioModel.deleteScenario(scenarioID);
		JSONObject json = new JSONObject();
		json.put("deleted", deleted);
		return json.toJSONString();
			
	}
	
	@POST
	@Path("/editScenario")
	@Produces(MediaType.TEXT_PLAIN)
	public String editScenario(@FormParam("scenarioID") int scenarioID, @FormParam("scenarioNumber") int scenarioNumber){
		boolean edited = ScenarioModel.editScenario(scenarioID, scenarioNumber);
		JSONObject json = new JSONObject();
		json.put("edited", edited);
		return json.toJSONString();
			
	}
	
	@POST
	@Path("/checkScenarioNumber")
	@Produces(MediaType.TEXT_PLAIN)
	public String checkScenarioNumber(@FormParam("scenarioNumber") int scenarioNumber) {
		boolean exists = ScenarioModel.checkScenarioNumberExists(scenarioNumber);
		JSONObject json = new JSONObject();
		json.put("exists", exists);
		return json.toJSONString();
	}
}
