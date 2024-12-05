package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;

import DAO.BlogPageDAO;
import DAO.ProductPageDAO;
import Model.Brand;
import Model.Customer;
import Model.PriceFilter;
import Model.Product;
import Model.ProductCategory;
import Utilities.SharedMethod;

public class ProductPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Check for productCategoryID start
		if(!SharedMethod.intCheck(request.getParameter("productCategoryID"), 1, Integer.MAX_VALUE)) {
			response.sendRedirect("/Schon/NotFoundPageServlet");
			return;
		}
		
		int productCategoryID = Integer.parseInt(request.getParameter("productCategoryID"));
		ProductCategory productCategory = ProductPageDAO.getProductCategory(productCategoryID);
		
		if(productCategory == null) {
			response.sendRedirect("/Schon/NotFoundPageServlet");
			return;
		}
		
		request.setAttribute("productCategoryID", productCategoryID);
		request.setAttribute("name", productCategory.getName());
		//Check for productCategoryID end
		
		
		//Filter set up start
		int brandID = 0; 
		int priceFilterID = 0;
		PriceFilter priceFilter = null;
		double minPrice = 0d;
		double maxPrice = 0d;
		int sortByPrice = -1;
		boolean filter = false;
		
		if(SharedMethod.intCheck(request.getParameter("brandID"), 1, Integer.MAX_VALUE)) {
			brandID = Integer.parseInt(request.getParameter("brandID"));
			if(!ProductPageDAO.checkBrandID(brandID))
				brandID = 0;
		}
		
		if(SharedMethod.intCheck(request.getParameter("priceFilterID"), 1, Integer.MAX_VALUE)) {
			priceFilterID = Integer.parseInt(request.getParameter("priceFilterID"));
			priceFilter = ProductPageDAO.getPriceFilter(priceFilterID);
			if(priceFilter == null)
				priceFilterID = 0;
			else {
				minPrice = priceFilter.getMinPrice();
				maxPrice = priceFilter.getMaxPrice();
			}
		}
		
		if(SharedMethod.intCheck(request.getParameter("sortByPrice"), 0, 1))
			sortByPrice = Integer.parseInt(request.getParameter("sortByPrice"));
		
		if(SharedMethod.booleanCheck(request.getParameter("filter")))
			filter = Boolean.parseBoolean(request.getParameter("filter"));
		
		request.setAttribute("brandID", brandID);
		request.setAttribute("priceFilterID", priceFilterID);
		request.setAttribute("sortByPrice", sortByPrice);
		//Filter set up end

		
		//Pagination set up start
		int rowCount = ProductPageDAO.getProductListRowCount(
				productCategoryID, 
				brandID, 
				priceFilterID, 
				minPrice, 
				maxPrice
		);
		int pageCount = SharedMethod.getPageCount(rowCount);
		int currentPage = 1;
		
		if(!filter)
			if(SharedMethod.intCheck(request.getParameter("currentPage"), 1, pageCount))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			
		int nextThreePage = SharedMethod.getNextThreePage(currentPage, pageCount);
		request.setAttribute("rowCount", rowCount);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("nextThreePage", nextThreePage);
		//Pagination set up end
		
		
		//Get list start
		request.setAttribute("productList", ProductPageDAO.getProductList(
				productCategoryID, 
				brandID, 
				priceFilterID, 
				minPrice, 
				maxPrice, 
				sortByPrice, 
				currentPage
		));
		request.setAttribute("hotSaleProductList", ProductPageDAO.getHotSaleProductList(productCategoryID));
		request.setAttribute("voteOfReviewAVGList", ProductPageDAO.getVoteOfReviewAVGList());
		request.setAttribute("productImageJoinImageList", ProductPageDAO.getProductImageJoinImageList());
		
		request.setAttribute("menuList", ProductPageDAO.getMenuList());
		request.setAttribute("productCategoryList", ProductPageDAO.getProductCategoryList());
		request.setAttribute("blogCategoryList", ProductPageDAO.getBlogCategoryList());
		
		Cookie[] cookies = request.getCookies();
		String userName = "";
		String password = "";
		
		if(cookies != null)
			for(Cookie item : cookies) {
				if(item.getName().equals("userName"))
					userName = item.getValue();
				if(item.getName().equals("password"))
					password = item.getValue();
			}
		
		request.setAttribute("userName", userName);
		request.setAttribute("password", password);
		
		request.setAttribute("brandList", ProductPageDAO.getbrandList());
		request.setAttribute("productOfBrandCountList", ProductPageDAO.getProductOfBrandCountList(productCategoryID));
		
		request.setAttribute("priceFilterList", ProductPageDAO.getPriceFilterList());
		
		request.setAttribute("productOfProductCategoryCountList", ProductPageDAO.getProductOfProductCategoryCountList());
		//Get list end
		
		
		//Get wish list and cart count start
		int cartCount = 0;
		int wishListCount = 0;
		
		HttpSession session = request.getSession();
		Customer customer = null; 
		if(session.getAttribute("customer") != null) {
			customer = (Customer)session.getAttribute("customer");
			
			if(ProductPageDAO.checkCustomerID(customer.getCustomerID())) {
				cartCount = ProductPageDAO.getCartCount(customer.getCustomerID());
				wishListCount = ProductPageDAO.getWishListCount(customer.getCustomerID());
			}
		}
		
		request.setAttribute("cartCount", cartCount);
		request.setAttribute("wishListCount", wishListCount);
		//Get wish list and cart count end
		
		request.getRequestDispatcher("/MainWeb/View/ProductPage.jsp").forward(request, response);
	}
}
