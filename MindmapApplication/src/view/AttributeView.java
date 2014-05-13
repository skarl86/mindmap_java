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
public class AttributeView extends View {
	/**
	 * @param color
	 */
	public AttributeView(Color color) {
		super(color);
		// TODO Auto-generated constructor stub
		setLayout(new GridLayout(4, 2));
		add(new JLabel("이름"));
		add(new JTextField());
		add(new JLabel("나이"));
		add(new JTextField());
		
	}

}
