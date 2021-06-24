package model.vo.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.vo.dao.UserDao;
import model.vo.service.IdChkService;

@WebServlet("/idChk")
public class IdChkCont extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		String id		= req.getParameter("id");
		String idChk 	= "몰라";
		String rtnPage 	= "";

		UserDao u = new UserDao();
		u.setUser_id(id);
		
		IdChkService is = new IdChkService();
		
		
		
		if(is.idDBChk(u) == 0) {
			idChk = "{\"msg\":\"사용 가능한 아이디입니다.\", \"flag\":\"1\"}";
			
		}else{
			idChk = "{\"msg\":\"중복된 아이디입니다.\", \"flag\":\"0\"}";
		
		}
		System.out.println(idChk);
		
	        resp.setContentType("text/json");
	        PrintWriter out =resp.getWriter();
	        
		rtnPage = idChk;
		
		out.println(rtnPage);
		out.close();
		
	}
	@Override
	public void doPost(HttpServletRequest req,
			HttpServletResponse resp) throws IOException, ServletException {	
		doGet(req, resp);
	}
	
}
