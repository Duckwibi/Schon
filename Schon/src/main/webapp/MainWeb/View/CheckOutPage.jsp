<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
						<h1>CHECK OUT</h1>
						<!-- Breadcrumbs of the Page -->
						<nav class="breadcrumbs">
							<ul class="list-unstyled">
								<li><a href="/Schon/HomePageServlet">Home <i class="fa fa-angle-right"></i></a></li>
								<li>Check Out</li>
							</ul>
						</nav><!-- Breadcrumbs of the Page end -->
					</div>
				</div>
			</div>
		</section>
        <!-- Mt Process Section of the Page -->
        <div class="mt-process-sec wow fadeInUp" data-wow-delay="0.4s">
          <div class="container">
            <div class="row">
              <div class="col-xs-12">
                <!-- Process List of the Page -->
                <ul class="list-unstyled process-list">
                  <li class="active">
                    <span class="counter">01</span>
                    <strong class="title">Shopping Cart</strong>
                  </li>
                  <li class="active">
                    <span class="counter">02</span>
                    <strong class="title">Check Out</strong>
                  </li>
                  <li>
                    <span class="counter">03</span>
                    <strong class="title">Order Complete</strong>
                  </li>
                </ul>
                <!-- Process List of the Page end -->
              </div>
            </div>
          </div>
        </div><!-- Mt Process Section of the Page end -->
        <!-- Mt Detail Section of the Page -->
        <section class="mt-detail-sec toppadding-zero wow fadeInUp" data-wow-delay="0.4s">
          <div class="container">
            <div class="row">
            
              <%@ include file="/MainWeb/PartialView/CheckOutPagePartialView/BillingDetailFormPage.jsp" %>
              
              <%@ include file="/MainWeb/PartialView/CheckOutPagePartialView/MyOrderPage.jsp" %>
              
            </div>
          </div>
        </section>
        <!-- Mt Detail Section of the Page end -->
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
  		
  		document.querySelector(".buttonSubmitOrder").addEventListener("click", () => {
  			$.ajax({
  				url: "/Schon/OrderServlet",
  				type: "POST",
  				data: {
  					firstName: document.querySelector(".inputOrder-FirstName").value,
  					lastName: document.querySelector(".inputOrder-LastName").value,
  					phone: document.querySelector(".inputOrder-Phone").value,
  					email: document.querySelector(".inputOrder-Email").value,
  					address: document.querySelector(".inputOrder-Address").value,
  					note: document.querySelector(".inputOrder-Note").value,
  					town: "${fn:escapeXml(requestScope.town)}",
  					city: "${fn:escapeXml(requestScope.city)}",
  					discountCodeName: "${fn:escapeXml(requestScope.discountCodeName)}"
  				},
  				success: (data) => {
  					alert(data.message);
  					if(data.message == "Đặt hàng thành công!")
  						window.location.assign("/Schon/CartPageServlet");
  				}
  			});
  		});
  		
  	});
  </script>
</body>
</html>