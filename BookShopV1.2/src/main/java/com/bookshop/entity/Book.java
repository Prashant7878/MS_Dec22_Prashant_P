package com.bookshop.entity;

import org.springframework.stereotype.Component;

import com.bookshop.constants.Genre;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
@Component
@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "Name cannot be blank")
	private String name;
	@NotNull(message = "Author cannot be blank")
	private String author;
	@NotNull(message = "Genre cannot be blank")
	private Genre genre;
	@NotNull(message = "Price cannot be blank")
	private double price;
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(int id, String name, String author, Genre genre, double price) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.genre = genre;
		this.price = price;
	}
	public Book(int id, String name, String author,double price) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}	
	
}
