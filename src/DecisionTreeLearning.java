import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


 public class DecisionTreeLearning {
	
	static ArrayList<TrainingDataItem> items = new  ArrayList<TrainingDataItem>();
	
	
	static DecisionTree ID3(ArrayList<TrainingDataItem> examples, String targetAttribute, List attributes){
		boolean isLeaf = false;
		for (TrainingDataItem item : examples) {
			String remeberTarget = "";
			
			if(remeberTarget.isEmpty()){
				remeberTarget = item.targetClass;
			} else {
				if(item.targetClass != remeberTarget){
					
				}
			}
		}
		
		if(isLeaf){
			
		}
		
		
		DecisionTree tree = new DecisionTree();

		return tree;
	}

	public static void main(String[] args) {
		File file = new File("training_data.txt");
	    BufferedReader reader = null;
	       
	    try {
	        reader = new BufferedReader(new FileReader(file));
	        String s = null;

	        do{
	        	s = reader.readLine();
	        	TrainingDataItem i = new TrainingDataItem(s);
	        	items.add(i);
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
	}
}

