<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="Utilities.SharedMethod" %>
    
<div class="product-detail-tab wow fadeInUp" data-wow-delay="0.4s">
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<ul class="mt-tabs text-center text-uppercase">
					<li><a href="#tab1" class="firstClick">INFORMATION</a></li>
					<li><a href="#tab2">REVIEWS (<span class="reviewCount">${requestScope.reviewCount }</span>)</a></li>
				</ul>
				<div class="tab-content">
					<div id="tab1">
						<p>${fn:escapeXml(pageScope.p.infomation) }</p>
					</div>
					<div id="tab2">
						<div class="product-comment">
							<c:forEach var="item" items="${requestScope.reviewJoinCustomerList }">
								<div class="mt-box">
									<div class="mt-hold">
										<ul class="mt-star">
											<c:forEach var="i" begin="1" end="4">
												<c:choose>
													<c:when test="${pageScope.i <= pageScope.item.review.vote }">
														<li><i class="fa fa-star"></i></li>
													</c:when>
													<c:otherwise>
														<li><i class="fa fa-star-o"></i></li>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</ul>
										<span class="name">
											${fn:escapeXml(pageScope.item.customer.firstName) }
											${fn:escapeXml(pageScope.item.customer.lastName) }
										</span>
										<time>${SharedMethod.dateFormat("MMM dd, yyyy, hh:mm:ssaa", pageScope.item.review.createdDate) }</time>
									</div>
									<p>${fn:escapeXml(pageScope.item.review.message) }</p>
								</div>
							</c:forEach>
							<form onsubmit="return false;" class="p-commentform">
								<fieldset>
									<h2>Add Comment</h2>
									<div class="mt-row">
										<label>Rating</label>
										<ul class="mt-star inputReview-Vote">
											<li><i class="fa fa-star"></i></li>
											<li><i class="fa fa-star-o"></i></li>
											<li><i class="fa fa-star-o"></i></li>
											<li><i class="fa fa-star-o"></i></li>
										</ul>
									</div>
									<div class="mt-row">
										<label>Review</label>
										<textarea class="form-control inputReview-Message"></textarea>
									</div>
									<button type="submit" class="btn-type4 buttonSubmitReview">ADD REVIEW</button>
								</fieldset>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	window.addEventListener("load", () => {
		document.querySelector(".firstClick").click();
	});
</script>