/**
 * @FileName 	: View.java
 * @Project 	: MindmapApplication
 * @Date 		: 2014. 5. 12.
 * @Author 		: NCri
 */
package view;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

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
	
	public static final int MIND_MAP = 0;
	public static final int ATTRIBUTE = 1;
	public static final int TOOL_BAR = 2;
	
	protected ActionListener _listner;
	
	public View(){
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	public View(ActionListener listner){
		super();
		this._listner = listner;
	}
	public View(Color color){
		super();
		setBackground(color);
	}
	
	public static View getInstance(int type, ActionListener listner){
		View instance = null;
		if(type == View.MIND_MAP){
			instance = new MindMapView(listner);
		}else if(type == View.ATTRIBUTE){
			instance = new AttributeView(listner);
		}else if(type == View.TOOL_BAR){
			instance = new ToolBarView(listner);
		}
		return instance;
	}
	
	public static View getInstance(int type){
		return View.getInstance(type, null);
	}
		
	public String toString(){
		return this.getClass().getName();
	}
}
