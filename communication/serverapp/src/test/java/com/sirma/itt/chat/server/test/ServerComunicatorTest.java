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

@RunWith(MockitoJUnitRunner.class)
public class ServerComunicatorTest {

	@Mock
	ConectionPanel listener;
	ServerComunicator comunicator;
	@Mock
	MessageTransferer transferer;

	@Before
	public void setUp() throws Exception {
		comunicator = new ServerComunicator(listener);
	}

	@Test
	public void proccesMessageTest() {
		Message message = new Message(MessageCommand.USER_LOG_IN);
		message.sender = "test";
		comunicator.processMesage(message, transferer);
		Mockito.verify(listener, Mockito.atLeast(0)).setConectionStatus(
				Mockito.any(MessageCommand.class));
		message = new Message(MessageCommand.TEXT_MESAGE, "test");
		message.sender = "test";
		comunicator.processMesage(message, transferer);
		Mockito.verify(listener, Mockito.atLeast(0)).showMesage(
				Mockito.anyString(), Mockito.anyString());
	}

	@Test
	public void addUserSessionTest() {
		comunicator.addUserSession(transferer);
		Mockito.verify(transferer, Mockito.atLeast(0)).start();
		;
	}

	@Test
	public void closeUserSessionTest() {

	}

}
