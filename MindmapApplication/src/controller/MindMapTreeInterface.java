/**
 * @FileName 	: MindMapTreeInterface.java
 * @Project 	: MindmapApplication
 * @Date 		: 2014. 6. 18.
 * @Author 		: NCri
 */
package controller;

import java.util.ArrayList;

/**
 * @Class		: MindMapTreeInterface
 * @Date 		: 2014. 6. 18.
 * @Author 		: NCri
 */
public interface MindMapTreeInterface<T> {
	public ArrayList<T> getChilds();
	public T addChild(T child);
	public void removeChild(T child);
	public T getParentNode();
	public void setParentNode(T parent);
}
