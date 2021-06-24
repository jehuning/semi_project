package model.vo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
	Connection conn = null;
	PreparedStatement pstmt = null;
	Statement stmt = null;
	
	
	public JDBCTemplate(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public Connection getConnect() {
		String jdbcUrl	= "jdbc:oracle:thin:@localhost:1521:XE";
		String userId 	= "jehuning";
		String userPass = "tkfkadk";
		
		try {
			conn = DriverManager.getConnection(jdbcUrl, userId, userPass);
			System.out.println("DB 연결 성공");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public void close() {
		try {
			pstmt.close();
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void commit() {}
	public void rollback () {}
}
