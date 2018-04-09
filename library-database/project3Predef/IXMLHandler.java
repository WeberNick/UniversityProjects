package project3Predef;

import java.util.List;

public interface IXMLHandler {
	
	void writeToXML(List<Buch> buecher, String filename);
	
	void writeToHTML(List<Buch> buecher, String filename);

}
