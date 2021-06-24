<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.vo.controller.* , model.vo.service.* , model.vo.dao.* " %>
    <%@ page import = "java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>일촌 신청</title>
<% 		String sessId = (String)session.getAttribute("id"); 
    	String pageId = (String)request.getParameter("friendId");
    	UserDao u = new UserDao();
    	u.setUser_id(pageId);
    	MainService ms = new MainService();
    	String name = ms.getUserData(u).getUser_name();
    	
   	%>
<script  type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>   	
<script>
function addFriend(){
	var queryString = $("#addFriendForm").serialize();

	 $.ajax({
		url:"/semi_project/addFriendCont",
      	type:"GET",
      	data : queryString,
       	dataType:"text",
        async	: false,
        success : function(data) {
        	window.close();
        	alert(data);
        },
        error : function(request,error) {
     	   alert('실패3');
     	  alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
	 });
}

</script>
</head>
<body>
	<div id="bg1">
	<h4>일촌 신청</h4>
	<div id = "bg2">
		<div id = "content" >
		
		<form id="addFriendForm">
			<%=name %>님의 일촌명(별명)을 <input type="text" id="friendNick" name="friendNick" value="일촌" size=8 maxlength=6 required/>으로 <br />
			일촌 신청합니다.<br/>
			<div style="text-align:center; margin: 10px;">상대방이 동의하시면 일촌이 맺어집니다.</div>
			<input type="hidden" id = "targetName" name="targetName" value="<%=name %>" />
			<input type="hidden" id = "targetId" name="targetId" value="<%=pageId %>" />
			<input type="button" value="보내기" onclick="addFriend()"><input type="button" value="취소" onclick="window.close();"/>
		</form>
		
		</div>
	</div>
</div>
</body>
</html>