package Perceptron;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.TreeSet;

public class ArffFormat {
	public static Boolean formatFile(String fileName, String tweet) {
		String[] sentenceWords = tweet.split(" ");
		
		TreeSet<String> words = readWordsPool("Palabras.txt");
		String result = "";
		
		TreeSet<String> mySet = new TreeSet<String>(Arrays.asList(sentenceWords));

        int zero = 0;
        int countOfWords = 0;

        for(String word : words){
        	String nuw = word.replace(" ", "");
        	System.out.println(nuw);
            if(mySet.contains(nuw)){
            	countOfWords++;
                zero = 1;
            }
            else{
                zero = 0;
            }

            result = result.concat(zero + ", ");
        }
        
        result = result.concat("?");
        result = result + System.lineSeparator();
        
        System.out.println(result);
        
        try {
            Files.write(Paths.get(fileName), result.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
        
        if(countOfWords > 1) {
        	return true;
        }
        
        return false;
	}
	
	public static TreeSet<String> readWordsPool(String name) {
		TreeSet<String> pool = new TreeSet<String>();
		
		BufferedReader br = null;
		FileReader fr = null;

		try {
			fr = new FileReader(name);
			br = new BufferedReader(fr);

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				pool.add(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return pool;
	}
	
	public static void wordsPool(String name, TreeSet<String> words){
        String fileName = name + ".txt";

        try {
            // Assume default encoding.
            FileWriter fileWriter = new FileWriter(fileName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for(String word : words){
                bufferedWriter.write(word +" ");
                bufferedWriter.newLine();
            }
            
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println("Error writing to file '" + fileName + "'");
        }
    }
	
	public static String fixString(String s){
		String s2 = s.replaceAll("[^A-Za-z0-9 ]", "");
		return s2.toLowerCase();
	}
}
