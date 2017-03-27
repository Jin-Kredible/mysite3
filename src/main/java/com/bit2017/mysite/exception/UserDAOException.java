package com.bit2017.mysite.exception;

public class UserDAOException extends RuntimeException {
	private static final long serialVersionUID = 2628452273629080330L;
	
	public UserDAOException(String message) {
		super( message);
	}
	
	public UserDAOException() {
		super("UserDao Exception Occured");
	}

}
