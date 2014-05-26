package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JToolBar;

import controller.action.MenuAction;

/**
 * @FileName 	: MainView.java
 * @Project 	: MindmapApplication
 * @Date 		: 2014. 5. 12.
 * @Author 		: NCri
 */

/**
 * @Class : MainView
 * @Date : 2014. 5. 12.
 * @Author : NCri
 */
public class MainView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 
	private ArrayList<View> _views;
	
	// Key : Class Name | Value : View's Rectangle
	private final Map<String, Rectangle> _viewBounds = new HashMap<String, Rectangle>();
	
	private final int _MENU_BAR_HEIGHT = 40;
	/**
	 * 
	 */
	public MainView(ActionListener listner) {
		// TODO Auto-generated constructor stub
		
		// Set Main Frame Size
		setSize(800, 600);

		// Not Resizable
		setResizable(false);
		
		// Set Title
		setTitle("Mind Map V1.0");

		// Set EXIT Button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Set MenuBar
		setJMenuBar(MenuBar.getInstance(listner));
		
		// Set Layout
		// Layout 을 수동으로 처리 하기 위해서.
		setLayout(null);

		// Set Bounds
		// init Tool Bar
		View toolBar = View.getInstance(View.TOOL_BAR, listner);
		_viewBounds.put(toolBar.toString(), new Rectangle(0, 0, getBounds().width, 30));
		toolBar.setBounds(_viewBounds.get(toolBar.toString()));
		add(toolBar, BorderLayout.PAGE_START);
		
		// init Attribute View
		View attrView = View.getInstance(View.ATTRIBUTE, listner);
		_viewBounds.put(attrView.toString(), new Rectangle(0, toolBar.getBounds().height,
				250, getBounds().height - (toolBar.getBounds().height) - _MENU_BAR_HEIGHT));
		attrView.setBounds(_viewBounds.get(attrView.toString()));
		add(attrView, BorderLayout.WEST);
		
		
		// Set Attribute
		setVisible(true);
	}
}
