<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Home</title>
<c:set var="contextPath" value="${pageContext.request.contextPath}"	scope="application" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-3.1.1.min.js" ></script>
<script type="text/javascript">
	$(function(){
		
		$('#btn').click(function(){
			$.post(
				"http://localhost:8080/spring-oauth2-server/mobile/user_info",
				{"access_token":"7568af9a-6027-4c6b-b87c-4a691ab643c1"},
				function(data){
					
					$('#data').html(JSON.stringify(data));
				}				
			);
			
		});
		
	});
</script>

</head>
<body>
	<h2>
		Spring Security OAuth2 <small class="badge" title="Version">1.0</small>
	</h2>
	
	<div>
		<span id="data"></span>
	</div>

	<p>
		<a href="${contextPath}/toLogin">Normal login</a>
	</p>
	<div>
		<div id="btn" class="btn btn-primary">发送请求</div>
	</div>

	<div>
		操作说明: <span style="color: red;">${param.errMsg }</span>
		<ol>
			<li>
				<p>菜单 User 是不需要OAuth 验证即可访问的(即公开的resource); 用于管理用户信息(添加,删除等).</p>
			</li>
			<li>
				<p>
					菜单 Unity 与 Mobile 需要OAuth 验证后才能访问(即受保护的resource); <br /> Unity 需要
					[ROLE_UNITY] 权限(resourceId:
					<mark>unity-resource</mark>
					), Mobile 需要 [ROLE_MOBILE] 权限(resourceId:
					<mark>mobile-resource</mark>
					).
				</p>
			</li>
			<li>
				<p>
					若需要自定义
					<code>client_details</code>
					数据并进行测试, 可进入<a href="client_details">client_details</a>去手动添加
					<code>client_details</code>
					或删除已创建的
					<code>client_details</code>
					.
				</p>
			</li>
		</ol>
	</div>
</body>
</html>