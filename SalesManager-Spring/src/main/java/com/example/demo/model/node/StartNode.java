package com.example.demo.model.node;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Nodeを起動させるクラス
 * @author yuta
 *
 */
public class StartNode extends Thread{
	
	private static final Logger logger = LoggerFactory.getLogger(StartNode.class);
	
	public static void main(String[] args) {
		
		try {
	    	ProcessBuilder pb = new ProcessBuilder
	    	("node", "../../../../../pleiades/2023-03-java/workspace/"
	    	+ "SalesManager-Spring/src/main/resources/static/node/main.js");
	    	Process p = pb.start();

	    	BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
	    	String line;
	    	while ((line = reader.readLine()) != null) {
	    		System.out.println(line);
	    	}
	    	
	         BufferedReader errorReader = new BufferedReader(new InputStreamReader(p.getErrorStream()));

	        // エラーメッセージを取り込んで表示
	        StringBuilder errorMessage = new StringBuilder();
	        String errorLine;
	        while ((errorLine = errorReader.readLine()) != null) {
	            errorMessage.append(errorLine).append("\n");
	        }
	        if (errorMessage.length() > 0) {
	            System.out.println("エラーメッセージ:\n" + errorMessage.toString());
	        }
	    	
	    	
	    	int exitCode = p.waitFor();
	    
	    	System.out.println(exitCode);
	    	System.out.println("処理終了");
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}
	
	public void run() {
		
		try {
		    	ProcessBuilder pb = new ProcessBuilder
		    	("node", "../../../pleaiades/2023-3-java/workspace/"
		    	+ "SalesManager-Spring/src/main/resources/static/node/main.js");
		    	Process p = pb.start();

		    	BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		    	String line;
		    	while ((line = reader.readLine()) != null) {
		    		logger.info(line);
		    	}
		    
		    	int exitCode = p.waitFor();
		    
		    	logger.info(""+exitCode);
		    	
			} catch (Exception e) {
				e.printStackTrace();
			
			}
	}
}
