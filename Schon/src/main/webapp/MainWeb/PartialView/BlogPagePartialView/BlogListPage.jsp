<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="Utilities.SharedMethod" %>

<div class="col-xs-12 col-sm-8 wow fadeInLeft" data-wow-delay="0.4s">
	<c:forEach var="item" items="${requestScope.blogJoinBlogCategoryList }">
		<c:set var="b" value="${pageScope.item.blog }" scope="page"></c:set>
		<c:set var="bc" value="${pageScope.item.blogCategory }" scope="page"></c:set>
		<article class="blog-post style2">
			<div class="img-holder">
				<c:url var="url" value="/BlogDetailPageServlet" scope="page">
					<c:param name="blogID" value="${pageScope.b.blogID }"></c:param>
				</c:url>
				<a href="${pageScope.url }"> 
					<img class="blogID-V2-${pageScope.b.blogID }" src="#" alt="image description">
				</a>
				<ul class="list-unstyled comment-nav">
					<li><a><i class="fa fa-comments"></i><span class="blogID-V3-${pageScope.b.blogID }">0</span></a></li>
				</ul>
			</div>
			<div class="blog-txt">
				<h2>
					<a href="${pageScope.url }">${fn:escapeXml(pageScope.b.title) }</a>
				</h2>
				<ul class="list-unstyled blog-nav">
					<li><a><i class="fa fa-clock-o"></i>${SharedMethod.dateFormat("dd MMM yyyy", pageScope.b.createdDate) }</a></li>
					<li><a><i class="fa fa-list"></i>${fn:escapeXml(pageScope.bc.name) }</a></li>
					<li><a><i class="fa fa-comment"></i><span class="blogID-V4-${pageScope.b.blogID }">0</span> Comments</a></li>
				</ul>
				<p class="maxText2Line">${fn:escapeXml(pageScope.b.content) }</p>
				<a href="${pageScope.url }" class="btn-more">Read More</a>
			</div>
		</article>
	</c:forEach>
	<!-- Blog Post of the Page -->
	
	<!-- Blog Post of the Page end -->
	<!-- Btn Holder of the Page -->
	<div class="btn-holder">
		<ul class="list-unstyled pagination">
		
			<c:if test="${requestScope.currentPage - 1 >= 1 }">
				<c:url var="url" value="/BlogPageServlet" scope="page">
					<c:param name="blogCategoryID" value="${requestScope.blogCategory.blogCategoryID }"></c:param>
					<c:param name="currentPage" value="${requestScope.currentPage - 1 }"></c:param>
				</c:url>
				<li><a href="${pageScope.url }">Before</a></li>
			</c:if>
			
			<c:forEach var="i" begin="${requestScope.currentPage }" end="${requestScope.nextThreePage }">
				<c:url var="url" value="/BlogPageServlet" scope="page">
					<c:param name="blogCategoryID" value="${requestScope.blogCategory.blogCategoryID }"></c:param>
					<c:param name="currentPage" value="${pageScope.i }"></c:param>
				</c:url>
				<c:choose>
					<c:when test="${pageScope.i == requestScope.currentPage }">
						<li class="active"><a href="${pageScope.url }">${pageScope.i }</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${pageScope.url }">${pageScope.i }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<c:if test="${requestScope.currentPage + 1 <= requestScope.pageCount }">
				<c:url var="url" value="/BlogPageServlet" scope="page">
					<c:param name="blogCategoryID" value="${requestScope.blogCategory.blogCategoryID }"></c:param>
					<c:param name="currentPage" value="${requestScope.currentPage + 1 }"></c:param>
				</c:url>
				<li><a href="${pageScope.url }">Next</a></li>
			</c:if>
		</ul>
	</div>
	<!-- Btn Holder of the Page end -->
</div>

<script>
	window.addEventListener("load", () => {
		<c:forEach var="item" items="${requestScope.blogImageJoinImageList}">
			if(document.querySelector(".blogID-V2-${pageScope.item.blogImage.blogID}") != null){
				let imgTag = document.querySelector(".blogID-V2-${pageScope.item.blogImage.blogID}");
				imgTag.src = "${fn:escapeXml(pageScope.item.image.link) }";
			}
		</c:forEach>
		
		<c:forEach var="item" items="${requestScope.commentOfBlogCountList}">
			if(document.querySelector(".blogID-V3-${pageScope.item.blogID}") != null){
				let spanTag = document.querySelector(".blogID-V3-${pageScope.item.blogID}");
				spanTag.innerText = "${pageScope.item.count }";
			}
			if(document.querySelector(".blogID-V4-${pageScope.item.blogID}") != null){
				let spanTag = document.querySelector(".blogID-V4-${pageScope.item.blogID}");
				spanTag.innerText = "${pageScope.item.count }";
			}
		</c:forEach>
	});
</script>