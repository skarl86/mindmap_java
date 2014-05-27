/**
 * @FileName 	: CanvasView.java
 * @Project 	: MindmapApplication
 * @Date 		: 2014. 5. 12.
 * @Author 		: NCri
 */
package view;

import java.awt.Point;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.EventListener;

import javax.swing.JLabel;


/**
 * @Class		: CanvasView
 * @Date 		: 2014. 5. 12.
 * @Author 		: NCri
 */
public class MindMapView extends View{
	private EventListener _listner;
	public MindMapView(EventListener listner){
		_listner = listner;
		setLayout(null);
		addMouseListener((MouseListener)listner);
		addMouseMotionListener((MouseMotionListener)listner);
	}
	
	public void addMapNode(Point point){
		MapNode node = new MapNode(point);
		node.addMouseMotionListener((MouseMotionListener) _listner);
		node.addMouseListener((MouseListener)_listner);
		add(node);
		repaint();
	}
	
	public void removeMapNode(MapNode view){
		remove(view);
		repaint();
	}
}
