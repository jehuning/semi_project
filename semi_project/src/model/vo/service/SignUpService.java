package model.vo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.vo.dao.BoardDao;
import model.vo.dao.UserDao;

public class SignUpService {
	public int SignUpInput(UserDao u){
//		UserDao user = new UserDao();
		PreparedStatement pstmt = null;
//		ResultSet rs = null;
		JDBCTemplate jt = new JDBCTemplate();
		int r = 0;
		try {
			Connection conn = jt.getConnect();
			 conn.setAutoCommit(false);
			
			String sql = "INSERT  INTO USER_INFO(USER_ID, USER_PASSWORD , USER_NAME , USER_BIRTH, USER_GENDER) "
					+ "VALUES(?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u.getUser_id());
			pstmt.setString(2, u.getUser_password());
			pstmt.setString(3, u.getUser_name());
			pstmt.setDate(4, java.sql.Date.valueOf(u.getUser_birth()));
			pstmt.setString(5, u.getUser_gender());
			r = pstmt.executeUpdate();
			
			String sql1 = "INSERT  INTO USER_PAGENAME(USER_ID, PAGE_NAME) "
					+ "VALUES(?, ?)";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, u.getUser_id());
			pstmt.setString(2, u.getUser_name()+"님의 미니홈피");
			pstmt.executeUpdate();
			
			System.out.println(r);
			conn.commit(); 
			conn.close();
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
		
		return r;
	}
	
}
