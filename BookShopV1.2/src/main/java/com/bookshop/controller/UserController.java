package com.bookshop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookshop.dto.OrderDto;
import com.bookshop.dto.UserDto;
import com.bookshop.entity.Book;
import com.bookshop.entity.Orders;
import com.bookshop.entity.User;
import com.bookshop.exceptions.InvalidCredentialException;
import com.bookshop.exceptions.InvalidPasswordException;
import com.bookshop.exceptions.UserAlreadyExistException;
import com.bookshop.service.UserService;

import jakarta.persistence.criteria.Order;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	private static Logger log=LoggerFactory.getLogger(UserController.class);
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) 
    {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return errors;
    }
	
	

	@GetMapping("/allbooks")
	public List<Book> getAllBooks() {
		return userService.getAllBooks();
	}

	@GetMapping("/searchbook/{name}")
	public ResponseEntity<Book> searchBookByName(@PathVariable("name") String name) {
		return new ResponseEntity<>(userService.searchBookByName(name),HttpStatus.OK);
	}
	

	@PostMapping("/orderbook")
	public ResponseEntity<String> orderBook(@RequestBody OrderDto odto) {
		userService.orderBook(odto.getBookname(), odto.getQty(), odto.getEmail());
		
		log.info(odto.getBookname()+" ordered by "+odto.getEmail());
		return new ResponseEntity<>("Order placed successfully!!!",HttpStatus.OK);
	}

	@PostMapping("/registeruser")
	public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
		try {
			
			userService.registerUser(user);
			log.info(user.getFname()+" "+user.getLname()+" Registered");
			
		} catch (UserAlreadyExistException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.OK);
		} catch (InvalidPasswordException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.OK);
		}
		return new ResponseEntity<String>("Registered Successfully",HttpStatus.OK);
	}

	@PostMapping("/userlogin")
	public String UserlogIn(@RequestBody UserDto user1)
	{
		String email = user1.getEmail();
		String password = user1.getPassword();
		User user=null;
		String msg="";
		try {
			
			user = userService.logIn(email, password);
			log.info(user.getFname()+" "+user.getFname()+" loggedIn");
			return "Successfully login!!";
			
		} catch (InvalidCredentialException e) {
				msg=e.getMessage();		
		}
		return msg;
	}
	
	@GetMapping("/allorders/{id}")
	public ResponseEntity<List<Orders>> getAllOrdersOfUser(@PathVariable("id") int uid)
	{
		return new ResponseEntity<>(userService.getAllOrdersofUser(uid),HttpStatus.OK);
	}
}
