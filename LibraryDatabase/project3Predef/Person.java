package project3Predef;

public class Person {
	
	String nachname;
	String vorname;
	
	int dbId;
	
	public Person(String vorname, String nachname){
		this.nachname = nachname;
		this.vorname = vorname;
	}
	
	public void setID(int id){
		this.dbId = id;
	}
	
	public int getId(){
		return this.dbId;
	}
	
	public String getName() {
		return this.vorname + " " + this.nachname;
	}
	
	public String toString(){
		return getName() + " (" + this.dbId + ")";
	}

}
