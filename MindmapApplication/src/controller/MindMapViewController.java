/**
 * @FileName 	: MindMapViewController.java
 * @Project 	: MindmapApplication
 * @Date 		: 2014. 5. 26.
 * @Author 		: NCri
 */
package controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Node;
import view.AttributeView;
import view.MainView;
import view.MapNode;
import view.MenuBar;
import view.MindMapView;
import view.PopUpMenu;
import view.View;

/**
 * @Class : MindMapViewController
 * @Date : 2014. 5. 26.
 * @Author : NCri
 */
public class MindMapViewController extends ViewController implements
		ActionListener, MouseListener, MouseMotionListener, PopupMenuListener {

	static enum LinkPoint {
		LEFT, RIGHT, TOP, BOTTOM
	}

	private MainView _mainFrame;
	
	private Point _selectedNodePoint;
	private Point _pressedPoint;
	
	private ArrayList<MapNode> _rootList = new ArrayList<MapNode>();

	// 루트 MapNode 를 가지고 있는 List.
	Map<Point, MapNode> mapNodeTable = new HashMap<Point, MapNode>();

	// 현재 선택 된 MapNode의 레퍼런스 변수.
	private MapNode _selectedMapNode;

	public MindMapViewController() {
		_mainFrame = new MainView(this);

		for (Component comp : _mainFrame.getComponents())
			System.out.println(comp);
	}

	public MapNode createNewNode(Point newNodePoint) {
		System.out.println("새로운 NODE를 생성.");
		MindMapView mapView = (MindMapView) _mainFrame.getView(View.MIND_MAP);

		// Add MapNode Object in MindMapView.
		MapNode newMapNode = mapView.addMapNode(newNodePoint);
		
		_rootList.add(newMapNode);
		mapView.setRootList(_rootList);
		
		return newMapNode;
	}

	public void createLinkNode(MapNode parent, LinkPoint point) {
		System.out.println("링크 NODE 생성.");

		Point linkNodePoint = _selectedMapNode.getLocation();

		switch (point) {
		case LEFT:
			linkNodePoint.x -= _selectedMapNode.getBounds().width * 1.5;
			break;
		case RIGHT:
			linkNodePoint.x += _selectedMapNode.getBounds().width * 1.5;
			break;
		case TOP:
			linkNodePoint.y -= _selectedMapNode.getBounds().height * 2;
			break;
		case BOTTOM:
			linkNodePoint.y += _selectedMapNode.getBounds().height * 2;
			break;
		default:
			break;
		}

		// Create new MapNode Object that will link to parent MapNode Object.
		MapNode willLinkNode = createNewNode(linkNodePoint);

		// Do Link new MapNode Object to selected MapNode Object.
		_selectedMapNode.addChild(willLinkNode);
		// TEST Code.
		willLinkNode.setBackground(Color.BLUE);
	}

	public void removeMapNode(MapNode mapNode) {
		System.out.println("노드 삭제.");

		MindMapView mapView = (MindMapView) _mainFrame.getView(View.MIND_MAP);

		// If it haven't childs, remove itself.
		if(mapNode.getChilds().size() == 0){
			mapNode.getParentMapNode().removeChild(mapNode);
			mapView.removeMapNode(mapNode);
			return;
		}
		
		ArrayList<MapNode> copyList = (ArrayList<MapNode>) mapNode.getChilds().clone();

		// To remove child MapNode Object.
		for(MapNode childMapNode : copyList){
			removeMapNode(childMapNode);
		}
		
		mapView.removeMapNode(mapNode);
	}

	public static void main(String[] args) {
		ViewController vc = ViewController.getInstance(MIND_MAP);
	}

	/*
	 * 踰꾪듉 �≪뀡 �대깽�몃� �꾨떖 諛쏅뒗��
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// System.out.println("紐⑤뱺 �≪뀡��Controller 媛�泥섎━�쒕떎.");
		System.out.println(e.getActionCommand());
		if (MenuBar.OPEN.equals(e.getActionCommand())) {
			JFileChooser chooser = new JFileChooser("C:/");
			FileFilter filter = new FileNameExtensionFilter("XML files", "XML");
			chooser.addChoosableFileFilter(filter);

			int value = chooser.showOpenDialog(null);
			if (value == JFileChooser.APPROVE_OPTION) {
				System.out.println("방금 선택하신 파일 : "
						+ chooser.getSelectedFile().getName());
				File file = chooser.getSelectedFile();
				System.out.println("파일이 있는 디렉토리 : " + file);
			}

		} else if (MenuBar.SAVE.equals(e.getActionCommand())) {
			System.out.println("硫붾돱&�대컮 �뚯씪 ��옣.");
		} else if (MenuBar.SAVE_AS.equals(e.getActionCommand())) {
			System.out.println("硫붾돱&��諛��뚯씪 �ㅻⅨ �대쫫�쇰줈 ��옣.");
		} else if (MenuBar.CLOSE.equals(e.getActionCommand())) {
			System.out.println("硫붾돱&��諛��꾨줈洹몃옩 �リ린.");
		} else if (AttributeView.BUTTON_NAME_CHANGE
				.equals(e.getActionCommand())) {
			System.out.println("�띿꽦 酉�蹂�꼍.");
		} else if (PopUpMenu.ACTION_NODE_CREATE.equals(e.getActionCommand())) {
			createNewNode(_selectedNodePoint);

		} else if (PopUpMenu.ACTION_NODE_LINK_CREATE.equals(e
				.getActionCommand())) {
			System.out.println(e.getSource());
			createLinkNode(_selectedMapNode, LinkPoint.TOP);

		} else if (PopUpMenu.ACTION_NODE_DELETE.equals(e.getActionCommand())) {
			removeMapNode(_selectedMapNode);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

		_selectedNodePoint = e.getPoint();

		if (MouseEvent.BUTTON3 == e.getButton()) {
			System.out.println(e.getSource());

			int popMenuType = -1;
			if (e.getSource() instanceof MapNode) {
				_selectedMapNode = (MapNode) e.getSource();
				popMenuType = PopUpMenu.TYPE_NODE_MENU2;
			} else if (e.getSource() instanceof MindMapView) {
				popMenuType = PopUpMenu.TYPE_NODE_MENU1;
			}

			PopUpMenu pop = new PopUpMenu(popMenuType, this);
			pop.show(e.getComponent(), e.getX(), e.getY());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e);
		if (e.getSource() instanceof MapNode) {
			_pressedPoint = e.getPoint();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent
	 * )
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() instanceof MapNode) {
			int diffX = _pressedPoint.x - e.getPoint().x;
			int diffY = _pressedPoint.y - e.getPoint().y;

			MapNode node = (MapNode) e.getSource();
			Rectangle curBounds = node.getBounds();
			node.setBounds(curBounds.x - diffX, curBounds.y - diffY,
					curBounds.width, curBounds.height);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		// System.out.println(e);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.event.PopupMenuListener#popupMenuCanceled(javax.swing.event
	 * .PopupMenuEvent)
	 */
	@Override
	public void popupMenuCanceled(PopupMenuEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.event.PopupMenuListener#popupMenuWillBecomeInvisible(javax
	 * .swing.event.PopupMenuEvent)
	 */
	@Override
	public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.event.PopupMenuListener#popupMenuWillBecomeVisible(javax.
	 * swing.event.PopupMenuEvent)
	 */
	@Override
	public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
		// TODO Auto-generated method stub
	}
}
