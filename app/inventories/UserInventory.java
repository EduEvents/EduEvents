package inventories;

import models.User;

import java.util.Collection;
import java.util.HashSet;

public class UserInventory {
	private static UserInventory inventory = new UserInventory();
	private HashSet<User> users = new HashSet<User>();
	protected UserInventory()
	{}



	public static UserInventory getInstance()
	{
		return inventory;
	}

	public void addUser(User user)
	{
		users.add(user);
	}

  public boolean isRegistered(User user) {
        return (users.contains(user));
  }

	/**
	* Returns value of inventory
	* @return
	*/
	public static UserInventory getInventory() {
		return inventory;
	}

	/**
	* Sets new value of inventory
	* @param
	*/
	public static void setInventory(UserInventory inventory) {
		UserInventory.inventory = inventory;
	}

	/**
	* Returns value of users
	* @return
	*/
	public HashSet<User> getUsers() {
		return users;
	}

	/**
	* Sets new value of users
	* @param
	*/
	public void setUsers(HashSet<User> users) {
		this.users = users;
	}

}
