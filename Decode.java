import java.util.*;
public class Decode {

	static String sortedAlph = "etaoinshrdlucmfwypvbgkqjxz";
	
	/*
	 * Keeps a count of the number of times each letter 
	 * appears in the encoded message
	 */
	public static int[] frequencyArray (String enc) {
		int [] freqArr = new int [26];
		for (int i = 0; i < enc.length(); i++) { //loops through string
			char ch = enc.charAt(i);
			if (Character.isLetter(ch))
				freqArr[ch - 97]++; //increments the count of the specific char
		}
		return freqArr;
	}
	/*
	 * Maps each letter in the encoded string to the decoded letter,
	 * returning an array of chars sorted by their frequency in the decoded message
	 */
	public static char [] map (int [] sorted, int[]unsorted) {		
		char [] mapped = new char [26];
		for (int i = 0; i < sorted.length; i++) {
			for (int j = 0; j < unsorted.length; j++)
				if (unsorted[j] == sorted[i]) { //finds where the count in the unsorted array appears in the sorted array
					mapped [25 - i] = (char) (j + 97); //builds the mapped array backwards instead of sorting the freqArray
					unsorted[j] = -1; //gets rid of duplicates 
					j = unsorted.length; //breaks out of inner for loop	
				}
		}
		return mapped;
	}
	
	/*
	 * copyArray creates and returns an exact copy of another array of ints
	 */
	public static int [] copyArray (int [] freq) {
		int [] original = new int [freq.length];
		for (int i = 0; i < freq.length; i++)
			original[i] = freq[i];
		return original;
	}
	/*
	 * Given the mapped array and encoded string, makeString returns the decoded message
	 */
	public static String makeString (String enc, char [] mapped) {
		String decoded = "";
		for (int i = 0; i < enc.length(); i++) { //loops through encoded message
			char c = enc.charAt(i);
			if (Character.isLetter(c)) {
				for (int j = 0; j < mapped.length; j++) //loops through mapped array
					if (c == mapped[j]) //finds where c appears in the mapped array
						decoded += sortedAlph.charAt(j); //adds the corresponding char 
														//to the decoded string
			}
			else
				decoded += c; //if c is not a letter, it does not get mapped so it just gets 
							  //added to the decoded string
		}
		return decoded;
	}
	/*
	 * decodeMessage calls the other methods of the Decode class to find and set the 
	 * decoded message for a given Message object (that has an encoded string set).  
	 */
	public static void decodeMessage (Message msg) {
		String encoded = msg.getEncoded(); //gets encoded string for the message object
		int [] freqArr = frequencyArray(encoded); //freqArr holds the count for each letter char
		int [] originalCount = copyArray(freqArr); //originalCount also holds the count for each letter char
		Arrays.sort(freqArr); //freqArr now holds the counts in ascending order
		char [] mappedCode = map(freqArr, originalCount); //mappedCode is now an ordered array of chars in
														//descending order of their frequency in the encoded message
		msg.setDecoded(makeString(encoded, mappedCode)); 
	}
}





