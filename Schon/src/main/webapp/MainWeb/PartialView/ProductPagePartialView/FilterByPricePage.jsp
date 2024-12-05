<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>   

<ul class="list-unstyled nice-form">
	<c:forEach var="item" items="${requestScope.priceFilterList }">
		<li>
			<label for="priceFilterID-${pageScope.item.priceFilterID }">
				<c:choose>
					<c:when test="${requestScope.priceFilterID == pageScope.item.priceFilterID }">
						<input id="priceFilterID-${pageScope.item.priceFilterID }" name="priceFilterIDRadio" type="radio" value="${pageScope.item.priceFilterID }" checked>
					</c:when>
					<c:otherwise>
						<input id="priceFilterID-${pageScope.item.priceFilterID }" name="priceFilterIDRadio" type="radio" value="${pageScope.item.priceFilterID }">
					</c:otherwise>
				</c:choose>
				<span class="fake-input"></span>
				<span class="fake-label">${fn:escapeXml(pageScope.item.name) }</span>
			</label>
		</li>
	</c:forEach>
</ul>