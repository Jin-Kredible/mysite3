<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css?a=d" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.request.contextPath }/board/list" method="post">
					<input type="text" id="keyword" name="keyword" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:forEach var="list" items="${list }" varStatus="status">				
					<tr>
						<td>${list.bNo }</td>
						<td class="left">
							<c:forEach begin="1" end="${list.depth}" varStatus="status">
								<img src="${pageContext.request.contextPath}/assets/images/reply.png" width="15px" height="15px">
							</c:forEach>
							<%-- <c:choose>
							<c:when test="${list.depth>0}">
								<img src="${pageContext.request.contextPath}/assets/images/reply.png" width="30px" height="20px">
							</c:when>
							</c:choose> --%>
							<a href="${pageContext.request.contextPath}/board/view?no=${list.bNo}">${list.title }</a>
						</td>
						<td>${list.userName }</td>
						<td>${list.hit }</td>
						<td>${list.regDate }</td>
						<c:if test="${authUser.no ==list.userNo }">
						<td><a href="${pageContext.request.contextPath}/board/delete?bNo=${list.bNo}&userNo=${list.userNo}" class="del">삭제</a></td>
						</c:if>
					</tr>
					</c:forEach>			
				</table>
				<div class="pager">
					<ul>
						<c:if test="${previous >0 }">
						<li><a href="${pageContext.request.contextPath }/board/list?pageNum=${previous}">◀</a></li>
						</c:if>
						
						<c:forEach begin="${beginPage }" end="${endPage}" var="page">
						<c:choose>
						<c:when test="${page == currentPage }">			
						<li class="selected">${page }</li>
						</c:when>
						<c:when test="${page > pageSize }">
						<li>${page }</li>
						</c:when>
						<c:otherwise>
						<li><a href="${pageContext.request.contextPath}/board/list?pageNum=${page}&keyword=${keyword}">${page }</a></li>
						</c:otherwise>
						</c:choose>
						</c:forEach>
					
						<c:if test="${next>0 }">
						<li><a href="${pageContext.request.contextPath }/board/list?pageNum=${next}">▶ </a></li>
						</c:if>
					</ul>
				</div>	
				<c:choose>			
				<c:when test="${authUser!=null }">
				<div class="bottom">
					<a href="${pageContext.request.contextPath }/board/writeform?userNo=${authUser.no}" id="new-book">글쓰기</a>
				</div>
				</c:when>	
				<c:otherwise>
				</c:otherwise>
				</c:choose>			
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/Navigation.jsp"/>
		<c:import url="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>