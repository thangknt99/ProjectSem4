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
	<h1>Danh sách tài khoản</h1>
	<hr>
	<table style="width: 100%">
		<tr>
			<th>Tài khoản</th>
			<th>Cấp độ</th>
		</tr>
	<c:forEach items="${userList}" var="user">
	<tr>
	<td>${user.userId}</td>
	<td>  
	<c:if test="${user.level eq 1}">
	Quản lý
	</c:if>
	<c:if test="${user.level eq 0}">
	Khách hàng
	</c:if>
	</td>
		
	</tr>
	</c:forEach>
	</table>
</body>
</html>