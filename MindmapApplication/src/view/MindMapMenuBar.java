/**
 * @FileName 	: MindMapMenuBar.java
 * @Project 	: MindmapApplication
 * @Date 		: 2014. 5. 13.
 * @Author 		: NCri
 */
package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * @Class		: MindMapMenuBar
 * @Date 		: 2014. 5. 13.
 * @Author 		: NCri
 */
public class MindMapMenuBar extends JMenuBar{
	
	public static JMenuBar init(){
		return MindMapMenuBar.initWithMenus(null);
	}
	public static JMenuBar initWithMenus(String[] menus){
		JMenuBar menuBar = new MindMapMenuBar();
		JMenu fileMenu = new JMenu("파일");
		menuBar.add(fileMenu);
		
		fileMenu.add(new JMenuItem("New"));
		return menuBar;		
	}

}
