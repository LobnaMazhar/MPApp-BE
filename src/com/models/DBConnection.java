package com.models;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
	private static Connection connection = null;

	public static Connection getActiveConnection() {

		// String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST"); String port =
		// System.getenv("OPENSHIFT_MYSQL_DB_PORT"); System.out.println(host);

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager
					.getConnection("jdbc:mysql://127.13.62.130:3306/mpapp?"
							+ "user=adminwqb4Kmc&password=gGniRgEyUmFE&characterEncoding=utf8");
		//	 connection = DriverManager
			// .getConnection("jdbc:mysql://localhost:3306/MPApp","root","phpMyAdmin_MySQL");
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
