<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="mt-pagination">
	<ul class="list-inline">
		<c:if test="${requestScope.currentPage - 1 >= 1 }">
			<c:url var="url" value="/ProductPageServlet" scope="page">
				<c:param name="productCategoryID" value="${requestScope.productCategoryID }"></c:param>
				<c:if test="${requestScope.brandID != 0 }">
					<c:param name="brandID" value="${requestScope.brandID }"></c:param>
				</c:if>
				<c:if test="${requestScope.priceFilterID != 0 }">
					<c:param name="priceFilterID" value="${requestScope.priceFilterID }"></c:param>
				</c:if>
				<c:if test="${requestScope.sortByPrice != -1 }">
					<c:param name="sortByPrice" value="${requestScope.sortByPrice }"></c:param>
				</c:if>
				<c:param name="currentPage" value="${requestScope.currentPage - 1 }"></c:param>
			</c:url>
			<li><a href="${pageScope.url }">Before</a></li>
		</c:if>

		<c:forEach var="item" begin="${requestScope.currentPage }" end="${requestScope.nextThreePage }">
			<c:url var="url" value="/ProductPageServlet" scope="page">
				<c:param name="productCategoryID" value="${requestScope.productCategoryID }"></c:param>
				<c:if test="${requestScope.brandID != 0 }">
					<c:param name="brandID" value="${requestScope.brandID }"></c:param>
				</c:if>
				<c:if test="${requestScope.priceFilterID != 0 }">
					<c:param name="priceFilterID" value="${requestScope.priceFilterID }"></c:param>
				</c:if>
				<c:if test="${requestScope.sortByPrice != -1 }">
					<c:param name="sortByPrice" value="${requestScope.sortByPrice }"></c:param>
				</c:if>
				<c:param name="currentPage" value="${pageScope.item }"></c:param>
			</c:url>
			<li><a href="${pageScope.url }">${pageScope.item }</a></li>
		</c:forEach>

		<c:if test="${requestScope.currentPage + 1 <= requestScope.pageCount }">
			<c:url var="url" value="/ProductPageServlet" scope="page">
				<c:param name="productCategoryID" value="${requestScope.productCategoryID }"></c:param>
				<c:if test="${requestScope.brandID != 0 }">
					<c:param name="brandID" value="${requestScope.brandID }"></c:param>
				</c:if>
				<c:if test="${requestScope.priceFilterID != 0 }">
					<c:param name="priceFilterID" value="${requestScope.priceFilterID }"></c:param>
				</c:if>
				<c:if test="${requestScope.sortByPrice != -1 }">
					<c:param name="sortByPrice" value="${requestScope.sortByPrice }"></c:param>
				</c:if>
				<c:param name="currentPage" value="${requestScope.currentPage + 1 }"></c:param>
			</c:url>
			<li><a href="${pageScope.url }">Next</a></li>
		</c:if>
	</ul>
</nav>