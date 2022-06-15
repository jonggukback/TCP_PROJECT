package three_2;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ChatView extends JFrame implements ActionListener {
	/*****************************************
	 * 					선언부				 *
	 *****************************************/
	String 				nickName	= null;	//닉네임 등록
	
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
	public ChatView() {
		initDisplay();
		jtf_msg.addActionListener(this);
	}
	public ChatView(Login lg) {
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
		
		jta_display = new JTextArea() {										// 
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
	
	/* 단위테스트용 - 나중에 지우기! */
	public static void main(String args[]) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		ChatView cv = new ChatView();
		cv.initDisplay();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
