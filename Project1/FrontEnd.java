/**
 * Assignment: Project 1
 * Due date: 9/29/2014
 * Instructor: Dr. DePasquale
 * Submitted by: Richard Levenson and Maxwell Pollack
 */

import java.util.Scanner;
import java.io.*;

/**
 * The FrontEnd class drives the Calc class.
 * The class contains a main method and a sendCommand method which work together to read input from a file and send
 * commands to a Calc object.
 * @author Richard Levenson
 * @author Maxwell Pollack
 */
public class FrontEnd
{
	/**
	 * The main method initializes a Scanner object which reads from the calculator.txt file,
	 * an integer which holds the total number of commands given by the file, and a Calc Object which manages the
	 * calculations and output for each command.  It runs a loop that gives the Scanner and the calculator to the
	 * sendCommand method for the total number of commands.
	 * @throws 	IOException 	An exception that may be thrown if there is a problem finding or opening calculator.txt.
	 */
	public static void main(String[] args) throws IOException
	{
		/**
	 	 * The Scanner object which will read from the File object that represents calculator.txt.
	 	 */
		Scanner filescan = new Scanner(new File("calculator.txt"));

		/**
	 	 * The number of commands that will be run.  Read from the first line of the input file.
	 	 */
		int numCommands = filescan.nextInt();

		/**
	 	 * The Calc object which will handle the calculations and output for the commands that are passed to it.
	 	 */
		Calc calculator = new Calc();

		//For loop runs for the total number of commands specified by the input file.
		for (int count = 1; count <= numCommands; count++)
		{
			//Passes the Scanner object and the Calc object to the sendCommand method
			FrontEnd.sendCommand(filescan, calculator);
		}


	}

	/**
	 * Reads the first token of a line of the input file and attempts to match it with a command of the Calc class.
	 * If the token is a known command, the relevant method of the Calc class is called and the needed parameters
	 * are read from the input file.  If the token is not a known command, the rest of the line of the input file
	 * is read and stored in a garbage string which is garbage collected when the method is done running.
	 * @param 	scan 	The Scanner object that reads from the input file.
	 * @param 	textCalc 	The Calculator object that is sent commands.
	 */
	public static void sendCommand(Scanner scan, Calc textCalc)
	{
		/**
	 	 * A string that is later assigned the first token of a line.
	 	 */
		String command;

		/**
		 * Only allows the rest of the method to run if there is more information in the input file.
		 * Prevents an error if the specified number of lines is larger than the true number of lines.
		 */
		if (scan.hasNext())
		{
			/**
		 	 * Assigns the first token of the line to the command String.
		 	 */
			command = scan.next();

			/**
		 	 * Uses the compareTo method of the String class to check if the token read from the input
		 	 * file matches any of the commands known by the Calc class.  Calls the appropriate method
		 	 * and reads the appropriate number of tokens for each known command.
		 	 */
			if (command.compareTo("precision") == 0)
				textCalc.setPrecision(scan.nextInt());
			else if (command.compareTo("+") == 0)
				textCalc.plus(scan.nextDouble(), scan.nextDouble());
			else if (command.compareTo("-") == 0)
				textCalc.minus(scan.nextDouble(), scan.nextDouble());
			else if (command.compareTo("*") == 0)
				textCalc.times(scan.nextDouble(), scan.nextDouble());
			else if (command.compareTo("/") == 0)
				textCalc.div(scan.nextDouble(), scan.nextDouble());
			else if (command.compareTo("%") == 0)
				textCalc.mod(scan.nextInt(), scan.nextInt());
			else if (command.compareTo("cube") == 0)
				textCalc.cube(scan.nextDouble());
			else if (command.compareTo("sin") == 0)
				textCalc.sin(scan.nextDouble());
			else if (command.compareTo("cos") == 0)
				textCalc.cos(scan.nextDouble());
			else if (command.compareTo("tan") == 0)
				textCalc.tan(scan.nextDouble());
			else if (command.compareTo("sqrt") == 0)
				textCalc.sqrt(scan.nextDouble());
			else if (command.compareTo("round") == 0)
				textCalc.round(scan.nextDouble());
			else if (command.compareTo("pow") == 0)
				textCalc.pow(scan.nextDouble(), scan.nextDouble());
			else if (command.compareTo("exp") == 0)
				textCalc.exp(scan.nextDouble());
			else if (command.compareTo("cuberoot") == 0)
				textCalc.cuberoot(scan.nextDouble());
			else if (command.compareTo("n!") == 0)
				textCalc.factorial(scan.nextInt());
			else if (command.compareTo("min") == 0)
				textCalc.min(scan.nextInt(), scan);
			else if (command.compareTo("max") == 0)
				textCalc.max(scan.nextInt(), scan);
			//If the token does not match a known command, assign the rest of the line to the garbage String.
			else
				scan.nextLine();
		}
	}
}