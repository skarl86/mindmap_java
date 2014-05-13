package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;

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
	/**
	 * 
	 */
	public MainView() {
		// TODO Auto-generated constructor stub
		
		// Set Title
		setTitle("Mind Map V1.0");

		// Set EXIT Button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set Layout
		// Layout 을 수동으로 처리 하기 위해서.
		setLayout(null);

		// Init View
		View attrView = new AttributeView(Color.WHITE);
		
		// Set Bounds
		_viewBounds.put(attrView.toString(), new Rectangle(0, 0, 200, 200));
		attrView.setBounds(_viewBounds.get(attrView.toString()));
		add(attrView);

		// Set Size
		setSize(800, 600);

		
		
		// Set Attribute
		setVisible(true);
	}
}
