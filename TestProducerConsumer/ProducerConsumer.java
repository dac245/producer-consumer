package TestProducerConsumer;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.*;
import java.awt.event.ActionListener;

public class ProducerConsumer extends JFrame implements ActionListener{	
	private JTextField maxText;
	private JTextField targetText;
	public static SharedTextField bufferText;
	public static JTextArea showNums;
	private int maxNum = 0;
	private int targNum = 0;
	
	public ProducerConsumer(){	// makes the GUI for input/output
		super("Target Finder");	// names the JFrame
		JPanel top = new JPanel();	// creates the JPanel as well as the other 2
		JPanel buttons = new JPanel();
		JPanel middle = new JPanel();
		top.setLayout(new FlowLayout());	// sets the layout to flowlayout to the panels. Same goes for the other 2
		buttons.setLayout(new FlowLayout());
		middle.setLayout(new FlowLayout());
		
		/**
		 *	The rest of the code up until the end bracket of this constructor makes the 
		 *	JFrame complete so we can use the GUI for our Producer and Consumer classes
		 */
		
		JLabel max = new JLabel("Max #");
		maxText = new JTextField(8);
		JLabel target = new JLabel("Target #");
		targetText = new JTextField(8);
		JLabel buffer = new JLabel("Buffer");
		bufferText = new SharedTextField();
		bufferText.setEditable(false);
		
		top.add(max);
		top.add(maxText);
		top.add(target);
		top.add(targetText);
		top.add(buffer);
		top.add(bufferText);
		
		showNums = new JTextArea();
		showNums.setLineWrap(true);
		showNums.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(showNums, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(420,190));
		middle.add(scrollPane);
		
		JButton button1 = new JButton("Start");
		JButton button2 = new JButton("Reset");
		JButton button3 = new JButton("Exit");
		
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		
		buttons.add(button1);
		buttons.add(button2);
		buttons.add(button3);
		
		add(top, BorderLayout.NORTH);
		add(middle, BorderLayout.CENTER);
		add(buttons, BorderLayout.SOUTH);
		
		setSize(480,300);
		setLocation(200,300);
		setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 *	This method makes sure the buttons work as intended when they are pressed.
	 *	They get the which button is pressed from .addActionListener() and the
	 *	buttons do as they say.
	 */
	
	public void actionPerformed(ActionEvent ae){
		if(ae.getActionCommand().equals("Start")){
			try{
				maxNum = Integer.parseInt(maxText.getText());	// changes the maxText from the JTextField to int
				targNum = Integer.parseInt(targetText.getText());	// changes the targText from the JTextField to int
				
				if(targNum > maxNum){	// if the target number is greater than the max number a window pop ups about the error
					JOptionPane.showMessageDialog(null, "Please Enter a Target Number < Max Number", "Error", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				ArrayBlockingQueue<String> buff = new ArrayBlockingQueue<String>(maxNum);	// queue for the threads so they can store the numbers
				
				Producer prd = new Producer(buff, targNum, maxNum);	// producer passes the queue, target number and max number
				Consumer cons = new Consumer(buff, targNum, maxNum);	// consumer passes the queue, target number and max number
				
				Thread pThread = new Thread(prd);	//	creates thread for the producer and the next line does the same for the consumer
				Thread cThread = new Thread(cons);
				
				pThread.start();	// starts the thread with .start() and goes to the respective classes with run()
				cThread.start();
				
				
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(null, "Please Enter a Positive Number", "Error", JOptionPane.INFORMATION_MESSAGE);
			}
		  }
		if(ae.getActionCommand().equals("Reset")){
			showNums.setText("");
			targetText.setText("");
			bufferText.setText("");
			maxText.setText("");
			
			maxText.requestFocusInWindow();
		}
		if(ae.getActionCommand().equals("Exit")){
			System.exit(0);
		}
	}
	
	
}