package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.google.gson.Gson;

import DAO.RegisterDAO;
import Model.Customer;
import Utilities.SharedMethod;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		Customer customer = new Customer();
		
		String password = "";
		String confirmPassword = "";
		
		if(!SharedMethod.stringCheck(request.getParameter("firstName"), 50, true)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Tên đầu nhập không hợp lệ!")));
			return;
		}
		
		customer.setFirstName(request.getParameter("firstName"));
		
		if(!SharedMethod.stringCheck(request.getParameter("lastName"), 50, true)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Tên cuối nhập không hợp lệ!")));
			return;
		}
		
		customer.setLastName(request.getParameter("lastName"));
		
		if(!SharedMethod.stringCheck(request.getParameter("email"), 100, true)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Email nhập không hợp lệ!")));
			return;
		}
		
		customer.setEmail(request.getParameter("email"));
		
		if(!SharedMethod.stringCheck(request.getParameter("phone"), 10, true)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Số điện thoại nhập không hợp lệ!")));
			return;
		}
		
		customer.setPhone(request.getParameter("phone"));
		
		if(!SharedMethod.stringCheck(request.getParameter("address"), 200, true)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Địa chỉ nhập không hợp lệ!")));
			return;
		}
		
		customer.setAddress(request.getParameter("address"));
		
		if(!SharedMethod.stringCheck(request.getParameter("userName"), 100, false)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Tài khoản nhập không hợp lệ!")));
			return;
		}
		
		customer.setUserName(request.getParameter("userName"));
		
		if(!SharedMethod.stringCheck(request.getParameter("password"), 50, false)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Mật khẩu nhập không hợp lệ!")));
			return;
		}
		
		password = request.getParameter("password");
		
		if(!SharedMethod.stringCheck(request.getParameter("confirmPassword"), 50, false)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Mật khẩu xác thực nhập không hợp lệ!")));
			return;
		}
		
		confirmPassword = request.getParameter("confirmPassword");
		
		if(!confirmPassword.equals(password)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Mật khẩu xác thực không trùng khớp với mật khẩu!")));
			return;
		}
		
		customer.setPassword(SharedMethod.getMD5Password(password));
		
		if(!RegisterDAO.checkUserName(customer.getUserName())) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Tên người dùng đã tồn tại!")));
			return;
		}
		
		int maxCustomerID = RegisterDAO.getMaxCustomerID();
		
		if(maxCustomerID == Integer.MAX_VALUE) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Đăng kí không thành công!")));
			return;
		}
		
		customer.setCustomerID(maxCustomerID + 1);
		
		RegisterDAO.createCustomer(customer);
		
		response.getWriter().write(new Gson().toJson(new JsonResult("Đăng kí thành công!")));
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
