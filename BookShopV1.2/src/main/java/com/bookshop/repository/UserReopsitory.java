package com.bookshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookshop.entity.Book;
import com.bookshop.entity.Orders;
import com.bookshop.entity.User;

@Repository
public interface UserReopsitory extends JpaRepository<User, Integer> {

	@Query(value="select * from user where email=?1",nativeQuery = true)
	public User findByEmail(String email);
	
	@Query(value = "select * from User u  where u.email = ?1 and u.password = ?2",nativeQuery = true)
	public User findUserByEmailAndPassword(String email, String password);
	
	
}