package hr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hr.vo.EmployeeVo;

public class EmployeeDao {
	public List<EmployeeVo> findByName(String keyword){
		
		List<EmployeeVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://192.168.100.56:3306/employees?charset=utf8"; //utf8이라고 적어야함
			
		    conn = DriverManager.getConnection(url, "hr", "hr");
			
		    
		    String sql = "select emp_no, first_name, last_name, date_format(hire_date, '%Y-%m-%d')" +
		    " from employees" + " where first_name like ? or last_name like ? order by hire_date desc";
		    
		    pstmt = conn.prepareStatement(sql);
		    pstmt.setString(1, "%" + keyword + "%");
		    pstmt.setString(2, "%" + keyword + "%");
		    rs = pstmt.executeQuery();
		    
		   while(rs.next()) {
			   Long no = rs.getLong(1);  // 1번째 column가져옴
			   String firstName = rs.getString(2);
			   String lastName = rs.getString(3);
			   String hireDate = rs.getString(4);
			   
			   EmployeeVo vo = new EmployeeVo();
			   vo.setNo(no);
			   vo.setFirstName(firstName);
			   vo.setLastName(lastName);
			   vo.setHireDate(hireDate);
			   
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

	public List<EmployeeVo> findBySalary(int min, int max) {
		// TODO Auto-generated method stub
		List<EmployeeVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://192.168.100.56:3306/employees?charset=utf8"; //utf8이라고 적어야함
			
		    conn = DriverManager.getConnection(url, "hr", "hr");
			
		    
		    String sql = "select a.emp_no, first_name, last_name, salary, date_format(hire_date, '%Y-%m-%d')" +
		    " from employees a, salaries b where a.emp_no=b.emp_no and to_date='9999-01-01' and salary>= ? and salary <= ? order by hire_date desc";
		    
		    pstmt = conn.prepareStatement(sql);
		    pstmt.setInt(1, min );
		    pstmt.setInt(2, max);
		    rs = pstmt.executeQuery();
		    
		   while(rs.next()) {
			   Long no = rs.getLong(1);  // 1번째 column가져옴
			   String firstName = rs.getString(2);
			   String lastName = rs.getString(3);
			   int salary = rs.getInt(4);
			   String hireDate = rs.getString(5);
			   
			   EmployeeVo vo = new EmployeeVo();
			   vo.setNo(no);
			   vo.setFirstName(firstName);
			   vo.setLastName(lastName);
			   vo.setSalary(salary);
			   vo.setHireDate(hireDate);
			   
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
