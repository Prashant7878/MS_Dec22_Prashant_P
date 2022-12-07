package com.bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookshop.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

	@Query(value = "select * from bookShop.Book where name = ?1",nativeQuery = true)
	public Book findBookByName(String name);
	
}
