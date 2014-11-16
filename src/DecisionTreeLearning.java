 import java.io.*;
 import java.util.ArrayList;
 import java.util.HashMap;

public class DecisionTreeLearning {
	public int targetClassCount = 4;

	public static double LogBaseX(double x, double base){
		return Math.log(x) / Math.log(base);
	}

	static double calcEntropy( ArrayList<TrainingDataItem> examples ){
		double entropy = 0;

		HashMap<String, Integer> targetClassOccurrenceCount = new HashMap<String, Integer>();

		for( TrainingDataItem item : examples ){
			Integer counter = targetClassOccurrenceCount.get( item.targetClass );
			if( counter == null ) {
				counter = 0;
			}
			counter++;

			targetClassOccurrenceCount.put(item.targetClass, counter);
		}


		for( String key : targetClassOccurrenceCount.keySet() ) {
			double occurrence = targetClassOccurrenceCount.get( key );
			double p = occurrence / examples.size();
			entropy -= p * LogBaseX(p,2);
		}

		return entropy;
	}

	 static double calcGain(ArrayList<TrainingDataItem> examples, String attribute ){
		 
		 double gain = 0;

		 HashMap< String, ArrayList<TrainingDataItem> > listOfExamplesSplitByAttributeValue = new HashMap< String, ArrayList<TrainingDataItem> >();

		 splitExamples(examples, attribute, listOfExamplesSplitByAttributeValue);

		 double gain2ndPart = 0;

		 for( String key : listOfExamplesSplitByAttributeValue.keySet() ) {
			 ArrayList<TrainingDataItem> list = listOfExamplesSplitByAttributeValue.get(key);

			 gain2ndPart += ( list.size() / examples.size() ) * calcEntropy( list );
		 }

		 gain = calcEntropy( examples ) - gain2ndPart;

		 return gain;
	 }

	public static void splitExamples(ArrayList<TrainingDataItem> examples, String attribute, HashMap<String, ArrayList<TrainingDataItem>> listOfExamplesSplitByAttributeValue) {
		for( TrainingDataItem item : examples ){
            ArrayList<TrainingDataItem> list = listOfExamplesSplitByAttributeValue.get( item.getAttributeValue( attribute ) );

            if( list == null ){
                list = new ArrayList<TrainingDataItem>();
                listOfExamplesSplitByAttributeValue.put( item.getAttributeValue( attribute ), list );
            }

            list.add( item );
        }
	}

	 // returns best valued attribute
	 static String selectBestAttribute(ArrayList<TrainingDataItem> examples, ArrayList<String> attributes) {

		 String bestCurrentAttribute = "";
		 double bestCurrentGain = -1;

		 for( String attribute : attributes) {
			 // calc GAIN for every attribute
			 double currentGain = calcGain(examples, attribute);

			 if( currentGain > bestCurrentGain ) {
				 bestCurrentAttribute = attribute;
				 bestCurrentGain = currentGain;
			 }
		 }

		 return bestCurrentAttribute;
	 }

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
				System.out.println("readline = " + s);
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
	    
	    System.out.println(items.size());

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
		
		System.out.println(items.size());

	}
}

