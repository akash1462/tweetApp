package com.tweetapp.tweet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tweetapp.tweet.mapper.UserRowMapper;
import com.tweetapp.tweet.model.User;
import com.tweetapp.tweet.util.ConnectionHandler;

public class UserDaoImpl implements UserDao {

	private PreparedStatement ps;
	private ResultSet rs;
	Connection conn;
	
	public User userExists(String email) {
		ps = null;
		rs = null;
		conn = ConnectionHandler.getConnection();
		User user = null;
		try {
			ps = conn.prepareStatement("select * from tweet.users where email = ?");
			ps.setString(1, email);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserRowMapper rowMapper = new UserRowMapper();
				user = rowMapper.mapRow(rs);
			}
		} catch (SQLException e) {
			System.out.println("Exception Message: " + e);
			throw new RuntimeException("SQL Exception retrieving user details.");
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	public void registerUser(User user) {
		ps = null;
		conn = ConnectionHandler.getConnection();
		try {
			ps = conn.prepareStatement("insert into tweet.users(email,password,first_name,last_name) values(?,?,?,?)");
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getFirstName());
			ps.setString(4, user.getLastName());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Exception");
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				throw new RuntimeException("Connection is  not closed properly");
			}
		}
	}

	public void updatePassword(String email, String newPassword) {
		ps = null;
		conn = ConnectionHandler.getConnection();
		try {
			ps = conn.prepareStatement("update tweet.users set password = ? where email = ?");
			ps.setString(1, newPassword);
			ps.setString(2, email);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Exception");
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				throw new RuntimeException("Connection is  not closed properly");
			}
		}
	}

	public List<User> getAllUser() {
		ps = null;
		rs = null;
		Connection conn = ConnectionHandler.getConnection();
		ArrayList<User> users = new ArrayList<>();
		try {
			ps = conn.prepareStatement("select * from tweet.users;");
			rs = ps.executeQuery();
			while (rs.next()) {
				UserRowMapper rowMapper = new UserRowMapper();
				User user = rowMapper.mapRow(rs);
				users.add(user);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Unable to get All User details");
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				throw new RuntimeException("Connection not closed properly");
			}
		}
		return users;
	}

	public void updateLogInd(String logInd, String email) {
		ps = null;
		conn = ConnectionHandler.getConnection();
		try {
			ps = conn.prepareStatement("update tweet.users set logged_in = ? where email = ?");
			ps.setString(1, logInd);
			ps.setString(2, email);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Exception");
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				throw new RuntimeException("Connection not closed properly");
			}
		}
	}
	
	public void closeConnection() throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (ps != null) {
			ps.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

}
