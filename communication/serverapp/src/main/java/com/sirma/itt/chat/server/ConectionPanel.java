package com.sirma.itt.chat.server;

import java.awt.Component;
import java.awt.Dimension;
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

/**
 * Manage user connection request.
 * 
 * @author Evgeni Stefanov
 * 
 */
public class ConectionPanel extends JFrame {

	private static final String newLine = System.getProperty("line.separator");
	private final JTextField ipAdress = new JTextField(15);
	private final JTextField port = new JTextField(4);
	private final JTextArea textArea = new JTextArea();
	private final ActionListener listener;

	/**
	 * Create connection user interface.
	 */
	public ConectionPanel(ActionListener listener) {
		this.listener = listener;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel panel = new JPanel();
		ipAdress.setText("localhost");
		port.setText("1300");
		JButton startConection = new JButton("Conect");
		startConection.setName(ComponentID.CONECT_BUTTON_ID);
		startConection.addActionListener(listener);
		JButton stopConection = new JButton("Disconect");
		stopConection.setName(ComponentID.DISCONECT_BUTTON_ID);
		stopConection.addActionListener(listener);
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
		textArea.setText(message + newLine + textArea.getText());
	}

	public String getIP() {
		return ipAdress.getText();
	}

	public int getPort() {
		return Integer.parseInt(port.getText());
	}

}
