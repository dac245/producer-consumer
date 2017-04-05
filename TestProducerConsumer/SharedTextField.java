package TestProducerConsumer;

import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.ArrayList;

public class SharedTextField extends JTextField{
	private String thisText;
	static public Object resource1 = new Object();
	static public Object resource2 = new Object();
	private int x;
	
	public SharedTextField(){
		super(8);
		
	}

	public String getValue(){	// gets the value from the JTextField buffer
		synchronized(resource1)	// used so only 1 thread uses it 
		{
			thisText = ProducerConsumer.bufferText.getText();
			return thisText;
		}
	}
	
	public void setValue(int value){	// sets the value from the JTextField buffer
		synchronized(resource2)	// used so only 1 thread uses it
		{
			String val = Integer.toString(value);
			ProducerConsumer.bufferText.setText(val);
		}
	}
}