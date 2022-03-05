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
							style="font-weight: 400;">${msg}</h5>
					</th>
				</tr>
				<tr class="success">
					<th colspan="8">
						<h3 align="center" class="text text-primary display-4">Account
							Details</h3>
					</th>
				</tr>
<!-- 				<tr> -->
<!-- 					<td colspan="8" align="center"> Select Role <select -->
<!-- 						class="btn btn-default dropdown-toggle btn-secondary" id="roleSelector" -->
<!-- 						data-toggle="dropdown"> -->
<!-- 							<option class="btn btn-default dropdown-toggle" value="ADMIN">ADMIN</option> -->
<!-- 							<option class="btn btn-default dropdown-toggle" value="cw">CASE -->
<!-- 								WORKER</option> -->
<!-- 					</select> -->
<!-- 					</td> -->
<!-- 				</tr> -->
				<tr>
					<td>S.No</td>
					<td>Name</td>
					<td>Email</td>
					<td>Phone</td>
					<td>Gender</td>
					<td>Role</td>
					<td align="center">Action</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="list" varStatus="index">
					<tr>
						<td>${index.count}</td>
						<td>${list.fname} ${list.lname}</td>
						<td>${list.email}</td>
						<td>${list.mobileNo}</td>
						<td>${list.gender}</td>
						<td>${list.role}</td>
						<td><a href="loadeditpage?id=${list.adminId}"
							class="btn btn-success btn-sm">EDIT</a> &nbsp; <c:if
								test="${list.deleteStatus == 'INACTIVE'}">
								<a href="updateaccountstatus?id=${list.adminId}&&status=ACTIVE"
									onclick="confirm('Are you sure?')" class="btn btn-success btn-sm">ACTIVATE</a>
							</c:if> <c:if test="${list.deleteStatus=='ACTIVE'}">
								<a href="updateaccountstatus?id=${list.adminId}&&status=INACTIVE"
									onclick="confirm('Are you sure?')" class="btn btn-danger btn-sm">DELETE</a>
							</c:if></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>

</html>