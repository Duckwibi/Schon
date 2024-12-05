package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.google.gson.Gson;

import DAO.LoginDAO;
import Model.Customer;
import Utilities.SharedMethod;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		String userName = "";
		String password = "";
		String MD5Password = "";
		boolean rememberMe = false;
		
		if(!SharedMethod.stringCheck(request.getParameter("userName"), 100, true)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Tên tài khoản nhập không hợp lệ!")));
			return;
		}
		
		userName = request.getParameter("userName");
		
		if(!SharedMethod.stringCheck(request.getParameter("password"), 50, true)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Mật khẩu nhập không hợp lệ!")));
			return;
		}
			
		password = request.getParameter("password");
		MD5Password = SharedMethod.getMD5Password(password);
		
		if(SharedMethod.booleanCheck(request.getParameter("rememberMe")))
			rememberMe = Boolean.parseBoolean(request.getParameter("rememberMe"));
		
		
		Customer customer = LoginDAO.getCustomer(userName, MD5Password);
		
		if(customer == null) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Tài khoản hoặc mật khẩu không tồn tại!")));
			return;
		}
		
		if(rememberMe) {
			Cookie cookie = new Cookie("userName", userName);
			cookie.setMaxAge(60*60*24);
			response.addCookie(cookie);
			
			cookie = new Cookie("password", password);
			cookie.setMaxAge(60*60*24);
			response.addCookie(cookie);
		}
		
		HttpSession session = request.getSession();
		
		session.setAttribute("customer", customer);
		
		response.getWriter().write(new Gson().toJson(new JsonResult("Đăng nhập thành công!")));
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
