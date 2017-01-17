<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML>
<html>
<head>
<title>User Overview</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/jquery-3.1.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/jquery.pager.js"></script>
<link href="${pageContext.request.contextPath}/resources/Pager.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript">
	$(function() {
		//分页开始
		$("#pager").pager({
			pagenumber : '${pageNo}',
			pagecount : '${pagination.pageCount}',
			buttonClickCallback : pageClick
		});
	});

	//分页点击函数
	pageClick = function(pageNo) {
		var form = $('<form action="${pageContext.request.contextPath}/user/overview" method="post"></form>');
		var pageNoInput = $('<input type="hidden" name="pageNo"/>');
		pageNoInput.val(pageNo);
		pageNoInput.appendTo(form);
		form.appendTo('body');
		form.submit();
	};
</script>


</head>

<body>
	<a href="${pageContext.request.contextPath}/success">Home</a>

	<h2>User Overview</h2>

	<div class="pull-right">
		<a href="form/plus" class="btn btn-success btn-sm">Add User</a>
	</div>
	<form action="" class="form-inline">
		<div class="form-group">
			<input type="text" class="form-control" placeholder="Type username"
				name="username" value="${overviewDto.username}" />
		</div>
		<button type="submit" class="btn btn-default">Search</button>
		&nbsp;<span class="text-info">Total: ${overviewDto.size}</span>
	</form>
	<br />

	<div>
		<table class="table table-bordered table-hover table-striped">
			<thead>
				<tr>
					<th>Username</th>
					<th>Privileges</th>
					<th>Phone</th>
					<th>Email</th>
					<th>CreateTime</th>
					<th>Operation</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pagination.pageList}" var="user">
					<tr>
						<td>${user.username}</td>
						<td>${user.privileges}</td>
						<td>${user.phone}</td>
						<td>${user.email}</td>
						<td>${user.createTime}</td>
						<td><a
							href="${pageContext.request.contextPath}/user/toUpdate/${user.id}">修改</a>
							&nbsp;&nbsp; <a
							href="${pageContext.request.contextPath}/user/deleteUser/${user.id}">刪除</a>

						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div id="pager"></div>
	</div>
</body>
</html>