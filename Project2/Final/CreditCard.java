/**
 * Assignment: Project 2 - CreditCard.java
 * Due Date: 10/23/2014
 * Instructor: Dr. DePasquale
 * Submitted by Richard Levenson and Jeremy Leon
 */

import java.text.DecimalFormat;
/**
* Creates a credit card object that stores credit card type,
* credit card number, ccv2 code, and expiration date. Is aggregrated
* by goldmember object.
*
* @author Richard Levenson
* @author Jeremy Leon
*/
public class CreditCard
{
	/**
	* The string name of the credit card type and expiration date	
	*/
	private String ccType, ccExpiry;

	/**
	* The credit card number
	*/
	private long ccNum;

	/**
	* The credit card verification number.
	*/
	private short ccv2Code;

	/**
	 * Decimal formatter to format ccv2 codes to show all 3 digits, regardless of zeroes
	 */
	private DecimalFormat fmt = new DecimalFormat("000");

	/**
	* Creates the credit card object wtih data representing all the
	* credit card information
	* 
	* @param ccType Credit card type
	* @param ccNum Credit card number
	* @param ccv2Code Card verification number
	* @param ccExpiry Card expiration date
	*/
	public CreditCard(String ccType, long ccNum, short ccv2Code, String ccExpiry)
	{
		this.ccType = ccType;
		this.ccNum = ccNum;
		this.ccv2Code = ccv2Code;
		this.ccExpiry = ccExpiry;
	}

	/**
	* Returns all credit card information as a string
	* 
	* @return all info as string
	*/
	public String toString()
	{
		return (ccType + "\t" + ccNum + "\t" + fmt.format(ccv2Code) + "\t" + ccExpiry);
	}
}