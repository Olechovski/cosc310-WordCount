package edu.iup.cosc310.util;
import java.util.Iterator;

/**
 * An abstract data type for a Dictionary that maps a set of keys to values.
 * 
 * @author Eric Olechovski
 *
 * @param <K> Data type for the keys
 * @param <V> Data type for the values
 */
public interface Dictionary<K,V>{
	
	
	/**
	 * Put a key together with its associated value into the dictionary.
	 * If the key already exists then the new value replaces the current
	 * value associated with the key. Values can be retrieved using the
	 * get method.
	 * 
	 * @param key the key
	 * @param value the new value
	 * @return the original value if the key already exists in
	 * 			the dictionary, otherwise null.
	 */
	public V put (K key, V value);

	
	/**
	 * Get the current value associated with a give key.
	 * 
	 * @param key the key
	 * @return the current value associated with key in the dictionary
	 * 			if found, otherwise null.
	 */
	public V get(K key);
	
	
	/**
	 * Remove the key and its associated value associated from the 
	 * dictionary. The value associated with the key is returned.
	 *  If the key does not exist in the dictionary then null is returned.
	 * 
	 * @param key the key
	 * @return the value associated with the removed key in the dictionary.
	 * 			If the key did not exist then null.
	 */
	public V remove(K key);
	
	
	/**
	 * Create an Iterator to iterate over the keys of the dictionary.
	 * 
	 * @return an Iterator to iterator over the keys.
	 */
	public Iterator<K> keys();
	
	
	
	/**
	 * Test if the dictionary is empty
	 * 
	 * @return true if the dictionary is empty, otherwise false
	 */
	public boolean isEmpty();
	
	
	/**
	 * Get the number of keys in the dictionary
	 * 
	 * @return the number of keys in the dictionary
	 */
	public int noKeys();

}
