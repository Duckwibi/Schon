<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<ul class="list-unstyled category-list">
	<c:forEach var="item" items="${requestScope.productCategoryList }">
		<c:url var="url" value="/ProductPageServlet" scope="page">
			<c:param name="productCategoryID" value="${pageScope.item.productCategoryID }"></c:param>
		</c:url>
		<li>
			<a href="${pageScope.url }">
				<span class="name">${fn:escapeXml(pageScope.item.name) }</span>
				<span class="num productCategoryID-${pageScope.item.productCategoryID }">0</span>
			</a>
		</li>
	</c:forEach>
</ul>

<script>
	window.addEventListener("load", () => {
		<c:forEach var="item" items="${requestScope.productOfProductCategoryCountList }">
			document.querySelector(".productCategoryID-${pageScope.item.productCategoryID }").innerText = "${pageScope.item.count}";
		</c:forEach>
	});
</script>