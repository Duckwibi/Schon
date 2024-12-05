<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="Utilities.SharedMethod" %>


<div class="mt-product-table wow fadeInUp" data-wow-delay="0.4s">
	<div class="container">
		<c:forEach var="item" items="${requestScope.wishListListJoinProductList }">
			<c:set var="w" value="${pageScope.item.wishList }" scope="page"></c:set>
			<c:set var="p" value="${pageScope.item.product }" scope="page"></c:set>
			<div class="row border">
				<div class="col-xs-12 col-sm-2">
					<div class="img-holder">
						<img class="productID-V1-${pageScope.w.productID }" src="#" alt="image description">
					</div>
				</div>
				<div class="col-xs-12 col-sm-5">
					<strong class="product-name">${fn:escapeXml(pageScope.p.name) }</strong>
				</div>
				<div class="col-xs-12 col-sm-2">
					<strong class="product-name">
						<i class="fa fa-dollar"></i>
						${SharedMethod.moneyFormat(pageScope.p.price - pageScope.p.price * pageScope.p.discount / 100) }
					</strong>
				</div>
				<div class="col-xs-12 col-sm-2">
					<form onsubmit="return false" class="coupon-form">
						<fieldset>
							<button type="submit" style="margin-top: 15px;" class="buttonApply productID-V2-${pageScope.w.productID }">APPLY</button>
						</fieldset>
					</form>
				</div>
				<div class="col-xs-12 col-sm-1">
					<a><i class="fa fa-close buttonDelete productID-V3-${pageScope.w.productID }"></i></a>
				</div>
			</div>
		</c:forEach>
	</div>
</div>

<script>
	window.addEventListener("load", () => {
		<c:forEach var="item" items="${requestScope.productImageJoinImageList }">
			if(document.querySelector(".productID-V1-${pageScope.item.productImage.productID }") != null){
				let imgTag = document.querySelector(".productID-V1-${pageScope.item.productImage.productID }");
				imgTag.src = "${fn:escapeXml(pageScope.item.image.link)}";
			}
		</c:forEach>
	});
</script>
