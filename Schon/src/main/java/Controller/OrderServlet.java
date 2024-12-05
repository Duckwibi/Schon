package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

import com.google.gson.Gson;


import DAO.OrderDAO;

import JoinTable.CartJoinProduct;
import Model.Customer;
import Model.DiscountCode;

import Model.Order;
import Model.ShippingPrice;
import Utilities.SharedMethod;


public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		Customer customer = null; 
		if(session.getAttribute("customer") == null) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Vui lòng đăng nhập để thực hiện chức năng này!")));
			return;
		}
		customer = (Customer)session.getAttribute("customer");
		if(!OrderDAO.checkCustomerID(customer.getCustomerID())) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Tài khoản lỗi vui lòng đăng nhập lại!")));
			return;
		}
		
		if(!SharedMethod.stringCheck(request.getParameter("firstName"), 50, true)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Tên đầu nhập không hợp lệ!")));
			return;
		}
		String firstName = request.getParameter("firstName");
		
		if(!SharedMethod.stringCheck(request.getParameter("lastName"), 50, true)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Tên cuối nhập không hợp lệ!")));
			return;
		}
		String lastName = request.getParameter("lastName");
		
		if(!SharedMethod.stringCheck(request.getParameter("phone"), 10, true)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Số điện thoại nhập không hợp lệ!")));
			return;
		}
		String phone = request.getParameter("phone");
		
		if(!SharedMethod.stringCheck(request.getParameter("email"), 100, true)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Email nhập không hợp lệ!")));
			return;
		}
		String email = request.getParameter("email");
		
		if(!SharedMethod.stringCheck(request.getParameter("address"), 50, true)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Địa chỉ nhập không hợp lệ!")));
			return;
		}
		String address = request.getParameter("address");
		
		if(!SharedMethod.stringCheck(request.getParameter("note"), 50, true)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Ghi chú nhập không hợp lệ!")));
			return;
		}
		String note = request.getParameter("note");
		
		if(!SharedMethod.stringCheck(request.getParameter("town"), 100, true)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Tên tỉnh thành nhập không hợp lệ!")));
			return;
		}
		String town = request.getParameter("town");
		
		if(!SharedMethod.stringCheck(request.getParameter("city"), 100, true)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Tên thành phố nhập không hợp lệ!")));
			return;
		}
		String city = request.getParameter("city");
		
		ShippingPrice shippingPrice = OrderDAO.getShippingPrice(town, city);
		if(shippingPrice == null) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Phạm vi giao hàng của bạn không được hỗ trợ!")));
			return;
		}
		
		ArrayList<CartJoinProduct> cartJoinProductList = OrderDAO.getCartJoinProductList(customer.getCustomerID());
		
		if(cartJoinProductList.size() == 0) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Không thể đặt hàng lúc này!")));
			return;
		}
		
		ArrayList<CartJoinProduct> errorCartJoinProductList = new ArrayList<CartJoinProduct>(
				cartJoinProductList.stream().filter(
						i -> i.getProduct().getQuantity() < i.getCart().getQuantity()
				).collect(Collectors.toList()));
		
		
		if(errorCartJoinProductList.size() != 0) {
			String message = "";
			for(CartJoinProduct item : errorCartJoinProductList){
				message += "Sản phẩm " + item.getProduct().getName();
				message += " có số lượng trong kho là " + item.getProduct().getQuantity() + ".\n";
			}
			message += "Vui lòng cập nhật lại giỏ hàng!";
			response.getWriter().write(new Gson().toJson(new JsonResult(message)));
			return;
		}
			
		
		int discount = 0;
		
		if(SharedMethod.stringCheck(request.getParameter("discountCodeName"), 100, true)) {
			DiscountCode discountCode = OrderDAO.getDiscountCode(request.getParameter("discountCodeName"));
			if(discountCode != null) {
				Date currentDate = new Date(System.currentTimeMillis());
				Date endDate = new Date(discountCode.getEndDate().getTime());
				
				if(endDate.compareTo(currentDate) == 1)
					discount = discountCode.getDiscount();
			}
		}
		
		int maxOrderID = OrderDAO.getMaxOrderID();
		
		if(maxOrderID == Integer.MAX_VALUE) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Không thể đặt hàng lúc này!")));
			return;
		}
		
		int orderID = maxOrderID + 1;
		
		Order order = new Order(
				orderID,
				new Timestamp(System.currentTimeMillis()),
				firstName,
				lastName,
				phone,
				email,
				address,
				note,
				discount,
				shippingPrice.getPrice(),
				customer.getCustomerID()
		);
		
		OrderDAO.createOrderAndOrderDetail(order, cartJoinProductList);
		
		
		response.getWriter().write(new Gson().toJson(new JsonResult("Đặt hàng thành công!")));
	}
	public class JsonResult{
		private String message;
		public JsonResult() {
			super();
		}
		public JsonResult(String message) {
			super();
			this.message = message;
		}
		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}

}
