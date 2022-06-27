package three_3;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class changeView_02 extends JFrame implements ActionListener {
	/****************************************
	 * 				   선언부					*	
	 ****************************************/
	boolean box_toggle 	= false;	// 일종의 토글박스 기능 - 클릭시 입력칸들 활성화/비활성화
	
	// J입력
	JLabel jlb_changepw	= new JLabel("PW 변경");	   				// 비번변경  라벨
	JLabel jlb_repw 	= new JLabel("PW 확인");	 				// 비번변경  라벨
	JLabel jlb_nick		= new JLabel("닉네임 변경");				// 닉네임변경 라벨
	JPasswordField 	jpf_changepw	= new JPasswordField("");	// 비밀번호 입력칸
	JPasswordField 	jpf_repw		= new JPasswordField("");	// 재확인	 입력칸
	JTextField 		jtf_nick		= new JTextField("");		// 닉네임 텍스트필드
	
	// J버튼
	// 이미지 경로를 확인 해주세요.
	String imgPath	= "D:\\java_study\\workspace_java\\kh_javaAC\\Messenger_jababaFamily\\src\\img\\";
	JButton 		jbtn_update 	= new JButton(new ImageIcon(imgPath+"변경하기.png"));	// 업데이트 버튼
	JButton 		jbtn_del 		= new JButton("아이디 삭제");							// 아이디 삭제 버튼
	JButton 		jbtn_pwok 		= new JButton(new ImageIcon(imgPath+"버튼.png")); 	// 비번 체크 버튼	
	JButton 		jbtn_nickok 	= new JButton(new ImageIcon(imgPath+"버튼.png")); 	// 닉네임 체크 버튼
	// 폰트설정
	Font			jl_font 		= new Font("맑은고딕체", Font.BOLD, 17);				// 폰트를 파라미터 값(글씨체, 폰트굵기, 크기)으로  생성한다.
	
	/****************************************
	 * 				   생성자					*	
	 ****************************************/
	public changeView_02() {
		initDisplay(); 
		}
	
	/****************************************
	 * 				   화면처리				*	
	 ****************************************/
	public void initDisplay() {
		
	    this.setLayout(null);									// 레이아웃
	    this.setTitle("꽉자바 ver.1");							// 타이틀 붙이기
	    this.setSize(450, 410);									// 실행창 크기
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// ?
	    this.setVisible(true);									// 보인다.
	    this.setLocation(600, 150);								// 실행시 위치지정
	    this.addWindowListener(new WindowAdapter() { // 익명리스너
	   	 public void windowClosing(WindowEvent e) {
	            System.exit(0);
	   	 }});
	    jbtn_pwok.addActionListener(this);		// 비번확인버튼의 이벤트를 듣는다.
	    jbtn_nickok.addActionListener(this);	// 닉네임확인 버튼 
	    jbtn_update.addActionListener(this);	// 변경하기 버튼
	    
	    
        /////////////////////////// 비밀번호 ////////////////////////////////
        // 비밀번호 변경
        jlb_changepw.setBounds(40, 40, 130, 40);		// 위치지정
        jlb_changepw.setFont(jl_font);					// 폰트세팅
        this.add(jlb_changepw);							// 붙이기
        // 비밀번호 텍스트입력칸
        jpf_changepw.setBounds(140, 40, 200, 40);		// 위치지정
        jpf_changepw.setEnabled(box_toggle);			// 디폴트 : 비활성화 상태
        this.add(jpf_changepw);							// 붙이기

        // 비번재확인
        jlb_repw.setBounds(40, 100, 130, 40);			// 위치지정
        jlb_repw.setFont(jl_font);						// 폰트세팅
        this.add(jlb_repw);								// 붙이기
        // 비번재확인 텍스트입력칸
        jpf_repw.setBounds(140, 100, 200, 40);			// 위치지정
        jpf_repw.setEnabled(box_toggle);				// 디폴트 : 비활성화 상태
        this.add(jpf_repw);								// 붙이기
        
        // 비밀번호 확인버튼
        jbtn_pwok.setBounds(355, 45, 28, 28);			// 위치지정
        this.add(jbtn_pwok);							// 붙이기
        /////////////////////////// 비밀번호 ///////////////////////////////
        
        /////////////////////////// 닉네임 ////////////////////////////////
        // 닉네임
        jlb_nick.setBounds(40, 160, 130, 40);			// 위치지정
        jlb_nick.setFont(jl_font);						// 폰트세팅
        this.add(jlb_nick);								// 붙이기
        // 닉네임 텍스트필드
        jtf_nick.setBounds(140, 160, 200, 40);			// 위치지정
        jtf_nick.setEnabled(box_toggle);				// 디폴트 : 비활성화 상태
        this.add(jtf_nick);								// 붙이기
        
        // 닉네임 확인 버튼
        jbtn_nickok.setBounds(355, 160, 28, 28);		// 위치지정
        this.add(jbtn_nickok);							// 붙이기
        /////////////////////////// 닉네임 ////////////////////////////////
        
        // 변경하기 버튼
        jbtn_update.setBounds(160, 230, 140, 40);		// 위치지정
        jbtn_update.setEnabled(box_toggle);				// 디폴트 : 비활성화 상태
        this.add(jbtn_update);							// 붙이기
        
        // 아이디 삭제 버튼
        jbtn_del.setBounds(160, 290, 140, 40);			// 위치지정
        jbtn_del.setFont(jl_font);						// 폰트지정
		jbtn_del.setEnabled(box_toggle);				// 디폴트 : 비활성화 상태
		jbtn_del.setBackground(new Color(158,9,9));		// 색지정
		jbtn_del.setForeground(new Color(212,212,212)); // 색지정
        this.add(jbtn_del);
        
        
	}/////////////////[end of initDisplay]////////////////
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		// 비밀번호 or 닉네임 중복확인 버튼
		if (obj == jbtn_pwok) {						// '비밀번호체크 버튼' 눌리면
			System.out.println("버튼눌림");
			MemberDAO mDao = new MemberDAO();			// DAO 인스턴스화
			String user_pw = jpf_changepw.getText();	// user_changepw에 입력한 값을 저장한다.
//			int result = mDao.signIn(user_id ,user_pw);	// 위에서 입력한 pw를 dao클래스의 아이디체크메소드 파라미터로 던져주고 반환받은 값을 result에 저장
//			if(result == -1) {							// 만약 result가 -1이면 중복된 아이디가 있다.
//				// Dao에 있는 메시지 출력
//			}else if(result == 1) {						// 사용가능한 아이디라면
				box_toggle = false;						
				jtf_nick.setEnabled(box_toggle);		
				box_toggle = true;						
				jpf_changepw.setEnabled(box_toggle);		
				jpf_repw.setEnabled(box_toggle);			
				jbtn_update.setEnabled(box_toggle);		
				jbtn_del.setEnabled(box_toggle);			
				} 
//			else if (obj == jbtn)
		//	}
			else if (obj == jbtn_nickok) {
				box_toggle = false;						
				jpf_changepw.setEnabled(box_toggle);		
				jpf_repw.setEnabled(box_toggle);			
				box_toggle = true;						
				jtf_nick.setEnabled(box_toggle);		
				jbtn_update.setEnabled(box_toggle);		
				jbtn_del.setEnabled(box_toggle);			
				}
		
			else if (obj == jbtn_update) {			// '변경하기 버튼'이 눌리면
//			MemberDAO mDao = new MemberDAO();		// DAO 인스턴스화
//			String user_ID = jtf_id.getText();		// user_ID는 미리 입력받아져 있어야한다.
//			String user_pw = jpf_pw.getText();		// user_pw에 입력받은 텍스트를 넣음 
//			String user_nick = jtf_nick.getText();	// user_name에 입력받은 텍스트를 담음
//	    	int result = mDao.signUp(user_ID,user_pw,user_nick); // 위에서 담은 3가지 변수값을 Dao클래스 아래 signUp메소드로 던져서 DB 대조후에 리턴값 받음
//	    	if(result == 1) {	//회원가입 성공		
				
//    			new TalkClient();
    			dispose();
	    	}
		}
//	}
	
	public static void main(String[] args) {
		new changeView_02();
	}

}/////////////////[end of class changeView_02]////////////////
