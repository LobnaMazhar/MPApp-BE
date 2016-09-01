package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

public class ItemModel {
	int itemID;
    String itemEvoCode;
    String itemShortDescription;
    int itemQuantity;
    
    public int getItemID(){
    	return itemID;
    }
    
    public String getItemEvoCode(){
    	return itemEvoCode;
    }
    
    public String getItemShortDecription(){
    	return itemShortDescription;
    }
    
    public int getItemQuantity(){
    	return itemQuantity;
    }
    
    public static ArrayList<ItemModel> getItems(){
    	ArrayList<ItemModel> items = new ArrayList<ItemModel>();
    	try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "SELECT * FROM `items` ORDER BY `itemShortDescription`";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ItemModel item = new ItemModel();
				item.itemID = rs.getInt("itemID");
				item.itemEvoCode = rs.getString("itemEvoCode");
				item.itemShortDescription = rs.getString("itemShortDescription");
				item.itemQuantity = rs.getInt("itemQuantity");
				items.add(item);
			}
			return items;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return items;
    }
    
    public static boolean addItem(String evocode, String shortDescription, int quantity){
    	try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "INSERT INTO `items`(`itemEvoCode`, `itemShortDescription`, `itemQuantity`) VALUES (?,?,?)";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, evocode);
			stmt.setString(2, shortDescription);
			stmt.setInt(3, quantity);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
    }
    
    public static boolean deleteItem(int itemID){
    	try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "DELETE FROM `items` WHERE itemID = ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, itemID);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return false;
    }

    public static boolean editItem(int itemID, String itemEvoCode, String itemShortDescription, int itemQuantity){
    	try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "UPDATE `items` SET `itemEvoCode`=?,`itemShortDescription`=?,`itemQuantity`=? WHERE `itemID`=?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, itemEvoCode);
			stmt.setString(2, itemShortDescription);
			stmt.setInt(3, itemQuantity);
			stmt.setInt(4, itemID);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return false;
    }

    public static boolean addItemsToScenario(int scenarioNumber, ArrayList<Integer> selectedItemsIDsList){
    	//get scenarioNumber
    	//loop 3la l AL
    	try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "IINSERT INTO `items_scenarios`(`items_scenariosItemID`, `items_scenariosScenarioID`) VALUES (?, ?)";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			// stmt.set()
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
    }

    public static ItemModel getItem(int itemID){
    	ItemModel item = new ItemModel();
    	try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "SELECT * FROM `items` WHERE itemID = ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, itemID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				item.itemID = rs.getInt("itemID");
				item.itemEvoCode = rs.getString("itemEvoCode");
				item.itemShortDescription = rs.getString("itemShortDescription");
				item.itemQuantity = rs.getInt("itemQuantity");
			}
			return item;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return item;
    
    }
    
    public static boolean updateItemsStockByScenario(int scenarioID){
    	try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "UPDATE `items` JOIN items_scenarios ON items.itemID = items_scenarios.items_scenariosItemID AND items_scenarios.items_scenariosScenarioID = ? SET items.itemQuantity = items.itemQuantity - items_scenarios.items_scenariosItemQuantity";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, scenarioID);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return false;
    }
    
    public static int getItemIDByShortDescription(String shortDescription){
    	int itemID = 0;
    	try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "SELECT itemID FROM `items` WHERE itemShortDescription = ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, shortDescription);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				itemID = rs.getInt("itemID");
				return itemID;
			}
			return itemID;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itemID;
    
    }
}
