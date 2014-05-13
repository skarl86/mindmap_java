/**
 * @FileName 	: View.java
 * @Project 	: MindmapApplication
 * @Date 		: 2014. 5. 12.
 * @Author 		: NCri
 */
package view;

import java.awt.Color;

import javax.swing.JPanel;

/**
 * @Class		: View
 * @Date 		: 2014. 5. 12.
 * @Author 		: NCri
 */
abstract public class View extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public View(){
		this(Color.WHITE);
	}
	
	public View(Color color){
		setSize(100, 100);
		setBackground(color);
	}
	public String toString(){
		return this.getClass().getName();
	}
}
