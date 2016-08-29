package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

public class ProjectsInSitesModel {
	
	int projectsInSitesID;
	String projectsInSitesSiteID;
	int projectsInSitesProjectID;
	int projectsInSitesDate;
	int projectsInSitesScenarioID;
	
	public int getProjectsInSitesID() {
		return projectsInSitesID;
	}
	
	public String getProjectsInSitesSiteID() {
		return projectsInSitesSiteID;
	}
	
	public int getProjectsInSitesProjectID() {
		return projectsInSitesProjectID;
	}
	
	public int getProjectsInSitesDate() {
		return projectsInSitesDate;
	}
	
	public int getProjectsInSitesScenarioID() {
		return projectsInSitesScenarioID;
	}
	
	
	public static boolean addProjectToSite(String siteID, int projectID, int date, int scenarioID){
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "INSERT INTO `sites_projects`(`sites_projectsSiteID`, `sites_projectsProjectID`, `sites_projectsDate`, `sites_projectsScenarioID`) VALUES (?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, siteID);
			stmt.setInt(2, projectID);
			stmt.setInt(3, date);
			stmt.setInt(4, scenarioID);
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

}
