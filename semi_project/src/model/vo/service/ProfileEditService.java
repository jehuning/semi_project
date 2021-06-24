package model.vo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.vo.dao.UserDao;
import model.vo.dao.UserProfileDao;
import oracle.sql.BFILE;

public class ProfileEditService {
	public void setProfile(String id, UserProfileDao up){
//		UserDao user = new UserDao();
//		UserProfileDao up = new UserProfileDao();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JDBCTemplate jt = new JDBCTemplate();
		String pName = "";
		try {
			Connection conn = jt.getConnect();
			
			System.out.println(up.getPic_Name());
			System.out.println(up.getPic_RealName());
			String sql = "INSERT INTO USER_PROFILE(USER_ID, PIC_NAME, PIC_REALNAME, PROFILE_TXT, PROFILE_DATE) VALUES(?,?,?,?,SYSDATE)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, up.getPic_Name());
			pstmt.setString(3, up.getPic_RealName());
			pstmt.setString(4, up.getProfile_txt());
			
			int r = pstmt.executeUpdate();
			System.out.println(r);
			
			
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
	
	}
	public void setProfileTxt(String id, UserProfileDao up){
//		UserDao user = new UserDao();
//		UserProfileDao up = new UserProfileDao();
		PreparedStatement pstmt = null;
		JDBCTemplate jt = new JDBCTemplate();
		
		try {
			Connection conn = jt.getConnect();
			
			System.out.println(up.getPic_Name());
			System.out.println(up.getPic_RealName());
			String sql = "INSERT INTO USER_PROFILE(USER_ID, PIC_NAME, PIC_REALNAME, PROFILE_TXT, PROFILE_DATE) VALUES(?,?,?,?,SYSDATE)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, up.getPic_Name());
			pstmt.setString(3, up.getPic_RealName());
			pstmt.setString(4, up.getProfile_txt());
			
			int r = pstmt.executeUpdate();
			System.out.println(r);
			
			
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
	
	}
}
