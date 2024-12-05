<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="Utilities.SharedMethod" %>
    
<div class="related-products wow fadeInUp" data-wow-delay="0.4s">
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<h2>RELATED PRODUCTS</h2>
				<div class="row">
					<div class="col-xs-12">
						<c:forEach var="item" items="${requestScope.productList }">
							<div class="mt-product1 mt-paddingbottom20">
								<div class="box">
									<div class="b1">
										<div class="b2">
											<c:url var="url" value="/ProductDetailPageServlet" scope="page">
												<c:param name="productID" value="${pageScope.item.productID }"></c:param>
											</c:url> 
											<a href="${pageScope.url }"> 
												<img class="productID-V1-${pageScope.item.productID }" src="#" alt="image description">
											</a>
											<c:if test="${pageScope.item.discount > 0 && pageScope.item.isNew == 1 }">
												<span class="caption"> 
													<c:if test="${pageScope.item.discount > 0 }">
														<span class="off">${pageScope.item.discount }% Off</span>
													</c:if> 
													<c:if test="${pageScope.item.isNew == 1 }">
														<span class="new">NEW</span>
													</c:if>
												</span>
											</c:if>
											<ul class="mt-stars productID-V2-${pageScope.item.productID }" style="display: none">
												<li><i class="fa fa-star-o"></i></li>
												<li><i class="fa fa-star-o"></i></li>
												<li><i class="fa fa-star-o"></i></li>
												<li><i class="fa fa-star-o"></i></li>
											</ul>
											<ul class="links">
												<li class="buttonAddToCart productID-V3-${pageScope.item.productID }">
													<a> 
														<i class="icon-handbag"></i>
														<span>Add to Cart</span>
													</a>
												</li>
												<li class="buttonAddToWishList productID-V4-${pageScope.item.productID }">
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
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- related products end here -->
	
</div>
<script>
	window.addEventListener("load", () => {
		<c:forEach var="item" items="${requestScope.productImageJoinImageList }">
			if(document.querySelector(".productID-V1-${pageScope.item.productImage.productID}") != null){
				let imgTag = document.querySelector(".productID-V1-${pageScope.item.productImage.productID}");
				imgTag.src = "${fn:escapeXml(pageScope.item.image.link)}";
			}
		</c:forEach>
		
		<c:forEach var="item" items="${requestScope.voteOfReviewAVGList }">
			if(document.querySelector(".productID-V2-${pageScope.item.productID}") != null){
				let ulTag = document.querySelector(".productID-V2-${pageScope.item.productID}");
				ulTag.style.display = "block";
				for(let i = 0; i < ${pageScope.item.vote}; i++)
					ulTag.children[i].children[0].classList.replace("fa-star-o", "fa-star");
			}
		</c:forEach>
	});
</script>