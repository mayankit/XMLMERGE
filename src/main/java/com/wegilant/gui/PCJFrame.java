package com.wegilant.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.wegilant.App;
import com.wegilant.queue.Consumer;
import com.wegilant.queue.Producer;


/**
 * @author mayank.kumar
 * This is PCJFrame class that is launced after files merged
 *
 */
public class PCJFrame extends JFrame implements ActionListener {

	private static final Logger LOGGER = Logger.getLogger(PCJFrame.class.getName());
	
	JTextArea producerLabel = new JTextArea();
	JTextArea consumerLabel = new JTextArea();
	JScrollPane producerPane = new JScrollPane(producerLabel);
	JScrollPane consumerPane = new JScrollPane(consumerLabel);
	JButton start = new JButton();

	public JTextArea getProducerLabel() {
		return producerLabel;
	}

	public void setProducerLabel(JTextArea producerLabel) {
		this.producerLabel = producerLabel;
	}

	public JTextArea getConsumerLabel() {
		return consumerLabel;
	}

	public void setConsumerLabel(JTextArea consumerLabel) {
		this.consumerLabel = consumerLabel;
	}

	public JButton getStart() {
		return start;
	}

	public void setStart(JButton start) {
		this.start = start;
	}

	public PCJFrame() {

		LOGGER.info("PCJFrame: Initializing PCJFrame");
		setLayout(null);

		// producer text area configuration
		producerLabel.setText("Producer");
		producerLabel.setBounds(10, 10, 230, 230);
		producerLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		producerLabel.setLineWrap(true);

		// consumer text area configuration
		consumerLabel.setBounds(240, 10, 230, 230);
		consumerLabel.setText("Consumer");
		consumerLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		consumerLabel.setLineWrap(true);

		// button configuration
		start.setText("Start");
		start.setBounds(200, 260, 80, 25);
		start.addActionListener(this);

		// adding components on JFrame
		add(producerLabel);
		add(consumerLabel);
		add(start);
		add(producerPane);
		add(consumerPane);

		// setting properties of JFrame
		setTitle("Absolute positioning");
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
		LOGGER.info("PCJFrame: Initializing of PCJFrame completed");

	}

	// Action performed when button clicked
	public void actionPerformed(ActionEvent e) {
        
		LOGGER.info("PCJFrame: start button clicked");
		start.setEnabled(false);
		BlockingQueue<String> sharedQueue = new LinkedBlockingQueue<String>(3);
		Thread prodThread = new Thread(new Producer(sharedQueue, this));
		Thread consThread = new Thread(new Consumer(sharedQueue, this));
		prodThread.start();
		consThread.start();

	}

}
