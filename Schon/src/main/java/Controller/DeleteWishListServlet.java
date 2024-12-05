package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.google.gson.Gson;

import Controller.AddToWishListServlet.JsonResult;
import DAO.AddToWishListDAO;
import DAO.DeleteWishListDAO;
import Model.Customer;
import Model.WishList;
import Utilities.SharedMethod;

public class DeleteWishListServlet extends HttpServlet {
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
		if(!DeleteWishListDAO.checkCustomerID(customer.getCustomerID())) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Tài khoản lỗi vui lòng đăng nhập lại!", 0)));
			return;
		}
		
		if(!SharedMethod.intCheck(request.getParameter("productID"), 1, Integer.MAX_VALUE)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("ID sản phẩm nhập không hợp lệ!", 0)));
			return;
		}
		int productID = Integer.parseInt(request.getParameter("productID"));
		
		if(!DeleteWishListDAO.checkProductID(productID)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("ID sản phẩm không tồn tại!", 0)));
			return;
		}
		
		if(!DeleteWishListDAO.checkWishList(customer.getCustomerID(), productID)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Sản phẩm không tồn tại trong danh sách theo dõi!", 0)));
			return;
		}
		
		DeleteWishListDAO.deleteWishList(customer.getCustomerID() ,productID);
		
		int wishListCount = DeleteWishListDAO.getWishListCount(customer.getCustomerID());
		
		response.getWriter().write(new Gson().toJson(new JsonResult("Xóa sản phẩm khỏi danh sách theo dõi thành công!", wishListCount)));
		
	}
	public class JsonResult{
		private String message;
		private int wishListCount;
		public JsonResult() {
			super();
		}
		public JsonResult(String message, int wishListCount) {
			super();
			this.message = message;
			this.wishListCount = wishListCount;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public int getWishListCount() {
			return wishListCount;
		}
		public void setWishListCount(int wishListCount) {
			this.wishListCount = wishListCount;
		}
	}
}
