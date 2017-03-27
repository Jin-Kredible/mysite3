<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="navigation">
			<ul>
				<li><a href="${pageContext.request.contextPath }/main">Main page</a></li>
				<li><a href="${pageContext.request.contextPath }/guestbook/list">Guest Board</a></li>
				<li><a href="${pageContext.request.contextPath }/guestbook/list-ajax">Guest Board</a></li>
				<li><a href="${pageContext.request.contextPath }/board/list">Forum</a></li>
				<li><a href="${pageContext.request.contextPath }/gallery/list">Gallery</a></li>
				
			</ul>
		</div>