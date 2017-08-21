package com.tutorialspoint.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

import org.junit.Test;

import com.tutorialspoint.User;

public class TestUsers {

	@Test
	public void testGetAllUsers() {
		User diana = new User(1, "Diana","Software Engineer");
		User davin = new User(1, "Davin","Real Estate Agent");
		User dixon = new User(1, "Dixon","Dynamics Consultant");
		User donna = new User(1, "Donna","Physical Trainer");
		List<User> allUsers = new ArrayList<User>();
		allUsers.add(diana);
		allUsers.add(davin);
		allUsers.add(dixon);
		allUsers.add(donna);
		
		saveUserList(allUsers);
		
		System.out.print(allUsers);
	}

	
	private void saveUserList(List<User> userList) 
	{
		try {
			File file = new File("Users.dat");
			FileOutputStream fos;
			fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(userList);
			oos.close();
		}
		catch(FileNotFoundException e) { e.printStackTrace(); }
		catch(IOException e) { e.printStackTrace(); }
	}
}
