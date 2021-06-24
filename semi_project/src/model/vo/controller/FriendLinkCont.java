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

import model.vo.dao.UserDao;
import model.vo.service.FriendOrNotService;
import model.vo.service.MainService;

@WebServlet("/friendLinkCont")
public class FriendLinkCont  extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		RequestDispatcher dispatcher = null;

		String rtnPage = "";
		String friendId = (String)req.getParameter("friendId");
		HttpSession sess = req.getSession();
		String sessId =  (String)sess.getAttribute("id");
		
		System.out.println("세션유저: "+sessId);
		System.out.println("페이지유저: "+friendId);
		
		FriendOrNotService fs = new FriendOrNotService();
		boolean flag = fs.getFriendOrNot(sessId, friendId);
		
		if(sessId.equals(friendId)){
			req.setAttribute("id", sessId);
			dispatcher = req.getRequestDispatcher("views/minihompy.jsp");
		}else{
			if(flag) {
				req.setAttribute("id", friendId);
				dispatcher = req.getRequestDispatcher("views/minihompy_friend.jsp");
			}else {
				System.out.println("친구아님");
				req.setAttribute("id", friendId);
				dispatcher = req.getRequestDispatcher("views/minihompy_visitor.jsp");
			}
		}
	
		dispatcher.forward(req, resp);
	}
	@Override
	public void doPost(HttpServletRequest req,
			HttpServletResponse resp) throws IOException, ServletException {	
		doGet(req, resp);
	}
}
