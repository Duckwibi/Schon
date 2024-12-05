<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="Utilities.SharedMethod" %>

<div class="mt-product-table wow fadeInUp" data-wow-delay="0.4s">
	<div class="container">
		<div class="row border">
			<div class="col-xs-12 col-sm-6">
				<strong class="title">PRODUCT</strong>
			</div>
			<div class="col-xs-12 col-sm-2">
				<strong class="title">PRICE</strong>
			</div>
			<div class="col-xs-12 col-sm-2">
				<strong class="title">QUANTITY</strong>
			</div>
			<div class="col-xs-12 col-sm-2">
				<strong class="title">TOTAL</strong>
			</div>
		</div>
		<c:forEach var="item" items="${requestScope.cartJoinProductList }">
			<c:set var="c" value="${pageScope.item.cart }" scope="page"></c:set>
			<c:set var="p" value="${pageScope.item.product }"></c:set>
			
			<div class="row border">
				<div class="col-xs-12 col-sm-2">
					<div class="img-holder">
						<img class="productID-V1-${pageScope.c.productID }" src="#" alt="image description">
					</div>
				</div>
				<div class="col-xs-12 col-sm-4">
					<strong class="product-name">${fn:escapeXml(pageScope.p.name) }</strong>
				</div>
				<div class="col-xs-12 col-sm-2">
					<strong class="price">
						<i class="fa fa-dollar"></i>
						${SharedMethod.moneyFormat(pageScope.p.price - pageScope.p.price * pageScope.p.discount / 100) }
					</strong>
				</div>
				<div class="col-xs-12 col-sm-2">
					<form onsubmit="return false;" class="qyt-form">
						<fieldset>
							<select class="inputQuantity productID-V2-${pageScope.c.productID }">
								<c:forEach var="i" begin="1" end="10">
									<option value="${pageScope.i }">${pageScope.i }</option>
								</c:forEach>
							</select>
						</fieldset>
						<script>
							window.addEventListener("load", () => {
								let selectTag = document.querySelector(".productID-V2-${pageScope.c.productID }");
								selectTag.value = "${pageScope.c.quantity}";
							});
						</script>
					</form>
				</div>
				<div class="col-xs-12 col-sm-2">
					<strong class="price">
						<i class="fa fa-dollar"></i> 
						<span class="productID-V3-${pageScope.c.productID }">
							${SharedMethod.moneyFormat((pageScope.p.price - pageScope.p.price * pageScope.p.discount / 100) * pageScope.c.quantity) }
						</span>
					</strong>
					<a><i class="fa fa-close buttonDelete productID-V4-${pageScope.c.productID }"></i></a>
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
				imgTag.src = "${fn:escapeXml(pageScope.item.image.link) }";
			}
		</c:forEach>
	});
</script>