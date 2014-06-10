/**
 * @FileName 	: CanvasView.java
 * @Project 	: MindmapApplication
 * @Date 		: 2014. 5. 12.
 * @Author 		: NCri
 */
package view;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;


/**
 * @Class		: CanvasView
 * @Date 		: 2014. 5. 12.
 * @Author 		: NCri
 */
public class MindMapView extends View{
	private EventListener _listner;
	private Map<Point, Point> _lineMappingTable = new HashMap<Point, Point>();
	
	public MindMapView(EventListener listner){
		_listner = listner;
		
		setLayout(null);
		addMouseListener((MouseListener)listner);
		addMouseMotionListener((MouseMotionListener)listner);
	}
	
	public MapNode addMapNode(Point point){
		MapNode node = new MapNode(point);
		node.addMouseMotionListener((MouseMotionListener) _listner);
		node.addMouseListener((MouseListener)_listner);
		add(node);
		
		repaint();
		
		return node;
	}
	
	public void removeMapNode(MapNode view){
		remove(view);
		repaint();
	}
	
	public void setRootList(ArrayList<MapNode> rootList){
				
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}
}
