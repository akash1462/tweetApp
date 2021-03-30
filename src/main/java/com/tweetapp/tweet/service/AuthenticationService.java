package com.tweetapp.tweet.service;

import java.util.Scanner;

import com.tweetapp.tweet.dao.UserDao;
import com.tweetapp.tweet.dao.UserDaoImpl;
import com.tweetapp.tweet.model.User;
import com.tweetapp.tweet.util.TweetUtil;

/**
 * The Class AuthenticationService.
 */
public class AuthenticationService {

	/** The tweet util. */
	TweetUtil tweetUtil = new TweetUtil();

	/**
	 * Introduction menu.
	 */
	public void introductionMenu() {
		int selector = 0;
		System.out.println("Login/Register");
		System.out.println("1. Login");
		System.out.println("2. Register");
		System.out.println("3. Forgot Password");
		Scanner sc = new Scanner(System.in);
		selector = sc.nextInt();
		switch (selector) {
		case 1:
			System.out.println("Login");
			login();
			break;
		case 2:
			System.out.println();
			System.out.println("Register");
			registerUser();
			break;
		case 3:
			System.out.println();
			System.out.println("Forgot Password");
			forgotPassword();
			break;
		default:
			System.out.println("Please enter a vaild choice");
			introductionMenu();
		}

		sc.close();
	}

	/**
	 * Login.
	 */
	public void login() {
		System.out.println("Please Enter Valid Email-Address:");
		Scanner input = new Scanner(System.in);
		String email = input.nextLine();
		System.out.println(email);
		if (tweetUtil.validateEmail(email)) {
			UserDao userDao = new UserDaoImpl();
			User user = userDao.userExists(email);
			System.out.println("Please Enter Password:");
			String password = input.nextLine();
			// case sensitive password
			if (password.contentEquals(user.getPassword())) {
				userDao.updateLogInd("Y", user.getEmail());
				System.out.println("Logged in Successfully");
				TweetService tweetService = new TweetService();
				tweetService.tweetController(user);
			} else {
				System.out.println("Incorrect Email or Password");
				login();
			}
		} else {
			System.out.println("Invalid Email Address");
			login();
		}

	}

	/**
	 * Register user.
	 */
	public void registerUser() {
		User user = new User();
		System.out.println("Please Enter E-mail Id : ");
		Scanner input = new Scanner(System.in);
		String email = input.nextLine();
		if (tweetUtil.validateEmail(email)) {
			user.setEmail(email);
			System.out.println("Enter first name :");
			user.setFirstName(input.nextLine());

			System.out.println("Enter last name :");
			user.setLastName(input.nextLine());

			user.setGender(tweetUtil.selectGender());

			System.out.println("Enter the password:");
			user.setPassword(input.nextLine());

			UserDao userDao = new UserDaoImpl();
			User userExist = userDao.userExists(email);
			if (userExist == null) {
				userDao.registerUser(user);
				System.out.println("User Registered Successfully");
				introductionMenu();
			} else {
				System.out.println("User already exist");
				introductionMenu();
			}
		}
	}

	/**
	 * Forgot password.
	 */
	public void forgotPassword() {
		System.out.println("Please Enter E-mail Id : ");
		Scanner input = new Scanner(System.in);
		String email = input.nextLine();
		UserDao userDao = new UserDaoImpl();
		User userExist = userDao.userExists(email);
		if (userExist == null) {
			System.out.println("Invalid Email..");
			introductionMenu();
		} else {
			System.out.println("Enter new Password");
			String newPassword = input.nextLine();
			userDao.updatePassword(userExist.getEmail(), newPassword);
			System.out.println("Password Update Success ");
			introductionMenu();
		}

	}

	/**
	 * Reset password.
	 *
	 * @param user the user
	 */
	public void resetPassword(User user) {
		UserDao userDao = new UserDaoImpl();
		System.out.println("Enter previous password");
		Scanner sc = new Scanner(System.in);
		String oldPassword = sc.nextLine();
		TweetService tweet = new TweetService();
		if (oldPassword.contentEquals(user.getPassword())) {
			System.out.println("Enter new password : ");
			String newPassword = sc.nextLine();
			userDao.updatePassword(user.getEmail(), newPassword);
			System.out.println("Reset Password Success !! ");
			tweet.tweetController(user);
		} else {
			System.out.print("Invalid Old password");
			tweet.tweetController(user);
		}
		sc.close();
	}

	/**
	 * Logout.
	 *
	 * @param user the user
	 */
	public void logout(User user) {
		UserDao userDao = new UserDaoImpl();
		userDao.updateLogInd("N", user.getEmail());
		introductionMenu();
	}

}
