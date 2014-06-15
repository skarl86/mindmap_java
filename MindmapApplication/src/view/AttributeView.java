/**
 * @FileName 	: AttributView.java
 * @Project 	: MindmapApplication
 * @Date 		: 2014. 5. 12.
 * @Author 		: NCri
 */
package view;

import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.util.EventListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.MapNodeModel;

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
	
	private JTextField xTextField;
	private JTextField yTextField;
	private JTextField widthTextField;
	private JTextField heightTextField;
	private JTextField inputTextField;
	
	
	public AttributeView(EventListener listner) {
		// TODO Auto-generated constructor stub

		// Sample TEST Code
		GridLayout grid = new GridLayout(6, 2);
		//setLayout(new GridLayout(6, 2));
		grid.setVgap(70); 

		setLayout(null);
		JLabel label1 = new JLabel(FIELD_NAME_X);
		xTextField = new JTextField();
		JLabel label2 = new JLabel(FIELD_NAME_Y);
		yTextField = new JTextField();
		JLabel label3 = new JLabel(FIELD_NAME_WIDTH);
		widthTextField = new JTextField();
		JLabel label4 = new JLabel(FIELD_NAME_HEIGHT);
		heightTextField = new JTextField();
		JLabel label5 = new JLabel(FIELD_NAME_TEXT);
		inputTextField = new JTextField();
		
		label1.setBounds(35,20,50,20);
		xTextField.setBounds(70,20,150,20);
		label2.setBounds(35,50,50,20);
		yTextField.setBounds(70,50,150,20);
		label3.setBounds(20,80,50,20);
		widthTextField.setBounds(70,80,150,20);
		label4.setBounds(20,110,50,20);
		heightTextField.setBounds(70,110,150,20);
		label5.setBounds(25,140,50,20);
		inputTextField.setBounds(70,140,150,20);
		
		add(label1);
		add(xTextField);
		add(label2);
		add(yTextField);
		add(label3);
		add(widthTextField);
		add(label4);
		add(heightTextField);
		add(label5);
		add(inputTextField);
		
		JButton changeBtn = new JButton(BUTTON_NAME_CHANGE);
		changeBtn.addActionListener((ActionListener)listner);
		changeBtn.setBounds(80,170, 80, 40);
		add(changeBtn);		
	}
	
	public void changeStatus(MapNodeModel node){
		if(node == null){
			xTextField.setText("");
			yTextField.setText("");
			widthTextField.setText("");
			heightTextField.setText("");
			inputTextField.setText("");
			return;
		}
		xTextField.setText(String.valueOf(node.getX()));
		yTextField.setText(String.valueOf(node.getY()));
		widthTextField.setText(String.valueOf(node.getWidth()));
		heightTextField.setText(String.valueOf(node.getHeight()));
		inputTextField.setText(node.getText());
	}
	
	public MapNodeModel getStatus(){
		Rectangle rect = new Rectangle();
		rect.x = Integer.valueOf(xTextField.getText());
		rect.y = Integer.valueOf(yTextField.getText());
		rect.width = Integer.valueOf(widthTextField.getText());
		rect.height = Integer.valueOf(heightTextField.getText());
		MapNodeModel newNode = new MapNodeModel(rect, inputTextField.getText());
		return newNode;
	}

}
