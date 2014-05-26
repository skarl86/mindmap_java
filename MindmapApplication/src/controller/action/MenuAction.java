/**
 * @FileName 	: MenuAction.java
 * @Project 	: MindmapApplication
 * @Date 		: 2014. 5. 26.
 * @Author 		: NCri
 */
package controller.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MenuBar;

/**
 * @Class : MenuAction
 * @Date : 2014. 5. 26.
 * @Author : NCri
 */
public class MenuAction implements ActionListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent component) {
		// TODO Auto-generated method stub
		System.out.println(component.getSource());
		if(component.getActionCommand() == MenuBar.OPEN){
			
		}else if(component.getActionCommand() == MenuBar.SAVE){
			
		}else if(component.getActionCommand() == MenuBar.SAVE_AS){
			
		}else if(component.getActionCommand() == MenuBar.CLOSE){
			
		}
	}

	// MindMap Menu Bar Action
	public void createNewMindMap() {

	}

	public void openMindMap() {

	}

	public void saveAsMindMap() {

	}

	public void saveMindMap() {

	}

	public void closeMindMap() {

	}

}
