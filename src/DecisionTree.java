import java.util.ArrayList;


public class DecisionTree {
	public Node root;
	
	public DecisionTree() {
		root = new Node();
	}
	
	// main id3-function (without recursion)
	 static  DecisionTree id3(ArrayList<TrainingDataItem> examples, String targetAttribute, ArrayList<String> attributes){

		 DecisionTree tree = new DecisionTree();
		 tree.root.id3(examples, targetAttribute, attributes);
		 return tree;
	 }

	public String toString(){
		String res = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
		res += "<tree>" + root.toString() + "</tree>";
		return res;
	}
	
	public boolean testWithExamples(ArrayList<TrainingDataItem> examples){
		for(TrainingDataItem item: examples){
			if( testRecursive(root, item) < 1)
				return false;
		}
		return true;
	}
	
	public static int testRecursive(Node n, TrainingDataItem i){
		if( n.isLeaf ) {
			return i.targetClass.equals(n.label) ? 1 : 0;
		}
		
		String v = i.getAttributeValue(n.label);
		
		for(Node.Edge e: n.edges){
			if(e.value.equals(v)){
				return testRecursive(e.child, i);
			}
		}
		return -1;
	}
}