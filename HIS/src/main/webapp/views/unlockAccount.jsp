<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js">
	
</script>

<script>
	function validatePwd() {
		var newPwd = $('#newPwd').val();
		var confirmPwd = $('#confirmNewPwd').val();
		if (newPwd != confirmPwd) {
			$('#errID').text(
					"New Password and Confirm Password should be same.");
			return false;
		}
		return true;
	}
</script>
<title>Health Insurance System</title>
</head>
<body>
	<%@include file="header.jsp"%>
	<div class="container" align="center">
		<font color="red" size="4"> <span id="errID"></span>
		</font> <font color="red" size="4"> ${msg} </font>

		<form:form action="unlockAdminAccount" method="POST"
			modelAttribute="unlockAccountModel">
			<table class="table-hover">
				<tr class="primary">
					<td colspan="2">
						<h1 class="text text-primary display-4">Unlock Account !</h1>
					</td>
				</tr>
				<tr>
					<td><h5>Your Email :</h5></td>
					<td><form:input class="form-control" path="email"
							readonly="true" /></td>
				</tr>
				<tr>
					<td><h5>Enter Temporary Password :</h5></td>
					<td><form:password class="form-control" path="tempPwd"
							required="required" /></td>
				</tr>
				<tr>
					<td><h5>Enter New Password :</h5></td>
					<td><form:password class="form-control" path="newPwd"
							required="required" /></td>
				</tr>
				<tr>
					<td><h5>Confirm New Password :</h5></td>
					<td><form:password class="form-control" path="confirmNewPwd"
							required="required" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input class="btn btn-success"
						type="submit" value="ACTIVATE ACCOUNT"
						onclick="javascript:return validatePwd()"></td>
				</tr>

			</table>
		</form:form>
	</div>
</body>
</html>