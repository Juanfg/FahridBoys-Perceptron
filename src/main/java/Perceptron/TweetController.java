package Perceptron;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TweetController {
	// Se obtiene el current PATH
	public static Path currentRelativePath = Paths.get("");
	public static String currentPath = currentRelativePath.toAbsolutePath().toString();
	
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(method = RequestMethod.POST, value = "/tweets")
    public Tweet index(
    		@RequestParam(value = "text", required = false) String tweets, 
    		@RequestParam(value = "lat", required = false) String latitud, 
    		@RequestParam(value = "lon", required = false) String longitud) {
    	String tweet = ArffFormat.fixString(tweets);
    	boolean prospect = ArffFormat.formatFile("PredecirTweet.arff", tweet);
    	System.out.println(tweet);
    	
    	Perceptron perceptron = new Perceptron();
    	String hiddenLayers = "11";
    	double learningRate = 0.2;
    	double momentum = 0.8;
    	int trainingTime = 1000;
    	
    	String categoryString = perceptron.simpleWekaSolve(hiddenLayers, learningRate, momentum, trainingTime);
    	String category = categoryString.substring(40);
    	int categoryInteger = 0;
    	
    	String reducedCat = category.substring(category.length()-22, category.length());
    	String finalCat = "";
    	System.out.println(reducedCat);
    	
    	if(reducedCat.contains("seguro_vida")) {
    		categoryInteger = 1;
    		finalCat = "seguro_vida";
    	}
    	if(reducedCat.contains("seguro_hogar")) {
    		categoryInteger = 2;
    		finalCat = "seguro_hogar";
    	}
    	if(reducedCat.contains("seguro_coche")) {
    		categoryInteger = 3;
    		finalCat = "seguro_coche";
    	}
    	if(reducedCat.contains("seguro_moto")) {
    		categoryInteger = 4;
    		finalCat = "seguro_moto";
    	}
    	if(reducedCat.contains("credito_hipotecario")) {
    		categoryInteger = 5;
    		finalCat = "credito_hipotecario";
    	}
    	if(reducedCat.contains("credito_personal")) {
    		categoryInteger = 6;
    		finalCat = "credito_personal";
    	}
    	if(reducedCat.contains("credito_coche")) {
    		categoryInteger = 7;
    		finalCat = "credito_coche";
    	}
    	if(reducedCat.contains("credito_moto")) {
    		categoryInteger = 8;
    		finalCat = "credito_moto";
    	}
    	
    	if(prospect == false) {
    		finalCat = "no_category";
    		categoryInteger = 0;
    	}
    	
    	return new Tweet(counter.incrementAndGet(), categoryInteger, finalCat, prospect, latitud, longitud);
    }
}
