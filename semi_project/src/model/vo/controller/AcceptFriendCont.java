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

import model.vo.service.MainService;
@WebServlet("/acceptFriendCont")
public class AcceptFriendCont extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String rtnPage = "";
		
		String friendId = req.getParameter("friendId");
		HttpSession sess = req.getSession();
		String sessId =  (String)sess.getAttribute("id");
	
		System.out.println("세션유저: "+sessId);
		
		MainService ms = new MainService();
		int r = ms.setAcceptFriend(sessId, friendId);
		
		if(r==0){
			rtnPage = "수락 실패";
		}else{
			rtnPage = " 수락 성공";
		}
		PrintWriter out =resp.getWriter();
		out.println(rtnPage);
		out.close();
	}
	@Override
	public void doPost(HttpServletRequest req,
			HttpServletResponse resp) throws IOException, ServletException {	
		doGet(req, resp);
	}
}
