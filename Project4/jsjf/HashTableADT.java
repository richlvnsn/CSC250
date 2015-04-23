/**
* Assignment: Project 4
* Due date: 12/5/2014
* Instructor: Dr. DePasquale
* Submitted by: Rich Levenson and Daniel Sarnelli
*/

package jsjf;

/**
 * The HashTableADT class is the interface LinkedChainedHashtable implements.
 * @author Daniel Sarnelli
 * @author Rich Levenson
 */

public interface HashTableADT<T> {
	
	/**
	 * addElement adds any element to the hashtable.
	 * @param element
	 * @return true if this is the first time that the resource is added.
	 */
	
	public abstract boolean addElement(T element);
	
	/**
	 * Finds a matching element in the hashtable.
	 * @param element
	 * @return a generic object of type T.
	 */
	
	public abstract T find(T element);
	
	/**
	 * Returns the size of the hashtable.
	 * @return size of the hashtable.
	 */
	
	public abstract int size();
	
	/**
	 * @return true if the hashtable is empty.
	 */
	
	public abstract boolean isEmpty();
	
	/**
	 * @return a string of information from the hashtable in a readable format.
	 */
	
	public abstract String toString();
	
	/**
	 * Takes every element from each bucket in the hashtable and places it all into an array.
	 * @return an array of type T.
	 */
	
	public abstract T[] getElements();
}