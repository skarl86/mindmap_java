/**
 * @FileName 	: Tree.java
 * @Project 	: MindmapApplication
 * @Date 		: 2014. 5. 27.
 * @Author 		: NCri
 */
package model;

import java.util.ArrayList;

import view.MapNode;

/**
 * @Class		: Node
 * @Date 		: 2014. 5. 27.
 * @Author 		: NCri
 */
public class Node {
	
	// Node 데이터 형태.
	private int _x;
	private int _y;
	private String _text;
	
	private ArrayList<Node> _childs;
	
	public Node(MapNode node){
		_x = node.getLocation().x;
		_y = node.getLocation().y;
		_text = node.getText();
	}
	public boolean addChild(Node node){
		return _childs.add(node);
	}
	/**
	 * 
	 * @method Name	: addChild
	 * @date   		: 2014. 5. 27. 
	 * @author   	: NCri
	 * @description :
	 * @param node
	 * @return
	 */
	public boolean addChild(MapNode node){
		return _childs.add(new Node(node));		
	}
	
	/**
	 * 
	 * @method Name	: removeChild
	 * @date   		: 2014. 5. 27. 
	 * @author   	: NCri
	 * @description :
	 * @param node
	 * @return
	 */
	public boolean removeChild(MapNode node){
		return _childs.remove(node);
	}
	
	/**
	 * 
	 * @method Name	: isEmpty
	 * @date   		: 2014. 5. 27. 
	 * @author   	: NCri
	 * @description :
	 * @return
	 */
	public boolean isEmpty(){
		return _childs.isEmpty();
	}
	
	public ArrayList<Node> getChilds(){
		return _childs;
	}

	/**
	 * 
	 * @method Name	: getX
	 * @date   		: 2014. 5. 27. 
	 * @author   	: NCri
	 * @description :
	 * @return
	 */
	public int getX() {
		return _x;
	}

	/**
	 * 
	 * @method Name	: getY
	 * @date   		: 2014. 5. 27. 
	 * @author   	: NCri
	 * @description :
	 * @return
	 */
	public int getY() {
		return _y;
	}

	/**
	 * 
	 * @method Name	: getText
	 * @date   		: 2014. 5. 27. 
	 * @author   	: NCri
	 * @description :
	 * @return
	 */
	public String getText() {
		return _text;
	}

	/**
	 * 
	 * @method Name	: setX
	 * @date   		: 2014. 5. 27. 
	 * @author   	: NCri
	 * @description :
	 * @param x
	 */
	public void setX(int x) {
		_x = x;
	}

	/**
	 * 
	 * @method Name	: setY
	 * @date   		: 2014. 5. 27. 
	 * @author   	: NCri
	 * @description :
	 * @param y
	 */
	public void setY(int y) {
		_y = y;
	}

	/**
	 * 
	 * @method Name	: setText
	 * @date   		: 2014. 5. 27. 
	 * @author   	: NCri
	 * @description :
	 * @param text
	 */
	public void setText(String text) {
		_text = text;
	}
	
	
}
