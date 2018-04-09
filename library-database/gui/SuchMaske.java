package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import Projekt3.BuechereiController;

import java.util.List;

import project3Predef.*;

public class SuchMaske extends JFrame{
	private static final long serialVersionUID = 1L;
	private Font labelFont = new Font("Sans Serif",Font.BOLD,14);
	private Font textFont = new Font("Sans Serif",Font.PLAIN,18);
	private ABuechereiController controller = null;
	private JTextField autor = null;
	private JTextField titel= null;
	JList<Kategorie> kategListe = null;
	JButton ausleihButton = null;

	public SuchMaske(ABuechereiController controller,JFrame parentComp){
		super("Ausleihen");
		this.controller = controller;
		createComponents();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(parentComp.getX()+parentComp.getWidth()+30,parentComp.getY());
		setPreferredSize(new Dimension(400,200));
		setResizable(false);
		pack();
	}
	
	private void createComponents(){
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new GridLayout(2,1));
		autor = new JTextField(50);
		titel = new JTextField(50);
		autor.setFont(textFont);
		titel.setFont(textFont);
		searchPanel.add(autor);searchPanel.add(titel);

		
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new GridLayout(2,1));
		JLabel autorL = new JLabel("Autor");
		JLabel titelL = new JLabel("Titel");
		autorL.setFont(labelFont);
		titelL.setFont(labelFont);
		labelPanel.add(autorL);labelPanel.add(titelL); 
		
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel,BoxLayout.X_AXIS));
		infoPanel.add(labelPanel);
		infoPanel.add(Box.createRigidArea(new Dimension(50,0)));
		infoPanel.add(searchPanel);
		
		JPanel kategPanel = new JPanel();
		kategPanel.setLayout(new BoxLayout(kategPanel,BoxLayout.X_AXIS));
		JLabel kategL = new JLabel("Kategorien");
		kategL.setFont(labelFont);
		kategListe = new JList<Kategorie> ();
		kategListe.setListData(Kategorie.values());
		kategListe.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION );
		kategPanel.add(kategL);
		kategPanel.add(Box.createRigidArea(new Dimension(7,0)));
		kategPanel.add(new JScrollPane(kategListe));
		

		JButton clearButton = new JButton("clear");
		clearButton.setFont(textFont);
		clearButton.setActionCommand("clear");
		
		JButton searchButton = new JButton("Suche starten");
		searchButton.setFont(textFont);
		searchButton.setActionCommand("search");
		ButtonListener buttonLis = new ButtonListener();
		searchButton.addActionListener(buttonLis);
		clearButton.addActionListener(buttonLis);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,2));
		buttonPanel.add(searchButton);
		buttonPanel.add(clearButton);

		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(0,5,2,5));
		mainPanel.add(infoPanel);
		mainPanel.add(kategPanel);
		mainPanel.add(buttonPanel);
		this.add(mainPanel);
	}
	private void clearAll(){
		SuchMaske.this.autor.setText("");
		SuchMaske.this.titel.setText("");
		kategListe.clearSelection();
	}
	
	private void error()
	{
		JOptionPane.showMessageDialog(this, "NO DATABASE SELECTED! PLEASE SELECT A DATABASE FIRST USING 'Select Source' in the menu!", "Error: No Database selected", JOptionPane.ERROR_MESSAGE);
		this.dispose();
	}
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			String command = ((JButton)e.getSource()).getActionCommand();
			if (command.equals("search")){
				if(BuechereiController.getDatabase() == null || !(BuechereiController.getDatabase().endsWith(".db")))
				{
					error();
					return;
				}
				String autorText = SuchMaske.this.autor.getText().trim();
				String titelText = SuchMaske.this.titel.getText().trim();
				List<Kategorie> kategElemente = SuchMaske.this.kategListe.getSelectedValuesList();
				if (autorText.length()==0){
					autorText = null;
				}
				if (titelText.length()==0){
					titelText = null;
				}
				Kategorie[] kategs = kategElemente.toArray(new Kategorie[kategElemente.size()]);
				String[] kategStrings = new String[kategs.length];
				int i=0;
				for (Kategorie k : kategs){
					kategStrings[i] = k.name();
					++i;
				}
				List<Buch> buchListe = SuchMaske.this.controller.getVerfuegbareBuecher(autorText,titelText,kategStrings);
				// Buecher in Fenster aufbereiten
				BuchlistenViewer viewer = new BuchlistenViewer(SuchMaske.this.controller, buchListe,BuchlistenViewer.AUSLEIHE);
				viewer.placement(getX(), getY()+getHeight()+30);
				viewer.setVisible(true);
			}
			else if (command.equals("clear")){
				SuchMaske.this.clearAll();
			}
		}
	}
}
