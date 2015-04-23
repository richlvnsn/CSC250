/**
 * Assignment: Project 2 - GoldMember.java
 * Due Date: 10/23/2014
 * Instructor: Dr. DePasquale
 * Submitted by Richard Levenson and Jeremy Leon
 */

/**
* Creates a gold member object that stores data
* that only gold members have: credit card data and UPS
* tracking code. Is inhereited from the Member class. Uses
* the CreditCard class to store ctedit card data.
*
* @author Richard Levenson
* @author Jeremy Leon
*/
public class GoldMember extends Member
{
	/**
	* A credit card object to store credit card data
	*/
	private CreditCard creditCardInfo;

	/**
	* UPS tracking code stored as string
	*/
	private String UPSTrackingCode;

	/**
	* Creates a gold member object with data from the member object that
	* is processed by the member constructor, along with the gold member's
	* credit card data and UPS tracking code.
	* 
	* @param 	nationalID 	The person's national ID number.
	* @param 	givenName 	The person's first name.
	* @param 	middleInitial 	The person's middle initial.
	* @param 	surname 	The person's last name.
	* @param 	gender 	The person's gender.
	* @param 	address 	The address information of the person held in an Address object.
	* @param 	info 	Other information about the member held in a MemberInfo object.
	* @param  	creditCardInfo 	Credit card info
	* @param  	UPSTrackingCode 	UPS tracking code
	*/
	public GoldMember(long nationalID, String givenName, char middleInitial, String surname, String gender, Address address, MemberInfo info, CreditCard creditCardInfo, String UPSTrackingCode)
	{
		super(nationalID, givenName, middleInitial, surname, gender, address, info);
		this.creditCardInfo = creditCardInfo;
		this.UPSTrackingCode = UPSTrackingCode;
	}

	/**
	* Returns all gold member information as a string, partly using
	* member's to string
	* 
	* @return all info as string
	*/
	public String toString()
	{
		return (super.toString() + creditCardInfo + "\t" + UPSTrackingCode);
	}
}