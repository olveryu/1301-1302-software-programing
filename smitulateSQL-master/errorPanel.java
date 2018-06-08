import java.awt.*;  
import javax.swing.*;

public class errorPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GridLayout grid1;
	private static TextArea textArea;
	public errorPanel(){

		textArea = new TextArea(10,30);
		textArea.setFont(new Font("New Roman", Font.PLAIN, 15));
		grid1 = new GridLayout(1,1);
		
		textArea.setEditable(false);
		setLayout(grid1);
		textArea.setBackground(Color.pink);
		add(textArea);
	}
	
	// function of print out the status
	static void printError(){
		String dbName = ".";
		String tableName = "error.txt";
		DBCommands commands = new DBCommands();
		String error = commands.print(dbName, tableName);
		textArea.setText(error);
	}
	static void printFileError(){
		textArea.setText("File could not be opened");
	}


}
