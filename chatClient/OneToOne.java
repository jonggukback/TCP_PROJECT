package chatClient;

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
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class OneToOne extends JFrame implements ActionListener {
	String nickName = null;
	TalkClient tc = null;
	Image back = null;
	JPanel jp_first = new JPanel();
	JPanel jp_first_south 	= new JPanel(); 
	JPanel jp_first_east 	= new JPanel(); 
	JTextArea jta = new JTextArea(5,20);
	JScrollPane jsp_display = new JScrollPane(jta);
	JTextArea jta_display = null;
	JTextField jtf_msg = new JTextField(10);

	JButton jbtn_send = new JButton("전송");
	JButton jbtn_emoticon = new JButton("이모티콘");

	Font font = new Font("나눔고딕", Font.BOLD, 15);

	public OneToOne() {
		initDisplay();
	}
	public void initDisplay() {
		back = getToolkit().getImage("C:\\java\\workspace_java\\이미지\\main4");
		jta_display = new JTextArea() {
			private static final long serialVersionUID = 1L;
			
			public void paint(Graphics g) {
				g.drawImage(back, 0, 0, this);
				Point p = jsp_display.getViewport().getViewPosition();
				g.drawImage(back, p.x, p.y, null);
				paintComponent(g);
			}
		};
		
		jp_first_south.setLayout(new BorderLayout());
		jp_first_east.setLayout(new GridLayout(1,2));
		jp_first_east.add(jbtn_emoticon);
		jp_first_east.add(jbtn_send);
		jp_first_south.add("Center",jtf_msg);
		jp_first_south.add("East",jp_first_east);
		jp_first.setLayout(new BorderLayout());
		jp_first.add("Center", jsp_display);
		jp_first.add("South", jp_first_south);
		jta_display.setLineWrap(true); //자동 줄바꾸기 사용
		jta_display.setOpaque(true); //Opaque값을 true로 미리 설정해 주어야 배경색이 적용된다
		jta_display.setFont(font); // 채팅창 폰트
		this.add(jp_first);	
		this.setSize(350,600);
		this.setVisible(true);
		setResizable(false); // 창의 크기를 조절할 수 없도록
		
		
		jtf_msg.addActionListener(this);
	}

	public String getMsg() {
		String msg = jtf_msg.getText();
		jtf_msg.setText("");
		return msg;
	}

	public void successMsg(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Success!", JOptionPane.INFORMATION_MESSAGE);
	}

	public void errorMsg(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error!", JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if (jtf_msg == obj) {
			String msg = jtf_msg.getText();
			tc.groupMsg(msg);
			jtf_msg.setText("");
		}
	}

	public static void main(String[] args) {
		new OneToOne();
	}

}
