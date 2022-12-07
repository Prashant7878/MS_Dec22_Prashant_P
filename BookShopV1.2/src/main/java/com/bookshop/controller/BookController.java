package com.bookshop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookshop.entity.Book;
import com.bookshop.exceptions.BookAlreadyExistException;
import com.bookshop.exceptions.BookNotFoundExeption;
import com.bookshop.service.BookService;

@RestController
public class BookController {

	@Autowired
	public BookService bookServ;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) 
    {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return errors;
    }
	
	@PostMapping("/registerNewBook")
	public ResponseEntity<String> registerNewBook(@RequestBody Book b)
	{
		String exception="";
		try {
			bookServ.registerBook(b);
		} catch (BookAlreadyExistException e) {
			exception = e.getMessage();
			return  new ResponseEntity<>(exception,HttpStatus.ALREADY_REPORTED);
		}
		return new ResponseEntity<>("Successfully added",HttpStatus.OK);
	}
	
	@GetMapping("/allbooks")
	public List<Book> getAll()
	{
		return bookServ.getAllBooks();
	}
	
	@GetMapping("/findBook")
	public ResponseEntity<Book> findBook(@RequestParam int id)
	{
		return new ResponseEntity<>(bookServ.findBookById(id).get(),HttpStatus.OK);
	}
	
	@DeleteMapping("/removeBook/{id}")
	public String removeBook(@PathVariable("id") int id)
	{
		String err="";
		try {
			bookServ.removeBookById(id);
			
		} catch (BookNotFoundExeption e) {
			err = e.getMessage();
			return err;
		}
		err="successfully Deleted";
		return err;
	}
	
	@PutMapping("/updateBook/{id}")
	public String updateBook(@PathVariable("id") int id,Book b)
	{
		bookServ.updateBookById(b,id);
		return "Book updated successfully";
	}
}
