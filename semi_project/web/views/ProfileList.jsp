<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.vo.controller.* , model.vo.service.* , model.vo.dao.*  " %>
   
    <%@ page import = "java.util.*" %>
    <%@ page import = "com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
    <%@ page import = "com.oreilly.servlet.MultipartRequest" %>
    <%@ page import = "java.io.File" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>프로필 목록</title>
	<% String s = "";
    	s = (String)request.getParameter("id"); 
    	UserDao u = new UserDao();
    	u.setUser_id(s);
    	MainService ms = new MainService();
    	String name = ms.getUserData(u).getUser_name();
    	String profilePic = ms.getProfile(u).getPic_RealName();
    	String profileTxt = ms.getProfile(u).getProfile_txt();
    	ArrayList<UserProfileDao> upL = new ArrayList<>();
    	upL = ms.getProfileList(u);
    	int l = upL.size();
    	
    	int pNumBase = Integer.parseInt(request.getParameter("page"));
    	int pNum = 0;
   	%>
</head>
<body>
	<div id="profile_back">
		<%for(int i = 0; i < l; i++){
			if(i/2 +1 == pNumBase){%>
		<div id = "pDate"><%=upL.get(i).getProfile_date()%></div>
		<div id = "pic_box">
		    <img src="/semi_project/src/<%=upL.get(i).getPic_RealName() %>" style="max-width:95%; max-height:500px; vertical-align:middle; horizental-align:center;" ></img>
	    </div>
	    <div id = "text_box" style="text-align:center;"><%if(upL.get(i).getProfile_txt()==null){%>자기소개가 없습니다.<%}else{%><%=upL.get(i).getProfile_txt()%><%}%>
	    </div>
	    <div style="border-bottom: solid 1px gray"></div>
	    <%}}%>
    </div>
    <div style="text-align:center;">
   		<%for(int i = 1; i <= l; i++){%><a href="ProfileList.jsp?page=<%=pNum+1%>&id=<%=s%>"  ><%if(i%2 == 1){pNum = i/2 +1;%><%=pNum%>  </a><%}}%>
    </div>
</body>
</html>