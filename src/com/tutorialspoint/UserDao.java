package com.tutorialspoint;
import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileNotFoundException;  
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.io.ObjectInputStream; 
import java.io.ObjectOutputStream; 
import java.util.ArrayList; 
import java.util.List;  

public class UserDao 
{
	public List<User> getAllUsers() {
		List<User> userList = null;
		try {
			File file = new File("Users2.dat");
			if(!file.exists())
			{
				User diana = new User(1, "Diana","Software Engineer");
				User davin = new User(2, "Davin","Real Estate Agent");
				User dixon = new User(3, "Dixon","Dynamics Consultant");
				User donna = new User(4, "Donna","Physical Trainer");
				userList = new ArrayList<User>();
				userList.add(diana);
				userList.add(davin);
				userList.add(dixon);
				userList.add(donna);
				saveUserList(userList);
			}
			else
			{
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				userList = (List<User>) ois.readObject();
				ois.close();
			}
		}
		catch(IOException e) { e.printStackTrace(); }
		catch(ClassNotFoundException e) { e.printStackTrace(); }
		return userList;
	}
	
	private void saveUserList(List<User> userList) 
	{
		try {
			File file = new File("Users2.dat");
			FileOutputStream fos;
			fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(userList);
			oos.close();
		}
		catch(FileNotFoundException e) { e.printStackTrace(); }
		catch(IOException e) { e.printStackTrace(); }
	}
	
	public User getUser(int id)
	{
		List<User> users = getAllUsers();
		for(User u : users)
		{
			if(u.getId()==id)
				return u;
		}
		return null;
	}
	
	public int deleteUser(int id)
	{
		List<User> users = getAllUsers();
		for(User u : users)
		{
			if(u.getId()==id)
			{
				users.remove(users.indexOf(u));
				saveUserList(users);
				return 1;
			}
		}
		return 0;
	}
	
	public int addUser(User pUser) {
		List<User> userList = getAllUsers(); 
		for (User user : userList) {
			if (user.getId() == pUser.getId()) {
				return 0;
			}
		}
		userList.add(pUser);
		saveUserList(userList);
		return 1;
	}

	
	public int updateUser(User pUser) 
	{
		List<User> userList = getAllUsers();

		for (User user : userList) 
		{
			if (user.getId() == pUser.getId()) 
			{
				int index = userList.indexOf(user);
				userList.set(index, pUser);
				saveUserList(userList);
				return 1;
			}
		}
		return 0;
	}
	
}




