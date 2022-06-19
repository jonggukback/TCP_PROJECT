package three_2;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class TalkClient extends JFrame implements ActionListener {
	/*****************************************
	 * 					선언부				 *
	 *****************************************/
	////////////////통신과 관련한 전역변수 추가 시작//////////////
	Socket 				socket 		= null;
	ObjectOutputStream 	oos 		= null;	//말 하고 싶을 때
	ObjectInputStream 	ois			= null;	//듣기 할 때
	String 				nickName	= null;	//닉네임 등록
	////////////////통신과 관련한 전역변수 추가  끝  //////////////
	/* 오른편 패널 구성 */
	JPanel 	jp_second	  	= new JPanel();						// JPanel 2
	JPanel 	jp_second_south = new JPanel();						// 아래로 붙을 JPanel 2  
	JButton jbtn_one	  	= new JButton("1:1");				// '1:1' 버튼
	JButton jbtn_change	  	= new JButton("대화명변경");			// '대화명변경' 버튼
	JButton jbtn_font	  	= new JButton("글자색");				// '글자색' 버튼
	JButton jbtn_exit	  	= new JButton("나가기");				// '나가기' 버튼
	
	/* 왼편 패널 구성 */
	String 	cols[] 		  	= {"대화명"};							//
	String	data[][] 	  	= new String[0][1];					//
	DefaultTableModel dtm 	= new DefaultTableModel(data,cols); // ????채팅과 관련된건가????
	JTable		jtb 			= new JTable(dtm);				//
	JScrollPane jsp 			= new JScrollPane(jtb);			// 아마 채팅길어지면 스크롤 내리는 바?
	JPanel 		jp_first 		= new JPanel();					// JPanel 1
	JPanel 		jp_first_south 	= new JPanel();					// 아래로 붙을 JPanel 1 
	JTextField 	jtf_msg 		= new JTextField(20);			// south속지 center
	JButton 	jbtn_send  		= new JButton("전송");			// south속지 east
	JTextArea 	jta_display 	= null;							// 채팅입력칸 - 값이 정해지면 안됑~
	JScrollPane jsp_display 	= null;							// 
	
	//배경 이미지에 사용될 객체 선언-JTextArea에 페인팅
	Image back = null;
	
	/*****************************************
	 * 					생성자				 *
	 *****************************************/
	public TalkClient() {
		initDisplay();
		jtf_msg.addActionListener(this);
	}
	public TalkClient(Login lg) {
		jbtn_change.addActionListener(this);
		jbtn_exit.addActionListener(this);
	}
	
	/*****************************************
	 * 			       화면그리기				 *
	 *****************************************/
	public void initDisplay() {
		//사용자의 닉네임 받기
		nickName = JOptionPane.showInputDialog("닉네임을 입력하세요.");			// 최초 닉네임입력칸
		this.setLayout(new GridLayout(1,10));								// 
		jp_second.setLayout(new BorderLayout());							// 
		jp_second.add("Center",jsp);										// 채팅창 관련인듯?
		jp_second_south.setLayout(new GridLayout(2,2));						// 아래 버튼 4개 x,y축 위치지정인듯?
		jp_second_south.add(jbtn_one);										// '1:1' 버튼
		jp_second_south.add(jbtn_change);									// '대화명변경' 버튼
		jp_second_south.add(jbtn_font);										// '글자색' 버튼
		jp_second_south.add(jbtn_exit);										// '나가기' 버튼
		jp_second.add("South",jp_second_south);								// jp_second_south에 담긴 JPanal을 방위 남쪽으로 붙임
		// chatview의 왼편
		jp_first.setLayout(new BorderLayout());								// 보더레이아웃으로 레이아웃 세팅이겠지.?
		jp_first_south.setLayout(new BorderLayout());						// 
		jp_first_south.add("Center",jtf_msg);								// 입력칸 중앙
		jp_first_south.add("East",jbtn_send);								// '전송'버튼은 동쪽
		back = getToolkit().getImage("src\\chat\\step1\\accountmain.png");	//
		
		jta_display = new JTextArea() {										// 대충 배경관련된거
			private static final long serialVersionUID = 1L;
			public void paint(Graphics g) {
				g.drawImage(back, 0, 0, this);
				Point p = jsp_display.getViewport().getViewPosition();
				g.drawImage(back, p.x, p.y, null);
				paintComponent(g);
			}
		};
		jta_display.setLineWrap(true);
		jta_display.setOpaque(false);
		Font font = new Font("돋움",Font.BOLD,16);
		jta_display.setFont(font);
		jsp_display = new JScrollPane(jta_display);		
		jp_first.add("Center",jsp_display);
		jp_first.add("South",jp_first_south);
		this.add(jp_first);
		this.add(jp_second);
		this.setTitle(nickName);
		this.setSize(800, 550);
		this.setVisible(true);
	}
	
	
	public static void main(String args[]) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		TalkClient tc = new TalkClient();
		tc.initDisplay();
		tc.init();
	}
	//소켓 관련 초기화
	public void init() {
		try {
			//서버측의 ip주소 작성하기
			socket = new Socket("127.0.0.1",3002);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			//initDisplay에서 닉네임이 결정된 후 init메소드가 호출되므로
			//서버에게 내가 입장한 사실을 알린다.(말하기)
			oos.writeObject(100+"#"+nickName);
			//서버에 말을 한 후 들을 준비를 한다.
			TalkClientThread tct = new TalkClientThread(this);
			tct.start();
		} catch (Exception e) {
			//예외가 발생했을 때 직접적인 원인되는 클래스명 출력하기
			System.out.println(e.toString());
		}
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		String msg = jtf_msg.getText();
		if(jtf_msg==obj) {
			try {
				oos.writeObject(201
						   +"#"+nickName
						   +"#"+msg);
				jtf_msg.setText("");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		else if(jbtn_exit==obj) {
			try {
				oos.writeObject(500+"#"+this.nickName);
				//자바가상머신과 연결고리 끊기
				System.exit(0);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		else if(jbtn_change == obj) {
			String afterName = JOptionPane.showInputDialog("변경할 대화명을 입력하세요.");
			if(afterName == null || afterName.trim().length()<1) {
				JOptionPane.showMessageDialog(this
				, "변경할 대화명을 입력하세요"
				, "INFO", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			try {
				oos.writeObject(202
						   +"#"+nickName
						   +"#"+afterName
						   +"#"+nickName+"의 대화명이 "+afterName+"으로 변경되었습니다.");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}//////////////////////end of actionPerformed
}

