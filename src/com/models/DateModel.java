package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DateModel {
	
	int monthID;
	String monthName;
	
	public int getMonthID(){
		return monthID;
	}
	
	public String getMonthName(){
		return monthName;
	}
	
	public static String getMonthName(int monthID){
    	String monthName = "";
    	try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "SELECT monthName FROM `months` WHERE monthID = ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, monthID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				monthName = rs.getString("monthName");
				return monthName;
			}
			return monthName;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return monthName;
    
    }
}
