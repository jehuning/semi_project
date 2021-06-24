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

import model.vo.dao.FriendsDao;
import model.vo.dao.UserDao;
import model.vo.service.FriendOrNotService;
import model.vo.service.MainService;

@WebServlet("/friendMsgCont")
public class FriendMsgCont  extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		String rtnPage = "";
		String friendMsg= (String)req.getParameter("friendMsg");
		String pageId = (String)req.getParameter("pageId");
		HttpSession sess = req.getSession();
		String sessId =  (String)sess.getAttribute("id");
				
		FriendsDao fd = new FriendsDao();
		fd.setFriendId(sessId);
		fd.setFriendMsg(friendMsg);
		
		MainService ms = new MainService();
		int r = ms.setFriendsMsg(fd, pageId);
		System.out.println(r);
		
		if(r == 1){
			rtnPage = "일촌평이 등록되었습니다.";
		}else{
			rtnPage = "일촌평이 등록을 실패했습니다.";
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
