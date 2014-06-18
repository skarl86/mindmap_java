package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
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
	private static int _MENU_BAR_HEIGHT = 40;
	private Map<Integer, View> _views = new HashMap<Integer, View>();
	/**
	 * 
	 * @param listner
	 */
	public MainView(EventListener listner) {
		// TODO Auto-generated constructor stub
		
		// Set Main Frame Size
		setSize(800, 600);
		setPreferredSize(new Dimension(800, 600));
		setMaximumSize(new Dimension(1024, 768));
		// Not Resizable
//		setResizable(false);
		
		// Set Title
		setTitle("Mind Map V1.0");

		// Set EXIT Button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Set MenuBar
		setJMenuBar(MenuBar.getInstance((ActionListener)listner));
		
		// Set Layout
		// Layout 을 수동으로 처리 하기 위해서.
		setLayout(new BorderLayout());

		// Set Bounds of All View		
		// 가독성을 위해서 변수로 접근.
		Rectangle mainFrameBounds = getBounds();		
		Rectangle toolBarBounds = new Rectangle(
				0, 0, 
				mainFrameBounds.width, 30);
		
		Rectangle attributeBounds = new Rectangle(
				0, mainFrameBounds.x + toolBarBounds.getBounds().height,
				250, mainFrameBounds.height - (toolBarBounds.getBounds().height) - _MENU_BAR_HEIGHT); 
		
		Rectangle mindMapBounds = new Rectangle(
				attributeBounds.width, attributeBounds.y,
				mainFrameBounds.width - attributeBounds.width, attributeBounds.height);
		
		// init Tool Bar
		View toolBar = View.getInstance(View.TOOL_BAR, listner);
		toolBar.setBounds(toolBarBounds);
		add(toolBar, BorderLayout.PAGE_START);
		_views.put(View.TOOL_BAR, toolBar);
		
		// init Attribute View
		View attrView = View.getInstance(View.ATTRIBUTE, listner);
		attrView.setBounds(attributeBounds);
		attrView.setPreferredSize(attributeBounds.getSize());
		add(attrView, BorderLayout.WEST);
		_views.put(View.ATTRIBUTE, attrView);
		
		// init MindMap View
		View mindMapView = View.getInstance(View.MIND_MAP, listner);
		mindMapView.setBackground(Color.LIGHT_GRAY);
		mindMapView.setMaximumSize(this.getMaximumSize());
		mindMapView.setPreferredSize(this.getMaximumSize());
		mindMapView.setBounds(mindMapBounds);
		JScrollPane scroll = new JScrollPane();
		scroll.setSize(this.getMaximumSize());
		scroll.setPreferredSize(this.getMaximumSize());
		scroll.setViewportView(mindMapView);
		add(scroll, BorderLayout.CENTER);
		_views.put(View.MIND_MAP, mindMapView);
		
		// Set Attribute
		setVisible(true);
	}
	/**
	 * 
	 * @method Name	: getView
	 * @date   		: 2014. 5. 26. 
	 * @author   	: NCri
	 * @description :
	 * @param type
	 * @return
	 */
	public View getView(int type){
		return _views.get(type);		
	}
}
