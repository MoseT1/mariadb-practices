package emaillist.dao.test;

import java.util.List;

import emaillist.dao.EmaillistDao;
import emaillist.vo.EmaillistVo;

public class EmaillistDaoTest {

	public static void main(String[] args) {

		
		
		//testInsert();
		
		testFindAll();
		testDelete("kimmose98@naver.com");
		testFindAll();
		
	}

	private static void testDelete(String email) {
		
		new EmaillistDao().deleteByEmail(email);
	}

	private static void testInsert() {
		EmaillistVo vo = new EmaillistVo();
		vo.setFirstName("김");
		vo.setLastName("모세");
		vo.setEmail("kimmose98@naver.com");
		
		new EmaillistDao().insert(vo); 
	}

	private static void testFindAll() {
		List<EmaillistVo> list = new EmaillistDao().findAll();
		
		for(EmaillistVo vo : list) {
			System.out.println(vo);
		}
		
		
		
	}
	
	

}
