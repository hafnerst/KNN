import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TrainingDataFileReader {
	FileReader fr;
    BufferedReader br;

    ArrayList<double[]> negArrayList = new ArrayList<double[]>();
	ArrayList<double[]> posArrayList = new ArrayList<double[]>();    
    
    public TrainingDataFileReader() {
    	try {
			fr = new FileReader("app1.data");
			br = new BufferedReader(fr);

			for (String line = br.readLine(); line != null; line = br.readLine()) {
		       String[] arrayTrainingDataLine = line.split(",");
		       // Klasse ermitteln der aktuellen Zeile
		       if(Integer.parseInt(arrayTrainingDataLine[arrayTrainingDataLine.length - 1]) == 0) {
	    		   double[] arrTrainingData = new double[arrayTrainingDataLine.length-1];
	    		   // length -1 -> die Klasse braucht nicht gelernt werden
	    		   for(int i = 0; i < arrayTrainingDataLine.length-1; i++) {
			    	   //System.out.print(arrayTrainingDataLine[i] + " / ");
			    	   arrTrainingData[i] = Double.parseDouble(arrayTrainingDataLine[i]);
			       }
	    		   negArrayList.add(arrTrainingData);
	    	   } else {
	    		   double[] arrTrainingData = new double[arrayTrainingDataLine.length-1];
	    		   // length -1 -> die Klasse braucht nicht gelernt werden
	    		   for(int i = 0; i < arrayTrainingDataLine.length-1; i++) {
			    	   //System.out.print(arrayTrainingDataLine[i] + " / ");
			    	   arrTrainingData[i] = Double.parseDouble(arrayTrainingDataLine[i]);
			       }
	    		   posArrayList.add(arrTrainingData);
	    	   }
		       //System.out.println();
			}
		    br.close();
    	} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
    }
    
    public ArrayList<double[]> getNegArrayList() {
    	return negArrayList;
    }

	public ArrayList<double[]> getPosArrayList() {
		return posArrayList;
	}
}
