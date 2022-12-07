package com.bookshop.entity;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	
	@Autowired
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="books_id")
	private Book books;
	
	private int orderedQty;
	private double price;
	
	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Book getBook() {
		return books;
	}
	public void setBook(Book books) {
		this.books = books;
	}
	public int getOrderedQty() {
		return orderedQty;
	}
	public void setOrderedQty(int orderedQty) {
		this.orderedQty = orderedQty;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double calculatePrice(double qty, double price)
	{
		return (qty*price);
	}
	
}
