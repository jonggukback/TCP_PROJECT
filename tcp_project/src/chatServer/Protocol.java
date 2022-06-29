package chatServer;

public class Protocol {
	//프로토콜의 경우 어플에서 일괄적으로 적용하고 변경될 수 있도록 설계하는 것이 좋을 것이다.
	public static final int ADMISSION		= 100; // 입장
	
	public static final int ROOM_CREATE 	= 110;  // 1대1 신청
	public static final int ROOM_CREATE2 	= 111;  // 1대1 응답
	public static final int MESSAGE 		= 200; // 1대1 메시지
	
	public static final int GROUP_MESSAGE   = 201; // 단체 메시지
	public static final int NICKNAME_CHANGE  = 202; // 회원정보수정
	public static final int NOTICE		    = 203; // 공지사항
	public static final int ROOM_OUT 		= 500; // 단체 채팅방 아웃
	public static final int EXPULSION   	= 501; // 추방-벤
	
	
	public static final int LOGIN   	= 150;
	public static final int IDCHECK   	= 151;
	public static final int SIGNUP   	= 152;
	
	public static final int IMAGE = 204;
	
}
