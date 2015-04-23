/**
 * Assignment: Project 2 - Person.java
 * Due Date: 10/23/2014
 * Instructor: Dr. DePasquale
 * Submitted by Richard Levenson and Jeremy Leon
 */

/**
 * The Person class encapsulates the represenation of a person.  The class contains data for the person's ID number, 
 * first name, middle initial, last name, gender, and an object that contains Address information.
 * The class is abstract because its purpose is for child classes to inherit its data and methods, and no Person object needs to be instantiated.
 * @author Richard Levenson
 * @author Jeremy Leon
 */
abstract public class Person
{
	/**
	 * The person's ID number.
	 */
	protected long nationalID;

	/** 
	 * The person's first name.
	 */
	protected String givenName;

	/**
	 * The person's middle initial.
	 */
	protected char middleInitial;

	/**
	 * The person's last name.
	 */
	protected String surname;

	/**
	 * The person's gender.
	 */
	protected String gender;

	/**
	 * The person's address held in an Address object.
	 */
	protected Address address;

	/**
	 * Constructor sets up a Person object from the provided data.
	 * @param 	nationalID 	The person's national ID number.
	 * @param 	givenName 	The person's first name.
	 * @param 	middleInitial 	The person's middle initial.
	 * @param 	surname 	The person's last name.
	 * @param 	gender 	The person's gender.
	 * @param 	address 	The address information of the person held in an Address object.
	 */
	public Person(long nationalID, String givenName, char middleInitial, String surname, String gender, Address address)
	{
		this.nationalID = nationalID;
		this.givenName = givenName;
		this.middleInitial = middleInitial;
		this.surname = surname;
		this.gender = gender;
		this.address = address;
	}

	/**
	 * Used in the compareTo method in the Member class to sort Members by last name.
	 * @return the person's last name.
	 */
	public String getSurname()
	{
		return surname;
	}

	/**
	 * Used in the compareTo method in the Member class to sort Members by state.
	 * @return the person's state.
	 */
	public String getState()
	{
		return address.getState();
	}

	/**
	 * Used in the compareTo method in the Member class to sort Members by street.
	 * @return the person's street address.
	 */
	public String getStreet()
	{
		return address.getStreet();
	}

	/**
	 * @return a string represenation of the person.
	 */
	public String toString()
	{
		return (gender + "\t" + givenName + "\t" + middleInitial + "\t" + surname + "\t" + address + "\t");
	}
}