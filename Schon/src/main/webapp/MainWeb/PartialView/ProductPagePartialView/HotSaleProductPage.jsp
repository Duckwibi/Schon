<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="Utilities.SharedMethod" %>

<section class="shop-widget">
	<h2>HOT SALE</h2>
	<!-- mt product4 start here -->
	<c:forEach var="item" items="${requestScope.hotSaleProductList }">
		<div class="mt-product4 mt-paddingbottom20">
			<div class="img">
				<c:url var="url" value="/ProductDetailPageServlet" scope="page">
					<c:param name="productID" value="${pageScope.item.productID }"></c:param>
				</c:url>
				<a href="${pageScope.url }">
					<img class="productID-V3-${pageScope.item.productID }" src="#" alt="image description">
				</a>
			</div>
			<div class="text">
				<div class="frame">
					<strong><a href="${pageScope.url }">${fn:escapeXml(pageScope.item.name) }</a></strong>
					<ul class="mt-stars productID-V4-${pageScope.item.productID }" style="display: none;">
						<li><i class="fa fa-star-o"></i></li>
						<li><i class="fa fa-star-o"></i></li>
						<li><i class="fa fa-star-o"></i></li>
						<li><i class="fa fa-star-o"></i></li>
					</ul>
				</div>
				<del class="off">$${SharedMethod.moneyFormat(pageScope.item.price) }</del>
				<span class="price">$${SharedMethod.moneyFormat(pageScope.item.price - pageScope.item.price * pageScope.item.discount / 100) }</span>
			</div>
		</div>
	</c:forEach>
	<!-- mt product4 end here -->
</section>
<c:forEach var="item" items="${requestScope.productImageJoinImageList }"></c:forEach>
<script>
	window.addEventListener("load", () => {
		<c:forEach var="item" items="${requestScope.productImageJoinImageList }">
			if(document.querySelector(".productID-V3-${pageScope.item.productImage.productID }") != null){
				let imgTag = document.querySelector(".productID-V3-${pageScope.item.productImage.productID }");
				imgTag.src = "${fn:escapeXml(pageScope.item.image.link)}";
			}
		</c:forEach>
	
		<c:forEach var="item" items="${requestScope.voteOfReviewAVGList }">
			if(document.querySelector(".productID-V4-${pageScope.item.productID }") != null){
				let ulTag = document.querySelector(".productID-V4-${pageScope.item.productID }");
				ulTag.style.display = "block";
				for(let i = 0; i < ${pageScope.item.vote}; i++)
					ulTag.children[i].children[0].classList.replace("fa-star-o", "fa-star");
			}
		</c:forEach>
	});
</script>