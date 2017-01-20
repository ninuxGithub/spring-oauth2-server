




<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML>
<html>
<head>
	<title>authorization_code</title>
	<script src="${pageContext.request.contextPath}/resources/angular.min.js"></script>
	
</head>
<body>
	<a href="${pageContext.request.contextPath}/success">Home</a>

	<h2>
		authorization_code <small>从 spring-oauth-server获取 'code'</small>
	</h2>
	<br />

	<div ng-app="auth_code_app"  class="panel panel-default">
		<div class="panel-heading">
			步骤1:
			<code>从 spring-oauth-server获取 'code'</code>
		</div>
		<div class="panel-body">
			<div ng-controller="AuthorizationCodeCtrl" class="col-md-10">

				<form action="authorization_code" method="post"	class="form-horizontal">
					<input type="hidden" name="userAuthorizationUri" value="${userAuthorizationUri}" />

					<div class="form-group">
						<label class="col-sm-2 control-label">authorizationUri</label>

						<div class="col-sm-10">
							<p class="form-control-static">
								<code>${userAuthorizationUri}</code>
							</p>
						</div>
					</div>

					<a href="javascript:void(0);" ng-click="showParams()">显示请求参数</a>

					<div ng-show="visible">
						<div class="form-group">
							<label class="col-sm-2 control-label">response_type</label>

							<div class="col-sm-10">
								<input type="text" name="responseType" readonly="readonly"
									class="form-control" ng-model="responseType" />

								<p class="help-block">固定值 'code'</p>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">scope</label>

							<div class="col-sm-10">
								<select name="scope" ng-model="scope" class="form-control">
									<option value="read">read</option>
									<option value="write">write</option>
									<option value="read write">read write</option>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">client_id</label>

							<div class="col-sm-10">
								<input type="text" name="clientId" required="required"
									class="form-control" ng-model="clientId" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">redirect_uri</label>

							<div class="col-sm-10">
								<input type="text" name="redirectUri" class="form-control" readonly="readonly"
									required="required" size="50" ng-model="redirectUri" />

								<p class="help-block">redirect_uri 是在
									'AuthorizationCodeController.java' 中定义的一个 URI, 用于检查server端返回的
									'code'与'state',并发起对 access_token 的调用</p>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">state</label>

							<div class="col-sm-10">
								<input type="text" name="state" size="50" class="form-control" readonly="readonly"
									required="required" ng-model="state" />

								<p class="help-block">一个随机值, spring-oauth-server
									将原样返回,用于检测是否为跨站请求(CSRF)等</p>
							</div>
						</div>

					</div>
					<br /> <br />
					<button type="submit" class="btn btn-primary">去登录</button>
					<span class="text-muted">将重定向到 'spring-oauth-server' 的登录页面</span> <span
						class="label label-info">GET</span>
				</form>

			</div>
		</div>
	</div>
<script type="text/javascript">
 		var auth_code_app = angular.module('auth_code_app', []).
			controller('AuthorizationCodeCtrl',[ '$scope', function($scope) {
			$scope.userAuthorizationUri = '${userAuthorizationUri}';
			$scope.responseType = 'code';
			$scope.scope = 'read write';

			$scope.clientId = '${clientId}';
			$scope.redirectUri = '${host}authorization_code_callback';
			$scope.state = '${state}';

			$scope.visible = false;

			$scope.showParams = function() {
				$scope.visible = !$scope.visible;
			};
		}] );
	</script>
</body>
</html>