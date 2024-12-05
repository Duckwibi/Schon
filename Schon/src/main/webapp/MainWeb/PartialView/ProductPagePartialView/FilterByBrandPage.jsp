<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<ul class="list-unstyled nice-form">
	<c:forEach var="item" items="${requestScope.brandList }">
		<li>
			<label for="brandID-${pageScope.item.brandID }">
				<c:choose>
					<c:when test="${requestScope.brandID == pageScope.item.brandID }">
						<input id="brandID-${pageScope.item.brandID }" name="brandIDRadio" type="radio" value="${pageScope.item.brandID }" checked>
					</c:when>
					<c:otherwise>
						<input id="brandID-${pageScope.item.brandID }" name="brandIDRadio" type="radio" value="${pageScope.item.brandID }">
					</c:otherwise>
				</c:choose>
				<span class="fake-input"></span>
				<span class="fake-label">${fn:escapeXml(pageScope.item.name) }</span>
			</label> 
			<span class="num brandID-${pageScope.item.brandID }">0</span>
		</li>
	</c:forEach>
</ul>

<script>
	window.addEventListener("load", () => {
		<c:forEach var="item" items="${requestScope.productOfBrandCountList }">
			document.querySelector(".brandID-${pageScope.item.brandID }").innerText = "${pageScope.item.count}";
		</c:forEach>
	});
</script>