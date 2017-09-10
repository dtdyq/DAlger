package dtdyq.alger.core;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

public interface Container<T> extends Iterable<T>{

	/**
	 * return the size of elements in container 
	 * @return int
	 */
	int size();
	/**
	 * return true if the containr contain the specific key,return false else
	 * @return true or false
	 */
	boolean contain(T key);
	/**
	 * return the container's iterator
	 */
	Iterator<T> iterator(); 
	
	/**
	 * remove all elements in the container
	 */
	void clear();
	/**
	 * return true if there has no element in the container ,else return false
	 * @return boolean
	 */
	boolean isEmpty();
	/**
	 *  add the element to container
	 * @param value
	 */
	void add(T value);
	
	/**
	 * add the specific container's elements to this container
	 * @param con
	 */
	void add(Container<T> con);
	
	/**
	 * add the specific collection's element to this container
	 * @param col
	 */
	void add(Collection<T> col);
	
	/**
	 * remove the specific element from container
	 * @param value
	 */
	void remove(T value);
}
