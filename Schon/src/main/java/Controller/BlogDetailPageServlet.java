package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import DAO.BlogDetailPageDAO;
import DAO.BlogPageDAO;
import JoinTable.BlogJoinBlogCategory;
import JoinTable.CommentJoinCustomer;
import Model.Blog;
import Model.Comment;
import Model.Customer;
import Utilities.SharedMethod;

public class BlogDetailPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!SharedMethod.intCheck(request.getParameter("blogID"), 1, Integer.MAX_VALUE)) {
			response.sendRedirect("/Schon/NotFoundPageServlet");
			return;
		}
		int blogID = Integer.parseInt(request.getParameter("blogID"));
		
		BlogJoinBlogCategory blogJoinBlogCategory = BlogDetailPageDAO.getBlogJoinBlogCategory(blogID);
		if(blogJoinBlogCategory == null) {
			response.sendRedirect("/Schon/NotFoundPageServlet");
			return;
		}
		
		request.setAttribute("blogJoinBlogCategory", blogJoinBlogCategory);
		request.setAttribute("blogImageLink", BlogDetailPageDAO.getBlogImageLink(blogID));
		
		ArrayList<CommentJoinCustomer> commentJoinCustomerList = BlogDetailPageDAO.getCommentJoinCustomerList(blogID);
		
		request.setAttribute("blogCommentCount", commentJoinCustomerList.size());
		request.setAttribute("commentJoinCustomerList", commentJoinCustomerList);
		
		request.setAttribute("blogCategoryList", BlogDetailPageDAO.getBlogCategoryList());
		request.setAttribute("latestBlogList", BlogDetailPageDAO.getLatestBlogList(
				blogJoinBlogCategory.getBlogCategory().getBlogCategoryID()
		));
		request.setAttribute("latestBlogImageList", BlogDetailPageDAO.getBlogImageJoinImageList(2));
		
		//Part1 start
		request.setAttribute("menuList", BlogDetailPageDAO.getMenuList());
		request.setAttribute("productCategoryList", BlogDetailPageDAO.getProductCategoryList());

		Cookie[] cookies = request.getCookies();
		String userName = "";
		String password = "";

		if(cookies != null)
			for(Cookie item : cookies) {
				if(item.getName().equals("userName"))
					userName = item.getValue();
				if(item.getName().equals("password"))
					password = item.getValue();
			}

		request.setAttribute("userName", userName);
		request.setAttribute("password", password);
		//Part1 end


		//Part2 start
		int cartCount = 0;
		int wishListCount = 0;

		HttpSession session = request.getSession();
		Customer customer = null; 
		if(session.getAttribute("customer") != null)
			customer = (Customer)session.getAttribute("customer");

		if(customer != null)
			if(BlogDetailPageDAO.checkCustomerID(customer.getCustomerID())) {
				cartCount = BlogDetailPageDAO.getCartCount(customer.getCustomerID());
				wishListCount = BlogDetailPageDAO.getWishListCount(customer.getCustomerID());
			}

		request.setAttribute("cartCount", cartCount);
		request.setAttribute("wishListCount", wishListCount);
		//Part2 end

		request.getRequestDispatcher("/MainWeb/View/BlogDetailPage.jsp").forward(request, response);
	}

}
