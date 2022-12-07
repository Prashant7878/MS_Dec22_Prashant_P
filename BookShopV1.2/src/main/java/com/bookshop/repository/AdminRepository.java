package com.bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookshop.entity.Book;

@Repository
public interface AdminRepository extends JpaRepository<Book, Integer> {

}
