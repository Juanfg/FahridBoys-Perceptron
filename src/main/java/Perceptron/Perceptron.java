package Perceptron;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;
//import weka.classifiers.Evaluation;

public class Perceptron {
	// Se obtiene el current PATH
	public static Path currentRelativePath = Paths.get("");
	public static String currentPath = currentRelativePath.toAbsolutePath().toString();

	public static Instances training = null;
	public static Instances testing = null;
	public static Instances predicting = null;

	public static Instances getTrainingSet()
	{
		if(training == null)
			training = Perceptron.getSetGeneric(currentPath + "/Tweet.arff");
		
		return training;
	}
	
	public static Instances getTestingSet()
	{
		if(testing == null)
			testing =  Perceptron.getSetGeneric(currentPath + "/EvaluarTweet.arff");
		
		return testing;
	}
	
	public static Instances getPredictingSet() {
		if(predicting == null)
			predicting =  Perceptron.getSetGeneric(currentPath+ "/PredecirTweet.arff");
		
		return predicting;
	}
	
	public static Instances getSetGeneric(String filepath)
	{
		FileReader trainreader;
		try {
			trainreader = new FileReader(filepath);
			Instances train = new Instances(trainreader);
			train.setClassIndex(train.numAttributes()-1);
			
			return train;
		} catch (IOException e) {
			e.printStackTrace();
			throw new NullPointerException();
		}
	}
	
	public String simpleWekaSolve(String hiddenLayers, double learningRate, double momentum, int trainingTime)
	{
		try{
			//Reading training arff or csv file
			Instances training = Perceptron.getTrainingSet();

			//Instance of Perceptron
			MultilayerPerceptron mlp = new MultilayerPerceptron();
			mlp.setLearningRate(learningRate);
			mlp.setMomentum(momentum);
			mlp.setTrainingTime(trainingTime);
			mlp.setHiddenLayers(hiddenLayers);
			mlp.buildClassifier(training);
			
			Instances predictings = Perceptron.getPredictingSet();
			Instances predicteddata = new Instances(predictings);
			predicting = null;
			
			// Prediccion de datos
			double clsLabel = mlp.classifyInstance(predictings.instance(predictings.numInstances() - 1));
			predicteddata.instance(predictings.numInstances() - 1).setClassValue(clsLabel);
			
			// Escritura en archivo
			BufferedWriter writer = new BufferedWriter(new FileWriter(currentPath+"/ResultadosBancomer.arff"));
			writer.write(predicteddata.toString());
			writer.newLine();
			writer.flush();
			writer.close();
			
			// Regresa la categoria que se predijo
			return predicteddata.toString();
		}
		catch(Exception ex){
			ex.printStackTrace();
			throw new IllegalArgumentException();
		}
	}
}
