package com.bookshop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookshop.constants.Genre;
import com.bookshop.entity.Book;
import com.bookshop.entity.Orders;
import com.bookshop.entity.User;
import com.bookshop.exceptions.BookAlreadyExistException;
import com.bookshop.exceptions.BookNotFoundExeption;
import com.bookshop.service.AdminServiceImpl;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	public AdminServiceImpl adminServ;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) 
    {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return errors;
    }
	
	
	//Retrives all books from databse
	@GetMapping("/allbooks")
	public List<Book> getAllBook() {

		return adminServ.getAllBook();
	}

	//Add new book 
	@PostMapping("/addbook")
	public String addNewBook(@RequestBody Book book) {
		try {
			adminServ.addNewBook(book);
		} catch (BookAlreadyExistException e) {
			// TODO Auto-generated catch block
			String excepMsg = e.getMessage();
			return excepMsg;
		}
		return "Book added successfully!!!";
	}

	@PutMapping("/updatebook/{id}")
	public String updateBookById(@PathVariable("id") int id,@RequestBody Book book1) {

		adminServ.updateBookById(book1,id);
		return "Book updated Successfully!!!";
	}

	@GetMapping("/allusers")
	public List<User> getAllUsers() {

		return adminServ.getAllUsers();
	}

	@GetMapping("/allorders")
	public List<Orders> getAllOrders() {

		return adminServ.getAllOrders();
	}

	//delete the book by taking bookId as input
	@DeleteMapping("/deletebookbyid/{id}")
	public String deleteBookById(@PathVariable("id") int id) {

		try {
			
			adminServ.deleteBookById(id);
			
		} catch (BookNotFoundExeption e) {
			
			return e.getMessage();
		}

		return "Book removed successfully!!!";
	}

}
