package chatClient.Controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import chatClient.VO.MemberVO;
import chatClient.VO.MsgVO;
import chatServer.Protocol;

public class Controller {

	LoginDao logindao = new LoginDao();
	Socket socket = null;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;

	private static Controller instance;

	private Controller() {
	}

	public static Controller getInstance() {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}

	public MemberVO action(MemberVO pmVO) {
		int command = pmVO.getCommand();
		String result = null;

		switch (command) {
			case Protocol.LOGIN: {
				result = logindao.login(pmVO);
				pmVO.setMem_name(result);
			}
				break;
			case Protocol.IDCHECK: { 
				result = logindao.idCheck(pmVO);
				pmVO.setResult(Integer.valueOf(result));
			}
				break;
			case Protocol.SIGNUP: { 
				result = logindao.signUp(pmVO);
				pmVO.setResult(Integer.valueOf(result));
			}
		}
		return pmVO;
	}

	public void init(String nickName) { 
		try {
			// 서버측의 ip주소 작성하기
			socket = new Socket("127.0.0.1", 3002);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			// initDisplay에서 닉네임이 결정된 후 init메소드가 호출되므로
			// 서버에게 내가 입장한 사실을 알린다.(말하기)
			MsgVO mvo = new MsgVO();
			mvo.setProtocol(Protocol.ADMISSION);
			mvo.setNickname(nickName);
			oos.writeObject(mvo);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public void send(MsgVO mVO) {
		int Command = mVO.getProtocol();
		String nickName = mVO.getNickname();
		String afterName = mVO.getAfter_nickname();

		try {
			switch (Command) {
			case Protocol.GROUP_MESSAGE: {
				oos.writeObject(mVO);
			}
				break;
			case Protocol.ROOM_OUT: {
				mVO.setMsg(nickName + " 님이 퇴장하셨습니다.");
				oos.writeObject(mVO);
			}
				break;
			case Protocol.NICKNAME_CHANGE: {
				mVO.setMsg(nickName + " 님의 대화명이 " + afterName + "으로 변경되었습니다.");
				oos.writeObject(mVO);
			}
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MsgVO receive() throws ClassNotFoundException, IOException {
		MsgVO rVO = null;
		rVO = (MsgVO) ois.readObject();
		return rVO;
	}
}
