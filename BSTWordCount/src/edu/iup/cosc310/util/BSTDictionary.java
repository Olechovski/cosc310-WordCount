package edu.iup.cosc310.util;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;


/**
 * BSTDictionary creates a binary search tree in which contains a key 'K'
 * and its associated value 'V'
 * 
 * @author Eric Olechovski
 *
 * @param <K>
 * @param <V>
 */
public class BSTDictionary<K,V> implements Dictionary<K,V>{

	private int noKeys;
	private BSTDictionary<K,V> left;
	private BSTDictionary<K,V> right;
	private K key;
	private V value;

	/** 
	 * Creates an additional node to the binary search tree, however if 
	 * the root node is empty then it will fill that node with the passing 
	 * values. It will also keep track of the number of keys being added in.
	 * 
	 * @param <K,V>
	 * 
	 * @return <V>  returns the original value if the key already exist in the
	 * 				dictionary
	 */
	@Override
	public V put(K key, V value) {
		V prevValue = null;

		if(isEmpty()){
			noKeys++;
			this.key = key;
			this.value = value;
			left = new BSTDictionary<K,V>();
			right = new BSTDictionary<K,V>();

			return null;
		}

		// determine which side of the dictionary to create the new node
		int compare = ((Comparable<K>) this.key).compareTo(key);

		if( compare > 0){
			prevValue  = left.put(key, value);
		}
		else if(compare < 0){
			prevValue  = right.put(key, value);
		}
		else if (compare == 0){
			prevValue = this.value;
			this.value = value;
		}

		// when the prevValue is null that means a new node has been added
		if (prevValue == null) {
			noKeys++;
			return null;
		}
		else
			// the new key already exist therefore, replaces the 
			// the value with the new value and returns the old value
			return prevValue;
	}


	/**
	 * Finds the key in the dictionary and returns is associated value unless 
	 * the key does not exist within the dictionary, in this case 
	 * it would return null
	 * 
	 * @param <K>
	 * 
	 * @return <V>  returns the value associated key being passed in
	 */
	@Override
	public V get(K key) {

		if(isEmpty()){
			return null;
		}

		V prevValue = null;

		int compare = ((Comparable<K>) this.key).compareTo(key);

		if( compare > 0){
			prevValue  = left.get(key);
		}
		else if(compare < 0){
			prevValue  = right.get(key);
		}
		else if (compare == 0){
			prevValue = this.value;
		}

		return prevValue;
	}

	/**
	 * Removes a node from the dictionary that has a particular key 
	 * 
	 * @param <K>
	 * 
	 * @return <V>  returns the value of the node that is being removed from the
	 * 				dictionary however if the key does not exit is will return null
	 * 
	 */
	@Override
	public V remove(K key) {


		V value = null;

		if (isEmpty()){
			return null;
		}

		int compare = ((Comparable<K>) this.key).compareTo(key);

		if ( compare > 0){
			value = this.left.remove(key);

			if (value != null){
				noKeys--;
			}

		}

		else if (compare < 0){
			value = this.right.remove(key);


			if (value != null){
				noKeys--;
			}

		}


		// if the key does exist
		else if (compare == 0){

			value = this.value;
			BSTDictionary<K,V> subtree = null;

			if (noKeys == 1 && left.isEmpty() && right.isEmpty()){
				this.key = null;
				this.value = null;
				right = null;
				left = null;

				noKeys--;
				return value;
			}

			if (this.left.isEmpty()){
				// copy right sub tree
				subtree = this.right;

			}

			else if (this.right.isEmpty()){
				// copy left sub tree
				subtree = this.left;
			}


			else{
				subtree = right.getLeft();
				this.value = subtree.value;
				this.key = subtree.key;
				subtree.remove(subtree.key);

				return value;

			}

			this.value =  subtree.value;
			this.key = subtree.key;
			this.left = subtree.left;
			this.right = subtree.right;
			this.noKeys = subtree.noKeys;

		}

		return value;

	}


	/**
	 * @return the left most node of the right child to replace the removed node
	 */
	public BSTDictionary<K,V> getLeft(){
		if(left.isEmpty()){
			return this;
		}

		else{
			return left.getLeft();

		}

	}


	@Override
	public Iterator<K> keys() {
		return new KeyIterator<K>(this);
	}

	@Override
	public boolean isEmpty() {
		if (this.key == null){
			return true;
		}
		else
			return false;
	}

	@Override
	public int noKeys() {
		return noKeys;
	}

	/**
	 * KeyIterator implements the iterator used for the keys
	 * as well as creating a stack of the keys for the iterator to
	 * iterate through.
	 * 
	 * 
	 * @param <K>
	 */
	private class KeyIterator<K> implements Iterator<K>{


		Stack<BSTDictionary<K, V>> position = new Stack<BSTDictionary<K, V>>();


		public KeyIterator(BSTDictionary<K, V> bstDictionary) {
			pushLeft(bstDictionary);
		}


		/**
		 * adds the left side keys of the dictionary
		 * 
		 * @param dictionary
		 */
		private void pushLeft(BSTDictionary<K, V> dictionary){
			if (!dictionary.isEmpty()) {
				position.push(dictionary);
				pushLeft(dictionary.left);
			}

		}


		@Override
		public boolean hasNext() {
			return ! position.isEmpty();
		}

		@Override
		public K next() {
			if (!hasNext() ) {
				throw new NoSuchElementException();
			}
			BSTDictionary<K,V> node = position.pop();
			pushLeft(node.right);
			return node.key;

		}

	}





}
