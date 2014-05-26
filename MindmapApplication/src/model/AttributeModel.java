/**
 * @FileName 	: AttributeModel.java
 * @Project 	: MindmapApplication
 * @Date 		: 2014. 5. 20.
 * @Author 		: NCri
 */
package model;

import java.awt.Dimension;
import java.awt.Point;

/**
 * 요구사항.
 * [속성]	
 * 시작 x, y 좌표
 * 너비, 높이 
 * (노드) 텍스트 
 */

/**
 * @Class		: AttributeModel
 * @Date 		: 2014. 5. 20.
 * @Author 		: NCri
 */
public class AttributeModel extends Model {
	
	private Point _position;
	private Dimension _dimension;	
	private String _text;
	
	// Implement Setter
	public void setPosition(int x, int y){
		this.setPosition((double)x, (double)y);
	}
	
	public void setPosition(double x, double y){
		_position.setLocation(x, y);
	}
	
	public void setDimension(int width, int height){
		this.setDimension((double)width, (double)height);
	}
	
	public void setDimension(double width, double height){
		_dimension.setSize(width, height);
	}
	
	public void setText(String text){
		_text = text;
	}
	
	// Implement Getter
	public double getPositionX(){
		return _position.x;
	}
	
	public double getPositionY(){
		return _position.y;
	}
	
	public Point getPosition(){
		return _position;
	}
	
	public double getDimensionWidth(){
		return _dimension.width;
	}
	
	public double getDimensionHeight(){
		return _dimension.height;
	}
	
	public Dimension getDimension(){
		return _dimension;
	}
	
	public String getText(){
		return _text;
	}

}
