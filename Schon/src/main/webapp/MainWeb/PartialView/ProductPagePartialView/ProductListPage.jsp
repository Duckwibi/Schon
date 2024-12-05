<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="Utilities.SharedMethod" %>

<ul class="mt-productlisthold list-inline">
	<c:forEach var="item" items="${requestScope.productList }">
		<li>
			<!-- mt product1 large start here -->
			<div class="mt-product1 large">
				<div class="box">
					<div class="b1">
						<div class="b2">
							<c:url var="url" value="/ProductDetailPageServlet" scope="page">
								<c:param name="productID" value="${pageScope.item.productID }"></c:param>
							</c:url>
							<a href="${pageScope.url }">
								<img class="productID-V1-${pageScope.item.productID }" src="#" alt="image description">
							</a>
							<c:if test="${pageScope.item.discount > 0 }">
								<span class="caption">
									<span class="off">${pageScope.item.discount }% Off</span>
								</span>
							</c:if>
							<ul class="mt-stars productID-V2-${pageScope.item.productID }" style="display: none;">
								<li><i class="fa fa-star-o"></i></li>
								<li><i class="fa fa-star-o"></i></li>
								<li><i class="fa fa-star-o"></i></li>
								<li><i class="fa fa-star-o"></i></li>
							</ul>
							<ul class="links">
								<li class="buttonAddToCart productID-V5-${pageScope.item.productID }">
									<a> 
										<i class="icon-handbag"></i> 
										<span>Add to Cart</span>
									</a>
								</li>
								<li class="buttonAddToWishList productID-V6-${pageScope.item.productID }">
									<a> 
										<i class="icomoon icon-heart-empty"></i>
									</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="txt">
					<strong class="title">
						<a href="${pageScope.url }">${fn:escapeXml(pageScope.item.name) }</a>
					</strong> 
					<span class="price">
						<i class="fa fa-dollar"></i>
						<span>${SharedMethod.moneyFormat(pageScope.item.price) }</span>
					</span>
				</div>
			</div>
			<!-- mt product1 center end here -->
		</li>
	</c:forEach>
</ul>

<script>
	window.addEventListener("load", () => {
		<c:forEach var="item" items="${requestScope.productImageJoinImageList }">
			if(document.querySelector(".productID-V1-${pageScope.item.productImage.productID }") != null){
				let imgTag = document.querySelector(".productID-V1-${pageScope.item.productImage.productID }");
				imgTag.src = "${fn:escapeXml(pageScope.item.image.link) }";
			}
		</c:forEach>
	
		<c:forEach var="item" items="${requestScope.voteOfReviewAVGList }">
			if(document.querySelector(".productID-V2-${pageScope.item.productID }") != null){
				let ulTag = document.querySelector(".productID-V2-${pageScope.item.productID }");
				ulTag.style.display = "block";
				for(let i = 0; i < ${pageScope.item.vote}; i++)
					ulTag.children[i].children[0].classList.replace("fa-star-o", "fa-star");
			}
		</c:forEach>
	});
</script>