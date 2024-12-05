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

import DAO.BlogPageDAO;
import DAO.HomePageDAO;
import DAO.WishListPageDAO;
import JoinTable.WishListJoinProduct;
import Model.Customer;
import Model.WishList;

public class WishListPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Part1 start
		HttpSession session = request.getSession();
		Customer customer = null;
		if(session.getAttribute("customer") == null) {
			response.sendRedirect("/Schon/NotFoundPageServlet");
			return;
		}
		customer = (Customer)session.getAttribute("customer");
		if(!WishListPageDAO.checkCustomerID(customer.getCustomerID())) {
			response.sendRedirect("/Schon/NotFoundPageServlet");
			return;
		}
		int cartCount = WishListPageDAO.getCartCount(customer.getCustomerID());
		ArrayList<WishListJoinProduct> wishListListJoinProductList = WishListPageDAO.getWishListListJoinProductList(customer.getCustomerID());

		request.setAttribute("cartCount", cartCount);
		request.setAttribute("wishListCount", wishListListJoinProductList.size());
		request.setAttribute("wishListListJoinProductList", wishListListJoinProductList);
		request.setAttribute("productImageJoinImageList", WishListPageDAO.getProductImageJoinImageList());
		//Part1 end
		
		
		//Part2 start
		request.setAttribute("menuList", WishListPageDAO.getMenuList());
		request.setAttribute("productCategoryList", WishListPageDAO.getProductCategoryList());
		request.setAttribute("blogCategoryList", WishListPageDAO.getBlogCategoryList());

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

		request.getRequestDispatcher("/MainWeb/View/WishListPage.jsp").forward(request, response);
	}

}
