package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;

import com.google.gson.Gson;

import Controller.AddToCartServlet.JsonResult;
import DAO.AddToCartDAO;
import DAO.UpdateTotalDAO;
import Model.Customer;
import Model.DiscountCode;
import Model.ShippingPrice;
import Utilities.SharedMethod;

public class UpdateTotalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		Customer customer = null; 
		if(session.getAttribute("customer") == null) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Vui lòng đăng nhập để thực hiện chức năng này!", 0d, 0d, 0d)));
			return;
		}
		customer = (Customer)session.getAttribute("customer");
		if(!UpdateTotalDAO.checkCustomerID(customer.getCustomerID())) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Tài khoản lỗi vui lòng đăng nhập lại!", 0d, 0d, 0d)));
			return;
		}
		
		if(!SharedMethod.stringCheck(request.getParameter("town"), 100, true)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Tên tỉnh thành nhập không hợp lệ!", 0d, 0d, 0d)));
			return;
		}
		String town = request.getParameter("town");
		
		if(!SharedMethod.stringCheck(request.getParameter("city"), 100, true)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Tên thành phố nhập không hợp lệ!", 0d, 0d, 0d)));
			return;
		}
		String city = request.getParameter("city");
		
		ShippingPrice shippingPrice = UpdateTotalDAO.getShippingPrice(town, city);
		if(shippingPrice == null) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Phạm vi giao hàng của bạn không được hỗ trợ!", 0d, 0d, 0d)));
			return;
		}
		
		int discount = 0;
		
		if(SharedMethod.stringCheck(request.getParameter("discountCodeName"), 100, true)) {
			DiscountCode discountCode = UpdateTotalDAO.getDiscountCode(request.getParameter("discountCodeName"));
			if(discountCode != null) {
				Date currentDate = new Date(System.currentTimeMillis());
				Date endDate = new Date(discountCode.getEndDate().getTime());
				
				if(endDate.compareTo(currentDate) == 1)
					discount = discountCode.getDiscount();
			}
		}
		
		double shipping = shippingPrice.getPrice() - shippingPrice.getPrice() * discount / 100;
		
		double subTotal = UpdateTotalDAO.getSubTotal(customer.getCustomerID());
		
		double total = shipping + subTotal;
		
		response.getWriter().write(new Gson().toJson(new JsonResult("", subTotal, shipping, total)));
	}
	
	public class JsonResult{
		private String message;
		private double subTotal;
		private double shipping;
		private double total;
		public JsonResult() {
			super();
		}
		public JsonResult(String message, double subTotal, double shipping, double total) {
			super();
			this.message = message;
			this.subTotal = subTotal;
			this.shipping = shipping;
			this.total = total;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public double getSubTotal() {
			return subTotal;
		}
		public void setSubTotal(double subTotal) {
			this.subTotal = subTotal;
		}
		public double getShipping() {
			return shipping;
		}
		public void setShipping(double shipping) {
			this.shipping = shipping;
		}
		public double getTotal() {
			return total;
		}
		public void setTotal(double total) {
			this.total = total;
		}
	}
}
