<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="Utilities.SharedMethod" %>

<article class="blog-post detail">
	<div class="img-holder">
		<a>
			<img src="${fn:escapeXml(requestScope.blogImageLink) }" alt="image description">
		</a>
	</div>
	<time class="time" datetime="2016-02-03 20:00">
		<strong>${SharedMethod.dateFormat("dd", pageScope.b.createdDate) }</strong>
		${SharedMethod.dateFormat("MMM", pageScope.b.createdDate) }
	</time>
	<div class="blog-txt">
		<h2>
			<a>${fn:escapeXml(pageScope.b.title) }</a>
		</h2>
		<ul class="list-unstyled blog-nav">
			<li><a><i class="fa fa-clock-o"></i>${SharedMethod.dateFormat("dd MMM yyyy", pageScope.b.createdDate) }</a></li>
			<li><a><i class="fa fa-list"></i>${fn:escapeXml(pageScope.bc.name) }</a></li>
			<li><a><i class="fa fa-comment"></i><span class="commentCount">${requestScope.blogCommentCount }</span> Comments</a></li>
		</ul>
		<p>${fn:escapeXml(pageScope.b.content) }</p>
	</div>
</article>