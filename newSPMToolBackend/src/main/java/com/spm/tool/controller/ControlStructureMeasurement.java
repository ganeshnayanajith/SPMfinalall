
package com.spm.tool.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import com.spm.tool.model.*;


public class ControlStructureMeasurement {
	
	static ArrayList<String> lineList;
	public ControlStructureMeasurement(ArrayList<String> lines) {
		
		lineList = new ArrayList<>();
		this.lineList = lines;
	}

    public static ArrayList<StatementLine> calculateComplexityByType(ArrayList<Method> functionList) {

        int x;
        int y;
        int z;
        int a;
        int start;
        int end;
        int switchStart, switchEnd;
        int numberOfCases;
        String[] lineToChars;
        ArrayList<String> bracketCheckSwitch = new ArrayList<>();
        boolean isLinePresent;

        int Ctc;

        ArrayList<StatementLine> StatementList = new ArrayList<>();

        String[] divideBySpaces;
        if (functionList.size() == 0) {
            return null;
        }

        for (x = 0; x < functionList.size(); x++) {
            Method f1 = functionList.get(x);
            start = f1.getStartLine();
            end = f1.getEndLine();

            while (start != end + 1) {
                Ctc = 0;
                numberOfCases = 0;
                System.out.println("Line number " + start + " : " + lineList.get(start));

                if (lineList.get(start).contains("if") && lineList.get(start).contains("(") && lineList.get(start).contains(")")) {
                    System.out.println("Line number " + start + " has a if statement");
                    Ctc++;

                    divideBySpaces = lineList.get(start).split("\\s");
                    for (y = 0; y < divideBySpaces.length; y++) {
                    	System.out.println(divideBySpaces[y]);
                        if (divideBySpaces[y].contains("&&") || divideBySpaces[y].contains("||") || divideBySpaces[y].contains("&") || divideBySpaces[y].contains("|")) {
                            Ctc++;
                        }
                    }
                }

                if (lineList.get(start).contains("for") && lineList.get(start).contains("(") && lineList.get(start).contains(")")) {
                    System.out.println("Line number " + start + " has a for statement");
                    Ctc = Ctc + 2;

                    divideBySpaces = lineList.get(start).split("\\s");
                    for (y = 0; y < divideBySpaces.length; y++) {
                        if (divideBySpaces[y].contains("&&") || divideBySpaces[y].contains("||") || divideBySpaces[y].contains("&") || divideBySpaces[y].contains("|")) {
                        	Ctc = Ctc + 2;
                        }
                    }
                }
                
                if (lineList.get(start).contains("do") && lineList.get(start).contains("{")) {
                    System.out.println("Line number " + start + " has a do-while statement");
                    Ctc = Ctc + 2;

                    divideBySpaces = lineList.get(start).split("\\s");
                    for (y = 0; y < divideBySpaces.length; y++) {
                        if (divideBySpaces[y].contains("&&") || divideBySpaces[y].contains("||") || divideBySpaces[y].contains("&") || divideBySpaces[y].contains("|")) {
                        	Ctc = Ctc + 2;
                        }
                    }
                }
                
                
                if (lineList.get(start).contains("}") && lineList.get(start).contains("while")  && lineList.get(start).contains("(") && lineList.get(start).contains(")")) {
                	
                	divideBySpaces = lineList.get(start).split("\\s");
                    for (y = 0; y < divideBySpaces.length; y++) {
                        if (divideBySpaces[y].contains("&&") || divideBySpaces[y].contains("||") || divideBySpaces[y].contains("&") || divideBySpaces[y].contains("|")) {
                        	Ctc = Ctc + 2;
                        }
                    }
                }

                if (lineList.get(start).contains("while") && lineList.get(start).contains("(") && lineList.get(start).contains(")") && !(lineList.contains("}"))) {
                    System.out.println("Line number " + start + " has a While statement");
                    Ctc = Ctc + 2;

                    divideBySpaces = lineList.get(start).split("\\s");
                    for (y = 0; y < divideBySpaces.length; y++) {
                        if (divideBySpaces[y].contains("&&") || divideBySpaces[y].contains("||") || divideBySpaces[y].contains("&") || divideBySpaces[y].contains("|")) {
                        	Ctc = Ctc + 2;
                        }
                    }
                }

               
                
                if (lineList.get(start).contains("catch") && lineList.get(start).contains("(") && lineList.get(start).contains(")") ) {
                    System.out.println("Line number " + start + " has a Catch statement");
                    Ctc++;

                }

                if (lineList.get(start).contains("switch") && lineList.get(start).contains("(") && lineList.get(start).contains(")") && lineList.get(start).contains("{")) {
                    System.out.println("Line number " + start + " has a Switch statement");

                    divideBySpaces = lineList.get(start).split("\\s");
                    for (y = 0; y < divideBySpaces.length; y++) {
                        if (divideBySpaces[y].contains("{")) {
                            bracketCheckSwitch.add("{");
                        }
                    }

                    for (z = start + 1; z < end; z++) {
                    	//splitting into characters
                        lineToChars = lineList.get(z).split("(?!^)");
                        for (a = 0; a < lineToChars.length; a++) {

                            if (lineToChars[a].contains("{")) {
                                bracketCheckSwitch.add("{");
                                System.out.println("{");
                                System.out.println(bracketCheckSwitch.toString());
                            }
                            if (lineToChars[a].contains("}")) {
                                bracketCheckSwitch.remove(bracketCheckSwitch.size() - 1);
                                System.out.println("}");
                                System.out.println(bracketCheckSwitch.toString());
                            }

                        }
                        if (bracketCheckSwitch.toString() == "[]") {
                            break;
                        }
                    }
                    System.out.println("Switch is from " + start + " to " + z);
                    switchStart = start;
                    switchEnd = z;

                    while (switchStart < switchEnd + 1) {
                        if (lineList.get(switchStart).contains("case")) {
                            numberOfCases++;
                        }
                        switchStart++;
                    }

                    Ctc = Ctc + numberOfCases;
                }

                StatementLine s1 = new StatementLine(start, Ctc);
                StatementList.add(s1);
                start++;
            }
            System.out.println("");
        }

        ArrayList<StatementLine> displayCtcList = new ArrayList<>();

        for (y = 0; y < lineList.size(); y++) {
            isLinePresent = true;
            for (a = 0; a < StatementList.size(); a++) {
                if (StatementList.get(a).getLineNumber() == y) {
                    isLinePresent = false;
                }
            }
            if (isLinePresent) {
                StatementList.add(new StatementLine(y, 0));
            }
        }

        for (y = 1; y <= StatementList.size(); y++) {
            for (z = 0; z < StatementList.size(); z++) {
                if (StatementList.get(z).getLineNumber() + 1 == y) {
                    StatementLine s1 = new StatementLine(y, StatementList.get(z).getComplexity());
                    displayCtcList.add(s1);
                }
            }
        }

        return displayCtcList;

    }

    //sholud return statement arraylist
    public static ArrayList<StatementLine> calculateComplexityByNestingControlStructure(ArrayList<Method> functionList) {



        
        ArrayList<StatementLine> statementLines = new ArrayList<StatementLine>();
        for(Method method:functionList) {
			
			int bracketCount = 0;
			boolean ifStatus = false;
			boolean firstBracket = false;
			boolean firstIf = false;
			int ifPoints = 1;
			int previousIfIndex = 0;
			int previousIfLine = 0;
			boolean elseStatus = false;
			int preIndex=0;
			int preLine=0;
			boolean preNested = false;

			int ifCount = 0;
			int x = 0;
			int prev = 0;
			String prevLine = null;
			for(int i=method.getStartLine()-1;i<method.getEndLine();i++) {
				
				//if(!lines.get(i).replace(" ", "").substring(0, 2).equals("//")) {
			
					String[] parts = lineList.get(i).split("(?<!^)\\b");
					
	            	for (String part : parts) {
	            	
	            		
	            		String[] spaceSplit = part.split(" ");
	            		
	            		for (String spacePart: spaceSplit) {
	            		
	            			String word = spacePart.replace(" ", "");

	            			if(word.contains("if")||word.contains("for")||word.contains("while")||word.contains("default")||word.contains("switch")) {
	            				
	            				if(ifStatus||elseStatus) {
	            					
	            					
	            					if(ifCount == 1 && !preNested) {
	            						
	            						if(!part.equals(prevLine)) {
	            							StatementLine statementLine = new StatementLine(previousIfLine-1,ifPoints);
		            						statementLines.add(previousIfIndex-1,statementLine);
	            						}else {
	            							StatementLine statementLine = new StatementLine(previousIfLine,ifPoints);
		            						statementLines.add(previousIfIndex,statementLine);
		            						
	            						}
	            						preNested = true;
	            					}
	                				StatementLine statementLineNew = new StatementLine(i+1,++ifPoints);
	                				statementLines.add(x,statementLineNew);
	            				}
	            				if(!elseStatus) {
	            					ifStatus = true;
	            				}
	            				firstBracket = true;
	            				prevLine = part;
	            			}else if (ifStatus){
	            				if(word.contains("{")) {
	            					
	            					if(firstBracket) {
	            						
		            						previousIfIndex = x;
		            						previousIfLine = i+1;
	            						
	            						firstBracket = false;
	            						ifCount++;
	            					}
	            					if(bracketCount==0) {
	            						preNested = false;
	            						preIndex = previousIfIndex;
	            						preLine = previousIfLine;
	            					}
	            					bracketCount++;
	            				}else if(word.contains("}")){
	            					bracketCount--;
	            					if(bracketCount==0) {
	            						ifStatus=false;
	            						ifPoints = 1;
	            						ifCount = 0;
	            					}
	            				}
	            				
	            			}else if(word.contains("else")) {
	            			
	            				elseStatus=true;
	            				firstBracket = true;
	            			
	            				System.out.println(bracketCount);
	            			}else if(elseStatus) {
	            				
	            				if(word.contains("{")) {
	            					if(firstBracket) {
	            						System.out.println(i+1);
	            						previousIfIndex = preIndex;
	            						previousIfLine = preLine;
	            						firstBracket = false;
	            						ifCount++;
	            					}
	            					bracketCount++;
	            				}else if(word.contains("}")){
	            					System.out.println(i+1);
	            					bracketCount--;
	            					if(bracketCount==0) {
	            						
	            						elseStatus=false;
	            						ifPoints = 1;
	            						ifCount = 0;
	            					}
	            				}
	            			}
	            			
	    					
	            		}
	            	}
	            	

	            		
        				
        				
	           	}
				
	        }
        ArrayList<StatementLine> statementList = new ArrayList<StatementLine>();
        for(int x=1;x<=lineList.size();x++) {
        	boolean status = false;
        	for(StatementLine state:statementLines) {
        		if(state.getLineNumber()==x) {
        			status = true;
        			StatementLine st =new StatementLine(x, state.getComplexity());
        			statementList.add(st);
        		}
        	}
        	if(!status) {
        		StatementLine st =new StatementLine(x, 0);
    			statementList.add(st);
        	}
        }
        return statementList;
    }

}
