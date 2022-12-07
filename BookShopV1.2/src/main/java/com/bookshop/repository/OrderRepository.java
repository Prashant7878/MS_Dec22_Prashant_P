package com.bookshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookshop.entity.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {
	
	@Query(value="select o from Orders o where o.user.userId=?1")
	public List<Orders> getOderdetailsofUser(int uid);
	
}
