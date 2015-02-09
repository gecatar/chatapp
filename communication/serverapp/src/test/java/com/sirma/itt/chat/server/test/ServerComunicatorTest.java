package com.sirma.itt.chat.server.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.sirma.itt.chat.server.ConectionPanel;
import com.sirma.itt.chat.server.ServerComunicator;
import com.sirma.itt.comunicator.Message;
import com.sirma.itt.comunicator.MessageCommand;
import com.sirma.itt.comunicator.MessageTransferer;

/**
 * Test adding and removing clients from server.
 * 
 * @author GecaTM
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ServerComunicatorTest {

	@Mock
	private ConectionPanel listener;
	private ServerComunicator comunicator;
	@Mock
	private MessageTransferer transferer;

	@Before
	public void setUp() throws Exception {
		comunicator = new ServerComunicator(listener);
	}

	/**
	 * Test correct redirecting on messages.
	 */
	@Test
	public void proccesMessageTest() {
		Message message = new Message(MessageCommand.USER_LOG_IN);
		message.sender = "test";
		comunicator.processMesage(message, transferer);
		Mockito.verify(listener, Mockito.atLeast(0)).setConectionStatus(
				Mockito.any(MessageCommand.class));
		message = new Message(MessageCommand.TEXT_MESAGE, "test");
		message.sender = "test";
	}

	/**
	 * Test adding on users to server.
	 */
	@Test
	public void addUserSessionTest() {
		comunicator.addUserSession(transferer);
		Mockito.verify(transferer, Mockito.atLeast(1)).start();
	}

	/**
	 * Check correct disposing on users sessions.
	 */
	@Test
	public void closeUserSessionTest() {
		comunicator.addUserSession(transferer);
		comunicator.closeUserSession(transferer);
		Mockito.verify(transferer, Mockito.atLeast(1)).closeSocket();
	}

}
