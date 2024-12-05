package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import DAO.BlogPageDAO;
import DAO.HomePageDAO;
import DAO.ProductDetailPageDAO;
import JoinTable.ProductJoinProductCategory;
import Model.Customer;
import Model.Product;
import Utilities.SharedMethod;

public class ProductDetailPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Part1 start
		if(!SharedMethod.intCheck(request.getParameter("productID"), 1, Integer.MAX_VALUE)) {
			response.sendRedirect("/Schon/NotFoundPageServlet");
			return;
		}
		int productID = Integer.parseInt(request.getParameter("productID"));
		
		ProductJoinProductCategory productJoinProductCategory = ProductDetailPageDAO.getProductJoinProductCategory(productID);
		if(productJoinProductCategory == null) {
			response.sendRedirect("/Schon/NotFoundPageServlet");
			return;
		}
		
		request.setAttribute("productJoinProductCategory", productJoinProductCategory);
		
		int[] reviewCountAndVoteAVG = ProductDetailPageDAO.getReviewCountAndVoteAVG(productID);
		request.setAttribute("reviewCount", reviewCountAndVoteAVG[0]);
		request.setAttribute("voteAVG", reviewCountAndVoteAVG[1]);
		request.setAttribute("wishListCountOfProduct", ProductDetailPageDAO.getWishListCountOfProduct(productID));
		request.setAttribute("productImageLevel4List", ProductDetailPageDAO.getProductImageJoinImageList(4, productID));
		request.setAttribute("productImageLevel5List", ProductDetailPageDAO.getProductImageJoinImageList(5, productID));
		request.setAttribute("reviewJoinCustomerList", ProductDetailPageDAO.getReviewJoinCustomerList(productID));
		request.setAttribute("productList", ProductDetailPageDAO.getProductList(
				productJoinProductCategory.getProduct().getProductCategoryID(),
				productID
		));
		request.setAttribute("voteOfReviewAVGList", ProductDetailPageDAO.getVoteOfReviewAVGList());
		request.setAttribute("productImageJoinImageList", ProductDetailPageDAO.getProductImageJoinImageList());
		//Part1 end
		
		
		//Part2 start
		request.setAttribute("menuList", ProductDetailPageDAO.getMenuList());
		request.setAttribute("productCategoryList", ProductDetailPageDAO.getProductCategoryList());
		request.setAttribute("blogCategoryList", ProductDetailPageDAO.getBlogCategoryList());

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
		//Part2 end


		//Part3 start
		int cartCount = 0;
		int wishListCount = 0;

		HttpSession session = request.getSession();
		Customer customer = null; 
		if(session.getAttribute("customer") != null)
			customer = (Customer)session.getAttribute("customer");

		if(customer != null)
			if(ProductDetailPageDAO.checkCustomerID(customer.getCustomerID())) {
				cartCount = ProductDetailPageDAO.getCartCount(customer.getCustomerID());
				wishListCount = ProductDetailPageDAO.getWishListCount(customer.getCustomerID());
			}

		request.setAttribute("cartCount", cartCount);
		request.setAttribute("wishListCount", wishListCount);
		//Part3 end
		
		
		request.getRequestDispatcher("/MainWeb/View/ProductDetailPage.jsp").forward(request, response);
	}

}
