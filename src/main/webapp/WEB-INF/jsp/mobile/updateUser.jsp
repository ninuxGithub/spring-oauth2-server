<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>update</title>
<style>
	.modifyPanel{
		margin-top: 30px;
	}
	.align-center{
		width: 80px;
		margin: auto;
	}
	.name.ng-valid {
		background-color: lightgreen;
	}
	
	.name.ng-dirty.ng-invalid-required {
		background-color: red;
	}
	
	.name.ng-dirty.ng-invalid-minlength {
		background-color: yellow;
	}
	
	
	</style>
	
	<script src="${pageContext.request.contextPath}/resources/angular.min.js"></script>
	<link href="${contextPath}/resources/bootstrap.min.css" rel="stylesheet" />

</head>
<body>

	<div class="panel panel-default modifyPanel">
			<div class="panel-heading"><span class="lead">Modify User Password</span></div>
			<div class="formcontainer">
				<form:form action="${pageContext.request.contextPath}/mobile/changePassword" commandName="userForm" class="form-horizontal">
					<form:hidden path="id" />
					
					<div class="row">
						<div class="form-group col-md-10">
							<label class="col-md-2 control-label" for="file">UserName</label>
							<div class="col-md-7">
								<form:input path="username" class=" form-control input-sm" readonly="true" placeholder="Enter User Name" required="required"/>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="form-group col-md-10">
							<label class="col-md-2 control-label" for="file">Old Password</label>
							<div class="col-md-7">
								<form:password path="password"  class=" form-control input-sm" placeholder="Enter New Password" required="required"/>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-10">
							<label class="col-md-2 control-label" for="file">New Password</label>
							<div class="col-md-7">
								<form:password path="repassword"  class=" form-control input-sm" placeholder="Enter New Password" required="required"/>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-10">
							<form:errors path="*" style="color:red;"/>
						</div>
					</div>
					
					
					<div class="row">
						<div class="col-md-2 " style=" float: right;" >
							<input type="submit" value="修改" class="btn btn-primary btn-sm" />
							<button type="reset" class="btn btn-warning btn-sm" >取消</button>
						</div>
					</div>
				</form:form>
				
			</div>
		</div>

</body>
</html>