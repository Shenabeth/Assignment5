/**
 * This class converts Morse code to English
 * @author Shenabeth Jenkins
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MorseCodeConverter {
	//fields
	private static MorseCodeTree codeTree = new MorseCodeTree();
	
	
	//constructors
	/**
	 * no arg constructor
	 */
	public MorseCodeConverter() {
		
	}
	
	
	//methods
	/**
	 * returns a string with all the data in the tree in LNR order with an space in between them.
	 * Uses the toArrayList method in MorseCodeTree It should return the data in this order:
	 * "h s v i f u e l r a p w j b d x n c k y t z g q m o"
	 * Note the extra space between j and b - that is because there is an empty string that is the root,
	 * and in the LNR traversal, the root would come between the right most child of the left tree (j)
	 * and the left most child of the right tree (b).
	 * This is used for testing purposes to make sure the MorseCodeTree has been built properly
	 * 
	 * @return the data in the tree in LNR order separated by a space
	 */
	public static String printTree() {
		//create variables
		String treeString = "";
		
		//enhanced loop to go through every element
        for (String e : codeTree.toArrayList()) {
        	treeString += e + " ";
        }
        
        //return
        return treeString;
	}
	
	/**
	 * Converts Morse code into English. Each letter is delimited by a space (‘ ‘). Each word is delimited by a ‘/’.
	 * Example:
	 * code = ".... . .-.. .-.. --- / .-- --- .-. .-.. -.."
	 * 
	 * @param code - the morse code
	 * @return the - English translation
	 */
	public static String convertToEnglish(String code) {
		//create variables
		String englishString = "";
		
		//create arrays
        String[] fullCode = code.split("/");
        String[][] newCode = new String[fullCode.length][];
        
        //loop and split
        for (int i = 0; i < newCode.length; i++) {
        	newCode[i] = fullCode[i].split(" ");
        }
        
        //loop and convert
        for (int i = 0; i < newCode.length; i++) {
            for (int j = 0; j < newCode[i].length; j++) {
            	newCode[i][j] = codeTree.fetch(newCode[i][j]);
            	englishString += newCode[i][j];
            }
            englishString += (i == newCode.length - 1) ? "" : " ";
        }
        
        //return
        return englishString;
	}

	/**
	 * Converts a file of Morse code into English Each letter is delimited by a space (‘ ‘). Each word is delimited by a ‘/’.
	 * Example:
	 * a file that contains ".... . .-.. .-.. --- / .-- --- .-. .-.. -.."
	 * string returned = "Hello World"
	 * 
	 * @param codeFile - name of the File that contains Morse Code
	 * @return the English translation of the file
	 * @throws java.io.FileNotFoundException
	 */
	public static String convertToEnglish(File codeFile) throws FileNotFoundException {
		//create scanner
		Scanner reader = new Scanner(codeFile);
        
		//create variables
		String translateToEnglish = "";
		
		//loop to add
        while (reader.hasNextLine()) {
        	translateToEnglish += reader.nextLine();
        }
        
        //return
        return convertToEnglish(translateToEnglish);	
	}
}
