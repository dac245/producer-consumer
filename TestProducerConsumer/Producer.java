package TestProducerConsumer;

import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Random;

public class Producer extends SharedTextField implements Runnable{
	// hold the messages
	private int targ;
	private int max;
	private int msg;
	private ArrayBlockingQueue<String> buffer = null;
	
	// constructor
	public Producer(ArrayBlockingQueue<String> bf, int _targ, int _max) {
		buffer = bf;	// gets the buffer
		targ = _targ;	// gets the target number
		max = _max;		// gets the max number
	}
	
	
	
	// produce the numbers
	public void run() {
		
			while(true) {
				Random rand = new Random();	// makes a random object named rand to get random numbers
				int randNum = rand.nextInt((max - 0) + 1) + 0;	// gets random numbers from 0 to the max number
				try {
					super.setValue(randNum);	// uses the setValue method from SharedTextField to change the JTextField
					buffer.put(getValue());	// gets the value from the method and puts it in the buffer
				}
				catch(Exception e) {}

			}
		
	}
}