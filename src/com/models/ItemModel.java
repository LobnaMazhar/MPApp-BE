package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
        	String sql = "SELECT * FROM `items`";
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
    
    public static boolean deleteItem(int itemID){
    	ArrayList<ItemModel> items = new ArrayList<ItemModel>();
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
}
