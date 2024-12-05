<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<nav id="nav">
	<ul>
		<c:forEach var="item1" items="${requestScope.menuList }">
			<c:if test="${pageScope.item1.levels == 1 }">
				<li>
					<c:choose>
						<c:when test="${pageScope.item1.enableLink == 1 }">
							<a class="drop-link menuID-${pageScope.item1.menuID }" href="${fn:escapeXml(pageScope.item1.link) }">
								<span>${fn:escapeXml(pageScope.item1.name) }</span>
								<i class="fa fa-angle-down hidden-lg hidden-md" aria-hidden="true"></i>
							</a>
							<div class="s-drop">
								<ul>
									<c:forEach var="item2" items="${requestScope.menuList }">
										<c:if test="${pageScope.item2.levels == 2 && pageScope.item2.parentID == pageScope.item1.menuID }">
											<li>
												<a class="menuID-${pageScope.item2.menuID }" href="${fn:escapeXml(pageScope.item2.link) }">
													<span>${fn:escapeXml(pageScope.item2.name) }</span>
												</a>
											</li>
										</c:if>
									</c:forEach>
								</ul>
							</div>
						</c:when>
						<c:otherwise>
							<a class="menuID-${pageScope.item1.menuID }" href="${fn:escapeXml(pageScope.item1.link) }">
								<span>${fn:escapeXml(pageScope.item1.name) }</span>
							</a>
						</c:otherwise>
					</c:choose>
				</li>
			</c:if>
		</c:forEach>
	</ul>
</nav>

<script>
	window.addEventListener("load", () => {
		<c:forEach var="item" items="${requestScope.productCategoryList }">
			<c:url var="url" value="/ProductPageServlet" scope="page">
				<c:param name="productCategoryID" value="${pageScope.item.productCategoryID}"></c:param>
			</c:url>
			if(document.querySelector(".menuID-${pageScope.item.menuID}") != null){
				document.querySelector(".menuID-${pageScope.item.menuID}").href = "${pageScope.url}";
				document.querySelector(".menuID-${pageScope.item.menuID}>span").innerText = "${fn:escapeXml(pageScope.item.name)}";
			}
		</c:forEach>
		
		<c:forEach var="item" items="${requestScope.blogCategoryList }">
			<c:url var="url" value="/BlogPageServlet" scope="page">
				<c:param name="blogCategoryID" value="${pageScope.item.blogCategoryID}"></c:param>
			</c:url>
			if(document.querySelector(".menuID-${pageScope.item.menuID}") != null){
				document.querySelector(".menuID-${pageScope.item.menuID}").href = "${pageScope.url}";
				document.querySelector(".menuID-${pageScope.item.menuID}>span").innerText = "${fn:escapeXml(pageScope.item.name)}";
			}
		</c:forEach>
	});
</script>