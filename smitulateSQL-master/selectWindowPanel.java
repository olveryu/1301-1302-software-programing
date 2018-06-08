import java.awt.*;  
import javax.swing.*;

public class selectWindowPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GridLayout grid1;
	private static TextArea textArea;
	public selectWindowPanel(){

		textArea = new TextArea(10,30);
		textArea.setFont(new Font("New Roman", Font.PLAIN, 15));
		grid1 = new GridLayout(1,1);

		textArea.setEditable(false);
		setLayout(grid1);
		textArea.setBackground(Color.YELLOW);
		add(textArea);
	}
	
	// function of print text to the select window panel
	static void print(String str){
		textArea.append(str + "\n\n");
	}
	
	// function of clear the select window panel
	static void clear(){
		textArea.setText(" ");
	}
}
