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
			jsonObj.put("itemInScenarioID", itemsInScenario.get(i).getItemsInScenariosID());
			jsonObj.put("itemInScenarioItemID", itemsInScenario.get(i).getItemsInScenariosItemID());
			jsonObj.put("itemInScenarioScenarioID", itemsInScenario.get(i).getItemsInScenariosScenarioID());
			jsonObj.put("itemInScenarioItemQuantity", itemsInScenario.get(i).getItemsInScenariosItemQuantity());
			
			String itemShortDescription = ItemModel.getItem(itemsInScenario.get(i).getItemsInScenariosItemID()).getItemShortDecription();
			jsonObj.put("itemInScenarioItemShortDescription", itemShortDescription);
			
			json.add(jsonObj);
		}
		return json.toJSONString();
	}
	
	@POST
	@Path("/addItemToScenario")
	@Produces(MediaType.TEXT_PLAIN)
	public String addItemToScenario(@FormParam("itemID") int itemID, @FormParam("scenarioID") int scenarioID) {
		boolean added = ItemsInScenariosModel.addItemToScenario(itemID, scenarioID);
		JSONObject json = new JSONObject();
		json.put("added", added);
		return json.toJSONString();
	}
	
	@POST
	@Path("/editItemQuantityInScenario")
	@Produces(MediaType.TEXT_PLAIN)
	public String editItemQuantityInScenario(@FormParam("ItemQuantity") int itemQuantity, @FormParam("itemInScenarioID") int itemInScenarioID){
		boolean edited = ItemsInScenariosModel.editItemQuantityInScenario(itemQuantity, itemInScenarioID);
		JSONObject json = new JSONObject();
		json.put("edited", edited);
		return json.toJSONString();
			
	}
	
	@POST
	@Path("/deleteItemFromScenario")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(@FormParam("itemID") int itemID, @FormParam("scenarioID") int scenarioID){
		boolean deleted = ItemsInScenariosModel.deleteItemFromScenario(itemID, scenarioID);
		JSONObject json = new JSONObject();
		json.put("deleted", deleted);
		return json.toJSONString();
			
	}
}
