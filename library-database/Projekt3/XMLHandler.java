package Projekt3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.jdom2.*;
import org.jdom2.output.*;
import project3Predef.Buch;
import project3Predef.IXMLHandler;
import project3Predef.Kategorie;

public class XMLHandler implements IXMLHandler {

	DBHandler handler = new DBHandler();

	public void writeToXML(List<Buch> buecher, String filename) {
		Element buch = new Element("buch");
		for (int i = 0; i < buecher.size(); i++) {
			Element autor = new Element("autor"); autor.addContent(buecher.get(i).getAutor());
			Element titel = new Element("titel"); titel.addContent(buecher.get(i).getTitel());
			Element kategorien = new Element("kategorien");
			Element kategorie;
			Kategorie[] katArray = buecher.get(i).getKategorienArray();
			for (int j = 0; j < katArray.length; j++) {
				kategorie = new Element("kategorie");
				kategorie.addContent(katArray[j].toString());
				kategorien.addContent(kategorie);
			}
			buch.setAttribute("id", String.valueOf(buecher.get(i).getID()));
			buch.setAttribute("status", handler.returnStatus(buecher.get(i)));
			buch.addContent(autor);
			buch.addContent(titel);
			buch.addContent(kategorien);
		}
		Document document = new Document(buch);
		try {
			DocType doctype = new DocType("buch", BuechereiController.getDtd());
			document.setDocType(doctype);
			XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
			outputter.output(document, System.out);
			File file = new File(filename);
			FileWriter filewriter = new FileWriter(file);
			outputter.output(document, filewriter);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void writeToHTML(List<Buch> buecher, String filename) {
		Element buch = new Element("buch");
		Element html = new Element("html");
		Element head = new Element("head");
		Element body = new Element("body");
		Element h1 = new Element("h1");
		h1.addContent("Buch-Datenbank");
		for (int i = 0; i < buecher.size(); i++) {
			Element p = new Element("p");
			Element autor = new Element("autor");
			autor.addContent(buecher.get(i).getAutor());
			Element titel = new Element("titel");
			titel.addContent(buecher.get(i).getTitel());
			Element kategorien = new Element("kategorien");
			Element kategorie;
			Kategorie[] katArray = buecher.get(i).getKategorienArray();
			for (int j = 0; j < katArray.length; j++) {
				kategorie = new Element("kategorie");
				kategorie.addContent(katArray[j].toString());
				kategorien.addContent(kategorie);
			}
			buch.setAttribute("id", String.valueOf(buecher.get(i).getID()));
			buch.setAttribute("status", handler.returnStatus(buecher.get(i)));
			p.addContent(autor);
			p.addContent(titel);
			p.addContent(kategorien);
			buch.addContent(p);
		}
		body.addContent(buch);
		html.addContent(head);
		html.addContent(h1);
		html.addContent(body);
		Document document = new Document(html);
		try {
			XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
			File file = new File(filename);
			FileWriter filewriter = new FileWriter(file);
			filewriter.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\">"
			+"<link rel=\"stylesheet\" type=\"text/css\" href=\"" + BuechereiController.getCss() + "\">");
			System.out.println(BuechereiController.getCss());
			out.output(document, filewriter);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
