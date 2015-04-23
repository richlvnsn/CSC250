/**
* Assignment: Project 4
* Due date: 12/5/2014
* Instructor: Dr. DePasquale
* Submitted by: Rich Levenson and Daniel Sarnelli
*/

package edu.tcnj.csc250;
import jsjf.*;
import java.io.*;
import java.util.*;

/**
 * The Driver class is used for reading the information, storing it, and taking commands.
 * It also continually keeps track of all errors to output in a text file.
 * @author Richard Levenson
 * @author Daniel Sarnelli
 */

public class Driver {
	/**
	 * The main method attempts to read from a file and store the information in a hashtable in the try block.
	 * If this does not occur for some reason, the error is recorded.
	 * @throws IOException
	 */
	
	public static void main(String[] args) throws IOException {
		/**
		 * errors.txt will keep track of any and all errors that occur while the program is running.
		 */
		
		PrintWriter errors = new PrintWriter(new File("errors.txt"));
		try {
			Scanner logScan = new Scanner(new File("access_log"));
			LinkedChainedHashtable<HTTPLog> hashtable = new LinkedChainedHashtable<HTTPLog>();
			fillTable(logScan, hashtable);
			/**
			 * Take commands will deal with all user input once the file is read from properly.
			 */
			
			takeCommands(hashtable, errors);
		}
		catch(IOException e) {
			System.out.println("Cannot find file.");
			errors.println("Cannot find file.");
		}
		errors.close();
	}
	
	/**
	 * The readLine method takes all of the information from one line of the file.
	 * A local variable is created for each element, although many of these are unneeded.
	 * Finally, an HTTPLog is created with the appropriate information.
	 * @param log is the Scanner.
	 * @return an HTTPLog.
	 */

	public static HTTPLog readLine(Scanner log) {
		String line = log.nextLine();
		Scanner lineScan = new Scanner(line);

		String ip = lineScan.next();
		lineScan.useDelimiter("\\[|\\]");
		lineScan.next();
		String date = lineScan.next();
		lineScan.useDelimiter("\"");
		lineScan.next();
		String resource = lineScan.next();

		Scanner resourceScan = new Scanner(resource);
		String command = resourceScan.next();
		String resourceName = resourceScan.next();
		String protocol = resourceScan.next();

		lineScan.reset();
		lineScan.next();
		int responseCode = lineScan.nextInt();
		long numBytes = 0;
		if(lineScan.hasNextLong()) {
			numBytes = lineScan.nextLong();
		}
		lineScan.useDelimiter("\"");
		lineScan.next();
		String referringPage = lineScan.next();
		lineScan.next();
		String userAgent = lineScan.next();

		HTTPLog temp = new HTTPLog(date, resourceName, responseCode, numBytes);
		return temp;
	}
	
	/**
	 * The fillTable method is called right after the hashtable is created.
	 * This method is used to add each HTTPLog if it has a unique resource, or add its number of bytes to a pre-existing one.
	 * @param log is the Scanner.
	 * @param hashtable
	 */

	public static void fillTable(Scanner log, LinkedChainedHashtable hashtable) {
		while(log.hasNext()) {
			HTTPLog temp = readLine(log);
			boolean added = hashtable.addElement(temp);
			
			/**
			 * If the resource name was found, added will be false.
			 * In this case, the memory will be added to that resource and the date-time will be updated.
			 * Also, a counter is used to track the number of times it is accessed.
			 */
			
			if(added == false) {
				((HTTPLog)hashtable.find(temp)).addBytes(temp.getBytes());
				((HTTPLog)hashtable.find(temp)).setDate(temp.getDate());
				((HTTPLog)hashtable.find(temp)).increment();
				((HTTPLog)hashtable.find(temp)).setStatusCode(temp.getStatusCode());
			}
		}
	}
	
	/**
	 * The takeCommands method is a loop that the program will run through until the user enters QUIT.
	 * It calls decipherCommand to actually perform whatever the user is requesting.
	 * @param hashtable
	 * @param errors
	 */
	
	public static void takeCommands(LinkedChainedHashtable hashtable, PrintWriter errors) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your command: ");
		String command = scanner.nextLine();
		
		if (command.compareTo("QUIT") != 0) {
			decipherCommand(command, hashtable, errors);
		}
		
		scanner.close();
	}
	
	/**
	 * decipherCommand is used to either perform the action that the user is requesting, or print an invalid command message.
	 * @param c is the command.
	 * @param hashtable
	 * @param errors
	 */
	
	public static void decipherCommand(String c, LinkedChainedHashtable hashtable, PrintWriter errors) {
		if (c.length() >= 6) {
			if (c.substring(0, 6).compareTo("DETAIL") == 0) {
				
				/**
				 * When the detail command is used, the subsequent string is then passed to a separated method to be handled.
				 */
				
				if (c.length() >= 8) {
					detail(c.substring(7, c.length()),hashtable, errors);
				}
				else {
					System.out.println("Invalid command.");
					errors.println("Invalid command entered.");
				}
			}
			
			/**
			 * The other commands are also handled within their own methods.
			 * If none of these commands are called, an invalid command message is printed.
			 */
			
			else if (c.compareTo("TOPTENSERVED") == 0) {
				topTenServed(hashtable, errors);
			}
			else if (c.compareTo("TOPTENSIZE") == 0) {
				topTenSize(hashtable, errors);
			}
			else {
				System.out.println("Invalid command.");
				errors.println("Invalid command entered.");
			}
		}
		else if (c.compareTo("CHAIN") == 0) {
			chain(hashtable, errors);
		}
		else {
			System.out.println("Invalid command.");
			errors.println("Invalid command entered.");
		}
		
		/**
		 * Regardless of the outcome of this method, takeCommands will be called again.
		 * This continues until the user enters QUIT.
		 */
		
		takeCommands(hashtable, errors);
	}
	
	public static void detail(String r, LinkedChainedHashtable hashtable, PrintWriter errors) {
		System.out.println("Resource name: " + r);
		
		HTTPLog tempLog = new HTTPLog(r);
		try {
			tempLog = (HTTPLog)(hashtable.find(tempLog));
			
			System.out.println("Status Code: " + tempLog.getStatusCode());
			System.out.println("Date and Time: " + tempLog.getDate());
			System.out.println("Total Bytes: " + tempLog.getTotalBytes());
			System.out.println("Times Accessed: " + tempLog.getCounter());
		}
		catch(RuntimeException e) {
			System.out.println("Resource name not recognized.");
			errors.println("Resource name not recognized.");
		}
	}
	
	/**
	 * The topTenServed method is called whenever this command is entered.
	 * @param hashtable
	 * @param errors
	 */
	
	public static void topTenServed(LinkedChainedHashtable hashtable, PrintWriter errors) {
		
		/**
		 * getElements is used to return an array of objects.
		 * This is then casted to an array of HTTPLogs.
		 */
		
		Object[] temp1 = hashtable.getElements();
		HTTPLog[] temp = new HTTPLog[temp1.length];
		for (int k = 0; k < temp1.length; k++) {
			temp[k] = (HTTPLog)(temp1[k]);
		}
		
		/**
		 * An array of integers is created and populated with the number of times that each resource was accessed.
		 */
		
		int[] access = new int[temp.length];
		for(int i = 0; i < temp.length; i++) {
			access[i] = temp[i].getCounter();
		}
		
		/**
		 * This array is then passed to maxInRange 10 times.
		 * This method returns the index of the largest value.
		 * This index is then used to retrieve the resource from the original array.
		 * This index in the integer array is then changed to 0 and the process is repeated.
		 */
		
		for (int num = 0; num < 10; num++) {
			int index = maxInRange(access);
			System.out.println("#" + (num + 1) + "\nResource name: " + temp[index].getBase());
			System.out.println("Times Accessed: " + temp[index].getCounter());
			access[index] = 0;
		}

	}
	
	/**
	 * The topTenSize method is called whenever this command is entered.
	 * @param hashtable
	 * @param errors
	 */
	
	public static void topTenSize(LinkedChainedHashtable hashtable, PrintWriter errors) {
		
		/**
		 * getElements is used to return an array of objects.
		 * This is then casted to an array of HTTPLogs.
		 */
		
		Object[] temp1 = hashtable.getElements();
		HTTPLog[] temp = new HTTPLog[temp1.length];
		for (int k = 0; k < temp1.length; k++) {
			temp[k] = (HTTPLog)(temp1[k]);
		}
		
		/**
		 * An array of longs is created and populated with the totalBytes of each resource.
		 */
		
		long[] access = new long[temp.length];
		for(int i = 0; i < temp.length; i++) {
			access[i] = temp[i].getTotalBytes();
		}
		
		/**
		 * This array is then passed to maxInRange 10 times.
		 * This method returns the index of the largest value.
		 * This index is then used to retrieve the resource from the original array.
		 * This index in the long array is then changed to 0 and the process is repeated.
		 */
		
		for (int num = 0; num < 10; num++) {
			int index = maxInRange(access);
			System.out.println("#" + (num + 1) + "\nResource name: " + temp[index].getBase());
			System.out.println("Total Bytes: " + temp[index].getTotalBytes());
			access[index] = 0;
		}
	}
	
	/**
	 * The chain method is called whenever this command is entered.
	 * @param hashtable
	 * @param errors
	 */
	
	public static void chain(LinkedChainedHashtable hashtable, PrintWriter errors) {
		/**
		 * The longestBucket method is used to return the index of the bucket.
		 */
		
		System.out.println("Bucket Number: " + hashtable.longestBucket());
		
		/**
		 * The size of the bucket along with each element in it are printed.
		 */
		
		LinkedList<HTTPLog> temp = hashtable.longestList();
		System.out.println("Length: " + temp.size());
		System.out.println("Resource Names:");
		for (int i = 0; i < temp.size(); i++) {
			System.out.println(temp.get(i).getBase());
		}
	}
	
	/**
	 * maxInRange takes an array of integers and returns the index of the largest value.
	 * @param ints
	 * @return the index of the largest value.
	 */

	public static int maxInRange(int[] ints) {
		int index = 0;
		int counter = 0;
		
		/**
		 * The counter is updated in this loop each time a larger value is found.
		 * The index is then updated to wherever this value exists.
		 */
		
		for (int i = 0; i < ints.length; i++) {
			if (ints[i] > counter) {
				counter = ints[i];
				index = i;
			}
		}

		return index;
	}
	
	/**
	 * maxInRange takes an array of longs and returns the index of the largest value.
	 * @param ints
	 * @return the index of the largest value.
	 */

	public static int maxInRange(long[] ints) {
		int index = 0;
		long counter = 0;
		
		/**
		 * The counter is updated in this loop each time a larger value is found.
		 * The index is then updated to wherever this value exists.
		 */
		
		for (int i = 0; i < ints.length; i++) {
			if (ints[i] > counter) {
				counter = ints[i];
				index = i;
			}
		}

		return index;
	}
}