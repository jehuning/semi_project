<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.vo.controller.* , model.vo.service.* , model.vo.dao.* " %>
    <%@ page import = "java.util.ArrayList" %>
   
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<% 		String sessId = (String)session.getAttribute("id"); 
    	String pageId = (String)request.getParameter("friendId");
    	UserDao u = new UserDao();
    	u.setUser_id(pageId);
    	MainService ms = new MainService();
    	String name = ms.getUserData(u).getUser_name();
    	String profilePic = ms.getProfile(u).getPic_RealName();
    	String profileTxt = ms.getProfile(u).getProfile_txt();
    	String pageName =  ms.getPageName(u);
    	ArrayList<FriendsDao> fl = ms.getFriendsList(u);
    	ArrayList<FriendsDao> fml = ms.getFriendsMsgList(u);
    	int i = 0;
   	%>
<script  type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>      	
<script type="text/javascript">
	function profilePopUp(img){
		var p = window.open("ProfileEdit.jsp", 'popUp()', 'width=400px,height=700px,toolbars=no,scrollbars=no'); 
	}
	function profileList(){
		var pageId = $("#profileId").val();
		var link = "views/ProfileList.jsp?page=1&id="+pageId;
		window.open(link, 'popUp()', 'width=400px,height=700px,toolbars=no,scrollbars=no');
	}
	
	$(document).ready(function() {
		$("#previous").on('click', function(){
			var id = $("#profileId").val();
			var currentI = $("#profileIndex").val();
			var direction = $(this).text();
			 $.ajax({
				url:"/semi_project/ProfileTravelCont",
		      	type:"GET",
		       	dataType:"JSON",
		        data : {"id":id, "currentI":currentI, "direction": direction},
		        async	: false,
		        success : function(data) {
		     	    $("#profilePic").prop("src", data.profilePic); 
		     	 	$("#profileText").text(data.profileText);
		     	 	$("#profileIndex").val(data.currentI);
		        },
		        error : function(request,error) {
		     	   alert('실패1');
		     	  alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		        }
			 });
		});
	});
	$(document).ready(function() {
		$("#next").on('click', function(){
			var id = $("#profileId").val();
			var currentI = $("#profileIndex").val();
			var direction = $(this).text();
			 $.ajax({
				url:"/semi_project/ProfileTravelCont",
		      	type:"GET",
		       	dataType:"JSON",
		        data : {"id":id, "currentI":currentI, "direction": direction},
		        success : function(data) {
		     	    $("#profilePic").prop("src", data.profilePic); 
		     	 	$("#profileText").text(data.profileText);
		     	 	$("#profileIndex").val(data.currentI);
		        },
		        error : function(request,error) {
		     	   alert('실패2');
		     	  alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		        }
			 });
		});
	});
	
		 function friendMsgEdit(){
			var friendMsg = $("#friendMsg").val();
			var pageId = $("#profileId").val();
			
			 $.ajax({
				url:"/semi_project/friendMsgCont",
		      	type:"GET",
		       	dataType:"text",
		        data : {"friendMsg":friendMsg, "pageId":pageId},
		        success : function(data) {
		        	alert(data);
		        	location.reload();
		        },
		        error : function(request,error) {
		     	   alert('실패3');
		     	  alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		        }
			 });
		}
	
</script>

<style type ="text/css">
body {
background-color: gray;
}


div#main1{
width: 800px;
height:550px;
border-radius: 20px 20px;
background-color: #A7EEFF;
float:left;
margin:5px;
}
#main2_dashed{
position : block;
width: 762px;
height: 517px;
border-radius: 19px 19px;

margin : 17px auto;
border : 2px dashed white;
}
#main2{
position : block;
width: 755px;
height: 507px;
border-radius: 18px 18px;
background-color: #e2e2e2;
margin : 5px auto;
}

div#side_left{
width:200px;
height:480px;
float:left;
margin:10px 5px 10px 10px;
text-align : center;
font-size : 12px;
}
#left_back{
display:flex;
flex-direction : column;
justify-content : center;
width:95%;
height:445px;
clear : left;
background-color : white;
border-radius: 12px 12px;
margin:10px 5px;
}

#side_left_box1{


clear : left;

width:95%;
height:210px;
float:left;
margin:5px;
text-align : center;
}
#profileText{
margin:5px;
width:95%;
height:150px;
}

#side_left_box2{

width:95%;
height:50px;
margin:5px;
font-size : 10px;
text-align : left;
}
#a{
float : left;
left : 10px;
font-weight : bold;
}
#a a{
color : black;
text-decoration: none;
}
#a a:visted{
color : black;
}
#a a:hover{
color : red;
}
#b{
color : black;
position : relative;
float : right;
right : 5px;
text-align : right;
}
#b a{
color : black;
text-decoration: none;
}
#b a:visted{
color : black;
}
#b a:hover{
color : red;
}

#side_left_box3{


width:95%;
height:20%;
margin:5px;
font-size : 14px;
font-weight : bold;
text-align : left;
}
#c{
margin:4px;
}
#c a{
color : #0064CD;
text-decoration: none;
}
#c a:visted{
color : black;
}
#c a:hover{
color : red;
}

select{
width : 80%;
margin : 4px;
}

#mid_content{
display:inline-block;
width:525px;
height:480px;
float:left;
margin:5px;
}
#mid_back1{
positon : relative;
width : 525px;
height : 30px;
margin-top: 11px;
}
h1{
float : left;
margin-top: 0px;
margin-bottom : 4px;
font-size : 22px;
color : #0064CD;
}
#m_b{
position : relative;
float : left;
right : 15px;
top : 13px;
margin-left:20px;
text-align : right;
font-size : 10px;
font-weight : bold;


}
#m_b a{
color:gray;
}
#m_c{
position : relative;
float: right;
right : 5px;
top : 13px;
text-align : right;
font-size : 12px;
font-weight : bold;
}
#m_c a{
color:#FF9B00;
}
.mid_back2{
positon : relative;
width : 525px;
height : 445px;
background-color:white;
margin-top : 5px;
margin-left: 0px;
border-radius: 12px 12px;

}
.mid_back2{
	overflow-y:scroll;
  display:flex;
  flex-wrap:wrap;
  align-content:flex-start;
}
.mid_content_box1{
positon : relative;
width : 240px;
height : 110px;
margin-top : 10px;
margin-left: 8px;
margin-bottom : 0px;
padding-bottom: 0px;
font-size:14px;
}
.mid_content_box2{
positon : relative;
width : 240px;
height : 110px;
margin-top : 10px;
margin-left: 4px;
margin-right:0px;
margin-bottom : 0px;
padding-bottom: 0px;
font-size:14px;
}
table tr{
vertical-align:top;
height : 27px;
}
table td{
border-bottom:1px solid #d2d2d2;
}
#mid_content_box3{
position: relative;
margin : 0px;
margin-left : 15px;
width : 475px;
height : 230px;
padding: 4px;
font-size:14px;
}
#mid_content_box4{
position: relative;
margin : 5px;
margin-left : 15px;
width : 475px;
height : 230px;
padding: 4px;
font-size:14px;
}
#mid_content_box4 input[type="text"]{
margin: 4px;
margin-bottom:0px;
width:420px;
border:1px solid #d2d2d2;
}
#mid_content_box4 input[type="button"]{
width:38px;
font-size:12px;
}

.side_right{
position : absolute;
left : 780px;
display: flex;
width: 400px;
height: 600px;
flex-wrap:nowrap;
align-content : flex-start;
}


#side_rigth_back1{
margin-top : 92px;
margin-right:20px;
width:50px;
height:500px;

}

#side_rigth_box1{
width:100%;
height:100%;
}
.other_button {
width:64px;
height:35px;
margin-top:1px;
background-color:#2892CB;
color:white;
border-radius: 0px 6px 6px 0px;
border-top : 1px solid gray;
border-right : 1px solid gray;
border-bottom: 1px solid gray;
border-left:0px;
}
#home_button{
width:64px;
height:35px;
margin-top:1px;
background-color:white;
border-radius: 0px 6px 6px 0px;
border-top : 1px solid gray;
border-right : 1px solid gray;
border-bottom: 1px solid gray;
border-left:0px;
}
}
#side_rigth_back2{
margin-top: 60px;
width: 200px;
height: 350px;

}
#side_rigth_box2{
diplay : inline-flex;
flex-wrap: nowrap;
justify-content : flex-start;
align-content : flex-start;
margin-top: 10px;
width:180px;
height:310px;
background-color: white;
}
#friend_rec{
flex-wrap: nowrap;
width:90px;
height:20px;
margin-left:0px;
margin-right: -2px;
background-color:white;
border-top : 0px;
border-right : 1px solid gray;
border-bottom: 0px;
border-left:0px;
}
#fan{
flex-wrap: nowrap;
width:90px;
height:20px;
margin-left:-4px;
margin-top:-6px;
background-color:#efefef;
border-top : 0px;
border-right : 0px;
border-bottom: 1px solid gray;
border-left:0px;
}
.container{
position:block;
width:170px;

margin:0px auto;
display:grid;
grid-template-columns: 85px 85px;

grid-template-rows: 20px 20px;
}
.item{
font-size:13px;
padding: 5px;
}


</style>
</head>
<body >
	
	<div id = "main1">
		<div id = "main2_dashed">
		<div id = "main2">
			<div id = "side_left"><br> TODAY <font color = "red" style="margin-bottom:10px">2</font> | TOTAL 30
			   	<div id = "left_back">
				    <div id = "side_left_box1">
				    <img src="/semi_project/upload/<%if(profilePic==null){%>프로필.png<%}else{%><%=profilePic%><% }%>" id ="profilePic" style="max-width:95%; max-height:200px; vertical-align:middle; cursor:pointer;" ></img>
				    </div>
				    <div id = "profileText"><%if(profileTxt==null){%>자기소개가 없습니다.<%}else{%><%=profileTxt%><% }%></div>
				    <div id = "side_left_box2">
				    	<div id="a" ><a href="" id="list" onclick="profileList();" target="_blank" title="프로필 목록"> > history</a></div>
				    	<div id="b" >
				    	<input type="hidden" value="<%=pageId%>" id="profileId">
				    	<input type="hidden" value="<%=i%>" id="profileIndex">
				    	<a id="next" style="cursor:pointer;" title="다음" >▲</a><span> </span><a  id="previous" style="cursor:pointer;" title="이전">▼</a></div>
				    </div>
				    <div id = "side_left_box3">
				    	<div id="c" ><a href="#">
				    	 <%=name %><%if(ms.getUserData(u).getUser_gender().equals("남")){%> ♂<%}else{%> ♀<% }%></a></div>
				    	<div id="d" >
				    		<form action="/semi_project/friendLinkCont" method="get" >
				    		<select name="friendId" onChange="this.form.submit();">
					    		 <option value="" selected disabled hidden >일촌 파도타기</option>
					    		<%for(int j = 0; j< fl.size();j++){%>
					    		<option value="<%=fl.get(j).getFriendId()%>" ><%=fl.get(j).getFriendName() %>
					    		</option>
				    		<%}%>
				    		</select>
					    	</form>	
			    		</div>
				    </div>
				</div>
			</div>
			<div id = "mid_content">
				
			    <div id = "mid_back1">
			    	<div id="m_a"><h1><%if(pageName.equals("")){%><%=name%>님의 홈페이지<%}else{%><%=pageName%><%}%></h1></div>
			    	<div id="m_b" ><a href="" style="text-decoration:none;"></a></div>
			    	<div id="m_c" ><a href="#" style="text-decoration:none;"></a></div>
			    </div>
			    <div class = "mid_back2">
					<div class = "mid_content_box1">
						<b style="color:#11DEEE; margin:15px;"> 최근 게시물</b>
						<hr style="margin:0px; margin-left:10px; width:230px; border: 1px solid #d2d2d2;"></hr>
						<p style=" padding: 5px; margin:0px; margin-left:11px; width:220px; height:80px; background-color: #F7F7F7;">
							등록된 게시물이 없습니다. 
						</p>
					</div>
					<div class = "mid_content_box2">
						<hr style="margin-bottom:2px;margin-top: 18px; margin-right:15px; width:230px; border: 1px solid #d2d2d2;"></hr>
						<table style= "margin:0px; margin-left:5px; width:230px; height:80px; font-size:14px;">
							<tr >
								<td >
									다이어리  <font color=#0064CD>0/0</font>
								</td>
								<td>
									사진첩  <font color=#0064CD>0/0</font>
								</td>
							</tr>
							<tr >
								<td>
									갤러리  <font color=#0064CD>0/0</font>
								</td>
								<td>
									게시판  <font color=#0064CD>0/0</font>
								</td>
							</tr>
							<tr >
								<td>
									동영상  <font color=#0064CD>0/0</font>
								</td>
								<td>
									방명록  <font color=#0064CD>0/1</font>
								</td>
							</tr>
						
						</table>
					</div>
					<div id = "mid_content_box3">
						미니라이프  | <font style="color:#11DEEE">미니룸  |</font>  스토리룸
						<img src="/semi_project/upload/미니룸.png" style="display:block; width:470px; margin:0px auto;">
						
					</div>
					<div id = "mid_content_box4">
						<p style="display:block; padding: 4px; padding-bottom:1px; margin:13px auto; margin-bottom: 0px;width:458px; height:23px; background-color:#F7F7F7; color:#11DEEE; font-size:14px; font-weight:bold; border:1px solid #d2d2d2;" >일촌평</p>
						<input id= "friendMsg"type="text" value="" placeholder="나의 일촌에대해 한마디로 표현해주세요"><input type="button" value="등록" onclick="friendMsgEdit()"/>
						<p style="display:block; padding: 4px; margin:4px auto; margin-bottom: 0px;width:458px; font-size:13px;" >
							<%for(int k=0; k<fml.size(); k++){ %><font color=#0064CD><%=fml.get(k).getFriendName()%>(<%=fml.get(k).getFriendNick() %>)</font> : <%=fml.get(k).getFriendMsg()%><span style="font-size:9px;"> <%=fml.get(k).getFriendMsgDate() %></span><br><%}%>
						</p>
					</div>
					
					
				</div>
				
			</div>
		</div>
		</div> 	
	</div>
	<div class = "side_right">
		<div id = "side_rigth_back1">
			    <div id = "side_rigth_box1">
			    <input id = "home_button" type="button" value="홈">
			    <input class= "other_button" type="button" value="프로필">
			    <input class= "other_button" type="button" value="다이어리">
			     <input class= "other_button"type="button" value="쥬크박스">
			    <input class= "other_button" type="button" value="사진첩">
			    <input class= "other_button" type="button" value="게시판">
			    <input class= "other_button" type="button" value="갤러리">
			    <input class= "other_button" type="button" value="동영상">
			    <input class= "other_button" type="button" value="방명록">
			   
			    </div>
		</div>
		<div id = "side_rigth_back2">
			    <div id = "side_rigth_box2">
			    <input  id="friend_rec" type="button" value="친구추천">
			    <input  id="fan" type="button" value="팬">
			    
			    <div style="display:flex; padding-left:16px; padding-top:12px;font-size:13px;text-align:center;">
			    	<div><%=name %>님과 나는
			    	</div>
			   	 	<div style="background-color:#99D9EA;color:white; width:46px; height:22px; border-radius:12px;text-align:center;"> 
		    		일촌 </div>
		    		
	    		</div>
			   	<hr style="margin:0px; margin-top:15px; width:178px; border: 1px solid #d2d2d2;"></hr>
			   <div class= "container">
			   		<div class="item">댓글</div>
				   <div class="item">스크랩 <font color="#FF9B00">0</font></div>
				   <div class="item">앱스 <font color="#FF9B00">0</font></div>
				   <div class="item">소망상자</div>
			   </div>
			   <hr style="margin:0px; margin-top:10px; width:178px; border: 1px solid #d2d2d2;"></hr>
			    <div title="상대방과 나와의 관계도를 나타냅니다." style="padding:6px; padding-top:8px; font-size:13px;">
			      액티브  <progress value="22" max="100" style="width:100px; color:#FF9B00"></progress>
			    </div>
   			   <hr style="margin:0px; margin-top:7px; width:178px; border: 1px solid #d2d2d2;"></hr>
			    <div style="margin:0px; width:100%; height:50px; background-color:#efefef;">
			    	<span style="position:relative; left : 10px; top : 3px; display:block;width:160px; height:18px; background-color:#f9f9f9; border:1px solid gray; font-size:13px;"> 음악 선물하기</span>
			    	<audio style="width:90%; height:25px; margin-top:3px;" src="/semi_project/upload/" autoplay controls ></audio>
			    </div>
			    <div style="text-align:center; font-size:13px;">
			    	<b>추천BGM</b>/선물가게
			    	<div style="display:flex; " >
			    		<img src="/semi_project/upload/앨범1.png" style="margin-left:20px; margin-top: 4px; width:65px;">
			    		<img src="/semi_project/upload/앨범2.png" style="margin-left:10px; margin-top: 4px; width:65px;">
			    	</div>
			    	
			    </div>
		</div>
	</div>
</div>
	
</body>
</html>