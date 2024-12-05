<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="Utilities.SharedMethod" %>

<div class="mt-producttabs wow fadeInUp" data-wow-delay="0.4s">
	<!-- producttabs start here -->
	<ul class="producttabs">
		<li><a href="#tab1" class="active">FEATURED</a></li>
		<li><a href="#tab2">LATEST</a></li>
		<li><a href="#tab3">HOT SALE</a></li>
	</ul>
	<!-- producttabs end here -->
	<div class="tab-content text-center">
		<div id="tab1">
			<!-- tabs slider start here -->
			<div class="tabs-slider">
				<c:forEach var="item" items="${requestScope.featuredProductList }">
					<!-- slide start here -->
					<div class="slide">
						<!-- mt product1 center start here -->
						<div class="mt-product1 mt-paddingbottom20">
							<div class="box">
								<div class="b1">
									<div class="b2">
										<c:url var="url" value="/ProductDetailPageServlet" scope="page">
											<c:param name="productID" value="${pageScope.item.productID }"></c:param>
										</c:url>
										<a href="${pageScope.url }">
											<img class="productID-V3-${pageScope.item.productID }" src="#" alt="image description">
										</a>
										<c:if test="${pageScope.item.discount > 0 || pageScope.item.isNew == 1 }">
											<span class="caption">
												<c:if test="${pageScope.item.discount > 0 }">
													<span class="off">${pageScope.item.discount }% Off</span>
												</c:if>
												<c:if test="${pageScope.item.isNew == 1 }">
													<span class="new">New</span>
												</c:if>
											</span>
											
										</c:if>
										<ul class="mt-stars productID-V4-${pageScope.item.productID }" style="display: none;">
											<li><i class="fa fa-star-o"></i></li>
											<li><i class="fa fa-star-o"></i></li>
											<li><i class="fa fa-star-o"></i></li>
											<li><i class="fa fa-star-o"></i></li>
										</ul>
										<ul class="links">
											<li class="buttonAddToCart productID-V50-${pageScope.item.productID }"><a><i class="icon-handbag"></i><span>Add to Cart</span></a></li>
											<li class="buttonAddToWishList productID-V50-${pageScope.item.productID }"><a><i class="icomoon icon-heart-empty"></i></a></li>
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
					</div>
					<!-- slide end here -->
				</c:forEach>
				
			</div>
			<!-- tabs slider end here -->
		</div>
		<div id="tab2">
			<!-- tabs slider start here -->
			<div class="tabs-slider">
				<c:forEach var="item" items="${requestScope.latestProductList }">
					<!-- slide start here -->
					<div class="slide">
						<!-- mt product1 center start here -->
						<div class="mt-product1 mt-paddingbottom20">
							<div class="box">
								<div class="b1">
									<div class="b2">
										<c:url var="url" value="/ProductDetailPageServlet" scope="page">
											<c:param name="productID" value="${pageScope.item.productID }"></c:param>
										</c:url>
										<a href="${pageScope.url }">
											<img class="productID-V5-${pageScope.item.productID }" src="#" alt="image description">
										</a>
										<c:if test="${pageScope.item.discount > 0 || pageScope.item.isNew == 1 }">
											<span class="caption">
												<c:if test="${pageScope.item.discount > 0 }">
													<span class="off">${pageScope.item.discount }% Off</span>
												</c:if>
												<c:if test="${pageScope.item.isNew == 1 }">
													<span class="new">New</span>
												</c:if>
											</span>
											
										</c:if>
										<ul class="mt-stars productID-V6-${pageScope.item.productID }" style="display: none;">
											<li><i class="fa fa-star-o"></i></li>
											<li><i class="fa fa-star-o"></i></li>
											<li><i class="fa fa-star-o"></i></li>
											<li><i class="fa fa-star-o"></i></li>
										</ul>
										<ul class="links">
											<li class="buttonAddToCart productID-V50-${pageScope.item.productID }"><a><i class="icon-handbag"></i><span>Add to Cart</span></a></li>
											<li class="buttonAddToWishList productID-V50-${pageScope.item.productID }"><a><i class="icomoon icon-heart-empty"></i></a></li>
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
					</div>
					<!-- slide end here -->
				</c:forEach>
				
			</div>
			<!-- tabs slider end here -->
		</div>
		<div id="tab3">
			<!-- tabs slider start here -->
			<div class="tabs-slider">
				<c:forEach var="item" items="${requestScope.hotSaleProductList }">
					<!-- slide start here -->
					<div class="slide">
						<!-- mt product1 center start here -->
						<div class="mt-product1 mt-paddingbottom20">
							<div class="box">
								<div class="b1">
									<div class="b2">
										<c:url var="url" value="/ProductDetailPageServlet" scope="page">
											<c:param name="productID" value="${pageScope.item.productID }"></c:param>
										</c:url>
										<a href="${pageScope.url }">
											<img class="productID-V7-${pageScope.item.productID }" src="#" alt="image description">
										</a>
										<c:if test="${pageScope.item.discount > 0 || pageScope.item.isNew == 1 }">
											<span class="caption">
												<c:if test="${pageScope.item.discount > 0 }">
													<span class="off">${pageScope.item.discount }% Off</span>
												</c:if>
												<c:if test="${pageScope.item.isNew == 1 }">
													<span class="new">New</span>
												</c:if>
											</span>
											
										</c:if>
										<ul class="mt-stars productID-V8-${pageScope.item.productID }" style="display: none;">
											<li><i class="fa fa-star-o"></i></li>
											<li><i class="fa fa-star-o"></i></li>
											<li><i class="fa fa-star-o"></i></li>
											<li><i class="fa fa-star-o"></i></li>
										</ul>
										<ul class="links">
											<li class="buttonAddToCart productID-V50-${pageScope.item.productID }"><a><i class="icon-handbag"></i><span>Add to Cart</span></a></li>
											<li class="buttonAddToWishList productID-V50-${pageScope.item.productID }"><a><i class="icomoon icon-heart-empty"></i></a></li>
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
					</div>
					<!-- slide end here -->
				</c:forEach>
				
			</div>
			<!-- tabs slider end here -->
		</div>
	</div>
</div>

<script>
	<c:forEach var="item" items="${requestScope.productImageJoinImageList }">
		if(document.querySelector(".productID-V3-${pageScope.item.productImage.productID }") != null){
			let imgTag = document.querySelector(".productID-V3-${pageScope.item.productImage.productID }");
			imgTag.src = "${fn:escapeXml(pageScope.item.image.link) }";
		}
		if(document.querySelector(".productID-V5-${pageScope.item.productImage.productID }") != null){
			let imgTag = document.querySelector(".productID-V5-${pageScope.item.productImage.productID }");
			imgTag.src = "${fn:escapeXml(pageScope.item.image.link) }";
		}
		if(document.querySelector(".productID-V7-${pageScope.item.productImage.productID }") != null){
			let imgTag = document.querySelector(".productID-V7-${pageScope.item.productImage.productID }");
			imgTag.src = "${fn:escapeXml(pageScope.item.image.link) }";
		}
	</c:forEach>
	
	<c:forEach var="item" items="${requestScope.voteOfReviewAVGList }">
		if(document.querySelector(".productID-V4-${pageScope.item.productID }") != null){
			let ulTag = document.querySelector(".productID-V4-${pageScope.item.productID }");
			ulTag.style.display = "block";
			for(let i = 0; i < ${pageScope.item.vote }; i++)
				ulTag.children[i].children[0].classList.replace("fa-star-o", "fa-star");
		}
		if(document.querySelector(".productID-V6-${pageScope.item.productID }") != null){
			let ulTag = document.querySelector(".productID-V6-${pageScope.item.productID }");
			ulTag.style.display = "block";
			for(let i = 0; i < ${pageScope.item.vote }; i++)
				ulTag.children[i].children[0].classList.replace("fa-star-o", "fa-star");
		}
		if(document.querySelector(".productID-V8-${pageScope.item.productID }") != null){
			let ulTag = document.querySelector(".productID-V8-${pageScope.item.productID }");
			ulTag.style.display = "block";
			for(let i = 0; i < ${pageScope.item.vote }; i++)
				ulTag.children[i].children[0].classList.replace("fa-star-o", "fa-star");
		}
	</c:forEach>
</script>