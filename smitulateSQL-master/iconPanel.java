import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class iconPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton runAll, runOne, sweep; 
	private JPanel buttonPanel;
	private GridLayout grid1;

	public iconPanel(){
		ImageIcon icon;
		
		// icon button of run All
		icon = new ImageIcon("./icon/AllCommands.png");
		Image image = icon.getImage();
		Image newimg = image.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);  
		icon = new ImageIcon(newimg);
		runAll = new JButton(icon);
		runAll.setToolTipText("Run All Command");

		// icon button of one command
		icon = new ImageIcon("./icon/1 command.png");
		image = icon.getImage();
		newimg = image.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH); 
		icon = new ImageIcon(newimg);
		runOne = new JButton(icon);
		runOne.setToolTipText("Run one Command");

		// icon button of sweep
		icon = new ImageIcon("./icon/sweep.png");
		image = icon.getImage();
		newimg = image.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH); 
		icon = new ImageIcon(newimg);
		sweep = new JButton(icon);
		sweep.setToolTipText("sweep");

		// declare a new button panel
		buttonPanel = new JPanel(); 
		buttonListener listener = new buttonListener();
		
		// add listener for the button
		runAll.addActionListener(listener);
		runOne.addActionListener(listener);
		sweep.addActionListener(listener);
		
		// add button to the button panel
		buttonPanel.add(runAll);
		buttonPanel.add(runOne);
		buttonPanel.add(sweep);

		// set up the icon panel
		grid1 = new GridLayout(1,1);
		buttonPanel.setLayout(grid1);
		add(buttonPanel, BorderLayout.PAGE_START);
	}
	
	class buttonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			DBCommands command = new DBCommands();
			if (e.getSource() == runAll) {
				command.dropTable(".", "temp.txt");
				selectWindowPanel.clear();
				textAreaPanel.runAll();
				directoryTree.reFreash();
			}else if(e.getSource() == runOne) {
				command.dropTable(".", "temp.txt");
				selectWindowPanel.clear();
				textAreaPanel.runOne();
				directoryTree.reFreash();
			}else{
				textAreaPanel.sweep();
			}
		}
	}
}
