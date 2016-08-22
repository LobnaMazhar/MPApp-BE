package com.services;

import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.models.ItemsInScenariosModel;
import com.models.ScenarioModel;

@Path("/")
public class ItemsInScenariosServices {
	
	@POST
	@Path("/getItemsInScenario")
	@Produces(MediaType.TEXT_PLAIN)
	public String getItemsInScenario(@FormParam("scenarioID") int scenarioID) {
		ArrayList<ItemsInScenariosModel> itemsInScenario = ItemsInScenariosModel.getItemsInScenario(scenarioID);
		JSONArray json = new JSONArray();
		for (int i = 0; i < itemsInScenario.size(); ++i) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("itemsInScenariosID", itemsInScenario.get(i).getItemsInScenariosID());
			jsonObj.put("itemsInScenariosItemID", itemsInScenario.get(i).getItemsInScenariosItemID());
			jsonObj.put("itemsInScenariosScenarioID", itemsInScenario.get(i).getItemsInScenariosScenarioID());
			jsonObj.put("itemsInScenariosItemQuantity", itemsInScenario.get(i).getItemsInScenariosItemQuantity());
			json.add(jsonObj);
		}
		return json.toJSONString();
	}
	
	@POST
	@Path("/addItemsToScenario")
	@Produces(MediaType.TEXT_PLAIN)
	public String addItemsToScenario(@FormParam("itemID") int itemID, @FormParam("scenarioID") int scenarioID) {
		boolean added = ItemsInScenariosModel.addItemToScenario(itemID, scenarioID);
		JSONObject json = new JSONObject();
		json.put("added", added);
		return json.toJSONString();
	}
}
