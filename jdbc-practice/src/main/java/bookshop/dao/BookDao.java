package bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookshop.vo.BookVo;

public class BookDao {
	public void insert(BookVo vo) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://192.168.100.56:3306/webdb?charset=utf8"; //utf8이라고 적어야함
			
		    conn = DriverManager.getConnection(url, "webdb", "webdb");
			
		    
		    String sql = "insert book(title, author_no) values(?, ?)";
		    
		    pstmt = conn.prepareStatement(sql);
		    
		    pstmt.setString(1, vo.getTitle());
		    pstmt.setLong(2, vo.getAuthorNo());
		    
		    
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

	public void updateRent(Long num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://192.168.100.56:3306/webdb?charset=utf8"; //utf8이라고 적어야함
			
		    conn = DriverManager.getConnection(url, "webdb", "webdb");
			
		    
		    String sql = "update book set rent ="
		    		+ " case when rent='y' then 'n' else 'y' end"
		    		+ "  where no=?";
		    
		    pstmt = conn.prepareStatement(sql);
		    
		    
		    pstmt.setLong(1, num);
		    
		    
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

	public List<BookVo> findAll() {
		
		List<BookVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://192.168.100.56:3306/webdb?charset=utf8"; //utf8이라고 적어야함
			
		    conn = DriverManager.getConnection(url, "webdb", "webdb");
			
		    
		    String sql = "select a.no, title, rent, a.author_no, b.name from book a, author b where a.author_no = b.no";
		    
		    pstmt = conn.prepareStatement(sql);
		    
		    
		    rs = pstmt.executeQuery();
		    
		    while(rs.next()) {
				   Long no = rs.getLong(1);  // 1번째 column가져옴
				   String title = rs.getString(2);
				   String rent = rs.getString(3);
				   Long authorNo = rs.getLong(4);
				   String authorName = rs.getString(5);
				   
				   BookVo vo = new BookVo();
				   vo.setNo(no);
				   vo.setTitle(title);
				   vo.setRent(rent);
				   vo.setAuthorNo(authorNo);
				   vo.setAuthorName(authorName);
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
}
