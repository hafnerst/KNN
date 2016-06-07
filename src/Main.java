import java.util.ArrayList;

public class Main {
	

	public static void main(String[] args) {
		/*
		double[] negPoint1 = {-1,-1};
		double[] negPoint2 = {-2,-2};
		double[] posPoint1 = {1,1};
		double[] posPoint2 = {2,2};
		
		double[] actualPoint1 = {0,0,0};
		double[] actualPoint2 = {3,2,1};
		double[] actualPoint3 = {-1,0,0};
		
		ArrayList<double[]> negArrayList = new ArrayList<double[]>();
		ArrayList<double[]> posArrayList = new ArrayList<double[]>();
		ArrayList<double[]> testData = new ArrayList<double[]>();
		
		negArrayList.add(negPoint1);
		negArrayList.add(negPoint2);
		posArrayList.add(posPoint1);
		posArrayList.add(posPoint2);
		testData.add(actualPoint1);
		testData.add(actualPoint2);
		testData.add(actualPoint3);*/
		
		//TrainingDataFileReader trainingDatafr = new TrainingDataFileReader();
		//TestDataFileReader testDatafr = new TestDataFileReader();
		
		NormTrainingDataFR normTrainingDatafr = new NormTrainingDataFR();
		NormTestDataFR normTestDatafr = new NormTestDataFR();
		
		//KNN knn = new KNN(negArrayList, posArrayList, testData);
		//knn.runKNN();
		
		//KNN knn2 = new KNN(trainingDatafr.getNegArrayList(), trainingDatafr.getPosArrayList(), testDatafr.getTestDataArrayList());
		//knn2.runKNN();
		
		//KNN knn2 = new KNN(normTrainingDatafr.getNegArrayList(), normTrainingDatafr.getPosArrayList(), normTestDatafr.getTestDataArrayList());
		//knn2.runKNN();
		
		//Größe des Arrays steuert wie viel k-Nachbarn betrachtet werden sollen
		double errorRates[] = new double[30];
		
		for(int i = 0; i<errorRates.length; i++)
		{
			CrossValidation cv = new CrossValidation(normTrainingDatafr.getNegArrayList(), normTrainingDatafr.getPosArrayList(), normTestDatafr.getTestDataArrayList(), i+1);
			errorRates[i] = (100-cv.runKNN());
		}
		
		double minError = 100.0;
		int indexMinError = 0;
		for(int i = 0; i<errorRates.length; i++) 
		{
			if(errorRates[i] < minError)
			{
				minError = errorRates[i];
				indexMinError = i;
			}
		}
		
		System.out.println("optimale Zahl nächster Nachbarn k ist:");
		System.out.println("bei k: "+(indexMinError+1)+" mit einer Fehlerrate von: "+errorRates[indexMinError]);
	}
}
