package com.sirma.itt.chat.client;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import com.sirma.itt.comunicator.Message;

/**
 * Manage sending and receiving messages.
 * 
 * @author Evgeni Stefanov
 * 
 */
public class Conversation extends JSplitPane implements ActionListener,
		KeyListener, MouseListener {

	private static final DateFormat dateFormat = new SimpleDateFormat(
			"MM/dd HH:mm:ss");
	private static final String myMessageHTMLopenTag = "<font size=\"4\" color=\"blue\">";
	private static final String myMessageHTMLcloseTag = "</font>";
	private static final String otherMessageHTMLopenTag = "<font size=\"4\" color=\"black\">";
	private static final String otherMessageHTMLcloseTag = "</font>";
	private static final String dateFormatHTMLopenTag = "<font size=\"4\"><i>";
	private static final String dateFormatHTMLcloseTag = "</i></font>";
	private static final String newLine = System.getProperty("line.separator");
	private final MessageLogger messageLogger = new MessageLogger();
	private final View view;
	private final JEditorPane textArea = new JEditorPane();
	private final JTextField textField = new JTextField();
	private final Double dividerLocation = 0.85D;
	private String htmlMessage = "";
	private boolean conected = false;

	/**
	 * Create new conversation.
	 */
	public Conversation(String name, View view) {
		setName(name);
		setOrientation(VERTICAL_SPLIT);
		this.view = view;
		textArea.setName(name);
		textArea.setContentType("text/html");
		textArea.setEditable(false);
		textArea.addMouseListener(this);
		JScrollPane scrollTextArea = new JScrollPane(textArea);
		textField.setName(name);
		textField.addActionListener(this);
		textField.addKeyListener(this);
		textField.addMouseListener(this);
		setTopComponent(scrollTextArea);
		setBottomComponent(textField);
	}

	public boolean isConected() {
		return conected;
	}

	public void setConected(boolean conected) {
		this.conected = conected;
	}

	/**
	 * Create message.
	 */
	public String createMessage(String name, String text) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(dateFormatHTMLopenTag + "&lt;");
		stringBuilder.append(dateFormat.format(System.currentTimeMillis()));
		stringBuilder.append("&gt;" + dateFormatHTMLcloseTag + "&lt;");
		stringBuilder.append(name);
		stringBuilder.append("&gt;");
		stringBuilder.append(text);
		return stringBuilder.toString();
	}

	public String getTextFieldText() {
		return textField.getText();
	}

	/**
	 * Write message to text area.
	 */
	public void writeMesage(String name, String text) {
		htmlMessage = htmlMessage + otherMessageHTMLopenTag
				+ createMessage(name, text) + "<br>" + otherMessageHTMLcloseTag;
		textArea.setText(htmlMessage);
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}

	/**
	 * Detect when user send new message.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		messageLogger.logMessage(textField.getText());
		htmlMessage = htmlMessage + myMessageHTMLopenTag
				+ createMessage("Me", textField.getText()) + "<br>"
				+ myMessageHTMLcloseTag;
		textArea.setText(htmlMessage);
		textArea.setCaretPosition(textArea.getDocument().getLength());
		view.sendMessage(new Message(getName(), textField.getText()));
		textField.setText("");
	}

	/**
	 * Action listener.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getSource().getClass() == JTextField.class) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				textField.setText(messageLogger.getNextMessage());
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				textField.setText(messageLogger.getPreviousMessage());
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		setDividerLocation(dividerLocation);
		super.paint(g);
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		view.clearNotification(getName());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
