/**
 * @FileName 	: AttributView.java
 * @Project 	: MindmapApplication
 * @Date 		: 2014. 5. 12.
 * @Author 		: NCri
 */
package view;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.EventListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * @Class : AttributView
 * @Date : 2014. 5. 12.
 * @Author : NCri
 */

/**
 * 요구사항.
 * [속성]				[SWING 컴포넌트]
 * 시작 x, y 좌표 		JLabel, JTextField 
 * 너비, 높이 			JLabel, JTextField 
 * (노드) 텍스트 		JLabel, JTextField 
 */
public class AttributeView extends View {
	/**
	 * @param color
	 */
	public static final String FIELD_NAME_X = "X";
	public static final String FIELD_NAME_Y = "Y";
	public static final String FIELD_NAME_WIDTH = "WIDTH";
	public static final String FIELD_NAME_HEIGHT = "HEIGHT";
	public static final String FIELD_NAME_TEXT = "TEXT";
	
	public static final String BUTTON_NAME_CHANGE = "변경";
	
	public AttributeView(EventListener listner) {
		// TODO Auto-generated constructor stub

		// Sample TEST Code
		setLayout(new GridLayout(6, 2));
		add(new JLabel(FIELD_NAME_X));
		add(new JTextField());
		add(new JLabel(FIELD_NAME_Y));
		add(new JTextField());
		add(new JLabel(FIELD_NAME_WIDTH));
		add(new JTextField());
		add(new JLabel(FIELD_NAME_HEIGHT));
		add(new JTextField());
		add(new JLabel(FIELD_NAME_TEXT));
		add(new JTextField());
		JButton changeBtn = new JButton(BUTTON_NAME_CHANGE);
		changeBtn.addActionListener((ActionListener)listner);
		add(changeBtn);		
	}

}
