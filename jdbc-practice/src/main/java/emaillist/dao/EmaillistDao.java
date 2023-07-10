package emaillist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import emaillist.vo.EmaillistVo;

public class EmaillistDao {
	public List<EmaillistVo> findAll() {
		List<EmaillistVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://192.168.100.56:3306/webdb?charset=utf8"; //utf8이라고 적어야함
			
		    conn = DriverManager.getConnection(url, "webdb", "webdb");
			
		    
		    String sql = "select * from emaillist";
		    
		    pstmt = conn.prepareStatement(sql);
		    
		    rs = pstmt.executeQuery();
		    
		   while(rs.next()) {
			   Long no = rs.getLong(1);  // 1번째 column가져옴
			   String firstName = rs.getString(2);
			   String lastName = rs.getString(3);
			   String email = rs.getString(4);
			   
			   EmaillistVo vo = new EmaillistVo();
			   vo.setNo(no);
			   vo.setFirstName(firstName);
			   vo.setLastName(lastName);
			   vo.setEmail(email);
			   
			   result.add(vo);
		   }
		    
		}catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}catch (SQLException e) {
			System.out.println("Error:" + e);
		}finally {
			
			try {
				if(rs != null) {
					rs.close();
				}
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

	public void insert(EmaillistVo vo) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://192.168.100.56:3306/webdb?charset=utf8"; //utf8이라고 적어야함
			
		    conn = DriverManager.getConnection(url, "webdb", "webdb");
			
		    
		    String sql = "insert emaillist values(null, ?, ?, ?)";
		    
		    pstmt = conn.prepareStatement(sql);
		    
		    pstmt.setString(1, vo.getFirstName());
		    pstmt.setString(2, vo.getLastName());
		    pstmt.setString(3, vo.getEmail());
		    
		    rs = pstmt.executeQuery();
		    
		    
		}catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}catch (SQLException e) {
			System.out.println("Error:" + e);
		}finally {
			
			try {
				if(rs != null) {
					rs.close();
				}
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
				
	}

	public void deleteByEmail(String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://192.168.100.56:3306/webdb?charset=utf8"; //utf8이라고 적어야함
			
		    conn = DriverManager.getConnection(url, "webdb", "webdb");
			
		    
		    String sql = "delete from emaillist where email=?";
		    
		    pstmt = conn.prepareStatement(sql);
		    
		    pstmt.setString(1, email);
		   
		    
		    rs = pstmt.executeQuery();
		    
		    
		}catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}catch (SQLException e) {
			System.out.println("Error:" + e);
		}finally {
			
			try {
				if(rs != null) {
					rs.close();
				}
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
				
	}
}
