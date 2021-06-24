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

@WebServlet("/addFriendCont")
public class AddFriendCont  extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		RequestDispatcher dispatcher = null;

		String rtnPage = "";
		String targetFriendId = (String)req.getParameter("targetId");
		String targetFriendNick = (String)req.getParameter("friendNick");
		String targetFriendName = (String)req.getParameter("targetName");
		HttpSession sess = req.getSession();
		String sessId =  (String)sess.getAttribute("id");
		
		UserDao u = new UserDao();
		u.setUser_id(sessId);
		
		MainService ms = new MainService();
		String sessName = ms.getUserData(u).getUser_name();
		
		System.out.println("세션유저: "+sessId);
		System.out.println("페이지유저: "+targetFriendId);
		System.out.println("별명:"+targetFriendNick);
		FriendsDao fd = new FriendsDao();
		fd.setFriendId(targetFriendId);
		fd.setFriendNick(targetFriendNick);
		fd.setFriendName(targetFriendName);
		
		
		int r = ms.setAddFriendData(fd, sessId, sessName);
		System.out.println("신청여부: "+r);
		
		if(r==1){
			rtnPage = "일촌 신청 완료";
		}else{
			rtnPage = "일촌 신청 실패";
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
