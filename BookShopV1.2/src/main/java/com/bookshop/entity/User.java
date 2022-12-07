package com.bookshop.entity;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@NotBlank@NotNull(message = "Cannot be blank!!")
	private String fname;
	
	@NotNull(message = "Cannot be blank")
	private String lname;
	
	@Email(message = "Enter valid email ")
	private String email;
	
	@Min(value = 10, message = "Mobile number should be 10 digit")
	private long mobileNo;
	
	@NotBlank
	private String password;	
	
	
	public User() {

	}
	
	

	public User(int userId, String fname, String lname, String email, long mobileNo, String password) {
		super();
		this.userId = userId;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.mobileNo = mobileNo;
		this.password = password;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
		
}
