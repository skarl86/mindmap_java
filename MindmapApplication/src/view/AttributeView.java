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
 * �붽뎄�ы빆.
 * [�띿꽦]				[SWING 而댄룷�뚰듃]
 * �쒖옉 x, y 醫뚰몴 		JLabel, JTextField 
 * �덈퉬, �믪씠 			JLabel, JTextField 
 * (�몃뱶) �띿뒪��		JLabel, JTextField 
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
		GridLayout grid = new GridLayout(6, 2);
		//setLayout(new GridLayout(6, 2));
		grid.setVgap(70); 

		setLayout(null);
		JLabel label1 = new JLabel(FIELD_NAME_X);
		JTextField text1= new JTextField();
		JLabel label2 = new JLabel(FIELD_NAME_Y);
		JTextField text2= new JTextField();
		JLabel label3 = new JLabel(FIELD_NAME_WIDTH);
		JTextField text3= new JTextField();
		JLabel label4 = new JLabel(FIELD_NAME_HEIGHT);
		JTextField text4= new JTextField();
		JLabel label5 = new JLabel(FIELD_NAME_TEXT);
		JTextField text5= new JTextField();
		
		label1.setBounds(35,20,50,20);
		text1.setBounds(70,20,150,20);
		label2.setBounds(35,50,50,20);
		text2.setBounds(70,50,150,20);
		label3.setBounds(20,80,50,20);
		text3.setBounds(70,80,150,20);
		label4.setBounds(20,110,50,20);
		text4.setBounds(70,110,150,20);
		label5.setBounds(25,140,50,20);
		text5.setBounds(70,140,150,20);
		
		add(label1);
		add(text1);
		add(label2);
		add(text2);
		add(label3);
		add(text3);
		add(label4);
		add(text4);
		add(label5);
		add(text5);
		
		JButton changeBtn = new JButton(BUTTON_NAME_CHANGE);
		changeBtn.addActionListener((ActionListener)listner);
		changeBtn.setBounds(80,170, 80, 40);
		add(changeBtn);		
	}

}
