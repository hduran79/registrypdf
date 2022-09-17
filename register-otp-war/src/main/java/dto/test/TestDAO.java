package dto.test;

import java.io.Serializable;

public class TestDAO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private String age;
	private String country;
	
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
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}

}
