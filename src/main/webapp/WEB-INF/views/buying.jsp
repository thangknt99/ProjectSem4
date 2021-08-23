<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="menubar.jsp"%>
<!DOCTYPE html>
<html>
<head>
<style>
table, th, td {
	border: 1px solid black;
}
</style>
<title>Login</title>
</head>


<body>
<c:if test="${loginedUser.level eq 0}">
<form action="addtobuy">
		<h3>Tên sách</h3>
		<input type="text" name="bookName">
		<h3>Tác giả</h3>
		<input type="text" name="authorName">
		<input type="submit" value="AddNew">
	</form>
	</c:if>
	
	
<c:if test="${loginedUser.level eq 1}">
<table style="width: 100%">
		<tr>
			<th>Tên sách</th>
			<th>Tác giả</th>
			<th>Tools</th>
		</tr>
	<c:forEach items="${buyingList}" var="book">
	
	<tr>
	
	<td>${book.title}</td>
	<td>${book.authorName}</td>
	<td>
	
	
	<form action="addtodb">
	<input type="hidden" name="bookName" value="${book.title}"> 
	<input type="hidden" name="authorName" value="${book.authorName}">
	
	<input type="submit" value="AddBook">
	
	</form>
	<form action="deletebuying">
	<input type="hidden" name="bookName" value="${book.title}"> 
	<input type="hidden" name="authorName" value="${book.authorName}">
	
	<input type="submit" value="Delete">
	
	</form>
	
	</td>
		
	</tr>
	</c:forEach>
	</table>
	<h3>${sms}</h3>
	</c:if>
	
</body>
</html>