package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

public class PRVModel {

	int prvID;
	int prvProjectID;
	int prvRegionID;
	int prvVendorID;
	int prvYearTarget;
	
	public int getPrvID() {
		return prvID;
	}
	public int getPrvProjectID() {
		return prvProjectID;
	}
	public int getPrvRegionID() {
		return prvRegionID;
	}
	public int getPrvVendorID() {
		return prvVendorID;
	}
	public int getPrvYearTarget() {
		return prvYearTarget;
	}
	
	public static boolean addPRV(int projectID, int regionID, int vendorID, int yearTarget){
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "INSERT INTO `projects_regions_vendors`(`projects_regions_vendorsProjectID`, `projects_regions_vendorsRegionID`, `projects_regions_vendorsVendorID`, `projects_regions_vendorsYearTarget`) VALUES (?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, projectID);
			stmt.setInt(2, regionID);
			stmt.setInt(3, vendorID);
			stmt.setInt(4, yearTarget);
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
	
	public static ArrayList<PRVModel> getPRVs() {	
		ArrayList<PRVModel> prvs = new ArrayList<PRVModel>();
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "SELECT * FROM projects_regions_vendors";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				PRVModel prv = new PRVModel();
				prv.prvID = rs.getInt("projects_regions_vendorsID");
				prv.prvProjectID = rs.getInt("projects_regions_vendorsProjectID");
				prv.prvRegionID = rs.getInt("projects_regions_vendorsRegionID");
				prv.prvVendorID = rs.getInt("projects_regions_vendorsVendorID");
				prv.prvYearTarget = rs.getInt("projects_regions_vendorsYearTarget");
				prvs.add(prv);
			}
			return prvs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prvs;
	}
	
	public static ArrayList<PRVModel> getPRVsByProjectID(int projectID){
    	ArrayList<PRVModel> prvs = new ArrayList<PRVModel>();
    	try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "SELECT * FROM projects_regions_vendors WHERE projects_regions_vendorsProjectID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, projectID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				PRVModel prv = new PRVModel();
				prv.prvID = rs.getInt("projects_regions_vendorsID");
				prv.prvProjectID = rs.getInt("projects_regions_vendorsProjectID");
				prv.prvRegionID = rs.getInt("projects_regions_vendorsRegionID");
				prv.prvVendorID = rs.getInt("projects_regions_vendorsVendorID");
				prv.prvYearTarget = rs.getInt("projects_regions_vendorsYearTarget");
				prvs.add(prv);
			}
			return prvs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return prvs;
    }
	
	public static ArrayList<PRVModel> getAllProjectsInRegion(int regionID){
    	ArrayList<PRVModel> prvs = new ArrayList<PRVModel>();
    	try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "SELECT * FROM projects_regions_vendors WHERE projects_regions_vendorsRegionID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, regionID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				PRVModel prv = new PRVModel();
				prv.prvID = rs.getInt("projects_regions_vendorsID");
				prv.prvProjectID = rs.getInt("projects_regions_vendorsProjectID");
				prv.prvRegionID = rs.getInt("projects_regions_vendorsRegionID");
				prv.prvVendorID = rs.getInt("projects_regions_vendorsVendorID");
				prv.prvYearTarget = rs.getInt("projects_regions_vendorsYearTarget");
				prvs.add(prv);
			}
			return prvs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return prvs;
    }
	
	public static ArrayList<PRVModel> getProjectsInRegionSpecificVendor(int regionID, int vendorID){
    	ArrayList<PRVModel> prvs = new ArrayList<PRVModel>();
    	try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "SELECT * FROM projects_regions_vendors WHERE projects_regions_vendorsRegionID = ? AND projects_regions_vendorsVendorID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, regionID);
			stmt.setInt(2, vendorID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				PRVModel prv = new PRVModel();
				prv.prvID = rs.getInt("projects_regions_vendorsID");
				prv.prvProjectID = rs.getInt("projects_regions_vendorsProjectID");
				prv.prvRegionID = rs.getInt("projects_regions_vendorsRegionID");
				prv.prvVendorID = rs.getInt("projects_regions_vendorsVendorID");
				prv.prvYearTarget = rs.getInt("projects_regions_vendorsYearTarget");
				prvs.add(prv);
			}
			return prvs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return prvs;
    }
}
