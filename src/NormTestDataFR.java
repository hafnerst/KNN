import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class NormTestDataFR {
	FileReader fr;
    BufferedReader br;

    ArrayList<double[]> testDataArrayList = new ArrayList<double[]>(); 
	double [] xMin = new double[4];
	double [] xMax = new double[4];
    
    public NormTestDataFR() {
    	try {
			fr = new FileReader("app1.test");
			br = new BufferedReader(fr);
			
			/* xMin und xMax Werte der continous Attribute bestimmen */
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				String[] arrayTrainingDataLine = line.split(",");
				double[] arrTrainingData = new double[arrayTrainingDataLine.length-1];
    		   for(int i = 0; i < arrayTrainingDataLine.length-1; i++) {
    			   switch (i) {
    			   	   //Attribut 0
	    			   case 0: setIfExtrema(0, Double.parseDouble(arrayTrainingDataLine[i]));
	    			   break;
	    			   //Attribut 11
	    			   case 11: setIfExtrema(1, Double.parseDouble(arrayTrainingDataLine[i]));
	    			   break;
	    			   case 12: setIfExtrema(2, Double.parseDouble(arrayTrainingDataLine[i]));
	    			   break;
	    			   case 13: setIfExtrema(3, Double.parseDouble(arrayTrainingDataLine[i]));
	    			   break;
    			   }
		       }
			}
			
			fr = new FileReader("app1.test");
			br = new BufferedReader(fr);

			for (String line = br.readLine(); line != null; line = br.readLine()) {
		       String[] arrayTrainingDataLine = line.split(",");
    		   double[] arrTrainingData = new double[arrayTrainingDataLine.length];
    		   for(int i = 0; i < arrayTrainingDataLine.length; i++) {
	    			   switch (i) {
				   	   //Attribut 0
	    			   case 0: double nValue0 = normalizeValue(0, Double.parseDouble(arrayTrainingDataLine[i]));
	    			           arrTrainingData[i] = nValue0;
	    			   break;
	    			   case 2: arrTrainingData[i] = Double.parseDouble(arrayTrainingDataLine[i]);
	    			   		   //Geschlecht normalisieren
	    			   		   arrTrainingData[i] = arrTrainingData[i] - 1.0;
	    			   break;
	    			   //Attribut 11
	    			   case 11: double nValue11 = normalizeValue(1, Double.parseDouble(arrayTrainingDataLine[i]));
	    			            arrTrainingData[i] = nValue11;
	    			   break;
	    			   case 12: double nValue12 = normalizeValue(2, Double.parseDouble(arrayTrainingDataLine[i]));
	    			            arrTrainingData[i] = nValue12;
	    			   break;
	    			   case 13: double nValue13 = normalizeValue(3, Double.parseDouble(arrayTrainingDataLine[i]));
	    			            arrTrainingData[i] = nValue13;
	    			   break;
	    			   default: arrTrainingData[i] = Double.parseDouble(arrayTrainingDataLine[i]);
				   } 
    		   }
    		   testDataArrayList.add(arrTrainingData);
			}
		    br.close();
    	} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }  
    
    public ArrayList<double[]> getTestDataArrayList() {
    	return testDataArrayList;
    }
    
    private void setIfExtrema(int index, double value) {
    	if(value > xMax[index]) {
    		xMax[index] = value;
    	}
    	if(value < xMin[index] || xMin[index] == 0.0) {
    		xMin[index] = value;
    	}
    }
    
    private double normalizeValue(int index, double value) {
    	double nValue = 0.0;
    	nValue = (value - xMin[index])/(xMax[index] - xMin[index]);
    	return nValue;
    }
}
