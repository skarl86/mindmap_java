/**
 * @FileName 	: PopUpMenu.java
 * @Project 	: MindmapApplication
 * @Date 		: 2014. 5. 27.
 * @Author 		: NCri
 */
package view;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.event.PopupMenuListener;

/**
 * @Class : PopUpMenu
 * @Date : 2014. 5. 27.
 * @Author : NCri
 */
public class PopUpMenu extends JPopupMenu {
	public static final int TYPE_NODE_MENU1 = 0;
	public static final int TYPE_NODE_MENU2 = 1;
	
	public static final String ACTION_NODE_CREATE = "노드 생성";
	public static final String ACTION_NODE_LINK_CREATE = "연결 노드 생성";
	public static final String ACTION_NODE_DELETE = "노드 삭제";

	public int _type;

	public PopUpMenu(int type, EventListener listner) {
		_type = type;
		JMenuItem item = null;
		
		switch (_type) {
		case TYPE_NODE_MENU1:
			item = new JMenuItem(ACTION_NODE_CREATE);
			item.setActionCommand(ACTION_NODE_CREATE);
			item.addActionListener((ActionListener) listner);
			add(item);
			break;
		case TYPE_NODE_MENU2:
			item = new JMenuItem(ACTION_NODE_LINK_CREATE);
			item.setActionCommand(ACTION_NODE_LINK_CREATE);
			item.addActionListener((ActionListener) listner);
			add(item);
			
			item = new JMenuItem(ACTION_NODE_DELETE);
			item.setActionCommand(ACTION_NODE_DELETE);
			item.addActionListener((ActionListener) listner);
			add(item);
			break;
		default:
			break;
		}
		
		addPopupMenuListener((PopupMenuListener) listner);
	}
}
