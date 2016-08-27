package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

public class ScenarioModel {
	int scenarioID;
	int scenarioNumber;
	int projectID;
	
	public int getScenarioID(){
		return scenarioID;
	}
	
	public int getScenarioNumber(){
		return scenarioNumber;
	}
	
	public static ArrayList<ScenarioModel> getScenarios(){
		ArrayList<ScenarioModel> scenarios = new ArrayList<ScenarioModel>();
		
		try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "SELECT * FROM `scenarios`";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ScenarioModel scenario = new ScenarioModel();
				scenario.scenarioID = rs.getInt("scenarioID");
				scenario.scenarioNumber = rs.getInt("scenarioNumber");
				scenarios.add(scenario);
			}
			return scenarios;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return scenarios;
	}

	public static boolean deleteScenario(int scenarioID){
		try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "DELETE FROM `scenarios` WHERE `scenarioID` = ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, scenarioID);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return false;
	}
	
	public static boolean addScenario(int scenarioNumber){
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "INSERT INTO `scenarios`(`scenarioNumber`) VALUES (?)";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, scenarioNumber);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				int scenarioID = getScenarioID(scenarioNumber);
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static int getProjectID(int scenarioID){
		int projectID = 0;
		try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "SELECT projectID FROM scenarios WHERE scenarioID = ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, scenarioID);
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
	
	public static boolean editScenario(int scenarioID, int scenarioNumber){
		try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "UPDATE `scenarios` SET `scenarioNumber` = ?  WHERE `scenarioID` = ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, scenarioNumber);
			stmt.setInt(2, scenarioID);
			stmt.executeUpdate();			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return false;
	}
	
	public static boolean checkScenarioNumberExists(int scenarioNumber){
		try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "SELECT `scenarioID` FROM `scenarios` WHERE `scenarioNumber` = ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, scenarioNumber);
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
	
	public static int getScenarioID(int scenarioNumber){
		int scenarioID = 0;
		try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "SELECT scenarioID FROM scenarios WHERE scenarioNumber = ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, scenarioNumber);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				scenarioID = rs.getInt("scenarioID");
				return scenarioID;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return scenarioID;
	}
	
	public static boolean addScenarioToProject(int projectID, int scenarioID){
		try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "UPDATE `scenarios` SET `projectID` = ? WHERE `scenarioID` = ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, projectID);
			stmt.setInt(2, scenarioID);
			stmt.executeUpdate();			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return false;
	}
	
	public static boolean deleteScenarioFromProject(int scenarioID){
		try {
    		Connection conn = DBConnection.getActiveConnection();
        	String sql = "UPDATE `scenarios` SET `projectID` = NULL WHERE `scenarioID` = ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, scenarioID);
			stmt.executeUpdate();			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return false;
	}
}
