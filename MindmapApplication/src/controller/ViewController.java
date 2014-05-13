/**
 * @FileName 	: ViewController.java
 * @Project 	: MindmapApplication
 * @Date 		: 2014. 5. 12.
 * @Author 		: NCri
 */
package controller;

import javax.swing.JFrame;

import view.MainView;
import view.MindMapMenuBar;
/**
 * @Class		: ViewController
 * @Date 		: 2014. 5. 12.
 * @Author 		: NCri
 */
public class ViewController {
	private JFrame _mainFrame;
	
	public ViewController(){
		_mainFrame = new MainView();
	}
	public static ViewController init(){
		ViewController vc = new ViewController();
		return vc;		
	}
	public void onMenuBar(){
		_mainFrame.setJMenuBar(MindMapMenuBar.initWithMenus(null));
		_mainFrame.setVisible(true);
	}
	
	
	public static void main(String[] args){
		ViewController vc = ViewController.init();
		vc.onMenuBar();
		
	}
}
