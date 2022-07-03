package chatClient;

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
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class changeView extends JFrame implements ActionListener {
	/****************************************
	 * 				   선언부					*	
	 ****************************************/
	boolean box_toggle 	= false;	// 일종의 토글박스 기능 - 클릭시 입력칸들 활성화/비활성화
	MemberDAO mDao = new MemberDAO();	
	String myid = null;
	
	// J입력
	JLabel jlb_id		= new JLabel("아이디");		   			// 아이디 라벨
	JLabel jlb_pw 		= new JLabel("현재 PW");	 				// 현재 비밀번호 라벨
	JLabel jlb_changepw	= new JLabel("PW 변경");	   				// 비번변경  라벨
	JLabel jlb_repw 	= new JLabel("PW 확인");	 				// 비번변경  라벨
	JLabel jlb_nick		= new JLabel("닉네임 변경");				// 닉네임변경 라벨

//	JTextField 		jtf_id			= new JTextField(myid);		// 로그인에서 받아오는 아이디
	JPasswordField 	jpf_pw			= new JPasswordField("");	// 현재 비밀번호 입력칸
	JPasswordField 	jpf_changepw	= new JPasswordField("");	// 변경 비밀번호 입력칸
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
	public changeView() {
		
	}
	public changeView(ChatView cv) {
		this.myid = cv.myid; 
		System.out.println("chatview의 myid값 불러오기 성공 : " + myid);
	}
	
	/****************************************
	 * 				   화면처리				*	
	 ****************************************/
	public void initDisplay() {
		//선언부에 두게되면 생성자보다 먼저 초기화가 되면서 값 반영이 되지 않는다.
		JTextField 	jtf_id	= new JTextField(myid);		// 로그인에서 받아오는 아이디
		////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////
	    this.setLayout(null);									// 레이아웃
	    this.setTitle("꽉자바 ver.1");							// 타이틀 붙이기
	    this.setSize(450, 700);									// 실행창 크기
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
	    jbtn_del.addActionListener(this);
	    
        //////////////////////////// 아이디 /////////////////////////////////
	    // 아이디
	    jlb_id.setBounds(40, 40, 130, 40);				// 위치지정
	    jlb_id.setFont(jl_font);						// 폰트세팅
	    this.add(jlb_id);								// 붙이기
	    // 아이디 텍스트
	    jtf_id.setBounds(140, 40, 200, 40);				// 위치지정
	    jtf_id.setEnabled(box_toggle);					// 디폴트 : 비활성화 상태
	    this.add(jtf_id);								// 붙이기
        //////////////////////////// 아이디 /////////////////////////////////
	    
        /////////////////////////// 비밀번호 ////////////////////////////////
	    // 현재 비밀번호 
	    jlb_pw.setBounds(40, 100, 130, 40);				// 위치지정
	    jlb_pw.setFont(jl_font);						// 폰트세팅
	    this.add(jlb_pw);								// 붙이기
	    // 현재 비밀번호 텍스트입력칸
	    jpf_pw.setBounds(140, 100, 200, 40);			// 위치지정
	    jpf_pw.setEnabled(!box_toggle);					// 디폴트 : 비활성화 상태
	    this.add(jpf_pw);								// 붙이기
	    
        // 비밀번호 변경
        jlb_changepw.setBounds(40, 160, 130, 40);		// 위치지정
        jlb_changepw.setFont(jl_font);					// 폰트세팅
        this.add(jlb_changepw);							// 붙이기
        // 비밀번호 텍스트입력칸
        jpf_changepw.setBounds(140, 160, 200, 40);		// 위치지정
        jpf_changepw.setEnabled(box_toggle);			// 디폴트 : 비활성화 상태
        this.add(jpf_changepw);							// 붙이기

        // 비번재확인
        jlb_repw.setBounds(40, 220, 130, 40);			// 위치지정
        jlb_repw.setFont(jl_font);						// 폰트세팅
        this.add(jlb_repw);								// 붙이기
        // 비번재확인 텍스트입력칸
        jpf_repw.setBounds(140, 220, 200, 40);			// 위치지정
        jpf_repw.setEnabled(box_toggle);				// 디폴트 : 비활성화 상태
        this.add(jpf_repw);								// 붙이기
        
        // 비밀번호 확인버튼
        jbtn_pwok.setBounds(355, 105, 28, 28);			// 위치지정
        this.add(jbtn_pwok);							// 붙이기
        /////////////////////////// 비밀번호 ///////////////////////////////
        
        /////////////////////////// 닉네임 ////////////////////////////////
        // 닉네임
        jlb_nick.setBounds(40, 280, 130, 40);			// 위치지정
        jlb_nick.setFont(jl_font);						// 폰트세팅
        this.add(jlb_nick);								// 붙이기
        // 닉네임 텍스트필드
        jtf_nick.setBounds(140, 280, 200, 40);			// 위치지정
        jtf_nick.setEnabled(box_toggle);				// 디폴트 : 비활성화 상태
        this.add(jtf_nick);								// 붙이기
        
        // 닉네임 활성화 버튼
        jbtn_nickok.setBounds(355, 285, 28, 28);		// 위치지정
        this.add(jbtn_nickok);							// 붙이기
        /////////////////////////// 닉네임 ////////////////////////////////
        
        // 변경하기 버튼
        jbtn_update.setBounds(160, 350, 140, 40);		// 위치지정
        jbtn_update.setEnabled(box_toggle);				// 디폴트 : 비활성화 상태
        this.add(jbtn_update);							// 붙이기
        
        // 아이디 삭제 버튼
        jbtn_del.setBounds(160, 420, 140, 40);			// 위치지정
        jbtn_del.setFont(jl_font);						// 폰트지정
		jbtn_del.setEnabled(box_toggle);				// 디폴트 : 비활성화 상태
		jbtn_del.setBackground(new Color(158,9,9));		// 색지정
		jbtn_del.setForeground(new Color(212,212,212)); // 색지정
        this.add(jbtn_del);
        
        
	}/////////////////[end of initDisplay]////////////////
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String user_pw = jpf_pw.getText();			// user_pw 	에 사용자가 입력한 텍스트를 담음
		String user_repw = jpf_repw.getText();		// user_repw에 사용자가 입력한 텍스트를 담음
		String user_nickname = jtf_nick.getText();	// user_name에 사용자가 입력한 텍스트를 담음
		Object obj = e.getSource();
		// 비밀번호 확인 버튼
		if (obj == jbtn_pwok) {							// '비밀번호체크 버튼' 눌리면
			int result = mDao.signIn(myid, user_pw);	// *회원확인 - 로그인 다오 대체
			if(result == -1) {							// 만약 result가 -1이면 중복된 아이디가 있다.
			} else if (result == 0) {					// 비밀번호가 일치하지 않습니다.
			} else if (result == 1) {					// 로그인 성공(비번체크)		
				box_toggle = false;						
				jpf_pw.setEnabled(box_toggle);		
				jtf_nick.setEnabled(box_toggle);		
				box_toggle = true;						
				jpf_changepw.setEnabled(box_toggle);		
				jpf_repw.setEnabled(box_toggle);			
				jbtn_update.setEnabled(box_toggle);		
				jbtn_del.setEnabled(box_toggle);
			} 
		}	
		
		if (obj == jbtn_nickok) {						// '닉네임체크 버튼' 눌리면
			box_toggle = false;							// 
			jpf_changepw.setEnabled(box_toggle);		
			jpf_repw.setEnabled(box_toggle);			
			box_toggle = true;						
			jtf_nick.setEnabled(box_toggle);		
			jbtn_update.setEnabled(box_toggle);		
			jbtn_del.setEnabled(box_toggle);			
			}
		
		if (obj == jbtn_update) {						// '변경하기 버튼'이 눌리면
	// user_pw에 입력받은 텍스트를 넣음 
			int result = mDao.editMember(user_nickname,user_repw,myid); 
		    if(result == -1) {
		    	//회원정보 수정 실패
		    	}else {
	    		// 회원정보 수정 성공
	    		dispose();
		    	}					
			}
		
		if (obj == jbtn_del) {							// '삭제하기 버튼'이 눌리면
			int result = mDao.withdrawal(myid, user_repw); 
			}
		}//////////////////[end of actionPerformed]//////////////////////
	
	
}/////////////////[end of class changeView_02]////////////////

