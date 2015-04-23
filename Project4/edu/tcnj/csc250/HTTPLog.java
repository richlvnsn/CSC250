/**
* Assignment: Project 4
* Due date: 12/5/2014
* Instructor: Dr. DePasquale
* Submitted by: Rich Levenson and Daniel Sarnelli
*/

package edu.tcnj.csc250;
import java.util.Scanner;

/**
 * The HTTPLog class is used to store all the components of one line of the input file.
 * Accessors and mutators are also created.
 * @author Daniel Sarnelli
 * @author Rich Levenson
 */

public class HTTPLog implements Comparable<HTTPLog> {
	/**
	 * All of the private instance data is declared.
	 */
	
	private String date, request, base;
	private int responseCode, counter;
	private long numBytes, totalBytes;
	
	/**
	 * This constructor was created specifically for the detail command.
	 * In this case, only the base is passed.
	 * @param base
	 */

	public HTTPLog(String base) {
		this.base = ((base.equals("/")) ? "/index.html" : base);
	}
	
	/**
	 * This is the main constructor that takes in all of the necessary information.
	 * @param date is the date and time.
	 * @param request is the resource that contains the base.
	 * @param responseCode
	 * @param numBytes
	 */

	public HTTPLog(String date, String request, int responseCode, long numBytes) {
		this.date = date;
		this.request = request;
		this.responseCode = responseCode;
		this.numBytes = numBytes;
		
		totalBytes = numBytes;
		counter = 1;
		Scanner requestScan = new Scanner(this.request);
		Scanner findqMark = new Scanner(this.request);
		
		/**
		 * The base is taken as the first part of the resource prior to the \?.
		 */
		
		if (findqMark.findInLine("\\?") != null)
			requestScan.useDelimiter("\\?");
		this.base = requestScan.next();
		this.base = ((this.base.equals("/")) ? "/index.html" : this.base);

	}

	/**
	 * toString returns all of the necessary information separated by spaces.
	 */

	public String toString() {
		return ("" + date + " " + base + " " + totalBytes + " " + " " + responseCode + " " + counter);
	}
	
	/**
	 * getBase returns the base.
	 * @return base
	 */

	public String getBase() {
		return base;
	}
	
	/**
	 * setDate takes in a date and sets it as the new value.
	 * @param newDate is the date.
	 */

	public void setDate(String newDate) {
		date = newDate;
	}
	
	/**
	 * getDate returns the date.
	 * @return date
	 */

	public String getDate() {
		return date;
	}
	
	/**
	 * setStatusCode takes in an int and sets it as the response code.
	 * @param newCode is the response code.
	 */

	public void setStatusCode(int newCode) {
		responseCode = newCode;
	}
	
	/**
	 * getStatusCode returns the response code.
	 * @return response code
	 */

	public int getStatusCode() {
		return responseCode;
	}
	
	/**
	 * hashCode uses the base to create a number code based on the characters.
	 * This code will then be used to define the index of the hashtable.
	 */

	public int hashCode() {
		int code = 0;
		for (int i = 0; i < base.length(); i++) {
			code += base.charAt(i);
		}
		code /= 11;
		code += 2014;
		return code;
	}
	
	/**
	 * getBytes returns the number of bytes.
	 * @return bytes
	 */

	public long getBytes() {
		return numBytes;
	}
	
	/**
	 * getTotalBytes returns the total number of bytes.
	 * @return total number of bytes
	 */

	public long getTotalBytes() {
		return totalBytes;
	}
	
	/**
	 * addBytes takes in a long value and adds it to the previous totalBytes.
	 * @param bytes
	 */

	public void addBytes(long bytes) {
		totalBytes += bytes;
	}
	
	/**
	 * increment will increase the counter by 1.
	 */

	public void increment() {
		counter++;
	}
	
	/**
	 * getCounter returns the counter.
	 * @return counter
	 */

	public int getCounter() {
		return counter;
	}
	
	/**
	 * The compareTo is done strictly by comparing the base values.
	 */

	public int compareTo(HTTPLog T) {
		return T.getBase().compareTo(base);
	}
}