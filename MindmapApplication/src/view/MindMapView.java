/**
 * @FileName 	: CanvasView.java
 * @Project 	: MindmapApplication
 * @Date 		: 2014. 5. 12.
 * @Author 		: NCri
 */
package view;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.EventListener;

/**
 * @Class : CanvasView
 * @Date : 2014. 5. 12.
 * @Author : NCri
 */
public class MindMapView extends View {
	private EventListener _listner;

	public MindMapView(EventListener listner) {
		_listner = listner;

		setLayout(null);
		addMouseListener((MouseListener) listner);
		addMouseMotionListener((MouseMotionListener) listner);
		setVisible(true);
	}
	public MapNode addMapNode(MapNode newNode){
		newNode.addMouseMotionListener((MouseMotionListener) _listner);
		newNode.addMouseListener((MouseListener) _listner);
		add(newNode);

		repaint();
		
		return newNode;

	}
	public MapNode addMapNode(Point point) {
		MapNode node = new MapNode(point);
		
		return this.addMapNode(node);
	}
	public void removeMapNodeAll(){
		for (Component comp : getComponents()){
			if (comp instanceof MapNode){
				remove(comp);
			}
		}
		repaint();
	}
	public void removeMapNode(MapNode view) {
		remove(view);
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		MapNode parent = null;
		for (Component comp : getComponents()) {
			parent = (MapNode) comp;
			// 쓰래드를 돌릴 방법이 없나??
			for (MapNode child : parent.getChilds()) {
				Rectangle parentRect = parent.getBounds();
				Rectangle childRect = child.getBounds();

				int x1 = parentRect.x + (parentRect.width / 2);
				int x2 = childRect.x + (childRect.width / 2);
				int y1 = parentRect.y + (parentRect.height / 2);
				int y2 = childRect.y + (childRect.height / 2);

				int dx = x2 - x1;
				int dy = y2 - y1;

				double rad = Math.atan2(dx, dy);
				double degree = (rad * 180) / Math.PI;
				// y축을 중심으로 degree를 구함.

				if(-45 < degree && 45 > degree){
					// 남쪽.
					y1 = parentRect.y + parentRect.height;
					y2 = childRect.y;
				}else if(45 < degree && 135 > degree){
					// 동쪽.
					x1 = parentRect.x + parentRect.width;
					x2 = childRect.x;
				}else if((135 < degree && 180 >= degree) || (-135 > degree && -179 < degree)){
					// 북쪽.
					y1 = parentRect.y;
					y2 = childRect.y + childRect.height;
				}else if(-45 > degree && -135 < degree){
					// 서쪽.
					x1 = parentRect.x;
					x2 = childRect.x +childRect.width;
				}
				
				System.out.println("기울기 => " + degree);
				g.drawLine(x1, y1, x2, y2);
			}
		}
	}

	public Point getNodeLinePointer(Rectangle parent, Rectangle child) {
		Rectangle[] area = new Rectangle[6];
		area[0].setBounds(0, 0, parent.x, parent.y);
		area[1].setBounds(parent.x, 0, parent.x, parent.y);
		area[0].setBounds(0, 0, parent.x, parent.y);
		area[0].setBounds(0, 0, parent.x, parent.y);
		area[0].setBounds(0, 0, parent.x, parent.y);
		area[0].setBounds(0, 0, parent.x, parent.y);

		return null;
	}
}
