package bookshop.vo;

public class BookVo {
	private Long no;
	private String title;
	private String rent ;
	private Long authorNo;   // join까지 생각해서 author의 attribute도 넣어준다.
	private String authorName;
	
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	@Override
	public String toString() {
		return "BookVo [no=" + no + ", title=" + title + ", rent=" + rent + ", authorNo=" + authorNo + ", authorName="
				+ authorName + "]";
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRent() {
		return rent;
	}
	public void setRent(String rent) {
		this.rent = rent;
	}
	public Long getAuthorNo() {
		return authorNo;
	}
	public void setAuthorNo(Long authorNo2) {
		this.authorNo = authorNo2;
	}
}
