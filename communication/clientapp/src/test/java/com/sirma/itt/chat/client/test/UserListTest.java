package com.sirma.itt.chat.client.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.sirma.itt.chat.client.UserList;

/**
 * Check correct adding and removing on users from list.
 * 
 * @author GecaTM
 *
 */
public class UserListTest {

	private UserList userList;

	@Before
	public void setUp() throws Exception {
		userList = new UserList(null);
	}

	/**
	 * Check correct adding on users.
	 */
	@Test
	public void addUserTest() {
		userList.addUser("test");
		assertTrue(userList.contains("test"));
		userList.addUser("test");
		assertTrue(userList.contains("test"));
	}

	/**
	 * Check correct removing on users.
	 */
	@Test
	public void removeUserTest() {
		userList.addUser("test");
		userList.removeUser("test");
		assertFalse(userList.contains("test"));
		userList.removeUser("test");
		assertFalse(userList.contains("test"));
	}

	/**
	 * Check correct function on contains method.
	 */
	@Test
	public void containsTest() {
		assertFalse(userList.contains("test"));
		userList.addUser("test");
		assertTrue(userList.contains("test"));
	}

	/**
	 * Check correct function on contains method.
	 */
	@Test
	public void clearTest() {
		userList.addUser("test");
		userList.clear();
		assertFalse(userList.contains("test"));
	}

}
