package TestProducerConsumer;

import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Consumer extends SharedTextField implements Runnable {
	private long startTime;
	private long endTime;
	private int targ;
	private int max;
	private String msg;
	private SharedTextField stf;
	private boolean x = true;
	private ArrayBlockingQueue<String> buf = null;

	// gets buffer reference
	public Consumer(ArrayBlockingQueue<String> bf, int _targ, int _max) {
		buf = bf;	// gets the buffer
		targ = _targ;	// gets the target number
		max = _max;		// gets the max number
	}
	
	

	// do work in the run method
	public void run() {
		long startTime = System.currentTimeMillis();	// gets the start of the time in milliseconds
		while(x) {
				try {
					msg = buf.take();	// gets the information from the queue
					ProducerConsumer.showNums.append(msg + " ");	// puts what was in the queue to the JTextArea
					
					
					if(msg.equals(Integer.toString(targ))){	// once it gets to the target number we stop the loop
						x = false;
					}
				}
				catch(Exception e) {}
			}
			long endTime = System.currentTimeMillis();	// gets the time it took to stop the thread
			long millis = endTime - startTime;	// subtracts both start and end time to get how long it took
			ProducerConsumer.showNums.append("\nIt took " + String.valueOf(millis) + " milliseconds");	// shows it in the JTextArea
	}
}