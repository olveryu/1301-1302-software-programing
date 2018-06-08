import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class directoryTree extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JTree tree; 
	private static File file;
	private GridLayout grid1;
	private static JScrollPane scrollpane;
	private String path = ".";

	public directoryTree(){
		// declare the location of the file
		file = new File(path);
		// add root node to the location
		DefaultMutableTreeNode directory = new DefaultMutableTreeNode("Directory");
		// create the tree
		tree = new JTree(directory);
		tree.setFont(new Font("New Roman", Font.PLAIN, 15));
		tree(directory,file);
		// expand the tree
		for (int i = 0; i < tree.getRowCount(); i++) {
			tree.expandRow(i);
		}

		// set color of the tree
		if (tree.getCellRenderer() instanceof DefaultTreeCellRenderer){
			final DefaultTreeCellRenderer renderer = 
					(DefaultTreeCellRenderer)(tree.getCellRenderer());
			renderer.setBackgroundNonSelectionColor(Color.lightGray);
			renderer.setBackgroundSelectionColor(Color.ORANGE);
			renderer.setTextNonSelectionColor(Color.BLUE);
			renderer.setTextSelectionColor(Color.RED);
		}

		// put the JTree into a JScrollPane.
		scrollpane = new JScrollPane();
		scrollpane.getViewport().add(tree).setBackground(Color.lightGray);

		// setup the directoryPanel
		add(scrollpane);
		grid1 = new GridLayout(1,1);
		setLayout(grid1);
		setVisible(true);
	}
	
	//function to refresh the tree
	static void reFreash(){
		DefaultMutableTreeNode directory = new DefaultMutableTreeNode("Directory");
		tree = new JTree(directory);
		tree.setFont(new Font("New Roman", Font.PLAIN, 15));
		tree(directory,file);
		//expand the tree
		for (int i = 0; i < tree.getRowCount(); i++) {
			tree.expandRow(i);
		}
		scrollpane.getViewport().remove(tree);
		scrollpane.getViewport().add(tree).setBackground(Color.lightGray);
		if (tree.getCellRenderer() instanceof DefaultTreeCellRenderer){
			final DefaultTreeCellRenderer renderer = 
					(DefaultTreeCellRenderer)(tree.getCellRenderer());
			renderer.setBackgroundNonSelectionColor(Color.lightGray);
			renderer.setBackgroundSelectionColor(Color.ORANGE);
			renderer.setTextNonSelectionColor(Color.BLUE);
			renderer.setTextSelectionColor(Color.RED);
		}
	}

	
	// function to show the directory tree
	private static void tree(DefaultMutableTreeNode curTop, File dir) {
		File[] childs = dir.listFiles();
		if (childs != null) {
			if(childs.length > 0){
				for (int i = 0; i < childs.length; i++) {
					String s = childs[i].getName();
					if (childs[i].isDirectory() && !s.equals("bin") && !s.equals("icon") && !s.equals("src") && !s.equals("temp.txt") ) {
						String newPath = childs[i].getPath();
						dir = new File(newPath);
						DefaultMutableTreeNode subDir = new DefaultMutableTreeNode(childs[i].getName());
						curTop.add(subDir);
						tree(subDir,dir);
					}else if(s.indexOf("txt") != -1){
						DefaultMutableTreeNode subFile = new DefaultMutableTreeNode(childs[i].getName());
						curTop.add(subFile);
					}
				} 
			}else{
				DefaultMutableTreeNode subDir = new DefaultMutableTreeNode("..");
				curTop.add(subDir);
			}
		}
	}

}
