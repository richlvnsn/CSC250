/**
 * Assignment: Project 3 - Vehicle.java
 * Due Date: 11/19/2014
 * Instructor: Dr. DePasquale
 * Submitted by Richard Levenson and Hunter Dubel
 */

/**
 * The vehicle class controls the variables for each car such as how many there are total, each vehicles number, and their arrival and departure times.
 * @author Richard Levenson
 * @author Hunter Dubel
 */

public class Vehicle 
{
    /**
    * Initiates the number of cars to 0. Creates the other variables for the car class.
    */
    private static int count = 0;
    private int vehicleNumber, arrivalTime, departureTime;

    /**
    * Creates a variable str of type Street and a variable dir of type Direction
    */
    private Street str;
    private Direction dir;

    /**
    * The Vehicle class is a constructor that is called whenever a new car is created.
    *@param Street randomStreet The Simulator picks a random street of the enumerated type and gives it to the constructor.
    *@param Direction randomDirection The Simulator picks a random direction for the street and gives it to the constructor.
    *@param int arrival An integer with the value of time of the car's arrival at the intersection.
    */
    public Vehicle(Street randomStreet, Direction randomDirection, int arrival)
    {
        str = randomStreet;
        dir = randomDirection;
        arrivalTime = arrival;
        count++;
        vehicleNumber = count;
    }

    /**
    * Sets the departure time of the car to the global departure time at that moment.
    *@param int departureTime An integer used to hold the leaving time of the car.
    */
    public void setDeparture(int departureTime)
    {
        this.departureTime = departureTime;
    }

    /**
    *Returns the total count of the cars created.
    *@return int count The number of cars created.
    */
    public static int getCount()
    {
        return count;
    }

    /**
    *Gets the arrival time for the car.
    *@return int arrivalTime The arrival time for the car.
    */
    public int getArrival()
    {
        return arrivalTime;
    }

    /**
    *Checks to see if the car is on ChurchStreet or Main Street and returns what street it is on.
    *@return int 0 = ChurchStreet, 1 = Main Street
    */
    public int getStreet()
    {
        if (str == Street.Church)
            return 0;
        else
            return 1;
    }

    /**
    *Checks the direction of the car and returns it. No cars can travel southbound and continue. They all turn East, West, or North.
    *@return int 0 = Direction East, 1 = Direction West, 2 = Direction North
    */
    public int getDirection()
    {
        if (dir == Direction.E)
            return 0;
        else if (dir == Direction.W)
            return 1;
        else
            return 2;
    }

    /**
    *Returns the departure time set by the specific car.
    *@return int departureTime The time that the car leaves the intersection.
    */
    public int getDeparture()
    {
        return departureTime;
    }
    
    /**
    *Returns all of the information desired that is held by the car.
    */
    public String toString()
    {
        String bound;
        if (dir == Direction.E)
            bound = "(eastbound)";
        else if (dir == Direction.W)
            bound = "(westbound)";
        else
            bound = "(southbound)";
        return ("Vehicle #" + vehicleNumber + " " + bound);
    }
}