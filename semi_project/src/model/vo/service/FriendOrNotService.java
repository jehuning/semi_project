package model.vo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import model.vo.dao.FriendsDao;
import model.vo.dao.UserDao;

public class FriendOrNotService {
	public boolean getFriendOrNot(String sessId, String friendId){
//		UserDao user = new UserDao();
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JDBCTemplate jt = new JDBCTemplate();
		int r = 0;
		try {
			Connection conn = jt.getConnect();
			
			String sql1 = "SELECT * FROM FRIENDS_INFO WHERE USER_ID_1 = ? AND USER_ID_2 = ? ";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, sessId);
			pstmt.setString(2, friendId);
			r += pstmt.executeUpdate();
			
			String sql2 = "SELECT * FROM FRIENDS_INFO WHERE USER_ID_2 = ? AND USER_ID_1 = ? ";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, sessId);
			pstmt.setString(2, friendId);
			r += pstmt.executeUpdate();
		
			System.out.println("친구여부 : " +r);
			if(r==0){
				return false;
			}
			
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
		return true;
	}
}