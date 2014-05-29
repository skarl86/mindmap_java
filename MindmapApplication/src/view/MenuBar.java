/**
 * @FileName 	: MindMapMenuBar.java
 * @Project 	: MindmapApplication
 * @Date 		: 2014. 5. 13.
 * @Author 		: NCri
 */
package view;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * @Class		: MindMapMenuBar
 * @Date 		: 2014. 5. 13.
 * @Author 		: NCri
 */
/**
 * �붽뎄 �ы빆.
 * [�대깽�� 					[�≪뀡] 
 * �덈줈 留뚮뱾湲�				1. �덈줈��留덉씤�쒕㏊ �몄쭛���쒖옉�⑸땲�� 2. �대� �몄쭛 �뱀� 遺덈윭���댁슜���덈떎硫� �꾨줈
 *															洹몃옩��泥섏쓬 �쒖옉 �덉쓣 �뚯쓽 �곹깭濡��섎룎由쎈땲
 *															��
 * �닿린 						�뚯씪 濡쒕뱶 
 * ��옣 						�뚯씪 ��옣
 * �ㅻⅨ �대쫫�쇰줈 ��옣 			�덈줈���뚯씪 �앹꽦 
 * �リ린 						�꾨줈洹몃옩 醫낅즺
 */

/**
 * �붽뎄 �ы빆.
 * �뚯씪 ��옣��1. 媛앹껜 ��옣 諛⑹떇, 2. �щ윭遺꾩씠 怨좎븞���뚯씪 �ㅽ럺���섍굅�섏뿬 諛붿씠�덈━ �뱀� 臾몄옄 諛� * ���ㅽ듃由쇱쓣 �ъ슜�섏뿬 ��옣 �섎뒗 諛⑹떇, 3. XML ��엯�쇰줈 ��옣�섎뒗 諛⑹떇����媛�� 以��섎굹瑜��� * �앺빀�덈떎. 
 * 諛곗젏��3 > 2 > 1 �쒖쑝濡��믪� �먯닔瑜�諛쏄쾶 ��寃껋엯�덈떎.
 */
public class MenuBar extends JMenuBar{
	private static int _FILE = 0;
	
	public static String OPEN = "열기";
	public static String SAVE = "저장";
	public static String SAVE_AS = "다른이름으로 저장";
	public static String CLOSE = "닫기";
	
	
	public static JMenuBar getInstance(ActionListener listner){
		JMenuBar menuBar = new MenuBar();
		JMenu fileMenu = new JMenu("파일");
		menuBar.add(fileMenu);
		
		ArrayList<JMenuItem> items = new ArrayList<JMenuItem>();
		items.add(new JMenuItem(OPEN));
		items.add(new JMenuItem(SAVE));
		items.add(new JMenuItem(SAVE_AS));
		items.add(new JMenuItem(CLOSE));

		Map<Integer, ArrayList<JMenuItem>> kindOfMenu = new HashMap<Integer, ArrayList<JMenuItem>>();
		kindOfMenu.put(_FILE, items);
		
		for (JMenuItem item : kindOfMenu.get(_FILE)){
			fileMenu.add(item);
			item.addActionListener(listner);
		}
		
		return menuBar;		
	}

}
