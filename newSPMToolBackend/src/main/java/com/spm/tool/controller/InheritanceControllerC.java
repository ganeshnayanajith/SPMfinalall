package com.spm.tool.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class InheritanceControllerC {
	
	static ArrayList<String> lineList;
	private String filePath;
	private String fileName;
	int noOfAncestors = 0;
	
	public InheritanceControllerC(ArrayList<String> lines, String filePath, String fileName) {

		lineList = new ArrayList<>();
		this.lineList = lines;
		this.filePath = filePath;
		this.fileName = fileName;

	}

	public int calculateComplexityDueToInheritance() {

		System.out.println("************  calculateComplexityDueToInheritance C++   ***********");

		String word, className = "";

		try {

			// Read line by line using lineList
			for (String line : lineList) {
				System.out.println(line);
				
				if(line.contains("class")) {
					if(line.contains(":")) {
						if(line.contains("public") || line.contains("private") || line.contains("protected")) {
							noOfAncestors++;
						}
					}
				}

//				StringTokenizer stringTokenizer = new StringTokenizer(line);
//
//				while (stringTokenizer.hasMoreTokens()) {
//					word = stringTokenizer.nextToken();
//
//					if (word.equals("extends") || word.equals("implements")) {
//						noOfAncestors++;
//						className = stringTokenizer.nextToken();
//						System.out.println(className);
//						break;
//					}
//				}
//
//				break;

			}

			

		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
		
		return noOfAncestors+1;

	}




}
