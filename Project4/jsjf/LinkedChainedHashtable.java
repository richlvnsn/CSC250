/**
* Assignment: Project 4
* Due date: 12/5/2014
* Instructor: Dr. DePasquale
* Submitted by: Rich Levenson and Daniel Sarnelli
*/

package jsjf;
import java.util.*;
import java.io.*;

/**
 * The LinkedChainedHashtable class encapsulates a hashtable that handles collisions with the chaining method using linked lists.
 * The class contains instance data for an array of LinkedList<T> objects, which is the basis for the hashtable, and a static int for the total number of elements held in the table.
 * @author Richard Levenson
 * @author Daniel Sarnelli
 */
public class LinkedChainedHashtable<T extends Comparable<T>> implements HashTableADT<T>
{
	/**
	 * The array of LinkedList<T> objects that contain all of the elements of the hashtable.
	 */
	private LinkedList<T>[] table = new LinkedList[75];

	/**
	 * The total number of elements in the hashtable.
	 */
	private static int numElements = 0;

	/*public LinkedChainedHashtable(PrintWriter errors){
	}*/

	/**
	 * Attempts to add an element of type T to the hashtable.  If the element already exists within the hashtable, the element is not added and we return false.
	 * If the element does not already exist in the table, the element is added, the number of elements is incremented, and we return true.
	 * @param element the element to add to the hashtable
	 * @return whether or not the element was added to the hashtable
	 */
	public boolean addElement(T element){
		int index = getIndex(element.hashCode());
		int found = 1;
		boolean added;
		/**
		 * Instantiates a LinkedList<T> at this index of the array if one does not already exist.
		 */
		if(table[index] == null)
			table[index] = new LinkedList<T>();
		/**
		 * Creates a ListIterator to go through the linked list searching for the element.
		 */
		ListIterator tempList = table[index].listIterator();
		T tempElement = null;

		/**
		 * Loops until the element is found or the end of the ListIterator is reached.
		 */
		while(tempList.hasNext() && found != 0){
			tempElement = (T)tempList.next();
			found = ((Comparable)(tempElement)).compareTo(element);
		}
		if(found == 0){
			added = false;
			return added;
		}
		else{
			table[index].add(element);
			added = true;
			numElements++;
		}
		return added;
	}

	/**
	 * Finds an element in the hashtable that matches the provided element and returns that element.
	 * @param element the element to be found
	 * @return the element from the hashtable that was found
	 * @throws RuntimeException if there is no match in the hashtable of the provided element
	 */
	public T find(T element){
		int index = getIndex(element.hashCode());
		ListIterator tempList = table[index].listIterator();
		T tempElement = table[index].peek();
		int found = ((Comparable)tempElement).compareTo(element);
		while (tempList.hasNext() && found != 0){
			tempElement = (T)tempList.next();
			found = ((Comparable)tempElement).compareTo(element);
		}
		if (found != 0)
			throw new RuntimeException();

		return tempElement;
	}

	/**
	 * Returns the total number of elements in the hashtable.
	 * @return the total number of elements in the hashtable
	 */
	public int size(){
		//T[] temp = getElements();
		//return temp.length;
		return numElements;
	}

	/**
	 * Returns whether the hashtable is empty or not.
	 * @return true if the hashtable is empty, false if the hashtable has at least one element
	 */
	public boolean isEmpty(){
		boolean empty;
		if (numElements == 0)
			empty = true;
		else
			empty = false;
		return empty;
	}
	
	/**
	 * Returns a string representation of the hashtable.
	 * @return a string representation of the hashtable
	 */
	public String toString(){
		String str = ("This " + ((this.isEmpty()) ? "empty":"filled") + " hashtable has: \n" + numElements + "elements\n" + 75 + "buckets\n");
		return str;
	}
	
	/**
	 * First creates a LinkedList<T> of all of the elements in the hashtable.  Then returns the array equivalent of that LinkedList.
	 * @return the array of all elements in the hashtable
	 */
	public T[] getElements(){
		LinkedList<T> temp = new LinkedList<T>();
		for (int i = 0; i <75; i++){
			if (table[i] != null){

				for(int j = 0; j < table[i].size(); j++){
					 temp.add((table[i].get(j)));
				}
			}
		}
		/**
		 * In order to make an array of T objects, an array of Comparable objects must be created and cast to T[].
		 */
		T[] kTemp = (T[])(new Comparable[numElements]);
		for (int k = 0; k < numElements; k++)
		{
			kTemp[k] = temp.get(k);
		}
		return kTemp;
	}                                                                                                                                                                                    

	/**
	 * First creates an array of integers that stores the size of the linkedlists held in those indices of the hashtable.  Then returns the index of the one with the largest size.
	 * @return the index of the bucket with the longest LinkedList
	 */
	public int longestBucket(){
		int[] ints = new int[75];
		for (int i = 0; i < 75; i++){
			if (table[i] != null)
				ints[i] = table[i].size();
		}
		return maxInRange(ints);
	}

	/**
	 * Returns the longest LinkedList.
	 * @return the longest LinkedList
	 *
	 */
	public LinkedList<T> longestList(){
		return table[longestBucket()];
	}

	/**
	 * Uses the Division hashing method to calculate the index of the array that an element belongs in.
	 * @param the hash key provided by the element
	 * @return the index in which to store the element
	 */
	public static int getIndex(int key) {
		int index = Math.abs(key) % 75;
		return index;
	}

	 /**
	 * maxInRange takes an array of integers and returns the index of the largest value.
	 * @param ints
	 * @return the index of the largest value.
	 */
	public static int maxInRange(int[] ints){
		int index = 0;
		int counter = 0;

		/**
		 * The counter is updated in this loop each time a larger value is found.
		 * The index is then updated to wherever this value exists.
		 */
		for (int i = 0; i < ints.length; i++){
			if (ints[i] > counter){
				counter = ints[i];
				index = i;
			}
		}

		return index;
	}
}