package com.bookshop.exceptions;

public class BookAlreadyExistException extends Exception {

	public BookAlreadyExistException() {
		
		super("Book is already present!!!");
		
	}
}
