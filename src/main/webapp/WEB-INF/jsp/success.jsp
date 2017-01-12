<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Success</title>
</head>
<body>
	<h2 class="page-header">Success | <a href="${contextPath}/doLogout">Logout</a></h2>
	<div class="row" style="color: red;">当前登陆用户：${sessionScope.login_user.username} </div>
	
	<div class="row">
		菜单
		<ul>
			<li>
				<p>
					<a href="resources/api/SOS_API-1.0.html" target="_blank">API</a> <span
						class="text-muted">- 查看提供的API文档[公共]</span>
				</p>
			</li>
			<li>
				<p>
					<a href="client_details">client_details</a>
					<span
						class="text-muted">- 管理Client-Detail 资源(resource), 需要具有 [ROLE_ADMIN]
						权限(resourceId: <mark>admin-resource</mark>才能访问
					</span>
				</p>
			</li>
			<li>
				<p>
					<a href="${contextPath}/user/overview">User</a> 
					<span
						class="text-muted">- 管理User 资源(resource), 需要具有 [ROLE_ADMIN]
						权限(resourceId: <mark>admin-resource</mark>才能访问
					</span>
				</p>
			</li>
			<li>
				<p>
					<a href="${contextPath}/unity/dashboard">Unity</a> 
					<span
						class="text-muted">- Unity 资源(resource), 需要具有 [ROLE_UNITY]
						权限(resourceId: <mark>unity-resource</mark>才能访问
					</span>
				</p>
			</li>
			<li>
				<p>
					<a href="${contextPath}/mobile/dashboard">Mobile</a> 
					<span
						class="text-muted">- Mobile资源(resource), 需要具有 [ROLE_MOBILE]
						权限(resourceId: <mark>mobile-resource</mark>才能访问
					</span>
				</p>
			</li>
		</ul>
	</div>
</body>
</html>