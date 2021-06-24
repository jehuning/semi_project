package model.vo.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.vo.dao.BoardDao;
import model.vo.dao.UserDao;
import model.vo.service.SignInService;

@WebServlet("/signIn")
public class SignInCont extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException
	{
		// http://localhost:8090/java_jspservlet/hello1?name=%EA%B9%80%ED%98%95%ED%83%9C		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		RequestDispatcher dispatcher = null;
		String rtnPage = "";
		
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		
		UserDao u = new UserDao();
		u.setUser_id(id);
		u.setUser_password(password);
		
		SignInService ss = new SignInService();
		String result =	ss.signInCheck(u);
		if(result.equals("success")) {
			System.out.println("로그인 성공");
			
			// 2. 세션 생성 - getSession(boolean)
			// true:있으면 반환, 
			//없으면 생성반환/false: 있으면 반환)
			HttpSession session = req.getSession(true);
			
			// 3. 세션 유지 시간 설정(60(초)*60(분)*24(시간))
			session.setMaxInactiveInterval(60*60*24);

			// 4. 세션정보 추가
			if(session != null) {
				session.setAttribute("id", id);
			}
			
			// 5-1. 세션 정보 얻어오기
//			rtnPage += "세션아이디: <h1>" + 
//					session.getId() + "</h1><br />";	
//			rtnPage += "아이디: <h1>" + 
//					session.getAttribute("id") + "</h1><br />";
			
//			// 5-2. 세션 삭제
//			session.removeAttribute("id");
//			rtnPage += "세션아이디: <h1>" + 
//					session.getId() + "</h1><br />";
//			rtnPage += "이름: <h1>" + 
//					session.getAttribute("name") + "</h1><br />";
//			rtnPage += "나이: <h1>" + 
//					session.getAttribute("age") + "</h1><br />";		
			
			// 5-2. 모든 세션 삭제
//			session.invalidate();
//			rtnPage += "세션아이디: <h1>" + 
//			session.getId() + "</h1><br />";
//			rtnPage += "이름: <h1>" + 
//			session.getAttribute("name") + "</h1><br />";
//			rtnPage += "나이: <h1>" + 
//			session.getAttribute("age") + "</h1><br />";
			
		
			rtnPage = "http://localhost:8090/semi_project/views/minihompy.jsp";
			resp.sendRedirect(rtnPage);
			
			
		} else if(result.equals("wrongId")){
			System.out.println("로그인 아이디 실패");
			req.setAttribute("idMsg", "존재하지 않는 아이디입니다.");
			dispatcher = req.getRequestDispatcher("/views/login.jsp");
			dispatcher.forward(req, resp);
		}else if(result.equals("wrongPass")){
			System.out.println("로그인 비번 실패");
			req.setAttribute("passMsg", "비밀번호가 틀렸습니다.");
			dispatcher = req.getRequestDispatcher("/views/login.jsp");
			dispatcher.forward(req, resp);
			
		}
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		doGet(req,resp);
		
	}
}
