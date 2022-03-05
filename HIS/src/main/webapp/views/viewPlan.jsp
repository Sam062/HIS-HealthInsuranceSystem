<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta>
<title>View All Plans</title>
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
						<h3 align="center" class="text text-primary display-4">Plan
							Details
					</th>
				</tr>
				<tr>
					<td>S.No</td>
					<td>Plan Name</td>
					<td>Plan Desc</td>
					<td>Start Date</td>
					<td>Expiry Date</td>
					<td align="center" colspan="2">Action</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="list" varStatus="index">
					<tr>
						<td>${index.count}</td>
						<td>${list.planName}</td>
						<td>${list.planDescription}</td>
						<td>${list.createdDate}</td>
						<td>${list.expiryDate}</td>
						<td><a href="loadplanedit?id=${list.planId}"
							class="btn btn-success btn-sm">EDIT</a> &nbsp; <c:if
								test="${list.delStatus == 'INACTIVE'}">
								<a href="changeplanstatus?id=${list.planId}&&status=ACTIVE"
									onclick="confirm()" class="btn btn-success btn-sm">ACTIVATE</a>
							</c:if> <c:if test="${list.delStatus=='ACTIVE'}">
								<a href="changeplanstatus?id=${list.planId}&&status=INACTIVE"
									onclick="confirm()" class="btn btn-danger btn-sm">DELETE</a>
							</c:if></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>

</html>