package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

public class PRVMModel {

	int prvmID;
	int prvmProjectID;
	int prvmRegionID;
	int prvmVendorID;
	int prvmMonthID;
	int prvmPhasing;
	int prvmYearTarget;

	public int getPrvmID() {
		return prvmID;
	}

	public int getPrvmProjectID() {
		return prvmProjectID;
	}

	public int getPrvmRegionID() {
		return prvmRegionID;
	}

	public int getPrvmVendorID() {
		return prvmVendorID;
	}

	public int getPrvmMonthID() {
		return prvmMonthID;
	}

	public int getPrvmPhasing() {
		return prvmPhasing;
	}

	public int getPrvmYearTarget() {
		return prvmYearTarget;
	}
	
	public static boolean addPRVM(int projectID, int regionID, int monthID,
			int vendorID, int phasing, int yearTarget) {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "INSERT INTO `projects_regions_vendors_months`(`projects_regions_vendors_monthsProjectID`, `projects_regions_vendors_monthsRegionID`, `projects_regions_vendors_monthsVendorID`, `projects_regions_vendors_monthsMonthID`, `projects_regions_vendors_monthsPhasing`, `projects_regions_vendors_monthsRemainingOnPhasing`, `projects_regions_vendors_monthsYearTarget`, `projects_regions_vendors_monthsOriginalYearTarget`) VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, projectID);
			stmt.setInt(2, regionID);
			stmt.setInt(3, vendorID);
			stmt.setInt(4, monthID);
			stmt.setInt(5, phasing);
			stmt.setInt(6, phasing);
			stmt.setInt(7, yearTarget);
			stmt.setInt(8, yearTarget);
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
	
	public static ArrayList<PRVMModel> getPRVMs() {
		ArrayList<PRVMModel> prvms = new ArrayList<PRVMModel>();
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "SELECT * FROM projects_regions_vendors_months GROUP BY `projects_regions_vendors_monthsProjectID`, `projects_regions_vendors_monthsRegionID`, `projects_regions_vendors_monthsVendorID`";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				PRVMModel prvm = new PRVMModel();
				prvm.prvmID = rs.getInt("projects_regions_vendors_monthsID");
				prvm.prvmProjectID = rs
						.getInt("projects_regions_vendors_monthsProjectID");
				prvm.prvmRegionID = rs
						.getInt("projects_regions_vendors_monthsRegionID");
				prvm.prvmVendorID = rs
						.getInt("projects_regions_vendors_monthsVendorID");
				prvm.prvmMonthID = rs
						.getInt("projects_regions_vendors_monthsMonthID");
				prvm.prvmPhasing = rs
						.getInt("projects_regions_vendors_monthsPhasing");
				prvm.prvmYearTarget = rs
						.getInt("projects_regions_vendors_monthsYearTarget");
				prvms.add(prvm);
			}
			return prvms;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prvms;
	}
	
	public static ArrayList<PRVMModel> getPRVMsByProjectID(int projectID) {
		ArrayList<PRVMModel> prvms = new ArrayList<PRVMModel>();
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "SELECT projects_regions_vendors_monthsRegionID, projects_regions_vendors_monthsVendorID, projects_regions_vendors_monthsYearTarget, projects_regions_vendors_monthsID FROM projects_regions_vendors_months WHERE projects_regions_vendors_monthsProjectID = ? GROUP BY (projects_regions_vendors_monthsRegionID)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, projectID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				PRVMModel prvm = new PRVMModel();
				prvm.prvmID = rs.getInt("projects_regions_vendors_monthsID");
				prvm.prvmRegionID = rs
						.getInt("projects_regions_vendors_monthsRegionID");
				prvm.prvmVendorID = rs
						.getInt("projects_regions_vendors_monthsVendorID");
				prvm.prvmYearTarget = rs
						.getInt("projects_regions_vendors_monthsYearTarget");
				prvms.add(prvm);
			}
			return prvms;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prvms;
	}
	
	public static ArrayList<PRVMModel> getPhasing(int projectID, int regionID,
			int vendorID) {
		ArrayList<PRVMModel> prvms = new ArrayList<PRVMModel>();
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "SELECT `projects_regions_vendors_monthsMonthID`, `projects_regions_vendors_monthsPhasing` FROM `projects_regions_vendors_months` WHERE `projects_regions_vendors_monthsProjectID` = ? AND `projects_regions_vendors_monthsRegionID` = ? AND `projects_regions_vendors_monthsVendorID` = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, projectID);
			stmt.setInt(2, regionID);
			stmt.setInt(3, vendorID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				PRVMModel prvm = new PRVMModel();
				prvm.prvmMonthID = rs
						.getInt("projects_regions_vendors_monthsMonthID");
				prvm.prvmPhasing = rs
						.getInt("projects_regions_vendors_monthsPhasing");
				prvms.add(prvm);
			}
			return prvms;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prvms;
	}
	
	public static int getMonthPhasing(int projectID, int regionID, int vendorID, int monthID){
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "SELECT `projects_regions_vendors_monthsPhasing` FROM `projects_regions_vendors_months` WHERE `projects_regions_vendors_monthsProjectID` = ? AND `projects_regions_vendors_monthsRegionID` = ? AND `projects_regions_vendors_monthsVendorID` = ? AND `projects_regions_vendors_monthsMonthID` = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, projectID);
			stmt.setInt(2, regionID);
			stmt.setInt(3, vendorID);
			stmt.setInt(4, monthID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				return rs.getInt("projects_regions_vendors_monthsPhasing");
			}
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static boolean editPhasing(int projectID, int regionID,
			int vendorID, int monthID, int monthPhasing) {
		try {
			Connection conn = DBConnection.getActiveConnection();
			int addedPhasing = monthPhasing - getMonthPhasing(projectID, regionID, vendorID, monthID);
			String sql = "UPDATE `projects_regions_vendors_months` SET `projects_regions_vendors_monthsPhasing` = ?, projects_regions_vendors_monthsRemainingOnPhasing = projects_regions_vendors_monthsRemainingOnPhasing + ?  WHERE `projects_regions_vendors_monthsProjectID` = ? AND `projects_regions_vendors_monthsRegionID` = ? AND `projects_regions_vendors_monthsVendorID` = ? AND `projects_regions_vendors_monthsMonthID` = ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, monthPhasing);
			stmt.setInt(2, addedPhasing);
			stmt.setInt(3, projectID);
			stmt.setInt(4, regionID);
			stmt.setInt(5, vendorID);
			stmt.setInt(6, monthID);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean editYearTarget(int projectID, int regionID,
			int vendorID, int yearTarget) {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "UPDATE `projects_regions_vendors_months` SET `projects_regions_vendors_monthsYearTarget` = ?  WHERE `projects_regions_vendors_monthsProjectID` = ? AND `projects_regions_vendors_monthsRegionID` = ? AND `projects_regions_vendors_monthsVendorID` = ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, yearTarget);
			stmt.setInt(2, projectID);
			stmt.setInt(3, regionID);
			stmt.setInt(4, vendorID);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean checkIfPRVExists(int projectID, int regionID,
			int vendorID) {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "SElECT * from `projects_regions_vendors_months` WHERE `projects_regions_vendors_monthsProjectID` = ? AND `projects_regions_vendors_monthsRegionID` = ? AND `projects_regions_vendors_monthsVendorID` = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, projectID);
			stmt.setInt(2, regionID);
			stmt.setInt(3, vendorID);
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
	
	public static int checkEnoughPhasing(int projectID, int regionID, int vendorID, int monthID) {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "SELECT `projects_regions_vendors_monthsMonthID` From `projects_regions_vendors_months` WHERE `projects_regions_vendors_monthsProjectID` = ? AND `projects_regions_vendors_monthsRegionID` = ? AND `projects_regions_vendors_monthsVendorID` = ? AND `projects_regions_vendors_monthsMonthID` <= ? AND `projects_regions_vendors_monthsRemainingOnPhasing` >= 1";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, projectID);
			stmt.setInt(2, regionID);
			stmt.setInt(3, vendorID);
			stmt.setInt(4, monthID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("projects_regions_vendors_monthsMonthID");
			}
			return 0;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static boolean updatePhasing(int projectID, int regionID, int vendorID, int monthID) {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "UPDATE `projects_regions_vendors_months` SET `projects_regions_vendors_monthsRemainingOnPhasing` = `projects_regions_vendors_monthsRemainingOnPhasing`-1 WHERE `projects_regions_vendors_monthsProjectID` = ? AND `projects_regions_vendors_monthsRegionID` = ? AND `projects_regions_vendors_monthsVendorID` = ? AND `projects_regions_vendors_monthsMonthID` = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, projectID);
			stmt.setInt(2, regionID);
			stmt.setInt(3, vendorID);
			stmt.setInt(4, monthID);
			stmt.executeUpdate();
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static ArrayList<PRVMModel> getMonthlyPhasingPerProject(int projectID){
		ArrayList<PRVMModel> prvmList = new ArrayList<PRVMModel>();
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "SELECT `projects_regions_vendors_monthsRegionID`, `projects_regions_vendors_monthsVendorID`, `projects_regions_vendors_monthsMonthID`, `projects_regions_vendors_monthsPhasing` FROM `projects_regions_vendors_months` WHERE `projects_regions_vendors_monthsProjectID` = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, projectID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				PRVMModel prvmObject = new PRVMModel();
				
				prvmObject.prvmProjectID = projectID;
				prvmObject.prvmRegionID = rs.getInt("projects_regions_vendors_monthsRegionID");
				prvmObject.prvmVendorID = rs.getInt("projects_regions_vendors_monthsVendorID");
				prvmObject.prvmMonthID = rs.getInt("projects_regions_vendors_monthsMonthID");
				prvmObject.prvmPhasing = rs.getInt("projects_regions_vendors_monthsPhasing");
				
				prvmList.add(prvmObject);
			}
			return prvmList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prvmList;
	}
}
