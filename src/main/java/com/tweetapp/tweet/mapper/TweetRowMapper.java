package com.tweetapp.tweet.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.tweetapp.tweet.model.Tweet;

public class TweetRowMapper {

	/**
	 * Map row.
	 *
	 * @param rs the rs
	 * @return the tweet
	 * @throws SQLException the SQL exception
	 */
	public Tweet mapRow(ResultSet rs) throws SQLException {
		Tweet tweet = new Tweet();
		tweet.setUser_id(rs.getString("user_id"));
		tweet.setTweet(rs.getString("tweet"));
		return tweet;
	}
}
