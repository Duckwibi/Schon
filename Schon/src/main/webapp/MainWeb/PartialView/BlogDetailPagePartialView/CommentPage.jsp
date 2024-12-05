<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="Utilities.SharedMethod" %>

<div class="mt-comments-section">
	<div class="mt-comments-heading">
		<h2>COMMENTS</h2>
	</div>
	<ul class="list-unstyled commentContainer">
		<c:forEach var="item" items="${requestScope.commentJoinCustomerList }">
			<c:set var="co" value="${pageScope.item.comment }" scope="page"></c:set>
			<c:set var="cu" value="${pageScope.item.customer }" scope="page"></c:set>
			<li>
				<div class="txt">
					<h3>
						<a>${fn:escapeXml(pageScope.cu.firstName) } ${fn:escapeXml(pageScope.cu.lastName) }</a>
					</h3>
					<time class="mt-time">${SharedMethod.dateFormat("MMM dd, yyyy, hh:mm:ssaa", pageScope.co.createdDate) }</time>
					<p>${fn:escapeXml(pageScope.co.message) }</p>
				</div>
			</li>
		</c:forEach>
	</ul>
	<!-- Mt Leave Comments of the Page -->
	<div class="mt-leave-comment">
		<h2>LEAVE A COMMENT</h2>
		<form onsubmit="return false;" class="comment-form">
			<fieldset>
				<div class="form-group">
					<textarea placeholder="Message" class="inputComment-Message"></textarea>
				</div>
				<button type="submit" class="form-btn buttonSubmitComment">Submit</button>
			</fieldset>
		</form>
	</div>
	<!-- Mt Leave Comments of the Page end -->
</div>