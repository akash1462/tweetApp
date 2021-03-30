package com.tweetapp.tweet.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionHandler {
	public static Connection getConnection() {
		// Load Connection Properties
		Properties prop = new Properties();
		try {
			prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Get Connection Details
		String driver = prop.getProperty("driver");
		String url = prop.getProperty("connection-url");
		String username = prop.getProperty("user");
		String password = prop.getProperty("password");

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {

			System.out.println("Class not found");
			e.printStackTrace();
		}
		// Create Connection
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

}
