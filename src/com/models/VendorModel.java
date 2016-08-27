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
	
}
