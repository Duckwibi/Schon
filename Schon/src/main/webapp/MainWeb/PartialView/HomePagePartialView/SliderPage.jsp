<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="mt-main-slider">
	<!-- slider banner-slider start here -->
	<div class="slider banner-slider">
		<c:forEach var="item" items="${requestScope.sliderList }">
			<c:set var="p" value="${pageScope.item.product }"></c:set>
			<c:set var="pc" value="${pageScope.item.productCategory }" ></c:set>
			<!-- holder start here -->
			<div class="holder text-center productID-V1-${pageScope.p.productID }" style="background-image: url(#);">
				<div class="container">
					<div class="row">
						<div class="col-xs-12">
							<div class="text right">
								<strong class="title">${fn:escapeXml(pageScope.p.sliderTitle) }</strong>
								<h2 style="color:black">${fn:escapeXml(pageScope.pc.name.toUpperCase()) }</h2>
								<h2>${fn:escapeXml(pageScope.p.name) }</h2>
								<div class="txt">
									<p>${fn:escapeXml(pageScope.p.sliderDescription) }</p>
								</div>
								<c:url var="url" value="/ProductDetailPageServlet" scope="page">
									<c:param name="productID" value="${pageScope.p.productID }"></c:param>
								</c:url>
								<a href="${pageScope.url }" class="shop">shop now</a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- holder end here -->
		</c:forEach>
		
	</div>
	<!-- slider regular end here -->
</div>

<script>
	<c:forEach var="item" items="${requestScope.sliderImageList }">
		if(document.querySelector(".productID-V1-${pageScope.item.productImage.productID }") != null){
			let divTag = document.querySelector(".productID-V1-${pageScope.item.productImage.productID }");
			divTag.style.backgroundImage = "url(${fn:escapeXml(pageScope.item.image.link) })";
		}
	</c:forEach>
</script>