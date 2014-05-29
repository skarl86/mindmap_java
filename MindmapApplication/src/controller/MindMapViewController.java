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
import java.io.File;

import javax.swing.JFrame;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

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
	
	// �앹뾽 硫붾돱媛��ㅽ뻾��Node 媛믪쓣 ��옣��蹂�닔.
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
	 * 踰꾪듉 �≪뀡 �대깽�몃� �꾨떖 諛쏅뒗��
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		System.out.println("紐⑤뱺 �≪뀡��Controller 媛�泥섎━�쒕떎.");
		System.out.println(e.getActionCommand());
		if(MenuBar.OPEN.equals(e.getActionCommand())){
			JFileChooser chooser = new JFileChooser("C:/");
			FileFilter filter = new FileNameExtensionFilter("XML files", "XML");
			chooser.addChoosableFileFilter(filter);
			
			int value = chooser.showOpenDialog(null);
			if(value == JFileChooser.APPROVE_OPTION){
				System.out.println("방금 선택하신 파일 : "+chooser.getSelectedFile().getName());
				File file = chooser.getSelectedFile();
				System.out.println("파일이 있는 디렉토리 : "+file);
			}
			
		}else if(MenuBar.SAVE.equals(e.getActionCommand())){
			System.out.println("硫붾돱&�대컮 �뚯씪 ��옣.");
		}else if(MenuBar.SAVE_AS.equals(e.getActionCommand())){
			System.out.println("硫붾돱&��諛��뚯씪 �ㅻⅨ �대쫫�쇰줈 ��옣.");
		}else if(MenuBar.CLOSE.equals(e.getActionCommand())){
			System.out.println("硫붾돱&��諛��꾨줈洹몃옩 �リ린.");
		}else if(AttributeView.BUTTON_NAME_CHANGE.equals(e.getActionCommand())){
			System.out.println("�띿꽦 酉�蹂�꼍.");
		}else if(PopUpMenu.ACTION_NODE_CREATE.equals(e.getActionCommand())){
			System.out.println("留덉씤��留��앹뾽 硫붾돱 - �앹꽦.");
			// �몃뱶 �앹꽦.
			MindMapView mapView = (MindMapView) _mainFrame.getView(View.MIND_MAP);
			mapView.addMapNode(_willNodePoint);
			
		}else if(PopUpMenu.ACTION_NODE_LINK_CREATE.equals(e.getActionCommand())){
			System.out.println("留덉씤��留��앹뾽 硫붾돱 - 留곹겕 �곌껐.");
		}else if(PopUpMenu.ACTION_NODE_DELETE.equals(e.getActionCommand())){
			System.out.println("留덉씤��留��앹뾽 硫붾돱 - ��젣.");
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
			System.out.println("�ъ씤����옣.");
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
			System.out.println("�ъ씤��媛깆떊.");
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
