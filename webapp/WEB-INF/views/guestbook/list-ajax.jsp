<%@page import="com.bit2017.mysite.vo.UserVo"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>

var prepend = false;
var isEnd=false;
var dialogDeleteForm=null;

var page=0;
var render = function(vo) {
		var html =
		"<li id='li-" + vo.no + "'>	<table><tr><td>" +  vo.no  + "</td><td>" + vo.name + "</td>" + 
		"<td>" + vo.pubDate+ "</td><td><a href='' data-no=' " +vo.no +"'> 삭제 </a></td></tr>" + 
		"<tr><td colspan=4>" + vo.content + "</td>" +
		"</tr> </table> <br> </li>";
		
		if(prepend ==false) {
		$("#list").append(html);
		}
		if(prepend ==true) {
		$("#list").prepend(html);	
		}
		}

	

var fetchList = function() {
	if(isEnd==true) {
		return;
	}
	++page;
	$.ajax({
		url : "/mysite3/api/guestbook/list/" + page,
		type : "get",
		dataType : "json",
		data : "",
		success: function(response) {
			prepend =false;
			if(response.result != "success") {
				console.log(response.message);
				return;
			}
			
			$(response.data).each(function(index, vo){
				
				render(vo);
			});
			
			if(response.data.length ==0) {
				isEnd = true;
				return;
			}
			
		},
		error: function(XHR, status, error) {
				console.error(status + " : " + error);
		}
	});
	
	
}


/* {
	$("#btn-next").click(function(){
		fetchList();
	}); */
$(function(){
	$("#write-form").submit(function(event) {
		//폼의 submit 기본 이벤트 처리를 막는다.
		event.preventDefault();
		/*ajax 입력*/
		$.ajax({
		url : "/mysite3/api/guestbook/add",
		type : "post",
		dataType : "json",
		data : "name=" + $("#name").val() + "&" + "password="+ $("#password").val() + "&" + "content="+$("textarea").val(),
		success: function(response) {
		  	prepend =true;
		  	$("#name").val("");
		  	$("#password").val("");
		  	$("textarea").val("");
			if(response.result != "success") {
				console.log(response.message);
				return;
			}
			
			$(response.data).each(function(index, vo){
				render(vo);
			});
			
			if(response.data.length ==0) {
				isEnd = true;
				return;
			}
			
		},
		error: function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		

		return false;
	});
	
	
	$(window).scroll(function() {

		var $window = $(this);
		var scrollTop = $window.scrollTop();
		var windowHeight = $window.height();
		var documentHeight = $(document).height();
		
		if(scrollTop + windowHeight +10 > documentHeight) {
			fetchList();
		}
	});
	
	//삭제버튼 클릭 이벤트 매핑 (Live event mapping)
	$(document).on("click", "#list li a", function(event){
		event.preventDefault();
		var $a = $(this);
		var no = $a.attr("data-no");
		$("#no-delete").val(no);
		dialogDeleteForm.dialog("open");
		//console.log("hey");
	});
	dialogDeleteForm = $( "#dialog-form" ).dialog({
	    autoOpen: false,
	    height: 200,
	    width: 350,
	    modal: true,
	    buttons: {
	      "삭제": function() {
	    	  var no = $("#no-delete").val();
	    	  var password = $("#password-delete").val();
	    	  //console.log(no + password);
	    	  /* ajax 통신(삭제) */
	    	 $.ajax({
	    			url : "/mysite3/api/guestbook/delete",
	    			type : "post",
	    			dataType : "json",
	    			data : "no=" + no + "&" + "password="+ password,
	    			success: function(response) {
	    			  	console.log(response);
	    			  	if(response.result!="success") {
	    			  		console.log(response.message);
	    			  		dialogDeleteForm.dialog("close");
	    			  		return
	    			  		}	
	    			  	//삭제 실패
	    			  	if(response.data==-1) {
	    			  		$("#delete-tip-error").show();
	    			  		$("#delete-tip-normal").hide();
	    			  		$("#password-delete").val("").focus();
	    			  		return;
	    			  	}
	    			  	
	    			  	//삭제 성공 + 폼 리셋 (li 엘리멘트 삭제)
	    			  	$("#delete-tip-error").hide();
	    			  	$("#delete-tip-normal").show();
	    			  	$("#password-delete").val("").focus();
	    			  	
	    			   	$("#li-" + response.data).remove();
	    			  	
	    			  	dialogDeleteForm.dialog("close");
	    			  	
	    			  	
	    			},
	    			error: function(XHR, status, error) {
	    					console.error(status + " : " + error);
	    				}
	    			});
	    	   
	      },
	      "취소": function() {
	        dialogDeleteForm.dialog( "close" );
	      }
	    },
	    close: function() {
	    }
	  });

	
	fetchList();
});



</script>
</head>
<body>

	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp"/>
		<div id="content">
			<div id="guestbook">
				<form id="write-form" action="${pageContext.request.contextPath }/guestbook/add" method="post">
					<input type="hidden" name="a" value="insert">
					<table>
						<tr>
							<td>이름</td><td><input type="text" name="name" id="name"></td>
							<td>비밀번호</td><td><input type="password" name="password" id="password"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="content" id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				<ul id="list">
				</ul>
				<button id="btn-next">다음</button>
			</div>
		</div>
			<c:import url="/WEB-INF/views/include/Navigation.jsp"></c:import>
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
	</div>
	<div id="dialog-form" title="게시글 삭제" style="display:none">
  		<p id="delete-tip-normal" class="validateTips" style="padding:10px 0; font-weight:bold">삭제하기 위해 비밀번호를 입력해주세요</p>
  		<p id ="delete-tip-error" class="validateTips" style="padding:10px 0; font-weight:bold; color:#DC143C; display:none">비밀번호가 틀렸습니다. 다시 입력해주세요.</p>
 			 <form>
   						      <label for="password">게시글 비밀번호 : </label>
   						      <input type="hidden" id="no-delete" value="">
					      <input type="password" name="password" id="password-delete" value="" class="text ui-widget-content ui-corner-all">		
      						<input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
 			 </form>
	</div>
</body>
</html>