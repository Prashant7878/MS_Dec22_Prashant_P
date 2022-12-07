package com.bookshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.bookshop.constants.Genre;
import com.bookshop.entity.Book;
import com.bookshop.exceptions.BookAlreadyExistException;
import com.bookshop.exceptions.BookNotFoundExeption;
import com.bookshop.repository.BookRepository;


@Component
@Service
public class BookServiceImpl implements BookService {

	@Autowired
	public BookRepository bsr;
	@Override
	public void registerBook(@RequestBody() Book b) throws BookAlreadyExistException {
		if(bsr.existsById(b.getId()))
		{
			throw new BookAlreadyExistException();
		}else {
		bsr.save(b);
		}		
	}
	
	@Override
	public List<Book> getAllBooks() {
		return bsr.findAll();
	}

	@Override
	public Optional<Book> findBookById(int id){
		
		Optional<Book> b = bsr.findById(id);
			
		return b;
	}

	@Override
	public void removeBookById(int id) throws BookNotFoundExeption
	{
		Optional<Book> b = bsr.findById(id);
		
		if(b.isPresent()) {
			bsr.deleteById(id);
		}
		else {
			throw new BookNotFoundExeption("not present");
		}
	}
	
	
	@Override
	public void updateBookById(Book book1, int id) {
		
		Book book2 = bsr.findById(id).get();
		
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
		bsr.save(book2);		
	}	
}
