package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import Projekt3.BuechereiController;

import java.util.List;

import project3Predef.*;


public class RueckgabeMaske extends JFrame
{
	private static final long serialVersionUID = 1L;
	private Font labelFont = new Font("Sans Serif",Font.BOLD,14);
	private Font textFont = new Font("Sans Serif",Font.PLAIN,18);
	private ABuechereiController controller = null;
	private JTextField ausleiher = null;
	private JTextField autor = null;
	private JTextField titel= null;
	
	public RueckgabeMaske(ABuechereiController controller,JFrame parentComp)
	{
		super("Rueckgabe");
		this.controller = controller;
		createComponents();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(parentComp.getX(),parentComp.getY()+parentComp.getHeight()+50);
		setPreferredSize(new Dimension(350,160));
		setResizable(false);
		pack();
	}
	
	public void createComponents()
	{
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new GridLayout(3,1));
		ausleiher = new JTextField(50);
		autor = new JTextField(50);
		titel = new JTextField(50);
		ausleiher.setFont(textFont);
		autor.setFont(textFont);
		titel.setFont(textFont);
		searchPanel.add(ausleiher);
		searchPanel.add(autor);
		searchPanel.add(titel);
		
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new GridLayout(3,1));
		JLabel ausleiherL = new JLabel("Ausleiher(ID)");
		JLabel autorL = new JLabel("Autor");
		JLabel titelL = new JLabel("Titel");
		ausleiherL.setFont(labelFont);
		autorL.setFont(labelFont);
		titelL.setFont(labelFont);
		labelPanel.add(ausleiherL);
		labelPanel.add(autorL);
		labelPanel.add(titelL);
		
		JButton searchButton = new JButton("Suche starten");
		searchButton.setFont(textFont);
		searchButton.setActionCommand("search");
		
		JButton clearButton = new JButton("Clear");
		clearButton.setFont(textFont);
		clearButton.setActionCommand("clear");
		
		ButtonListener bL = new ButtonListener();
		searchButton.addActionListener(bL);
		clearButton.addActionListener(bL);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,2));
		buttonPanel.add(searchButton);
		buttonPanel.add(clearButton);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel,BoxLayout.X_AXIS));
		infoPanel.add(labelPanel);
		infoPanel.add(searchPanel);
		infoPanel.add(Box.createRigidArea(new Dimension(50,0)));
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		mainPanel.add(infoPanel);
		mainPanel.add(buttonPanel);
		this.add(mainPanel);
	}
	
	private void clearAll()
	{
		RueckgabeMaske.this.ausleiher.setText("");
		RueckgabeMaske.this.autor.setText("");
		RueckgabeMaske.this.titel.setText("");
	}
	
	private void error()
	{
		JOptionPane.showMessageDialog(this, "NO DATABASE SELECTED! PLEASE SELECT A DATABASE FIRST USING 'Select Source' in the menu!", "Error: No Database selected", JOptionPane.ERROR_MESSAGE);
		this.dispose();
	}
	
	private class ButtonListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e)
		{
			String command = ((JButton)e.getSource()).getActionCommand();
			if (command.equals("search"))
			{
				if(BuechereiController.getDatabase() == null || !(BuechereiController.getDatabase().endsWith(".db")))
				{
					error();
					return;
				}
				
				String ausleiherText = RueckgabeMaske.this.ausleiher.getText().trim();
				String autorText = RueckgabeMaske.this.autor.getText().trim();
				String titelText = RueckgabeMaske.this.titel.getText().trim();
				
				if(autorText.length() == 0)
				{
					autorText = null;
				}
				if(titelText.length() == 0)
				{
					titelText = null;
				}
				
				List<Buch> buchListe = RueckgabeMaske.this.controller.getAusgelieheneBuecher(autorText, titelText, ausleiherText);

				// Buecher in Fenster aufbereiten
				BuchlistenViewer viewer = new BuchlistenViewer(RueckgabeMaske.this.controller, buchListe,BuchlistenViewer.RUECKGABE);
				viewer.placement(getX(), getY()+getHeight()+30);
				viewer.setVisible(true);	
			}
			else if (command.equals("clear"))
			{
				RueckgabeMaske.this.clearAll();
			}	
		}
	}
}
