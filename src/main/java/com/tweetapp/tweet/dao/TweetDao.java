package com.tweetapp.tweet.dao;

import java.util.List;

import com.tweetapp.tweet.model.Tweet;

/**
 * The Interface TweetDao.
 */
public interface TweetDao {

	/**
	 * Adds the tweet.
	 *
	 * @param desc the desc
	 * @param user_id the user id
	 */
	void addTweet(String desc, String user_id );

	/**
	 * Gets the all tweet by user.
	 *
	 * @param email the email
	 * @return the all tweet by user
	 */
	List<Tweet> getAllTweetByUser(String email);

	/**
	 * Gets the all tweet.
	 *
	 * @return the all tweet
	 */
	List<Tweet> getAllTweet();

}
