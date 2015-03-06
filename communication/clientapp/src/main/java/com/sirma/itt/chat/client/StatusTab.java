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

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Manage sending and receiving messages.
 * 
 * @author Evgeni Stefanov
 * 
 */
public class StatusTab extends JSplitPane implements ActionListener,
		KeyListener, MouseListener {

	private static final String newLine = System.getProperty("line.separator");
	private final JTextArea textArea;
	private final JTextField textField;
	private final MessageLogger messageLogger;
	private final DateFormat dateFormat;
	private final Double dividerLocation = 0.85D;

	/**
	 * Create new conversation.
	 */
	public StatusTab() {
		setOrientation(VERTICAL_SPLIT);
		dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		textArea = new JTextArea();
		textArea.addMouseListener(this);
		JScrollPane scrollPane = new JScrollPane(textArea);
		textField = new JTextField();
		textField.addActionListener(this);
		textField.addKeyListener(this);
		textField.addMouseListener(this);
		textArea.setEditable(false);
		messageLogger = new MessageLogger();
		setTopComponent(scrollPane);
		setBottomComponent(textField);
	}

	/**
	 * Display connection status.
	 */
	public void setConectionStatus(String conectionCondition) {
		writeText(conectionCondition);
	}

	/**
	 * Create message.
	 */
	public String createMessage(String name, String text) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<");
		stringBuilder.append(dateFormat.format(System.currentTimeMillis()));
		stringBuilder.append("><");
		stringBuilder.append(name);
		stringBuilder.append(">");
		stringBuilder.append(text);
		stringBuilder.append(newLine);
		return stringBuilder.toString();
	}

	public String getTextFieldText() {
		return textField.getText();
	}

	/**
	 * Write message to text area.
	 */
	public void writeText(String text) {
		textArea.setText(textArea.getText() + newLine + text);
		textArea.setCaretPosition(textArea.getText().length());
	}

	/**
	 * Detect when user send new message.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		messageLogger.logMessage(textField.getText());
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
	public void paint(Graphics arg0) {
		setDividerLocation(dividerLocation);
		super.paint(arg0);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}
}
