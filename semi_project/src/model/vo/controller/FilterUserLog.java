package model.vo.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.vo.dao.UserDao;

public class FilterUserLog implements Filter {

	@Override
	public void init(FilterConfig config) {
	}
	
	@Override
	public void doFilter(ServletRequest req, 
			ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest)req;
		HttpServletResponse httpResp = (HttpServletResponse)resp;
		
		HttpSession sess = httpReq.getSession();
		System.out.println("필터호출");
		if(sess == null){
			httpResp.sendRedirect("/views/login.jsp");
		}
		
		if(sess.getAttribute("id")== null){
			httpResp.sendRedirect("/views/login.jsp");
		}
		chain.doFilter(req, resp);
	}
	
	@Override
	public void destroy() { }
}
