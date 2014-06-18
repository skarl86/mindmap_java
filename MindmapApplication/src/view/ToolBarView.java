/**
 * @FileName 	: ToolBar.java
 * @Project 	: MindmapApplication
 * @Date 		: 2014. 5. 26.
 * @Author 		: NCri
 */
package view;

import java.awt.event.ActionListener;
import java.net.URL;
import java.util.EventListener;

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

		button = makeNavigationButton("", MenuBar.NEW, "새로운 마인드 맵 생성.",
				MenuBar.NEW, listner);
		toolBar.add(button);

		// first button
		button = makeNavigationButton("", MenuBar.OPEN, "마인드 맵 열기.",
				MenuBar.OPEN, listner);
		toolBar.add(button);

		// second button
		button = makeNavigationButton("", MenuBar.SAVE, "마인드 맵 저장.",
				MenuBar.SAVE, listner);
		toolBar.add(button);

		// third button
		button = makeNavigationButton("", MenuBar.SAVE_AS, "다른 이름으로 마인드 맵 저장.",
				MenuBar.SAVE_AS, listner);

		toolBar.add(button);
	}

	private JButton makeNavigationButton(String imageName,
			String actionCommand, String toolTipText, String altText,
			ActionListener listner) {
		// Look for the image.
		String imgLocation = "images/" + imageName + ".gif";
		URL imageURL = ToolBarView.class.getResource(imgLocation);

		// Create and initialize the button.
		JButton button = new JButton();
		button.setActionCommand(actionCommand);
		button.setToolTipText(toolTipText);
		button.addActionListener(listner);

		if (imageURL != null) { // image found
			button.setIcon(new ImageIcon(imageURL, altText));
		} else { // no image found
			button.setText(altText);
			System.err.println("Resource not found: " + imgLocation);
		}

		return button;
	}
}
