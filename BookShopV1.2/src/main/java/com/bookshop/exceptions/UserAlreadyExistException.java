package com.bookshop.exceptions;

public class UserAlreadyExistException extends Exception {

	public UserAlreadyExistException() {
		super("you are already registered please login!!");
	}
}
