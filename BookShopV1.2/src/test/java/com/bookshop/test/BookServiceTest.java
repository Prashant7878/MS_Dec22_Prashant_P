package com.bookshop.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.bookshop.entity.Book;
import com.bookshop.repository.BookRepository;
import com.bookshop.repository.UserReopsitory;
import com.bookshop.service.BookService;
import com.bookshop.service.BookServiceImpl;
import com.bookshop.service.UserService;

@SpringBootTest(classes= {BookServiceTest.class})
class BookServiceTest {

	@Mock
	BookRepository bookRepo;
	
	@InjectMocks
	BookServiceImpl bookServ;
	
	public List<Book> BookDb;
	
	
	
	@Test
	@Order(1)
	public void test_getAllBooks()
	{
		List<Book> bookList = new ArrayList<>();
		bookList.add(new Book(1,"name","author",5262));
		bookList.add(new Book(2,"name2","author2",4587));
		bookList.add(new Book(3,"name3","author3",4758));
		
		when(bookRepo.findAll()).thenReturn(bookList);
		
		Assertions.assertEquals(3, bookServ.getAllBooks().size());
	}
}
