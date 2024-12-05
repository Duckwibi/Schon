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
import DAO.CreateCommentDAO;
import JoinTable.CommentJoinCustomer;
import Model.Comment;
import Model.Customer;
import Utilities.SharedMethod;

public class CreateCommentServlet extends HttpServlet {
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
		if(!CreateCommentDAO.checkCustomerID(customer.getCustomerID())) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Tài khoản lỗi vui lòng đăng nhập lại!", null, 0)));
			return;
		}
		
		if(!SharedMethod.intCheck(request.getParameter("blogID"), 1, Integer.MAX_VALUE)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("ID bài viết nhập không hợp lệ!", null, 0)));
			return;
		}
		int blogID = Integer.parseInt(request.getParameter("blogID"));
		
		if(!SharedMethod.stringCheck(request.getParameter("message"), 200, true)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Bình luận nhập không hợp lệ!", null, 0)));
			return;
		}
		String message = request.getParameter("message");
		
		if(!CreateCommentDAO.checkBlogID(blogID)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("ID bài viết không tồn tại!", null, 0)));
			return;
		}
		
		int maxCommentID = CreateCommentDAO.getMaxCommentID();
		if(maxCommentID == Integer.MAX_VALUE) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Không thể bình luận lúc này!", null, 0)));
			return;
		}
		int commentID = maxCommentID + 1;
		
		Comment comment = new Comment(
				commentID,
				new Timestamp(System.currentTimeMillis()),
				message,
				blogID,
				customer.getCustomerID()
		);
		
		CreateCommentDAO.createComment(comment);
		
		CommentJoinCustomer commentJoinCustomer = new CommentJoinCustomer(comment, customer);
		
		response.getWriter().write(new Gson().toJson(new JsonResult(
				"Gửi bình luận thành công!",
				commentJoinCustomer,
				CreateCommentDAO.getCommentCount(blogID)
		)));
	}

	public class JsonResult{
		private String message;
		private CommentJoinCustomer commentJoinCustomer;
		private int commentCount;
		
		public JsonResult() {
			super();
		}

		public JsonResult(String message, CommentJoinCustomer commentJoinCustomer, int commentCount) {
			super();
			this.message = message;
			this.commentJoinCustomer = commentJoinCustomer;
			this.commentCount = commentCount;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public CommentJoinCustomer getCommentJoinCustomer() {
			return commentJoinCustomer;
		}

		public void setCommentJoinCustomer(CommentJoinCustomer commentJoinCustomer) {
			this.commentJoinCustomer = commentJoinCustomer;
		}

		public int getCommentCount() {
			return commentCount;
		}

		public void setCommentCount(int commentCount) {
			this.commentCount = commentCount;
		}
	}
}
