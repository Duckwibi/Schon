package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.google.gson.Gson;

import DAO.SearchProductDAO;
import Utilities.SharedMethod;

public class SearchProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		
		if(!SharedMethod.intCheck(request.getParameter("productID"), 1, Integer.MAX_VALUE)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("ID sản phẩm nhập không hợp lệ!")));
			return;
		}
		int productID = Integer.parseInt(request.getParameter("productID"));
		
		if(!SearchProductDAO.checkProductID(productID)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("ID sản phẩm không tồn tại!")));
			return;
		}
		
		response.getWriter().write(new Gson().toJson(new JsonResult("")));
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
