package model.vo.dao;

import java.sql.Connection;
import java.util.ArrayList;

public class BoardDao {
	private String board_num;
	private String board_title;
	private String board_writer;
	private String board_content;
	private String board_date;
	
	ArrayList<BoardDao> alB = new ArrayList<>();
	
	
	public ArrayList<BoardDao> getAlB() {
		return alB;
	}

	public void setAlB(ArrayList<BoardDao> alB) {
		this.alB = alB;
	}

	public void insertBoard(Connection conn,BoardDao board) {
		alB.add(board);
		
	}

	public String getBoard_num() {
		return board_num;
	}

	public void setBoard_num(String board_num) {
		this.board_num = board_num;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public String getBoard_writer() {
		return board_writer;
	}

	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}

	public String getBoard_content() {
		return board_content;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

	public String getBoard_date() {
		return board_date;
	}

	public void setBoard_date(String board_date) {
		this.board_date = board_date;
	}
	
	
}
