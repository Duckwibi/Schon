<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>      

<div class="f-widget-tabs">
	<h3 class="f-widget-heading">Product Tags</h3>
	<ul class="list-unstyled tabs">
		<c:forEach var="item" items="${requestScope.productCategoryList }">
			<c:url var="url" value="/ProductPageServlet" scope="page">
				<c:param name="productCategoryID" value="${pageScope.item.productCategoryID }"></c:param>
			</c:url>
			<li><a href="${pageScope.url }">${fn:escapeXml(pageScope.item.name) }</a></li>
		</c:forEach>
	</ul>
</div>