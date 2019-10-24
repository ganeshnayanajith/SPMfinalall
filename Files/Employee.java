public class Employee extends Person {
	
	private String nameEmployee ;
	private String ageEmployee ;
	private String markEmployee ;
	public Employee(String nameEmployee , String ageEmployee , String markEmployee ) {
		super();
		this.nameEmployee  = nameEmployee ;
		this.ageEmployee  = ageEmployee ;
		this.markEmployee  = markEmployee ;
	}
	public String getNameEmployee () {
		return nameEmployee ;
	}
	public void setNameEmployee (String nameEmployee ) {
		this.nameEmployee  = nameEmployee ;
	}
	public String getAgeEmployee () {
		return ageEmployee ;
	}
	public void setAgeEmployee (String ageEmployee ) {
		this.ageEmployee  = ageEmployee ;
	}
	public String getMarkEmployee () {
		return markEmployee ;
	}
	public void setMarkEmployee (String markEmployee ) {
		this.markEmployee  = markEmployee ;
	}
	
}