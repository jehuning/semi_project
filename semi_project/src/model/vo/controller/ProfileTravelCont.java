package model.vo.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import model.vo.dao.UserDao;

import model.vo.service.MainService;


@WebServlet("/ProfileTravelCont")
public class ProfileTravelCont extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		

		String rtnPage = "";
		String id = (String)req.getParameter("id");
		int currentI= Integer.parseInt((String)req.getParameter("currentI"));
		String direction = (String)req.getParameter("direction");
		
		String profilePic = "";
    	String profileText = "";
	
		System.out.println(direction);
		
		UserDao u = new UserDao();
		u.setUser_id(id);
		
		MainService ms = new MainService();
		if(direction.equals("▲")){
			if(currentI >= 1){
				profilePic = ms.getProfileList(u).get(currentI-1).getPic_RealName();
		    	profileText = ms.getProfileList(u).get(currentI-1).getProfile_txt();
		    	currentI--;
			}else{
				profilePic = ms.getProfileList(u).get(currentI).getPic_RealName();
		    	profileText = ms.getProfileList(u).get(currentI).getProfile_txt();
			}
		}else{
			if(currentI < ms.getProfileList(u).size()){
				profilePic = ms.getProfileList(u).get(currentI+1).getPic_RealName();
		    	profileText = ms.getProfileList(u).get(currentI+1).getProfile_txt();
		    	currentI++;
			}else{
				profilePic = ms.getProfileList(u).get(currentI).getPic_RealName();
		    	profileText = ms.getProfileList(u).get(currentI).getProfile_txt();
			}
			
		}
		if(profileText == null){
			profileText="자기소개를 입력해주세요";
		}
		
		
		rtnPage = "{\"profilePic\":\"/semi_project/upload/"+ profilePic + "\", \"profileText\":\""+profileText+"\""
				+ ", \"currentI\":\""+currentI+"\"}";
			
		System.out.println(rtnPage);
		
	        resp.setContentType("text/json");
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
