/**
 * @FileName 	: MindMapViewController.java
 * @Project 	: MindmapApplication
 * @Date 		: 2014. 5. 26.
 * @Author 		: NCri
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import view.MainView;
import controller.action.MenuAction;

/**
 * @Class		: MindMapViewController
 * @Date 		: 2014. 5. 26.
 * @Author 		: NCri
 */
public class MindMapViewController extends ViewController implements ActionListener{
	
	private JFrame _mainFrame;
	
	public MindMapViewController(){
		_mainFrame = new MainView(this);
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
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("모든 액션은 Controller 가 처리한다.");
		System.out.println(e);
		
	}
}
