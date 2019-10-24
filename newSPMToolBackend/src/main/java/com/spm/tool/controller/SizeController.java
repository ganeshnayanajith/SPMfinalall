package com.spm.tool.controller;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.spm.tool.model.StatementLine;

public class SizeController {
	
	

    
    static ArrayList<String> lineList;
    static ArrayList<StatementLine> statementLines;
//	private String filePath;
//	private String fileName;


	public SizeController(ArrayList<String> lines) {
		lineList = new ArrayList<>();
		this.lineList = lines;
	}
    
	
	public ArrayList<StatementLine> calculateComplexityDueSize() {
		
		
		
		statementLines=new ArrayList<StatementLine>();
		
		//System.out.println("************  calculateComplexityDueSize   ***********");

		int lineNumber=0;
		
		
		

        ArrayList<String> variableList = new ArrayList<String>();
        ArrayList<String> methodList = new ArrayList<String>();
        
        
        
        
		
		for (String line : lineList) {
			
			
	
			
			lineNumber++;
			
			int arithConstVal = 0, relConstVal = 0, logicalConstVal = 0, bitConstVal = 0, misceConstVal = 0, assignConstVal = 0, keyConstVal = 0, maniConstVal = 0,
	                stringConstVal = 0, classConstVal = 0, specialConstVal = 0, methodConstVal = 0, objConstVal = 0, arrayConstVal = 0, varConstVal = 0,
	                numericConstVal = 0, pointerConstVal = 0, refConstVal = 0;

	        Boolean quotes = false, chkEquals = false, chkNew = false, arrayOpenCheck = false, arrayCloseCheck = false, chkVariable = false,
	                chkSemiColon = false;
			
			
			
			
			
			  String splitter[] = line.split(" ");
	            ArrayList<String> list = new ArrayList<String>();

	            list.add(line);

	            StringTokenizer stringTokenizer = new StringTokenizer(line);
	            while (stringTokenizer.hasMoreTokens()) {
	                String word = stringTokenizer.nextToken();

	                if (word.equals("long") || word.equals("(long") || word.equals("int") || word.equals("(int") || word.equals("double") || word.equals("(double") || word.equals("String") ||
	                word.equals("(String") || word.equals("float") || word.equals("(float") || word.equals("char") || word.equals("(char") || word.equals("boolean") || word.equals("(boolean") ||
	                word.equals("byte") || word.equals("(byte")) {
	                    String valIntoArray = stringTokenizer.nextToken();
	                    if(!valIntoArray.contains("(")){
	                        variableList.add(valIntoArray);
	                        System.out.println(variableList);
	                    }


	                }
	            }



	            if (line.contains("public") || line.contains("private") || line.contains("static")) {
	                if (!line.contains("class")) {
	                    if (line.contains("(")) {
	                        //methodConstVal++;
	                        StringTokenizer methodName = new StringTokenizer(line);
	                        while(methodName.hasMoreTokens()){
	                            String methodNameName = methodName.nextToken();

	                            if(methodNameName.contains("long") || methodNameName.contains("double") || methodNameName.contains("int") || methodNameName.contains("float") || methodNameName.contains("char") ||
	                            methodNameName.contains("boolean") || methodNameName.contains("byte") ||methodNameName.contains("double")){
	                                String mName = methodName.nextToken();
	                                String splitMethod[] = mName.split("\\(");
	                                methodList.add(splitMethod[0]);

	                            }
	                        }
	                        if (line.contains("byte") || line.contains("long") || line.contains("char") || line.contains("boolean")||
	                                line.contains("short") || line.contains("int") || line.contains("float") || line.contains("double")) {
	                            //varConstVal++;



	                            StringTokenizer methodToken = new StringTokenizer(line);
	                            while (methodToken.hasMoreTokens()){
	                                String methodParameter = methodToken.nextToken();



	                                if ( methodParameter.contains("(long") || methodParameter.contains("(int") || methodParameter.contains("(double") || methodParameter.contains("(boolean") ||
	                                methodParameter.contains("(char") || methodParameter.contains("(byte") || methodParameter.contains("(float") || methodParameter.contains("(String") ||
	                                methodParameter.contains("(short")) {
	                                    String valIntoArrayTwo = methodToken.nextToken();

	                                    String bracketSplit[] = valIntoArrayTwo.split("\\)");

	                                        variableList.add(bracketSplit[0]);
	                                        System.out.println(bracketSplit[0]);
	                                        System.out.println(variableList);

	                                }
	                            }

	                        }
	                    }
	                }
	            }



	            if(line.contains("System.out.println(")){
	                misceConstVal++;
	                misceConstVal++;
	                keyConstVal++;
	                keyConstVal++;
	                keyConstVal++;
	            }

	            if(line.contains("Exception") && (!line.contains("throws") &&(!quotes))){
	                keyConstVal++;
	                keyConstVal++;
	            }

	            if(line.contains("Exception") && (line.contains("throws") && (!quotes))){
	                methodConstVal++;
	                keyConstVal++;
	            }

	            if(line.contains("();")){
	                methodConstVal++;
	            }


	            for (String arrVal : splitter) {
	                System.out.println(arrVal);
	                //System.out.println(line);
	                if (arrVal.contains("\"")) {
	                    quotes = !quotes;
	                }
	                if (!quotes) {
	                    /*Check Arithmetic Operators*/
	                    if (arrVal.contains("++") || arrVal.equals("--") || arrVal.contains("+") || arrVal.contains("-") || arrVal.equals("*") || arrVal.equals("/") ||
	                            arrVal.contains("%")) {
	                        arithConstVal++;
	                        /*Check Special Constants*/
	                    }  if (arrVal.contains("new") || arrVal.contains("throw") || arrVal.contains("throws") || arrVal.contains("delete") || arrVal.contains("&")) {
	                        specialConstVal += 2;

	                        if(arrVal.equals("throw")){
	                            keyConstVal++;
	                        }
	                        /*Check Relation Operators*/
	                    }  if (arrVal.contains("==") || arrVal.contains("!=") || arrVal.contains(">") || arrVal.contains("<") || arrVal.contains(">=") || arrVal.contains("<=")) {
	                        relConstVal++;
	                        /*Check Logical Operators*/
	                    }  if (arrVal.contains("&&") || arrVal.equals("||") || arrVal.equals("!")) {
	                        logicalConstVal++;
	                        /*Check Bitwise Operators*/
	                    }  if (arrVal.equals("|") || arrVal.contains("^") || arrVal.contains("~") || arrVal.contains(">>") || arrVal.contains("<<") || arrVal.contains(">>>") ||
	                            arrVal.contains("<<<")) {
	                        bitConstVal++;
	                        /*Check Miscellaneous operators */
	                    }  if (arrVal.contains(",") || arrVal.contains("->") || arrVal.contains(".") || arrVal.contains("::")) {
	                        misceConstVal++;
	                        /*Check Assignment operators */
	                    }  if (arrVal.equals("+=") || arrVal.equals("-=") || arrVal.equals("*=") || arrVal.equals("/=") || arrVal.equals("=") ||
	                            arrVal.equals(">>>=") || arrVal.equals("|=") || arrVal.equals("&=") || arrVal.equals("%=") || arrVal.equals("<<=") || arrVal.equals(">>=") ||
	                            arrVal.equals("^=")) {
	                        assignConstVal++;
	                        /*Check Keywords */
	                    }  if (arrVal.contains("void") || arrVal.contains("double") || arrVal.contains("int") || arrVal.contains("float")  || arrVal.contains("printf") ||
	                             arrVal.contains("cout") || arrVal.contains("cin") || arrVal.equals("if") || arrVal.equals("for") ||
	                            arrVal.contains("do") || arrVal.contains("switch") || arrVal.contains("case") || arrVal.contains("abstract") || arrVal.contains("byte") ||
	                            arrVal.contains("extends") || arrVal.contains("import") || arrVal.contains("long") || arrVal.contains("private") || arrVal.contains("short") ||
	                            arrVal.contains("volatile") || arrVal.contains("null") || arrVal.contains("assert") || arrVal.contains("const") || arrVal.contains("final") ||
	                            arrVal.contains("goto") || arrVal.contains("instanceof") || arrVal.contains("native") || arrVal.contains("protected") || arrVal.contains("synchronized") ||
	                            arrVal.contains("transient") || arrVal.contains("boolean") || arrVal.contains("catch") || arrVal.contains("continue") || arrVal.contains("finally") ||
	                            arrVal.contains("strictfp") || arrVal.contains("this") || arrVal.contains("true") || arrVal.contains("false") || arrVal.contains("break") || arrVal.contains("char") ||
	                            arrVal.contains("default") || arrVal.contains("enum") || arrVal.contains("implements") || arrVal.contains("interface") || arrVal.contains("package") ||
	                            arrVal.contains("super") || arrVal.contains("do") || arrVal.contains("main") || arrVal.contains("String")) {

	                        keyConstVal++;
	                        /*Check Manipulators */
	                    }  if (arrVal.contains("endl") || arrVal.contains("\\n") || arrVal.contains("\\t") || arrVal.contains("ends") || arrVal.contains("flush") || arrVal.contains("dec") ||
	                            arrVal.contains("hex") || arrVal.contains("oct") || arrVal.contains("resetiosflags(") || arrVal.contains("setbase(") || arrVal.contains("setfill(") ||
	                            arrVal.contains("setiosflags(") || arrVal.contains("setprecision(") || arrVal.contains("setw(")) {
	                        maniConstVal++;

	                    }  if (arrVal.contains("1") || arrVal.contains("2") || arrVal.contains("3") || arrVal.contains("4") || arrVal.contains("5") || arrVal.contains("6") || arrVal.contains("7")
	                            || arrVal.contains("8") || arrVal.contains("9") || arrVal.contains("0")) {

	                        numericConstVal++;
	                    }

	                    if(arrVal.equals("main(String")){
	                        keyConstVal++;
	                    }

	                    //check while loop

	                    if (line.contains("while") && line.contains("{")) {
	                        keyConstVal++;
	                    }


	                    /*Check Objects */
	                    if (arrVal.contains("=")) {
	                        chkEquals = true;
	                    }
	                    if (arrVal.contains("new")) {
	                        chkNew = true;
	                    }
	                    if (chkEquals == true && chkNew == true) {
	                        objConstVal++;

	                        chkEquals = false;
	                        chkNew = false;
	                    }
	                    /*Check Arrays */
	                    if (arrVal.contains("[")) {
	                        arrayOpenCheck = true;
	                    }
	                    if (arrVal.contains("]")) {
	                        arrayCloseCheck = true;
	                    }
	                    if (arrayOpenCheck == true && arrayCloseCheck == true) {
	                        arrayConstVal++;
	                        arrayOpenCheck = false;
	                        arrayCloseCheck = false;
	                    }
	                    /*Check Variables */
	                    if (arrVal.contains("byte") || arrVal.contains("short") || arrVal.contains("int") || arrVal.contains("long") || arrVal.contains("float") ||
	                            arrVal.contains("double") || arrVal.contains("char") || arrVal.contains("boolean")) {
	                        chkVariable = true;
	                    }

	                    if (chkVariable == true && line.contains("*")) {
	                        pointerConstVal += 2;
	                        chkVariable = false;
	                    }

	                    if (chkVariable == true && line.contains("&")) {
	                        refConstVal += 2;
	                        chkVariable = false;
	                    }
//	                    if(arrVal.contains("*")){
//	                        chkPointer = true;
//	                    }
	                    if (line.contains(";")) {
	                        chkSemiColon = true;
	                    }
	                    if (chkVariable == true && chkSemiColon == true) {
	                        //varConstVal++;
//	                        String value = line;
//	                       String splitVariable[] = value.split(" ");
	//
	                        chkVariable = false;
	                        chkSemiColon = false;
	                    }                                   /*Check Pointer */

	                }//endIfQuotes


	                for (String value : variableList) {
	                    if (arrVal.equals(value) || arrVal.equals("(" + value) || arrVal.equals(value + "++") || arrVal.equals("(("+value) ||
	                            arrVal.equals(value+")") || arrVal.equals(value+";") ||
	                    arrVal.equals(value+"++)") || arrVal.equals("("+value+")") || arrVal.contains("("+value) || arrVal.contains(value+"++")) {
	                        varConstVal++;
	                    }


	                }

	                for(String methodVal : methodList){
	                    if(arrVal.contains(methodVal+"(") || arrVal.equals("(("+methodVal) || arrVal.equals(methodVal+")")) {
	                        varConstVal++;
	                        System.out.println(methodList);
	                    }
	                }
	            } //endFor



	            /*Check Methods */


	            /*Check String */
	            Pattern p = Pattern.compile("\"([^\"]*)\"");
	            Matcher m = p.matcher(line);
	            while (m.find()) {
	                System.out.println(m.group(1));
	                stringConstVal++;
	            }
			
			
			
			
			
	            
	            int val = pointerConstVal+refConstVal+specialConstVal+arithConstVal+relConstVal+logicalConstVal+bitConstVal+misceConstVal+assignConstVal+keyConstVal+maniConstVal+
	                    stringConstVal+classConstVal+objConstVal+varConstVal+arrayConstVal+numericConstVal+methodConstVal;
	    		
				
	    		StatementLine statementLine=new StatementLine(lineNumber, val);
	    		
	    		statementLines.add(statementLine);
			
			
			
			
    		
			
		}//end for loop line
	
	
	
		
		
		return statementLines;
		/* Display Values */

       
		
	}//end calc method
    
	
	
	
                               

}
