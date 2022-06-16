package ver2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnectionMgr {
	PreparedStatement 	pstmt = null;
	ResultSet 			rs = null;
	
	public static final String _DRIVER = "oracle.jdbc.driver.OracleDriver";
	public static final String url 	= "jdbc:oracle:thin:@localhost:1521:orcl11";
	public static final String user = "dongcarry";
	public static final String pw 	= "tiger";
	
	public static Connection getConnection(){ // 연결 
		Connection 		con = null;
		
		try {
			Class.forName(_DRIVER);
			con = DriverManager.getConnection(url,user,pw);
			System.out.println("연결 성공");
		} catch (Exception e) {
			System.out.println("연결 실패");
			e.printStackTrace();
		}
		return con;
	}
	
	public static void freeConnection(ResultSet rs, PreparedStatement pstmt, Connection con){
		try {
			if(rs !=null) rs.close();
			if(pstmt !=null) pstmt.close();
			if(con !=null) con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void freeConnection(PreparedStatement pstmt, Connection con){
		try {
			if(pstmt !=null) pstmt.close();
			if(con !=null) con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
