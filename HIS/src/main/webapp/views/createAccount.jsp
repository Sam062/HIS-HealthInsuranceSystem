<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script>
	$(document).ready(function(e) {
		$("#email").blur(function(event) {
			$("#dupEmail").html("");
			var emailId = $("#email").val();
			$.ajax({
				url : 'emailVaidation?email=' + emailId,
				success : function(response) {
					if (response == 'duplicate') {
						$("#dupEmail").html("Email already Exist");
						$("#email").focus();
					}
				}
			});
		});
	});
</script>
<title>Admin Account Create</title>
</head>
<body>
	<%@include file="AdminDashboardHeader.jsp"%>
	<div class="container" align="center">
		<h4 class="text text-info">${msg }</h4>
		<form:form action="AdminCreateAccount" method="POST"
			modelAttribute="adminModel">
			<table class="table-hover">
				<tr class="primary">
					<td colspan="2">
						<h2 class="text text-primary display-4">Create Account</h2>
					</td>
				</tr>
				<tr>
					<td><h5>First Name</h5></td>
					<td><form:input class="form-control" path="fname"
							placeholder="INPUT FIRST NAME" required="required" /></td>
				</tr>
				<tr>
					<td><h5>Last Name</h5></td>
					<td><form:input class="form-control" path="lname"
							required="required" placeholder="INPUT LAST NAME" /></td>
				</tr>
				<tr>
					<td><h5>Email</h5></td>
					<td><form:input class="form-control" type="email" path="email"
							required="required" placeholder="INPUT EMAIL ADDRESS" /><font
						color='red'>
							<div id="dupEmail"></div>
					</font></td>
				</tr>
				<tr>
					<td><h5>Mobile</h5></td>
					<td><form:input class="form-control" path="mobileNo"
							required="required" placeholder="INPUT MOBILE NUMBER" /></td>
				</tr>
				<tr>
					<td><h5>Gender</h5></td>
					<td><form:radiobutton class="radio-inline" required="required"
							path="gender" value="M" />&nbsp;MALE <form:radiobutton
							class="radio-inline" path="gender" value="F" />&nbsp;FEMALE</td>
				</tr>
				<tr>
					<td><h5>Role</h5></td>
					<td><form:select class="btn btn-default dropdown-toggle"
							type="button" data-toggle="dropdown" path="role"
							required="required">
							<form:option class="btn btn-default dropdown-toggle" value=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- SELECT -&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</form:option>
							<form:option class="btn btn-default dropdown-toggle" value="Admin">ADMIN</form:option>
							<form:option class="btn btn-default dropdown-toggle" value="Case Worker">CASE WORKER</form:option>
						</form:select></td>
				</tr>
				<tr>
					<td align="right"><input class="btn btn-danger" type="reset"
						value="RESET"></td>
					<td align="center"><input class="btn btn-success"
						type="submit" value="CREATE"></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>