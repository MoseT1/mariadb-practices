package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertTest02 {
	
	public static void main(String[] args) {
		
		insert("영업2");
	}

	public static boolean insert(String deptName) {
		boolean result = false;
		Connection conn = null;
		
		PreparedStatement pstmt = null;
		try {
			//1.JDBC Driver Class 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mariadb://192.168.100.56:3306/webdb?charset=utf8"; //utf8이라고 적어야함
			
		    conn = DriverManager.getConnection(url, "webdb", "webdb");
			
		    //3. sql, prepare
		    String sql = "insert into dept values(null, ?)";
		    
		    pstmt = conn.prepareStatement(sql);
		    
		    //4. 바인딩
		    pstmt.setString(1, deptName);
		    
		    
		    //5. SQL 실행
		    int count =  pstmt.executeUpdate();  //여기엔 sql 이미 만들어져있기 때문에, 그냥 실행만 시키면 됨
		    
		    result = count ==1;
		    
		}catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}catch (SQLException e) {
			System.out.println("Error:" + e);
		}finally {
			
			//6. 자원 정리
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;

	}

}
