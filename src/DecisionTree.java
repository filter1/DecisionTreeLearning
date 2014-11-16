import java.util.ArrayList;


public class DecisionTree {
	public Node root;
	
	public DecisionTree() {
		root = new Node();
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