/**
 * @FileName 	: Tree.java
 * @Project 	: MindmapApplication
 * @Date 		: 2014. 5. 27.
 * @Author 		: NCri
 */
package model;

import java.awt.Rectangle;
import java.util.ArrayList;

import view.MapNode;

/**
 * @Class		: Node
 * @Date 		: 2014. 5. 27.
 * @Author 		: NCri
 */
public class Node {
	
	// Node 데이터 형태.
	private Rectangle _bound;
	private String _text;
	private Node _parentNode;
	
	private ArrayList<Node> _childs = new ArrayList<Node>();
	
	public Node(Rectangle bound, String inputText){
		_bound = bound;
		_text = inputText;
	}
	
	public void setParentNode(Node parent){
		_parentNode = parent;
	}
	
	public Node getParentNode(){
		return _parentNode;
	}
	
	public boolean addChild(Node node){
		return _childs.add(node);
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
		return _bound.x;
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
		return _bound.y;
	}

	public int getWidth(){
		return _bound.width;
	}
	
	public int getHeight(){
		return _bound.height;
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

	public Rectangle getBounds(){
		return _bound;
	}
	
	public void setBounds(Rectangle bound){
		_bound = bound;
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
		_bound.x = x;
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
		_bound.y = y;
	}
	
	public void setWidth(int width){
		_bound.width = width;
	}

	public void setHeight(int height){
		_bound.height = height;
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
