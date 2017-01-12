<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML>
<html>
<head>
<title>authorization_code</title>
</head>
<body>
	<a href="${pageContext.request.contextPath}/success">Home</a>

	<h2>
		authorization_code <small>用 'access_token' 去访问spring-oauth-server 的API</small>
	</h2>
	<br />


	<div class="panel panel-default">
		<div class="panel-heading">
			步骤3:
			<code>用 'access_token' 去访问 spring-oauth-server 的API</code>
		</div>
		<div class="panel-body">
		
			<p>获取access_token成功, 现在可以访问spring-oauth-server资源了,
				以下提供两种方式去访问spring-oauth-server资源(或API).</p>
			<ul>
				<li>方式1: 调用本地的接口,由后台去向服务器获取资源并进行处理(如将JSON数据转化成对象), 通过页面展示信息 <br />
					<a href="${contextPath}/unity/user_info?access_token=${access_token}">Oauth
						Server 用户信息</a>
				</li>
				<li>方式2: 直接通过access_token去访问服务器的资源(该方式将直接获取JSON数据) <br /> <a
					href="${contextPath}/mobile/user_info?access_token=${access_token}"
					target="_blank">Oauth Server 用户信息 [JSON]</a>
				</li>
				<li>...</li>
			</ul>
			<p class="help-block">
				<em class="glyphicon glyphicon-info-sign"></em> 至于使用哪一种方式,
				在实际中请根据具体的需求或服务器资源提供的访问方式去选择
			</p>
		</div>
	</div>
</body>
</html>