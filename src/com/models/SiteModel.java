package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SiteModel {

	String date;
	String siteID;
	int regionID;
	String cablesType;
	
	public String getDate() {
		return date;
	}
	
	public String getSiteID() {
		return siteID;
	}
	
	public String getCablesType() {
		return cablesType;
	}
	
	public static ArrayList<SiteModel> getSites(){
		ArrayList<SiteModel> sites = new ArrayList<SiteModel>();
		
		try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "SELECT * FROM `sites`";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				SiteModel site = new SiteModel();
				site.date = rs.getString("Date");
				site.siteID = rs.getString("siteID");
				site.regionID = rs.getInt("regionID");
				site.cablesType = rs.getString("cablesType");
				sites.add(site);
			}
			return sites;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sites;
	}
	
	public static ArrayList<SiteModel> getSitesInAGivenDate(String date){
		ArrayList<SiteModel> sites = new ArrayList<SiteModel>();
		
		try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "SELECT * FROM `sites` where `Date` = ? ";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, date);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				SiteModel site = new SiteModel();
				site.date = rs.getString("Date");
				site.siteID = rs.getString("siteID");
				site.regionID = rs.getInt("regionID");
				site.cablesType = rs.getString("cablesType");
				sites.add(site);
			}
			return sites;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sites;
	}

	public static boolean deleteSite(String siteID){
		try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "DELETE FROM `sites` WHERE `siteID` = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, siteID);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return false;
	}
	
	public static boolean addSite(String date, String siteID, int regionID, String cablesType){
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "INSERT INTO `sites`(`date`, `siteID`, `regionID`, `cablesType`) VALUES (?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, date);
			stmt.setString(2, siteID);
			stmt.setInt(3, regionID);
			stmt.setString(4, cablesType);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static int getRegionID(String siteID){
		int regionID = 0;
		try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "SELECT regionID FROM sites WHERE siteID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, siteID);
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
	
	public static boolean editSite(String siteID, String date, String cablesType, String regionName){
		int regionID = RegionModel.getRegionID(regionName);
		try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "UPDATE `sites` SET `Date`= ?, `regionID`= ?, `cablesType`= ? WHERE `siteID` = ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, date);
			stmt.setInt(2, regionID);
			stmt.setString(3, cablesType);
			stmt.setString(4, siteID);
			stmt.executeUpdate();			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return false;
	}
}
