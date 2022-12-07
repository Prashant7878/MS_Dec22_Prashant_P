package com.bookshop.exceptions;

public class InvalidCredentialException extends Exception{

	public InvalidCredentialException() {
		super("Please enter valid credentials");
	}
}
