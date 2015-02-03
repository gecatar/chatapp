package com.sirma.itt.chat.server.test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.sirma.itt.chat.server.ConectionPanel;
import com.sirma.itt.chat.server.ServerComunicator;
import com.sirma.itt.comunicator.Message;
import com.sirma.itt.comunicator.MessageCommand;
import com.sirma.itt.comunicator.MessageTransferer;

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

	@Test
	public void proccesMessageTest() {
		comunicator.processMesage(new Message(MessageCommand.USER_LOG_IN),
				transferer);
		Mockito.verify(listener, Mockito.atLeast(1)).setConectionStatus(
				Mockito.any(MessageCommand.class));
		comunicator.processMesage(new Message(MessageCommand.TEXT_MESAGE),
				transferer);
		Mockito.verify(listener, Mockito.atLeast(1)).showMesage(
				Mockito.anyString(), Mockito.anyString());
	}

	@Test
	public void addUserSessionTest() {
		comunicator.addUserSession(transferer);
		Mockito.verify(transferer, Mockito.atLeast(1)).closeSocket();
	}

	@Test
	public void closeUserSessionTest() {

	}

}
