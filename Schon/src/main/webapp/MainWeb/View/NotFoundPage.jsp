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
  
  <style>
  	.mt-error-sec{
  		background: #fff !important;
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
      <!-- Main of the Page -->
      <main id="mt-main">
        <!-- Mt Contact Banner of the Page -->
        <section class="mt-contact-banner wow fadeInUp" data-wow-delay="0.4s" style="background-image: url(/Schon/MainWeb/Image/Dowload/img11.jpg);">
          <div class="container">
            <div class="row">
              <div class="col-xs-12 text-center">
                <h1>404 Page</h1>
                <nav class="breadcrumbs">
                  <ul class="list-unstyled">
                    <li><a href="/Schon/HomePageServlet">Home <i class="fa fa-angle-right"></i></a></li>
                    <li><a href="#">404 page</a></li>
                  </ul>
                </nav>
              </div>
            </div>
          </div>
        </section><!-- Mt Contact Banner of the Page -->
        <!-- Mt Error Sec of the Page -->
        <section class="mt-error-sec dark style3">
          <div class="container">
            <div class="row">
              <div class="col-xs-12 col-sm-6">
                <div class="error-holder pull-right">
                  <h1 class="text-uppercase montserrat">PAGE NOT FOUND!</h1>
                  <div class="txt">
                    <p>The page you are looking for was moved, <br>removed, renamed or might never existed.</p>
                  </div>
                  <a href="/Schon/HomePageServlet" class="btn-back text-uppercase">BACK TO HOME</a>
                </div>
              </div>
              <div class="col-xs-12 col-sm-6">
                <span class="error-code2 montserrat">404</span>
              </div>
            </div>
          </div>
        </section>
        <!-- Mt Error Sec of the Page end -->
      </main>
      <!-- footer of the Page -->
      <%@ include file="/MainWeb/PartialView/FooterPage.jsp" %>
      <!-- footer of the Page end -->
      <span id="back-top" class="fa fa-arrow-up"></span>
    </div>
  </div>
  <!-- include jQuery -->
  <script src="/Schon/MainWeb/js/jquery.js"></script>
  <!-- include jQuery -->
  <script src="/Schon/MainWeb/js/plugins.js"></script>
  <!-- include jQuery -->
  <script src="/Schon/MainWeb/js/jquery.main.js"></script>
</body>
</html>