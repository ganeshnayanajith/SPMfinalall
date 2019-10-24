package com.spm.tool.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spm.tool.model.Complexity;
import com.spm.tool.model.Method;
import com.spm.tool.model.StatementLine;

@RequestMapping("/file")
@RestController
public class FileHandling {

	private static String UPLOAD_DIR = "upload";
	ArrayList<StatementLine> statementLinesCs=new ArrayList<StatementLine>();
    ArrayList<Method> recursiveOutput = new ArrayList<>();
	ArrayList<StatementLine> nestingCtrlStructureOutput = new ArrayList<>();
	ArrayList<StatementLine> typeCtrlStructureOutput = new ArrayList<>();
	int Ci = 0;

	@CrossOrigin("http://localhost:3000")
	@PostMapping("/upload")
	public ArrayList<Complexity> upload(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
		
		
		ArrayList<Method> methods;
		String line = null;
		String[][][] list;
		String Path,fileName,fullPath,filepath="",exType="";
		String[] potion;
		String[] extension;
		int j=-1;
		
		
		
//		ArrayList<String> lineList = new ArrayList<>();
		ArrayList<String> inheritancelineList = new ArrayList<>();

		//System.out.println("======	Starting	=========");
		try {

			fullPath = file.getOriginalFilename().toString();
			fullPath = fullPath.replace("\\", "\\\\");
			//System.out.println(fullPath);
			potion = fullPath.split("\\\\");
			
			for (int i = 0; i < potion.length; i++) {
				if(i%2==0) {
					//System.out.println(potion[i]);
					if(i!=(potion.length-1)) {
						filepath+=potion[i];
						filepath+="\\\\";
					}
				}
			}
			
			//System.out.println("======");
			//System.out.println(filepath);
			
//			fileName = file.getOriginalFilename().toString();
			fileName = potion[potion.length-1];
			//System.out.println(fileName);
			Path = request.getServletContext().getRealPath("")+UPLOAD_DIR+File.separator+fileName;
			saveFile(file.getInputStream(),Path);
			//System.out.println(Path);
			
			extension=fileName.split("\\.");
			exType=extension[1];
			//System.out.println(exType);
			
//			String line = null;

	        try {
	            // FileReader reads text files in the default encoding.
	            FileReader fileReader = 
	                new FileReader(Path);

	            // Always wrap FileReader in BufferedReader.
	            BufferedReader bufferedReader = 
	                new BufferedReader(fileReader);
	            boolean classStatus = false;
	            
	            while((line = bufferedReader.readLine()) != null) {
	            	
	            	
	            	inheritancelineList.add(line);
	        		//add the code lines to the array list
//	            	if(!classStatus) {
//		            	if(line.contains(" class ")) {
//		            		classStatus = true;
//		            		inheritancelineList.add(line);
//		            	}
//	            	}else {
//	            		inheritancelineList.add(line);
//	            		
//	            	}
	        	}
	        }catch(Exception ex) {
	        	ex.printStackTrace();
	        }
			
	        
	       
	       
	        
			FindMethodsController findMethods = new FindMethodsController();
			
			RecursiveController recursive = new RecursiveController();
			
			ControlStructureMeasurement structures = new ControlStructureMeasurement(inheritancelineList);
			
			
			
			//System.out.println("*****************	SizeController  ****************");
			
			SizeController sizeController=new SizeController(inheritancelineList);
			statementLinesCs=sizeController.calculateComplexityDueSize();
			
//			for (StatementLine statementLineCs : statementLinesCs) {
//				System.out.println(statementLineCs.getLineNumber()+" : "+statementLineCs.getComplexity());
//			}
			

			
			//System.out.println("*****************	InheritanceController  ****************");
			
			if(exType.equals("java")) {
				InheritanceControllerJava inheritanceControllerJava=new InheritanceControllerJava(inheritancelineList,filepath,fileName);
				Ci=inheritanceControllerJava.calculateComplexityDueToInheritance();
			}
			
			if(exType.equals("cpp")) {
				InheritanceControllerC inheritanceControllerC=new InheritanceControllerC(inheritancelineList, filepath, fileName);
				Ci=inheritanceControllerC.calculateComplexityDueToInheritance();
			}
			
			
			
			
			
			
			methods = findMethods.getMethods(Path, fileName);
						
			typeCtrlStructureOutput = structures.calculateComplexityByType(methods);
			
			nestingCtrlStructureOutput = structures.calculateComplexityByNestingControlStructure(methods);
				
			recursiveOutput = recursive.checkRecurtion(methods, inheritancelineList);
			
			
			
			int i=1;
			
			for(Complexity c:this.getComplexity(inheritancelineList)) {
				if(i==1) {
					System.out.println("LineNo\tCs\tCnc\tCtc\tCi\tTW\tCps\tCr");
				}
				System.out.println(i++ +"\t"+c.getCs()+"\t"+c.getCnc()+"\t"+c.getCtc()+"\t"+c.getCi()+"\t"+c.getTW()+"\t"+c.getCps()+"\t"+c.getCr());
			}
			
			displayMethods(methods);
			
			//return "http://localhost:8080/upload/"+fileName;
		}catch(Exception e) {
			System.out.println(e);
			//return e.getMessage().toString();
		}
		
		return this.getComplexity(inheritancelineList);
	}

	private void saveFile(InputStream inputStream,String path) {
		try {
			OutputStream outputStream= new FileOutputStream(new File(path));
			int read = 0;
			byte[] bytes = new byte[1024];
			while((read = inputStream.read(bytes))!= -1) {
				outputStream.write(bytes,0,read);
			}
			outputStream.flush();
			outputStream.close();
					
		}catch(Exception e) {
			
		}
	}
	
	public void displayMethods(ArrayList<Method> method) {
		for(Method m:method) {
			System.out.println("---"+m.getName()+"-----Starts at: "+m.getStartLine()+"--------Ends at: "+m.getEndLine());
		}
	}
	
	public ArrayList<Complexity> getComplexity(ArrayList<String> lines) {
		
		ArrayList<Complexity> complexity = new ArrayList<>();
		
		for(String line:lines) {
			Complexity com = new Complexity();
			com.setLine(line);
			complexity.add(com);
			
		}
		return addNestedControlStructure(complexity);
	}
	
	public ArrayList<Complexity> addNestedControlStructure(ArrayList<Complexity> complexity) {
		

		for(StatementLine line:nestingCtrlStructureOutput) {
			//System.out.print(line.getLineNumber());
			complexity.get(line.getLineNumber()-1).setCnc(line.getComplexity());
			//System.out.println(complexity.get(line.getLineNumber()-1).getLine());
		}
		
		return addTypeControlStructure(complexity);
	}
	
	public ArrayList<Complexity> addTypeControlStructure(ArrayList<Complexity> complexity) {
		
		for(StatementLine line:typeCtrlStructureOutput) {
			//System.out.println(line.getComplexity());
			complexity.get(line.getLineNumber()-1).setCtc(line.getComplexity());
		
		}
		
		return addSizeAndInheritance(complexity);
	}
	
	public ArrayList<Complexity> addRecursion(ArrayList<Complexity> complexity) {
		
		for(Method method:recursiveOutput) {
			
			for(int i=method.getStartLine()-1;i<method.getEndLine();i++) {
				
				complexity.get(i).setCr(complexity.get(i).getCps()*2);
			}
			
		
		}
		
		return complexity;
	}
	
	public ArrayList<Complexity> addSizeAndInheritance(ArrayList<Complexity> complexity) {
		
		
		
		
		for(StatementLine line:statementLinesCs) {
			//System.out.println(line.getComplexity());
			complexity.get(line.getLineNumber()-1).setCs(line.getComplexity());
			if(line.getComplexity()==0) {
				complexity.get(line.getLineNumber()-1).setCi(0);

			}else {
				complexity.get(line.getLineNumber()-1).setCi(Ci);
			}
			complexity.get(line.getLineNumber()-1).setTW(complexity.get(line.getLineNumber()-1).getCi()+complexity.get(line.getLineNumber()-1).getCnc()+complexity.get(line.getLineNumber()-1).getCtc());
			complexity.get(line.getLineNumber()-1).setCps(complexity.get(line.getLineNumber()-1).getTW()*complexity.get(line.getLineNumber()-1).getCs());
		}
		
		return addRecursion(complexity);
	}
	
	
	
}
