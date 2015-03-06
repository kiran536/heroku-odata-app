package main.java.com.auphelia.services;

public class SimpleElement {
	
	private String title;
	private String href;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	
	public SimpleElement(){
		
	}
	public SimpleElement(String sTitle, String sHref){
		title = sTitle;
		href = sHref;
	}
}
