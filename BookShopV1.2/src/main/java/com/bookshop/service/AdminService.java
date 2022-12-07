package com.bookshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bookshop.entity.Book;
import com.bookshop.entity.Orders;
import com.bookshop.entity.User;
import com.bookshop.exceptions.BookAlreadyExistException;
import com.bookshop.exceptions.BookNotFoundExeption;

@Service
public interface AdminService {
	
	public List<Book> getAllBook();
	
	public String addNewBook(Book book) throws BookAlreadyExistException;
	
	public String updateBookById(Book book, int id);
	
	public List<User> getAllUsers();
	
	public List<Orders> getAllOrders();
	
	public String deleteBookById(int id) throws BookNotFoundExeption;
	
}
