import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class userInterface {
	public static void main (String[] args){
		// declare frame
		JFrame frame;
		// declared grid bag layout
		GridBagLayout grid1;
		JLabel message;
		ImageIcon iconImage;
		
		// declared all panel
		menuPanel menu = new menuPanel();
		iconPanel icon = new iconPanel();
		directoryTree directory = new directoryTree();
		textAreaPanel textArea = new textAreaPanel();
		selectWindowPanel selectWindow = new selectWindowPanel();
		errorPanel error = new errorPanel();
		statusPanel status = new statusPanel();
		
		
		frame = new JFrame("SQL DATABASE");
		grid1 = new GridBagLayout();
		frame.setLayout(grid1);
		
		GridBagConstraints c = new GridBagConstraints();
		
		// add menu
		c.fill = GridBagConstraints.BOTH;
		frame.setJMenuBar(menu);
		
		// add icon
	    c.gridx = 0;
	    c.gridy = 0;
		frame.add(icon);
		
		// add directory
		c.insets = new Insets(2, 2, 2, 2); 
		c.ipady = 10;
	    c.gridx = 0;
	    c.gridy = 1;
	    c.gridheight = 8;
		frame.add(directory,c);
		
		// sql label
		message = new JLabel("SQL Command editor");
		message.setFont(new Font("New Roman", Font.BOLD, 15));
	    c.gridx = 1;
	    c.gridy = 0;
		c.gridwidth = 2;
	    c.gridheight = 1;
		frame.add(message,c);
		
		// add textArea
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.ipady = 0;
	    c.gridx = 2;
	    c.gridy = 2;
		frame.add(textArea,c);

		// select label
		message = new JLabel("select table row");
		message.setFont(new Font("New Roman", Font.BOLD, 15));
	    c.gridx = 2;
	    c.gridy = 3;
		frame.add(message,c);
		
		// add selectWdindow
	    c.gridx = 2;
	    c.gridy = 4;
		frame.add(selectWindow,c);
		
		// status label 
		iconImage = new ImageIcon("./icon/Status icon.png");
		Image image = iconImage.getImage();
		Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);  
		iconImage = new ImageIcon(newimg);
		message = new JLabel("Status",iconImage,10);
		message.setFont(new Font("New Roman", Font.BOLD, 20));
	    c.gridx = 1;
	    c.gridy = 6;
	    c.gridheight = 1;
		frame.add(message,c);
		
		// error label 
		iconImage = new ImageIcon("./icon/Error icon.png");
		image = iconImage.getImage();
		newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);  
		iconImage = new ImageIcon(newimg);
		message = new JLabel(iconImage);
		message = new JLabel("Error",iconImage,10);
		message.setFont(new Font("New Roman", Font.BOLD, 20));
	    c.gridx = 3;
	    c.gridy = 6;
		frame.add(message,c);
		
		// add error textArea
	    c.gridx = 3;
	    c.gridy = 7;
	    c.gridwidth = 1;
		frame.add(error,c);
		
		// add status textArea
	    c.gridx = 2;
	    c.gridy = 7;
		frame.add(status,c);
		
		// set up the frame
		frame.setTitle("DATABASE");
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(250,100);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}