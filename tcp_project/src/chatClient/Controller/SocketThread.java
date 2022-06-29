package chatClient.Controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import chatClient.VO.MsgVO;
import chatClient.View.ChatView;
import chatClient.View.ChatView2;
import chatServer.Protocol;

public class SocketThread extends Thread {
	ChatView2 chatview2 = null;
	Controller controller = Controller.getInstance();
	Vector<String> userList = new Vector<>();
	public SocketThread(ChatView2 chatview2) {
		this.chatview2 = chatview2;
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
						System.out.println("socket Thread nickname : "+mVO.getNickname());
						chatview2.append(mVO.getNickname() + "님이 입장하였습니다.");
						chatview2.addRow(mVO.getNickname());
					}
						break;
	
					case Protocol.MESSAGE: {
	
					}
						break;
						
					case Protocol.IMAGE:{
						String fileName = mVO.getFileName();
						chatview2.sendimg(fileName);
					}
						break;
						
					case Protocol.GROUP_MESSAGE: {
						chatview2.append("[" + mVO.getNickname() + "]" + mVO.getMsg());
					}
						break;
	
					case Protocol.NICKNAME_CHANGE: {
						chatview2.reFresh(mVO.getNickname(), mVO.getAfter_nickname());
						chatview2.append(mVO.getMsg());
					}
						break;
	
					case Protocol.NOTICE: {
						chatview2.append("[" + mVO.getNickname() + "]" + mVO.getMsg());
						chatview2.successMsg("[" + mVO.getNickname() + "]" + mVO.getMsg());
					}
						break;
	
					case Protocol.ROOM_OUT: {
						chatview2.append(mVO.getMsg());
						chatview2.deleteRow(mVO.getNickname());
	
					}
						break;

					case Protocol.EXPULSION: {
						if (chatview2.getNickName().equals(mVO.getNickname())) { // 같은 닉네임이면 종료
							chatview2.successMsg("운영자에 의해 강퇴 당하셨습니다");
							chatview2.dispose();
						} else { // 다른 닉네임이면 강퇴 당한 아이디 채팅창에 그리고 대화목록에서 삭제
							chatview2.append(mVO.getNickname() + "님이 운영자에 의해 강퇴당하셨습니다.");
							chatview2.deleteRow(mVO.getNickname());
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
