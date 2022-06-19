package three_2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class And extends JFrame implements ActionListener {
	/****************************************
	 * 				   선언부					*	
	 ****************************************/
	String imgPath	="D:\\java_study\\workspace_java\\kh_javaAC\\Messenger_jababaFamily\\src\\img\\";	// 이미지 경로를 문자열로..지정??
	RoundedButton jbtn_roomin = new RoundedButton("방\s\s입\s\s장");		// 방입장 버튼
	RoundedButton jbtn_1 	  = new RoundedButton("");					// 공방
	RoundedButton jbtn_2	  = new RoundedButton("");					// 공방
//	JButton jbtn_roomin	= new JButton("방입장");							// 기본 J버튼
//			(new ImageIcon(imgPath+"로그인2.png"));
	JButton jbtn_maker 	= new JButton(new ImageIcon(imgPath+"말풍선2.png")); 	// 채팅방생성
	JButton jbtn_pf		= new JButton(new ImageIcon(imgPath+"설정2.png"));	// 프로필
    
    Font jbtn_font = new Font("맑은고딕체", Font.BOLD, 20);
    boolean isjbtn = false;
	// JPanel에 쓰일 이미지아이콘
		ImageIcon ig = new ImageIcon(imgPath+"main4.png");
	
	
	/****************************************
	 * 				   생성자					*	
	 ****************************************/
	public And() {
		initDisplay();
//		jbtn_chrin.addActionListener(this);
//		jbtn_rc.addActionListener(this);
	}
	
	/* 배경이미지 */
	class mypanal extends JPanel {
		public void paintComponent(Graphics g) {
			g.drawImage(ig.getImage(), 0, 0, null);
			setOpaque(false);
			super.paintComponents(g);
		}
	}
	
	/****************************************
	 * 				   화면처리				*	
	 ****************************************/
	public void initDisplay() {
		setContentPane(new mypanal());
	    this.setLayout(null);
	    this.setTitle("꽉자바 ver.1");
	    this.setSize(350, 600);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setVisible(true);
	    this.setLocation(600, 150);
	    this.addWindowListener(new WindowAdapter() { // 익명리스너
	   	 public void windowClosing(WindowEvent e) {
	            System.exit(0);
	   	 }});
	    /* 버튼 구성 */
	    // 방입장, 방생성, 프로필 버튼
	    jbtn_roomin.addActionListener(this);
	    jbtn_maker.addActionListener(this);
	    jbtn_pf.addActionListener(this);
	    
	     // 방입장
	     jbtn_roomin.setBounds(40, 150, 270, 50);
	     jbtn_roomin.setFont(jbtn_font);
		 this.add(jbtn_roomin);

		 
		 // 가짜방
		 jbtn_1.setBounds(40, 230, 270, 50);	
		 jbtn_2.setBounds(40, 310, 270, 50);
		 this.add(jbtn_1);
		 this.add(jbtn_2);
		 
		 // 채팅방생성
		 jbtn_maker.setBounds(215, 500, 40, 40);
		 jbtn_maker.setBorderPainted(false);		// 외부라인 색조절
		 jbtn_maker.setContentAreaFilled(false);	// 배경색 투명도 조절
		 this.add(jbtn_maker);
		 
		 // 프로필
		 jbtn_pf.setBounds(275, 500, 40, 40);
		 jbtn_pf.setBorderPainted(false);
		 jbtn_pf.setContentAreaFilled(false);
		 this.add(jbtn_pf);
			
		}	
	@Override
	public void actionPerformed(ActionEvent e) {
		// 채팅방 입장버튼
		Object obj = e.getSource();
		if(obj == jbtn_roomin) {
			System.out.println("방입장");
			new TalkClient();
		}
		// 채팅방 생성버튼
		else if (obj == jbtn_maker) {
			isjbtn = true;
			jbtn_maker.setBorderPainted(isjbtn);
			System.out.println("채팅방생성");
		}
		
		
		// 회원정보 수정버튼
		else if (obj == jbtn_pf) {
			isjbtn = true;
			System.out.println("프로필 수정");
			jbtn_pf.setContentAreaFilled(isjbtn);
			dispose();
			new Profile();
		}
	}
	
	// 단위테스트용 - 나중에 없애주세
	public static void main(String[] args) {
			new And();
	}
}/////////////////[end of class And]////////////////
