
import java.awt.GridLayout;


import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class PreferencesPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JRadioButton button;
	JLabel label;
	JCheckBox checkBox;
	JTextField textField;
	
	public PreferencesPanel() {
		
		this.setLayout(new GridLayout(3,4));
		label = new JLabel("Toggle:");
		this.add(label);
		button = new JRadioButton("Colored Backgrounds");
		this.add(button);
		button.setSelected(true);
		button = new JRadioButton("Show Directory Tree");
		this.add(button);
		button.setSelected(true);
		button = new JRadioButton("Large Icons");
		this.add(button);
		label = new JLabel("Options:");
		this.add(label);
		checkBox = new JCheckBox("Show Line Numbers");
		this.add(checkBox);
		checkBox = new JCheckBox("Hide Status/Error Logs");
		this.add(checkBox);
		checkBox = new JCheckBox("Allow Bluetooth File Drop");
		this.add(checkBox);
		label = new JLabel("Change Font (Name, style, size):");
		this.add(label);
		textField = new JTextField("Arial");
		textField.setToolTipText("Enter font name here");
		this.add(textField);
		textField = new JTextField("Plain");
		textField.setToolTipText("Enter font style here");
		this.add(textField);
		textField = new JTextField("18");
		textField.setToolTipText("Enter font size here");
		this.add(textField);
	}

}
