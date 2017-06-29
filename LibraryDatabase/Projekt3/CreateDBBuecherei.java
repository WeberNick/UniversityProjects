package Projekt3;

import java.sql.*;
import java.util.Properties;

public class CreateDBBuecherei 
{
	Properties properties;
	private Connection c = null;
	private Statement stmt;
	private PreparedStatement pstm1;

	public CreateDBBuecherei()
	{
		this.createDB();
	}
	
	public void connect()
    {
    	init();
		try 
		{
			Class.forName("org.sqlite.JDBC");
		    c = DriverManager.getConnection("jdbc:sqlite:Buecher.db", properties);
		    c.setAutoCommit(false);
		} 
		catch(Exception e) 
		{
		    System.err.println(e.getClass().getName() + ": " + e.getMessage() );
		    System.exit(0);
		}
		System.out.println("Opened database successfully");
    }
	
	private void init()
	{
		properties = new Properties();
		properties.setProperty("PRAGMA foreign_keys", "ON");
	}

    private void createTableBuch()
    {
    	try 
    	{
			stmt = c.createStatement();
			String sql = "CREATE TABLE buch " +
					"(buch_id INTEGER PRIMARY  KEY  AUTOINCREMENT," +
					" titel           TEXT    NOT NULL, " + 
					" autor           TEXT    NOT NULL, " + 
					" kategorien      TEXT    ," + 
					" status           INTEGER NOT NULL DEFAULT 0)"; 
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
    	} 
    	catch (Exception e) 
    	{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
			System.exit(0);
    	}
    	System.out.println("Table buch created successfully");
    }


	private void createTablePerson()
	{
		try 
		{
			stmt = c.createStatement();
			String sql = "CREATE TABLE person " +
					"(benutzer_id INTEGER PRIMARY  KEY  AUTOINCREMENT," +
					" vorname           TEXT    NOT NULL, " + 
					" nachname          TEXT    NOT NULL)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
		} 
		catch(Exception e) 
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage() );
			e.printStackTrace();
			System.exit(0);
		}
		System.out.println("Table person created successfully");
	}

	private void createTableAusgeliehen()
    {
		try 
		{
			stmt = c.createStatement();
			String sql = "CREATE TABLE ausgeliehen_von " +
					"(ausleihnr   INTEGER PRIMARY  KEY  AUTOINCREMENT," +
					" ref_buch    INTEGER REFERENCES buch(buch_id), " + 
					" ref_person  INTEGER REFERENCES person(benutzer_id), " +
					" rueckgabe TEXT NOT NULL)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
		} 
		catch(Exception e) 
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage() );
			e.printStackTrace();
			System.exit(0);
		}
		System.out.println("Table ausgeliehen_von created successfully");    
	}

	private void disconnect()
	{
		if(c != null)
		{
			try 
			{
				c.close();
		    } 
			catch(SQLException e)
			{
				System.err.println(e.getClass().getName() + ": " + e.getMessage() );
				e.printStackTrace();
				System.exit(0);
		    }
		}
	}
	
	private void createDB()
	{
		this.connect();
		this.init();
		this.createTableBuch();
		this.createTablePerson();
		this.createTableAusgeliehen();
		this.disconnect();
	}
}
