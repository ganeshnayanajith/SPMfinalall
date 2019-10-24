public class Manager extends Employee {
	
	private String nameManager ;
	private String ageManager ;
	private String markManager ;
	public Manager(String nameManager , String ageManager , String markManager ) {
		super();
		this.nameManager  = nameManager ;
		this.ageManager  = ageManager ;
		this.markManager  = markManager ;
	}
	public String getNameManager () {
		return nameManager ;
	}
	public void setNameManager (String nameManager ) {
		this.nameManager  = nameManager ;
	}
	public String getAgeManager () {
		return ageManager ;
	}
	public void setAgeManager (String ageManager ) {
		this.ageManager  = ageManager ;
	}
	public String getMarkManager () {
		return markManager ;
	}
	public void setMarkManager (String markManager ) {
		this.markManager  = markManager ;
	}
	
}