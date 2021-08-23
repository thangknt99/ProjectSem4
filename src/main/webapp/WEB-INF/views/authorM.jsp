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
	<h1>Danh sách các tác giả</h1>
	<hr>
	<form action="addAuthor">
		<h3>Tác giả</h3>
		<input type="text" name="authorName"> <input type="submit"
			value="AddNew">
	</form>
	<br>
	<c:if test="${message eq 1}">
		<h3>Tác giả đã được thêm vào!</h3>
	</c:if>
	<c:if test="${message eq 0}">
		<h3>Hủy thao tác, tác giả đã có!</h3>
	</c:if>
	<br>
	<table>
		<tr>
			<th>Tác giả</th>
			<th>Tiểu sử</th>
			<th>Tools</th>
		</tr>
		<c:forEach items="${authorList}" var="au">
			<tr>
				<td>${au.nameAuthor}</td>

				<td>

					<form action="changeDescription">
						<input type="hidden" name="authorId" value="${au.authorId}">
						<input style="width: 950px" type="text" name="description"
							value="${au.description}" id="myselect"
							onchange="this.form.submit()">
					</form>
				</td>
				<td><input type="button" value="Notthing here"></td>
			</tr>
		</c:forEach>

	</table>
</body>
</html>