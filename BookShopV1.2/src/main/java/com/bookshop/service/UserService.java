package com.bookshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookshop.entity.Book;
import com.bookshop.entity.Orders;
import com.bookshop.entity.User;
import com.bookshop.exceptions.InvalidCredentialException;
import com.bookshop.exceptions.InvalidPasswordException;
import com.bookshop.exceptions.UserAlreadyExistException;
import com.bookshop.repository.UserReopsitory;

import jakarta.persistence.criteria.Order;

@Service
public interface UserService {
	
	public List<Book> getAllBooks();
	
	public Book searchBookByName(String name);
	
	public User getUser(String email,String password);
	
	public String orderBook(String bookName, int qty, String email);
	
	public void registerUser(User user) throws UserAlreadyExistException,InvalidPasswordException;
	
	public User logIn(String email,String password) throws InvalidCredentialException;
	
	public List<Orders> getAllOrdersofUser(int userId);
}
