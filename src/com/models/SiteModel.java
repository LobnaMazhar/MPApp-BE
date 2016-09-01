package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

public class SiteModel {

	String siteID;
	int regionID;
	int vendorID;
	
	public String getSiteID(){
		return siteID;
	}
	
	public int getRegionID(){
		return regionID;
	}
	
	public int getVendorID(){
		return vendorID;
	}
	
	public static boolean addSite(String siteID, int regionID, int vendorID){
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "INSERT INTO `sites`(`siteID`, `regionID`, `vendorID`) VALUES (?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, siteID);
			stmt.setInt(2, regionID);
			stmt.setInt(3, vendorID);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean checkIfSiteExists(String siteID){
		try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "SELECT * FROM `sites` WHERE `siteID` = ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, siteID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static SiteModel getSiteByID(String siteID) {
		SiteModel site = new SiteModel();
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "SELECT * FROM `sites` WHERE `siteID` = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, siteID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				site.siteID = rs.getString("siteID");
				site.regionID = rs.getInt("regionID");
				site.vendorID = rs.getInt("vendorID");
			}
			return site;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return site;
	}
}
