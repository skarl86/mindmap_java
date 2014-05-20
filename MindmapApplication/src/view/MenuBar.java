/**
 * @FileName 	: MindMapMenuBar.java
 * @Project 	: MindmapApplication
 * @Date 		: 2014. 5. 13.
 * @Author 		: NCri
 */
package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * @Class		: MindMapMenuBar
 * @Date 		: 2014. 5. 13.
 * @Author 		: NCri
 */
/**
 * 요구 사항.
 * [이벤트] 					[액션] 
 * 새로 만들기 				1. 새로운 마인드맵 편집을 시작합니다. 2. 이미 편집 혹은 불러낸 내용이 있다면, 프로
 *															그램이 처음 시작 했을 때의 상태로 되돌립니
 *															다.
 * 열기 						파일 로드 
 * 저장 						파일 저장
 * 다른 이름으로 저장 			새로운 파일 생성 
 * 닫기 						프로그램 종료
 */

/**
 * 요구 사항.
 * 파일 저장은 1. 객체 저장 방식, 2. 여러분이 고안한 파일 스펙에 의거하여 바이너리 혹은 문자 방
 * 식 스트림을 사용하여 저장 하는 방식, 3. XML 타입으로 저장하는 방식의 세 가지 중 하나를 선
 * 택합니다. 
 * 배점은 3 > 2 > 1 순으로 높은 점수를 받게 될 것입니다.
 */
public class MenuBar extends JMenuBar{
	
	public static JMenuBar init(){
		return MenuBar.initWithMenus(null);
	}
	public static JMenuBar initWithMenus(String[] menus){
		JMenuBar menuBar = new MenuBar();
		JMenu fileMenu = new JMenu("파일");
		menuBar.add(fileMenu);
		
		fileMenu.add(new JMenuItem("New"));
		return menuBar;		
	}

}
