package com.tweetapp.tweet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tweetapp.tweet.mapper.TweetRowMapper;
import com.tweetapp.tweet.model.Tweet;
import com.tweetapp.tweet.util.ConnectionHandler;

public class TweetDaoImpl implements TweetDao {
	
	private PreparedStatement ps;
	private ResultSet rs;
	Connection conn;
	
	public void addTweet(String desc, String user_id) {
		ps = null;
		conn = ConnectionHandler.getConnection();
		try {
			ps = conn.prepareStatement("insert into tweet.tweets(tweet,user_id) values(?,?)");
			ps.setString(1, desc);
			ps.setString(2, user_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Trouble in posting Tweet");
		} finally {
			try {
				closeConnection();
				throw new RuntimeException("Connection is  not closed properly");
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Tweet> getAllTweetByUser(String userId) {
		conn = ConnectionHandler.getConnection();
		ps = null;
		rs = null;
		ArrayList<Tweet> tweets = new ArrayList<>();
		try {
			ps = conn.prepareStatement("select * from tweet.tweets where user_id = ?");
			ps.setString(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				TweetRowMapper rowMapper = new TweetRowMapper();
				Tweet tweet = rowMapper.mapRow(rs);
				tweets.add(tweet);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error fetching tweet by user");
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				throw new RuntimeException("Connection not closed properly");
			}
		}
		return tweets;
	}
	
	
	public List<Tweet> getAllTweet() {
		conn = ConnectionHandler.getConnection();
		ps = null;
		rs = null;
		ArrayList<Tweet> tweets = new ArrayList<>();
		try {
			ps = conn.prepareStatement("select * from tweet.tweets");
			rs = ps.executeQuery();
			while (rs.next()) {
				TweetRowMapper rowMapper = new TweetRowMapper();
				Tweet tweet = rowMapper.mapRow(rs);
				tweets.add(tweet);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error fetching all tweet details");
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				throw new RuntimeException("Connection not closed properly");
			}
		}
		return tweets;
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
