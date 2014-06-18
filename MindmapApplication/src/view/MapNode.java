/**
 * @FileName 	: NodeView.java
 * @Project 	: MindmapApplication
 * @Date 		: 2014. 5. 26.
 * @Author 		: NCri
 */
package view;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.MindMapTreeInterface;

/**
 * @Class		: NodeView
 * @Date 		: 2014. 5. 26.
 * @Author 		: NCri
 */
public class MapNode extends JLabel implements MindMapTreeInterface<MapNode>{
	
	private final int _LIMIT_CHILDS_COUNT = 4;
	private int _count = 0;
	private MapNode _parentNode;
	
	private int _id = 0;
	
	{
		++_count;
		_id = _count;
	}
	
	private ArrayList<MapNode>_childs = new ArrayList<MapNode>();
	
	/**
	 * 
	 * @param point
	 */
	public MapNode(Point point){
		super();
		setText("Node");
		setVerticalAlignment(SwingConstants.CENTER);
		setHorizontalAlignment(SwingConstants.CENTER);
		setOpaque(true);
		setBackground(Color.YELLOW);
		System.out.println(point);
		setBounds(new Rectangle(point.x, point.y, 100, 50));
		setVisible(true);
	}
	public void setID(int id){
		_id = id;
	}
	
	public int getID(){
		return _id;
	}
	/**
	 * 
	 * @method Name	: setParentMapNode
	 * @date   		: 2014. 6. 2. 
	 * @author   	: NCri
	 * @description :
	 * @param parent
	 */
	public void setParentNode(MapNode parent){
		_parentNode = parent;
	}
	/**
	 * 
	 * @method Name	: getParentMapNode
	 * @date   		: 2014. 6. 2. 
	 * @author   	: NCri
	 * @description :
	 * @return
	 */
	public MapNode getParentNode(){
		return _parentNode;
	}
	/**
	 * 
	 * @method Name	: addChild
	 * @date   		: 2014. 6. 2. 
	 * @author   	: NCri
	 * @description :
	 * @param child
	 * @return
	 */
	public MapNode addChild(MapNode child){
//		if(_count == _LIMIT_CHILDS_COUNT)
//			return null;
		child.setParentNode(this);
		_childs.add(child);
		_count++;
		return child;
	}
	/**
	 * 
	 * @method Name	: removeChild
	 * @date   		: 2014. 6. 2. 
	 * @author   	: NCri
	 * @description :
	 * @param child
	 * @return
	 */
	public void removeChild(MapNode child){		
		_childs.remove(child);
		_count--;
	}
	/**
	 * 
	 * @method Name	: getChilds
	 * @date   		: 2014. 6. 2. 
	 * @author   	: NCri
	 * @description :
	 * @return
	 */
	public ArrayList<MapNode> getChilds(){		
		return _childs;
		
	}
	
	public void setSelected(boolean isSelected){
		if(isSelected){
			setBorder(new LineBorder(Color.RED));
		}else setBorder(new EmptyBorder(0, 0, 0, 0));
	}
}
