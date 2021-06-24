package model.vo.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

import model.vo.dao.BoardDao;
import model.vo.dao.FriendsDao;
import model.vo.dao.UserDao;
import model.vo.dao.UserProfileDao;
import oracle.sql.BFILE;

public class MainService {
	public UserDao getUserData(UserDao u){
		JDBCTemplate jt = new JDBCTemplate();
		Connection conn = jt.getConnect();
		UserDao user = new UserDao();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			
			
			
			String sqlId = "SELECT USER_ID, USER_NAME, USER_BIRTH, USER_GENDER FROM USER_INFO WHERE USER_ID = ? ";
			pstmt = conn.prepareStatement(sqlId);
			pstmt.setString(1, u.getUser_id());
			int r = pstmt.executeUpdate();
			System.out.println(r);
			rs = pstmt.executeQuery(sqlId);
			while(rs.next()){
				user.setUser_id(rs.getString("user_id"));
				user.setUser_name(rs.getString("user_name"));
				user.setUser_birth(rs.getString("user_birth"));
				user.setUser_gender(rs.getString("user_gender"));
			}
		
		
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
		
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
		return user;
	}
	public String getPageName(UserDao u){
//		UserDao user = new UserDao();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JDBCTemplate jt = new JDBCTemplate();
		String pName = "";
		try {
			Connection conn = jt.getConnect();
			
			
			String sql = "SELECT PAGE_NAME FROM USER_PAGENAME WHERE USER_ID = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u.getUser_id());
			pstmt.executeUpdate();
			rs = pstmt.executeQuery(sql);
		
			while(rs.next()){
			pName = rs.getString("page_name");
			}
			System.out.println("페이지명:"+pName);
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
		return pName;
	}
	public int setPageName(UserDao u, String pageName){
//		UserDao user = new UserDao();
		PreparedStatement pstmt = null;
		JDBCTemplate jt = new JDBCTemplate();
		int r = 0;
		
		try {
			Connection conn = jt.getConnect();
			
			String sql1 = "UPDATE USER_PAGENAME SET PAGE_NAME=?  WHERE USER_ID = ? ";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, pageName);
			pstmt.setString(2, u.getUser_id());
			r = pstmt.executeUpdate();
			if(r==0){
				String sql2 = "INSERT INTO USER_PAGENAME (USER_ID, PAGE_NAME) VALUES(?,?)";
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, u.getUser_id());
				pstmt.setString(2, pageName);
				
				r = pstmt.executeUpdate();
			}
			
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
		return r;
	}
	public UserProfileDao getProfile(UserDao u){
//		UserDao user = new UserDao();
		UserProfileDao up = new UserProfileDao();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JDBCTemplate jt = new JDBCTemplate();

		try {
			Connection conn = jt.getConnect();
			
			// 오름/내림 정렬 후 순서를 뽑는것과 조건검색을 같이 할 때는 from절에 서브쿼리를 써야함.
			String sql = "SELECT PIC_NAME, PIC_REALNAME, PROFILE_TXT FROM (SELECT * FROM USER_PROFILE WHERE USER_ID = ? ORDER BY PROFILE_DATE DESC) WHERE ROWNUM = 1";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u.getUser_id());
			pstmt.executeUpdate();
			rs = pstmt.executeQuery(sql);
			
			
			while(rs.next()){
				up.setPic_Name(rs.getString("pic_name"));
				up.setPic_RealName(rs.getString("pic_realname"));
				up.setProfile_txt(rs.getString("profile_txt"));
			}
		
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
		return up;
	}
	public ArrayList<UserProfileDao> getProfileList(UserDao u){
//		UserDao user = new UserDao();
		
		ArrayList<UserProfileDao> pList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JDBCTemplate jt = new JDBCTemplate();

		try {
			Connection conn = jt.getConnect();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
			
		
			// 오름/내림 정렬 후 순서를 뽑는것과 조건검색을 같이 할 때는 from절에 서브쿼리를 써야함.
			String sql = "SELECT PIC_NAME, PIC_REALNAME, PROFILE_TXT, PROFILE_DATE FROM USER_PROFILE WHERE USER_ID = ? ORDER BY PROFILE_DATE DESC ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u.getUser_id());
			pstmt.executeUpdate();
			rs = pstmt.executeQuery(sql);
			
			while(rs.next()){
				UserProfileDao up = new UserProfileDao();
				up.setPic_Name(rs.getString("PIC_NAME"));
				up.setPic_RealName(rs.getString("PIC_REALNAME"));
				up.setProfile_txt(rs.getString("PROFILE_TXT"));
				up.setProfile_date(transFormat.format(rs.getDate("PROFILE_DATE")));
				pList.add(up);
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
		return pList;
	}
	public ArrayList<FriendsDao> getFriendsList(UserDao u){
//		UserDao user = new UserDao();
		
		ArrayList<FriendsDao> fList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JDBCTemplate jt = new JDBCTemplate();

		try {
			Connection conn = jt.getConnect();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
			
		
			
			String sql1 = "SELECT USER_ID_1, USER_NAME_1, USER_NICK_1 FROM FRIENDS_INFO WHERE USER_ID_2 = ?";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, u.getUser_id());
			pstmt.executeUpdate();
			rs = pstmt.executeQuery(sql1);
			
			while(rs.next()){
				FriendsDao fd = new FriendsDao();
				fd.setFriendId(rs.getString("USER_ID_1"));
				fd.setFriendName(rs.getString("USER_NAME_1"));
				fd.setFriendNick(rs.getString("USER_NICK_1"));
				fList.add(fd);
			}
			String sql2 = "SELECT USER_ID_2, USER_NAME_2, USER_NICK_2 FROM FRIENDS_INFO WHERE USER_ID_1 = ?";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, u.getUser_id());
			pstmt.executeUpdate();
			rs = pstmt.executeQuery(sql2);
			
			while(rs.next()){
				FriendsDao fd = new FriendsDao();
				fd.setFriendId(rs.getString("USER_ID_2"));
				fd.setFriendName(rs.getString("USER_NAME_2"));
				fd.setFriendNick(rs.getString("USER_NICK_2"));
				fList.add(fd);
			}
			
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
		return fList;
	}
	public ArrayList<FriendsDao> getFriendsMsgList(UserDao u){
//		UserDao user = new UserDao();
		
		ArrayList<FriendsDao> fmList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JDBCTemplate jt = new JDBCTemplate();

		try {
			Connection conn = jt.getConnect();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			String sql1 = "SELECT USER_ID_1, USER_NAME_1, USER_NICK_1, MSG_FROM_FRIEND_2, MSG_2_DATE FROM FRIENDS_INFO "
					+ "WHERE USER_ID_2 = ? AND MSG_FROM_FRIEND_2 IS NOT NULL";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, u.getUser_id());
			pstmt.executeUpdate();
			rs = pstmt.executeQuery(sql1);
			
			while(rs.next()){
				FriendsDao fd = new FriendsDao();
				fd.setFriendId(rs.getString("USER_ID_1"));
				fd.setFriendName(rs.getString("USER_NAME_1"));
				fd.setFriendNick(rs.getString("USER_NICK_1"));
				fd.setFriendMsg(rs.getString("MSG_FROM_FRIEND_2"));
				fd.setFriendMsgDate(transFormat.format(rs.getDate("MSG_2_DATE")));
				fmList.add(fd);
			}
			String sql2 = "SELECT USER_ID_2, USER_NAME_2, USER_NICK_2, MSG_FROM_FRIEND_1, MSG_1_DATE FROM FRIENDS_INFO "
					+ "WHERE USER_ID_1 = ? AND MSG_FROM_FRIEND_1 IS NOT NULL";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, u.getUser_id());
			pstmt.executeUpdate();
			rs = pstmt.executeQuery(sql2);
			
			while(rs.next()){
				FriendsDao fd = new FriendsDao();
				fd.setFriendId(rs.getString("USER_ID_2"));
				fd.setFriendName(rs.getString("USER_NAME_2"));
				fd.setFriendNick(rs.getString("USER_NICK_2"));
				fd.setFriendMsg(rs.getString("MSG_FROM_FRIEND_1"));
				fd.setFriendMsgDate(transFormat.format(rs.getDate("MSG_1_DATE")));
				fmList.add(fd);
			}
			DescListService ds = new DescListService();
			Collections.sort(fmList, ds);

			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
		return fmList;
	}
	public int setFriendsMsg(FriendsDao fd, String pageId){
//		UserDao user = new UserDao();
		
		ArrayList<FriendsDao> fmList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JDBCTemplate jt = new JDBCTemplate();
		int r = 0;
		try {
			Connection conn = jt.getConnect();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			String sql1 = "UPDATE FRIENDS_INFO SET MSG_FROM_FRIEND_2 = ?, MSG_2_DATE = SYSDATE WHERE USER_ID_1 = ? AND USER_ID_2 = ?";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, fd.getFriendMsg());
			pstmt.setString(2, fd.getFriendId());
			pstmt.setString(3, pageId);
			r += pstmt.executeUpdate();
			
			
			String sql2 = "UPDATE FRIENDS_INFO SET MSG_FROM_FRIEND_1 = ?, MSG_1_DATE = SYSDATE WHERE USER_ID_2 = ? AND USER_ID_1 = ?";
			//id2가 글쓴이, 
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, fd.getFriendMsg());
			pstmt.setString(2, fd.getFriendId());
			pstmt.setString(3, pageId);
			r += pstmt.executeUpdate();
			
			
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
		return r;
	}
	public int setAddFriendData(FriendsDao fd, String sessId, String sessName){
//		UserDao user = new UserDao();
		
		
		PreparedStatement pstmt = null;
		
		JDBCTemplate jt = new JDBCTemplate();
		int r = 0;
		try {
			Connection conn = jt.getConnect();
			
			String sql = "INSERT INTO FRIENDS_ADD (USER_ID_1, USER_NAME_1, USER_NICK_1, USER_ID_2, USER_NAME_2) VALUES(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fd.getFriendId());
			pstmt.setString(2, fd.getFriendName());
			pstmt.setString(3, fd.getFriendNick());
			pstmt.setString(4, sessId);
			pstmt.setString(5, sessName);
			r += pstmt.executeUpdate();
			

			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
		return r;
	}
	public ArrayList<FriendsDao> getAcceptMyList(String sessId){
//		UserDao user = new UserDao();
		
		ArrayList<FriendsDao> myInfoList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JDBCTemplate jt = new JDBCTemplate();

		try {
			Connection conn = jt.getConnect();
			
			String sql = "SELECT USER_NAME_1, USER_NICK_1 FROM FRIENDS_ADD WHERE USER_ID_1 = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sessId);
			pstmt.executeUpdate();
			rs = pstmt.executeQuery(sql);
			
			while(rs.next()){
				FriendsDao fd1 = new FriendsDao();
				
				fd1.setFriendName(rs.getString("USER_NAME_1"));
				fd1.setFriendNick(rs.getString("USER_NICK_1"));
				
				myInfoList.add(fd1);
			
			}
		
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
		return myInfoList;
	}
	public ArrayList<FriendsDao> getAcceptFriendList(String sessId){
//		UserDao user = new UserDao();
	
		ArrayList<FriendsDao> friendInfoList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JDBCTemplate jt = new JDBCTemplate();

		try {
			Connection conn = jt.getConnect();
			
			String sql = "SELECT USER_ID_2, USER_NAME_2 FROM FRIENDS_ADD WHERE USER_ID_1 = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sessId);
			pstmt.executeUpdate();
			rs = pstmt.executeQuery(sql);
			
			while(rs.next()){
				FriendsDao fd2 = new FriendsDao();
			
				fd2.setFriendId(rs.getString("USER_ID_2"));
				fd2.setFriendName(rs.getString("USER_NAME_2"));
			
				friendInfoList.add(fd2);
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
		return friendInfoList;
	}
	public int setAcceptFriend(String sessId, String friendId){
//		UserDao user = new UserDao();
	
		PreparedStatement pstmt = null;
		JDBCTemplate jt = new JDBCTemplate();
		int r = 0;
		try {
			Connection conn = jt.getConnect();
			
			String sql1 = "INSERT INTO FRIENDS_INFO SELECT * FROM FRIENDS_ADD WHERE USER_ID_1 = ? AND USER_ID_2 = ?";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, sessId);
			pstmt.setString(2, friendId);
			r += pstmt.executeUpdate();
			System.out.println(r);
			
			String sql2 = "DELETE FROM FRIENDS_ADD WHERE USER_ID_1 = ? AND USER_ID_2 = ?";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, sessId);
			pstmt.setString(2, friendId);
			r += pstmt.executeUpdate();
			System.out.println(r);

			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
		return r;
	}
	public int setRefuseFriend(String sessId, String friendId){
		PreparedStatement pstmt = null;
		JDBCTemplate jt = new JDBCTemplate();
		int r = 0;
		try {
			Connection conn = jt.getConnect();
			
			String sql = "DELETE FROM FRIENDS_ADD WHERE USER_ID_1 = ? AND USER_ID_2 = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sessId);
			pstmt.setString(2, friendId);
			r += pstmt.executeUpdate();
			System.out.println(r);

			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
		return r;
	}
}
