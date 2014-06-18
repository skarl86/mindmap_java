/**
 * @FileName 	: ToolBar.java
 * @Project 	: MindmapApplication
 * @Date 		: 2014. 5. 26.
 * @Author 		: NCri
 */
package view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.EventListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 * @Class : ToolBar
 * @Date : 2014. 5. 26.
 * @Author : NCri
 */
public class ToolBarView extends View {

	public ToolBarView(EventListener listner) {
		// super(new BorderLayout());

		// init JToolbar
		JToolBar toolBar = new JToolBar();

		// Create the Button
		addButtons(toolBar, (ActionListener) listner);

		//
		toolBar.setFloatable(false);
		toolBar.setRollover(true);

//		// Seperator
//		toolBar.addSeparator();

		// Add JToolBar
		add(toolBar);
	}

	private void addButtons(JToolBar toolBar, ActionListener listner) {
		JButton button = null;

		button = makeNavigationButton("new", MenuBar.NEW, "새로운 마인드 맵 생성.",
				MenuBar.NEW, listner);
//		button.setPreferredSize(new Dimension(50, 50));
		toolBar.add(button);

		// first button
		button = makeNavigationButton("open", MenuBar.OPEN, "마인드 맵 열기.",
				MenuBar.OPEN, listner);
//		button.setPreferredSize(new Dimension(50, 50));
		toolBar.add(button);

		// second button
		button = makeNavigationButton("save", MenuBar.SAVE, "마인드 맵 저장.",
				MenuBar.SAVE, listner);
//		button.setPreferredSize(new Dimension(50, 50));
		toolBar.add(button);

		// third button
		button = makeNavigationButton("save_as", MenuBar.SAVE_AS, "다른 이름으로 마인드 맵 저장.",
				MenuBar.SAVE_AS, listner);
//		button.setPreferredSize(new Dimension(50, 50));
		toolBar.add(button);
	}

	private JButton makeNavigationButton(String imageName,
			String actionCommand, String toolTipText, String altText,
			ActionListener listner) {
		// Look for the image.
		File file = new File(".");
		File imgFile = null;
		String imgLocation = null;
		
//		try {
//			imgLocation = file.getCanonicalPath() + "/images/" + imageName + ".gif";
//			System.out.println(imgLocation);
//			
//			imgFile = new File(file.getCanonicalPath() + "/images/" + imageName + ".gif");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		
		// Create and initialize the button.
		JButton button = new JButton();
		button.setActionCommand(actionCommand);
		button.setToolTipText(toolTipText);
		button.addActionListener(listner);


		if (imgFile!= null) { // image found
			Image img = null;
			try {
				img = ImageIO.read(imgFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Image resizedImage = 
				    img.getScaledInstance(50, 50, 0);
			button.setIcon(new ImageIcon(resizedImage, altText));
		} else { // no image found
			button.setText(altText);
			System.err.println("Resource not found: " + imgLocation);
		}

		return button;
	}
}
