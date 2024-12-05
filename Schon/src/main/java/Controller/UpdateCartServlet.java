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
import DAO.UpdateCartDAO;
import JoinTable.CartJoinProduct;
import Model.Cart;
import Model.Customer;
import Utilities.SharedMethod;

public class UpdateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		Customer customer = null; 
		if(session.getAttribute("customer") == null) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Vui lòng đăng nhập để thực hiện chức năng này!", 0d)));
			return;
		}
		customer = (Customer)session.getAttribute("customer");
		if(!UpdateCartDAO.checkCustomerID(customer.getCustomerID())) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Tài khoản lỗi vui lòng đăng nhập lại!", 0d)));
			return;
		}
		
		if(!SharedMethod.intCheck(request.getParameter("productID"), 1, Integer.MAX_VALUE)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("ID sản phẩm nhập không hợp lệ!", 0d)));
			return;
		}
		int productID = Integer.parseInt(request.getParameter("productID"));
		
		if(!SharedMethod.intCheck(request.getParameter("quantity"), 1, 10)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Số lượng sản phẩm nhập không hợp lệ!", 0d)));
			return;
		}
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		
		if(!UpdateCartDAO.checkProductID(productID)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("ID sản phẩm không tồn tại!", 0d)));
			return;
		}
		
		CartJoinProduct carJoinProduct = UpdateCartDAO.getCartJoinProduct(customer.getCustomerID(), productID);
		if(carJoinProduct == null) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Sản phẩm không tồn tại trong giỏ hàng!", 0d)));
			return;
		}
		
		Cart cart = new Cart(customer.getCustomerID(), productID, quantity);
		UpdateCartDAO.updateCart(cart);
		
		double total = (carJoinProduct.getProduct().getPrice() - carJoinProduct.getProduct().getPrice() * 
				carJoinProduct.getProduct().getDiscount() / 100) * quantity;
		
		response.getWriter().write(new Gson().toJson(new JsonResult("Cập nhật sản phẩm trong giỏ hàng thành công!", total)));
		
	}
	public class JsonResult{
		private String message;
		private double total;
		public JsonResult() {
			super();
		}
		public JsonResult(String message, double total) {
			super();
			this.message = message;
			this.total = total;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public double getTotal() {
			return total;
		}
		public void setTotal(double total) {
			this.total = total;
		}
	}
}
