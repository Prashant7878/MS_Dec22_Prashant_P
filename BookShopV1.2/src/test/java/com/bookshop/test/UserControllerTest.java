package com.bookshop.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.bookshop.controller.UserController;
import com.bookshop.entity.User;
import com.bookshop.exceptions.InvalidCredentialException;
import com.bookshop.service.UserServiceImpl;

@SpringBootTest(classes= {UserControllerTest.class})
class UserControllerTest {

	@Mock
	UserServiceImpl userServ;
	
	
	@InjectMocks
	UserController userCont;
	
	@Test
	public void userLoginTest() throws InvalidCredentialException
	{
		List<User> userList = new ArrayList<>();
		
		userList.add(new User(1,"prashant","pagare","email@gmail.com",456789,"Prashant1232"));
		userList.add(new User(2,"prashant","pagare","email2@gmail.com",456789,"Prashant1234"));
		userList.add(new User(3,"prashant","pagare","email3@gmail.com",456789,"Prashant1235"));
		
		//User u = null;
		for(User u:userList)
		{
			when(userServ.logIn(u.getEmail(),u.getPassword())).thenReturn(u);
			Assertions.assertEquals(u.getFname(), userServ.logIn("email@gmail.com","Prashant123" ).getFname());
		}
		
		
		
//		when(userServ.logIn(u.getEmail(),u.getPassword())).thenReturn(u);
//		
//		Assertions.assertEquals(u.getFname(), userServ.logIn("ema@gmail.com","Prashant123" ).getFname());
		
	}

}
