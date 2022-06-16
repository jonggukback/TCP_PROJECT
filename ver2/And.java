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

import ver2.Login.mypanal;

public class And extends JFrame implements ActionListener {
	// 이미지 경로
	String imgPath="C:\\java\\workspace_java\\project\\이미지\\";
	// 배경화면
	ImageIcon ig = new ImageIcon(imgPath+"대화방배경.png");
	// 상단 "채팅" 폰트
	Font jl_font = new Font("맑은고딕체", Font.BOLD, 30);
	// 채팅방 이름 폰트
	Font room_font = new Font("맑은고딕체", Font.BOLD, 25);
	// 카톡방 추가하기
    JButton jt_chatplus = new JButton(
            new ImageIcon(imgPath+"말풍선.png"));
    // 설정 (회원정보 수정하기 & 탈퇴)
    JButton jbtn_change = new JButton(
    		new ImageIcon(imgPath+"설정.png"));
	JLabel jb_chat = new JLabel("채팅");
	
	//////////임시 채팅방///////
	JButton jb_room = new JButton("꽉자바 패밀리 채팅방");
	
	public And() {
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
    /////////////////////////////////////////////////////
    /* 화면처리 */
    /////////////////////////////////////////////////////    
	public void initDisplay() {
		jb_room.addActionListener(this); // 채팅방 입장
		jt_chatplus.addActionListener(this); // 카톡방 생성하기
		jbtn_change.addActionListener(this); // 개인정보 수정
		
		setContentPane(new mypana2());
        this.setLayout(null);
        this.setVisible(true);
        this.setTitle("꽉자바 ver.1");
        this.setSize(350, 600); //창 사이즈
        this.setLocation(600, 150); // 창 열리는 위치
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }});
        // 위에 대화창
        jb_chat.setBounds(20, 15, 80, 40);
        jb_chat.setFont(jl_font);
        this.add(jb_chat);
        // 채팅방
        jb_room.setBounds(0, 100, 350, 50);
        jb_room.setFont(room_font);
        this.add(jb_room);
        
        // 카톡방 추가하기
        jt_chatplus.setBounds(220, 510, 40, 40);
        this.add(jt_chatplus);
        
        //설정 (회원정보 수정하기 & 탈퇴)
        jbtn_change.setBounds(280, 510, 40, 40);
        this.add(jbtn_change);
		
	}
	
	public static void main(String[] args) {
		new And();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		// 버튼을 누르면 개인정보 수정으로 넘어간다.
		if (obj == jbtn_change) {
			
			new Change();

		}
		else if(obj == jb_room) {
			dispose();
			TalkClient tc = new TalkClient(this);
			tc.initDisplay();
			tc.init();
		}
		//대화방 추가
//		else if(obj == jt_chatplus) {
//			
//		}
	}
}
