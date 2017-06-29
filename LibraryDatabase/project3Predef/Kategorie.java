package project3Predef;

public enum Kategorie {
    JUGEND, KINDER, KRIMI, SCIENCE_FICTION, FANTASY, SACHBUCH, ROMAN, FACHBUCH, RATGEBER, FREIZEIT,KLASSIKER;

    public String toString(){
    	String str = this.name().toLowerCase();
    	char firstLetter = str.charAt(0);
    	return (char)(firstLetter - 32) + str.substring(1);
    }
}