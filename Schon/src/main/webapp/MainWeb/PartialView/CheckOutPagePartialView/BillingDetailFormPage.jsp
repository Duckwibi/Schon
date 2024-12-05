<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="col-xs-12 col-sm-6">
	<h2>BILLING DETAILS</h2>
	<!-- Bill Detail of the Page -->
	<form onsubmit="return false;" class="bill-detail">
		<fieldset>
			<div class="form-group">
				<div class="col">
					<input type="text" class="form-control inputOrder-FirstName" placeholder="First Name"
					value="${fn:escapeXml(requestScope.customer.firstName)}">
				</div>
				<div class="col">
					<input type="text" class="form-control inputOrder-LastName" placeholder="Last Name" 
					value="${fn:escapeXml(requestScope.customer.lastName)}">
				</div>
			</div>
			<div class="form-group">
				<input type="text" class="form-control inputOrder-Phone" placeholder="Phone" 
				value="${fn:escapeXml(requestScope.customer.phone)}">
			</div>
			<div class="form-group">
				<input type="text" class="form-control inputOrder-Email" placeholder="Email" 
				value="${fn:escapeXml(requestScope.customer.email)}">
			</div>
			<div class="form-group">
				<textarea class="form-control inputOrder-Address" placeholder="Address"></textarea>
			</div>
			<div class="form-group">
				<textarea class="form-control inputOrder-Note" placeholder="Order Notes"></textarea>
			</div>
		</fieldset>
	</form>
	<!-- Bill Detail of the Page end -->
</div>
<script>
	window.addEventListener("load", () => {
		document.querySelector(".inputOrder-Address").value = "${fn:escapeXml(requestScope.customer.address)}";
		document.querySelector(".inputOrder-Note").value = "none";
	});	
</script>