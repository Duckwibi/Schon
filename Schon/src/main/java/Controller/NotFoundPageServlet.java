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
import DAO.NotFoundPageDAO;
import Model.Customer;

/**
 * Servlet implementation class NotFoundPageServlet
 */
public class NotFoundPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Partial view start
		request.setAttribute("menuList", NotFoundPageDAO.getMenuList());
		request.setAttribute("productCategoryList", NotFoundPageDAO.getProductCategoryList());
		request.setAttribute("blogCategoryList", NotFoundPageDAO.getBlogCategoryList());

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
		//Partial view end
		
		
		//Get wish list and cart count start
		int cartCount = 0;
		int wishListCount = 0;

		HttpSession session = request.getSession();
		Customer customer = null; 
		if(session.getAttribute("customer") != null)
			customer = (Customer)session.getAttribute("customer");

		if(customer != null)
			if(NotFoundPageDAO.checkCustomerID(customer.getCustomerID())) {
				cartCount = NotFoundPageDAO.getCartCount(customer.getCustomerID());
				wishListCount = NotFoundPageDAO.getWishListCount(customer.getCustomerID());
			}

		request.setAttribute("cartCount", cartCount);
		request.setAttribute("wishListCount", wishListCount);
		//Get wish list and cart count end
		
		request.getRequestDispatcher("/MainWeb/View/NotFoundPage.jsp").forward(request, response);
	}

}
