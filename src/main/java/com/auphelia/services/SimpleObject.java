package main.java.com.auphelia.services;

public class SimpleObject {
	private String name;
	private String value;
	private String type;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public SimpleObject(){
		
	}
	public SimpleObject(String sName, String sValue,String sType){
		name = sName;
		value = sValue;
		type = sType;
	}
	
}
