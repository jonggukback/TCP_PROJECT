package chatClient.Controller;

import chatClient.VO.MsgVO;
import chatClient.View.ChatView;
import chatServer.Protocol;

public class SocketThread extends Thread {
	ChatView chatview = null;
	Controller controller = Controller.getInstance();

	public SocketThread(ChatView chatview) {
		this.chatview = chatview;
	}

	public void run() {
		boolean isStop = false;
		MsgVO mVO = null;
		while (!isStop) {
			try {
				mVO = controller.receive();
				System.out.println("프로토콜 : "+mVO.getProtocol());
				System.out.println("닉네임 : "+mVO.getNickname());
				System.out.println("바뀐 닉네임 : "+mVO.getAfter_nickname());
				System.out.println("메시지 내용 : "+mVO.getMsg());
				switch (mVO.getProtocol()) {
					case Protocol.ADMISSION: {// 100#apple
						chatview.append(mVO.getNickname() + "님이 입장하였습니다.");
						chatview.addRow(mVO.getNickname());
					}
						break;
	
					case Protocol.MESSAGE: {
	
					}
						break;
	
					case Protocol.GROUP_MESSAGE: {
						chatview.append("[" + mVO.getNickname() + "]" + mVO.getMsg());
					}
						break;
	
					case Protocol.NICKNAME_CHANGE: {
						chatview.reFresh(mVO.getNickname(), mVO.getAfter_nickname());
						chatview.append(mVO.getMsg());
					}
						break;
	
					case Protocol.NOTICE: {
						chatview.append("[" + mVO.getNickname() + "]" + mVO.getMsg());
						chatview.successMsg("[" + mVO.getNickname() + "]" + mVO.getMsg());
					}
						break;
	
					case Protocol.ROOM_OUT: {
						chatview.append(mVO.getMsg());
						chatview.deleteRow(mVO.getNickname());
	
					}
						break;

					case Protocol.EXPULSION: {
						if (chatview.getNickName().equals(mVO.getNickname())) { // 같은 닉네임이면 종료
							chatview.successMsg("운영자에 의해 강퇴 당하셨습니다");
							chatview.dispose();
						} else { // 다른 닉네임이면 강퇴 당한 아이디 채팅창에 그리고 대화목록에서 삭제
							chatview.append(mVO.getNickname() + "님이 운영자에 의해 강퇴당하셨습니다.");
							chatview.deleteRow(mVO.getNickname());
						}
					}
						break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
