package chatClient.View;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import chatClient.Controller.Controller;
import chatClient.VO.MsgVO;
import chatServer.Protocol;

public class ChatView extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel jp_second = new JPanel();
	JPanel jp_second_south = new JPanel();
	JButton jbtn_one = new JButton("1:1");
	JButton jbtn_change = new JButton("대화명변경");
	JButton jbtn_font = new JButton("글자색");
	JButton jbtn_exit = new JButton("나가기");
	String cols[] = { "대화명" };
	String data[][] = new String[0][1];
	public DefaultTableModel dtm = new DefaultTableModel(data, cols);
	JTable jtb = new JTable(dtm);
	JScrollPane jsp = new JScrollPane(jtb);
	JPanel jp_first = new JPanel();
	JPanel jp_first_south = new JPanel();
	JTextField jtf_msg = new JTextField(20);// south속지 center
	JButton jbtn_send = new JButton("전송");// south속지 east
	public JTextArea jta_display = null;
	JScrollPane jsp_display = null;
	// 배경 이미지에 사용될 객체 선언-JTextArea에 페인팅
	Image back = null;
	public String nickName = null;
	Controller controller = Controller.getInstance();


	public ChatView(String nickName) {
		this.nickName = nickName;
		initDisplay();
	}

	public void initDisplay() {
		this.setLayout(new GridLayout(1, 2));
		jp_second.setLayout(new BorderLayout());
		jp_second.add("Center", jsp);
		jp_second_south.setLayout(new GridLayout(2, 2));
		jp_second_south.add(jbtn_one);
		jp_second_south.add(jbtn_change);
		jp_second_south.add(jbtn_font);
		jp_second_south.add(jbtn_exit);
		jp_second.add("South", jp_second_south);
		jp_first.setLayout(new BorderLayout());
		jp_first_south.setLayout(new BorderLayout());
		jp_first_south.add("Center", jtf_msg);
		jp_first_south.add("East", jbtn_send);
		back = getToolkit().getImage("src\\chat\\step1\\accountmain.png");
		jta_display = new JTextArea() {
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
		Font font = new Font("나눔고딕", Font.BOLD, 15);
		jta_display.setFont(font);
		jsp_display = new JScrollPane(jta_display);
		jp_first.add("Center", jsp_display);
		jp_first.add("South", jp_first_south);
		this.add(jp_first);
		this.add(jp_second);
		this.setTitle(nickName);
		this.setSize(800, 550);
		this.setVisible(true);
		setResizable(false); // 창이 가운데 나오도록

		jtf_msg.addActionListener(this);
		jbtn_change.addActionListener(this);
		jbtn_exit.addActionListener(this);
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
		Vector<String> userList = new Vector<>();
		userList.add(nickName);
		dtm.addRow(userList); /// 접속인원 보여주는 dtm에 닉네임 추가
	}
	
	public void deleteRow(String nickName) {
		for (int i = 0; i < dtm.getRowCount(); i++) {
			String n = (String) dtm.getValueAt(i, 0);
			if (n.equals(nickName)) {
				dtm.removeRow(i); // 나가면 dtm(접속인원)에서 제거
			}
		}
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
		}
	}
}
