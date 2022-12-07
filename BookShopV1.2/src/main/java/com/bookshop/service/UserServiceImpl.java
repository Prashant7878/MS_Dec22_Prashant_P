package com.bookshop.service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookshop.entity.Book;
import com.bookshop.entity.Orders;
import com.bookshop.entity.User;
import com.bookshop.exceptions.InvalidCredentialException;
import com.bookshop.exceptions.InvalidPasswordException;
import com.bookshop.exceptions.UserAlreadyExistException;
import com.bookshop.repository.AdminRepository;
import com.bookshop.repository.BookRepository;
import com.bookshop.repository.OrderRepository;
import com.bookshop.repository.UserReopsitory;

import jakarta.persistence.criteria.Order;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	public UserReopsitory userRepo;
	
	@Autowired
	public BookRepository bookRepo;
	
	@Autowired
	public OrderRepository orderRepo;
	
	@Autowired
	public AdminRepository adminRepo;

	@Override
	public List<Book> getAllBooks() {
		return bookRepo.findAll();
	}

	@Override
	public Book searchBookByName(String name) {
		Book book = bookRepo.findBookByName(name);
		return book;
	}


	@Override
	public String orderBook(String bookName, int qty, String email ) {
				
		User user = userRepo.findByEmail(email);
		Book book = bookRepo.findBookByName(bookName);
		Orders order = new Orders();
		order.setBook(book);
		order.setOrderedQty(qty);
		order.setUser(user);
		double price = (book.getPrice()*qty);
		order.setPrice(price);
		
		orderRepo.save(order);
		return "Order placed successfully!!!";
		
	}

	@Override
	public User getUser(String email, String password) {
		return userRepo.findUserByEmailAndPassword(email, password);
		
	}


	@Override
	public void registerUser(User user) throws UserAlreadyExistException,InvalidPasswordException{

		User userDb = userRepo.findByEmail(user.getEmail());
		if(userDb==null)
		{
			String password = user.getPassword();
			String passRequire = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()]).{8,20}$";
			if(Pattern.compile(passRequire).matcher(password).matches())
			{
				userRepo.save(user);
			}else
			{
				throw new InvalidPasswordException("Please enter password atleast 8 characters should have atleast one upper, lower case,any special character and atleast one digit");
			}
						
		}
		else
		{
			throw new UserAlreadyExistException();
		}
		
	}

	@Override
	public User logIn(String email, String password) throws InvalidCredentialException {
		
		
		User user = userRepo.findUserByEmailAndPassword(email, password);
		
		if(user==null)
		{
			throw new InvalidCredentialException();
		}
		else
		{
			System.out.println(user.getEmail()+"    "+user.getPassword());
			return user;
		}
		
	}

	@Override
	public List<Orders> getAllOrdersofUser(int userId) {
		List<Orders> orderList = orderRepo.getOderdetailsofUser(userId);		
		return orderList;
	}
	
	
	
}
