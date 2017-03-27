<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<div id="header">
			<a href="${pageContext.request.contextPath }/main"><h1>My OverWatch Website</h1></a>
			<ul>
			<c:choose>
			<c:when test="${empty authUser }">
				<li><a href="${pageContext.request.contextPath }/user/loginform">로그인</a></li>
				<li><a href="${pageContext.request.contextPath }/user/joinform">회원가입</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${pageContext.request.contextPath }/user/modifyform">회원정보수정</a></li>
				<li><a href="${pageContext.request.contextPath }/user/logout">로그아웃</a></li>
				<li>${sessionScope.authUser.name }님 안녕하세요</li>
			</c:otherwise>
				</c:choose>
			</ul>
		</div>

</body>
</html>