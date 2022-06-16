package mvc;
// ctrl + shift + y : 소문자로 바꾸기
// Lombok - setter, getter 생략 - 자동
// setter - 쓰기, 저장
// getter - 읽기, 듣기
// 외부에서의 위변조를 막기 위해서 이다.
// getter, setter public함
// 오라클 컬럼명과 vo전변 이름은 통일
// 오라클 타입과 자바 타입은 맞춘다.
// 예외 발생 - 화면에서 입력 받는 값이 문자열 취급을 함
// <input type = "text">

public class MemberVO {
	private String id   = ""; //
	private String pw   = ""; //
	private String nickname = ""; //
	String ids[] = new String[3];
	String pws[] = new String[3];
	String nicknames[] = new String[3];
	
	private String command = "";
	
	private int result = 0;
	
	// 전역변수는 초기화를 생략할 수 있음.
	// 왜? 생성자가 대신 해주니까
	public MemberVO() {
	}
	public MemberVO(String id, String pw, String nickname) {
		this.id = id;
		this.pw = pw;
		this.nickname = nickname;
		
	}
	public String getid() {
		return id;
	}
	public void setid(String id) {
		this.id = id;
	}
	public String getpw() {
		return pw;
	}
	public void setMem_pw(String pw) {
		this.pw = pw;
	}
	public String getname() {
		return nickname;
	}
	public void setMem_name(String name) {
		this.nickname = nickname;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
}