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
	
	
	public static boolean addProjectToSite(String siteID, int projectID, int date, int scenarioID, int rollout_expansion){
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "INSERT INTO `sites_projects`(`sites_projectsSiteID`, `sites_projectsProjectID`, `sites_projectsDate`, `sites_projectsScenarioID`, `Rollout/Expansion`) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, siteID);
			stmt.setInt(2, projectID);
			stmt.setInt(3, date);
			stmt.setInt(4, scenarioID);
			stmt.setInt(5, rollout_expansion);
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
	
	public static boolean checkDuplicateSiteIDinSameProject(String siteID, int projectID){
		try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "SELECT `sites_projectsID` FROM `sites_projects` WHERE `sites_projectsSiteID` = ? AND `sites_projectsProjectID` = ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, siteID);
			stmt.setInt(2, projectID);
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
	
	public static ArrayList<ProjectsInSitesModel> getSites(int rollout_expansion){
		ArrayList<ProjectsInSitesModel> sites = new ArrayList<ProjectsInSitesModel>();
		
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "SELECT * FROM `sites_projects` WHERE `Rollout/Expansion` = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, rollout_expansion);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				ProjectsInSitesModel site = new ProjectsInSitesModel();
				
				site.projectsInSitesID = rs.getInt("sites_projectsID");
				site.projectsInSitesSiteID = rs.getString("sites_projectsSiteID");
				site.projectsInSitesProjectID = rs.getInt("sites_projectsProjectID");
				site.projectsInSitesDate = rs.getInt("sites_projectsDate");
				
				sites.add(site);
			}
			return sites;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return sites;
	}
}
