package emaillist.main;

import java.util.List;
import java.util.Scanner;

import emaillist.dao.EmaillistDao;
import emaillist.vo.EmaillistVo;

public class EmaillistApp {
	private static Scanner sc = new Scanner(System.in);  
	
	public static void main(String[] args) {
	
		
		while(true) {
			System.out.print("(l)ist (d)elete (i)nsert (q)uit  >");
			
			String command = sc.nextLine();
			
			if ("q".equals(command)) {
				break;
			} else if("l".equals(command)) {
				doList();
			}else if("d".equals(command)) {
				doDelete();
			}else if("i".equals(command)) {
				doInsert();
			}
		}

	}
	
	private static void doInsert() {
		System.out.print("성: ");
		String firstName = sc.nextLine();
		
		System.out.print("이름: ");
		String lastName = sc.nextLine();
		
		System.out.print("이메일: ");
		String email = sc.nextLine();
		
		EmaillistVo vo = new EmaillistVo();
		vo.setFirstName(firstName);
		vo.setLastName(lastName);
		vo.setEmail(email);
		
		new EmaillistDao().insert(vo);
	}	

	private static void doDelete() {
		System.out.print("이메일: ");
		String email = sc.nextLine();
		
		new EmaillistDao().deleteByEmail(email);
	}

	private static void doList() {
		List<EmaillistVo> list = new EmaillistDao().findAll();
		for (EmaillistVo vo : list) {
			System.out.println(vo.getFirstName() + " " + vo.getLastName() + "-" + vo.getEmail());
		}
	}

}
