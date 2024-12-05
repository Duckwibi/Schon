package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import DAO.ContactPageDAO;
import DAO.HomePageDAO;
import Model.Customer;

public class ContactPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Part1 start
		request.setAttribute("menuList", ContactPageDAO.getMenuList());
		request.setAttribute("productCategoryList", ContactPageDAO.getProductCategoryList());
		request.setAttribute("blogCategoryList", ContactPageDAO.getBlogCategoryList());


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
			if(ContactPageDAO.checkCustomerID(customer.getCustomerID())) {
				cartCount = ContactPageDAO.getCartCount(customer.getCustomerID());
				wishListCount = ContactPageDAO.getWishListCount(customer.getCustomerID());
			}

		request.setAttribute("cartCount", cartCount);
		request.setAttribute("wishListCount", wishListCount);
		//Part2 end

		request.getRequestDispatcher("/MainWeb/View/ContactPage.jsp").forward(request, response);
	}

}
