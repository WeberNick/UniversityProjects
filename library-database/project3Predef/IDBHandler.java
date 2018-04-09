package project3Predef;

import java.util.List;

public interface IDBHandler {
	/**
	 * Diese Methode liest alle Buecher (unabhaengig vom Status) aus der DB aus.
	 * Optional gefiltert anhand der query Parameter.
	 * Null bei den query Paramtern bedeutet keine Einschraenkung.
	 * 
	 * @param autorQuery null oder Such-String fuer den Autor
	 * @param titelQuery null oder Such-String fuer den Titel
	 * @param kategorieQuery null oder Array mit Kategorie-Suchstrings
	 * @return Liste der Buecher
	 */
	List<Buch> getBuecher(String autorQuery, String titelQuery, String[] kategorieQuery);
	
	/**
	 * Diese Methode liest nur verfuegbare Buecher aus der DB aus.
	 * Optional gefiltert anhand der query Parameter.
	 * Null bei den query Paramtern bedeutet keine Einschraenkung.
	 * 
	 * @param autorQuery null oder  Such-String fuer den Autor
	 * @param titelQuery null oder Such-String fuer den Titel
	 * @param kategorieQuery null oder Array mit Kategorie-Suchstrings
	 * @return Liste der Buecher
	 */
	List<Buch> getVerfuegbareBuecher(String autorQuery, String titelQuery, String[] kategorieQuery);
	
	/**
	 * Diese Methode liest nur ausgeliehene Buecher aus der DB aus.
	 * Optional gefiltert anhand der query Parameter.
	 * Null bei den query Paramtern bedeutet keine Einschraenkung.
	 * 
	 * @param autorQuery null oder Such-String fuer den Autor
	 * @param titelQuery null oder Such-String fuer den Titel
	 * @param kategorieQuery null oder Array mit Kategorie-Suchstrings
	 * @return Liste der Buecher
	 */
	List<Buch> getAusgelieheneBuecher(String autorQuery, String titelQuery, String benutzerQuery);
	
	void setAusgeliehen(Buch buch, Person person);
	
	void returnBuch(Buch buch);
	
	void setVerfuegbar(Buch buch);
	
	void addBooks(List<Buch> buecher);
	
	Person returnPerson(int id);

}
