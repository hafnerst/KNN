import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TestDataFileReader {
	FileReader fr;
    BufferedReader br;

    ArrayList<double[]> testDataArrayList = new ArrayList<double[]>();  
    
    public TestDataFileReader() {
    	try {
			fr = new FileReader("app1.test");
			br = new BufferedReader(fr);

			for (String line = br.readLine(); line != null; line = br.readLine()) {
		       String[] arrayTrainingDataLine = line.split(",");
    		   double[] arrTrainingData = new double[arrayTrainingDataLine.length];
    		   for(int i = 0; i < arrayTrainingDataLine.length; i++) {
		    	   arrTrainingData[i] = Double.parseDouble(arrayTrainingDataLine[i]); 
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
}
