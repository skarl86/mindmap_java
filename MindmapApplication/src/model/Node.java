/**
 * @FileName 	: Tree.java
 * @Project 	: MindmapApplication
 * @Date 		: 2014. 5. 27.
 * @Author 		: NCri
 */
package model;

import java.util.ArrayList;

/**
 * @Class		: Tree
 * @Date 		: 2014. 5. 27.
 * @Author 		: NCri
 */
public class Node {
	private int _capacity = 0;
	private int _x;
	private int _y;
	private String _text;
	private ArrayList<Object> _childs;
	private int _count;
	
	public Node(int capacity){
		_capacity = capacity;
		_childs = new ArrayList<Object>();
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
	public boolean addChild(Node node){
		if(isFull()){
			return false;
		}
		
		_childs.add(node);
		_count++;
		
		return true;
		
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
	public boolean removeChild(Node node){
		if(isEmpty()){
			return false;
		}
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
	
	/**
	 * 
	 * @method Name	: isFull
	 * @date   		: 2014. 5. 27. 
	 * @author   	: NCri
	 * @description :
	 * @return
	 */
	public boolean isFull(){
		return _count == _capacity;
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
