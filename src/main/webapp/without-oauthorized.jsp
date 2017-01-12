<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Error Happened</title>
</head>
<body>
	<h2 class="page-header">Error Happened</h2>
	<a href="${pageContext.request.contextPath}/">Home</a>
	<div class="row">
		<div class="col-md-12">
			<div class="well well-sm">
				<p>您当前要访问的地址为：${param.requestUrl}</p>

				<p>导致的原因：${param.factor}</p>
				<p>导致的原因：${param.localMsg}</p>
				
				<p><a class="btn btn-primary" href="${pageContext.request.contextPath}/${param.pathUrl}" >${param.action}</a></p>
			</div>
		</div>
	</div>

</body>
</html>