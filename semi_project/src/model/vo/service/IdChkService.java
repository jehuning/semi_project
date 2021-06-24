package model.vo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.vo.dao.BoardDao;
import model.vo.dao.UserDao;

public class IdChkService {
	public int idDBChk(UserDao u){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JDBCTemplate jt = new JDBCTemplate();
		int r = 0;
		try {
			Connection conn = jt.getConnect();
			System.out.println("DB 연결 성공");
			
			String sql = "SELECT USER_ID FROM USER_INFO WHERE USER_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u.getUser_id() );
			r = pstmt.executeUpdate();
			rs = pstmt.executeQuery(sql);
			while(rs.next()){
				System.out.println(rs.getString("user_id"));
			}
			System.out.println(r);
			
		
			
						
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
		
		return r;
	}
	
}
