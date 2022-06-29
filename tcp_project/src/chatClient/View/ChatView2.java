package chatClient.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import chatClient.Controller.Controller;
import chatClient.Controller.SocketThread;
import chatClient.VO.MsgVO;
import chatServer.Protocol;

import java.awt.*;
import javax.swing.*;

public class ChatView2 extends JFrame implements ActionListener,ItemListener  {
	Vector<String> userList = new Vector<>();
	Image img = null;
	Controller controller = Controller.getInstance();
	SocketThread st = new SocketThread(this);
	JTextField jtf_name = new JTextField(20);
	JButton jbtn_search = new JButton("찾기");
	String comboicon[] = {"전체보기","닉네임으로 검색"};
	JComboBox jcb = new JComboBox(comboicon);
	String selectCombo = null;

	JPanel jp_first 			=	new JPanel();
	JPanel jp_first_south 		= 	new JPanel();
	JPanel jp_first_east		= 	new JPanel();
	JPanel jp_second 			= 	new JPanel();
	JPanel jp_second_north      =   new JPanel();
	JPanel jp_second_south 		= 	new JPanel();
	
	JButton jbtn_whisper 		= 	new JButton("1:1");
	JButton jbtn_change 		= 	new JButton("회원수정");
	JButton jbtn_exit 			= 	new JButton("나가기");
	JButton jbtn_send 			= 	new JButton("전송");
	JButton jbtn_emoticon 		= 	new JButton("이모티콘");
	JTextField jtf_msg 			=	new JTextField(20);
	public JTextArea jta_display 		= 	null; // 채팅창 배경
	JScrollPane jsp_display		= 	null; // 채팅창
	Image back					= 	null;
	String nickName			    = 	null;
	
	String cols[] = {"접속자"};
	String data[][] = new String[0][1];
	DefaultTableModel dtm = new DefaultTableModel(data,cols);
	JTable jtb = new JTable(dtm);
	JScrollPane jsp = new JScrollPane(jtb);
	JSplitPane jspp = new JSplitPane(SwingConstants.VERTICAL, jp_first,jp_second);
	
	// ChatView가 실행되면서 동시에 TalkClienThread가 생성되고
	// run()메소드로 쓰레드가 실행됩니다.
	public ChatView2(String nickName) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.nickName = nickName;
		initDisplay(true);
		st.start();
	}
	
	public void initDisplay(boolean is) {
		/////////////////////////배경 이미지/////////////////////////////
		back = getToolkit().getImage("C:\\java\\workspace_java\\이미지\\채팅창 배경.png");
		jta_display = new JTextArea() {
			private static final long serialVersionUID = 1L;
			public void paint(Graphics g) {
				g.drawImage(back, 0, 0, this);
				Point p = jsp_display.getViewport().getViewPosition();
				g.drawImage(back, p.x, p.y, null);
				paintComponent(g);
			}
		};
		jp_second_north.setLayout(new BorderLayout());
		jp_second_north.add("West",jcb);
		jp_second_north.add("Center",jtf_name);
		jp_second_north.add("East",jbtn_search);
		
		jp_second_south.setLayout(new GridLayout(1,3));
		jp_second_south.add(jbtn_whisper); // 1대1 대화
		jp_second_south.add(jbtn_change); //회원정보 수정
		jp_second_south.add(jbtn_exit);	// 나가기
		
		jp_first_south.setLayout(new BorderLayout());
		jp_first_east.setLayout(new GridLayout(1,2));
		jp_first_east.add(jbtn_emoticon);
		jp_first_east.add(jbtn_send);
		jp_first_south.add("Center",jtf_msg);
		jp_first_south.add("East",jp_first_east);
		jspp.setPreferredSize(new Dimension(400,200));
//		jspp.setOrientation(SwingConstants.VERTICAL);
		jp_first.setLayout(new BorderLayout());
		jp_second.setLayout(new BorderLayout());
		jsp_display = new JScrollPane(jta_display);	
		jp_first.add("Center", jsp_display);
		jp_first.add("South", jp_first_south);
		jp_second.add("North", jp_second_north);
		jp_second.add("Center", jsp);
		jp_second.add("South", jp_second_south);
		
		jta_display.setLineWrap(true);
		jta_display.setOpaque(false);
		Font font = new Font("나눔고딕", Font.BOLD, 15);
		jta_display.setFont(font);
		this.add(jspp);
		this.setTitle(nickName);
		this.setSize(800, 550);
		this.setVisible(is);
		this.setLocation(600, 150);
		setResizable(false); // 창이 가운데 나오도록
		jbtn_search.setEnabled(false);
		
		
		///////// 이벤트 처리 ////////////
		jtf_msg.addActionListener(this);
		jbtn_change.addActionListener(this);
		jbtn_exit.addActionListener(this);
		jbtn_emoticon.addActionListener(this);
		jbtn_search.addActionListener(this);
		jbtn_whisper.addActionListener(this);
		jcb.addItemListener(this);
	}

	public String getMsg() {
		String msg = jtf_msg.getText();
		jtf_msg.setText("");
		return msg;
	}
	
	public String getNickName() {
		return this.nickName;
	}
	
	public void append(String msg) {
		jta_display.append(msg+"\n");
		jta_display.setCaretPosition(jta_display.getDocument().getLength());
	}

	public void addRow(String nickName) {
		userList.add(nickName);
		System.out.println("userList 추가 : "+userList);
		dtm.addRow(new String[]{nickName}); /// 접속인원 보여주는 dtm에 닉네임 추가
	}
	
	
	public void deleteRow(String nickName) {
		for (int i = 0; i < dtm.getRowCount(); i++) {
			String n = (String) dtm.getValueAt(i, 0);
			if (n.equals(nickName)) {
				dtm.removeRow(i); // 나가면 dtm(접속인원)에서 제거
			}
		}
		
		for (String name : userList) {
			if (name.equals(nickName)) {
				userList.remove(name);
			}
		}
		System.out.println("userList 삭제 : "+userList);
	}
	
	public void reFresh(String nickName, String afterName) {
		for (int i = 0; i < dtm.getRowCount(); i++) {
			String imsi = (String) dtm.getValueAt(i, 0);
			if (nickName.equals(imsi)) {
				dtm.setValueAt(afterName, i, 0);
				break;
			}
		}
		setTitle(afterName);
		this.nickName = afterName;
	}
	
	public void successMsg(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Success!", JOptionPane.INFORMATION_MESSAGE);
	}

	public void errorMsg(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error!", JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();

		if (jtf_msg == obj) {
			MsgVO mVO = new MsgVO();
			mVO.setProtocol(Protocol.GROUP_MESSAGE);
			mVO.setNickname(nickName);
			mVO.setMsg(getMsg());
			controller.send(mVO);

		} else if (jbtn_exit == obj) {
			MsgVO mVO = new MsgVO();
			mVO.setProtocol(Protocol.ROOM_OUT);
			mVO.setNickname(nickName);
			controller.send(mVO);
			System.exit(0);

		} else if (jbtn_change == obj) {
			String afterName = JOptionPane.showInputDialog("변경할 대화명을 입력하세요.");
			if (afterName == null || afterName.trim().length() < 1) {
				errorMsg("변경할 대화명을 입력해주세요.");
				return;
			} else {
				MsgVO mVO = new MsgVO();
				mVO.setProtocol(Protocol.NICKNAME_CHANGE);
				mVO.setNickname(nickName);
				mVO.setAfter_nickname(afterName);
				controller.send(mVO);
			}
		}else if (jbtn_emoticon == obj) {
			successMsg("이모티콘 메세지 전송");
			MsgVO mVO = new MsgVO();
			
			mVO.setFileName("123.jpg");
			mVO.setProtocol(Protocol.IMAGE);
			controller.send(mVO);
		}else if (jbtn_search == obj) {
			int count = 0;
			String selectName = jtf_name.getText();
			System.out.println("선택한 이름 : "+selectName);
			System.out.println("유저 리스트 : "+userList);
			for (String name : userList) {
				if (name.matches("(.*)"+selectName+"(.*)")) {
					dtm.setRowCount(0);
					dtm.addRow(new String[]{name});
					return;
				}else {
					count++;
				}
			}
			if (count == userList.size()) {
				errorMsg("검색하신 닉네임은 존재하지 않습니다, 다시 검색해주세요");
				jtf_name.setText("");
				dtm.setRowCount(0);
				for (String name : userList) {
					dtm.addRow(new String[]{name});
				}
				count = 0;
			}
		}else if (jbtn_whisper == obj) {
			int select = jtb.getSelectedRow();
			String nickname = (String) dtm.getValueAt(select, 0);
			successMsg(nickname+" 님에게 1대1 대화를 신청했습니다.");
			jtf_name.setText("");
			dtm.setRowCount(0);
			for (String name : userList) {
				dtm.addRow(new String[]{name});
			}
		}
	}
	
	

	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, null);
		super.paintComponents(g);
	}
	
	public void sendimg(String fileName) {
		ImageIcon imgicon = new ImageIcon("C:\\Users\\User\\Desktop\\TCP_PROJECT\\tcp_project\\img\\"+fileName);
		img = imgicon.getImage();
		jta_display.add(new JLabel("ㅎㅇ"));
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Object obj = e.getSource();
		if (obj == jcb) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				selectCombo = comboicon[jcb.getSelectedIndex()];
				System.out.println(selectCombo);
				if (selectCombo.equals("전체보기")) {
					jbtn_search.setEnabled(false);
					dtm.setRowCount(0);
					for (String name : userList) {
						dtm.addRow(new String[]{name});
					}
					return;
				}else if(selectCombo.equals("닉네임으로 검색")) {
					jbtn_search.setEnabled(true);
				}
			}
		}
	}
}
