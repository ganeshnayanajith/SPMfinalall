package com.spm.tool.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class InheritanceControllerJava {

	static ArrayList<String> lineList;
	private String filePath;
	private String fileName;
	int noOfAncestors = 0;

	public InheritanceControllerJava(ArrayList<String> lines, String filePath, String fileName) {

		lineList = new ArrayList<>();
		this.lineList = lines;
		this.filePath = filePath;
		this.fileName = fileName;

	}

	public int calculateComplexityDueToInheritance() {

		//System.out.println("************  calculateComplexityDueToInheritance   ***********");

		String word, className = "";

		try {

			Boolean found = false;
			// Read line by line using lineList

			for (String line : lineList) {

				if (!found) {

					//System.out.println(line);

					StringTokenizer stringTokenizer = new StringTokenizer(line);

					while (stringTokenizer.hasMoreTokens()) {
						word = stringTokenizer.nextToken();

						//System.out.println(word);

						if (word.equals("extends")) {
							noOfAncestors++;
							className = stringTokenizer.nextToken();
							//System.out.println(className);
							found = !found;
							parentRecursionMultiple(className);
							//break;
						}

						if (word.equals("implements")) {
							noOfAncestors++;
							className = stringTokenizer.nextToken();
							//System.out.println(className);
							if (className.contains(",")) {
								//System.out.println("Multiple implements");
								while(!className.equals("{")) {
									
									 if (className != null && className.length() > 0 && className.charAt(className.length() - 1) == ',') {
										 className = className.substring(0, className.length() - 1);
									    }
									 
									 
									parentRecursionMultiple(className);
									className=stringTokenizer.nextToken();
								}
								
							} else {
								//System.out.println("Single implements");
								parentRecursionMultiple(className);
							}
							found = !found;
							//break;
						}
					} // end while word line

				} // end if found

			} // end for line loop

			

		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
		
		
		//System.out.println(noOfAncestors);
		
		return noOfAncestors+2;

	}
	
	
	public void parentRecursionMultiple(String className) {

		String parentPath = "";
		String lineParent = "";
		String wordParent = "";
		String classNameParent = "";

		//System.out.println("************  parentRecursion   ***********");
		//System.out.println("Number Of Ancestors :" + noOfAncestors);

		try {

			parentPath = filePath + className + ".java";

			//System.out.println(parentPath);

			FileReader filereader = new FileReader(parentPath);
			BufferedReader bufferedreader = new BufferedReader(filereader);
			
			Boolean foundParent = false;

			while ((lineParent = bufferedreader.readLine()) != null) {
				
				
				
				
				
				if (!foundParent) {

					//System.out.println(lineParent);

					StringTokenizer stringTokenizerParent = new StringTokenizer(lineParent);

					while (stringTokenizerParent.hasMoreTokens()) {
						wordParent = stringTokenizerParent.nextToken();

						//System.out.println(word);

						if (wordParent.equals("extends")) {
							noOfAncestors++;
							classNameParent = stringTokenizerParent.nextToken();
							//System.out.println(classNameParent);
							foundParent = !foundParent;
							parentRecursionMultiple(classNameParent);
							//break;
						}

						if (wordParent.equals("implements")) {
							//System.out.println("88888888888888");
							noOfAncestors++;
							classNameParent = stringTokenizerParent.nextToken();
							//System.out.println(classNameParent);
							if (classNameParent.contains(",")) {
								System.out.println("Multiple implements");
								while(!classNameParent.equals("{")) {
									
									 if (classNameParent != null && classNameParent.length() > 0 && classNameParent.charAt(classNameParent.length() - 1) == ',') {
										 classNameParent = classNameParent.substring(0, classNameParent.length() - 1);
									    }
									 
									 
									parentRecursionMultiple(classNameParent);
									classNameParent=stringTokenizerParent.nextToken();
								}
								
							} else {
								//System.out.println("Single implements");
								parentRecursionMultiple(classNameParent);
							}
							foundParent = !foundParent;
							//break;
						}
					} // end while word line

				} // end if found
				
				
				
				
				
				

//				System.out.println(lineParent);
//				StringTokenizer stringTokenizerParent = new StringTokenizer(lineParent);
//
//				while (stringTokenizerParent.hasMoreTokens()) {
//					wordParent = stringTokenizerParent.nextToken();
//
//					if (wordParent.equals("extends") || wordParent.equals("implements")) {
//						noOfAncestors++;
//						classNameParent = stringTokenizerParent.nextToken();
//						System.out.println(classNameParent);
//
//						parentRecursion(classNameParent);
//
//						break;
//					}
//				}

				//break;

			}//end while line loop

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void parentRecursion(String className) {

		String parentPath = "";
		String lineParent = "";
		String wordParent = "";
		String classNameParent = "";

		//System.out.println("************  parentRecursion   ***********");
		//System.out.println("Number Of Ancestors :" + noOfAncestors);

		try {

			parentPath = filePath + className + ".java";

			//System.out.println(parentPath);

			FileReader filereader = new FileReader(parentPath);
			BufferedReader bufferedreader = new BufferedReader(filereader);

			while ((lineParent = bufferedreader.readLine()) != null) {

				//System.out.println(lineParent);
				StringTokenizer stringTokenizerParent = new StringTokenizer(lineParent);

				while (stringTokenizerParent.hasMoreTokens()) {
					wordParent = stringTokenizerParent.nextToken();

					if (wordParent.equals("extends") || wordParent.equals("implements")) {
						noOfAncestors++;
						classNameParent = stringTokenizerParent.nextToken();
						//System.out.println(classNameParent);

						parentRecursion(classNameParent);

						break;
					}
				}

				break;

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
