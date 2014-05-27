/**
 * @FileName 	: Model.java
 * @Project 	: MindmapApplication
 * @Date 		: 2014. 5. 12.
 * @Author 		: NCri
 */
package model;

import java.util.ArrayList;
import java.util.Map;

/**
 * @Class		: Model
 * @Date 		: 2014. 5. 12.
 * @Author 		: NCri
 */

abstract public class Model{
	public static final int ATTRIBUTE = 0;
	public static final int MIND_MAP_NODE_TREE = 1;
	
//	public static Model getInstace(int type){
//		Model instance = null;
//		if(type == Model.ATTRIBUTE){
//			instance = new AttributeModel();
//		}else if(type == Model.MIND_MAP_NODE_TREE){
//			instance = new MapNodeTreeModel();
//		}		
//		return instance;
//	}
	
	abstract public Map getData();
	abstract public ArrayList<Model> getAllOfData();
	
}
