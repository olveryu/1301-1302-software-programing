import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.*;

public class textAreaPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GridLayout grid1;
	private static TextArea textArea;
	private static String copyString = "";
	public textAreaPanel(){
		textArea = new TextArea(10,150);
		textArea.setFont(new Font("New Roman", Font.PLAIN, 15));
		textArea.setBackground(Color.white);
		grid1 = new GridLayout(1,1);
		
		setLayout(grid1);
		add(textArea);
	}
	
	// function of sweep
	static void sweep(){
		textArea.setText(" ");
	}
	
	// function of runAll
	static void runAll(){
		DBCommands command = new DBCommands();
		Database db = new Database();
		String input;
		String dbName = ".";
		String tableName = "temp.txt";
		input = textArea.getText().replace(";","");
		command.dropTable(dbName, tableName);
		command.createTable(dbName, tableName);
		command.insert(input, dbName, tableName);
		db.checkDatabase(tableName);
		errorPanel.printError();
		statusPanel.printStatus();
		command.dropTable(dbName, tableName);
	}
	
	// function of runOne
	static void runOne(){
		DBCommands command = new DBCommands();
		Database db = new Database();
		String input;
		String dbName = ".";
		String tableName = "temp.txt";
		input = textArea.getSelectedText().replace(";","");
		command.dropTable(dbName, tableName);
		command.createTable(dbName, tableName);
		command.insert(input, dbName, tableName);
		db.checkDatabase(tableName);
		errorPanel.printError();
		statusPanel.printStatus();
		command.dropTable(dbName, tableName);
	}

	// function of copy
	static void copy(){
		copyString = textArea.getSelectedText();
	}
	
	// function of paste
	static void paste(){
		textArea.append(copyString);
	}
	
	// function of open file
	static void open(File f){
		textArea.setText(" ");
		try{
			Scanner sc = new Scanner(f);
			while(sc.hasNextLine()){
				textArea.append(sc.nextLine() + "\n");
			}
			sc.close();
		}
		catch(FileNotFoundException e){
			errorPanel.printFileError();
		}
	}
	
	// function of saveFile
	static void saveFile(String dbName, String tableName){
		DBCommands command = new DBCommands();
		command.deleteAll(dbName, tableName);
		command.insert(textArea.getText(), dbName, tableName);
	}
}


