<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.vo.controller.* , model.vo.service.* , model.vo.dao.*  " %>
    <%@ page import = "java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<script  type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>프로필</title>
	<% String s = "";
    	s = (String)session.getAttribute("id"); 
    	UserDao u = new UserDao();
    	u.setUser_id(s);
    	MainService ms = new MainService();
    	String profilePic = ms.getProfile(u).getPic_RealName();
    	String profileTxt = ms.getProfile(u).getProfile_txt();
   	%>
   	<script type="text/javascript">
   	$(document).ready(function() {
		$("#edit").click(function(event){
			var formData = new FormData($("#profileform")[0]);
			
			 $.ajax({
				url:"/semi_project/ProfileEditCont",
				type:"POST",
				enctype: 'multipart/form-data',
				processData: false, 
				contentType: false, 
		       	//dataType:"JSON",
		        data : formData,
		        success : function(data) {
		     	  // $("#profileText").text(data.msg);
		            // if(data.flag == 1) {
		              //   idChk = true;
		             //} else {
		             	// idChk = false;
		            // }
					opener.document.location.reload();
					self.close();
	
		        },
		        error : function() {
		     	   alert('실패');
		        }
			 });
		});
	});
   	function setThumbnail(event) { 
   		var reader = new FileReader(); 
   		reader.onload = function(event) { 
   			
   			document.getElementById("target_img").setAttribute("src", event.target.result); 
		 }; 
   			reader.readAsDataURL(event.target.files[0]); 
	} 

   	</script>
</head>
<body>
	<div id="profileBack" style="">
   		<form  id="profileform" name="profileform" >
   		
   		<div style="text-align:center;"><img id = "target_img" style="max-width:95%; max-height:500px;" src="/semi_project/upload/<%if(profilePic==null){%>프로필.png<%}else{%><%=profilePic%><% }%>" onclick="document.all.file.click();">
   		
	    <input type="file" id="file" style = "display:none;" name="file"  accept="image/*" onchange="setThumbnail(event);" />
	    
		
   		</div>
   		
   		<div style="text-align:right; font-size:10px; margin-bottom:5px; ">▲ 눌러서 수정하기</div>
   		<div>
   		
   		<textarea name="profileText" id = "profileText" style="height:120px;width:367px; resize:none;" onclick="this.select()"><%if(profileTxt==null){%>자기소개가 없습니다.<%}else{%><%=profileTxt%><%}%></textarea>
   		<input type="button" id= "edit" value="저장 후 닫기" />
   		
   		</div>
   		</form>
	</div>
 
 
	


</body>
</html>