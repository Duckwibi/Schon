<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<section class="widget category-widget">
	<h3>CATEGORIES</h3>
	<ul class="list-unstyled widget-nav">
		<c:forEach var="item" items="${requestScope.blogCategoryList }">
			<c:url var="url" value="/BlogPageServlet" scope="page">
				<c:param name="blogCategoryID" value="${pageScope.item.blogCategoryID }"></c:param>
			</c:url>
			<li><a href="${pageScope.url }">${fn:escapeXml(pageScope.item.name) }</a></li>
		</c:forEach>
	</ul>
</section>