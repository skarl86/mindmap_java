/**
 * @FileName 	: NodeView.java
 * @Project 	: MindmapApplication
 * @Date 		: 2014. 5. 26.
 * @Author 		: NCri
 */
package view;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * @Class		: NodeView
 * @Date 		: 2014. 5. 26.
 * @Author 		: NCri
 */
public class MapNode extends JLabel{
	public MapNode(Point point){
		super();
		setText("Node");
		setVerticalAlignment(SwingConstants.CENTER);
		setHorizontalAlignment(SwingConstants.CENTER);
		setOpaque(true);
		setBackground(Color.YELLOW);
		System.out.println(point);
		setBounds(new Rectangle(point.x, point.y, 60, 30));
		setVisible(true);
	}
}
