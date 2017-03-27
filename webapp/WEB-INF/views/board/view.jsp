<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${contentNo.title }</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
								${contentNo.content}
							</div>
						</td>
					</tr>
				</table>
				<div class="bottom">
					<a href="${pageContext.request.contextPath }/board/list">글목록</a>
					<c:if test="${authUser.no== contentNo.userNo }">
					<a href="${pageContext.request.contextPath }/board/modifyform?no=${no}&title=${contentNo.title}&content=${contentNo.content}">글수정</a>
					</c:if>
					<c:if test="${not empty authUser }">
					<a href="${pageContext.request.contextPath }/board/replyform?gNo=${contentNo.gNo}&oNo=${contentNo.oNo}&depth=${contentNo.depth}">답글달기</a>
					</c:if>
				</div>
			</div>
		</div>
			<c:import url="/WEB-INF/views/include/Navigation.jsp"/>
		<c:import url="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>