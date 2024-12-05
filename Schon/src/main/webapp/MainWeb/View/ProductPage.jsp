<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="Utilities.SharedMethod" %>
<html lang="en">
<head>
	<!-- set the encoding of your site -->
	<meta charset="utf-8">
	<!-- set the viewport width and initial-scale on mobile devices -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Schön. | eCommerce HTML5 Template</title>
	<link href='https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,200,200italic,300,300italic,400italic,600,600italic,700,700italic,900,900italic%7cMontserrat:400,700%7cOxygen:400,300,700' rel='stylesheet' type='text/css'>
	<!-- include the site stylesheet -->
	<link rel="stylesheet" href="/Schon/MainWeb/css/bootstrap.css">
	<!-- include the site stylesheet -->
	<link rel="stylesheet" href="/Schon/MainWeb/css/animate.css">
	<!-- include the site stylesheet -->
	<link rel="stylesheet" href="/Schon/MainWeb/css/icon-fonts.css">
	<!-- include the site stylesheet -->
	<link rel="stylesheet" href="/Schon/MainWeb/css/main.css">
	<!-- include the site stylesheet -->
	<link rel="stylesheet" href="/Schon/MainWeb/css/responsive.css">
	
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	
	<style>
		.customCode{
			display: flex;
			flex-wrap: wrap;
			justify-content: flex-end;
		}
		.customCode>button{
			display: block;
			outline: none;
			background: #494949;
			padding: 2px 15px;
			border: 0;
			border-radius: 5px;
			color: white;
			transition: .3s;
		}
		.customCode>button:hover{
			background: #ff6060;
		}
	</style>
</head>
<body>
	<!-- main container of all the page elements -->
	<div id="wrapper">
		<!-- Page Loader -->
		<div id="pre-loader" class="loader-container">
			<div class="loader">
				<img src="/Schon/MainWeb/Image/svg/rings.svg" alt="loader">
			</div>
		</div>
		<!-- W1 start here -->
		<div class="w1">
			<!-- mt header style4 start here -->
			<%@ include file="/MainWeb/PartialView/HeaderPage.jsp" %>
			<!-- mt header style4 end here -->
			<!-- mt side menu start here -->
			<%@ include file="/MainWeb/PartialView/LoginRegisterPage.jsp" %>
			<!-- mt side menu end here -->
			<!-- mt search popup start here -->
			<%@ include file="/MainWeb/PartialView/SearchPopupPage.jsp" %>
			<!-- mt search popup end here -->
			<!-- mt main start here -->
			<main id="mt-main">
				<!-- Mt Contact Banner of the Page -->
				<section class="mt-contact-banner style4 wow fadeInUp" data-wow-delay="0.4s" style="background-image: url(/Schon/MainWeb/Image/Dowload/img11.jpg);">
					<div class="container">
						<div class="row">
							<div class="col-xs-12 text-center">
								<h1>${fn:escapeXml(requestScope.name) }</h1>
								<!-- Breadcrumbs of the Page -->
								<nav class="breadcrumbs">
									<ul class="list-unstyled">
										<li><a href="/Schon/HomePageServlet">Home <i class="fa fa-angle-right"></i></a></li>
										<li>${fn:escapeXml(requestScope.name) }</li>
									</ul>
								</nav><!-- Breadcrumbs of the Page end -->
							</div>
						</div>
					</div>
				</section><!-- Mt Contact Banner of the Page end -->
				<div class="container">
					<div class="row">
						<!-- sidebar of the Page start here -->
						<aside id="sidebar" class="col-xs-12 col-sm-4 col-md-3 wow fadeInLeft" data-wow-delay="0.4s">
							<!-- shop-widget filter-widget of the Page start here -->
							<section class="shop-widget filter-widget bg-grey">
								<h2>FILTER</h2>
								<span class="sub-title">Filter by Brands</span>
								<!-- nice-form start here -->
								
								<%@ include file="/MainWeb/PartialView/ProductPagePartialView/FilterByBrandPage.jsp" %>
								
								<!-- nice-form end here -->
								<span class="sub-title">Filter by Prices</span>
								
								<%@ include file="/MainWeb/PartialView/ProductPagePartialView/FilterByPricePage.jsp" %>
								
								<span class="sub-title">Sort by Price</span>
								
								<%@ include file="/MainWeb/PartialView/ProductPagePartialView/SortByPricePage.jsp" %>
								
								<div class="customCode">
									<button type="button" class="buttonFilter">Filter</button>
								</div>
							</section><!-- shop-widget filter-widget of the Page end here -->
							<!-- shop-widget of the Page start here -->
							<section class="shop-widget">
								<h2>CATEGORIES</h2>
								<!-- category list start here -->
								<%@ include file="/MainWeb/PartialView/ProductPagePartialView/ProductCategoryListPage.jsp" %>
								<!-- category list end here -->
							</section><!-- shop-widget of the Page end here -->
							<!-- shop-widget of the Page start here -->
							<%@ include file="/MainWeb/PartialView/ProductPagePartialView/HotSaleProductPage.jsp" %>
							<!-- shop-widget of the Page end here -->
						</aside><!-- sidebar of the Page end here -->
						<div class="col-xs-12 col-sm-8 col-md-9 wow fadeInRight" data-wow-delay="0.4s">
							<!-- mt shoplist header start here -->
							<header class="mt-shoplist-header">
								<!-- mt-textbox start here -->
								<div class="mt-textbox">
									<p>Showing  <strong>1–9</strong> of  <strong>${requestScope.rowCount }</strong> results</p>
								</div><!-- mt-textbox end here -->
							</header><!-- mt shoplist header end here -->
							<!-- mt productlisthold start here -->
							<%@ include file="/MainWeb/PartialView/ProductPagePartialView/ProductListPage.jsp" %>
							<!-- mt productlisthold end here -->
							<!-- mt pagination start here -->
							<%@ include file="/MainWeb/PartialView/ProductPagePartialView/ProductPaginationPage.jsp" %>
							<!-- mt pagination end here -->
						</div>
					</div>
				</div>
			</main><!-- mt main end here -->
			<!-- footer of the Page -->
			<%@ include file="/MainWeb/PartialView/FooterPage.jsp" %>
			<!-- footer of the Page end -->
		</div><!-- W1 end here -->
		<span id="back-top" class="fa fa-arrow-up"></span>
	</div>
	<!-- include jQuery -->
	<script src="/Schon/MainWeb/js/jquery.js"></script>
	<!-- include jQuery -->
	<script src="/Schon/MainWeb/js/plugins.js"></script>
	<!-- include jQuery -->
	<script src="/Schon/MainWeb/js/jquery.main.js"></script>

	<script>
		window.addEventListener("load", () => {
						
			function getRadioValue(value, list){
							
				list.forEach((item) => {
					if(item.checked)
						value = item.value;
				});
				return value;
			}
						
			document.querySelector(".buttonFilter").addEventListener("click", () => {
							
				let brandIDRadioList = document.querySelectorAll("[name='brandIDRadio']");
				let priceFilterIDRadioList = document.querySelectorAll("[name='priceFilterIDRadio']");
				let sortByPriceRadioList = document.querySelectorAll("[name='sortByPriceRadio']");
							
							
				let url = "/Schon/ProductPageServlet";
				url += "?productCategoryID=" + encodeURIComponent("${requestScope.productCategoryID}");
				
				let brandID = getRadioValue(0, brandIDRadioList);
				url += brandID == 0 ? "" : "&brandID=" + encodeURIComponent(brandID);
				
				let priceFilterID = getRadioValue(0, priceFilterIDRadioList);
				url += priceFilterID == 0 ? "" : "&priceFilterID=" + encodeURIComponent(priceFilterID);
				
				let sortByPrice = getRadioValue(-1 ,sortByPriceRadioList);
				url += sortByPrice == -1 ? "" : "&sortByPrice=" + encodeURIComponent(sortByPrice);
				
				url += "&filter=" + encodeURIComponent(true);
							
				window.location.assign(url);
			});
			
			document.querySelectorAll(".buttonAddToCart").forEach((item) => {
				item.addEventListener("click", () => {
					
					if(confirm("Xác nhận thêm sản phẩm vào giỏ hàng?")){
						let productID = item.classList[1].replaceAll("productID-V5-", "");
						
						$.ajax({
							url: "/Schon/AddToCartServlet",
							type: "POST",
							data: {
								productID: productID,
								quantity: 1
							},
							success: (data) => {
								alert(data.message);	
								if(data.message == "Thêm vào giỏ hàng thành công!")
									document.querySelector(".cartCount").innerText = data.cartCount > 9 ? 
											"9+" : data.cartCount.toString();
							}
						});
					}
					
				});
			});
			
			document.querySelectorAll(".buttonAddToWishList").forEach((item) => {
				item.addEventListener("click", () => {
					
					if(confirm("Xác nhận thêm sản phẩm vào danh sách theo dõi?")){
						let productID = item.classList[1].replaceAll("productID-V6-", "");
						
						$.ajax({
							url: "/Schon/AddToWishListServlet",
							type: "POST",
							data: {
								productID: productID
							},
							success: (data) => {
								alert(data.message);
								if(data.message == "Thêm vào danh sách theo dõi thành công!")
									document.querySelector(".wishListCount").innerText = data.wishListCount > 9 ? 
											"9+" : data.wishListCount.toString();
								
							}
						});
					}
					
				});
			});
		});
	</script>
</body>
</html>