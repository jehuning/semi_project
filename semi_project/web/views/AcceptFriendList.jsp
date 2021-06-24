<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.vo.controller.* , model.vo.service.* , model.vo.dao.* " %>
    <%@ page import = "java.util.ArrayList" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>일촌 신청 목록</title>
<% 
ArrayList<FriendsDao> fdL = (ArrayList<FriendsDao>)request.getAttribute("relationMyInfo");
int l = fdL.size();
%>
<script  type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>   	
 <script>
 function acceptView(){
		var url = "/semi_project/views/AcceptFriend.jsp";
		window.open(url, '_blank', 'width=400px,height=200px,toolbars=no,scrollbars=no'); 
		
	}
 </script>	
</head>
<body>
<%if(l != 0){ %>
	<table style="border-collapse:collapse;">
	<c:forEach var="relationMyInfo" items="${relationMyInfo}" varStatus="status">
	<tr >
	<td style="border-bottom:1px solid gray;">일촌 신청 받은 목록</td>
	</tr>
	<tr ><!-- 첫번째 줄 시작 -->
	<td style="border-bottom:1px solid gray;">
	<a href =/semi_project/views/AcceptFriend.jsp?friendId=${relationFriendInfo[status.index].friendId} >
	${relationFriendInfo[status.index].friendName}(${relationFriendInfo[status.index].friendId})님이 당신을 ${relationMyInfo.friendNick}(으)로 일촌 신청하였습니다.</a></td>
	</tr><!-- 첫번째 줄 끝 -->
	</c:forEach>
	</table>
	<%}else{%><h3>받은 일촌 신청이 없습니다.</h3><%} %>
</body>
</html>