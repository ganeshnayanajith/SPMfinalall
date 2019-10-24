public class Employee implements Person {
	
	private String name;
	private String age;
	private String mark;
	public Person(String name, String age, String mark) {
		super();
		this.name = name;
		this.age = age;
		this.mark = mark;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	
}