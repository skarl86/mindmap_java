/**
 * @FileName 	: AttributView.java
 * @Project 	: MindmapApplication
 * @Date 		: 2014. 5. 12.
 * @Author 		: NCri
 */
package view;

import java.awt.Color;
import java.awt.GridLayout;

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
	public AttributeView(Color color) {
		super(color);
		// TODO Auto-generated constructor stub

		// Sample TEST Code
		setLayout(new GridLayout(4, 2));
		add(new JLabel("이름"));
		add(new JTextField());
		add(new JLabel("나이"));
		add(new JTextField());
		
	}

}
