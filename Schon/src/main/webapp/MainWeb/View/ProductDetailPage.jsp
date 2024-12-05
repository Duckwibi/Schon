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
				<!-- Mt Product Detial of the Page -->
				<%@ include file="/MainWeb/PartialView/ProductDetailPagePartialView/ProductInfomationPage.jsp" %>
				<!-- Mt Product Detial of the Page end -->
				<%@ include file="/MainWeb/PartialView/ProductDetailPagePartialView/ReviewPage.jsp" %>
				<!-- related products start here -->
				<%@ include file="/MainWeb/PartialView/ProductDetailPagePartialView/RelatedProductListPage.jsp" %>
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
			
			let inputRating = document.querySelector(".inputReview-Vote");
			let i = 1;
			
			inputRating.addEventListener("click", () => {
				
				if(i != 0)
					inputRating.children[i].children[0].classList.replace("fa-star-o", "fa-star");
				else
					for(let j = 1; j < inputRating.children.length; j++)
						inputRating.children[j].children[0].classList.replace("fa-star", "fa-star-o");
				
				i++;
				if(i == 4)
					i = 0;
			});
			
			function escapeXml(text) {
				return text.replaceAll("&", '&amp;').replaceAll("<", '&lt;').replaceAll(">", '&gt;').replaceAll("\"", "&quot;").replaceAll("\'", "&apos;");
			}
			
			document.querySelector(".buttonSubmitReview").addEventListener("click", () => {
				$.ajax({
					url: "/Schon/CreateReviewServlet",
					type: "POST",
					data: {
						productID: ${pageScope.p.productID},
						vote: i != 0 ? i : 4,
						message: document.querySelector(".inputReview-Message").value
					},
					success: (data) => {
						alert(data.message);
						
						if(data.message == "Đánh giá sản phẩm thành công!"){
							
							let review = data.reviewJoinCustomer.review;
							let customer = data.reviewJoinCustomer.customer;
							
							let reviewContainer = document.querySelector(".product-comment");
							let element = document.createElement("div");
							element.classList.add("mt-box");
							
							let createHTML = "<div class=\"mt-hold\">";
							createHTML += "<ul class=\"mt-star\">";
							for(let i = 1; i <= 4; i++)
								if(i <= review.vote)
									createHTML += "<li><i class=\"fa fa-star\"></i></li>";
								else
									createHTML += "<li><i class=\"fa fa-star-o\"></i></li>";
							createHTML += "</ul>";
							createHTML += "<span class=\"name\">";
							createHTML += escapeXml(customer.firstName) + " " + escapeXml(customer.lastName) 
							createHTML += "</span>";
							createHTML += "<time>" + review.createdDate + "</time>";
							createHTML += "</div>";
							createHTML += "<p>" + escapeXml(review.message) + "</p>";
							
							element.innerHTML = createHTML;
							
							reviewContainer.prepend(element);
							
							let productVoteAVGContainer = document.querySelector(".voteAVG");
							productVoteAVGContainer.innerHTML = "";
							
							for(let i = 0; i < 4; i++)
								productVoteAVGContainer.innerHTML += "<li><a><i class=\"fa fa-star-o\"></i></a></li>";
							
							for(let i = 0; i < data.voteAVG; i++)
								productVoteAVGContainer.children[i].children[0].children[0].classList.replace("fa-star-o", "fa-star");
							
							document.querySelectorAll(".reviewCount").forEach((item) => {
								let num = Number(item.innerText) + 1;
								item.innerText = num.toString();
							});
						}
						
					}
				});
			});
			
			
			document.querySelector(".buttonSubmitAddToCart").addEventListener("click", () => {
				
				if(confirm("Xác nhận thêm sản phẩm vào giỏ hàng!")){
					
					$.ajax({
						url: "/Schon/AddToCartServlet",
						type: "POST",
						data: {
							productID: ${pageScope.p.productID },
							quantity: document.querySelector(".inputQuantity").value
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
			
			document.querySelector(".buttonSubmitAddToWishList").addEventListener("click", () => {
				
				if(confirm("Xác nhận thêm sản phẩm vào danh sách theo dõi?")){
					$.ajax({
						url: "/Schon/AddToWishListServlet",
						type: "POST",
						data: {
							productID: ${pageScope.p.productID }
						},
						success: (data) => {
							alert(data.message);
							if(data.message == "Thêm vào danh sách theo dõi thành công!"){
								document.querySelector(".wishListCount").innerText = data.wishListCount > 9 ? 
										"9+" : data.wishListCount.toString();
								
								let currentProductWishListCount = Number(document.querySelector(".currentProductWishListCount").innerText) + 1;
								document.querySelector(".currentProductWishListCount").innerText = currentProductWishListCount.toString();
								
							}
							
						}
					});
				}
				
			});
			
			document.querySelectorAll(".buttonAddToCart").forEach((item) => {
				item.addEventListener("click", () => {
					
					if(confirm("Xác nhận thêm sản phẩm vào giỏ hàng?")){
						let productID = item.classList[1].replaceAll("productID-V3-", "");
						
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
						let productID = item.classList[1].replaceAll("productID-V4-", "");
						
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