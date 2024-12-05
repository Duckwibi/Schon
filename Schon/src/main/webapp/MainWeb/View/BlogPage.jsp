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
  
  <style>
  	.maxText2Line{
  		overflow: hidden;
  		text-overflow: ellipsis;
  		display: -webkit-box;
  		-webkit-line-clamp: 2;
           		line-clamp: 2; 
   		-webkit-box-orient: vertical;
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
      <!-- Main of the Page -->
      <main id="mt-main">
        <!-- Mt Contact Banner of the Page -->
        <section class="mt-contact-banner style4 wow fadeInUp" data-wow-delay="0.4s" style="background-image: url(/Schon/MainWeb/Image/Dowload/img11.jpg);">
			<div class="container">
				<div class="row">
					<div class="col-xs-12 text-center">
						<h1>${fn:escapeXml(requestScope.blogCategory.name) }</h1>
						<!-- Breadcrumbs of the Page -->
						<nav class="breadcrumbs">
							<ul class="list-unstyled">
								<li><a href="/Schon/HomePageServlet">Home <i class="fa fa-angle-right"></i></a></li>
								<li>${fn:escapeXml(requestScope.blogCategory.name) }</li>
							</ul>
						</nav>
						<!-- Breadcrumbs of the Page end -->
					</div>
				</div>
			</div>
		</section>
        <!-- Mt Contact Banner of the Page end -->
        <!-- Mt Blog Detail of the Page -->
        <div class="mt-blog-detail style1">
          <div class="container">
            <div class="row">
              <%@ include file="/MainWeb/PartialView/BlogPagePartialView/BlogListPage.jsp" %>
              <div class="col-xs-12 col-sm-4 text-right sidebar wow fadeInRight" data-wow-delay="0.4s">
                <!-- Category Widget of the Page -->
                <%@ include file="/MainWeb/PartialView/BlogPagePartialView/CategoryListPage.jsp" %>
                <!-- Category Widget of the Page end -->
                <!-- Popular Widget of the Page -->
                <%@ include file="/MainWeb/PartialView/BlogPagePartialView/LatestBlogListPage.jsp" %>
                <!-- Popular Widget of the Page end -->
              </div>
            </div>
          </div>
        </div>
        <!-- Mt Blog Detail of the Page end -->
      </main>
      <!-- footer of the Page -->
      <%@ include file="/MainWeb/PartialView/FooterPage.jsp" %>
      <!-- footer of the Page end -->
    </div>
    <!-- W1 end here -->
    <span id="back-top" class="fa fa-arrow-up"></span>
  </div>
  <!-- include jQuery -->
  <script src="/Schon/MainWeb/js/jquery.js"></script>
  <!-- include jQuery -->
  <script src="/Schon/MainWeb/js/plugins.js"></script>
  <!-- include jQuery -->
  <script src="/Schon/MainWeb/js/jquery.main.js"></script>
</body>
</html>