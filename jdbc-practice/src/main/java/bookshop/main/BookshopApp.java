package bookshop.main;

import java.util.List;
import java.util.Scanner;

import bookshop.dao.BookDao;
import bookshop.vo.BookVo;

public class BookshopApp {

	public static void main(String[] args) {
		System.out.println("*****도서 정보 출력하기******");
		displayBookInfo();
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("대여 하고 싶은 책의 번호를 입력하세요:");
		Long num = scanner.nextLong();
		scanner.close();
		
		new BookDao().updateRent(num);
		
		System.out.println("*****도서 정보 출력하기******");
		displayBookInfo();
		
	}

	private static void displayBookInfo() {
		List<BookVo> list = new BookDao().findAll();
		
		for (BookVo vo : list) {
			System.out.println("책 번호: " + vo.getNo() + "| 책 제목: " + vo.getTitle() + "| 저자: " + vo.getAuthorName() + "| 대여 유무: " + ("n".equals(vo.getRent())? "재고 있음" : "재고 없음"));
		}
	}
	

}
