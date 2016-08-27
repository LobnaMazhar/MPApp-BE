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
	
	public int getProjectsInSitesID() {
		return projectsInSitesID;
	}
	
	public String getProjectsInSitesSiteID() {
		return projectsInSitesSiteID;
	}
	
	public int getProjectsInSitesProjectID() {
		return projectsInSitesProjectID;
	}
	
	public static ArrayList<ProjectsInSitesModel> getProjectsInSite(int siteID){
    	ArrayList<ProjectsInSitesModel> projectsInSite = new ArrayList<ProjectsInSitesModel>();
    	try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "SELECT * FROM sites_projects WHERE sites_projectsSiteID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, siteID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ProjectsInSitesModel projectInSite = new ProjectsInSitesModel();
				projectInSite.projectsInSitesID = rs.getInt("sites_projectsID");
				projectInSite.projectsInSitesSiteID = rs.getString("sites_projectsSiteID");
				projectInSite.projectsInSitesProjectID = rs.getInt("sites_projectsProjectID");
				projectsInSite.add(projectInSite);
			}
			return projectsInSite;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return projectsInSite;
    }
	
	public static ArrayList<ProjectsInSitesModel> getSitesInProject(int projectID){
    	ArrayList<ProjectsInSitesModel> sitesInProject = new ArrayList<ProjectsInSitesModel>();
    	try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "SELECT * FROM sites_projects WHERE sites_projectsProjectID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, projectID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ProjectsInSitesModel siteInProject = new ProjectsInSitesModel();
				siteInProject.projectsInSitesID = rs.getInt("sites_projectsID");
				siteInProject.projectsInSitesSiteID = rs.getString("sites_projectsSiteID");
				siteInProject.projectsInSitesProjectID = rs.getInt("sites_projectsProjectID");
				sitesInProject.add(siteInProject);
			}
			return sitesInProject;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return sitesInProject;
    }
	
	public static boolean addProjectToSite(int projectID, int siteID){
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "INSERT INTO `sites_projects`(`sites_projectsProjectID`, `sites_projectsSiteID`) VALUES (?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, projectID);
			stmt.setInt(2, siteID);
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
        	String sql = "SELECT `sites_projectsID` FROM `sites_projects` WHERE `siteID` = ? AND `projectID` = ?";
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

}
