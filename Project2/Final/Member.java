/**
 * Assignment: Project 2 - Member.java
 * Due Date: 10/23/2014
 * Instructor: Dr. DePasquale
 * Submitted by Richard Levenson and Jeremy Leon
 */

import java.util.*;
import java.text.DecimalFormat;

/**
 * The Member class extends the Person class to represent a gym social network member whose data is sorted by our program.
 * The class extends Comparable which forces it to implement a compareTo method which helps with the sorting of member data.
 * The class contains a MemberInfo object which holds additional information about the member and two DecimalFormat objects along with six Strings that help with formatting of telephone numbers and ID numbers in the toString.
 * @author Richard Levenson
 * @author Jeremy Leon
 */
public class Member extends Person implements Comparable<Member>
{
	/**
	 * Aggregated object that contains more information about the member.
	 */
	protected MemberInfo info;

	/**
	 * Formats the nationalID variable before converting to a string to ensure that leading zeros are not cut off.
	 */
	private static DecimalFormat idFormat = new DecimalFormat("000000000");

	/**
	 * Formats the telephone variable before converting to a string to ensure that leading zeros are not cut off.
	 */
	private static DecimalFormat teleFormat = new DecimalFormat("0000000000");

	/**
	 * Strings that hold substrings of the telephone and nationalID string represenations for formatting purposes.
	 */
	protected String tele1, tele2, tele3, id1, id2, id3;

	/**
	 * Constructor sets up a Member object by first calling the Person constructor through the super reference and then and then assigning the MemberInfo object to instance data.
	 * @param 	nationalID 	The person's national ID number.
	 * @param 	givenName 	The person's first name.
	 * @param 	middleInitial 	The person's middle initial.
	 * @param 	surname 	The person's last name.
	 * @param 	gender 	The person's gender.
	 * @param 	address 	The address information of the person held in an Address object.
	 * @param 	info 	Other information about the member held in a MemberInfo object.
	 */
	public Member(long nationalID, String givenName, char middleInitial, String surname, String gender, Address address, MemberInfo info)
	{
		super(nationalID, givenName, middleInitial, surname, gender, address);
		this.info = info;
	}


	/**
	 * Receives the telephone variable from the MemberInfo object, converts it into a string, and inserts "-" characters.
	 * @return the formatted telephone number
	 */
	private String formatTelephone()
	{
		String teleString = "" + teleFormat.format(info.getTelephone());
		/**
		 * TeleString is split into three substrings so that "-" characters can be inserted.
		 */
		tele1 = teleString.substring(0,3);
		tele2 = teleString.substring(3,6);
		tele3 = teleString.substring(6,10);
		teleString = tele1 + "-" + tele2 + "-" + tele3;
		return teleString;
	}

	/**
	 * Converts the nationalID long into a string and inserts "-" characters.
	 * @return 	the formatted ID number.
	 */
	private String formatID()
	{
		String idString = "" + idFormat.format(nationalID);
		/**
		 * idString is split into three substrings so that "-" characters can be inserted.
		 */
		id1 = idString.substring(0,3);
		id2 = idString.substring(3,5);
		id3 = idString.substring(5,9);
		idString = id1 + "-" + id2 + "-" + id3;
		return idString;
	}

	/**
	 * Compares Member objects to allow sorting first by last name ascending, second by state descending, and third by street descending.
	 * @param 	compare 	The object to compare this object to.
	 * @return 	the sorting value
	 */
	public int compareTo(Member compare)
	{
	 		int surnameSort = surname.compareTo(compare.surname);
	 		if (surnameSort != 0)
	 			return surnameSort;
	 		int stateSort = compare.getState().compareTo(getState());
	 		if (stateSort != 0)
	 			return stateSort;
	 		int streetSort = compare.getStreet().compareTo(getStreet());
	 		return streetSort;
		   
	}

	/**
	 * @return a string represenation of the member.
	 */
	public String toString()
	{
		String teleString = this.formatTelephone();
		String idString = this.formatID();
		return (super.toString() + info.getEmail() + "\t" + teleString + "\t" + idString + "\t" + info.getBirthday() + "\t");
	}
}