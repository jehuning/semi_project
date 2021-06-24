package model.vo.service;

import model.vo.dao.UserDao;

public class test {

	public static void main(String[] args) {
		IdChkService is = new IdChkService();
		SignUpService ss = new SignUpService();
		UserDao u = new UserDao();
		u.setUser_id("ljh1");
		u.setUser_password("1");
		u.setUser_name("2");
		u.setUser_birth("1989-02-28");
		u.setUser_gender("남");
		ss.SignUpInput(u);
	}

}
