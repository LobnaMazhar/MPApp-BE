package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegionModel {
	
	int regionID;
	String regionName;
	int monthlyPhasing;
	
	public int getRegionID(){
		return regionID;
	}
	
	public String getRegionName(){
		return regionName;
	}
	
	public int getMonthlyPhasing(){
		return monthlyPhasing;
	}
	
	public static ArrayList<RegionModel> getRegions(){
		ArrayList<RegionModel> regions = new ArrayList<RegionModel>();
		
		try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "SELECT * FROM `regions`";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				RegionModel region = new RegionModel();
				region.regionID = rs.getInt("regionID");
				region.regionName = rs.getString("regionName");
				regions.add(region);
			}
			return regions;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return regions;
	
	}
	
	public static RegionModel getRegion(int regionID){
		
    	RegionModel region = new RegionModel();
    	try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "SELECT * FROM `regions` WHERE regionID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, regionID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				region.regionID = rs.getInt("regionID");
				region.regionName = rs.getString("regionName");
				region.monthlyPhasing = rs.getInt("monthlyPhasing");
			}
			return region;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return region;
    }
	
	public static String getRegionName(int regionID){
		
		String regionName = "";
		try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "SELECT regionName FROM regions WHERE regionID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1,regionID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				regionName = rs.getString("regionName");
				return regionName;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return regionName;
	}
	
	public static int getRegionID(String regionName){

		int regionID = 0;
		try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "SELECT regionID FROM regions WHERE regionName = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, regionName);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				regionID = rs.getInt("regionID");
				return regionID;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return regionID;
	}
	
	public static int getRegionMonthlyPhasing(int regionID) {
		
		int monthlyPhasing = 0;
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "SELECT monthlyPhasing FROM regions WHERE regionID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, regionID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				monthlyPhasing = rs.getInt("monthlyPhasing");
				return monthlyPhasing;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return monthlyPhasing;
	}
	
	public static String getRegionNameForSite(String siteID){
		String regionName = "";
		try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "SELECT regionName FROM regions JOIN sites ON regions.regionID = sites.regionID WHERE siteID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, siteID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				regionName = rs.getString("regionName");
				return regionName;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return regionName;
	}
	
}