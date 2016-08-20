package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TechnicalPlanModel {
	int technicalPlanID;
	String technicalPlanName;
	
	public int getTechnicalPlanID(){
		return technicalPlanID;
	}
	
	public String getTechnicalPlanName(){
		return technicalPlanName;
	}
	
	public static ArrayList<TechnicalPlanModel> getTechnicalPlans(){
		ArrayList<TechnicalPlanModel> technicalPlans = new ArrayList<TechnicalPlanModel>();
		
		try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "SELECT * FROM `technicalplans`";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				TechnicalPlanModel technicalPlan = new TechnicalPlanModel();
				technicalPlan.technicalPlanID = rs.getInt("technicalPlanID");
				technicalPlan.technicalPlanName = rs.getString("technicalPlanName");
				technicalPlans.add(technicalPlan);
			}
			return technicalPlans;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return technicalPlans;
	}
}
