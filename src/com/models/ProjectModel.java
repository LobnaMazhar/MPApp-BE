package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

public class ProjectModel {
	int projectID;
	String projectName;
	
	public int getProjectID(){
		return projectID;
	}
	
	public String getProjectName(){
		return projectName;
	}
	
	public static ArrayList<ProjectModel> getProjects(){
		ArrayList<ProjectModel> projects = new ArrayList<ProjectModel>();
		
		try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "SELECT * FROM `projects`";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ProjectModel project = new ProjectModel();
				project.projectID = rs.getInt("projectID");
				project.projectName = rs.getString("projectName");
				projects.add(project);
			}
			return projects;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projects;
	}
	
	public static String getProjectName(int projectID){
		String projectName="";
		try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "SELECT projectName FROM projects WHERE projectID = ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,projectID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				projectName = rs.getString("projectName");
				return projectName;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projectName;
	}
	
	public static int getProjectID(String projectName){
		int projectID = 0;
		try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "SELECT projectID FROM projects WHERE projectName = ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, projectName);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				projectID = rs.getInt("projectID");
				return projectID;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projectID;
	}
	
	public static boolean addProject(String projectName){
    	try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "INSERT INTO `projects`(`projectName`) VALUES (?)";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, projectName);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
    }
	
	public static boolean deleteProject(int projectID){
		try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "DELETE FROM `projects` WHERE `projectID` = ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, projectID);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return false;
	}
	
	public static boolean editProject(int projectID, String projectName){
		try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "UPDATE `projects` SET `projectName` = ?  WHERE `projectID` = ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, projectName);
			stmt.setInt(2, projectID);
			stmt.executeUpdate();			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return false;
	}
	
	
	public static ArrayList<ProjectModel> getProjectsInPRVMs(){
		ArrayList<ProjectModel> projects = new ArrayList<ProjectModel>();
		try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "SELECT DISTINCT projects.projectID, projects.projectName FROM projects JOIN projects_regions_vendors_months ON projects.projectID = projects_regions_vendors_months.projects_regions_vendors_monthsProjectID";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ProjectModel project = new ProjectModel();
				project.projectID = rs.getInt("projectID");
				project.projectName = rs.getString("projectName");
				projects.add(project);
			}
			return projects;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projects;
	}
	
	public static ArrayList<ProjectModel> getProjectsInSiteBySiteID(String siteID){
    	ArrayList<ProjectModel> projectsInSite = new ArrayList<ProjectModel>();
    	try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "SELECT projectID, projectName FROM projects JOIN sites_projects ON projects.projectID = sites_projectsProjectID WHERE sites_projects.sites_projectsSiteID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, siteID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ProjectModel projectInSite = new ProjectModel();
				projectInSite.projectID = rs.getInt("projectID");
				projectInSite.projectName = rs.getString("projectName");
				projectsInSite.add(projectInSite);
			}
			return projectsInSite;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return projectsInSite;
    }
}
