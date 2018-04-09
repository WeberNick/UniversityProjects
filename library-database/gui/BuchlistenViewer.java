package gui;


import java.awt.*;

import javax.swing.*;

import project3Predef.ABuechereiController;
import project3Predef.Buch;
import project3Predef.Person;

import javax.swing.border.EmptyBorder; 
import javax.swing.border.LineBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BuchlistenViewer extends JFrame
{
	public static String AUSLEIHE = "Ausleihen";
	public static String RUECKGABE = "Zurueckgeben";
	private String aktStat;
	private List<Buch> buecherListe;
	private JCheckBox[] checkbox;
	private ABuechereiController controller = null;
	
	public BuchlistenViewer(ABuechereiController controller, List<Buch> buchListe, String stat)
	{
		super("Auswahl");
		this.controller = controller;
		this.aktStat = stat;
		this.buecherListe = buchListe;
		this.setVisible(true);
		createComponents();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		pack();
	}
	
	public void createComponents()
	{
		JPanel hilfsPanel = new JPanel();
		hilfsPanel.setLayout(new BorderLayout());
		
		JPanel anzeigePanel = new JPanel();
		anzeigePanel.setLayout(new BoxLayout(anzeigePanel, BoxLayout.Y_AXIS));
		
		
		if(buecherListe != null)
		{
			checkbox = new JCheckBox[buecherListe.size()];
			for(int i = 0; i < checkbox.length; i++) 
			{
				checkbox[i] = new JCheckBox(buecherListe.get(i).getAutor() + ": " + buecherListe.get(i).getTitel());
				anzeigePanel.add(checkbox[i]);
			}
		}
		else
		{
			JLabel noEntries = new JLabel("There are no entries found for your search!");
			EmptyBorder eBorder = new EmptyBorder(2, 10, 2, 10);
			LineBorder lBorder = new LineBorder(Color.red); 
			noEntries.setBorder(BorderFactory.createCompoundBorder(lBorder, eBorder)); 
			anzeigePanel.add(noEntries);
		}
		
		JButton selectButton = new JButton("Select all");
		selectButton.setActionCommand("select");
		
		JButton clearButton = new JButton("Clear all");
		clearButton.setActionCommand("clear");
		
		JButton statButton = new JButton(aktStat);
		statButton.setActionCommand(aktStat);
		
		ButtonListener buttonLis = new ButtonListener();
		selectButton.addActionListener(buttonLis);
		clearButton.addActionListener(buttonLis);
		statButton.addActionListener(buttonLis);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,3));
		buttonPanel.add(selectButton);
		buttonPanel.add(clearButton);
		buttonPanel.add(statButton);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		hilfsPanel.add(anzeigePanel, BorderLayout.WEST);
		mainPanel.add(hilfsPanel);
		mainPanel.add(buttonPanel);
		this.add(mainPanel);
	}
	
	private void clearAll()
	{
		for (int i = 0; i < checkbox.length; i++)
		{
			checkbox[i].setSelected(false);
		}
	}
	
	private void selectAll()
	{
		for (int i = 0; i < checkbox.length; i++)
		{
			checkbox[i].setSelected(true);
		}	
	}
	
	public void placement(int x, int y)
	{
		setLocation(x,y);
	}
	
	private void error()
	{
		JOptionPane.showMessageDialog(this, "Your ID is incorrect, please enter a valid ID to borrow a book. Hint: Only enter numbers!", "Error: Incorrect ID", JOptionPane.ERROR_MESSAGE);
	}
	
	private void confirm()
	{
		JOptionPane.showMessageDialog(this, "Return successfully", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private class ButtonListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e)
		{
			String command = ((JButton)e.getSource()).getActionCommand();
			if (command.equals("select"))
			{
				BuchlistenViewer.this.selectAll();
			}
			else if (command.equals("clear"))
			{
				BuchlistenViewer.this.clearAll();
			}	
			else if(command.equals("Ausleihen"))
			{
				
				String identification = JOptionPane.showInputDialog("To borrow please enter your ID.", "Enter your ID-number here.");
				if(!(identification.matches("\\d+")))
				{
					error();
				}
				
				int id = Integer.parseInt(identification);
				Person ausleiher = controller.getPerson(id);
				
				for (int i = 0; i < checkbox.length; i++) 
				{
					if(checkbox[i].isSelected())
					{	
						Buch ausleihendesBuch = buecherListe.get(i);
						controller.createAusleihe(ausleihendesBuch, ausleiher);
					}
				}
				BuchlistenViewer.this.dispose();
			}
			else if(command.equals("Zurueckgeben"))
			{
				for (int i = 0; i < checkbox.length; i++) 
				{
					if(checkbox[i].isSelected())
					{	
						Buch rueckgabeBuch = buecherListe.get(i);
						controller.deleteAusleihe(rueckgabeBuch);
					}
				}
				confirm();
				BuchlistenViewer.this.dispose();
			}
		}	
	}
}
