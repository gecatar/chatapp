package com.sirma.itt.chat.client.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.sirma.itt.chat.client.MessageLogger;

/**
 * Check correct logging on messages.
 * 
 * @author GecaTM
 *
 */
public class MessageLoggerTest {

	private MessageLogger messageLogger;

	@Before
	public void setUp() throws Exception {
		messageLogger = new MessageLogger();
	}

	/**
	 * Check get next message.
	 */
	@Test
	public void getNextMessageTest() {
		messageLogger.logMessage("test");
		assertEquals("test", messageLogger.getNextMessage());
		assertEquals("", messageLogger.getNextMessage());
	}

	/**
	 * Check get previous message.
	 */
	@Test
	public void getPreviousMessageTest() {
		messageLogger.logMessage("test");
		messageLogger.logMessage("proba");
		messageLogger.getNextMessage();
		messageLogger.getNextMessage();
		assertEquals("proba", messageLogger.getPreviousMessage());
		assertEquals("test", messageLogger.getNextMessage());
		assertEquals("", messageLogger.getNextMessage());
	}
}
