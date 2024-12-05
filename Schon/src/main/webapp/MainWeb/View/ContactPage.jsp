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
                <h1>CONTACT</h1>
                <nav class="breadcrumbs">
                  <ul class="list-unstyled">
                    <li><a href="index.html">Home <i class="fa fa-angle-right"></i></a></li>
                    <li><a href="#">Contact</a></li>
                  </ul>
                </nav>
              </div>
            </div>
          </div>
        </section><!-- Mt Contact Banner of the Page -->
        <!-- Mt Contact Detail of the Page -->
        <section class="mt-contact-detail content-info wow fadeInUp" data-wow-delay="0.4s">
          <div class="container-fluid">
            <div class="row">
              <div class="col-xs-12 col-sm-8">
                <div class="txt-wrap">
                  <h1>schön. chair maker</h1>
                  <p>Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut <br>enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut <br>aliquip ex ea commodo consequat. </p>
                </div>
                <ul class="list-unstyled contact-txt">
                  <li>
                    <strong>Address</strong>
                    <address>Suite 18B, 148 Connaught Road <br>Central <br>New Yankee</address>
                  </li>
                  <li>
                    <strong>Phone</strong>
                    <a href="#">+1 (555) 333 22 11</a>
                  </li>
                  <li>
                    <strong>E_mail</strong>
                    <a href="#">info@schon.chair</a>
                  </li>
                </ul>
              </div>
              <div class="col-xs-12 col-sm-4">
                <h2>Have a question?</h2>
                <!-- Contact Form of the Page -->
                <form onsubmit="return false;" class="contact-form">
                  <fieldset>
                    <input type="text" class="form-control inputContact-Name" placeholder="Name">
                    <input type="email" class="form-control inputContact-Email" placeholder="E-Mail">
                    <textarea class="form-control inputContact-Message" placeholder="Message"></textarea>
                    <button class="btn-type3 buttonSubmitContact" type="submit">Send</button>
                  </fieldset>
                </form>
                <!-- Contact Form of the Page end -->
              </div>
            </div>
          </div>
        </section><!-- Mt Contact Detail of the Page end -->
        <!-- Mt Map Holder of the Page -->
        <div class="mt-map-holder wow fadeInUp" data-wow-delay="0.4s" data-lat="52.392363" data-lng="1.480408" data-zoom="8">
          <div class="map-info">
            <h2>Sochan</h2>
            <p>Lorem ipsum dolor sit amet...</p>
          </div>
        </div><!-- Mt Map Holder of the Page end -->
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
  <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js"></script>
  
  <script>
  	window.addEventListener("load", () => {
  		
  		document.querySelector(".buttonSubmitContact").addEventListener("click", () => {
  			$.ajax({
  				url: "/Schon/CreateContactServlet",
  				type: "POST",
  				data: {
  					name: document.querySelector(".inputContact-Name").value,
  					email: document.querySelector(".inputContact-Email").value,
  					message: document.querySelector(".inputContact-Message").value,
  				},
  				success: (data) => {
  					alert(data.message);
  				}
  				
  			});
  		});
  		
  	});
  </script>
</body>
</html>