package com.tweetapp.tweet.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.tweetapp.tweet.model.User;

public class UserRowMapper {
	public User mapRow(ResultSet rs) throws SQLException {
		User user = new User();
		user.setFirstName(rs.getString("first_name"));
		user.setLastName(rs.getString("last_name"));
		user.setGender(rs.getString("gender"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		return user;
	}
}
