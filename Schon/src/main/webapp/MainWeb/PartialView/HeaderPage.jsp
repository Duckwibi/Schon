<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<header id="mt-header" class="style4">
	<!-- mt bottom bar start here -->
	<div class="mt-bottom-bar">
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<!-- mt logo start here -->
					<div class="mt-logo">
						<a href="#"><img src="/Schon/MainWeb/Image/mt-logo.png"
							alt="schon"></a>
					</div>
					<!-- mt icon list start here -->
					<ul class="mt-icon-list">
						<li class="hidden-lg hidden-md">
							<a href="#" class="bar-opener mobile-toggle">
								<span class="bar"></span> 
								<span class="bar small"></span> 
								<span class="bar"></span>
							</a>
						</li>
						<li><a href="#" class="icon-magnifier"></a></li>
						<li>
							<a href="#" class="icon-heart cart-opener wishListPageLink">
								<c:choose>
									<c:when test="${requestScope.wishListCount > 9 }">
										<span style="margin-bottom: -3px;" class="num wishListCount">9+</span>
									</c:when>
									<c:otherwise>
										<span style="margin-bottom: -3px;" class="num wishListCount">${requestScope.wishListCount }</span>
									</c:otherwise>
								</c:choose>
							</a> 
						</li>
						<li>
							<a href="#" class="cart-opener cartPageLink"> 
								<span class="icon-handbag"></span> 
								<c:choose>
									<c:when test="${requestScope.cartCount > 9 }">
										<span class="num cartCount">9+</span>
									</c:when>
									<c:otherwise>
										<span class="num cartCount">${requestScope.cartCount }</span>
									</c:otherwise>
								</c:choose>
							</a>
						</li>
						<li>
							<a href="#" class="bar-opener side-opener"> 
								<span class="bar"></span> 
								<span class="bar small"></span> 
								<span class="bar"></span>
							</a>
						</li>
					</ul>
					<!-- mt icon list end here -->
					<!-- navigation start here -->
					<%@ include file="/MainWeb/PartialView/MenuPage.jsp"%>
					<!-- mt icon list end here -->
				</div>
			</div>
		</div>
	</div>
	<!-- mt bottom bar end here -->
	<span class="mt-side-over"></span>
</header>
<script>
	window.addEventListener("load", () => {
		document.querySelector(".wishListPageLink").addEventListener("click", () => {
			window.location.assign("/Schon/WishListPageServlet");
		});
		document.querySelector(".cartPageLink").addEventListener("click", () => {
			window.location.assign("/Schon/CartPageServlet");
		});
	});
</script>