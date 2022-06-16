package ver2;

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


public class Change extends JFrame implements ActionListener{
	// 이미지 경로
	String imgPath="C:\\java\\workspace_java\\project\\이미지\\";
	// 배경화면
	ImageIcon ig = new ImageIcon(imgPath+"대화방배경.png");
	// 상단 "채팅" 폰트
	Font jl_font = new Font("맑은고딕체", Font.BOLD, 20);
	// 아이디
	JLabel jlb_id = new JLabel("아이디");
	JTextField jtf_id = new JTextField(""); // id 입력창
    // 패스워드
	JLabel jlb_pw = new JLabel("패스워드");
    JPasswordField jpf_pw = new JPasswordField(""); // pw 입력창
    // 확인버튼
    JButton jb_ok = new JButton(
    		new ImageIcon(imgPath+"확인.png"));
	
	public Change() {
		initDisplay();
	}
	/* 배경이미지 */
    class mypana3 extends JPanel {
        public void paintComponent(Graphics g) {
            g.drawImage(ig.getImage(), 0, 0, null);
            setOpaque(false);
            super.paintComponents(g);
        }
    }
	public void initDisplay() {
		jb_ok.addActionListener(this);
		setContentPane(new mypana3());
		this.setLayout(null);
        this.setVisible(true);
        this.setTitle("꽉자바 ver.1");
        this.setSize(420, 230); //창 사이즈
        this.setLocation(600, 150); // 창 열리는 위치
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }});
    	// id 라인
        jlb_id.setBounds(45, 40, 130, 40);
        jlb_id.setFont(jl_font);
        this.add(jlb_id);
        
        //아이디 입력창
        jtf_id.setBounds(150, 40, 130, 40);
        this.add(jtf_id);
        
        // pw 라인
        jlb_pw.setBounds(45, 90, 130, 40);
        jlb_pw.setFont(jl_font);
        this.add(jlb_pw);
        
        //pw 입력창
        jpf_pw.setBounds(150, 90, 130, 40);
        this.add(jpf_pw);
        
        jb_ok.setBounds(290, 90, 70, 40);
        this.add(jb_ok);
	}
	
	public static void main(String[] args) {
		new Change();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		// 확인버튼 누르면..
		if (obj == jb_ok) {
		this.dispose();
		new SignChange();
		}

	}
}
