package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VendorModel {
	private int vendorID;
	private String vendorName;
	
	public int getVendorID(){
		return vendorID;
	}
	
	public String getVendorName(){
		return vendorName;
	}
	
	public static ArrayList<VendorModel> getVendors(){
		ArrayList<VendorModel> vendors = new ArrayList<VendorModel>();
		
		try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "SELECT * FROM `vendors`";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				VendorModel vendor = new VendorModel();
				vendor.vendorID = rs.getInt("vendorID");
				vendor.vendorName = rs.getString("vendorName");
				vendors.add(vendor);
			}
			return vendors;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vendors;
	
	}
	
	public static String getVendorName(int vendorID){
		
		String vendorName = "";
		try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "SELECT vendorName FROM vendors WHERE vendorID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1,vendorID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				vendorName = rs.getString("vendorName");
				return vendorName;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vendorName;
	}
	
	public static ArrayList<VendorModel> getVendorsWithProjectAndRegion(int projectID, int regionID){
		ArrayList<VendorModel> vendors = new ArrayList<VendorModel>();
		try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "SELECT DISTINCT vendors.vendorID, vendors.vendorName FROM vendors JOIN projects_regions_vendors_months ON vendors.vendorID = projects_regions_vendors_months.projects_regions_vendors_monthsVendorID WHERE projects_regions_vendors_months.projects_regions_vendors_monthsProjectID = ? AND projects_regions_vendors_months.projects_regions_vendors_monthsRegionID = ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, projectID);
			stmt.setInt(2, regionID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				VendorModel vendor = new VendorModel();
				vendor.vendorID = rs.getInt("vendorID");
				vendor.vendorName = rs.getString("vendorName");
				vendors.add(vendor);
			}
			return vendors;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vendors;
	
	}
	
	
}
