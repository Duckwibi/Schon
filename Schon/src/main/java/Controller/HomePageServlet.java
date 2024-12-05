package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import DAO.BlogPageDAO;
import DAO.HomePageDAO;
import DAO.ProductPageDAO;
import Model.Customer;
import Model.Product;
import OtherTable.BestSellerProduct;
import Utilities.SharedMethod;


public class HomePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setAttribute("sliderList", HomePageDAO.getSliderList());
		request.setAttribute("sliderImageList", HomePageDAO.getProductImageJoinImageList(1));
		request.setAttribute("bannerList", HomePageDAO.getBannerList());
		request.setAttribute("bannerImageList", HomePageDAO.getProductImageJoinImageList(2));
		
		ArrayList<Product> featuredProductList = HomePageDAO.getFeaturedProductList();
		ArrayList<Product> latestProductList = HomePageDAO.getLatestProductList();
		ArrayList<Product> hotSaleProductList = HomePageDAO.getHotSaleProductList();
		ArrayList<BestSellerProduct> bestSellerProductList = HomePageDAO.getBestSellerProductList();
		
		request.setAttribute("featuredProductList", featuredProductList);
		request.setAttribute("latestProductList", latestProductList);
		request.setAttribute("hotSaleProductList", hotSaleProductList);
		request.setAttribute("bestSellerProductList", bestSellerProductList);
		
		request.setAttribute("top3FeaturedProductList", SharedMethod.takeFromList(featuredProductList, 3));
		request.setAttribute("top3LatestProductList", SharedMethod.takeFromList(latestProductList, 3));
		request.setAttribute("top3HotSaleProductList", SharedMethod.takeFromList(hotSaleProductList, 3));
		request.setAttribute("top3BestSellerProductList", SharedMethod.takeFromList(bestSellerProductList, 3));
		
		request.setAttribute("productImageJoinImageList", HomePageDAO.getProductImageJoinImageList(3));
		request.setAttribute("voteOfReviewAVGList", HomePageDAO.getVoteOfReviewAVGList());
		
		
		//Part1 start
		request.setAttribute("menuList", HomePageDAO.getMenuList());
		request.setAttribute("productCategoryList", HomePageDAO.getProductCategoryList());
		request.setAttribute("blogCategoryList", HomePageDAO.getBlogCategoryList());

		
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
		//Part1 end
		
		
		//Part2 start
		int cartCount = 0;
		int wishListCount = 0;

		HttpSession session = request.getSession();
		Customer customer = null; 
		if(session.getAttribute("customer") != null)
			customer = (Customer)session.getAttribute("customer");

		if(customer != null)
			if(HomePageDAO.checkCustomerID(customer.getCustomerID())) {
				cartCount = HomePageDAO.getCartCount(customer.getCustomerID());
				wishListCount = HomePageDAO.getWishListCount(customer.getCustomerID());
			}

		request.setAttribute("cartCount", cartCount);
		request.setAttribute("wishListCount", wishListCount);
		//Part2 end
		
		request.getRequestDispatcher("/MainWeb/View/HomePage.jsp").forward(request, response);
	}
}
