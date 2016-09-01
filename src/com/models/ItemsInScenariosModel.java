package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

public class ItemsInScenariosModel {

	int itemsInScenariosID;
	int itemsInScenariosItemID;
	int itemsInScenariosScenarioID;
	int itemsInScenariosItemQuantity;
	
	public int getItemsInScenariosID(){
		return itemsInScenariosID;
	}
	
	public int getItemsInScenariosItemID(){
		return itemsInScenariosItemID;
	}
	
	public int getItemsInScenariosScenarioID(){
		return itemsInScenariosScenarioID;
	}
	
	public int getItemsInScenariosItemQuantity(){
		return itemsInScenariosItemQuantity;
	}
	
	public static ArrayList<ItemsInScenariosModel> getItemsInScenario(int scenarioID){
    	ArrayList<ItemsInScenariosModel> itemsInScenario = new ArrayList<ItemsInScenariosModel>();
    	try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "SELECT * FROM items_scenarios WHERE items_scenariosScenarioID = ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, scenarioID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ItemsInScenariosModel itemInScenario = new ItemsInScenariosModel();
				itemInScenario.itemsInScenariosID = rs.getInt("items_scenariosID");
				itemInScenario.itemsInScenariosItemID = rs.getInt("items_scenariosItemID");
				itemInScenario.itemsInScenariosScenarioID = rs.getInt("items_scenariosScenarioID");
				itemInScenario.itemsInScenariosItemQuantity = rs.getInt("items_scenariosItemQuantity");
				itemsInScenario.add(itemInScenario);
			}
			return itemsInScenario;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return itemsInScenario;
    }
	
	public static boolean addItemToScenario(int itemID, int scenarioID){
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "INSERT INTO `items_scenarios`(`items_scenariosItemID`, `items_scenariosScenarioID`) VALUES (?, ?)";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, itemID);
			stmt.setInt(2, scenarioID);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()){
				return true;
			}
			return false;
			}catch (SQLException e){
			e.printStackTrace();
		}
			return false;
	}
	
	public static boolean editItemQuantityInScenario(int itemQuantity, int itemID){
		try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "UPDATE `items_scenarios` SET `items_scenariosItemQuantity`= ? WHERE `items_scenariosID`= ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, itemQuantity);
			stmt.setInt(2, itemID);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return false;
	}
	
	public static boolean deleteItemFromScenario(int itemID, int scenarioID){
		try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "DELETE FROM `items_scenarios` WHERE `items_scenariosItemID` = ? AND `items_scenariosScenarioID` = ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, itemID);
			stmt.setInt(2, scenarioID);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return false;
	}
	
	public static boolean isAvailableInStock(int scenarioID) {
		try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "SELECT `items_scenarios`.`items_scenariosItemQuantity`, `items`.`itemQuantity` FROM `items_scenarios` JOIN `items` ON `items_scenarios`.`items_scenariosItemID` = `items`.`itemID` WHERE `items_scenarios`.`items_scenariosScenarioID` = ?";
    		PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, scenarioID);
			ResultSet rs = stmt.executeQuery();
			int itemQuantity, itemStock;
			while(rs.next()) {
				itemQuantity = rs.getInt("items_scenariosItemQuantity");
				itemStock = rs.getInt("itemQuantity");
				if (itemQuantity > itemStock)
					return false;	
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
