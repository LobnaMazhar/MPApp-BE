package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class BOMModel {
	
	int siteID;
	String date;
	int regionID;
	
	public static int addBOM(int siteID, String date, int regionID){
		int bomID = -1;
    	try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "INSERT INTO `bom`(`siteID`, `date`, `regionID`) VALUES (?,?,?)";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, siteID);
			stmt.setString(2, date);
			stmt.setInt(3, regionID);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				return (bomID = rs.getInt("bomID"));
			}
			return bomID;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bomID;
    }
	
}
