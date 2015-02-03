package com.sirma.itt.chat.server.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.sirma.itt.chat.server.UserManager;
import com.sirma.itt.comunicator.Message;
import com.sirma.itt.comunicator.MessageTransferer;

@RunWith(MockitoJUnitRunner.class)
public class UserManagerTest {

	@Mock
	MessageTransferer transferer;

	/**
	 * Test correct registering on new user.
	 */
	@Test
	public void registerUserTestSecond() {
		UserManager userManager = new UserManager();
		userManager.registerUser("test", transferer);
		assertEquals("Users:\ntest", userManager.toString());
	}

	/**
	 * Test correct removing on user.
	 */
	@Test
	public void removeUserTest() {
		UserManager userManager = new UserManager();
		userManager.registerUser("test", transferer);
		userManager.removeUser(transferer);
		assertEquals("Users:", userManager.toString());
		Mockito.verify(transferer, Mockito.atLeast(1)).closeSocket();
	}

	/**
	 * Test sending on messages to user.
	 */
	@Test
	public void sendMessageToUserTest() {
		UserManager userManager = new UserManager();
		userManager.registerUser("test", transferer);
		userManager.sendMesageToUser(new Message("sender", "message text"));
		Mockito.verify(transferer, Mockito.atLeast(1)).sendData(
				Mockito.any(Message.class));
	}

	/**
	 * Check correct removing on all users.
	 */
	@Test
	public void removeUsersSessionsTest() {
		UserManager userManager = new UserManager();
		userManager.registerUser("test", transferer);
		userManager.registerUser("testSecond", transferer);
		userManager.removeUsersSessions();
		Mockito.verify(transferer, Mockito.atLeast(2)).closeSocket();
		assertEquals("Users:", userManager.toString());
	}
}
