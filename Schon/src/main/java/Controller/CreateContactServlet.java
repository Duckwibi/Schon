package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

import com.google.gson.Gson;

import DAO.CreatedContactDAO;
import Model.Contact;
import Utilities.SharedMethod;

public class CreateContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		if(!SharedMethod.stringCheck(request.getParameter("name"), 100, true)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Tên nhập không hợp lệ!")));
			return;
		}
		String name = request.getParameter("name");
		
		if(!SharedMethod.stringCheck(request.getParameter("email"), 100, true)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Email nhập không hợp lệ!")));
			return;
		}
		String email = request.getParameter("email");
		
		if(!SharedMethod.stringCheck(request.getParameter("message"), 200, true)) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Lời nhắn nhập không hợp lệ!")));
			return;
		}
		String message = request.getParameter("message");
		
		int maxContactID = CreatedContactDAO.getMaxContactID();
		
		if(maxContactID == Integer.MAX_VALUE) {
			response.getWriter().write(new Gson().toJson(new JsonResult("Không thể gửi liên hệ lúc này!")));
			return;
		}
		int contactID = maxContactID + 1;
		
		Contact contact = new Contact(
				contactID,
				new Timestamp(System.currentTimeMillis()),
				name,
				email,
				message
		);
		
		CreatedContactDAO.createContact(contact);
		
		response.getWriter().write(new Gson().toJson(new JsonResult("Gửi thành công!")));
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
