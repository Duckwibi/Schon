<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="Utilities.SharedMethod" %>

<c:set var="p" value="${requestScope.productJoinProductCategory.product }" scope="page"></c:set>
<c:set var="pc" value="${requestScope.productJoinProductCategory.productCategory }" scope="page"></c:set>

<section class="mt-product-detial wow fadeInUp" data-wow-delay="0.4s">
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<!-- Slider of the Page -->
				<div class="slider">
					<!-- Comment List of the Page -->
					<ul class="list-unstyled comment-list">
						<li><a><i class="fa fa-heart"></i><span class="currentProductWishListCount">${requestScope.wishListCountOfProduct }</span></a></li>
						<li><a><i class="fa fa-comments"></i><span class="reviewCount">${requestScope.reviewCount }</span></a></li>
					</ul>
					<!-- Comment List of the Page end -->
					<!-- Product Slider of the Page -->
					<div class="product-slider">
						<c:forEach var="item" items="${requestScope.productImageLevel4List }">
							<div class="slide">
								<img src="${fn:escapeXml(pageScope.item.image.link) }" alt="image descrption">
							</div>
						</c:forEach>
					</div>
					<!-- Product Slider of the Page end -->
					<!-- Pagg Slider of the Page -->
					<ul class="list-unstyled slick-slider pagg-slider">
						<c:forEach var="item" items="${requestScope.productImageLevel5List }">
							<li>
								<div class="img">
									<img src="${fn:escapeXml(pageScope.item.image.link) }" alt="image description">
								</div>
							</li>
						</c:forEach>
					</ul>
					<!-- Pagg Slider of the Page end -->
				</div>
				<!-- Slider of the Page end -->
				<!-- Detail Holder of the Page -->
				<div class="detial-holder">
					<!-- Breadcrumbs of the Page -->
					<ul class="list-unstyled breadcrumbs">
						<li>
							<c:url var="url" value="/ProductPageServlet" scope="page">
								<c:param name="productCategoryID" value="${pageScope.pc.productCategoryID }"></c:param>
							</c:url>
							<a href="${pageScope.url }">${fn:escapeXml(pageScope.pc.name) } 
								<i class="fa fa-angle-right"></i>
							</a>
						</li>
						<li>${fn:escapeXml(pageScope.p.name) }</li>
					</ul>
					<!-- Breadcrumbs of the Page end -->
					<h2>${fn:escapeXml(pageScope.p.name.toUpperCase()) }</h2>
					<!-- Rank Rating of the Page -->
					<div class="rank-rating">
						<ul class="list-unstyled rating-list voteAVG">
							<c:forEach var="item" begin="1" end="4">
								<c:choose>
									<c:when test="${pageScope.item <= requestScope.voteAVG }">
										<li><a><i class="fa fa-star"></i></a></li>
									</c:when>
									<c:otherwise>
										<li><a><i class="fa fa-star-o"></i></a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</ul>
						<span class="total-price">Reviews (<span class="reviewCount">${requestScope.reviewCount }</span>)</span>
					</div>
					<!-- Rank Rating of the Page end -->
					<ul class="list-unstyled list">
						<li><a><i class="fa fa-share-alt"></i>SHARE</a></li>
						<li><a class="buttonSubmitAddToWishList"><i class="fa fa-heart"></i>ADD TO WISHLIST</a></li>
					</ul>
					<div class="txt-wrap">
						<p>${fn:escapeXml(pageScope.p.description) }</p>
					</div>
					<div class="text-holder">
						<span class="price">$ ${SharedMethod.moneyFormat(pageScope.p.price - pageScope.p.price * pageScope.p.discount / 100) } 
						<del>${SharedMethod.moneyFormat(pageScope.p.price) }</del></span>
					</div>
					<!-- Product Form of the Page -->
					<form onsubmit="return false;" class="product-form">
						<fieldset>
							<div class="row-val">
								<label for="qty">qty</label> 
								<input type="number" id="qty" value="1" class="inputQuantity">
							</div>
							<div class="row-val">
								<button class="buttonSubmitAddToCart" type="submit">ADD TO CART</button>
							</div>
						</fieldset>
					</form>
					<!-- Product Form of the Page end -->
				</div>
				<!-- Detail Holder of the Page end -->
			</div>
		</div>
	</div>
</section>
