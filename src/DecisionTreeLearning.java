 import java.io.*;
 import java.util.ArrayList;
 import java.util.HashMap;

public class DecisionTreeLearning {

	// main id3-function (without recursion)
	 static  DecisionTree id3(ArrayList<TrainingDataItem> examples, String targetAttribute, ArrayList<String> attributes){

		 DecisionTree tree = new DecisionTree();
		 tree.root.id3(examples, targetAttribute, attributes);
		 return tree;
	 }


	public static void main(String[] args) {
	
		ArrayList<TrainingDataItem> items = new  ArrayList<TrainingDataItem>();

		File file = new File("car.data");
	    BufferedReader reader = null;
	       
	    try {
	        reader = new BufferedReader(new FileReader(file));
	        String s = null;

	        do{
	        	s = reader.readLine();
//				System.out.println("readline = " + s);
				if( s != null) {
					TrainingDataItem i = new TrainingDataItem(s);
					items.add(i);
				}
	        }while(s != null && s != "");
	        
  
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {

	        e.printStackTrace();
	    } finally {
	        try {
	            if (reader != null) {
	                reader.close();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
//	    System.out.println(items.size());

		ArrayList<String> attributes = new ArrayList<String>();
		attributes.add("buying");
		attributes.add("maint");
		attributes.add("doors");
		attributes.add("persons");
		attributes.add("lug_boot");
		attributes.add("safety");

		DecisionTree tree = id3(items, new String( "targetClass" ), attributes );

//		System.out.println( tree );

		PrintWriter writer;
		try {
			writer = new PrintWriter("cardata.xml", "UTF-8");
			writer.println(tree);
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Testing the learned tree with " + items.size() + " data items" + ": The result is a " + tree.testWithExamples(items) +" decision tree.");
	}
}

