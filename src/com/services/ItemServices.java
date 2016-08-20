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
import com.models.UserModel;

@Path("/")
public class ItemServices {

	@POST
	@Path("/getItems")
	@Produces(MediaType.TEXT_PLAIN)
	public String getItems() {
		ArrayList<ItemModel> items = ItemModel.getItems();
		JSONArray json = new JSONArray();
		for (int i = 0; i < items.size(); ++i) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("itemID", items.get(i).getItemID());
			jsonObj.put("itemEvoCode", items.get(i).getItemEvoCode());
			jsonObj.put("itemShortDescription", items.get(i).getItemShortDecription());
			jsonObj.put("itemQuantity", items.get(i).getItemQuantity());
			json.add(jsonObj);
		}
		
		return json.toJSONString();
	}
	
	@POST
	@Path("/deleteItem")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(@FormParam("itemID") int itemID){
		boolean deleted = ItemModel.deleteItem(itemID);
		JSONObject json = new JSONObject();
		json.put("deleted", deleted);
		return json.toJSONString();
			
	}
	
	@POST
	@Path("/getItemsInScenario")
	@Produces(MediaType.TEXT_PLAIN)
	public String getItemsInScenario(@FormParam("scenarioID") int scenarioID) {
		ArrayList<ItemModel> items = ItemModel.getItemsInScenario(scenarioID);
		JSONArray json = new JSONArray();
		for (int i = 0; i < items.size(); ++i) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("itemID", items.get(i).getItemID());
			jsonObj.put("itemEvoCode", items.get(i).getItemEvoCode());
			jsonObj.put("itemShortDescription", items.get(i).getItemShortDecription());
			jsonObj.put("itemQuantity", items.get(i).getItemQuantity());
			json.add(jsonObj);
		}
		return json.toJSONString();
	}
	
	@POST
	@Path("/editItem")
	@Produces(MediaType.TEXT_PLAIN)
	public String editItem(@FormParam("itemID") int itemID, @FormParam("itemEvoCode") String itemEvoCode, @FormParam("itemShortDescription") String itemShortDescription, @FormParam("itemQuantity") int itemQuantity){
		boolean edited = ItemModel.editItem(itemID, itemEvoCode, itemShortDescription, itemQuantity);
		JSONObject json = new JSONObject();
		json.put("edited", edited);
		return json.toJSONString();
			
	}
	
	@POST
	@Path("/addItem")
	@Produces(MediaType.TEXT_PLAIN)
	public String addItem(@FormParam("itemEvoCode") String itemEvoCode,
			@FormParam("itemShortDescription") String itemShortDescription, @FormParam("itemQuantity") int itemQuantity) {
		boolean added = ItemModel.addItem(itemEvoCode, itemShortDescription, itemQuantity);
		JSONObject json = new JSONObject();
		json.put("added", added);
		return json.toJSONString();
	}
}
