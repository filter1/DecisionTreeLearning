import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DecisionTree {
	public Node root;
	
	public DecisionTree() {
		root = new Node();
	}

	public String toString(){
		String res = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";

		res += "<tree>";

		res += root.toString();

		res += "</tree>";
		return res;
	}

	public class Node{

		private class Edge{
			String value;
			Node child;
		}

		public String toString(){
			if( isLeaf ) {
				return new String( "<leaf>" + this.label + "</leaf>" );
			}

			String res = "<node attribute=\"" + this.label + "\">";

			for (Edge e: edges){
				res += "<branch value=" + "\"" + e.value + "\">";
				res += e.child.toString();
				res += "</branch>";
			}
			res += "</node>";

			return res;
		}

		ArrayList<Edge> edges = new ArrayList<Edge>();

		String label;
		boolean isLeaf;

		public void setLeaf(boolean isLeaf, String targetClass) {
			this.isLeaf = isLeaf;
			this.label = targetClass;
		}

		// create new sub-nodes with attribute 'label'
		// returns list of nodes, that are new children
		public ArrayList<Node> fork( String label, List<String> values ){
			this.label = label;

			ArrayList<Node> returnNodes = new ArrayList<Node>();

			for( String value : values ) {
				Edge edge = new Edge();
				Node node = new Node();

				returnNodes.add( node );

				edge.value = value;
				edge.child = node;

				edges.add( edge );
			}

			return returnNodes;
		}

		// print only stuff of this Node!
		public void debugOutput(){
			System.out.println( "NodeOutput: label = " + label + " | isLeaf = " + isLeaf + " | edge-Count = " + edges.size() + " |" );
		}

		// main recursion id3-function
		public void id3(ArrayList<TrainingDataItem> examples, String targetAttribute,  ArrayList<String> attributes){


			// 1. Check if we can end recursion
			boolean isLeaf = true;
			String rememberTarget = "";
			for (TrainingDataItem item : examples) {

				if(rememberTarget.isEmpty()){ // < first cycle
					rememberTarget = item.targetClass;
				} else {
					if( ! item.targetClass.equals( rememberTarget) ){
						isLeaf = false;
						break;
					}
				}
			}

			//System.out.println( "isLeaf = " + isLeaf );

			if(isLeaf){
				this.setLeaf(true, rememberTarget);
			}
			else{
				// search best valued split
				String bestAttribute = DecisionTreeLearning.selectBestAttribute( (ArrayList<TrainingDataItem>) examples.clone(), attributes );

				HashMap< String, ArrayList<TrainingDataItem> > listOfExamplesSplitByAttributeValue = new HashMap< String, ArrayList<TrainingDataItem> >();

				DecisionTreeLearning.splitExamples(examples, bestAttribute, listOfExamplesSplitByAttributeValue);

				ArrayList<String> labels = new ArrayList<String>();
				for( String key : listOfExamplesSplitByAttributeValue.keySet() ) {
					labels.add(key);
				}

				// split up the node
				// copy the attributes so the reference stuff is working with recursion
				ArrayList<Node> newNodes = this.fork(bestAttribute, labels);
				for(Edge edge: this.edges){
					ArrayList<String> copy = (ArrayList<String>) attributes.clone();
					copy.remove(bestAttribute);

					edge.child.id3(listOfExamplesSplitByAttributeValue.get(edge.value), targetAttribute, copy );
				}

			}
			//this.debugOutput();
		}
	}
}