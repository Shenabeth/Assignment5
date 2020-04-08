/**
 * The external Tree Node for Linked Trees
 * @author Shenabeth Jenkins
 *
 * @param <T>
 */
public class TreeNode<T> {
	//fields
    protected T data;
    protected TreeNode<T> leftNode;
    protected TreeNode<T> rightNode;
	
    
	//constructors
	/**
	 * Create a new TreeNode with left and right child set to null and data set to the dataNode
	 * @param dataNode - the data to be stored in the TreeNode
	 */
	public TreeNode(T dataNode) {
		data = dataNode;
		leftNode = null;
		rightNode = null;
	}
	
	/**
	 * Used for making deep copies
	 * @param node - node to make copy of
	 */
	public TreeNode(TreeNode<T> node) {
		data = node.data;
		leftNode = new TreeNode<>(node.leftNode);
        rightNode = new TreeNode<>(node.rightNode);
	}
	
	
	//methods
	/**
	 * Return the data within this TreeNode
	 * @return the data within the TreeNode
	 */
	public T getData() {
		return data;
	}
}
