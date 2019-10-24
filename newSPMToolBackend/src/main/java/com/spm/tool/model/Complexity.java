package com.spm.tool.model;

public class Complexity {

	String Line;
	int Cs;
	int Ctc;
	int Cnc;
	int Ci;
	int TW;
	int Cps;
	int Cr;
		
	public Complexity() {
		super();
		
	}

	public Complexity(String line, int cs, int ctc, int cnc, int ci, int tW, int cps, int cr) {
		super();
		Line = line;
		Cs = cs;
		Ctc = ctc;
		Cnc = cnc;
		Ci = ci;
		TW = tW;
		Cps = cps;
		Cr = cr;
	}

	public String getLine() {
		return Line;
	}

	public void setLine(String line) {
		Line = line;
	}

	public int getCs() {
		return Cs;
	}

	public void setCs(int cs) {
		Cs = cs;
	}

	public int getCtc() {
		return Ctc;
	}

	public void setCtc(int ctc) {
		Ctc = ctc;
	}

	public int getCnc() {
		return Cnc;
	}

	public void setCnc(int cnc) {
		Cnc = cnc;
	}

	public int getCi() {
		return Ci;
	}

	public void setCi(int ci) {
		Ci = ci;
	}

	public int getTW() {
		return TW;
	}

	public void setTW(int tW) {
		TW = tW;
	}

	public int getCps() {
		return Cps;
	}

	public void setCps(int cps) {
		Cps = cps;
	}

	public int getCr() {
		return Cr;
	}

	public void setCr(int cr) {
		Cr = cr;
	}
	
	
	
}
