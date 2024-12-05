<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="Utilities.SharedMethod" %>

<div class="mt-bestseller bg-grey text-center wow fadeInUp" data-wow-delay="0.4s">
	<div class="container">
		<div class="row">
			<div class="col-xs-12 mt-heading text-uppercase">
				<h2 class="heading">BEST SELLER</h2>
				<p>EXCEPTEUR SINT OCCAECAT</p>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<div class="bestseller-slider">
					<c:forEach var="item" items="${requestScope.bestSellerProductList }">
						<c:set var = "p" value="${pageScope.item.product }" scope="page"></c:set>
						<div class="slide">
							<!-- mt product1 center start here -->
							<div class="mt-product1 large">
								<div class="box">
									<div class="b1">
										<div class="b2">
											<c:url var="url" value="/ProductDetailPageServlet" scope="page">
												<c:param name="productID" value="${pageScope.p.productID }"></c:param>
											</c:url>
											<a href="${pageScope.url }">
												<img class="productID-V9-${pageScope.p.productID }" src="#" alt="image description">
											</a>
											<c:if test="${pageScope.p.discount > 0 }">
												<span class="caption"> 
													<span class="best-price">Best Price</span>
												</span>
											</c:if>
											<ul class="links add" style="width: 40%">
												<li class="buttonAddToCart productID-V50-${pageScope.p.productID }"><a><i class="icon-handbag"></i></a></li>
												<li class="buttonAddToWishList productID-V50-${pageScope.p.productID }"><a><i class="icomoon icon-heart-empty"></i></a></li>
											</ul>
										</div>
									</div>
								</div>
								<div class="txt">
									<strong class="title">
										<a href="product-detail.html">${fn:escapeXml(pageScope.p.name) }</a>
									</strong> 
									<span class="price">
										<i class="fa fa-dollar"></i>
										<span>${SharedMethod.moneyFormat(pageScope.p.price) }</span>
									</span>
								</div>
							</div>
							<!-- mt product1 center end here -->
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
	<c:forEach var="item" items="${requestScope.productImageJoinImageList }">
		if(document.querySelector(".productID-V9-${pageScope.item.productImage.productID }") != null){
			let imgTag = document.querySelector(".productID-V9-${pageScope.item.productImage.productID }");
			imgTag.src = "${fn:escapeXml(pageScope.item.image.link) }";
		}
	</c:forEach>	
</script>