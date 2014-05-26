/**
 * @FileName 	: ViewController.java
 * @Project 	: MindmapApplication
 * @Date 		: 2014. 5. 12.
 * @Author 		: NCri
 */
package controller;


/**
 * @Class : ViewController
 * @Date : 2014. 5. 12.
 * @Author : NCri
 */
abstract public class ViewController {
	public static final int MIND_MAP = 0;
	
	public static ViewController getInstance(int type){
		ViewController instance = null;
		if(type == MIND_MAP){
			instance = new MindMapViewController();
		}		
		return instance;		
	}
}
