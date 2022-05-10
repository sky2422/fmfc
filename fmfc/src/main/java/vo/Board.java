package vo;

import java.util.Date;

public class Board {
	private int board_num; /* 글 번호 */
	
	private String c_id; /* 글 작성자 */
	private String board_subject; /* 글 제목 */
	private String board_content; /* 글 내용 */
	private String board_file; /* 첨부 파일 */
	
	private int board_count; /* 조회수 */
	private Date board_date; /* 작성일 */
	
	public Board() {
		super();
	}
	//글쓰기 폼에 있는 필드로만 구성된 생성자 
	public Board(String c_id, String board_subject, String board_content, String board_file) {
		super();
		this.c_id = c_id;
		this.board_subject = board_subject;
		this.board_content = board_content;
		this.board_file = board_file;
	}

	//글쓰기 폼에 있는 필드로만 구성된 생성자 + 글번호 추가
	public Board(int board_num, String c_id, String board_subject, String board_content, String board_file) {
		super();
		this.board_num = board_num;
		this.c_id = c_id;
		this.board_subject = board_subject;
		this.board_content = board_content;
		this.board_file = board_file;
	}
	
	//글 조회
	public Board(int board_num, String c_id, String board_subject, int board_count, Date board_date) {
		super();
		this.board_num = board_num;
		this.c_id = c_id;
		this.board_subject = board_subject;
		this.board_count = board_count;
		this.board_date = board_date;
	}

	public int getBoard_num() {
		return board_num;
	}
	
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}

	public String getC_id() {
		return c_id;
	}

	public void setC_id(String c_id) {
		this.c_id = c_id;
	}

	public String getBoard_subject() {
		return board_subject;
	}

	public void setBoard_subject(String board_subject) {
		this.board_subject = board_subject;
	}

	public String getBoard_content() {
		return board_content;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

	public String getBoard_file() {
		return board_file;
	}

	public void setBoard_file(String board_file) {
		this.board_file = board_file;
	}

	public int getBoard_count() {
		return board_count;
	}

	public void setBoard_count(int board_count) {
		this.board_count = board_count;
	}

	public Date getBoard_date() {
		return board_date;
	}

	public void setBoard_date(Date board_date) {
		this.board_date = board_date;
	}

	
	
}

