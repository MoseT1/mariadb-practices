package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteTest01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		delete(6L);
		delete(7L);
		
	}
	public static boolean delete(Long no) {
		boolean result = false;
		Connection conn = null;
		Statement stmt = null;
		try {
			//1.JDBC Driver Class 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mariadb://192.168.100.56:3306/webdb?charset=utf8"; //utf8이라고 적어야함
			
		    conn = DriverManager.getConnection(url, "webdb", "webdb");
			
		    //3. Statement 생성, 쿼리 날리는 애
		    stmt = conn.createStatement();
		    
		    //4. SQL 실행  - 여기선 sql문에 ; 붙이면 안된다.
		    String sql = "delete from dept where no= " + no;
		    
		    int count = stmt.executeUpdate(sql); // executeInsert도 있지만 업데이트로 해도 된다.
		    
		    //5. 결과 처리
//		    if (count ==1) {
//		    	result = true;
//		    }
		    result = count ==1;
		    
		}catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}catch (SQLException e) {
			System.out.println("Error:" + e);
		}finally {
			
			//6. 자원 정리
			try {
				if(stmt != null) {
					stmt.close();
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
