/*
 * University of Mannheim
 * Programmierpraktikum 2
 * Project 3
 * Nick Weber - 1405012
 */


package Projekt3;


import gui.BuechereiGUI;
import project3Predef.ABuechereiController;

public class BuechereiController extends ABuechereiController 
{
	/*
	 * Zwei und einen halben Hinweise:
	 * 1. Da die vorgegebene DTD fehlerhaft ist (Wurzelelement fehlt),
	 * habe ich mittels File-Chooser die moeglichkeit eingebaut eine
	 * (ggf. fehlerfreie) DTD aus einem Verzeichnis auszuwaehlen.
	 * Ebenso besteht die moeglichkeit eine schon bestehende Datenbank 
	 * und CSS mit dem Projekt zu verbinden.
	 * 
	 * 2. Da weder in der Projektbeschreibung noch in einer Email oder
	 * in den Kommentaren die implementierung der Methode 'importCSV'
	 * vorgeschrieben wurde, gehe ich davon aus, dass wir diese
	 * nicht implementieren muessen.
	 * 
	 * Der halbe Hinweis: Ich weiss das ihr Tutoren dafuer nicht 
	 * zustaendig seid aber es waere SEHR wuenschenswert wenn die 
	 * Projekte in Zukunft (bzw. fuer die Jahrgaenge nach uns)
	 * fehlerfreier gestaltet sind. Wenn uns Klassen vorgegeben
	 * werden, gehe ich davon aus, das diese auch korrekt sind
	 * und wir an diesen nichts aendern muessen. Es gingen einige
	 * Stunden dafuer drauf um Fehler zu finden/beheben die von
	 * anderen vorgebenen Klassen verursacht wurden. 
	 * Der bereits bekannte Fehler mit der falschen Sichtbarkeit
	 * des DBHandlers und XMLHandlers waren nicht die einzigen.
	 */
	
	
	private static String db;
	private static String dtd;
	private static String css;
	public BuechereiController()
	{
		super();
		super.dbhandler = new DBHandler();
		super.xmlhandler = new XMLHandler();
		BuechereiGUI gui = new BuechereiGUI(this);
	}
	
	public static String getDatabase()
	{
		return db;
	}
	
	public static String getDtd()
	{
		return dtd;
	}
	
	public static String getCss()
	{
		return css;
	}
	
	public void openDatabase(String filename)
	{
		db = filename;
		System.out.println("Your current database is " + db);
	}
	
	public void openDtd(String filename)
	{
		dtd = filename;
		System.out.println("Your current dtd is " + dtd);
	}
	
	public void openCss(String filename)
	{
		css = filename;
		System.out.println("Your current css is " + css);
	}
	
	public static void main(String[] args) 
	{
		BuechereiController bController = new BuechereiController();
	}
	
	@Override
	public void importCSV(String filename) {
		// TODO Auto-generated method stub	
	}
}
