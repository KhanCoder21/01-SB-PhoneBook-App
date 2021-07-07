<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<script type="text/javascript">
	function confirmDelete() {
		return confirm("Are you sure ,you want to delete ?");
	}
</script>

<meta charset="ISO-8859-1">
<title>all contacts info</title>
</head>
<body>
	<h3>view contacts</h3>
	<a href="loadForm"> +add more</a>
	<table border="1">
		<thead>

			<tr>
				<th>s.no</th>
				<th>Name</th>
				<th>Email</th>
				<th>Number</th>
				<th>Action</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${contacts}" var="c" varStatus="count">
				<tr>
					<td>${count.index+1}</td>
					<td>${c.contactName}</td>
					<td>${c.contactEmail}</td>
					<td>${c.contactNumber}</td>
					<td><a href="editContact?cid=${c.contactId}">edit</a> &nbsp; <a
						href="deleteContact?cid=${c.contactId}" onclick="confirmDelete()">delete</a></td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
	<c:if test="${currentPno >1 }">
		<a href="viewContacts?pno=${currentPno-1 }">Previous</a>
	</c:if>

	<c:forEach begin="1" end="${tp}" var="pageNo">

		<c:choose>
			<c:when test="${currentPno==pageNo}">${pageNo}</c:when>

			<c:otherwise>
				<a href="viewContacts?pno=${pageNo}">${pageNo}</a>
			</c:otherwise>
		</c:choose>

	</c:forEach>
	<c:if test="${currentPno < tp}">
		<a href="viewContacts?pno=${currentPno+1 }">Next</a>
	</c:if>

</body>
</html>