package ver2;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class TalkPeople extends JFrame implements ActionListener {
	////////////////통신과 관련한 전역변수 추가 시작//////////////
	Socket 				socket 	= null;
	ObjectOutputStream 	oos 	= null;//말 하고 싶을 때
	ObjectInputStream 	ois		= null;//듣기 할 때
	String 				nickName= null;//닉네임 등록
	////////////////통신과 관련한 전역변수 추가  끝  //////////////
	public static void main(String args[]) {
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

