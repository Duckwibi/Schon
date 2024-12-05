package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.google.gson.Gson;

import Controller.AddToCartServlet.JsonResult;
import DAO.AddToCartDAO;
import DAO.DeleteCartDAO;
import Model.Cart;
import Model.Customer;
import Utilities.SharedMethod;

/**
 * Servlet implementation class DeleteCartServlet
 */
public class DeleteCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		Customer customer = null; 
		if(session.getAttribute("customer") == null) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Vui lòng đăng nhập để thực hiện chức năng này!", 0)));
			return;
		}
		customer = (Customer)session.getAttribute("customer");
		if(!DeleteCartDAO.checkCustomerID(customer.getCustomerID())) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Tài khoản lỗi vui lòng đăng nhập lại!", 0)));
			return;
		}
		
		if(!SharedMethod.intCheck(request.getParameter("productID"), 1, Integer.MAX_VALUE)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("ID sản phẩm nhập không hợp lệ!", 0)));
			return;
		}
		int productID = Integer.parseInt(request.getParameter("productID"));
		
		
		if(!DeleteCartDAO.checkProductID(productID)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("ID sản phẩm không tồn tại!", 0)));
			return;
		}
		
		if(!DeleteCartDAO.checkCart(customer.getCustomerID(), productID)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Sản phẩm không tồn tại trong giỏ hàng!", 0)));
			return;
		}
		
		DeleteCartDAO.deleteCart(customer.getCustomerID(), productID);
		
		int cartCount = DeleteCartDAO.getCartCount(customer.getCustomerID());
		
		response.getWriter().write(new Gson().toJson(new JsonResult("Xóa sản phẩm khỏi giỏ hàng thành công!", cartCount)));
	}
	
	public class JsonResult{
		private String message;
		private int cartCount;
		public JsonResult() {
			super();
		}
		public JsonResult(String message, int cartCount) {
			super();
			this.message = message;
			this.cartCount = cartCount;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public int getCartCount() {
			return cartCount;
		}
		public void setCartCount(int cartCount) {
			this.cartCount = cartCount;
		}
	}
}
