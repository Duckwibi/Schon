<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="Utilities.SharedMethod" %>

<div class="banner-frame">
	<c:forEach var="item" items="${requestScope.bannerList }">
		<c:url var="url" value="/ProductDetailPageServlet" scope="page">
			<c:param name="productID" value="${pageScope.item.productID }"></c:param>
		</c:url>
		<c:choose>
			<c:when test="${pageScope.item.discount > 0 }">
				<!-- banner-1 start here -->
				<div class="banner-1 wow fadeInLeft" data-wow-delay="0.4s">
					<img class="productID-V2-${pageScope.item.productID }" alt="image description" src="#">
					<div class="holder">
						<h2>${fn:escapeXml(pageScope.item.name.toUpperCase()) }</h2>
						<div class="txts">
							<a class="btn-shop" href="${pageScope.url }"> 
								<span>shop now</span> 
								<i class="fa fa-angle-right"></i>
							</a>
							<div class="discount">
								<span>-${pageScope.item.discount }%</span>
							</div>
						</div>
					</div>
				</div>
				<!-- banner-1 end here -->
			</c:when>
			<c:otherwise>
				<!-- banner-4 start here -->
				<div class="banner-4 hidden-sm wow fadeInRight"
					data-wow-delay="0.4s">
					<img class="productID-V2-${pageScope.item.productID }" alt="image description" src="#">
					<div class="holder">
						<h2>${fn:escapeXml(pageScope.item.name.toUpperCase()) }</h2>
						<span class="price">$ ${SharedMethod.moneyFormat(pageScope.item.price) }</span>
						<a class="btn-shop add" href="${pageScope.url }"> 
							<span>shop now</span> 
							<i class="fa fa-angle-right"></i>
						</a>
					</div>
				</div>
				<!-- banner-4 end here -->
			</c:otherwise>
		</c:choose>
	</c:forEach>
	
</div>
<!-- banner frame end here -->
<script>
	window.addEventListener("load", () => {
		<c:forEach var="item" items="${requestScope.bannerImageList }">
			if(document.querySelector(".productID-V2-${pageScope.item.productImage.productID }") != null){
				let imgTag = document.querySelector(".productID-V2-${pageScope.item.productImage.productID }");
				imgTag.src = "${fn:escapeXml(pageScope.item.image.link) }";
			}
		</c:forEach>
	});
	
</script>