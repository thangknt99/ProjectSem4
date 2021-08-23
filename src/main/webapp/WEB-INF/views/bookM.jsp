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

	<h1>Danh sách sách tại thư viện</h1>
	<hr>

	<c:if test="${message eq 1}">
		<h3>Sách đã được thêm vào thư viện</h3>
	</c:if>
	<c:if test="${message eq 0}">
		<h3>Hủy thao tác, sách đã có trong thư viện</h3>
	</c:if>

	<table style="width: 100%">
		<tr>
			<th>Tên sách</th>
			<th>Tác giả</th>
			<th>Tình trạng</th>
			<th>Trong kho</th>
			<th>Ngày nhập sách</th>
			<th>Thay đổi gần nhất</th>
			<th>Tools</th>
		</tr>
		<c:forEach items="${listBook}" var="book">
			<tr>
				<td>${book.title}</td>
				<td>${book.authorName}</td>
				<td>
					<form action="statusbook">
						<input type="hidden" name="uuid" value="${book.uuid}"> <select
							name="status" id="myselect" onchange="this.form.submit()">
							<c:if test="${book.status eq 1}">
								<option value="1" selected>Có thể đăng kí mượn</option>
							</c:if>
							<c:if test="${book.status eq 2}">
								<option value="2" selected>Hết sách hoặc đang có người mượn</option>
							</c:if>
							<c:if test="${book.status eq 3}">
								<option value="3" selected>Không hiện thị cho người dùng</option>
							</c:if>
							<c:if test="${book.status eq 4}">
								<option value="4" selected>Đăng kí mua hoặc đang nhập kho</option>
							</c:if>
								<option value="1">Có thể đăng kí mượn</option>
								<option value="2">Hết sách hoặc đang có người mượn</option>
								<option value="3">Không hiện thị cho người dùng</option>
								<option value="4">Đăng kí mua hoặc đang nhập kho</option>
						</select>
					</form>
				</td>
				<td>${book.stock}</td>
				<td>${book.datemodifiles}</td>
				<td>${book.datetoupdate}</td>
				<td>
					<form action="editbook" method="POST">
						<input type="hidden" name="uuid" value="${book.uuid}"> <input
							type="submit">
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>


	<form action="addbook">
		<h3>Tên sách</h3>
		<input type="text" name="bookName">
		<h3>Tác giả</h3>
		<input type="text" name="authorName">
		<input type="submit" value="AddNew">
	</form>


	<c:forEach items="${bookEdit}" var="book">
		<form action="editbook" method="GET">
			<input type="hidden" name="uuid" value="${book.uuid}">
			<h3>Tên sách</h3>
			<input type="text" name="bookName" value="${book.title}">
			<h3>Tác giả</h3>
			<input type="text" name="authorName" value="${book.authorName}">
			<h3>Tình trạng</h3>
			${book.status}
			<h3>Trong kho</h3>
			<input type="text" name="stock" value="${book.stock}"> <input
				type="submit" value="Edit">
		</form>
	</c:forEach>

</body>
</html>