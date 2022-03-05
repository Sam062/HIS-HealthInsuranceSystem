<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<body>
	<%@include file="CaseWorkerDashboardHeader.jsp"%>
	<div class="container" align="center">
		<h4 class="text text-info">${msg }</h4>
		<form:form action="registerApplicant" method="POST"
			modelAttribute="applicantModel">
			<form:hidden class="form-control" path="applicantId" />
			<table class="table-hover">
				<tr class="primary">
					<td colspan="2">
						<h2 class="text text-primary display-4">Applicant
							Registration</h2>
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
					<td><h5>Gender</h5></td>
					<td><form:radiobutton class="radio-inline" required="required"
							path="gender" value="M" />&nbsp;MALE <form:radiobutton
							class="radio-inline" path="gender" value="F" />&nbsp;FEMALE</td>
				</tr>
				<tr>
					<td><h5>DOB</h5></td>
					<td><form:input type="date" class="form-control"
							path="dateOfBirth" required="required" /></td>
				</tr>
				<tr>
					<td><h5>SSN Number</h5></td>
					<td><form:input class="form-control" path="ssnNumber"
							placeholder="INPUT SSN NUMBER" required="required" /></td>
				</tr>
				<tr>
					<td><h5>Email</h5></td>
					<td><form:input type="email" class="form-control" path="email"
							placeholder="INPUT EMAIL" required="required" /></td>
				</tr>
				<tr>
					<td align="right"><input class="btn btn-danger" type="reset"
						value="RESET"></td>
					<td align="center"><input class="btn btn-success"
						type="submit" value="REGISTER"></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>