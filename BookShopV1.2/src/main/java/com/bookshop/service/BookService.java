package com.bookshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bookshop.entity.Book;
import com.bookshop.exceptions.BookAlreadyExistException;
import com.bookshop.exceptions.BookNotFoundExeption;

@Service

public interface BookService {

		public void registerBook(Book b) throws BookAlreadyExistException;
		
		public List<Book> getAllBooks();
		
		public Optional<Book> findBookById(int id);
		
		public void removeBookById(int id) throws BookNotFoundExeption;
		
		public void updateBookById(Book b1, int id);
}
