<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
<script type="text/javascript">
	function signPopUp(){
		window.open("SignUp.jsp", '_blank', 'width=400px,height=700px,toolbars=no,scrollbars=no'); 
	
	}

</script>
<style>
	#bg1 {
		position: absolute;
		width : 330px;
		height : 225px;
		background-color : #eeeeee;
		
		
	}
	#bg2 {
		position: absolute;
		width : 310px;
		height: 180px;
		background-color : white;
		margin-top: 5px;
		margin-left : 10px;
		border : solid 1px #dddddd;
	}
	#content {
		margin : 30px;
	}
	h4{
		margin-top : 10px;
		margin-left : 15px;
		margin-bottom : 0px;
	}
	input[type="text"], input[type="password"] 
	{ width: 170px; 
	height: auto; 
	}
	input[type="text"]:focus, input[type="password"]:focus 
	{ 
	 outline: solid 1px orange;  
	}
	input[type="submit"]{
		width : 50px;
		height: 50px;
		padding-left : 4px;
		background-color : orange;
		border-radius: 7px;
		color : white;
	}
	#idMsg, #passMsg {
		font-size : 12px;
		color : red;
	}

</style>
</head>
<body>
<div id="bg1">
	<h4>42월드 로그인</h4>
	<div id = "bg2">
		<div id = "content" >
		<form action="<%=request.getContextPath()%>/signIn" method ="post">
		<table>
		<tr>
			<td><input type="text" name="id" class="textbox" placeholder = "아이디"/><div id="idMsg">${idMsg}</div></td>
			
			<td rowspan="2"><input type="submit" value="로그인"></td>
		</tr>
		<tr>
			<td>
			<input type="password" name="password" class="input" placeholder = "비밀번호"  /><div id="passMsg">${passMsg}</div>
			</td>
		</tr>
		</table>
		</form>
			<p><a href="javascript:signPopUp()"><h5>회원 가입</h5></a></p>
		</div>
	</div>
</div>
</body>
</html>