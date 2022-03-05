<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<body>
	<%@include file="header.jsp"%>
	<div class="container" align="center">
		<form:form action="pwdRecovery" method="POST" modelAttribute="accModel">
			<table class="table-hover">
				<tr class="primary">
					<td colspan="2">
						<h2 class="text text-primary display-4">Recover Password Here</h2>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<h3 align="center" class="text text-danger">${msg}</h3>
					</td>
				</tr>
				<tr>
					<td><h5>Email :</h5></td>
					<td><form:input class="form-control" type="email" path="email"
							required="required" placeholder="INPUT EMAIL ADDRESS" /></td>
				</tr>
				<tr>
					<td align="center" colspan="2"><input class="btn btn-success"
						type="submit" value="Recover"></td>
				</tr>
			</table>
		</form:form>
	</div>

</body>
</html>