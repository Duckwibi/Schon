<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="Utilities.SharedMethod" %>

<div class="mt-smallproducts wow fadeInUp" data-wow-delay="0.4s">
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-6 col-md-3 mt-paddingbottomsm">
				<h3 class="heading">Featured Products</h3>
				<c:forEach var="item" items="${requestScope.top3FeaturedProductList }">
					<!-- mt product4 start here -->
					<div class="mt-product4 mt-paddingbottom20">
						<div class="img">
							<c:url var="url" value="/ProductDetailPageServlet" scope="page">
								<c:param name="productID" value="${pageScope.item.productID }"></c:param>
							</c:url>
							<a href="${pageScope.url }">
								<img class="productID-V10-${pageScope.item.productID }" alt="image description" src="#">
							</a>
						</div>
						<div class="text">
							<div class="frame">
								<strong>
									<a href="${pageScope.url }">${fn:escapeXml(pageScope.item.name) }</a>
								</strong>
								<ul class="mt-stars productID-V11-${pageScope.item.productID }" style="display: none;">
									<li><i class="fa fa-star-o"></i></li>
									<li><i class="fa fa-star-o"></i></li>
									<li><i class="fa fa-star-o"></i></li>
									<li><i class="fa fa-star-o"></i></li>
								</ul>
							</div>
							<c:if test="${pageScope.item.discount > 0 }">
								<del class="off">$${SharedMethod.moneyFormat(pageScope.item.price) }</del>
							</c:if>
							<span class="price">$${SharedMethod.moneyFormat(pageScope.item.price - pageScope.item.price * pageScope.item.discount / 100) }</span>
						</div>
					</div>
					<!-- mt product4 end here -->
				</c:forEach>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-3 mt-paddingbottomsm">
				<h3 class="heading">Latest Products</h3>
				<c:forEach var="item" items="${requestScope.top3LatestProductList }">
					<!-- mt product4 start here -->
					<div class="mt-product4 mt-paddingbottom20">
						<div class="img">
							<c:url var="url" value="/ProductDetailPageServlet" scope="page">
								<c:param name="productID" value="${pageScope.item.productID }"></c:param>
							</c:url>
							<a href="${pageScope.url }">
								<img class="productID-V12-${pageScope.item.productID }" alt="image description" src="#">
							</a>
						</div>
						<div class="text">
							<div class="frame">
								<strong>
									<a href="${pageScope.url }">${fn:escapeXml(pageScope.item.name) }</a>
								</strong>
								<ul class="mt-stars productID-V13-${pageScope.item.productID }" style="display: none;">
									<li><i class="fa fa-star-o"></i></li>
									<li><i class="fa fa-star-o"></i></li>
									<li><i class="fa fa-star-o"></i></li>
									<li><i class="fa fa-star-o"></i></li>
								</ul>
							</div>
							<c:if test="${pageScope.item.discount > 0 }">
								<del class="off">$${SharedMethod.moneyFormat(pageScope.item.price) }</del>
							</c:if>
							<span class="price">$${SharedMethod.moneyFormat(pageScope.item.price - pageScope.item.price * pageScope.item.discount / 100) }</span>
						</div>
					</div>
					<!-- mt product4 end here -->
				</c:forEach>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-3 mt-paddingbottomxs">
				<h3 class="heading">Hot Sale Products</h3>
				<c:forEach var="item" items="${requestScope.top3HotSaleProductList }">
					<!-- mt product4 start here -->
					<div class="mt-product4 mt-paddingbottom20">
						<div class="img">
							<c:url var="url" value="/ProductDetailPageServlet" scope="page">
								<c:param name="productID" value="${pageScope.item.productID }"></c:param>
							</c:url>
							<a href="${pageScope.url }">
								<img class="productID-V14-${pageScope.item.productID }" alt="image description" src="#">
							</a>
						</div>
						<div class="text">
							<div class="frame">
								<strong>
									<a href="${pageScope.url }">${fn:escapeXml(pageScope.item.name) }</a>
								</strong>
								<ul class="mt-stars productID-V15-${pageScope.item.productID }" style="display: none;">
									<li><i class="fa fa-star-o"></i></li>
									<li><i class="fa fa-star-o"></i></li>
									<li><i class="fa fa-star-o"></i></li>
									<li><i class="fa fa-star-o"></i></li>
								</ul>
							</div>
							<c:if test="${pageScope.item.discount > 0 }">
								<del class="off">$${SharedMethod.moneyFormat(pageScope.item.price) }</del>
							</c:if>
							<span class="price">$${SharedMethod.moneyFormat(pageScope.item.price - pageScope.item.price * pageScope.item.discount / 100) }</span>
						</div>
					</div>
					<!-- mt product4 end here -->
				</c:forEach>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-3">
				<h3 class="heading">Best Seller</h3>
				<c:forEach var="item" items="${requestScope.top3BestSellerProductList }">
					<c:set var="p" value="${pageScope.item.product }" scope="page"></c:set>
					<!-- mt product4 start here -->
					<div class="mt-product4 mt-paddingbottom20">
						<div class="img">
							<c:url var="url" value="/ProductDetailPageServlet" scope="page">
								<c:param name="productID" value="${pageScope.p.productID }"></c:param>
							</c:url>
							<a href="${pageScope.url }">
								<img class="productID-V16-${pageScope.p.productID }" alt="image description" src="#">
							</a>
						</div>
						<div class="text">
							<div class="frame">
								<strong>
									<a href="${pageScope.url }">${fn:escapeXml(pageScope.p.name) }</a>
								</strong>
								<ul class="mt-stars productID-V17-${pageScope.p.productID }" style="display: none;">
									<li><i class="fa fa-star-o"></i></li>
									<li><i class="fa fa-star-o"></i></li>
									<li><i class="fa fa-star-o"></i></li>
									<li><i class="fa fa-star-o"></i></li>
								</ul>
							</div>
							<c:if test="${pageScope.p.discount > 0 }">
								<del class="off">$${SharedMethod.moneyFormat(pageScope.p.price) }</del>
							</c:if>
							<span class="price">$${SharedMethod.moneyFormat(pageScope.p.price - pageScope.p.price * pageScope.p.discount / 100) }</span>
						</div>
					</div>
					<!-- mt product4 end here -->
				</c:forEach>
			</div>
		</div>
	</div>
</div>

<script>
	window.addEventListener("load", () => {
		<c:forEach var="item" items="${requestScope.productImageJoinImageList }">
			if(document.querySelector(".productID-V10-${pageScope.item.productImage.productID }") != null){
				let imgTag = document.querySelector(".productID-V10-${pageScope.item.productImage.productID }");
				imgTag.src = "${fn:escapeXml(pageScope.item.image.link) }";
			}
			if(document.querySelector(".productID-V12-${pageScope.item.productImage.productID }") != null){
				let imgTag = document.querySelector(".productID-V12-${pageScope.item.productImage.productID }");
				imgTag.src = "${fn:escapeXml(pageScope.item.image.link) }";
			}
			if(document.querySelector(".productID-V14-${pageScope.item.productImage.productID }") != null){
				let imgTag = document.querySelector(".productID-V14-${pageScope.item.productImage.productID }");
				imgTag.src = "${fn:escapeXml(pageScope.item.image.link) }";
			}
			if(document.querySelector(".productID-V16-${pageScope.item.productImage.productID }") != null){
				let imgTag = document.querySelector(".productID-V16-${pageScope.item.productImage.productID }");
				imgTag.src = "${fn:escapeXml(pageScope.item.image.link) }";
			}
		</c:forEach>
	
		<c:forEach var="item" items="${requestScope.voteOfReviewAVGList }">
			if(document.querySelector(".productID-V11-${pageScope.item.productID }") != null){
				let ulTag = document.querySelector(".productID-V11-${pageScope.item.productID }");
				ulTag.style.display = "block";
				for(let i = 0; i < ${pageScope.item.vote }; i++)
					ulTag.children[i].children[0].classList.replace("fa-star-o", "fa-star");
			}
			if(document.querySelector(".productID-V13-${pageScope.item.productID }") != null){
				let ulTag = document.querySelector(".productID-V13-${pageScope.item.productID }");
				ulTag.style.display = "block";
				for(let i = 0; i < ${pageScope.item.vote }; i++)
					ulTag.children[i].children[0].classList.replace("fa-star-o", "fa-star");
			}
			if(document.querySelector(".productID-V15-${pageScope.item.productID }") != null){
				let ulTag = document.querySelector(".productID-V15-${pageScope.item.productID }");
				ulTag.style.display = "block";
				for(let i = 0; i < ${pageScope.item.vote }; i++)
					ulTag.children[i].children[0].classList.replace("fa-star-o", "fa-star");
			}
			if(document.querySelector(".productID-V17-${pageScope.item.productID }") != null){
				let ulTag = document.querySelector(".productID-V17-${pageScope.item.productID }");
				ulTag.style.display = "block";
				for(let i = 0; i < ${pageScope.item.vote }; i++)
					ulTag.children[i].children[0].classList.replace("fa-star-o", "fa-star");
			}
		</c:forEach>
	});
</script>