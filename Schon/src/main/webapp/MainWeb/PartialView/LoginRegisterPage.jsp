<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>     

<div class="mt-side-menu">
	<!-- mt holder start here -->
	<div class="mt-holder">
		<a href="#" class="side-close"><span></span><span></span></a> <strong
			class="mt-side-title">MY ACCOUNT</strong>
		<!-- mt side widget start here -->
		<div class="mt-side-widget">
			<header>
				<span class="mt-side-subtitle">SIGN IN</span>
				<p>Welcome back! Sign in to Your Account</p>
			</header>
			<form onsubmit="return false;">
				<fieldset>
					<input type="text" placeholder="Username" class="input userName" value="${fn:escapeXml(requestScope.userName) }">
					<input type="password" placeholder="Password" class="input password" id="password" value="${fn:escapeXml(requestScope.password) }">
					<div class="box">
						<span class="left">
							<input class="checkbox rememberMe" type="checkbox" id="check1">
							<label for="check1">Remember Me</label>
						</span>
					</div>
					<button type="submit" class="btn-type1 loginBtn">Login</button>
				</fieldset>
			</form>
		</div>
		<!-- mt side widget end here -->
		<div class="or-divider">
			<span class="txt">or</span>
		</div>
		<!-- mt side widget start here -->
		<div class="mt-side-widget">
			<header>
				<span class="mt-side-subtitle">CREATE NEW ACCOUNT</span>
				<p>Create your very own account</p>
			</header>
			<form onsubmit="return false;">
				<fieldset>
					<input type="text" placeholder="First Name" class="input firstName">
					<input type="text" placeholder="Last Name" class="input lastName">
					<input type="text" placeholder="Your Email" class="input email">
					<input type="text" placeholder="Your Phone" class="input phone">
					<textarea placeholder="Address" class="input address"></textarea>
					<input type="text" placeholder="Username" class="input userName">
					<input type="password" placeholder="Password" class="input password">
					<input type="password" placeholder="Re-type Password" class="input confirmPassword">
					<button type="submit" class="btn-type1 registerBtn" style="margin-top: 10px">Register</button>
				</fieldset>
			</form>
		</div>
		<!-- mt side widget end here -->
	</div>
	<!-- mt holder end here -->
</div>

<script>
	window.addEventListener("load", () => {
		document.querySelector(".loginBtn").addEventListener("click", () => {
			
			let rememberMe = false;
			if(document.querySelector(".rememberMe").checked)
				rememberMe = true;
			
			$.ajax({
				url: "/Schon/LoginServlet",
				type: "POST",
				data: {
					userName: document.querySelectorAll(".userName")[0].value,
					password: document.querySelectorAll(".password")[0].value,
					rememberMe: rememberMe
				},
				success: (data) => {
					alert(data.message);	
					if(data.message == "Đăng nhập thành công!")
						window.location.assign("/Schon/HomePageServlet");
				}
			});
		});
		
		document.querySelector(".registerBtn").addEventListener("click", () => {
			
			$.ajax({
				url: "/Schon/RegisterServlet",
				type: "POST",
				data: {
					firstName: document.querySelector(".firstName").value,
					lastName: document.querySelector(".lastName").value,
					email: document.querySelector(".email").value,
					phone: document.querySelector(".phone").value,
					address: document.querySelector(".address").value,
					userName: document.querySelectorAll(".userName")[1].value,
					password: document.querySelectorAll(".password")[1].value,
					confirmPassword: document.querySelector(".confirmPassword").value,
				},
				success: (data) => {
					alert(data.message);
				}
			});
		});
	});
</script>