package chatClient.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import chatClient.VO.MemberVO;
import chatServer.DButil;

public class LoginDao {

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	// 회원가입 메소드(signUp) -- 회원가입 버튼과 매핑
	public String signUp(MemberVO pmVO) {
		String result = null;
		String sql = "INSERT INTO CHATMEMBER(ID,PWD,NAME) VALUES(?,?,?)";
		
		con = DButil.getConnection(); // DButil에서 예외처리 했으므로 따로 해주지 않아도 됨
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pmVO.getMem_id());
			pstmt.setString(2, pmVO.getMem_pw());
			pstmt.setString(3, pmVO.getMem_name());
			result = pstmt.executeUpdate()+"";
			System.out.println("데이터 " + result + "건이 추가되었습니다");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		DButil.close(con, pstmt);
		}
		return result;
	}

	public String idCheck(MemberVO pmVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT *                                    ");
		sql.append("  FROM (                                    ");
		sql.append("        SELECT                              ");
		sql.append("               CASE WHEN ID = ? THEN '1'	");
		sql.append("               ELSE '-1'                    ");
		sql.append("                END IDCHECK                 ");
		sql.append("          FROM CHATMEMBER                   ");
		sql.append("        ORDER BY IDCHECK DESC               ");
		sql.append("       )                                    ");
		sql.append(" WHERE ROWNUM = 1                           ");
		String result = "";

		con = DButil.getConnection();
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, pmVO.getMem_id());
			rs = pstmt.executeQuery();
			if (rs.next()) { // 
				result = rs.getString("IDCHECK"); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		DButil.close(con, pstmt, rs); // 닫기
		}
		return result;
	}

	public String login(MemberVO pmVO) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT *											");
		sql.append("  FROM (			 								");
		sql.append("		SELECT 									    ");
		sql.append("		       CASE WHEN ID = ? THEN				");
		sql.append("		            CASE WHEN PWD = ? THEN NAME     ");
		sql.append("		            ELSE '0'					    ");
		sql.append("		            END								");
		sql.append("		       ELSE '-1'							");
		sql.append("		        END LOGIN							");
		sql.append("		  FROM CHATMEMBER    						");
		sql.append("		ORDER BY LOGIN DESC							");
		sql.append("	   )											");
		sql.append(" WHERE ROWNUM = 1									");
		
		String result = "";
		con = DButil.getConnection();
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, pmVO.getMem_id());
			pstmt.setString(2, pmVO.getMem_pw());
			rs = pstmt.executeQuery();

			if (rs.next()) {
					result = rs.getString("LOGIN");
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		DButil.close(con, pstmt, rs);
		}
		return result;
	}

}
