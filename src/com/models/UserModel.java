package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {

	private int userID;
	private String userName;
	private String userPassword;

	public int getUserID() {
		return userID;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserPassword() {
		return userPassword;
	}
	
	public static UserModel login(String name, String password) {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "SELECT * FROM `users` WHERE `userName` = ? and `userPassword` = ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				UserModel user = new UserModel();
				user.userID = rs.getInt("userID");
				user.userName = rs.getString("userName");
				return user;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static UserModel addUser(String name, String password){
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "INSERT INTO `users`(`userName`, `userPassword`) VALUES (?,?)";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				UserModel user = new UserModel();
				user.userID = rs.getInt("userID");
				user.userName = rs.getString("userName");
				user.userPassword = rs.getString("userPassword");
				return user;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
