package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import DAO.BlogPageDAO;
import DAO.HomePageDAO;
import Model.BlogCategory;
import Model.Customer;
import Utilities.SharedMethod;

public class BlogPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!SharedMethod.intCheck(request.getParameter("blogCategoryID"), 1, Integer.MAX_VALUE)) {
			response.sendRedirect("/Schon/NotFoundPageServlet");
			return;
		}
		int blogCategoryID = Integer.parseInt(request.getParameter("blogCategoryID"));
		
		BlogCategory blogCategory = BlogPageDAO.getBlogCategory(blogCategoryID);
		if(blogCategory == null) {
			response.sendRedirect("/Schon/NotFoundPageServlet");
			return;
		}
		
		request.setAttribute("blogCategory", blogCategory);
		request.setAttribute("blogCategoryList", BlogPageDAO.getBlogCategoryList());
		request.setAttribute("latestBlogList", BlogPageDAO.getLatestBlogList(blogCategoryID));
		request.setAttribute("latestBlogImageList", BlogPageDAO.getBlogImageJoinImageList(2));
		
		int rowCount = BlogPageDAO.getBlogListRowCount(blogCategoryID);
		int pageCount = SharedMethod.getPageCount(rowCount);
		int currentPage = 1;
		
		if(SharedMethod.intCheck(request.getParameter("currentPage"), 1, pageCount))
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		int nextThreePage = SharedMethod.getNextThreePage(currentPage, pageCount);
		
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("nextThreePage", nextThreePage);
		
		request.setAttribute("blogJoinBlogCategoryList", BlogPageDAO.getBlogJoinBlogCategoryList(blogCategoryID, currentPage));
		request.setAttribute("blogImageJoinImageList", BlogPageDAO.getBlogImageJoinImageList(1));
		request.setAttribute("commentOfBlogCountList", BlogPageDAO.getCommentOfBlogCountList());
		
		//Part1 start
		request.setAttribute("menuList", BlogPageDAO.getMenuList());
		request.setAttribute("productCategoryList", BlogPageDAO.getProductCategoryList());

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
			if(BlogPageDAO.checkCustomerID(customer.getCustomerID())) {
				cartCount = BlogPageDAO.getCartCount(customer.getCustomerID());
				wishListCount = BlogPageDAO.getWishListCount(customer.getCustomerID());
			}

		request.setAttribute("cartCount", cartCount);
		request.setAttribute("wishListCount", wishListCount);
		//Part2 end
		
		request.getRequestDispatcher("/MainWeb/View/BlogPage.jsp").forward(request, response);
	}


}
