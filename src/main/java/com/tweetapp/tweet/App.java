package com.tweetapp.tweet;

import com.tweetapp.tweet.service.AuthenticationService;

public class App {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		System.out.println("Welcome to Tweet App");
		AuthenticationService authenticationService = new AuthenticationService();
		authenticationService.introductionMenu();
	}
}
