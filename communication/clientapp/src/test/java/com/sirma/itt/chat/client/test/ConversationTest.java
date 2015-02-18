package com.sirma.itt.chat.client.test;

import static org.junit.Assert.assertTrue;

import java.awt.event.ActionEvent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.sirma.itt.chat.client.Conversation;
import com.sirma.itt.chat.client.View;
import com.sirma.itt.comunicator.Message;

@RunWith(MockitoJUnitRunner.class)
public class ConversationTest {

	private Conversation conversation;
	@Mock
	private View view;

	@Before
	public void setUp() {
		conversation = new Conversation("test", view);
	}

	@Test
	public void createMessageTest() {
		String message = conversation.createMessage("user", "text");
		assertTrue(message.length() == 60);
		assertTrue(message.contains("user"));
		assertTrue(message.contains("text"));
	}

	@Test
	public void sendMessageTest() {
		conversation.setConected(true);
		conversation.actionPerformed(new ActionEvent(conversation, 0, null));
		conversation.setConected(false);
		conversation.actionPerformed(new ActionEvent(conversation, 0, null));
		Mockito.verify(view).sendMessage(Mockito.any(Message.class));
	}

}
