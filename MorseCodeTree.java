/**
 * This is a MorseCodeTree which is specifically used for the conversion of morse code to english.
 * It relies on a root (reference to root of the tree).
 * @author Shenabeth Jenkins
 *
 */

import java.util.ArrayList;

public class MorseCodeTree implements LinkedConverterTreeInterface<String> {
	//fields
	private TreeNode<String> root;


	//constructor
	/**
	 * calls the buildTree method
	 */
	public MorseCodeTree() {
		//calls the buildTree method
		buildTree();
	}


	//overridden methods
	/**
	 * Returns a reference to the root
	 */
	@Override
	public TreeNode<String> getRoot() {
		return new TreeNode<>(root);
	}

	/**
	 * sets the root of the MorseCodeTree
	 * @param newNode - a copy of newNode will be the new root
	 */
	@Override
	public void setRoot(TreeNode<String> newNode) {
		root = new TreeNode<>(newNode);
	}

	/**
	 * Adds element to the correct position in the tree based on the code
	 * This method will call the recursive method addNode
	 * @param code - the code for the new node to be added, example ".-."
	 * @param letter - the letter for the corresponding code, example "r"
	 */
	@Override
	public LinkedConverterTreeInterface<String> insert(String code, String result) {
		//insert
		addNode(root, code, result);
		
		//then return
		return this;
	}

	/**
	 * This is a recursive method that adds element to the correct position in the tree based on the code.
	 * A '.' (dot) means traverse to the left. A "-" (dash) means traverse to the right.
	 * The code ".-" would be stored as the right child of the left child of the root Algorithm for the recursive method:
	 * 1. if there is only one character
	 * a. if the character is '.' (dot) store to the left of the current root
	 * b. if the character is "-" (dash) store to the right of the current root
	 * c. return
	 * 2. if there is more than one character
	 * a. if the first character is "." (dot) new root becomes the left child
	 * b. if the first character is "-" (dash) new root becomes the right child
	 * c. new code becomes all the remaining characters in the code (beyond the first character)
	 * d. call addNode(new root, new code, letter)
	 * 
	 * @param root - the root of the tree for this particular recursive instance of addNode
	 * @param code - the code for this particular recursive instance of addNode
	 * @param letter - the data of the new TreeNode to be added
	 */
	@Override
	public void addNode(TreeNode<String> root, String code, String letter) {
		
		if (code.length() == 1) {
			// '.' (dot) means traverse to the left
			if (code.equals(".")) { root.leftNode = new TreeNode<>(letter); }
			
			// "-" (dash) means traverse to the right
			else if (code.equals("-")) { root.rightNode = new TreeNode<>(letter); }
		}
		
		else {
			// '.' (dot) means traverse to the left
			if (code.charAt(0) == '.') { addNode(root.leftNode, code.substring(1), letter); } 
			
			// "-" (dash) means traverse to the right
			else if (code.charAt(0) == '-') { addNode(root.rightNode, code.substring(1), letter); }
		}
	}

	/**
	 * Fetch the data in the tree based on the code
	 * This method will call the recursive method fetchNode
	 * @param code - the code that describes the traversals to retrieve the string (letter)
	 */
	@Override
	public String fetch(String code) {
		//pass the root and code
		return fetchNode(root, code);
	}

	/**
	 * This is the recursive method that fetches the data of the TreeNode that corresponds with the
	 * code A '.' (dot) means traverse to the left. A "-" (dash) means traverse to the right.
	 * The code ".-" would fetch the data of the TreeNode stored as the right child of the left child of the root
	 * 
	 * @param root - the root of the tree for this particular recursive instance of addNode
	 * @param code - the code for this particular recursive instance of addNode
	 * @return the string (letter) corresponding to the code
	 */
	@Override
	public String fetchNode(TreeNode<String> root, String code) {
		//create variable
		String letter = "";
		
		//traverse left or right
		if (code.length() == 1) {
			// '.' (dot) means traverse to the left
			if (code.equals(".")) { letter = root.leftNode.data; }
			
			// "-" (dash) means traverse to the right
			else if (code.equals("-")) { letter = root.rightNode.data; }
		}

		else {
			//make sure code is not empty
			if (!code.isEmpty()) {
				// '.' (dot) means traverse to the left
				if (code.charAt(0) == '.') { letter = fetchNode(root.leftNode, code.substring(1)); }
				
				// "-" (dash) means traverse to the right
				else if (!code.isEmpty() && code.charAt(0) == '-') { letter = fetchNode(root.rightNode, code.substring(1)); }
			}
		}
		
		//return
		return letter;
	}

	/**
	 * This operation is not supported in the MorseCodeTree
	 * @param data - data of node to be deleted
	 * @return reference to the current tree
	 * @throws java.lang.UnsupportedOperationException
	 */
	@Override
	public LinkedConverterTreeInterface<String> delete(String data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Not supported yet");
	}

	/**
	 * This operation is not supported in the MorseCodeTree
	 * @returns reference to the current tree
	 * @throws java.lang.UnsupportedOperationException
	 */
	@Override
	public LinkedConverterTreeInterface<String> update() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Not supported yet");
	}

	/**
	 * This method builds the MorseCodeTree by inserting the nodes of the tree level by level based on the code.
	 * The root will have a value of "" (empty string)
	 * level one: insert(".", "e"); insert("-", "t");
	 * level two: insert("..", "i"); insert(".-", "a");
	 * insert("-.", "n"); insert("--", "m"); etc.
	 * Look at the tree and the table of codes to letters in the assignment description.
	 */
	@Override
	public void buildTree() {
		//create a tree node
		root = new TreeNode<>("");

		//level 1
		insert("." , "e");
		insert("-" , "t");

		//level 2
		insert(".." , "i");
		insert(".-" , "a");
		insert("-." , "n");
		insert("--" , "m");

		//level 3
		insert("..." , "s");
		insert("..-" , "u");
		insert(".-." , "r");
		insert(".--" , "w");
		insert("-.." , "d");
		insert("-.-" , "k");
		insert("--." , "g");
		insert("---" , "o");

		//level 4
		insert("...." , "h");
		insert("...-" , "v");
		insert("..-." , "f");
		insert(".-.." , "l");
		insert(".--." , "p");
		insert(".---" , "j");
		insert("-..." , "b");
		insert("-..-" , "x");
		insert("-.-." , "c");
		insert("-.--" , "y");
		insert("--.." , "z");
		insert("--.-" , "q");
	}

	/**
	 * Returns an ArrayList of the items in the linked Tree in LNR (Inorder) Traversal order
	 * Used for testing to make sure tree is built correctly
	 * @returns an ArrayList of the items in the linked Tree
	 */
	@Override
	public ArrayList<String> toArrayList() {
		//create variables
		ArrayList<String> treeArrayList = new ArrayList<>();
		
		//call LNRoutputTraversal with root and the tree array
		LNRoutputTraversal(root, treeArrayList);
		
		//return the tree array
		return treeArrayList;
	}

	/**
	 * The recursive method to put the contents of the tree in an ArrayList in LNR (Inorder)
	 * @param root - the root of the tree for this particular recursive instance
	 * @param list - the ArrayList that will hold the contents of the tree in LNR order
	 */
	@Override
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		//make sure root isn't null
		if (root != null) {
			//traverse with left node
			LNRoutputTraversal(root.leftNode, list);
			
			//add node
			list.add(root.data);
			
			//traverse with right node
			LNRoutputTraversal(root.rightNode, list);
		}
	}


}
