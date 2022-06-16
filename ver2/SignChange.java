package ver2;

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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ver2.Sign.mypana2;

public class SignChange extends JFrame implements ActionListener{
	String imgPath="C:\\java\\workspace_java\\project\\이미지\\";	// 이미지 경로를 문자열로..지정??
	JLabel jlb_nickname = new JLabel("변경할 닉네임");							// "[입력]" : 문자열을 화면에 그림
	JLabel jlb_pw = new JLabel("변경할 비밀번호");							// "[입력]" : 문자열을 화면에 그림
	JLabel jlb_repw = new JLabel("비밀번호 재확인");					// "[입력]" : 문자열을 화면에 그림
	JButton jlb_del = new JButton("아이디 삭제");
	
	Font jl_font = new Font("맑은고딕체", Font.BOLD, 17);				// ID와 PW의 (글씨체, 폰트굵기, 크기)를 파라미터 값으로 호출한다?
	JTextField jtf_nickname = new JTextField("");
	JTextField jpf_pw = new JTextField("");				
	JTextField jtf_repw = new JTextField("");			
	// 변경하기 버튼
	JButton jbtn_ok = new JButton(
	    		new ImageIcon(imgPath+"변경하기.png"));
	
	// JPanel에 쓰일 이미지아이콘
	ImageIcon ig = new ImageIcon(imgPath+"main4.png");
	
	public SignChange() {
		initDisplay();
	}
	/* 배경이미지 */
	class mypana2 extends JPanel {
		public void paintComponent(Graphics g) {
			g.drawImage(ig.getImage(), 0, 0, null);
			setOpaque(false);
			super.paintComponents(g);
		}
	}
	public void initDisplay() {
		setContentPane(new mypana2());
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

				// 변경할 이름
				jlb_nickname.setBounds(40, 100, 200, 40);
				jlb_nickname.setFont(jl_font);
				jtf_nickname.setBounds(40, 130, 250, 40);
				this.add(jlb_nickname);
				this.add(jtf_nickname);

				// 변경할 pw 라인
				jlb_pw.setBounds(40, 180, 200, 40);
				jlb_pw.setFont(jl_font);
				jpf_pw.setBounds(40, 213, 250, 40); // 모음유무에 따라 실제 값 차이는 없지만 시각적인 줄간격 오차가 나서 모음에는 3씩 추가
				this.add(jlb_pw);
				this.add(jpf_pw);
				
				// 비번 재확인
				jlb_repw.setBounds(40, 260, 150, 40);
				jlb_repw.setFont(jl_font);
				jtf_repw.setBounds(40, 293, 250, 40);
				this.add(jlb_repw);
				this.add(jtf_repw);
				
				//변경하기 버튼
				jbtn_ok.setBounds(105, 340, 120, 40);
				this.add(jbtn_ok);
				
				//아이디 삭제 버튼
				jlb_del.setBackground(new Color(158,9,9));
				jlb_del.setForeground(new Color(212,212,212));
				jlb_del.setBounds(90, 500, 150, 40);
				jlb_del.setFont(jl_font);
				this.add(jlb_del);
				
	}
	
	public static void main(String[] args) {
		new SignChange();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == jbtn_ok) {
		MemberDAO mDao = new MemberDAO();
		String user_NICKNAME = jtf_nickname.getText(); // 아이디를 db에서 불러 와야함
		String user_PW = jpf_pw.getText();
    	int result = mDao.signIn(user_NICKNAME,user_PW);
    		if(result == 1) {
    		// 로그인성공
    		this.dispose();
    		new And();
    		}
    		else if(result == 0) {
    			//아이디와 비밀번호가 일치하지 않습니다.
    		}
		}
		else if (obj == jlb_del) {
			// 아이디 삭제
		}
	}
}
