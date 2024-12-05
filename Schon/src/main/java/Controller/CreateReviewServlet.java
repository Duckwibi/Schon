package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Timestamp;

import com.google.gson.Gson;

import Controller.AddToCartServlet.JsonResult;
import DAO.AddToCartDAO;
import DAO.CreateReviewDAO;
import JoinTable.ReviewJoinCustomer;
import Model.Customer;
import Model.Review;
import Utilities.SharedMethod;

public class CreateReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		Customer customer = null; 
		if(session.getAttribute("customer") == null) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Vui lòng đăng nhập để thực hiện chức năng này!", null, 0)));
			return;
		}
		customer = (Customer)session.getAttribute("customer");
		if(!CreateReviewDAO.checkCustomerID(customer.getCustomerID())) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Tài khoản lỗi vui lòng đăng nhập lại!", null, 0)));
			return;
		}
		
		if(!SharedMethod.intCheck(request.getParameter("productID"), 1, Integer.MAX_VALUE)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("ID sản phẩm nhập không hợp lệ!", null, 0)));
			return;
		}
		int productID = Integer.parseInt(request.getParameter("productID"));
		
		if(!SharedMethod.intCheck(request.getParameter("vote"), 1, 4)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Đánh giá nhập không hợp lệ!", null, 0)));
			return;
		}
		int vote = Integer.parseInt(request.getParameter("vote"));
		
		if(!SharedMethod.stringCheck(request.getParameter("message"), 200, true)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Bình luận nhập không hợp lệ!", null, 0)));
			return;
		}
		String message = request.getParameter("message");
		
		if(CreateReviewDAO.checkReview(customer.getCustomerID(), productID)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Bạn đã đánh giá sản phẩm này!", null, 0)));
			return;
		}
		
		Review review = new Review(
				customer.getCustomerID(),
				productID,
				vote,
				new Timestamp(System.currentTimeMillis()),
				message
		);
		
		CreateReviewDAO.createReview(review);
		
		response.getWriter().write(new Gson().toJson(new JsonResult(
				"Đánh giá sản phẩm thành công!", 
				CreateReviewDAO.getReviewJoinCustomer(customer.getCustomerID(), productID),
				CreateReviewDAO.getVoteAVG(productID)
		)));
	}

	public class JsonResult{
		private String message;
		private ReviewJoinCustomer reviewJoinCustomer;
		private int voteAVG;
		public JsonResult() {
			super();
		}
		public JsonResult(String message, ReviewJoinCustomer reviewJoinCustomer, int voteAVG) {
			super();
			this.message = message;
			this.reviewJoinCustomer = reviewJoinCustomer;
			this.voteAVG = voteAVG;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public ReviewJoinCustomer getReviewJoinCustomer() {
			return reviewJoinCustomer;
		}
		public void setReviewJoinCustomer(ReviewJoinCustomer reviewJoinCustomer) {
			this.reviewJoinCustomer = reviewJoinCustomer;
		}
		public int getVoteAVG() {
			return voteAVG;
		}
		public void setVoteAVG(int voteAVG) {
			this.voteAVG = voteAVG;
		}
	}
}
