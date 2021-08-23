<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	background-color: #333;
}

li {
	float: left;
}

li a {
	display: block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

li a:hover {
	background-color: #111;
}
</style>
</head>

<body>
	<c:if test="${loginedUser.level eq 1}">
		<h2>&Dagger;&nbsp;Cấp độ: QUẢN LÝ&nbsp;&Dagger;</h2>
	<ul>
		<li><a href="book">Quản lý sách</a></li>
		<li><a href="user">Quản lý người dùng</a></li>
		<li><a href="author">Quản lý tác giả</a></li>
		<li><a href="#">Mượn/ gia hạn sách</a></li>
		<li><a href="buying">Mua bổ sung</a></li>
		<li><a href="http://localhost:8080/SpringMVC">Đăng xuất</a></li>
	</ul>
	</c:if>

	<c:if test="${loginedUser.level eq 0}">
		<h2>&dagger;&nbsp;Cấp độ: Khách hàng&nbsp;&dagger;</h2>
		<ul>
		<li><a href="#">Thông tin cá nhân</a></li>
		<li><a href="#">Sách trong thư viện</a></li>
		<li><a href="#">Sách yêu thích</a></li>
		<li><a href="#">Mượn/ gia hạn sách</a></li>
		<li><a href="buying">Mua bổ sung</a></li>
		<li><a href="http://localhost:8080/SpringMVC">Đăng xuất</a></li>
	</ul>
	</c:if>
	



</body>
</html>