/**
 * Assignment: Project 2 - Address.java
 * Due Date: 10/23/2014
 * Instructor: Dr. DePasquale
 * Submitted by Richard Levenson and Jeremy Leon
 */

/**
* Creates an Address object that stores address data including
* street name, city name, state name, and ZIP code.
*
* @author Richard Levenson
* @author Jeremy Leon
*/

import java.text.DecimalFormat;

public class Address
{
	/**
	* String representation of street, city, and state name.
	*/
	private String street, city, state;
	
	/**
	* ZIP code stored as an int
	*/
	private int zipCode;

	/**
	* Decimal formatter to format ZIP codes to show all 5 digits, regardless of zeroes
	*/
	private DecimalFormat fmt = new DecimalFormat("00000");

	/**
	* Creates Address object that stores street name, city name, state abbreviation
	* and ZIP code
	* 
	* @param street Street name
	* @param city City name
	* @param state State abbreviatioon
	* @param zipcode ZIP code
	*/
	public Address(String street, String city, String state, int zipCode)
	{
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}

	/**
	* Gets street name and returns it as a string
	* 
	* @return street name
	*/
	public String getStreet()
	{
		return street;
	}

	/**
	* Gets state abbreviation and returns it as a string
	* 
	* @return state abbreviation
	*/
	public String getState()
	{
		return state;
	}

	/**
	* Returns all Address information as one string
	* 
	* @return all info as string
	*/
	public String toString()
	{
		return(street + "\t" + city + "\t" + state + "\t" + fmt.format(zipCode));
	}
}