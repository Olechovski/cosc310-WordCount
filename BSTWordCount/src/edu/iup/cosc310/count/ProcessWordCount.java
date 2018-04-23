package edu.iup.cosc310.count;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

import edu.iup.cosc310.util.BSTDictionary;

/**
 * Displays the words from a text file in alphabetical order
 * and the number of how many times that word was found in the text
 * 
 * 
 * @author Eric Olechovski
 *
 */
public class ProcessWordCount {

	private static Scanner in;
	public static void main(String[] args) {

		BSTDictionary<String,Integer> dictionary = new BSTDictionary<String, Integer>();
		
		// reads in the text file from the command line
		try{
			in = new Scanner ( new File(args[0]) );
		}
		catch (FileNotFoundException e){
			System.err.println ("File not Found");
			System.exit(1);
		}
		in.useDelimiter("\\W+");
	
		while (in.hasNext()){
			
			String word = in.next().trim();
			Integer count = dictionary.get(word);
			
			if (count == null){
				dictionary.put(word, 1);
			}
			else{
				dictionary.put(word, count + 1);
			}

		}


		// display each word and number of times it occurred in the file
		for(Iterator<String> iter = dictionary.keys(); iter.hasNext();){
			String w = iter.next();
			System.out.println(w + " : " + dictionary.get(w));
		}

	}



}
