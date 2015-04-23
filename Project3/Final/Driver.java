/**
 * Assignment: Project 3 - Driver.java
 * Due Date: 11/19/2014
 * Instructor: Dr. DePasquale
 * Submitted by Richard Levenson and Hunter Dubel
 */

/**
 * The Driver class simply controls the start up of the program at a very base level so that there is not much interference with the physical classes.
 * Driver starts by creating a new Simulator object called intersection. It then calls the simulate method from that object.
 * @author Richard Levenson
 * @author Hunter Dubel
 */

public class Driver
{
	public static void main(String[] args)
	{
		Simulator intersection = new Simulator();
		intersection.simulate();
	}
}