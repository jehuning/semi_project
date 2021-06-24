package model.vo.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.vo.service.MainService;
@WebServlet("/acceptFriendListCont")
public class AcceptFriendListCont extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		RequestDispatcher dispatcher = null;

		String sessId =  (String)req.getParameter("sessionId");
	
		System.out.println("세션유저: "+sessId);
		
		MainService ms = new MainService();
	
		req.setAttribute("relationMyInfo", ms.getAcceptMyList(sessId));
		req.setAttribute("relationFriendInfo", ms.getAcceptFriendList(sessId));
		dispatcher = req.getRequestDispatcher("/views/AcceptFriendList.jsp");
		dispatcher.forward(req, resp);
		
	}
	@Override
	public void doPost(HttpServletRequest req,
			HttpServletResponse resp) throws IOException, ServletException {	
		doGet(req, resp);
	}
}
