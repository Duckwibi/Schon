<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="Utilities.SharedMethod" %>

<section class="mt-detail-sec style1 wow fadeInUp" data-wow-delay="0.4s">
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-6">
				<h2>CALCULATE SHIPPING</h2>
				<form onsubmit="return false;" class="bill-detail">
					<fieldset>
						<div class="form-group">
							<select class="form-control inputUpdateTotal-Town">
								<option value="">Select Town</option>
								<c:forEach var="item" items="${requestScope.townList }">
									<option value="${fn:escapeXml(pageScope.item) }">
										${fn:escapeXml(pageScope.item) }
									</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<select class="form-control inputUpdateTotal-City">
								<option value="">Select City</option>
								<c:forEach var="item" items="${requestScope.cityList }">
									<option value="${fn:escapeXml(pageScope.item) }">
										${fn:escapeXml(pageScope.item) }
									</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<input type="text" placeholder="Discount Code" class="form-control inputUpdateTotal-DiscountCodeName">
						</div>
						<div class="form-group">
							<button class="update-btn buttonUpdateTotal" type="submit">
								UPDATE TOTAL 
								<i class="fa fa-refresh"></i>
							</button>
						</div>
					</fieldset>
				</form>
			</div>
			<div class="col-xs-12 col-sm-6">
				<h2>CART TOTAL</h2>
				<ul class="list-unstyled block cart">
					<li>
						<div class="txt-holder">
							<strong class="title sub-title pull-left">CART SUBTOTAL</strong>
							<div class="txt pull-right">
								<span class="cartSubTotal"> 
									<i class="fa fa-dollar"></i>
									${SharedMethod.moneyFormat(requestScope.subTotal) }
								</span>
							</div>
						</div>
					</li>
					<li>
						<div class="txt-holder">
							<strong class="title sub-title pull-left">SHIPPING</strong>
							<div class="txt pull-right">
								<span class="cartShipping">
									<i class="fa fa-dollar"></i> 
									0
								</span>
							</div>
						</div>
					</li>
					<li style="border-bottom: none;">
						<div class="txt-holder">
							<strong class="title sub-title pull-left">CART TOTAL</strong>
							<div class="txt pull-right">
								<span class="cartTotal"> 
									<i class="fa fa-dollar"></i>
									${SharedMethod.moneyFormat(requestScope.subTotal) }
								</span>
							</div>
						</div>
					</li>
				</ul>
				<a class="process-btn buttonSubmitProcess"> PROCEED TO CHECKOUT <i class="fa fa-check"></i></a>
			</div>
		</div>
	</div>
</section>
