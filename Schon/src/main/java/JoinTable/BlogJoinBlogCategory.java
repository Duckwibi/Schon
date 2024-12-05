package JoinTable;

import Model.Blog;
import Model.BlogCategory;

public class BlogJoinBlogCategory {
	private Blog blog;
	private BlogCategory blogCategory;
	public BlogJoinBlogCategory() {
		super();
	}
	public BlogJoinBlogCategory(Blog blog, BlogCategory blogCategory) {
		super();
		this.blog = blog;
		this.blogCategory = blogCategory;
	}
	public Blog getBlog() {
		return blog;
	}
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	public BlogCategory getBlogCategory() {
		return blogCategory;
	}
	public void setBlogCategory(BlogCategory blogCategory) {
		this.blogCategory = blogCategory;
	}
}
