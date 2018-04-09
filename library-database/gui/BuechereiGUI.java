package gui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import Projekt3.BuechereiController;
import project3Predef.ABuechereiController;

public class BuechereiGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private Font textfont = new Font("Sans Serif", Font.BOLD, 24);
	private ABuechereiController controller = null;
	private File currentDir = new File(".");

	public File getCurrentDir() {
		return currentDir;
	}

	public BuechereiGUI(ABuechereiController controller) {
		super("Buecherei");
		this.controller = controller;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 150);
		setVisible(true);
		createComponents();
		addMenu();
		setResizable(false);
		pack();
	}

	private void createComponents() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(2, 1));
		JButton suche = new JButton("Suche/Ausleihe");
		suche.setActionCommand("search");
		JButton rueckgabe = new JButton("Rueckgabe");
		rueckgabe.setActionCommand("return");
		suche.setFont(this.textfont);
		rueckgabe.setFont(this.textfont);
		ButtonListener bLis = new ButtonListener();
		suche.addActionListener(bLis);
		rueckgabe.addActionListener(bLis);
		mainPanel.add(suche);
		mainPanel.add(rueckgabe);
		getContentPane().add(mainPanel);
	}

	private void addMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menuO = new JMenu("Output");
		JMenu menu1 = new JMenu("Select Source");
		JMenuItem expXML = new JMenuItem("Export to XML");
		JMenuItem expHTML = new JMenuItem("Export to HTML");
		JMenuItem selectDatabase = new JMenuItem("Select Database");
		JMenuItem selectDtd = new JMenuItem("Select DTD");
		JMenuItem selectCss = new JMenuItem("Select CSS");
		menuO.add(expXML);
		menuO.add(expHTML);
		menu1.add(selectDatabase);
		menu1.add(selectDtd);
		menu1.add(selectCss);
		menuBar.add(menu1);
		menuBar.add(menuO);
		BMenuListener bLis = new BMenuListener();
		expXML.addActionListener(bLis);
		expHTML.addActionListener(bLis);
		selectDatabase.addActionListener(bLis);
		selectDtd.addActionListener(bLis);
		selectCss.addActionListener(bLis);
		this.setJMenuBar(menuBar);
	}

	/**
	 * gibt den Controller an andere GUI-Komponenten weiter, damit diese direkt
	 * die Anfragen an diesen richten koennen anstatt die Anfragen
	 * durchzureichen
	 * 
	 * @return
	 */

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command = ((JButton) e.getSource()).getActionCommand();
			switch (command) {
			case "search":
				new SuchMaske(controller, BuechereiGUI.this).setVisible(true);
				break;
			case "return":
				new RueckgabeMaske(controller, BuechereiGUI.this)
						.setVisible(true);
				break;
			default:
				break;
			}
		}
	}

	private void error() {
		JOptionPane.showMessageDialog(this,
						"NO DATABASE SELECTED! PLEASE SELECT A DATABASE FIRST USING 'Select Source' in the menu!",
						"Error: No Database selected",
						JOptionPane.ERROR_MESSAGE);
	}

	private class BMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String filename;
			String command;
			File selectedFile = chooseFile();
			if (selectedFile == null) {
				filename = "Error, no file selected";
				command = "Error, no file selected";
			} else {
				filename = selectedFile.getAbsolutePath();
				command = ((JMenuItem) e.getSource()).getText();
			}

			switch (command) {
			case "Export to XML":
				if (BuechereiController.getDatabase() == null
						|| !(BuechereiController.getDatabase().endsWith(".db"))) {
					error();
					return;
				}
				BuechereiGUI.this.controller.writeBuecherToXML(filename);
				break;
			case "Export to HTML":
				if (BuechereiController.getDatabase() == null
						|| !(BuechereiController.getDatabase().endsWith(".db"))) {
					error();
					return;
				}
				BuechereiGUI.this.controller.writeBuecherToHTML(filename);
				break;
			case "Select Database":
				BuechereiGUI.this.controller.openDatabase(filename);
				break;
			case "Select DTD":
				BuechereiGUI.this.controller.openDtd(filename);
				break;
			case "Select CSS":
				BuechereiGUI.this.controller.openCss(filename);
				break;
			default:
				break;
			}
		}

		private File chooseFile() {
			File file = null;
			JFileChooser chooser = new JFileChooser(
					BuechereiGUI.this.currentDir);
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"SQLite database", "db");
			FileNameExtensionFilter filter1 = new FileNameExtensionFilter(
					"XML", "xml");
			FileNameExtensionFilter filter2 = new FileNameExtensionFilter(
					"HTML", "html");
			FileNameExtensionFilter filter3 = new FileNameExtensionFilter(
					"DTD", "dtd");
			FileNameExtensionFilter filter4 = new FileNameExtensionFilter(
					"CSS", "css");
			chooser.setFileFilter(filter4);
			chooser.setFileFilter(filter3);
			chooser.setFileFilter(filter2);
			chooser.setFileFilter(filter1);
			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(BuechereiGUI.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				file = chooser.getSelectedFile();
				BuechereiGUI.this.currentDir = chooser.getCurrentDirectory();
			}
			if (returnVal == JFileChooser.CANCEL_OPTION) {
				System.out.println("No File selected, cancel");
			}
			return file;
		}
	}
}