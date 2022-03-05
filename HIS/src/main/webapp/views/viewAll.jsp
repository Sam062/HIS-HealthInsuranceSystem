<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<body>
	<%@ include file="AdminDashboardHeader.jsp"%>
	<div class="container" align="center">
		<table class="table table-striped" border="1" id="ACCNT_DTLS">
			<thead>
				<tr class="success">
					<th colspan="8">
						<h5 align="center" class="text text-danger"
							style="font-weight: 400;">${msg} No Accounts found.</h5>
					</th>
				</tr>
				<tr class="success">
					<th colspan="8">
						<h3 align="center" class="text text-primary display-4">Account
							Details</h3>
					</th>
				</tr>
				<tr>
					<td colspan="8" align="center">Select Role <select
						class="btn btn-default dropdown-toggle" id="roleSelector"
						data-toggle="dropdown">
							<option class="btn btn-default dropdown-toggle" value="ADMIN">ADMIN</option>
							<option class="btn btn-default dropdown-toggle" value="cw">CASE
								WORKER</option>
					</select>
					</td>
				</tr>
				<tr>
					<td>S.No</td>
					<td>Name</td>
					<td>Email</td>
					<td>Phone</td>
					<td>Gender
					<td>Role
					<td align="center">Action
				</tr>
			</thead>
		</table>
	</div>
</body>

</html>