import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class menuPanel extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JMenu file, edit, help;
	JMenuItem open, run, save, copy, paste, preferences, about;
	JFileChooser fc;

	public menuPanel() {


		MenusListener listener = new MenusListener();

		file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_A);
		this.add(file);

		open = new JMenuItem("Open SQL script");
		file.add(open);
		open.addActionListener(listener);

		run = new JMenuItem("Run SQL script");
		file.add(run);
		run.addActionListener(listener);

		save = new JMenuItem("Save SQL script");
		file.add(save);
		save.addActionListener(listener);

		edit = new JMenu("Edit");
		edit.setMnemonic(KeyEvent.VK_A);
		this.add(edit);

		copy = new JMenuItem("Copy");
		edit.add(copy);
		copy.addActionListener(listener);

		paste = new JMenuItem("Paste");
		edit.add(paste);
		paste.addActionListener(listener);

		preferences = new JMenuItem("Preferences");
		edit.add(preferences);
		preferences.addActionListener(listener);

		help = new JMenu("Help");
		this.add(help);

		about = new JMenuItem("About");
		help.add(about);
		about.addActionListener(listener);
	}

	class MenusListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == open){
				fc = new JFileChooser();
				fc.showOpenDialog(fc.getAccessory());
				File f = fc.getSelectedFile();
				if(f != null){
					textAreaPanel.open(fc.getSelectedFile());
				}
			}
			else if(e.getSource() == run){
				fc = new JFileChooser();
				fc.showOpenDialog(fc.getAccessory());
				File f = fc.getSelectedFile();
				if(f != null){
					textAreaPanel.open(fc.getSelectedFile());
				}
				textAreaPanel.runAll();
			}
			else if(e.getSource() == save){
				DBCommands commands = new DBCommands();
				//save commands to txt file
				fc = new JFileChooser();
				fc.showSaveDialog(fc.getAccessory());
				if(fc.getSelectedFile() != null){
					commands.createTable(fc.getCurrentDirectory().getPath(),fc.getSelectedFile().getName());
					textAreaPanel.saveFile(fc.getCurrentDirectory().getPath(), fc.getSelectedFile().getName());
				}
			}
			else if(e.getSource() == copy){
				//copy selected text
				textAreaPanel.copy();
			}
			else if(e.getSource() == paste){
				//paste selected text
				textAreaPanel.paste();
			}
			else if(e.getSource() == preferences){
				PreferencesPanel panel = new PreferencesPanel();
				JFrame frame = new JFrame("Preferances");
				frame.add(panel);
				frame.pack();
				frame.setVisible(true);
				frame.setLocation(500, 300);
				frame.setSize(1000,200);
				frame.setResizable(false);

			}
			else if(e.getSource() == about){
				ImageIcon icon = new ImageIcon("./icon/victorlawson.jpg");
				JPanel about = new JPanel();
				about.setLayout(new GridLayout(4,1));
				JLabel label = new JLabel("This project is inspried by Victor Lawson");
				label.setFont(new Font("Comic Sans", Font.PLAIN,40));
				about.add(label);
				label = new JLabel("Function: Designed to work similarly to MYSQL Workbench.");
				label.setFont(new Font("Comic Sans", Font.PLAIN,15));
				about.add(label);
				label = new JLabel("Copyright: Edwin Yan and Daniel Harper.");
				label.setFont(new Font("Comic Sans", Font.PLAIN,15));
				about.add(label);
				label = new JLabel("Year: 2017");
				label.setFont(new Font("Comic Sans", Font.PLAIN,15));
				about.add(label);
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame,
						about, 
						"About", JOptionPane.NO_OPTION, icon);
			}
		}
	}

}

