package three_2;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Sign extends JFrame implements ActionListener {
	/////////////////////////////////////////////////////
	/* 선언부 */
	/////////////////////////////////////////////////////
	String imgPath	= "D:\\java_study\\workspace_java\\kh_javaAC\\Messenger_jababaFamily\\src\\img\\";	// 이미지 경로를 문자열로..지정??
	JLabel jlb_id 	= new JLabel("아이디");						// "[입력]" : 문자열을 화면에 그림
	JLabel jlb_pw 	= new JLabel("비밀번호");						// "[입력]" : 문자열을 화면에 그림
	JLabel jlb_repw = new JLabel("비밀번호 재확인");					// "[입력]" : 문자열을 화면에 그림
	JLabel jlb_name = new JLabel("이름");							// "[입력]" : 문자열을 화면에 그림
	
	Font			jl_font 	= new Font("맑은고딕체", Font.BOLD, 17);	// ID와 PW의 (글씨체, 폰트굵기, 크기)를 파라미터 값으로 호출한다?
	JTextField 		jtf_id 		= new JTextField("");						
	JPasswordField 	jpf_pw		= new JPasswordField("");				// PW는 숨겨진다
	JPasswordField 	jpf_repw 	= new JPasswordField("");			
	JTextField 		jtf_name 	= new JTextField("");						
	
    JButton jbtn_but 	= new JButton(
    			new ImageIcon(imgPath+"버튼.png")); //버튼
	
	JButton jbtn_ok 	= new JButton(
	    		new ImageIcon(imgPath+"가입하기.png"));
	// JPanel에 쓰일 이미지아이콘
	ImageIcon ig = new ImageIcon(imgPath+"main4.png");
	
	boolean	isIDCheck = false;
	
	/////////////////////////////////////////////////////
	/* 생성자 */
	/////////////////////////////////////////////////////
	public Sign(){
		initDisplay();
//		jbtn_ok.setEnabled(false);			// 생성자 위치에 있으면 안됌 why?
		jbtn_ok.addActionListener(this);
		jbtn_but.addActionListener(this);
	}
	/////////////////////////////////////////////////////
	/* 배경이미지 */
	// jpanal 오버라이드 //
	/////////////////////////////////////////////////////
	class mypanal extends JPanel {
		public void paintComponent(Graphics g) {
			g.drawImage(ig.getImage(), 0, 0, null);
			setOpaque(false);
			super.paintComponents(g);
		}
	}
	/////////////////////////////////////////////////////
	/* 화면처리 */
	/////////////////////////////////////////////////////
	public void initDisplay() {
		setContentPane(new mypanal());
		
		/* 버튼과 텍스트필드 구성 */
//		jbtn_ok.addActionListener(this);
//		jbtn_but.addActionListener(this);
		this.setLayout(null);
		this.setTitle("꽉자바패밀리 ver.1"); 							// 타이틀 제목
		this.setSize(350, 600);										// 로그인 창 사이즈 - 2개의 파라미터로 지정
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);										// 창을 보여준다.
		this.setLocation(600, 150);									// 창이 처음 띄어지는 위치 - (800, 250)은 중앙
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		// id 라인
		jlb_id.setBounds(40, 100, 80, 40);		
		jlb_id.setFont(jl_font);						
		jtf_id.setBounds(40, 130, 250, 40);
		this.add(jlb_id);						
		this.add(jtf_id);						

		// pw 라인
		jlb_pw.setBounds(40, 180, 80, 40);
		jlb_pw.setFont(jl_font);
		jpf_pw.setBounds(40, 213, 250, 40); 
		this.add(jlb_pw);
		this.add(jpf_pw);
		
		// 비번 재확인
		jlb_repw.setBounds(40, 260, 150, 40);
		jlb_repw.setFont(jl_font);
		jpf_repw.setBounds(40, 293, 250, 40);
		this.add(jlb_repw);
		this.add(jpf_repw);
		
		// 이름
		jlb_name.setBounds(40, 340, 80, 40);
		jlb_name.setFont(jl_font);
		jtf_name.setBounds(40, 373, 250, 40);
		this.add(jlb_name);
		this.add(jtf_name);

		//가입하기 버튼
		jbtn_ok.setEnabled(false);					// 아이디 중복검사가 될 때까지 가입하기 버튼은 잠겨있다.
		jbtn_ok.setBounds(105, 465, 120, 40);
		this.add(jbtn_ok);
		
		//버튼
		jbtn_but.setBounds(295, 135, 25, 25);
		this.add(jbtn_but);  
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// 회원가입 버튼
		Object obj = e.getSource();
		
		// 중복확인 버튼
		if (obj == jbtn_but) {						// '아이디체크 버튼' 눌리면
			MemberDAO mDao = new MemberDAO();		// DAO 인스턴스화
			String user_id = jtf_id.getText();		// user_id에 입력한 값을 저장한다.
			int result = mDao.idCheck(user_id);		// 위에서 입력한 id값을 dao클래스의 아이디체크메소드 파라미터로 던져주고 반환받은 값을 result에 저장
			if(result == -1) {						// 만약 result가 -1이면 중복된 아이디가 있다.
				
			}else if(result == 1) {					// 사용가능한 아이디라면
				jtf_id.setEditable(false);			// 아이디입력칸 비활성화
				jbtn_but.setEnabled(false);			// '중복체크버튼' 비활성화
				isIDCheck = true;					// isIDCheck 전역변수를 true로 바꿔줌
				jbtn_ok.setEnabled(isIDCheck);		// isIDCheck의 값을 받아서 '가입하기' 버튼 상태변화
			}
			
		}
        
		else if (obj == jbtn_ok) {					// '가입하기 버튼'이 눌리면
			MemberDAO mDao = new MemberDAO();		// DAO 인스턴스화
			String user_ID = jtf_id.getText();		// user_ID에 입력받은 텍스트를 담음
			String user_pw = jpf_pw.getText();		// user_pw에 입력받은 텍스트를 넣음 
			String user_name = jtf_name.getText();	// user_name에 입력받은 텍스트를 담음
        	int result = mDao.signUp(user_ID,user_pw,user_name); // 위에서 담은 3가지 변수값을 Dao클래스 아래 signUp메소드로 던져서 DB 대조후에 리턴값 받음
        	if(result == 1) {	
    			new Login();
    			dispose();
        		//회원가입 성공
        	}
        	else if(result == -1) {
        		//회원가입 실패
        	}
        	
        }
}

}


/*
 - 불리언 변수 id_check는 꼭 필요한가?
 - id 중복체크할 때, 오라클 서버의 테이블이 비어있으면 가입하기버튼이 활성화 되지 않는다. 로우가 하나라도 들어가 있어야 활성화가 된다. why???????
 */
