import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class KNN {
	ArrayList<double[]> negArrayList;
	ArrayList<double[]> posArrayList;
	ArrayList<double[]> testData;
    final static int CAPACITY = 100000;
    final static int K = 1;
    double[] actualPoint;
	double pointClass = 0;
	int countAllIterations = 0;
	int countRight = 0;
		
	public KNN(ArrayList<double[]> negArrayList, ArrayList<double[]> posArrayList, ArrayList<double[]> testData){
		this.negArrayList = negArrayList;
		this.posArrayList = posArrayList;
		this.testData = testData;	
	}
	
	public void runKNN() {
		// aktuelle Punkt der verglichen werden soll
		for(int x = 0; x < testData.size(); x++) {
			PriorityQueue<double[]> orderedDistances = new PriorityQueue<double[]>(CAPACITY, new Comparator<double[]>() {
			    @Override
			    public int compare(double[] c1, double[] c2) {
			    	return Double.compare(c1[1], c2[1]);
			    }
			});
			
			// Klasse aus den Testdaten ermitteln
			pointClass = testData.get(x)[testData.get(x).length - 1];
			for(int i = 0; i < negArrayList.size(); i++) {
				// Abstände berechnen
				double distance = 0;
				for(int l = 0; l < negArrayList.get(i).length;l++){
					distance += Math.pow(testData.get(x)[l] -  negArrayList.get(i)[l], 2);
				}
				distance = Math.sqrt(distance);
				double[] arrDistance = {0, distance};
				orderedDistances.add(arrDistance);
			}
			
			for(int k = 0; k < posArrayList.size(); k++) {
				// Abstände berechnen
				double distance = 0;
				for(int l = 0; l < posArrayList.get(k).length; l++) {
					distance += Math.pow(testData.get(x)[l] - posArrayList.get(k)[l], 2);
				}
				
				distance = Math.sqrt(distance);
				double[] arrDistance = {1, distance};
				orderedDistances.add(arrDistance);
			}
			
			int posCount = 0;
			int negCount = 0;
			int counter = 0;
			
			while (!orderedDistances.isEmpty()) {
	            actualPoint = orderedDistances.poll();
	            //System.out.println("Klasse: " + actualPoint[0] + "/ Distance: " + actualPoint[1]);
	            if(counter < K) {
					if(actualPoint[0] == 0.0) {
						negCount++;
					} else {
						posCount++;
					}	
				}
				counter++;    
			}
			
			if(posCount > negCount) {
				if(pointClass == 1) {
					countRight++;
				}
				//System.out.println("\nKlasse: 1 / negCount: " + negCount + " / posCount " + posCount);
			} else if (posCount == negCount) {
				if(pointClass == 1) {
					countRight++;
				}
				//System.out.println("\nKlasse 1 / negCount: " + negCount + " / posCount " + posCount);
			} else {
				if(pointClass == 0) {
					countRight++;
				}
				//System.out.println("\nKlasse 0 / negCount: " + negCount + " / posCount " + posCount);
			}
			countAllIterations++;
		}
		
		double percent = 100 * countRight / countAllIterations;
		System.out.println("Durchläufe: " + countAllIterations + " / Richtige: " + countRight + " / Prozent richtig: " + percent + " %");
	}
}

