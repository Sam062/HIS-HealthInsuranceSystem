<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Create Plan</title>
</head>
<body>
	<%@include file="AdminDashboardHeader.jsp"%>
	<div class="container" align="center">
		<h4 class="text text-info">${msg }</h4>
		<form:form action="createPlan" method="POST"
			modelAttribute="planModel">
			<form:hidden class="form-control" path="planId"/>
			<table class="table-hover">
				<tr class="primary">
					<td colspan="2">
						<h2 class="text text-primary display-4">Create Plan</h2>
					</td>
				</tr>
				<tr>
					<td><h5>Plan Name</h5></td>
					<td><form:input class="form-control" path="planName"
							placeholder="INPUT PLAN NAME" required="required" /></td>
				</tr>
				<tr>
					<td><h5>Plan Description</h5></td>
					<td><form:input class="form-control" path="planDescription"
							required="required" placeholder="INPUT PLAN DESC" /></td>
				</tr>
				<tr>
					<td><h5>Plan Start Date</h5></td>
					<td><form:input type="date" class="form-control"
							path="createdDate" required="required" /></td>
				</tr>
				<tr>
					<td><h5>Plan End Date</h5></td>
					<td><form:input type="date" class="form-control"
							path="expiryDate" required="required" /></td>
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