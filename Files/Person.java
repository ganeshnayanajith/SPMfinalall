public class Person {
	
	private String namePerson ;
	private String agePerson ;
	private String markPerson ;
	public Person(String namePerson , String agePerson , String markPerson ) {
		super();
		this.namePerson  = namePerson ;
		this.agePerson  = agePerson ;
		this.markPerson  = markPerson ;
	}
	public String getNamePerson () {
		return namePerson ;
	}
	public void setNamePerson (String namePerson ) {
		this.namePerson  = namePerson ;
	}
	public String getAgePerson () {
		return agePerson ;
	}
	public void setAgePerson (String agePerson ) {
		this.agePerson  = agePerson ;
	}
	public String getMarkPerson () {
		return markPerson ;
	}
	public void setMarkPerson (String markPerson ) {
		this.markPerson  = markPerson ;
	}
	
}