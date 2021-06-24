package model.vo.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.vo.dao.BoardDao;
import model.vo.dao.UserDao;
import model.vo.service.SignUpService;


public class SignUpCont extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException
	{
			
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		RequestDispatcher dispatcher = null;
		String rtnPage = "";
		
		
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String birth = req.getParameter("bDate");
		String gender = req.getParameter("gender");
		
		System.out.println(id);
		
		UserDao u = new UserDao();
		u.setUser_id(id);
		u.setUser_password(password);
		u.setUser_name(name);
		u.setUser_birth(birth);
		u.setUser_gender(gender);
		
		
		SignUpService ss = new SignUpService();
		int r = ss.SignUpInput(u);
		if(r == 0){
			rtnPage = "<h1>가입을 실패했습니다.</h1><br /><a href = \"/semi_project/views/SignUp.jsp\"><h2>가입화면으로 돌아가기</h2></a><br />";
		}else{
			rtnPage = "<h1>가입을 환영합니다.</h1><br /><a href = \"/semi_project/views/login.jsp\"><h2>바로 로그인 하기</h2></a><br />";
		}
		
		
//		if(ss.SignUpInput(u) == 0) {
//			request.setAttribute("errorMsg", "게시글 수정 실패");
//			dispatcher = request.getRequestDispatcher("views/common/errorPage.jsp");
//		} else {
//			request.setAttribute("Board", bs.updateBoard(b));
//			dispatcher = request.getRequestDispatcher("views/common/boardUpdateForm.jsp");
//			request.setAttribute("board",bs.roadBoard());
//		}
		
	
		PrintWriter out = resp.getWriter();
		out.println(rtnPage);
		out.close();
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		doGet(req,resp);
		
	}
	
}
