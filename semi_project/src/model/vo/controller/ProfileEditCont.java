package model.vo.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import java.io.File;
import com.oreilly.servlet.MultipartRequest;



import model.vo.dao.UserDao;
import model.vo.dao.UserProfileDao;
import model.vo.service.IdChkService;
import model.vo.service.MainService;
import model.vo.service.ProfileEditService;


@WebServlet("/ProfileEditCont")
public class ProfileEditCont extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		
		
		String rtnPage 	= "";
		HttpSession session = req.getSession(true);
		String id = (String)session.getAttribute("id");
		
		ServletContext sc = getServletContext();
		String dir =  sc.getRealPath("/upload");
		System.out.println(id);
		
		int maxSize = 1024 * 1024 * 100;
		String encoding = "UTF-8";
		
		MultipartRequest mpr = new MultipartRequest(req, dir, maxSize, encoding, new DefaultFileRenamePolicy());
		String profileText	= mpr.getParameter("profileText");
		System.out.println("텍스트:"+profileText);
		String fileName = mpr.getOriginalFileName("file");
		String fileRealName = mpr.getFilesystemName("file");
	
		if(fileRealName == null){
			MainService ms = new MainService();
			UserDao u = new UserDao();
			u.setUser_id(id);
	    	String profilePic = ms.getProfile(u).getPic_RealName();
	    	fileRealName = profilePic;
	    	fileName = profilePic;
		}
		
		UserProfileDao up = new UserProfileDao();
		up.setPic_Name(fileName);
		up.setPic_RealName(fileRealName);
		up.setProfile_txt(profileText);
		
		ProfileEditService  pes = new ProfileEditService();
		pes.setProfile(id, up);
		
		
		
//	        resp.setContentType("text/json");
//	        PrintWriter out =resp.getWriter();
//	        
//		
//		
//		out.println(rtnPage);
//		out.close();
		
	}
	@Override
	public void doPost(HttpServletRequest req,
			HttpServletResponse resp) throws IOException, ServletException {	
		doGet(req, resp);
	}
	
}
