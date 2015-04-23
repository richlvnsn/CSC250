/**
 * Assignment: Project 1
 * Due date: 9/29/2014
 * Instructor: Dr. DePasquale
 * Submitted by: Richard Levenson and MaxWell Pollack
 */

import java.util.Scanner;
import java.text.NumberFormat;
import java.math.RoundingMode;

/**
 * The Calc class represents a calculator.
 * The class contains a NumberFormat objects that handles the precision of the output
 * and an integer that keeps track of the current precision that is set.  It also
 * contains various methods that represent calculator commands.  The methods generally
 * print an output to the screen that mimics the original input from an input file handled
 * by the main class and also includes the calculated answer for the command.
 * @author Richard Levenson
 * @author Maxwell Pollack
 */
public class Calc
{
	/**
	 * The NumberFormat object that handles the number of decimal places that
	 * the output of other methods will be rounded to.
	 */
	private NumberFormat precision = NumberFormat.getInstance();

	/**
	 * The current precision of the calculator's output.
	 */
	private int currentPrecision;

	/**
	 * Creates a Calc object with no initial parameters.  Sets the default precision to 3.
	 */
	public Calc()
	{
		currentPrecision = 3;
		precision.setMaximumFractionDigits(currentPrecision);
		precision.setMinimumFractionDigits(currentPrecision);
	}

	/**
	 * Sets the precision of the outputs of the calculator.
	 * @param 	numPrecision 	The precision that the calculator will use.
	 */
	public void setPrecision(int numPrecision)
	{
		currentPrecision = numPrecision;
		precision.setMaximumFractionDigits(numPrecision);
		precision.setMinimumFractionDigits(numPrecision);
		//Sets the format to truncate instead of round if precision is set to 0.
		if(numPrecision == 0)
			precision.setRoundingMode(RoundingMode.FLOOR);
		else
			precision.setRoundingMode(RoundingMode.HALF_EVEN);
	}

	/**
	 * Adds two numbers and outputs the original command, input, and the sum.
	 * @param 	num1 	The first addend.
	 * @param 	num2 	The second addend.
	 */
	public void plus(double num1, double num2)
	{
		System.out.println("+ " + num1 + " " + num2 + " = " + precision.format(num1 + num2));
	}

	/**
	 * Subtracts one number from another and outputs the original command, input, and the difference.
	 * @param 	num1 	The minuend.
	 * @param 	num2 	The subtrahend.
	 */
	public void minus(double num1, double num2)
	{
		System.out.println("- " + num1 + " " + num2 + " = " + precision.format(num1 - num2));
	}

	/**
	 * Multiplies two numbers and outputs the original command, input, and the product.
	 * @param 	num1 	The first factor.
	 * @param 	num2 	The second factor.
	 */
	public void times(double num1, double num2)
	{
		System.out.println("* " + num1 + " " + num2 + " = " + precision.format(num1 * num2));
	}

	/**
	 * Divides one number from another and outputs the original command, input, and the quotient.
	 * @param 	num1 	The dividend.
	 * @param 	num2 	The divisor.
	 */
	public void div(double num1, double num2)
	{
		System.out.println("/ " + num1 + " " + num2 + " = " + precision.format(num1 / num2));
	}

	/**
	 * Divides one number from another and outputs the original command, input, and the remainder after the division.
	 * @param 	num1 	The dividend.
	 * @param 	num2 	The divisor.
	 */
	public void mod(int num1, int num2)
	{
		System.out.println("% " + num1 + " " + num2 + " = " + (num1 % num2));
	}

	/**
	 * Raises a base to the power of three and outputs the original command, input, and the calculated answer.
	 * @param 	num1 	The base.
	 */
	public void cube(double num1)
	{
		System.out.println("cube " + num1 + " = " + precision.format(num1 * num1 * num1));
	}

	/**
	 * Converts an angle from degrees to radians, calculates the sine of that angle and outputs the original command, input, and the calculated answer.
	 * @param 	angle 	An angle given in degrees.
	 */
	public void sin(double angle)
	{
		System.out.println("sin " + angle + " = " + precision.format(Math.sin((angle*Math.PI)/180)));
	}

	/**
	 * Converts an angle from degrees to radians, calculates the cosine of that angle and outputs the original command, input, and the calculated answer.
	 * @param 	angle 	An angle given in degrees.
	 */
	public void cos(double angle)
	{
		System.out.println("cos " + angle + " = " + precision.format(Math.cos((angle*Math.PI)/180)));
	}

	/**
	 * Converts an angle from degrees to radians, calculates the tangent of that angle and outputs the original command, input, and the calculated answer.
	 * @param 	angle 	An angle given in degrees.
	 */
	public void tan(double angle)
	{
		System.out.println("tan " + angle + " = " + precision.format(Math.tan((angle*Math.PI)/180)));
	}
	
	/**
	 * Calculates the square root of a number and outputs the original command, input, and the calculated answer.
	 * @param 	num1 	The number for which the square root is being found.
	 */
	public void sqrt(double num1)
	{
		System.out.println("sqrt " + num1 + " = " + precision.format(Math.sqrt(num1)));
	}
	
	/**
	 * Raises a base to an exponent and outputs the original command, input, and the remainder after the division.
	 * @param 	num1 	The base.
	 * @param 	num2 	The exponent.
	 */
	public void pow(double num1, double num2)
	{
		System.out.println("pow " + num1 + " " + num2 + " = " + precision.format(Math.pow(num1, num2)));
	}

	/**
	 * Prints a double number rounded to the nearest integer.  Utilizes the RoundingMode class.
	 * Modifies the precision of the calculator before the output and then converts the precision
	 * and rounding back to what it was before the method call.
	 * @param 	num1 	The double number that is being rounded.
	 */
	public void round(double num1)
	{
		//Sets the number of decimal points to 0, causing the output to resemble an integer.
		precision.setMaximumFractionDigits(0);
		precision.setMinimumFractionDigits(0);
		//Sets the rounding to round to the nearest whole number.
		precision.setRoundingMode(RoundingMode.HALF_EVEN);
		System.out.println("round " + num1 + " = " + precision.format(num1));
		//Sets the number of decimal points back to what was last set for the calculator
		precision.setMaximumFractionDigits(currentPrecision);
		precision.setMinimumFractionDigits(currentPrecision);
		//If the precision was set to zero, sets the rounding mode to truncate.  Otherwise, sets rounding to the nearest whole number.
		if(currentPrecision == 0)
			precision.setRoundingMode(RoundingMode.FLOOR);
		else
			precision.setRoundingMode(RoundingMode.HALF_EVEN);
	}

	/**
	 * Raises Euler's constant to an exponent and outputs the original command, input, and the remainder after the division.
	 * @param 	num1 	The exponent.
	 */
	public void exp(double num1)
	{
		System.out.println("exp " + num1 + " = " + precision.format(Math.pow(Math.E, num1)));
	}

	/**
	 * Calculates the cube root of a number and outputs the original command, input, and the cube root.
	 * @param 	num1 	The number for which the cube root is being found.
	 */
	public void cuberoot(double num1)
	{
		System.out.println("cuberoot " + num1 + " = " + precision.format(Math.cbrt(num1)));
	}

	/**
	 * Calculates the factorial of a number and outputs the original command, input, and the calculated answer.
	 * @param 	num1 	The number for which the factorial is being calculated.
	 */
	public void factorial(int num1)
	{
		/**
		 * The answer being returned.  The long type is used because the answer will likely be a very large integer value.
		 */
		long ans = num1;
		//Handles the calculation of the factorial, which changes depending on num1.
		for(int count = 1; count < num1; count++)
		{
			ans = ans * (num1 - count);
		}
		System.out.println("n! " + num1 + " = " + ans);
	}

	/**
	 * Finds the minimum number of a specified amount of numbers contained in an input file.  Outputs the original
	 * command, input, and the minimum.
	 * @param 	number 	The amount of numbers that will be examined.
	 * @param 	minScan 	The Scanner object that scans the list of numbers from an input file.
	 */
	public void min(int number, Scanner minScan)
	{
		/**
		 * Reads the first value and sets it as a candidate for the minimum value.  
		 */
		int minCandidate = minScan.nextInt();

		/**
		 * The minimum value initialized as the first minimum candidate.
		 */
		int minimum = minCandidate;

		/**
		 * A String of all candidates for mimimum.  Used to copy the original input of the file so that it can be used in the output.
		 */
		String allCandidates = " " + minCandidate + " ";
		//For loop runs for the specified number of terms
		for(int count = 1; count < number; count++)
		{
			//Reads the next value and sets it as a candidate for minimum.
			minCandidate = minScan.nextInt();
			//If the candidate is less than the current minimum, assigns it as the new minimum.
			if (minCandidate < minimum)
				minimum = minCandidate;
			//Adds the new candidate to the list of all candidates
			allCandidates = allCandidates + minCandidate + " ";
		}
		System.out.println("min " + number + allCandidates + "= " + minimum);
	}

	/**
	 * Finds the maximum number of a specified amount of numbers contained in an input file.  Outputs the original
	 * command, input, and the maximum.
	 * @param 	number 	The amount of numbers that will be examined.
	 * @param 	minScan 	The Scanner object that scans the list of numbers from an input file.
	 */
	public void max(int number, Scanner maxScan)
	{
		/**
		 * Reads the first value and sets it as a candidate for the maximum value.  
		 */
		int maxCandidate = maxScan.nextInt();

		/**
		 * The maximum value initialized as the first minimum candidate.
		 */
		int maximum = maxCandidate;

		/**
		 * A String of all candidates for maximum.  Used to copy the original input of the file so that it can be used in the output.
		 */
		String allCandidates = " " + maxCandidate + " ";
		//For loop runs for the specified number of terms
		for(int count = 1; count < number; count++)
		{
			//Reads the next value and sets it as a candidate for maximum.
			maxCandidate = maxScan.nextInt();
			//If the candidate is greater than the current maximum, assigns it as the new maximum.
			if (maxCandidate > maximum)
				maximum = maxCandidate;
			//Adds the new candidate to the list of all candidates
			allCandidates = allCandidates + maxCandidate + " ";
		}
		System.out.println("max " + number + allCandidates + "= " + maximum);
	}
}