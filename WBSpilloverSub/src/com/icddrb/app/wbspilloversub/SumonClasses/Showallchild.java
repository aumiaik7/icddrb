package com.icddrb.app.wbspilloversub.SumonClasses;

public class Showallchild {
	private String id;
	private String Name;
	private String Sex;
	private String Mage;
	private String Bdate;
	private String sourcedob;
	private String birthorder;
	private String spilloverchild;
	private String spillovermochild;
	
	
	
	
	
	
	public Showallchild() {

	}
	
	
	public Showallchild(String id, String name, String sex, String mage,
			String bdate, String sourcedob, String birthorder,
			String spilloverchild,String spillovermochild) {
		
		this.id = id;
		this.Name = name;
		this.Sex = sex;
		this.Mage = mage;
		this.Bdate = bdate;
		this.sourcedob = sourcedob;
		this.birthorder = birthorder;
		this.spilloverchild = spilloverchild;
		this.spillovermochild = spillovermochild;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		this.Name = name;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		this.Sex = sex;
	}
	public String getMage() {
		return Mage;
	}
	public void setMage(String mage) {
		this.Mage = mage;
	}
	public String getBdate() {
		return Bdate;
	}
	public void setBdate(String bdate) {
		this.Bdate = bdate;
	}
	public String getSourcedob() {
		return sourcedob;
	}
	public void setSourcedob(String sourcedob) {
		this.sourcedob = sourcedob;
	}
	public String getBirthorder() {
		return birthorder;
	}
	public void setBirthorder(String birthorder) {
		this.birthorder = birthorder;
	}
	public String getSpilloverchild() {
		return spilloverchild;
	}
	public void setSpilloverchild(String spilloverchild) {
		this.spilloverchild = spilloverchild;
	}
	public String getSpillovermochild() {
		return spillovermochild;
	}
	public void setSpillovermochild(String spillovermochild) {
		this.spillovermochild = spillovermochild;
	}
	
	
	
}
