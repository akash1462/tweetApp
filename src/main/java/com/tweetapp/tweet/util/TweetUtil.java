package com.tweetapp.tweet.util;

import java.util.Scanner;

/**
 * The Class TweetUtil.
 */
public class TweetUtil {

	/**
	 * Validate email.
	 *
	 * @param email the email
	 * @return true, if successful
	 */
	public boolean validateEmail(String email) {
		if (email.contains("@")) {
			return true;
		}
		return false;
	}
	
	/**
	 * Select gender.
	 *
	 * @return the string
	 */
	public String selectGender(){
		System.out.println("Please select Gender :");
		System.out.println("1.Male");
		System.out.println("2.Female");
		System.out.println("3.Other");
		Scanner input = new Scanner(System.in);
		int selectedValue = input.nextInt();
		switch(selectedValue){
		case 1:
			return "Male";
		case 2: 
			return "Female";
		case 3: 
			return "Other";
		default:
			System.out.println("Invalid input");
			selectGender();
		}
		return null;
		
	}
	
}
