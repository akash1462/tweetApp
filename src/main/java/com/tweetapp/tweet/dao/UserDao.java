package com.tweetapp.tweet.dao;

import java.util.List;

import com.tweetapp.tweet.model.User;

public interface UserDao {

	/**
	 * User exists.
	 *
	 * @param email the email
	 * @return the user
	 */
	User userExists(String email);

	/**
	 * Register user.
	 *
	 * @param user the user
	 */
	void registerUser(User user);

	/**
	 * Update password.
	 *
	 * @param email the email
	 * @param newPassword the new password
	 */
	void updatePassword(String email, String newPassword);

	/**
	 * Gets the all user.
	 *
	 * @return the all user
	 */
	List<User> getAllUser();

	/**
	 * Update log ind.
	 *
	 * @param logInd the log ind
	 * @param email the email
	 */
	void updateLogInd(String logInd, String email);

}
