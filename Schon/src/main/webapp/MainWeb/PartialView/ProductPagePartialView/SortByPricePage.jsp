<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul class="list-unstyled nice-form">
	<li>
		<label for="sort1">
			<c:choose>
				<c:when test="${requestScope.sortByPrice == 0 }">
					<input id="sort1" name="sortByPriceRadio" type="radio" value="0" checked>
				</c:when>
				<c:otherwise>
					<input id="sort1" name="sortByPriceRadio" type="radio" value="0">
				</c:otherwise>
			</c:choose>
			<span class="fake-input"></span> 
			<span class="fake-label">ASC by price</span>
		</label>
	</li>
	<li>
		<label for="sort2">
			<c:choose>
				<c:when test="${requestScope.sortByPrice == 1 }">
					<input id="sort2" name="sortByPriceRadio" type="radio" value="1" checked> 
				</c:when>
				<c:otherwise>
					<input id="sort2" name="sortByPriceRadio" type="radio" value="1"> 
				</c:otherwise>
			</c:choose>
			<span class="fake-input"></span> 
			<span class="fake-label">DESC by price</span>
		</label>
	</li>
</ul>