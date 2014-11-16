 import java.io.*;
 import java.util.ArrayList;
 import java.util.HashMap;

public class DecisionTreeLearning {

	public static void main(String[] args) {
	
		// 1. reading data from file
		ArrayList<TrainingDataItem> items = new  ArrayList<TrainingDataItem>();

		File file = new File("car.data");
	    BufferedReader reader = null;
	       
	    try {
	        reader = new BufferedReader(new FileReader(file));
	        String s = null;

	        do{
	        	s = reader.readLine();
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
	    	    
	    // 2. initializing the attributes for the given data
	    // could be done more generic...
		ArrayList<String> attributes = new ArrayList<String>();
		attributes.add("buying");
		attributes.add("maint");
		attributes.add("doors");
		attributes.add("persons");
		attributes.add("lug_boot");
		attributes.add("safety");

		// 3. run algorithm
		DecisionTree tree = DecisionTree.id3(items, new String( "targetClass" ), attributes );

		// 4. write to file
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
		
		// 5. test if the decision tree classifies correctly.
		System.out.println("Testing the learned tree with " + items.size() + " data items" + ": The result is a " + tree.testWithExamples(items) +" decision tree.");
	}
}

