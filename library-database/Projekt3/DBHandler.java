package Projekt3;

import java.util.ArrayList;
import java.util.List;

import project3Predef.Buch;
import project3Predef.IDBHandler;
import project3Predef.Kategorie;
import project3Predef.Person;

import java.sql.*;


public class DBHandler implements IDBHandler {
	Connection c = null;
	Statement stmt = null;

	public void connect(String filename) {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + filename);
			c.setAutoCommit(false);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	public List<Buch> getBuecher(String autorQuery, String titelQuery,
			String[] kategorieQuery) {
		List<Buch> buecher = new ArrayList<Buch>();
		try {
			connect(BuechereiController.getDatabase());
			
			stmt = c.createStatement();
			
			/*
			 * Da diese Methode nur von 'writeBuecherToXML' bzw. 'writeBuecherToHTML' aufgerufen wird
			 * und dort alle 3 Parameter null gesetzt werden, muss man keine Ueberpruefung,
			 * ob Parameter und Tabelleninhalt (titel, autor, kategorie) uebereinstimmen, vornehmen!
			 */
			
			ResultSet rs = stmt
					.executeQuery("SELECT buch_id, autor, titel, kategorien FROM buch;");
			
			if(autorQuery == null && titelQuery == null && kategorieQuery == null)
			{
				autorQuery = ""; titelQuery = "";
			}
			
			while (rs.next()) {
				int id = rs.getInt("buch_id");
				String autor = rs.getString("autor");
				String titel = rs.getString("titel");
				String kategorie = rs.getString("kategorien");
				String[] kategorieStringArray;
				if (kategorie.contains(" ")) {
					kategorieStringArray = kategorie.split(" ");
				} else {
					kategorieStringArray = new String[1];
					kategorieStringArray[0] = kategorie;
				}
				Kategorie[] kategorieArray = new Kategorie[kategorieStringArray.length];

				for (int i = 0; i < kategorieStringArray.length; i++) {
					kategorieArray[i] = Kategorie
							.valueOf(kategorieStringArray[i].toUpperCase());
				}
				buecher.add(new Buch(autor, titel));
				buecher.get(buecher.size() - 1).setKategorien(
						kategorieArray);
				buecher.get(buecher.size() - 1).setID(id);
			}

			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return buecher;
	}

	public List<Buch> getVerfuegbareBuecher(String autorQuery,
			String titelQuery, String[] kategorieQuery) {
		List<Buch> buecher = new ArrayList<Buch>();
		try {
			connect(BuechereiController.getDatabase());
			
			if (autorQuery == null) {
				autorQuery = "";
			}
			if (titelQuery == null) {
				titelQuery = "";
			}

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT buch_id, autor, titel, kategorien, status FROM buch WHERE autor LIKE '%"
							+ autorQuery
							+ "%' AND titel LIKE '%"
							+ titelQuery
							+ "%' AND status = 0;");

			while (rs.next()) {
				int id = rs.getInt("buch_id");
				String autor = rs.getString("autor");
				String titel = rs.getString("titel");
				String kategorie = rs.getString("kategorien");
				String[] kategorieStringArray;
				if (kategorie.contains(" ")) {
					kategorieStringArray = kategorie.split(" ");
				} else {
					kategorieStringArray = new String[1];
					kategorieStringArray[0] = kategorie;
				}
				Kategorie[] kategorieArray = new Kategorie[kategorieStringArray.length];

				for (int i = 0; i < kategorieStringArray.length; i++) {
					kategorieArray[i] = Kategorie
							.valueOf(kategorieStringArray[i].toUpperCase());
				}
				boolean kategorieMatch = true;
				if (kategorieQuery.length > kategorieStringArray.length) {
					kategorieMatch = false;
				} else if ((kategorieQuery.length <= kategorieStringArray.length)
						&& kategorieQuery.length > 0) {
					kategorieMatch = false;
					boolean addieren = true;
					boolean[] werte = new boolean[kategorieQuery.length];
					for (int i = 0; i < kategorieQuery.length; i++) {
						if (kategorie.toUpperCase().contains(kategorieQuery[i])) {
							werte[i] = true;
						} else {
							werte[i] = false;
						}
					}

					for (int j = 0; j < werte.length; j++) {
						if (werte[j] == false) {
							addieren = false;
						}
					}

					if (addieren == true) {
						buecher.add(new Buch(autor, titel));
						buecher.get(buecher.size() - 1).setKategorien(kategorieArray);
						buecher.get(buecher.size() - 1).setID(id);
					}
				}
				if (kategorieMatch) {
					buecher.add(new Buch(autor, titel));
					buecher.get(buecher.size() - 1).setKategorien(kategorieArray);
					buecher.get(buecher.size() - 1).setID(id);
				}

			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return buecher;
	}

	public List<Buch> getAusgelieheneBuecher(String autorQuery,
			String titelQuery, String benutzerQuery) {
		List<Buch> buecher = new ArrayList<Buch>();
		try {
			connect(BuechereiController.getDatabase());
			if (autorQuery == null) {
				autorQuery = "";
			}
			if (titelQuery == null) {
				titelQuery = "";
			}

			stmt = c.createStatement();
			Statement stmt2 = c.createStatement();

			if (!(benutzerQuery.matches("\\d+"))) {
				benutzerQuery = "0";
			}

			int ausleiherID = Integer.parseInt(benutzerQuery);
			if (ausleiherID != 0) {
				ResultSet rs = stmt
						.executeQuery("SELECT ref_buch FROM ausgeliehen_von WHERE ref_person = "
								+ ausleiherID + ";");

				while (rs.next()) {
					int buch_id = rs.getInt("ref_buch");
					ResultSet rs2 = stmt2
							.executeQuery("SELECT buch_id, autor, titel, kategorien FROM buch WHERE buch_id = "
									+ buch_id
									+ " AND autor LIKE '%"
									+ autorQuery
									+ "%' AND titel LIKE '%"
									+ titelQuery + "%';");
					while (rs2.next()) {
						String autor = rs2.getString("autor");
						String titel = rs2.getString("titel");
						String kategorien = rs2.getString("kategorien");
						String[] kategorieStringArray = kategorien.split(" ");
						Kategorie[] kategorieArray = new Kategorie[kategorieStringArray.length];

						for (int i = 0; i < kategorieStringArray.length; i++) {
							kategorieArray[i] = Kategorie
									.valueOf(kategorieStringArray[i]
											.toUpperCase());
						}

						buecher.add(new Buch(autor, titel));
						buecher.get(buecher.size() - 1).setKategorien(
								kategorieArray);
						buecher.get(buecher.size() - 1).setID(buch_id);
					}
					rs2.close();
				}
				rs.close();
			} else {
				ResultSet rs = stmt
						.executeQuery("SELECT buch_id, autor, titel, kategorien FROM buch WHERE  status = 1 AND autor LIKE '%"
								+ autorQuery
								+ "%' AND titel LIKE '%"
								+ titelQuery + "%';");
				while (rs.next()) {
					int buch_id = rs.getInt("buch_id");
					String autor = rs.getString("autor");
					String titel = rs.getString("titel");
					String kategorien = rs.getString("kategorien");
					String[] kategorieStringArray = kategorien.split(" ");
					Kategorie[] kategorieArray = new Kategorie[kategorieStringArray.length];

					for (int i = 0; i < kategorieStringArray.length; i++) {
						kategorieArray[i] = Kategorie
								.valueOf(kategorieStringArray[i].toUpperCase());
					}

					buecher.add(new Buch(autor, titel));
					buecher.get(buecher.size() - 1).setKategorien(
							kategorieArray);
					buecher.get(buecher.size() - 1).setID(buch_id);
				}
			}

			stmt.close();
			stmt2.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return buecher;
	}

	public void setAusgeliehen(Buch buch, Person person) {
		try {
			connect(BuechereiController.getDatabase());
			
			stmt = c.createStatement();
			Statement stmt2 = c.createStatement();

			stmt.executeUpdate("UPDATE buch SET status = 1 WHERE buch_id = "
					+ buch.getID() + ";");
			c.commit();
			stmt2.executeUpdate("INSERT INTO ausgeliehen_von (ref_buch, ref_person, rueckgabe) VALUES ("
					+ buch.getID()
					+ ", "
					+ person.getId()
					+ ", 'Brings wieder');");
			c.commit();
			stmt.close();
			stmt2.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	public void returnBuch(Buch buch) {
		try {
			connect(BuechereiController.getDatabase());
			
			stmt = c.createStatement();
			System.out.println("");
			stmt.executeUpdate("DELETE FROM ausgeliehen_von WHERE ref_buch = "
					+ buch.getID() + ";");
			c.commit();

			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		setVerfuegbar(buch);
	}

	public void setVerfuegbar(Buch buch) {
		try {
			connect(BuechereiController.getDatabase());
			
			int id = buch.getID();
			stmt = c.createStatement();
			stmt.executeUpdate("UPDATE buch set status = 0 WHERE buch_id=" + id
					+ ";");
			c.commit();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void addBooks(List<Buch> buecher) {
		try {
			connect(BuechereiController.getDatabase());
			
			stmt = c.createStatement();
			Buch[] buch=buecher.toArray(new Buch[buecher.size()]);
			for(int i=0;i<buecher.size();i++){
				String titel=buch[i].getTitel();
				String autor=buch[i].getAutor();
				String kategorien=buch[i].getKategorienList().toString();
				stmt.executeUpdate("INSERT INTO buch (titel,autor,kategorien) VALUES ('"
						+ titel + "','"+ autor+ "','"+ kategorien+ "');");
				stmt.close();
				c.close();
			}
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	//return die Person mit der id aus dem Parameter oder die 
	//Person welche das Buch mit der id aus dem Parameter ausgeliehen hat?
	public Person returnPerson(int id) {
		Person person = null;
		try {
			connect(BuechereiController.getDatabase());
			
			stmt = c.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT * FROM person WHERE benutzer_id = "
							+ id + ";");
			while (rs.next()) {
				person = new Person(rs.getString("vorname"),
						rs.getString("nachname"));
				person.setID(id);
			}

			stmt.close();
			rs.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return person;
	}
	
	public String returnStatus(Buch buch)
	{
		String help = "";
		try {
			connect(BuechereiController.getDatabase());
			
			stmt = c.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT status FROM buch WHERE buch_id = " + buch.getID() + ";");
			while (rs.next()) {
				int buchStatus = rs.getInt("status");
				if(buchStatus == 0)
					help = "Verfuegbar";
				else help = "Ausgeliehen";
			}

			stmt.close();
			rs.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return help;
	}
	
}