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
public class MapNodeModel {
	private static int _count = 0;
	private int _id = 0;
	
	{
		++_count;
		_id = _count;
	}
	// Node 데이터 형태.
	private Rectangle _bound;
	private String _text;
	private MapNodeModel _parentNode;
	
	private ArrayList<MapNodeModel> _childs = new ArrayList<MapNodeModel>();
	public MapNodeModel(){}
	
	public MapNodeModel(Rectangle bound, String inputText){
		_bound = bound;
		_text = inputText;
	}
	
	public int getID(){
		return _id;
	}
	public void setParentNode(MapNodeModel parent){
		_parentNode = parent;
	}
	
	public MapNodeModel getParentNode(){
		return _parentNode;
	}
	
	public boolean addChild(MapNodeModel node){
		node.setParentNode(this);
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
	
	public ArrayList<MapNodeModel> getChilds(){
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
	
	public static void print(MapNodeModel root, int depth){
		System.out.println(root.getText() + " " + depth);
		
		if(root.getChilds().size() == 0){
			return;
		}
		
		for(MapNodeModel child : root.getChilds()){
			print(child, depth + 1);
		}
	}
	public String printToXML(MapNodeModel root, int depth){
		StringBuffer bf = new StringBuffer();
		bf.append("<node>\n");
		bf.append(String.format("<id>%d</id>\n", root.getID()));
		
		if(root.getParentNode() == null)
			bf.append("<parentID>0</parentID>\n");
		else
			bf.append(String.format("<parentID>%d</parentID>\n",root.getParentNode().getID()));

		bf.append(String.format("<depth>%d</depth>\n", depth));
		bf.append(String.format("<x>%d</x>\n", root.getX()));
		bf.append(String.format("<y>%d</y>\n", root.getY()));
		bf.append(String.format("<width>%d</width>\n", root.getWidth()));
		bf.append(String.format("<height>%d</height>\n", root.getHeight()));
		bf.append(String.format("<text>%s</text>\n", root.getText()));
		bf.append("</node>\n");
		
		if(root.getChilds().size() == 0){
			return bf.toString();
		}
		
		for(MapNodeModel child : root.getChilds()){
			bf.append(printToXML(child, depth + 1));
		}
		
		return bf.toString();
	}
}
