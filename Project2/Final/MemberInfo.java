/**
 * Assignment: Project 2 - MemberInfo.java
 * Due Date: 10/23/2014
 * Instructor: Dr. DePasquale
 * Submitted by Richard Levenson and Jeremy Leon
 */

/**
* Creates a MemberInfo object that stores member's
* email address, telephone, and birthday date.
*
* @author Richard Levenson
* @author Jeremy Leon
*/
public class MemberInfo
{
	/**
	* String representation of email adress and birthday date
	*/
	private String emailAddress, birthday;

	/**
	* Telephone number as a long
	*/
	private long telephone;

	/**
	* Creates the MemberInfo object wtih data representing email
	* birthday and telephoen number
	* 
	* @param emailAddress Member email address
	* @param telephone Telephone number
	* @param birthday Birthday date
	*/
	public MemberInfo(String emailAddress, long telephone, String birthday)
	{
		this.emailAddress = emailAddress;
		this.telephone = telephone;
		this.birthday = birthday;
	}

	/**
	* Gets email adresss and returns it as string.
	* 
	* @return email address
	*/
	public String getEmail()
	{
		return emailAddress;
	}

	/**
	* Gets telephone number and returns it as long
	* 
	* @return phone number
	*/
	public long getTelephone()
	{
		return telephone;
	}

	/**
	* Gets birthday date and returns it as string
	* 
	* @return birthday date
	*/
	public String getBirthday()
	{
		return birthday;
	}
}