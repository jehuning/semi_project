<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("utf-8"); %>
<% response.setContentType("text/html; charset=utf-8"); %>  

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
<script  type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	passChk = false;
	idChk = false;
	$(document).ready(function() {
		$("#idChk").click(function(event){
			var id = $("#id").val();
			 $.ajax({
				url:"/semi_project/idChk",
		      	type:"GET",
		       	dataType:"JSON",
		       
		        data : {"id":id},
		        success : function(data) {
		     	   $("#result").text(data.msg);
		             if(data.flag == 1) {
		                 idChk = true;
		             } else {
		             	 idChk = false;
		             }
		
		        },
		        error : function() {
		     	   alert('실패');
		        }
			 });
		});
	});
	
	function inputCheck(inputPass){
		p1 = document.getElementById('password').value;
		p2 = inputPass.value;
		
		if(p1 == p2){
			document.getElementById('passComment').innerText ="비밀번호가 일치합니다."
				document.getElementById('passComment').style.color ="green"
					passChk = true;
		}else{
			document.getElementById('passComment').innerText = "잘못 입력하셨습니다."
				document.getElementById('passComment').style.color ="red"
					passChk = false;
		}
	}
	
	function finalCheck(){
		alert("확인");
		
		
		if(passChk && idChk){
			
			alert("회원가입을 성공했습니다.");
			return true;
		}else if(passChk){
			alert("아이디 중복체크를 하세요");
			return false;
		}else if(idChk){
			alert("비밀번호를 확인 하세요");
			return false;
		}else{
			alert("아이디와 비밀번호를 확인하세요");
			return false;
		}
		return false;
	}
</script>

<style>
	#bg1{
		position: absolute;
		width : 100%;
		height : 100%;
		background-color : #f5f5f5;
	}
	label{
		width : 100px;
	}
	h5 {
		margin : 0px;
	}
</style>

</head>
<body>

<div id="bg1">
	<h4>42월드 회원가입</h4>
	<div id = "bg2">
		<div id = "content" >
		<fieldset>
		<form action="http://localhost:8090/semi_project/signUpCont" method="post" onsubmit = "return finalCheck()">
			<label>아이디 : <input type="text" name="id" id="id" required/></label><input type="button" id="idChk"value="중복확인"><h5 id="result"></h5><br/>
			<label>비밀번호 : <input type="password" id="password" name="password" required/></label><div></div><br/>
			<label>비밀번호 확인 : <input type="password" name="pChecking" onblur="inputCheck(this)" required/></label><div id="passComment"></div><br/>
			<label>이름 :<input type="text" name="name" required/></label> <div></div><br/>
			<label>생년월일 : <input type="date" name="bDate" /></label><div></div><br/>
			<label>성별 :</label> <label><input type="radio" name="gender" value="남자" required/>남성  </label> 
			<label><input type="radio" name="gender" value="여자" required/>여성  </label> <div></div><br/>
			<input type="submit" value="회원가입">
		</form>
		</fieldset>
		</div>
	</div>
</div>
</body>
</html>