package com.tweetapp.tweet.model;

public class Tweet {
	
	private String user_id;
	private String tweet;
	
	public String getTweet() {
		return tweet;
	}
	public void setTweet(String tweet) {
		this.tweet = tweet;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "Tweet [user_id=" + user_id + ", tweet=" + tweet + "]";
	}
	

}
