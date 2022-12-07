package com.bookshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.bookshop.constants.Genre;
import com.bookshop.entity.Book;
import com.bookshop.entity.Orders;
import com.bookshop.entity.User;
import com.bookshop.exceptions.BookAlreadyExistException;
import com.bookshop.exceptions.BookNotFoundExeption;
import com.bookshop.repository.AdminRepository;
import com.bookshop.repository.OrderRepository;
import com.bookshop.repository.UserReopsitory;


@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	public AdminRepository adminRepo;

	@Autowired
	public UserReopsitory userRepo;
	@Autowired
	public OrderRepository orderRepo;
	
	@Override
	public List<Book> getAllBook() {
		
		return adminRepo.findAll();
	}

	@Override
	public String addNewBook(Book book) throws BookAlreadyExistException {
		
		if(adminRepo.existsById(book.getId()))
		{
			throw new BookAlreadyExistException();
		}else {
		adminRepo.save(book);
		}
		
		return "Book added successfully!!!";
	}

	@Override
	public String updateBookById(@RequestBody Book book1,int id) {
		
		Book book2 = adminRepo.findById(id).get();
		
		String name = book1.getName();
		Genre genre = book1.getGenre();
		String author = book1.getAuthor();
		double price = book1.getPrice();
		
		if(name!=null)
			book2.setName(name);
		if(genre != null)
			book2.setGenre(genre);
		if(author!=null)
			book2.setAuthor(author);
		if(price!=book2.getPrice())
			book2.setPrice(price);
		
		adminRepo.save(book2);
		
		return "Book updated Successfully!!!";
	}

	@Override
	public List<User> getAllUsers() {
	
		return userRepo.findAll();
	}

	@Override
	public List<Orders> getAllOrders() {
		
		return orderRepo.findAll();
	}

	@Override
	public String deleteBookById(int id) throws BookNotFoundExeption {
		
Optional<Book> book = adminRepo.findById(id);
		
		if(book.isPresent()) {
			adminRepo.deleteById(id);
		}
		else {
			throw new BookNotFoundExeption("not present");
		}
		
		return "Book removed successfully!!!";
	}

}
