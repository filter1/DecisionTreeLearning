import java.util.ArrayList;

public class DecisionTree {
	public Node root;
	
	public DecisionTree() {
		root = new Node();
	}
	
	private class Edge{
		String value;
		Node child;
	}
	
	public class Node{
		boolean isLeaf;
		boolean isRoot;
		
		ArrayList<Edge> edges = new ArrayList<Edge>();
		
		String attribute;
		String classification;
	}
}
