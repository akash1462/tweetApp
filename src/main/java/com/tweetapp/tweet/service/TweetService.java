package com.tweetapp.tweet.service;

import java.util.List;
import java.util.Scanner;

import com.tweetapp.tweet.dao.TweetDao;
import com.tweetapp.tweet.dao.TweetDaoImpl;
import com.tweetapp.tweet.dao.UserDao;
import com.tweetapp.tweet.dao.UserDaoImpl;
import com.tweetapp.tweet.model.Tweet;
import com.tweetapp.tweet.model.User;

/**
 * The Class TweetService.
 */
public class TweetService {
	
	/** The authentication service. */
	AuthenticationService authenticationService = new AuthenticationService();
	
	/** The user dao. */
	UserDao userDao = new UserDaoImpl();
	
	/** The tweet dao. */
	TweetDao tweetDao = new TweetDaoImpl();
	
	/**
	 * Tweet controller.
	 *
	 * @param user the user
	 */
	public void tweetController(User user) {
		System.out.println();
		System.out.println("Select your choice :");
		System.out.println("1. Post a Tweet");
		System.out.println("2. View my Tweet");
		System.out.println("3. View all Tweets");
		System.out.println("4. View all Users");
		System.out.println("5. Reset Password");
		System.out.println("6. Logout");
		Scanner sc = new Scanner(System.in);
		try {
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Post a new Tweet !!");
				postTweet(user);
				break;
			case 2:
				getTweetByUser(user);
				break;
			case 3:
				getAllTweet(user);
				break;
			case 4:
				getAllUser(user);
				break;
			case 5:
				authenticationService.resetPassword(user);
				break;
			case 6:
				authenticationService.logout(user);
				break;
			default:
				System.out.println("Please select a valid option.");
				tweetController(user);
			}
		} catch (Exception e) {
//			System.out.println("Invalid Input");
		}
		sc.close();
	}
	
	/**
	 * Post tweet.
	 *
	 * @param user the user
	 */
	public void postTweet(User user) {
		System.out.println("Enter the tweet description:");
		Scanner sc = new Scanner(System.in);
		String desc = sc.nextLine();
		tweetDao.addTweet(desc,user.getEmail());
		System.out.println("Tweet posted Successfully");
		tweetController(user);
	}
	
	/**
	 * Gets the tweet by user.
	 *
	 * @param user the user
	 * @return the tweet by user
	 */
	public void getTweetByUser(User user) {
		List<Tweet> tweets = tweetDao.getAllTweetByUser(user.getEmail());
		System.out.println("Tweets posted by " + user.getFirstName());
		if (tweets.isEmpty()) {
			System.out.println("You have not posted any tweet yet.");
		} else {
			for (Tweet tweet : tweets) {
				System.out.println(tweet);
			}
		}
		tweetController(user);
	}
	
	/**
	 * Gets the all tweet.
	 *
	 * @param user the user
	 * @return the all tweet
	 */
	public void getAllTweet(User user) {
		System.out.println("All Tweets posted by all user ");
		List<Tweet> tweets = tweetDao.getAllTweet();
		if (tweets.isEmpty()) {
			System.out.println("--- No Tweet ---");
		} else {
			for (Tweet tweet : tweets) {
				System.out.println(tweet);
			}
		}
		tweetController(user);
	}
	
	/**
	 * Gets the all user.
	 *
	 * @param currentUser the current user
	 * @return the all user
	 */
	public void getAllUser(User currentUser) {
		System.out.println("All users");
		List<User> users = userDao.getAllUser();
		if (users.isEmpty()) {
			System.out.println("No Users");
		} else {
			for (User user : users) {
				System.out.println(user);
			}
		}
		tweetController(currentUser);
	}
}
