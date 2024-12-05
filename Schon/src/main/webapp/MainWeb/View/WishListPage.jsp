<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <!-- set the encoding of your site -->
  <meta charset="utf-8">
  <!-- set the viewport width and initial-scale on mobile devices -->
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Schön. | eCommerce HTML5 Template</title>
  <!-- include the site stylesheet -->
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
    <div class="w1">
      <!-- mt -header style14 start from here -->
      <%@ include file="/MainWeb/PartialView/HeaderPage.jsp" %>
      
      <%@ include file="/MainWeb/PartialView/LoginRegisterPage.jsp" %>
      <!-- mt -header style14 end here -->
      <!-- mt search popup start here -->
      <%@ include file="/MainWeb/PartialView/SearchPopupPage.jsp" %>
      <!-- mt search popup end here -->
      <!-- Main of the Page -->
      <main id="mt-main">
        <section class="mt-contact-banner style4 wow fadeInUp" data-wow-delay="0.4s" style="background-image: url(/Schon/MainWeb/Image/Dowload/img11.jpg);">
			<div class="container">
				<div class="row">
					<div class="col-xs-12 text-center">
						<h1>WISH LIST</h1>
						<!-- Breadcrumbs of the Page -->
						<nav class="breadcrumbs">
							<ul class="list-unstyled">
								<li><a href="/Schon/HomePageServlet">Home <i class="fa fa-angle-right"></i></a></li>
								<li>Wish List</li>
							</ul>
						</nav><!-- Breadcrumbs of the Page end -->
					</div>
				</div>
			</div>
		</section>
        <div class="paddingbootom-md hidden-xs"></div>
        <!-- Mt Product Table of the Page -->
        <%@ include file="/MainWeb/PartialView/WishListPagePartialView/MyWishListPage.jsp" %>
        <!-- Mt Product Table of the Page end -->
        <div class="paddingbootom-md hidden-xs"></div>
      </main><!-- Main of the Page end here -->
      <!-- footer of the Page -->
      <%@ include file="/MainWeb/PartialView/FooterPage.jsp" %>
      <!-- footer of the Page end -->
    </div>
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
  		
  		document.querySelectorAll(".buttonApply").forEach((item) => {
			item.addEventListener("click", () => {
				
				if(confirm("Xác nhận thêm sản phẩm vào giỏ hàng?")){
					let productID = item.classList[1].replaceAll("productID-V2-", "");
					
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
  		
  		document.querySelectorAll(".buttonDelete").forEach((item) => {
			item.addEventListener("click", () => {
				
				if(confirm("Xác nhận xóa sản phẩm khỏi danh sách theo dõi?")){
					let productID = item.classList[3].replaceAll("productID-V3-", "");
					
					$.ajax({
						url: "/Schon/DeleteWishListServlet",
						type: "POST",
						data: {
							productID: productID
						},
						success: (data) => {
							alert(data.message);		
							if(data.message == "Xóa sản phẩm khỏi danh sách theo dõi thành công!"){
								document.querySelector(".wishListCount").innerText = data.wishListCount > 9 ? 
										"9+" : data.wishListCount.toString();
								
								item.parentElement.parentElement.parentElement.remove();
							}
						}
					});
				}
				
			});
		});
  		
  	});
  </script>
</body>
</html>