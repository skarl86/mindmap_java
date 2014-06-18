/**
 * @FileName 	: MindMapViewController.java
 * @Project 	: MindmapApplication
 * @Date 		: 2014. 5. 26.
 * @Author 		: NCri
 */
package controller;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import model.MapNodeModel;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import view.AttributeView;
import view.MainView;
import view.MapNode;
import view.MenuBar;
import view.MindMapView;
import view.PopUpMenu;
import view.View;

/**
 * @Class : MindMapViewController
 * @Date : 2014. 5. 26.
 * @Author : NCri
 */
public class MindMapViewController extends ViewController implements
		ActionListener, MouseListener, MouseMotionListener, PopupMenuListener,
		KeyListener{

	private final String _applcationTitle = "Mind Map V1.0";
	static enum LinkPoint {
		LEFT, RIGHT, TOP, BOTTOM
	}

	private MainView _mainFrame;

	private Point _selectedNodePoint;
	private Point _pressedPoint;
	// 팝메뉴를 실행했을 때 위치값.
	private Point _menuPoint;

	// 마인드 맵의 Root 노드들을 가지고 있는 리스트.
	private ArrayList<MapNode> _rootList = new ArrayList<MapNode>();

	// MapNode View 와 Model 객체를 맵핑 시켜줄 테이블.
	private Map<MapNode, MapNodeModel> _nodeMappingTable = new HashMap<MapNode, MapNodeModel>();

	// 현재 선택 된 MapNode의 레퍼런스 변수.
	private MapNode _selectedMapNode;
	
	private File _savedFile;
	private boolean _isSaved;
	public MindMapViewController() {
		_mainFrame = new MainView(this);

		for (Component comp : _mainFrame.getComponents())
			System.out.println(comp);
	}

	public static void main(String[] args) {
		ViewController vc = ViewController.getInstance(MIND_MAP);
	}

	public void recursiveNode(MapNodeModel root, int depth, Document doc,
			Element rootElement) {
		// node 엘리먼트
		Element node = doc.createElement("node");
		rootElement.appendChild(node);

		// 속성값 정의
		Attr attr = doc.createAttribute("id");
		attr.setValue(String.valueOf(root.getID()));
		node.setAttributeNode(attr);

		// 속성값을 정의하는 더 쉬운 방법
		// staff.setAttribute("id", "1");

		// parent ID 엘리먼트
		Element parentID = doc.createElement("parentID");
		if (root.getParentNode() != null)
			parentID.appendChild(doc.createTextNode(String.valueOf(root
					.getParentNode().getID())));
		else
			parentID.appendChild(doc.createTextNode("-1"));
		node.appendChild(parentID);

		// tree depth 엘리먼트
		Element depthElement = doc.createElement("depth");
		depthElement.appendChild(doc.createTextNode(String.valueOf(depth)));
		node.appendChild(depthElement);

		// x 엘리먼트
		Element x = doc.createElement("x");
		x.appendChild(doc.createTextNode(String.valueOf(root.getX())));
		node.appendChild(x);

		// y 엘리멘트
		Element y = doc.createElement("y");
		y.appendChild(doc.createTextNode(String.valueOf(root.getY())));
		node.appendChild(y);

		// width 엘리멘트
		Element width = doc.createElement("width");
		width.appendChild(doc.createTextNode(String.valueOf(root.getWidth())));
		node.appendChild(width);

		// height 엘리멘트
		Element height = doc.createElement("height");
		height.appendChild(doc.createTextNode(String.valueOf(root.getHeight())));
		node.appendChild(height);

		// text 엘리멘트
		Element text = doc.createElement("text");
		text.appendChild(doc.createTextNode(root.getText()));
		node.appendChild(text);

		for (MapNodeModel child : root.getChilds())
			recursiveNode(child, depth + 1, doc, rootElement);
	}

	public void recursive(MapNodeModel root, int depth, Document doc,
			Element rootElement) {
		Element node = doc.createElement("node");
		rootElement.appendChild(node);

		// 속성값 정의
		Attr attr = doc.createAttribute("id");
		attr.setValue(String.valueOf(root.getID()));
		node.setAttributeNode(attr);

		// 속성값을 정의하는 더 쉬운 방법
		// staff.setAttribute("id", "1");

		// parent ID 엘리먼트
		attr = doc.createAttribute("parentID");
		if (root.getParentNode() == null)
			attr.setValue("0");
		else
			attr.setValue(String.valueOf(root.getParentNode().getID()));
		node.setAttributeNode(attr);
		// tree depth 엘리먼트
		attr = doc.createAttribute("depth");
		attr.setValue(String.valueOf(depth));
		node.setAttributeNode(attr);

		// x 엘리먼트
		attr = doc.createAttribute("x");
		attr.setValue(String.valueOf(root.getX()));
		node.setAttributeNode(attr);
		// y 엘리멘트
		attr = doc.createAttribute("y");
		attr.setValue(String.valueOf(root.getY()));
		node.setAttributeNode(attr);
		// width 엘리멘트
		attr = doc.createAttribute("width");
		attr.setValue(String.valueOf(root.getWidth()));
		node.setAttributeNode(attr);
		// height 엘리멘트
		attr = doc.createAttribute("height");
		attr.setValue(String.valueOf(root.getHeight()));
		node.setAttributeNode(attr);
		// text 엘리멘트
		attr = doc.createAttribute("text");
		attr.setValue(root.getText());
		node.setAttributeNode(attr);
		for (MapNodeModel child : root.getChilds())
			recursive(child, depth + 1, doc, rootElement);

	}

	public void saveToXML(File file) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// 루트 엘리먼트
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("nodes");
			doc.appendChild(rootElement);

			for (MapNode root : _rootList) {
				MapNodeModel rootNodeModel = _nodeMappingTable.get(root);
				// recursiveNode(rootNodeModel, 0, doc, rootElement);
				recursive(rootNodeModel, 0, doc, rootElement);
			}

			// XML 파일로 쓰기
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();

			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			StreamResult result;
			result = new StreamResult(new FileOutputStream(file));

			// 파일로 쓰지 않고 콘솔에 찍어보고 싶을 경우 다음을 사용 (디버깅용)
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			_isSaved = false;
			System.out.println("File saved!");
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void openToFile(File file) {
		/****************** XML 파일 파싱 START ******************/
		DocumentBuilderFactory docBuildFact = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuild;
		Document doc;
		try {
			docBuild = docBuildFact.newDocumentBuilder();
			doc = docBuild.parse(file);
			doc.getDocumentElement().normalize();

			// FILE_LIST엘리먼트 리스트
			NodeList fileList = doc.getElementsByTagName("nodes");
			for (int i = 0; i < fileList.getLength(); i++) {
				Node fileListNode = (Node) fileList.item(i);
				if (fileListNode.getNodeType() == Node.ELEMENT_NODE) {
					// FILE_LIST엘리먼트
					Element fileListElmnt = (Element) fileListNode;
					// FILE 태그
					NodeList fileNameList = fileListElmnt
							.getElementsByTagName("node");

					// Tree Model을 생성한다.
					MapNodeModel rootNodeModel = makeMapNodeModelTree(fileNameList);
					MapNode rootNode = makeMapNodeTree(null, rootNodeModel);
					ArrayList<MapNodeModel> modelList = makeListFromTree(null,
							rootNodeModel);
					ArrayList<MapNode> nodeList = makeListFromTree(null,
							rootNode);

					// MapNode View와 MapNodeModel를 맵핑 시켜준다.
					for (MapNodeModel model : modelList) {
						for (MapNode node : nodeList) {
							if (model.getID() == node.getID())
								_nodeMappingTable.put(node, model);
						}
					}

					drawMapNode(rootNode);
				}
			}
			/****************** XML 파일 파싱 END ******************/
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList makeListFromTree(ArrayList list, MindMapTreeInterface root) {
		if (list == null)
			list = new ArrayList<MapNodeModel>();
		list.add(root);

		for (Object child : root.getChilds()) {
			makeListFromTree(list, (MindMapTreeInterface) child);
		}

		return list;
	}

	public MapNodeModel makeMapNodeModelTree(NodeList nodeList) {
		Map<String, MapNodeModel> mappingTable = new HashMap<String, MapNodeModel>();
		for (int j = 0; j < nodeList.getLength(); j++) {
			Element n = (Element) nodeList.item(j);

			int id = Integer.valueOf(n.getAttribute("id"));
			int parntID = Integer.valueOf(n.getAttribute("parentID"));
			int x = Integer.valueOf(n.getAttribute("x"));
			int y = Integer.valueOf(n.getAttribute("y"));
			int width = Integer.valueOf(n.getAttribute("width"));
			int height = Integer.valueOf(n.getAttribute("height"));
			String text = n.getAttribute("text");

			MapNodeModel newModel = new MapNodeModel();
			newModel.setID(id);
			newModel.setX(x);
			newModel.setY(y);
			newModel.setWidth(width);
			newModel.setHeight(height);
			newModel.setText(text);

			if (mappingTable.get(String.valueOf(parntID)) == null)
				mappingTable.put(String.valueOf(id), newModel);
			else {
				MapNodeModel parent = mappingTable.get(String.valueOf(parntID));
				parent.addChild(newModel);
				newModel.setParentNode(parent);
				mappingTable.put(String.valueOf(id), newModel);
			}
		}

		// Root Node의 parent ID값은 0.
		for (Entry<String, MapNodeModel> entry : mappingTable.entrySet()) {
			MapNodeModel node = entry.getValue();
			if (node.getParentNode() == null)
				return node;
		}
		return null;
	}

	public MapNode makeMapNodeTree(Map<MapNodeModel, MapNode> mappingTable,
			MapNodeModel root) {
		MapNode mainRoot = null;

		MapNode newNode = new MapNode(root.getBounds().getLocation());
		newNode.setID(root.getID());
		newNode.setBounds(root.getBounds());
		newNode.setText(root.getText());

		if (root.getParentNode() == null)
			mainRoot = newNode;

		if (mappingTable == null)
			mappingTable = new HashMap<MapNodeModel, MapNode>();

		mappingTable.put(root, newNode);

		MapNode parent = mappingTable.get(root.getParentNode());

		if (parent != null) {
			newNode.setParentNode(parent);
			parent.addChild(newNode);
		}

		for (MapNodeModel node : root.getChilds())
			makeMapNodeTree(mappingTable, node);

		return mainRoot;
	}

	public void drawMapNode(MapNode root) {
		MindMapView mapView = (MindMapView) _mainFrame.getView(View.MIND_MAP);
		MapNode newMapNode = mapView.addMapNode(root);
		mapView.repaint();
		for (MapNode child : root.getChilds())
			drawMapNode(child);
	}

	public LinkPoint getPointOnLinkNode(Dimension size, Point point) {
		int cx = size.width / 2;
		int cy = size.height / 2;

		int diffX = cx - point.x;
		int diffY = cy - point.y;

		LinkPoint answer = LinkPoint.TOP;

		if (diffX < 0 && Math.abs(diffX) > Math.abs(diffY)) {
			answer = LinkPoint.RIGHT;
		} else if (diffX > 0 && Math.abs(diffX) > Math.abs(diffY)) {
			answer = LinkPoint.LEFT;
		} else if (diffY < 0 && Math.abs(diffX) < Math.abs(diffY)) {
			answer = LinkPoint.BOTTOM;
		} else if (diffY > 0 && Math.abs(diffX) < Math.abs(diffY)) {
			answer = LinkPoint.TOP;
		}

		return answer;

	}

	public MapNode createNewNode(Point newNodePoint) {
		System.out.println("새로운 NODE를 생성.");
		_isSaved = true;
		MindMapView mapView = (MindMapView) _mainFrame.getView(View.MIND_MAP);

		// Add MapNode Object in MindMapView.
		MapNode newMapNode = mapView.addMapNode(newNodePoint);
		MapNodeModel newNode = new MapNodeModel(newMapNode.getBounds(),
				newMapNode.getText());

		// MapNode View에 해당하는 Node Model 객체를 맵핑 시켜준다.
		_nodeMappingTable.put(newMapNode, newNode);
		return newMapNode;
	}

	public MapNode createLinkNode(MapNode parent, Point mousePoint) {
		System.out.println("링크 NODE 생성.");

		LinkPoint where = getPointOnLinkNode(parent.getSize(), mousePoint);

		Point linkNodePoint = parent.getLocation();
		switch (where) {
		case LEFT:
			linkNodePoint.x -= parent.getBounds().width * 1.5;
			break;
		case RIGHT:
			linkNodePoint.x += parent.getBounds().width * 1.5;
			break;
		case TOP:
			linkNodePoint.y -= parent.getBounds().height * 2;
			break;
		case BOTTOM:
			linkNodePoint.y += parent.getBounds().height * 2;
			break;
		default:
			break;
		}
		if (isOutOfMindMapViewBounds(linkNodePoint))
			linkNodePoint = new Point(0, 0);

		// Create new MapNode Object that will link to parent MapNode Object.
		MapNode willLinkNode = createNewNode(linkNodePoint);

		// Do Link new MapNode Object to selected MapNode Object.
		parent.addChild(willLinkNode);

		// MapNode View에 해당하는 Model 객체도 동기화 시켜준다.
		MapNodeModel newNode = _nodeMappingTable.get(willLinkNode);
		MapNodeModel selectedMapNodeModel = _nodeMappingTable.get(parent);
		selectedMapNodeModel.addChild(newNode);

		// TEST Code.
		// willLinkNode.setBackground(Color.BLUE);
		return willLinkNode;
	}

	public void removeMapNodeAll() {
		System.out.println("모든 노드 삭제.");
		MindMapView mapView = (MindMapView) _mainFrame.getView(View.MIND_MAP);

		mapView.removeMapNodeAll();

		_rootList.clear();
	}

	public void removeMapNode(MapNode mapNode) {
		// 재귀 함수.

		System.out.println("노드 삭제.");

		MindMapView mapView = (MindMapView) _mainFrame.getView(View.MIND_MAP);

		// If it haven't childs, remove itself.
		if (mapNode.getChilds().size() == 0) {
			// 부모 노드가 없다면 그건 루트다.
			if (mapNode.getParentNode() != null)
				mapNode.getParentNode().removeChild(mapNode);
			mapView.removeMapNode(mapNode);
			return;
		}

		ArrayList<MapNode> copyList = (ArrayList<MapNode>) mapNode.getChilds()
				.clone();

		// To remove child MapNode Object.
		for (MapNode childMapNode : copyList) {
			removeMapNode(childMapNode);
		}

		mapView.removeMapNode(mapNode);
		if (mapNode.getParentNode() != null)
			mapNode.getParentNode().removeChild(mapNode);
	}

	public void refreshStatus(MapNode selectedNode) {
		AttributeView attrView = (AttributeView) _mainFrame
				.getView(View.ATTRIBUTE);

		if (selectedNode == null) {
			attrView.changeStatus(null);
			return;
		}

		MapNodeModel changedNode = _nodeMappingTable.get(selectedNode);
		changedNode.setBounds(selectedNode.getBounds());
		changedNode.setText(selectedNode.getText());
		attrView.changeStatus(changedNode);
		_isSaved = false;
	}

	public boolean isOutOfMindMapViewBounds(Point point) {
		boolean isOut = false;
		isOut = (point.x < 0 || point.y < 0);
		return isOut;
	}

	public boolean isOutOfMainBounds(Point point) {
		boolean isOut = false;

		View attrView = _mainFrame.getView(View.ATTRIBUTE);
		View toolBar = _mainFrame.getView(View.TOOL_BAR);
		Dimension attrSize = attrView.getSize();
		Dimension toolSize = toolBar.getSize();

		isOut = (point.x > Toolkit.getDefaultToolkit().getScreenSize().width || point.y > Toolkit
				.getDefaultToolkit().getScreenSize().height);
		isOut = isOut || ((point.x >= 0 && point.x < attrSize.width));
		isOut = isOut || (point.y < toolSize.height + 100);
		return isOut;
	}
	
	public void save(){
		JFileChooser chooser = new JFileChooser("C:/");
		FileFilter filter = new FileNameExtensionFilter("XML files", "XML");
		chooser.addChoosableFileFilter(filter);

		int value = chooser.showSaveDialog(null);
		if (value == JFileChooser.APPROVE_OPTION) {
			System.out.println("방금 선택하신 파일 : "
					+ chooser.getSelectedFile().getName());
			File file = chooser.getSelectedFile();
			System.out.println("파일이 있는 디렉토리 : " + file);
			_savedFile = file;
			saveToXML(file);
			refreshTitle(file.getName());
		}
	}
	private void refreshTitle(String title){
		_mainFrame.setTitle(_applcationTitle + "_" + "\"" + title + "\"");
	}
	
	private void checkSave(){
		// 현재 생성된 노드가 없고, 저장하지 않았다면.
		if(_rootList.size() >= 1 && _isSaved){
			int result = JOptionPane.showConfirmDialog(_mainFrame.getView(MIND_MAP),"현재 내용을 저장하시겠습니까?","확인", JOptionPane.YES_NO_OPTION);
			System.out.println(result);
			// 예.
			if(result == 0){
				// 없다면.
				if(_savedFile == null){
					save();
				}else{
				// 저장을 진행 중인 파일이 있다면.
					saveToXML(_savedFile);
				}
			}else{
				//아니오 또는 닫기.
				//파일을 저장하지 않는다.
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getActionCommand());
		if (MenuBar.OPEN.equals(e.getActionCommand())) {
			System.out.println("열기.");
			
			checkSave();
			
			JFileChooser chooser = new JFileChooser("C:/");
			FileFilter filter = new FileNameExtensionFilter("XML files", "XML");
			chooser.addChoosableFileFilter(filter);

			int value = chooser.showOpenDialog(null);
			if (value == JFileChooser.APPROVE_OPTION) {
				System.out.println("방금 선택하신 파일 : "
						+ chooser.getSelectedFile().getName());
				File file = chooser.getSelectedFile();
				System.out.println("파일이 있는 디렉토리 : " + file);
				
				removeMapNodeAll();
				openToFile(file);
				_savedFile = file;
				refreshTitle(file.getName());
			}

		} else if (MenuBar.NEW.equals(e.getActionCommand())) {
			System.out.println("새로 만들기.");
			
			checkSave();
			
			// 세이브 파일 레퍼런스를 초기화.
			_savedFile = null;
			removeMapNodeAll();
			refreshStatus(null);
		} else if (MenuBar.SAVE.equals(e.getActionCommand())) {
			System.out.println("저장.");
			if(_savedFile == null)
				save();
			else{
				saveToXML(_savedFile);
				refreshTitle(_savedFile.getName());				
			}
				
		} else if (MenuBar.SAVE_AS.equals(e.getActionCommand())) {
			System.out.println("다른 이름으로 저장.");
			save();
		} else if (MenuBar.CLOSE.equals(e.getActionCommand())) {
			System.out.println("닫기.");
			System.exit(0);
		} else if (AttributeView.BUTTON_NAME_CHANGE
				.equals(e.getActionCommand())) {
			System.out.println("상태 변경.");
			if (_selectedMapNode != null) {
				AttributeView attrView = (AttributeView) _mainFrame
						.getView(View.ATTRIBUTE);

				// 현재 속성 뷰에 입력된 값을 가져온다.
				MapNodeModel currentStatus = attrView.getStatus();

				// 가져온 속성 값을 현재 선택 된 MapNode 에 해당하는
				// Node에 갱신 시켜준다.
				// 주의) 객체 자체를 대체 해주면 안된다.
				// 그렇게 되면 트리 구조 또한 망가지기 때문에 값만 바꿔서
				// 넘겨주는 형태가 안전함.
				MapNodeModel oldStatus = _nodeMappingTable
						.get(_selectedMapNode);
				oldStatus.setBounds(currentStatus.getBounds());
				oldStatus.setText(currentStatus.getText());
				_nodeMappingTable.put(_selectedMapNode, oldStatus);

				// 변경 된 모델 값을 뷰에 갱신 해준다.
				_selectedMapNode.setBounds(currentStatus.getBounds());
				_selectedMapNode.setText(currentStatus.getText());

				// 위치가 변경되면서 선의 위치도 변경되어야 한다.
				// 그래소 Mind Map View를 Repaint 해준다.
				MindMapView mapView = (MindMapView) _mainFrame
						.getView(MIND_MAP);
				mapView.repaint();
			}
		} else if (PopUpMenu.ACTION_NODE_CREATE.equals(e.getActionCommand())) {
			// 최상위 루트 노드만 추가.
			_rootList.add(createNewNode(_selectedNodePoint));

		} else if (PopUpMenu.ACTION_NODE_LINK_CREATE.equals(e
				.getActionCommand())) {
			System.out.println(e.getSource());
			createLinkNode(_selectedMapNode, _menuPoint);

		} else if (PopUpMenu.ACTION_NODE_DELETE.equals(e.getActionCommand())) {
			removeMapNode(_selectedMapNode);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

		_selectedNodePoint = e.getPoint();

		if (MouseEvent.BUTTON3 == e.getButton()) {
			System.out.println(e.getSource());
			int popMenuType = -1;
			if (e.getSource() instanceof MapNode) {
				_menuPoint = e.getPoint();
				if (_selectedMapNode != null)
					_selectedMapNode.setSelected(false);
				_selectedMapNode = (MapNode) e.getSource();
				_selectedMapNode.setSelected(true);
				popMenuType = PopUpMenu.TYPE_NODE_MENU2;
			} else if (e.getSource() instanceof MindMapView) {
				popMenuType = PopUpMenu.TYPE_NODE_MENU1;
			}

			PopUpMenu pop = new PopUpMenu(popMenuType, this);
			pop.show(e.getComponent(), e.getX(), e.getY());
		} else if (MouseEvent.BUTTON1 == e.getButton()) {
			if (e.getSource() instanceof MapNode) {
				// 기존의 선택 된 MapNode를 토글 해줘야 한다.
				if (_selectedMapNode != null)
					_selectedMapNode.setSelected(false);
				_selectedMapNode = (MapNode) e.getSource();
				_selectedMapNode.setSelected(true);
				refreshStatus(_selectedMapNode);
			} else {
				// MinpMap View 부분을 클릭했을 때 선택된 노드를 풀어줘야한다.
				if (_selectedMapNode != null) {
					_selectedMapNode.setSelected(false);
					_selectedMapNode = null;
					// refreshStatus 시 null을 넘기면 상태 뷰의 값이
					// 다 사라진다.
					refreshStatus(_selectedMapNode);
				}
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e);
		if (e.getSource() instanceof MapNode) {
			_pressedPoint = e.getPoint();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent
	 * )
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if (!isOutOfMainBounds(new Point(e.getXOnScreen(), e.getYOnScreen()))) {
			if (e.getSource() instanceof MapNode) {
				int diffX = _pressedPoint.x - e.getPoint().x;
				int diffY = _pressedPoint.y - e.getPoint().y;

				MapNode node = (MapNode) e.getSource();
				Rectangle curBounds = node.getBounds();
				node.setBounds(curBounds.x - diffX, curBounds.y - diffY,
						curBounds.width, curBounds.height);

				// 상태값 변경.
				refreshStatus(node);

				// 선 모양을 갱신하기 위해서.
				MindMapView mapView = (MindMapView) _mainFrame
						.getView(View.MIND_MAP);
				mapView.repaint();
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		// System.out.println(e);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.event.PopupMenuListener#popupMenuCanceled(javax.swing.event
	 * .PopupMenuEvent)
	 */
	@Override
	public void popupMenuCanceled(PopupMenuEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.event.PopupMenuListener#popupMenuWillBecomeInvisible(javax
	 * .swing.event.PopupMenuEvent)
	 */
	@Override
	public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.event.PopupMenuListener#popupMenuWillBecomeVisible(javax.
	 * swing.event.PopupMenuEvent)
	 */
	@Override
	public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		Point point = null;
		System.out.println(e);
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_DOWN:
			point = _selectedMapNode.getBounds().getLocation();
			point.y += 2;
			_selectedMapNode.setLocation(point);
			break;
		case KeyEvent.VK_UP:
			point = _selectedMapNode.getBounds().getLocation();
			point.y -= 2;
			_selectedMapNode.setLocation(point);
			break;
		case KeyEvent.VK_LEFT:
			point = _selectedMapNode.getBounds().getLocation();
			point.x -= 2;
			_selectedMapNode.setLocation(point);
			break;
		case KeyEvent.VK_RIGHT:
			point = _selectedMapNode.getBounds().getLocation();
			point.x += 2;
			_selectedMapNode.setLocation(point);
			break;
		}
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
