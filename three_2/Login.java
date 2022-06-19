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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener{
	/****************************************
	 * 				   선언부					*	
	 ****************************************/
    String imgPath	="D:\\java_study\\workspace_java\\kh_javaAC\\Messenger_jababaFamily\\src\\img\\";
    JLabel jlb_id 	= new JLabel("아이디");
    JLabel jlb_pw 	= new JLabel("패스워드");
    
    Font 			jl_font = new Font("맑은고딕체", Font.BOLD, 14);
    JTextField 		jtf_id 	= new JTextField("");
    JPasswordField 	jpf_pw 	= new JPasswordField("");
    
    JButton jbtn_login = new JButton(
            new ImageIcon(imgPath+"로그인2.png"));
    JButton jbtn_join = new JButton(
    		new ImageIcon(imgPath+"회원가입2.png"));
    // JPanel에 쓰일 이미지아이콘
    ImageIcon ig = new ImageIcon(imgPath+"main2.png");
    String user_ID = null;
    
    
	/****************************************
	 * 				   생성자					*	
	 ****************************************/
    public Login(){
        initDisplay();
    }
    /* 배경이미지 */ 
    // JPanal 오버라이드 
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
        jbtn_join.addActionListener(this);
        jbtn_login.addActionListener(this);
        this.setLayout(null);
        this.setTitle("꽉자바 ver.1");
        this.setSize(350, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocation(600, 150);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }});
            
         // id 라인
	        jlb_id.setBounds(45, 200, 80, 40);
	        jlb_id.setFont(jl_font);
	        this.add(jlb_id);
	        
	        //아이디 입력창
	        jtf_id.setBounds(110, 200, 185, 40);
	        this.add(jtf_id);
	        
	        // pw 라인
	        jlb_pw.setBounds(45, 240, 80, 40);
	        jlb_pw.setFont(jl_font);
	        this.add(jlb_pw);
	        
	        //pw 입력창
	        jpf_pw.setBounds(110, 240, 185, 40);
	        this.add(jpf_pw);
	        
	        // 로그인 버튼 라인
	        jbtn_login.setBounds(175, 285, 120, 40);
	        this.add(jbtn_login);
	        
	        // 회원가입 버튼 라인
	        jbtn_join.setBounds(45, 285, 120, 40);
	        this.add(jbtn_join);        
        }
    
	public static void main(String[] args) {
		new Login();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 회원가입 버튼
		Object obj = e.getSource();
		if (obj == jbtn_join) {
			new Sign();
			dispose();
		}
		
		// 로그인 버튼
		else if(obj == jbtn_login) {
			MemberDAO mDao = new MemberDAO();
			user_ID = jtf_id.getText();
			String user_pw = jpf_pw.getText();
        	int result = mDao.signIn(user_ID,user_pw);
        	if(result == 1) {
        		// 로그인성공
        		this.dispose();
        		new And();
        		System.out.println("아이디 : " + user_ID);
        	}
        	else if(result == 0) {
        		//아이디와 비밀번호가 일치하지 않습니다.
        		
        	}
        	else if(result == -1) {
        		//등록되지 않은 회원입니다.
        	}
		}
	}
}
