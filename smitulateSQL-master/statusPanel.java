import java.awt.*;  
import javax.swing.*;

public class statusPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GridLayout grid1;
	private static TextArea textArea;
	public statusPanel(){

		textArea = new TextArea(10,30);
		textArea.setFont(new Font("New Roman", Font.PLAIN, 15));
		grid1 = new GridLayout(1,1);
		
		textArea.setEditable(false);
		setLayout(grid1);
		textArea.setBackground(Color.CYAN);
		add(textArea);
	}
	
	// function of print out the status
	static void printStatus(){
		String dbName = ".";
		String tableName = "status.txt";
		DBCommands commands = new DBCommands();
		String status = commands.print(dbName, tableName);
		textArea.setText(status);
	}
}
