package Perceptron;

import java.io.FileReader;
import java.io.IOException;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;

public class Perceptron {

	static Instances training = null;
	static Instances testing = null;

	public static Instances getTrainingSet()
	{
		if(training == null)
			training = Perceptron.getSetGeneric("C:\\Users\\juanf\\Desktop\\Tweet.arff");
		
		return training;
	}
	
	public static Instances getTestingSet()
	{
		if(testing == null)
			testing =  Perceptron.getSetGeneric("C:\\Users\\juanf\\Desktop\\EvaluarTweet.arff");
		
		return testing;
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
	
	public double simpleWekaTrain(String hiddenLayers, double learningRate, double momentum, int trainingTime)
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
			
			Instances testing = Perceptron.getTestingSet();
			Evaluation eval = new Evaluation(testing);
		    eval.evaluateModel(mlp, testing);
		    System.out.println(eval);
		    return (1 - eval.errorRate()); //Printing Training Mean root squared Error
		}
		catch(Exception ex){
			ex.printStackTrace();
			throw new IllegalArgumentException();
		}
	}
}
