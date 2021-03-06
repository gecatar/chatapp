package com.sirma.itt.chat.server;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sirma.itt.comunicator.ComponentID;
import com.sirma.itt.comunicator.MessageCommand;

/**
 * Manage user connection request.
 * 
 * @author Evgeni Stefanov
 * 
 */
public class ConectionPanel extends JFrame implements ActionListener {

	private static final String newLine = System.getProperty("line.separator");
	private final JTextField ipAdress = new JTextField(15);
	private final JTextField port = new JTextField(4);
	private final JTextArea textArea = new JTextArea();
	private final Dimension defaultViewDimension = new Dimension(300, 400);
	private final ServerApp listener;

	/**
	 * Create connection user interface.
	 */
	public ConectionPanel(ServerApp listener) {
		this.listener = listener;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(defaultViewDimension);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		ipAdress.setText("localhost");
		port.setText("1300");
		JButton startConection = new JButton("Conect");
		startConection.setName(ComponentID.CONECT_BUTTON_ID);
		startConection.addActionListener(this);
		JButton stopConection = new JButton("Disconect");
		stopConection.setName(ComponentID.DISCONECT_BUTTON_ID);
		stopConection.addActionListener(this);
		JLabel ipLabel = new JLabel("IP Adress");
		ipLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		ipAdress.setMaximumSize(new Dimension(150, 20));
		ipAdress.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel portLabel = new JLabel("Port");
		portLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		port.setMaximumSize(new Dimension(80, 20));
		port.setAlignmentX(Component.CENTER_ALIGNMENT);
		startConection.setAlignmentX(Component.CENTER_ALIGNMENT);
		stopConection.setAlignmentX(Component.CENTER_ALIGNMENT);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setMaximumSize(new Dimension(200, 250));
		// -----------------------------------------------
		panel.add(ipAdress);
		panel.add(port);
		panel.add(startConection);
		panel.add(stopConection);
		panel.add(ipLabel);
		panel.add(ipAdress);
		panel.add(portLabel);
		panel.add(port);
		panel.add(startConection);
		panel.add(stopConection);
		panel.add(scrollPane);
		add(panel);
		setVisible(true);
	}

	/**
	 * Display message.
	 */
	public void displayMessage(String message) {
		textArea.setText(textArea.getText() + newLine + message);
		textArea.setCaretPosition(textArea.getText().length());
	}

	/**
	 * Gets IP address.
	 * 
	 * @return IP address.
	 */
	public String getIP() {
		return ipAdress.getText();
	}

	/**
	 * Gets port
	 * 
	 * @return port.
	 */
	public int getPort() {
		return Integer.parseInt(port.getText());
	}

	/**
	 * Change connection status displayed on screen.
	 */
	public void setConectionStatus(MessageCommand status) {
		if (status == MessageCommand.COMUNICATOR_CONECTING) {
			displayMessage("Waiting for clients...");
		}
		if (status == MessageCommand.COMUNICATOR_DISCONECTED) {
			displayMessage("Close connection.");
		}
	}

	/**
	 * Called when new user connect to server.
	 * 
	 * @param name
	 */
	public void addUser(String name) {
		displayMessage("User:" + name + " connected.");
	}

	/**
	 * Called when user leave server.
	 * 
	 * @param name
	 */
	public void removeUser(String name) {
		displayMessage("User:" + name + " disconnected.");
	}

	/**
	 * When user press connect or disconnect buttons.
	 */
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() instanceof JButton) {
			JButton button = (JButton) event.getSource();
			if (button.getName().equals(ComponentID.CONECT_BUTTON_ID)) {
				listener.startConection(ipAdress.getText(),
						Integer.parseInt(port.getText()));
			}
			if (button.getName().equals(ComponentID.DISCONECT_BUTTON_ID)) {
				listener.stopConnection();
			}
		}
	}

}
