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

import DAO.CartPageDAO;
import DAO.HomePageDAO;
import DAO.WishListPageDAO;
import JoinTable.CartJoinProduct;
import Model.Customer;

public class CartPageServlet extends HttpServlet {
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
		if(!CartPageDAO.checkCustomerID(customer.getCustomerID())) {
			response.sendRedirect("/Schon/NotFoundPageServlet");
			return;
		}
		
		ArrayList<CartJoinProduct> cartJoinProductList = CartPageDAO.getCartJoinProductList(customer.getCustomerID());
		int wishListCount = CartPageDAO.getWishListCount(customer.getCustomerID());
		
		request.setAttribute("cartCount", cartJoinProductList.size());
		request.setAttribute("wishListCount", wishListCount);
		request.setAttribute("cartJoinProductList", cartJoinProductList);
		request.setAttribute("productImageJoinImageList", CartPageDAO.getProductImageJoinImageList());
		request.setAttribute("subTotal", CartPageDAO.getSubTotal(customer.getCustomerID()));
		request.setAttribute("townList", CartPageDAO.getTownOrCityList(false));
		request.setAttribute("cityList", CartPageDAO.getTownOrCityList(true));
		//Part1 end
		
		
		//Part2 start
		request.setAttribute("menuList", CartPageDAO.getMenuList());
		request.setAttribute("productCategoryList", CartPageDAO.getProductCategoryList());
		request.setAttribute("blogCategoryList", CartPageDAO.getBlogCategoryList());

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
		

		request.getRequestDispatcher("/MainWeb/View/CartPage.jsp").forward(request, response);
		
	}

}
