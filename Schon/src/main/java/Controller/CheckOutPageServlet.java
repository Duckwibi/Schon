package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import DAO.BlogPageDAO;
import DAO.CartPageDAO;
import DAO.CheckOutPageDAO;
import DAO.UpdateTotalDAO;
import JoinTable.CartJoinProduct;
import Model.Customer;
import Model.DiscountCode;
import Model.ShippingPrice;
import Utilities.SharedMethod;

public class CheckOutPageServlet extends HttpServlet {
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
		if(!CheckOutPageDAO.checkCustomerID(customer.getCustomerID())) {
			response.sendRedirect("/Schon/NotFoundPageServlet");
			return;
		}
		
		if(!SharedMethod.stringCheck(request.getParameter("town"), 100, true)) {
			response.sendRedirect("/Schon/NotFoundPageServlet");
			return;
		}
		String town = request.getParameter("town");
		
		if(!SharedMethod.stringCheck(request.getParameter("city"), 100, true)) {
			response.sendRedirect("/Schon/NotFoundPageServlet");
			return;
		}
		String city = request.getParameter("city");
		
		ShippingPrice shippingPrice = CheckOutPageDAO.getShippingPrice(town, city);
		if(shippingPrice == null) {
			response.sendRedirect("/Schon/NotFoundPageServlet");
			return;
		}

		ArrayList<CartJoinProduct> cartJoinProductList = CheckOutPageDAO.getCartJoinProductList(customer.getCustomerID());
		if(cartJoinProductList.size() == 0) {
			response.sendRedirect("/Schon/NotFoundPageServlet");
			return;
		}
		
		int discount = 0;
		
		if(SharedMethod.stringCheck(request.getParameter("discountCodeName"), 100, true)) {
			DiscountCode discountCode = CheckOutPageDAO.getDiscountCode(request.getParameter("discountCodeName"));
			if(discountCode != null) {
				Date currentDate = new Date(System.currentTimeMillis());
				Date endDate = new Date(discountCode.getEndDate().getTime());
				
				if(endDate.compareTo(currentDate) == 1)
					discount = discountCode.getDiscount();
			}
		}
		
		double shipping = shippingPrice.getPrice() - shippingPrice.getPrice() * discount / 100;
		double subTotal = CheckOutPageDAO.getSubTotal(customer.getCustomerID());
		double total = shipping + subTotal;
		
		request.setAttribute("cartCount", cartJoinProductList.size());
		request.setAttribute("wishListCount", CheckOutPageDAO.getWishListCount(customer.getCustomerID()));
		request.setAttribute("cartJoinProductList", cartJoinProductList);
		request.setAttribute("subTotal", subTotal);
		request.setAttribute("shipping", shipping);
		request.setAttribute("total", total);
		request.setAttribute("customer", customer);
		request.setAttribute("town", town);
		request.setAttribute("city", city);
		request.setAttribute("discountCodeName", request.getParameter("discountCodeName"));
		//Part1 end
		
		
		//Part2 start
		request.setAttribute("menuList", CheckOutPageDAO.getMenuList());
		request.setAttribute("productCategoryList", CheckOutPageDAO.getProductCategoryList());
		request.setAttribute("blogCategoryList", CheckOutPageDAO.getBlogCategoryList());

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
		
		request.getRequestDispatcher("/MainWeb/View/CheckOutPage.jsp").forward(request, response);
	}

}
