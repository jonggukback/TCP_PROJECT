package privatechat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Member.DBConnectionMgr;
/*************************************************************************
 * 수정해야 할 부분들
 * 1. pvt_room을 서버에서 받아오기
 * @author Yuri,Yihyun
 ************************************************************************/
public class PrivatechatDAO {
/////////////////////////// DB 연동 ///////////////////////////
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	DBConnectionMgr dbMgr = new DBConnectionMgr();
//////////////////////////////////////////////////////////////
	//생성자
	public PrivatechatDAO() {
		
	}
	
	//1:1대화 저장 메소드
	public void savePvt(String pvt_room, String pvt_msg) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO PRIVATECHAT(pvt_room,pvt_msg) VALUES (?,?)");
		int result = 0;
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, pvt_room);
			pstmt.setString(2, pvt_msg);
			result = pstmt.executeUpdate();
			
			System.out.println(result+"건이 입력되었습니다.");
		} catch(SQLException se) {
			System.out.println(se.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnectionMgr.freeConnection(pstmt, con);
		}
		
	}
	
}
