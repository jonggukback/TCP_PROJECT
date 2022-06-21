package chatting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Member.DBConnectionMgr;

public class ChatDao {
	//DB 연동
	Connection 			con 	= null;
	PreparedStatement	pstmt 	= null;
	ResultSet			rs		= null;
	DBConnectionMgr 	dbMgr 	= new DBConnectionMgr();
	//생성자
	public ChatDao(){
		
	}
	
	//대화를 했을 때 대화 내용 저장
	public void chatInsert(ChatVO cVO) {
		cVO = new ChatVO();
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO CHATLOG   		");
		sql.append("VALUES (?,?,?,?,?)      	");
		int result = 0;
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, cVO.getRoomnum());
			pstmt.setString(2, cVO.getNickname());
			pstmt.setString(3, cVO.getChat_msg());
			pstmt.setString(4, cVO.getChat_date());
			pstmt.setString(5, cVO.getChat_time());
			result = pstmt.executeUpdate();
			
			System.out.println(result+"건의 대화내용 저장 성공");
		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("대화내용 저장 실패");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("대화내용 저장 실패");
		} finally {
			DBConnectionMgr.freeConnection(pstmt, con);
		}
	}
	
}
