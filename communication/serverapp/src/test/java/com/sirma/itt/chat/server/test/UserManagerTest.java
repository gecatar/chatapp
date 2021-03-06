package com.sirma.itt.chat.server.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.sirma.itt.chat.server.UserManager;
import com.sirma.itt.comunicator.Message;
import com.sirma.itt.comunicator.MessageCommand;
import com.sirma.itt.comunicator.MessageTransferer;

/**
 * Test correct management on users sessions.
 * 
 * @author GecaTM
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class UserManagerTest {

	@Mock
	private MessageTransferer transferer;
	private UserManager userManager;

	@Before
	public void setUp() throws Exception {
		userManager = new UserManager();
	}

	/**
	 * Test correct registering on new user.
	 */
	@Test
	public void registerUserFirstTest() {
		userManager.registerUser("test", transferer);
		Mockito.verify(transferer, Mockito.atLeast(1)).sendData(
				Mockito.any(Message.class));
	}

	/**
	 * Test correct registering on new user.
	 */
	@Test
	public void registerUserSecondTest() {
		userManager.registerUser("test", transferer);
		userManager.registerUser("test", transferer);
		Mockito.verify(transferer, Mockito.atLeast(1)).closeSocket();
	}

	/**
	 * Test correct removing on user.
	 */
	@Test
	public void removeUserTest() {
		userManager.registerUser("phantom", transferer);
		userManager.removeUser(transferer);
		Mockito.verify(transferer, Mockito.never()).closeSocket();
		userManager.registerUser("test", transferer);
		userManager.removeUser(transferer);
		assertEquals("Users:", userManager.toString());
	}

	/**
	 * Test sending on messages to user.
	 */
	@Test
	public void sendMessageToUserTest() {
		userManager.registerUser("test", transferer);
		userManager.sendMesageToUser(Message.create()
				.setCommandID(MessageCommand.TEXT_MESAGE).setReceiver("test")
				.setText("message text"));
		userManager.sendMesageToUser(Message.create()
				.setCommandID(MessageCommand.TEXT_MESAGE)
				.setReceiver("missing").setText("message text"));
		Mockito.verify(transferer, Mockito.atLeast(1)).sendData(
				Mockito.any(Message.class));
	}

	/**
	 * Check correct removing on all users.
	 */
	@Test
	public void removeUsersSessionsTest() {
		userManager.registerUser("test", transferer);
		userManager.registerUser("testSecond", transferer);
		userManager.removeUsersSessions();
		Mockito.verify(transferer, Mockito.atLeast(2)).closeSocket();
		assertEquals("Users:", userManager.toString());
	}
}
