<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="mt-search-popup">
	<div class="mt-holder">
		<a href="#" class="search-close"><span></span><span></span></a>
		<div class="mt-frame">
			<form onsubmit="return false;">
				<fieldset>
					<input class="inputSearch-productID" type="text" placeholder="Search...">
					<button class="icon-magnifier buttonSubmitSearch" type="submit"></button>
				</fieldset>
			</form>
		</div>
	</div>
</div>

<script>
	window.addEventListener("load", () => {
		
		document.querySelector(".buttonSubmitSearch").addEventListener("click", () => {
			
			let productID = document.querySelector(".inputSearch-productID").value;
			
			$.ajax({
				url: "/Schon/SearchProductServlet",
				type: "GET",
				data: {
					productID: productID
				},
				success: (data) => {
					if(data.message != "")
						alert(data.message);
					else{
						let url = "/Schon/ProductDetailPageServlet";
						url += "?productID=" + encodeURIComponent(productID);
						
						window.location.assign(url);
					}
						
				}
			});
			
		});
		
	});
</script>