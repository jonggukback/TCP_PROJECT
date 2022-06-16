package mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UIController {
	private final String _DEL = "delete";
	private final String _INS = "insert";
	private final String _UPD = "update";
	private final String _SEL = "select";
	Login login= null; //로그인
	Sign sign = null; //회원가입
	TalkClient talkclient = null; //채팅방
	MemberDAO memberdao = null; //DAO
	MemberVO membervo = null; //vo
	String menu = null;
	String user_ID = null;
	String user_pw = null;
	UIController() {}
	
	
	public UIController(String menu, String user_ID, String user_pw) {
		System.out.println("UIController 생성자 호출 성공");
		this.menu = menu;// login
		this.user_ID = user_ID;
		this.user_pw = user_pw;
	}
	
	public String send () {
		System.out.println("send 호출 성공");
		String nickName = null;
		MemberDAO mdao = new MemberDAO();
		nickName = mdao.signIn(user_ID, user_pw);
		return nickName;
	
	}
	
	
	
	
	public List<Map<String,Object>> UIController(){
		System.out.println("UIController 호출 성공");			
		List<Map<String,Object>> deptList = new ArrayList<>();
		// insert here
		
		return deptList;
	}
	


}
