/**
 * Assignment: Project 2 - Processor.java
 * Due Date: 10/23/2014
 * Instructor: Dr. DePasquale
 * Submitted by Richard Levenson and Jeremy Leon
 */

import java.util.*;
import java.io.*;
import java.net.*;

/**
 * The Processor class encapsulates a processor object which is responsible for scanning data from a file, 
 * managing that data in a polymorphic array, sorting the data, and outputting the data to an output file.
 * The class contains a polymorphic array of Member reference variables which hold both Member and GoldMember objects, a Scanner object, and a String.
 * @author 	Richard Levenson
 * @author 	Jeremy Leon
 */
public class Processor
{
	/**
	 * The polymorphic Member array that holds the data to be processed.
	 */
	private Member[] data;

	/**
	 * The scanner that manages input from the file hosted online.
	 */
	private Scanner urlScan;

	/**
	 * The web address of the file that is processed by this processor.
	 */
	private String url;

	/**
	 * The first line of the input and output.  Contains the column titles for the data.
	 */
	private String columnTitles;

	/**
	 * The constructor which creates a processor object from the provided data.
	 * @param 	url 	The string representation of the web address of the file.
	 */
	public Processor (String url)
	{
		this.url = url;
		data = new Member[40000];
	}
	
	/**
	 * Uses the Scanner to read input from the file online and assigns each piece of data to a local variable.
	 * Then, passes the local variables in the correct order to a constructor for either a Member or GoldMember, depending on whether credit card information appears on the line.
	 * The Member or GoldMember object is created and stored in the polymorphic array.
	 * The previous steps are repeated for the specified number of objects hardcoded into the program.
	 * After all objects are added to the array, the array is sorted.
	 * @throws 	IOException 	An exception that may be thrown if there is a problem finding or opening the input stream of the Scanner.
	 * @throws 	MalformedURLException 	An exception that may be thrown if there is a problem when trying to access the web address of the file.
	 */
	public void scanAssignCreate() throws MalformedURLException, IOException
	{
		/**
		 * Creates the Scanner that is used to read input from the online file.
		 */
		urlScan = new Scanner(new BufferedReader(new InputStreamReader(new URL(url).openStream())));

		urlScan.next(); //Gets rid of the "number" column in the header
		columnTitles = urlScan.nextLine();

		/**
		 * Reads the file, assigns the read data to variables, and creates the appropriate object (Member or GoldMember) with the data.
		 */
		for (int i = 0; i<40000; i++)
		{
			int number = urlScan.nextInt();
			urlScan.useDelimiter("\t");
			String gender = urlScan.next();
			String givenName = urlScan.next();
			char middleInitial = urlScan.next().charAt(0);
			String surname = urlScan.next();
			String street = urlScan.next();
			String city = urlScan.next();
			String state = urlScan.next();
			int zip = urlScan.nextInt();
			String email = urlScan.next();


			String telephoneString = urlScan.next();
			telephoneString = telephoneString.replace("-", "");
			long telephone = Long.valueOf(telephoneString);

			String nationalIDString = urlScan.next();
			nationalIDString = nationalIDString.replace("-", "");
			long nationalID = Long.valueOf(nationalIDString);

			String birthday = urlScan.next();

			Address testAddress = new Address(street, city, state, zip);

			MemberInfo testInfo = new MemberInfo(email, telephone, birthday);

			/**
			 * Checks to see if the next token is the name of a credit card.  If it is, the Scanner continues to read the rest of the credit card information and then creates a GoldMember object.
			 * If the next token is not a credit card, a Member object is created.
			 */
			if(urlScan.hasNext("Visa") || urlScan.hasNext("MasterCard"))
			{
				String ccType = urlScan.next();
				long ccNumber = urlScan.nextLong();
				short cvv2 = urlScan.nextShort();
				String ccExpiry = urlScan.next();
				urlScan.useDelimiter("\n");
				String UPS = urlScan.next();
				urlScan.useDelimiter("\t");
				CreditCard testCard = new CreditCard(ccType, ccNumber, cvv2, ccExpiry);

				data[i] = new GoldMember(nationalID, givenName, middleInitial, surname, gender, testAddress, testInfo, testCard, UPS);
				if(urlScan.hasNext())
					urlScan.nextLine();
			} else
			{
				data[i] = new Member(nationalID, givenName, middleInitial, surname, gender, testAddress, testInfo);
				urlScan.nextLine();
			}
		}

		/**
		 * Sorts the polymorphic array of members based on the compareTo() method in the Member class.
		 */
		Arrays.sort(data);
	}

	/**
	 * For the specified number of objects hardcoded into the program, calls the toString of the Member or GoldMember object held in each index of the array
	 * and writes it to the "sortedData.txt" file.
	 * @throws 	IOException 	An exception that may be thrown if there is a problem finding or opening the "sortedData.txt" file.
	 */
	public void outputToFile() throws IOException
	{
		/**
		 * Creates a PrintWriter object which allows writing to an external file.
		 */
		PrintWriter writer = new PrintWriter(new File("sortedData.txt"));

		/**
		 * Writes to the external file.
		 */
		writer.println(columnTitles);
		for (int i = 0; i<40000; i++)
		{
			writer.println(data[i]);
		}
		writer.close();
	}

}