package three_2;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import three_2.Login.mypanal;

public class And extends JFrame implements ActionListener {
	/****************************************
	 * 				   선언부					*	
	 ****************************************/
	String imgPath	="D:\\java_study\\workspace_java\\kh_javaAC\\Messenger_jababaFamily\\src\\img\\";	// 이미지 경로를 문자열로..지정??
	JButton jbtn_chrin = new JButton("채팅방입장");
//			(new ImageIcon(imgPath+"로그인2.png"));
    JButton jbtn_rc = new JButton("방생성");
//    		(new ImageIcon(imgPath+"회원가입2.png"));
    JButton jbtn_pf = new JButton("프로필");
//    		new ImageIcon(imgPath+"회원가입2.png"));
	
	
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
	      /* 버튼과 텍스트필드 구성 */
	      /* 회원가입 버튼 */
//	     jbtn_join.addActionListener(this);
//	     jbtn_login.addActionListener(this);
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
	     // 채팅방
//	     jbtn_chrin.setBounds(175, 285, 120, 40);
//		 this.add(jbtn_chrin);
//		 
//		 jbtn_rc.setBounds(175, 285, 120, 40);
//		 this.add(jbtn_rc);
//		 
//		 jbtn_pf.setBounds(175, 285, 120, 40);
//		 this.add(jbtn_pf);
			
		}	
	// 단위테스트용 - 나중에 없애주세
	public static void main(String[] args) {
			new And();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// 채팅방 입장버튼
		
		
		
		// 채팅방 생성버튼
		
		
		
		// 회원정보 수정버튼
		
		
		
	}

}
