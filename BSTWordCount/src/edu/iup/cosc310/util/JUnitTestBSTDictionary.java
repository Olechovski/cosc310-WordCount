package edu.iup.cosc310.util;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

public class JUnitTestBSTDictionary {

	BSTDictionary<String,Integer> dictionary = new BSTDictionary<String, Integer>();
	
	@FunctionalInterface
	interface Runner {
		public void run();
	}

	public static Class catchException(Runner runner) {
		try {
			runner.run();
			return null;
		} catch (Exception e) {
			return e.getClass();
		}
	}

	
	public void putBalancedTree(){
		dictionary.put("H", 8);
		dictionary.put("D", 4);
		dictionary.put("L", 12);
		dictionary.put("B", 2);
		dictionary.put("F", 6);
		dictionary.put("A", 1);
		dictionary.put("C", 3);
		dictionary.put("E", 5);
		dictionary.put("G", 7);
		dictionary.put("J", 10);
		dictionary.put("N", 14);
		dictionary.put("K", 11);
		dictionary.put("I", 9);
		dictionary.put("M", 13);
		dictionary.put("O", 15);
		
	}
	
	@Test
	public void testPut() {
		assertTrue(dictionary.isEmpty());
		assertEquals(0, dictionary.noKeys());
		assertEquals(null,dictionary.put("C", 3));
		assertFalse(dictionary.isEmpty());
		assertEquals(1, dictionary.noKeys());
		assertEquals(null, dictionary.put("B", 4));
		assertEquals(2, dictionary.noKeys());
		assertEquals(Integer.valueOf(4), dictionary.put("B", 6));
		assertEquals(2, dictionary.noKeys());
		assertEquals(null, dictionary.put("A", 5));
		assertEquals(3, dictionary.noKeys());
	}

	@Test
	public void testGet() {
		assertTrue(dictionary.isEmpty());
		assertEquals(null,dictionary.get("C"));
		putBalancedTree();
		assertEquals(Integer.valueOf(3),dictionary.get("C"));
		assertEquals(Integer.valueOf(11),dictionary.get("K"));
		assertEquals(null,dictionary.get("Z"));
		assertEquals(dictionary.get("C"), dictionary.put("C", 4));
		assertEquals(Integer.valueOf(4),dictionary.get("C"));
	}
	
	@Test
	public void testRemove() {
		assertTrue(dictionary.isEmpty());
		putBalancedTree();
		assertEquals(15, dictionary.noKeys());
		assertEquals(Integer.valueOf(1), dictionary.remove("A"));
		assertEquals(14, dictionary.noKeys());
		assertEquals(Integer.valueOf(2), dictionary.remove("B"));
		assertEquals(13, dictionary.noKeys());
		assertEquals(Integer.valueOf(3), dictionary.remove("C"));
		assertEquals(12, dictionary.noKeys());
		assertEquals(Integer.valueOf(4), dictionary.remove("D"));
		assertEquals(11, dictionary.noKeys());
		assertEquals(Integer.valueOf(5), dictionary.remove("E"));
		assertEquals(10, dictionary.noKeys());
		assertEquals(Integer.valueOf(6), dictionary.remove("F"));
		assertEquals(9, dictionary.noKeys());
		assertEquals(Integer.valueOf(7), dictionary.remove("G"));
		assertEquals(8, dictionary.noKeys());
		assertEquals(Integer.valueOf(8), dictionary.remove("H"));
		assertEquals(7, dictionary.noKeys());
		assertEquals(Integer.valueOf(9), dictionary.remove("I"));
		assertEquals(6, dictionary.noKeys());
		assertEquals(Integer.valueOf(10), dictionary.remove("J"));
		assertEquals(5, dictionary.noKeys());
		assertEquals(Integer.valueOf(11), dictionary.remove("K"));
		assertEquals(4, dictionary.noKeys());
		assertEquals(Integer.valueOf(12), dictionary.remove("L"));
		assertEquals(3, dictionary.noKeys());
		assertEquals(Integer.valueOf(13), dictionary.remove("M"));
		assertEquals(2, dictionary.noKeys());
		assertEquals(Integer.valueOf(14), dictionary.remove("N"));
		assertEquals(1, dictionary.noKeys());
		assertEquals(Integer.valueOf(15), dictionary.remove("O"));
		assertEquals(0, dictionary.noKeys());
		
		assertTrue(dictionary.isEmpty());
		assertEquals(null, dictionary.remove("O"));
		putBalancedTree();
		assertEquals(null, dictionary.remove("Z"));
		
	}
	
	@Test
	public void testIsEmpty() {
		assertEquals(0, dictionary.noKeys());
		assertTrue(dictionary.isEmpty());
		assertEquals(null, dictionary.put("A", 1));
		assertEquals(1, dictionary.noKeys());
		assertFalse(dictionary.isEmpty());
		assertEquals(Integer.valueOf(1), dictionary.remove("A"));
		assertEquals(0, dictionary.noKeys());
		assertTrue(dictionary.isEmpty());
	}
	
	
	@Test
	public void testKeyIterator() {
		assertEquals(null, dictionary.put("B", 3));
		assertEquals(null, dictionary.put("A", 5));
		assertEquals(null, dictionary.put("C", 1));
		Iterator<String> iter = dictionary.keys();
		assertTrue(iter.hasNext());
		assertEquals("A", iter.next());
		assertEquals("B", iter.next());
		assertEquals("C", iter.next());
		assertEquals(NoSuchElementException.class, catchException(()-> {iter.next(); }));
	}
	
}
