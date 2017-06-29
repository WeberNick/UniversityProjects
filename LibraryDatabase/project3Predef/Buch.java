package project3Predef;

import java.util.List;
import java.util.ArrayList;

public class Buch {
	
	String autor;
	String titel;
	List<Kategorie> kategorien = new ArrayList<Kategorie>();
	
	Person ausleiher;
	
	int dbId = -1; // Default-Wert fuer ungueltige ID
	
    public Buch(String autor, String titel){
    	this.titel = titel;
    	this.autor = autor;
    }

    public String getAutor (){
    	return autor;
    }
    	
    public String getTitel (){
    	return titel;
    }

    public void setID(int id){
    	this.dbId = id;
    }
    	
    public int getID (){
    	return dbId;
    }

    public void setKategorien(Kategorie[] kategorien){
    	for (int i=0;i<kategorien.length;i++){
    	    this.kategorien.add(kategorien[i]);
    	}
    }	
    
    public void setKategorien(List<Kategorie> kategorien){
    	this.kategorien.addAll(kategorien);
    }
    
    public List<Kategorie> getKategorienList(){
    	return new ArrayList<Kategorie>(this.kategorien);
    }
    
    public Kategorie[] getKategorienArray(){
    	return this.kategorien.toArray(new Kategorie[this.kategorien.size()]);
    }
    
    public String toString(){
    	return this.autor + ": " + this.titel;
    }

}
