/**
 * Assignment: Project 3 - Simulator.java
 * Due Date: 11/19/2014
 * Instructor: Dr. DePasquale
 * Submitted by Richard Levenson and Hunter Dubel
 */

import jsjf.*;
import jsjf.exceptions.*;
import java.text.DecimalFormat;
import java.io.*;

/**
 * The Simulator class encapsulates a simulator object which simulates the flow of traffic at an intersection.
 * The class contains a constant max number of vehicles to randomly be generated, a clock which keeps track of time,
 * a PrintWriter to write output to a file, a DecimalFormat object to format the time output, and six queues to represent
 * the six lanes that meet at the intersection.
 * @author Richard Levenson
 * @author Hunter Dubel
 */
public class Simulator
{
	/**
	 * The maximum number of vehicles to process in this simulation.
	 */
	private static final int maxVehicles = 120;
	/**
	 * The time that has passed during the simulation.
	 */
	private int clock;
	/**
	 * The six queues that represent the six lanes that meet at the intersection.  Vehicles are stored in the queues,
	 * representing vehicles in line at a traffic light.
	 */
	private LinkedQueue<Vehicle> eastToEast, eastToNorth, westToNorth, westToWest, southToEast, southToWest;
	/**
	 * Used to format the time output so that it lines up nicely.
	 */
	private DecimalFormat clockFmt = new DecimalFormat("000");
	/**
	 * Used to write output to an external file.
	 */
	private PrintWriter writer;

	/**
	 * Constructor which creates a Simulator object.  Initializes the clock at 0 and creates the six queues.  Uses a try-catch to prevent
	 * the program from crashing if there is a problem loading the output file.
	 */
	public Simulator()
	{
		clock = 0; //Time starts at 0
		southToEast = new LinkedQueue<Vehicle>();
		southToWest = new LinkedQueue<Vehicle>();
		eastToEast = new LinkedQueue<Vehicle>();
		eastToNorth = new LinkedQueue<Vehicle>();
		westToWest = new LinkedQueue<Vehicle>();
		westToNorth = new LinkedQueue<Vehicle>();
		try
		{
			writer = new PrintWriter(new File("output.txt"));
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Problem finding file.");
		}
	}

	/**
	 * Runs the actual simulation.  Prints output to the external file, calls process() to randomly create vehicles, and calls move methods to move vehicles through the intersection.
	 */
	public void simulate()
	{
		writer.println("---Start of simulation, time set to 0.");
		process(7, 6);
		/**
		 * Loops until all 120 vehicles are created and all queues are emptied.
		 */
		do
		{
			writer.println("\n---Light changed. Now processing southbound traffic---");
			clock += 3;
			moveChurch();
			clock += 3;
			moveChurch();
			process(8, 8);
			writer.println("\n---Light changed. Now processing east/west-bound traffic---");
			clock +=3;
			moveMain();
			clock += 3;
			moveMain();
			clock +=3;
			moveMain();
			process(3,13);
		}while(Vehicle.getCount() < maxVehicles || eastToEast.isEmpty() == false || eastToNorth.isEmpty() == false || westToNorth.isEmpty() == false || westToWest.isEmpty() == false || southToEast.isEmpty() == false || southToWest.isEmpty() == false);
		writer.close();
	}

	/**
	 * Simulates the movement of one set of cars through the intersection when the light on Church Street turns green.  This takes 3 seconds (handled by simulate()).
	 */
	private void moveChurch()
	{
		Vehicle temp;
		/**
		 * If statements avoid calling dequeue() on an empty queue.
		 */
		if (southToEast.isEmpty() == false)
		{
			temp = southToEast.dequeue();
			temp.setDeparture(clock - temp.getArrival());
			writer.println("[Time " + clockFmt.format(clock) + "] " + temp + " turned right and headed westbound.  Total wait time " + temp.getDeparture() + " seconds.");
		}
		if (southToWest.isEmpty() == false)
		{
			temp = southToWest.dequeue();
			temp.setDeparture(clock - temp.getArrival());
			writer.println("[Time " + clockFmt.format(clock) + "] " + temp + " turned left and headed eastbound.  Total wait time " + temp.getDeparture() + " seconds.");
		}
	}

	/**
	 * Simulates the movement of one set of cars through the intersection when the light on Main Street turns green.  This takes 3 seconds (handled by simulate()).
	 */
	private void moveMain()
	{
		Vehicle temp;
		/**
		 * If statements avoid calling dequeue() on an empty queue.
		 */
		if (eastToEast.isEmpty() == false)
		{
			temp = eastToEast.dequeue();
			temp.setDeparture(clock - temp.getArrival());
			writer.println("[Time " + clockFmt.format(clock) + "] " + temp + " continued eastbound.  Total wait time " + temp.getDeparture() + " seconds.");
		}
		if (eastToNorth.isEmpty() == false)
		{
			temp = eastToNorth.dequeue();
			temp.setDeparture(clock - temp.getArrival());
			writer.println("[Time " + clockFmt.format(clock) + "] " + temp + " turned left and headed northbound.  Total wait time " + temp.getDeparture() + " seconds.");
		}
		if (westToWest.isEmpty() == false)
		{
			temp = westToWest.dequeue();
			temp.setDeparture(clock - temp.getArrival());
			writer.println("[Time " + clockFmt.format(clock) + "] " + temp + " continued westbound.  Total wait time " + temp.getDeparture() + " seconds.");
		}
		if (westToNorth.isEmpty() == false)
		{
			temp = westToNorth.dequeue();
			temp.setDeparture(clock - temp.getArrival());
			writer.println("[Time " + clockFmt.format(clock) + "] " + temp + " turned right and headed northbound.  Total wait time " + temp.getDeparture() + " seconds.");
		}
	}

	/**
	 * Handles the creation of a random number of vehicles within a range as determined by the provided data.
	 * @param 	initial 	the smallest number vehicles to create
	 * @param 	range 	the number of possible additional vehicles to add
	 */
	private void process(int initial, int range)
	{
		for (int i = 0; i<(initial + (range*Math.random())); i++)
		{
			if(Vehicle.getCount() < maxVehicles)
				createVehicle();
		}
	}

	/**
	 * Randomly generates a number from 0 to 5.  Creates a vehicle and assigns it to a lane based on the randomly generated number.
	 */
	private void createVehicle()
	{
		Vehicle temp;
		Street tempStreet;
		Direction tempDirection;
		int randomLane = (int)(6*Math.random());
		if (randomLane == 0) //Church street and turning East
		{
			tempStreet = Street.Church;
			tempDirection = Direction.S;
			temp = new Vehicle(tempStreet, tempDirection, clock);
			southToEast.enqueue(temp);
		}
		else if (randomLane == 1) //Church street and turning West
		{
			tempStreet = Street.Church;
			tempDirection = Direction.S;
			temp = new Vehicle(tempStreet, tempDirection, clock);
			southToWest.enqueue(temp);
		}
		else if (randomLane == 2) //Main street, heading East, continuing East
		{
			tempStreet = Street.Main;
			tempDirection = Direction.E;
			temp = new Vehicle(tempStreet, tempDirection, clock);
			eastToEast.enqueue(temp);
		}
		else if (randomLane == 3) //Main street, heading East, turning North
		{
			tempStreet = Street.Main;
			tempDirection = Direction.E;
			temp = new Vehicle(tempStreet, tempDirection, clock);
			eastToNorth.enqueue(temp);
		}
		else if (randomLane == 4) //Main street, heading West, continuing West
		{
			tempStreet = Street.Main;
			tempDirection = Direction.W;
			temp = new Vehicle(tempStreet, tempDirection, clock);
			westToWest.enqueue(temp);
		}
		else if (randomLane == 5) //Main street, heading West, turning North
		{
			tempStreet = Street.Main;
			tempDirection = Direction.W;
			temp = new Vehicle(tempStreet, tempDirection, clock);
			westToNorth.enqueue(temp);
		}
	}

}