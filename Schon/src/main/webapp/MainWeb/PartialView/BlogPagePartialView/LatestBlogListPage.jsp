<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="Utilities.SharedMethod" %>

<section class="widget popular-widget">
	<h3>LATEST POST</h3>
	<ul class="list-unstyled text-right popular-post">
		<c:forEach var="item" items="${requestScope.latestBlogList }">
			<li>
				<c:url var="url" value="/BlogDetailPageServlet" scope="page">
					<c:param name="blogID" value="${pageScope.item.blogID }"></c:param>
				</c:url>
				<div class="img-post">
					<a href="${pageScope.url }"> 
						<img class="blogID-V1-${pageScope.item.blogID }" src="#" alt="image description">
					</a>
				</div>
				<div class="info-dscrp">
					<p class="maxText2Line">${fn:escapeXml(pageScope.item.content) }</p>
					<time>${SharedMethod.dateFormat("dd.MM.yyyy", pageScope.item.createdDate) }</time>
				</div>
			</li>
		</c:forEach>
	</ul>
	
</section>
<script>
	window.addEventListener("load", () => {
		<c:forEach var="item" items="${requestScope.latestBlogImageList}">
			if(document.querySelector(".blogID-V1-${pageScope.item.blogImage.blogID}") != null){
				let imgTag = document.querySelector(".blogID-V1-${pageScope.item.blogImage.blogID}");
				imgTag.src = "${fn:escapeXml(pageScope.item.image.link) }";
			}
		</c:forEach>
	});
</script>