<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="Utilities.SharedMethod" %>

<div class="col-xs-12 col-sm-6">
	<div class="holder">
		<h2>YOUR ORDER</h2>
		<ul class="list-unstyled block">
			<li>
				<div class="txt-holder">
					<div class="text-wrap pull-left">
						<strong class="title">PRODUCTS</strong>
						<c:forEach var="item" items="${requestScope.cartJoinProductList }">
							<c:set var="c" value="${pageScope.item.cart }"></c:set>
							<c:set var="p" value="${pageScope.item.product }"></c:set>
							
							<span>${fn:escapeXml(pageScope.p.name) } x${pageScope.c.quantity }</span>
						</c:forEach>
						
					</div>
					<div class="text-wrap txt text-right pull-right">
						<strong class="title">TOTALS</strong>
						<c:forEach var="item" items="${requestScope.cartJoinProductList }">
							<c:set var="c" value="${pageScope.item.cart }"></c:set>
							<c:set var="p" value="${pageScope.item.product }"></c:set>
							
							<span>
								<i class="fa fa-dollar"></i>
								${SharedMethod.moneyFormat((pageScope.p.price - pageScope.p.price * pageScope.p.discount / 100) * pageScope.c.quantity) }
							</span>
						</c:forEach>
					</div>
				</div>
			</li>
			<li>
				<div class="txt-holder">
					<strong class="title sub-title pull-left">CART SUBTOTAL</strong>
					<div class="txt pull-right">
						<span><i class="fa fa-dollar"></i> ${SharedMethod.moneyFormat(requestScope.subTotal) }</span>
					</div>
				</div>
			</li>
			<li>
				<div class="txt-holder">
					<strong class="title sub-title pull-left">SHIPPING</strong>
					<div class="txt pull-right">
						<span><i class="fa fa-dollar"></i> ${SharedMethod.moneyFormat(requestScope.shipping) }</span>
					</div>
				</div>
			</li>
			<li style="border-bottom: none;">
				<div class="txt-holder">
					<strong class="title sub-title pull-left">ORDER TOTAL</strong>
					<div class="txt pull-right">
						<span><i class="fa fa-dollar"></i> ${SharedMethod.moneyFormat(requestScope.total) }</span>
					</div>
				</div>
			</li>
		</ul>
	</div>
	<a class="process-btn buttonSubmitOrder" style="margin-top: 20px">PROCEED TO CHECKOUT<i class="fa fa-check"></i></a>
</div>