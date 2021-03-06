<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fun" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Update User</title>
</head>
<body>
	<a href="${pageContext.request.contextPath}/success">Home</a>

	<h2>Update User</h2>

	<form:form action="${pageContext.request.contextPath}/user/update"
		commandName="formDto" cssClass="form-horizontal">
		<div class="form-group">
			<label class="col-sm-2 control-label">ClientId<em class="text-danger">*</em></label>
			<div class="col-sm-10">
				<form:input path="clientId" cssClass="form-control"
					placeholder="Type clientid (在client_details注册页面注册之后的client_id)" required="required" />
				<p class="help-block">ClientId, unique.</p>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-2 control-label">Username<em class="text-danger">*</em></label>
			<div class="col-sm-10">
				<form:input path="username" cssClass="form-control" placeholder="Type username" required="required" />
				<p class="help-block">Username, unique.</p>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Password<em class="text-danger">*</em></label>
			<div class="col-sm-10">
				<form:password path="password" cssClass="form-control" placeholder="Type password" required="required" />
				<p class="help-block">Password, required.</p>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Privileges<em class="text-danger">*</em></label>
			<div class="col-sm-10">
				<div class="col-sm-10">
					<c:forEach var="tag" items="${tags}">
						<label class="checkbox-inline"> 
							<form:checkbox path="privileges" value="${tag.name}" /> ${tag.name}
						</label>
					</c:forEach>
					<p class="help-block">Select Privilege(s).</p>
				</div>
				<p class="help-block">Select Privilege(s).</p>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label">Phone</label>
			<div class="col-sm-10">
				<form:input path="phone" cssClass="form-control" placeholder="Type phone" />
				<p class="help-block">User phone, optional.</p>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Email</label>
			<div class="col-sm-10">
				<form:input path="email" cssClass="form-control" placeholder="Type email" />
				<p class="help-block">User email, optional.</p>
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-2"></div>
			<div class="col-sm-10">
				<form:errors path="*" cssClass="label label-warning" />
			</div>
		</div>


		<div class="form-group">
			<div class="col-sm-2"></div>
			<div class="col-sm-10">
				<button type="submit" class="btn btn-success">Update</button>
				<a href="../overview">Cancel</a>
			</div>
		</div>
		<form:hidden path="id" />
		<form:hidden path="guid" />

	</form:form>

</body>
</html>