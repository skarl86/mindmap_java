/**
 * @FileName 	: MindMapViewController.java
 * @Project 	: MindmapApplication
 * @Date 		: 2014. 5. 26.
 * @Author 		: NCri
 */
package controller;

import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import view.AttributeView;
import view.MainView;
import view.MapNode;
import view.MenuBar;
import view.MindMapView;
import view.PopUpMenu;
import view.View;

/**
 * @Class		: MindMapViewController
 * @Date 		: 2014. 5. 26.
 * @Author 		: NCri
 */
public class MindMapViewController extends ViewController implements ActionListener, MouseListener, MouseMotionListener, PopupMenuListener{
	
	private MainView _mainFrame;
	private Point _willNodePoint;
	
	private Point _pressedPoint;
	
	// 팝업 메뉴가 실행된 Node 값을 저장할 변수.
	private MapNode _selectedMapNode;
	public MindMapViewController(){
		_mainFrame = new MainView(this);
		for(Component comp : _mainFrame.getComponents())
			System.out.println(comp);
	}
	// Attribute View Action
	public void changeNodeStatus(){
		
	}
	
	// Mind Map Action
	public void moveNode(){
		
	}
	
	public static void main(String[] args){
		ViewController vc = ViewController.getInstance(MIND_MAP);
	}
	/* 
	 * 버튼 액션 이벤트를 전달 받는다.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		System.out.println("모든 액션은 Controller 가 처리한다.");
		System.out.println(e.getActionCommand());
		if(MenuBar.OPEN.equals(e.getActionCommand())){
			System.out.println("메뉴&툴바 파일 열기.");			
		}else if(MenuBar.SAVE.equals(e.getActionCommand())){
			System.out.println("메뉴&툴바 파일 저장.");
		}else if(MenuBar.SAVE_AS.equals(e.getActionCommand())){
			System.out.println("메뉴&툴 바 파일 다른 이름으로 저장.");
		}else if(MenuBar.CLOSE.equals(e.getActionCommand())){
			System.out.println("메뉴&툴 바 프로그램 닫기.");
		}else if(AttributeView.BUTTON_NAME_CHANGE.equals(e.getActionCommand())){
			System.out.println("속성 뷰 변경.");
		}else if(PopUpMenu.ACTION_NODE_CREATE.equals(e.getActionCommand())){
			System.out.println("마인드 맵 팝업 메뉴 - 생성.");
			// 노드 생성.
			MindMapView mapView = (MindMapView) _mainFrame.getView(View.MIND_MAP);
			mapView.addMapNode(_willNodePoint);
			
		}else if(PopUpMenu.ACTION_NODE_LINK_CREATE.equals(e.getActionCommand())){
			System.out.println("마인드 맵 팝업 메뉴 - 링크 연결.");
		}else if(PopUpMenu.ACTION_NODE_DELETE.equals(e.getActionCommand())){
			System.out.println("마인드 맵 팝업 메뉴 - 삭제.");
			MindMapView mapView = (MindMapView) _mainFrame.getView(View.MIND_MAP);
			mapView.removeMapNode(_selectedMapNode);
		}
		
	}
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		_willNodePoint = e.getPoint();
		
		if(MouseEvent.BUTTON3 == e.getButton()){
			System.out.println(e.getSource());
			
			int popMenuType = -1;
			if(e.getSource() instanceof MapNode){
				_selectedMapNode = (MapNode) e.getSource();
				popMenuType = PopUpMenu.TYPE_NODE_MENU2;
			}else if(e.getSource() instanceof MindMapView){
				popMenuType = PopUpMenu.TYPE_NODE_MENU1;
			}
			
			PopUpMenu pop = new PopUpMenu(popMenuType, this);			
			pop.show(e.getComponent(), e.getX(), e.getY());
		}
		
	}
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e);
		if(e.getSource() instanceof MapNode){
			System.out.println("포인트 저장.");
			_pressedPoint = e.getPoint();
		}
		
	}
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() instanceof MapNode){
			System.out.println("포인트 갱신.");
			int diffX = _pressedPoint.x - e.getPoint().x;
			int diffY = _pressedPoint.y - e.getPoint().y;
			
			MapNode node = (MapNode) e.getSource();
			Rectangle curBounds = node.getBounds();
			node.setBounds(curBounds.x - diffX, curBounds.y - diffY, curBounds.width, curBounds.height);			
		}		
	}
	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
//		System.out.println(e);
		
	}
	/* (non-Javadoc)
	 * @see javax.swing.event.PopupMenuListener#popupMenuCanceled(javax.swing.event.PopupMenuEvent)
	 */
	@Override
	public void popupMenuCanceled(PopupMenuEvent e) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see javax.swing.event.PopupMenuListener#popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent)
	 */
	@Override
	public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
		// TODO Auto-generated method stub
	}
	/* (non-Javadoc)
	 * @see javax.swing.event.PopupMenuListener#popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent)
	 */
	@Override
	public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
		// TODO Auto-generated method stub
	}
}
