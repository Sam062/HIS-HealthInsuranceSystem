<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta>
<title>View All Accounts</title>
<link
	href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css"
	rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script
	src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
<script>
	$(document).ready(function() {
		$('#ACCNT_DTLS').DataTable({
			"pagingType" : "full_numbers"
		});
	});
	function deleteConfirm() {
		return confirm("Are you sure, You want to Delete?");
	}
</script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="container" align="center">
		<table class="table table-striped" border="1" id="ACCNT_DTLS">
			<thead>
				<tr class="success">
					<th colspan="9" class=""><h3 align="center"
							class="text text-primary">All Accounts</h3></th>
				</tr>
				<tr>
					<th>S.No</th>
					<th>Name</th>
					<th>Email</th>
					<th>Phone</th>
					<th>Gender</th>
					<th>Role</th>
					<th>Status</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="list" varStatus="index">
					<tr>
						<td>${index.count }</td>
						<td>${list.fname}&nbsp;${list.lname}</td>
						<td>${list.email}</td>
						<td>${list.mobileNo}</td>
						<td>${list.gender}</td>
						<td>${list.role}</td>
						<td>${list.accountStatus}</td>
						<td><a href="edit?id=" class="btn btn-success btn-sm">EDIT</a>
							&nbsp; <a href="delete?id=" onclick="deleteConfirm()"
							class="btn btn-danger btn-sm">DELETE</a></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>
		<h3 class="text-success">${msg}</h3>
	</div>

</body>
</html>