<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/user.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#join-form").submit(function() {
			/*회원가입 폼 유효성 validation*/

			//1. 이름
			var name = $("#name").val();
			if (name == "") {
				alert("이름이 비어있습니다");
				$("#name").focus();
				return false;
			}
			;

			//2.이메일
			var email = $("#email").val();
			if (email == "") {
				alert("이메일이 비어 있습니다");
				$("#email").focus();
				return false;
			}

			var isVisible = $("#exists").is(":visible");
			if (isVisible == false) {
				alert("이메일 중복 체크를 해주세요");
				return false;
			}

				
			//3.패스워드
			var password = $("input[type='password']").val();
			if (password == "") {
				alert("패스워드를 입력해주세요");
				$("#password").focus();
				return false;
			}

			//4.약관동의
			var agree = $("#agree-prov").is(":checked");
			if (agree == false) {
				alert("약관에 동의해주세요");
				return false;
			} 
			return true;
		});

		$("input[type='button']").click(function() {
			var email = $("#email").val();
			/* ajax 통신 */
			$.ajax({
				url : "/mysite3/user/checkemail?email=" + email,
				type : "get",
				dataType : "json",
				data : "",
				//  contentType: "application/json",
				success : function(response) {

					console.log(response);

					if (response.result == "fail") {
						console.log("error" + response.message);
						return;
					}

					if (response.data == "Exist") {
						//alert("존재하는 이메일입니다. 다른 이메일을 사용해주세요");
						$("#dialogMessage").dialog();
						$("#email").val("").focus();
						return;
					}
					
					if(email=="") {
						$("#dialogMessage2").dialog();
						$("#email").focus();
						return;
					}

					//존재하지 않는 경우(사용가능)
					$("#exists").show();
					$("input[type='button']").hide();
				},
				error : function(XHR, status, error) {
					console.error(status + " : " + error);
				}

			});
		});

		$("#email").keyup(function() {
			if ($("#exists").is(":visible") == true) {
				alert("다시 중복체크를 해주세요");
			}
			$("#exists").hide();
			$("input[type='button']").show();

		});

	});
</script>
</head>
<body>

	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="user">

				<form:form modelAttribute="userVo" id="join-form" name="joinForm" method="post"
					action="${pageContext.request.contextPath }/user/join">
					<label class="block-label" for="name">이름 </label> 
					<form:input path="name" />
					<spring:hasBindErrors name="userVo">
						<c:if test="${errors.hasFieldErrors('name') }">
							<p style="text-align: left; padding: 5px 0; color: #F08080">
								<strong>
								<spring:message
										code="${errors.getFieldError( 'name' ).codes[0] }"
										text="${errors.getFieldError( 'name' ).defaultMessage }" />
								</strong>
							</p>
						</c:if>
					</spring:hasBindErrors>
					
					
					
					<label class="block-label" for="email">이메일</label> 
					<form:input path="email"/>
						 <input	type="button" value="중복체크">
						<img id="exists" src="/mysite3/assets/images/check.png" style="display: none" width="40" height="40">
						<p style="text-align: left; padding: 5px 0; color: #F08080; font-weight:bold"><form:errors path="email"/></p>

						<label class="block-label">패스워드</label> 
						<form:password path="password"/>
							<p style="text-align: left; padding: 5px 0; color: #F08080; font-weight:bold"><form:errors path="password"/></p>
	
						<fieldset>
							<legend>성별</legend>
							<label>여</label> <input type="radio" name="gender" value="female"
								checked="checked"> <label>남</label> <input type="radio"
								name="gender" value="male">
						</fieldset>

						<fieldset>
							<legend>약관동의</legend>
							<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
							<label>서비스 약관에 동의합니다.</label>
						</fieldset>

					<input type="submit" value="가입하기">

				</form:form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/Navigation.jsp"></c:import>
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
	</div>
	<div id="dialogMessage" title="중복된 이메일" style="display: none">
		<p>
			존재하는 이메일 입니다. <br>다른 이메일을 사용해주세요
		</p>
	</div>
	<div id="dialogMessage2" title="비어있는 이메일" style="display: none">
		<p>
			이메일이 비어있습니다. <br>이메일 형식으로 입력뒤 다시 중복체크 부탁드립니다.
		</p>
	</div>
</body>
</html>