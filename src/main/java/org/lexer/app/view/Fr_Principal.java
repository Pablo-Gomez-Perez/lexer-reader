package org.lexer.app.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import java.awt.BorderLayout;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import javax.swing.JFileChooser;

import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class Fr_Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu menuArchivo;
	private JMenuItem optNuevo;
	private JMenuItem optAbrirDoc;
	private JSeparator separator;
	private JMenuItem optCerrarEditor;
	private JMenuItem optSalir;
	private JTextArea txaDataString;
	private JScrollPane scrollPane;
	private List<Character> characters;
	private JMenu menuActions;
	private JMenuItem opcionAnalizar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fr_Principal frame = new Fr_Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Fr_Principal() {
		setTitle("Lexer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1292, 687);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menuArchivo = new JMenu("Archivo");
		menuBar.add(menuArchivo);
		
		optNuevo = new JMenuItem("Nuevo");
		optNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startNewEditor();
			}
		});
		menuArchivo.add(optNuevo);
		
		optAbrirDoc = new JMenuItem("Abrir");
		optAbrirDoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openFile();
			}
		});
		menuArchivo.add(optAbrirDoc);
		
		separator = new JSeparator();
		menuArchivo.add(separator);
		
		optCerrarEditor = new JMenuItem("Cerrar Editor");
		optCerrarEditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeEditor();
			}
		});
		menuArchivo.add(optCerrarEditor);
		
		optSalir = new JMenuItem("Salir");
		menuArchivo.add(optSalir);
		
		menuActions = new JMenu("Actions");
		menuBar.add(menuActions);
		
		opcionAnalizar = new JMenuItem("Analizar");
		opcionAnalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillList();
			}
		});
		menuActions.add(opcionAnalizar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		txaDataString = new JTextArea();
		scrollPane.setViewportView(txaDataString);
		txaDataString.setLineWrap(true);
		txaDataString.setEnabled(false);
		txaDataString.setEditable(false);
		txaDataString.setSelectedTextColor(new Color(255, 255, 255));
		txaDataString.setCaretColor(new Color(255, 0, 0));
		txaDataString.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txaDataString.setSelectionColor(new Color(0, 51, 255));
		txaDataString.setForeground(new Color(0, 255, 51));
		txaDataString.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 13));
		txaDataString.setBackground(new Color(109,109,109));
		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}
	
	/**
	 * set the the text editor enabled and editable
	 */
	private void startNewEditor() {
		this.txaDataString.setEnabled(true);
		this.txaDataString.setEditable(true);
		this.txaDataString.setBackground(new Color(0,0,0));
		this.txaDataString.setForeground(new Color(0, 255, 51));
	}
	
	/**
	 * clear all the data in the editor and set it unabled and not editable
	 */
	private void closeEditor() {
		this.txaDataString.setText("");
		this.txaDataString.setEnabled(false);
		this.txaDataString.setEditable(false);
		this.txaDataString.setBackground(new Color(109,109,109));
	}
	
	private void fillList() {
		String text = this.txaDataString.getText();		
		characters = new LinkedList<Character>();
		
		for(int i = 0; i < text.length(); i++) {
			char cr = text.charAt(i);
			if(cr != '\n' || cr != ' '|| cr != '\t' || cr != '\r') {
				this.characters.add(text.charAt(i));
			}	
		}		
		
		characters.stream().forEach(c -> {System.out.println(c);});
	}	
	
	/**
	 * Using a JFile Choser to select a text file on a especific path of the pc, readit and print the content
	 * in the text editor
	 */
	private void openFile() {
		
		int option = 0;
		var fc = new JFileChooser();
		var strContent = new StringBuilder();
		
		if(!(this.txaDataString.getText().isBlank())) {
			option = JOptionPane.showConfirmDialog(this, "The content int the editor will be replaced, you sure you want to continue?", "Open new file?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		}			
		
		if(option > 0) {
			return;
		}
		
		this.startNewEditor();
		
		int selection = fc.showOpenDialog(this);
		
		if(selection == JFileChooser.APPROVE_OPTION) {
			
			var file = fc.getSelectedFile();
			
			try(FileReader fr = new FileReader(file)){
				var bReader = new BufferedReader(fr);
				String line;
				
				while((line = bReader.readLine()) != null) {
					strContent.append(line);
					strContent.append("\n");
				}
				
				this.txaDataString.setText(strContent.toString());
				
			}catch(IOException er) {
				er.printStackTrace();
				this.txaDataString.append(er.getMessage());
			}
			
		}
		
		
	}

}
