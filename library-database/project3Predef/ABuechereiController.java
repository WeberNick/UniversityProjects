package project3Predef;

import java.util.List;

import Projekt3.DBHandler;

/**
 * Die Klasse BuechereiController ist die Klasse,
 * mit der das GUI direkt kommuniziert.
 * Sie verwaltet einen DB und einen XML Handler und reicht die Eingaben/Anfragen,
 * die ueber das GUI erfolgt sind, an diese weiter.
 * 
 * Der DB Handler koennte bei Anfragen direkt Buecher-Objekte zurueckgeben,
 * bei denen die Attribute anhand des aktuellen Datenbank-Status gesetzt sind.
 * D.h. er befuellt bei Buechern die dbId mit dem Primaerschluessel aus der DB.
 * Sofern das Buch ausgeliehen ist, wird ein entsprechendes
 * Personen Objekt referenziert.  Ist das Buch verfuegbar, ist der Wert des Attributs
 * null.
 */
public abstract class ABuechereiController {
	
	protected IDBHandler dbhandler;
	protected IXMLHandler xmlhandler;
	
	public abstract void importCSV(String filename);
	
	public abstract void openDatabase(String filename);
	public abstract void openDtd(String filename);
	public abstract void openCss(String filename);
	
	public List<Buch> getBuecher(String autorQuery, String titelQuery, String[] kategorieQuery){
		return this.dbhandler.getBuecher(autorQuery, titelQuery, kategorieQuery);
	}
	
	public List<Buch> getVerfuegbareBuecher(String autorQuery, String titelQuery, String[] kategorieQuery){
		return this.dbhandler.getVerfuegbareBuecher(autorQuery, titelQuery, kategorieQuery);
	}
	
	/* irrelevante Kriterien fuer die Suchanfrage haben hierbei den Wert null */
	public List<Buch> getAusgelieheneBuecher(String autorQuery, String titelQuery, String benutzerQuery){
		return this.dbhandler.getAusgelieheneBuecher(autorQuery, titelQuery, benutzerQuery);
	}
	
	public void writeBuecherToXML(String filename){
		this.xmlhandler.writeToXML(this.dbhandler.getBuecher(null, null, null), filename);
	}
	
	public void writeBuecherToHTML(String filename){
		this.xmlhandler.writeToHTML(this.dbhandler.getBuecher(null, null, null), filename);
	}
	
	public void createAusleihe(Buch buch, Person person){
		this.dbhandler.setAusgeliehen(buch, person);
	}
		
	public void deleteAusleihe(Buch buch){
		this.dbhandler.returnBuch(buch);
	}
	
	public Person getPerson(int id){
		return this.dbhandler.returnPerson(id);
	}

}