/**
 * Assignment: Project 2 - Driver.java
 * Due Date: 10/23/2014
 * Instructor: Dr. DePasquale
 * Submitted by Richard Levenson and Jeremy Leon
 */

import java.util.*;
import java.io.*;
import java.net.*;

/**
 * The Driver class contains the main method which drives the Processor class.
 * @author	Richard Levenson
 * @author	Jeremy Leon
 */
public class Driver
{
	/**
	 * The main method instantiates a Processor object by passing it a string that contains the URL of the online server that contains the input file.
	 * It then calls the scanAssignCreate() and outputToFile() methods of the Processor.
	 */
	public static void main(String[] args) throws MalformedURLException, IOException
	{
		/**
		 * The Processor that will be used to scan, handle, and output data.
		 */
		Processor process = new Processor("https://s3.amazonaws.com/depasquale/datasets/personalData.txt");
		process.scanAssignCreate();
		process.outputToFile();
	}
}