package model.vo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.vo.dao.BoardDao;
import model.vo.dao.UserDao;

public class SignInService {
	public String signInCheck(UserDao u){
		JDBCTemplate jt = new JDBCTemplate();
		Connection conn = jt.getConnect();
		//		UserDao user = new UserDao();
		PreparedStatement pstmt = null;
//		ResultSet rs = null;
		
		int rI = 0;
		int rP = 0;
		
		try {
			
			
			
			String sqlId = "SELECT USER_ID FROM USER_INFO WHERE USER_ID = ? ";
			pstmt = conn.prepareStatement(sqlId);
			pstmt.setString(1, u.getUser_id());
			rI = pstmt.executeUpdate();
			
			String sqlPass = "SELECT USER_PASSWORD FROM USER_INFO WHERE USER_ID = ? AND USER_PASSWORD = ?";
			pstmt = conn.prepareStatement(sqlPass);
			pstmt.setString(1, u.getUser_id());
			pstmt.setString(2, u.getUser_password());
			rP = pstmt.executeUpdate();
			System.out.println(rI);
			System.out.println(rP);
			if(rI == 1 && rP == 1){
				
				return "success";
			}else if(rI == 1){
				return "wrongPass";
			}else if(rI == 0){
				return "wrongId";
			}
		
			conn.close();
		} catch (SQLException e) {
		
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
		return "wrongId";
	}
	public UserDao signInUser(UserDao u){
		UserDao user = new UserDao();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JDBCTemplate jt = new JDBCTemplate();
	
		try {
			Connection conn = jt.getConnect();
			
			
			String sql = "SELECT USER_ID, USER_NAME, USER_BIRTH, USER_GENDER FROM USER_INFO WHERE USER_ID = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u.getUser_id());
			pstmt.executeUpdate();
			rs = pstmt.executeQuery(sql);
			
			while(rs.next()){
				user.setUser_id(rs.getString("user_id"));
				user.setUser_name(rs.getString("user_name"));
				user.setUser_birth(rs.getString("user_birth"));
				user.setUser_gender(rs.getString("user_gender"));
			}
			
			
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
		return user;
	}
	
}
