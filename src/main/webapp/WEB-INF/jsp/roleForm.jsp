<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>  
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/home.css/home.css">
<meta charset="UTF-8">
<title>Roles</title>
</head>
<body>

<jsp:include page = "./common/sidebar/sidebar.jsp"></jsp:include>

<div align="center">
<h1>Assign Roles!</h1>
	<frm:form action= "${pageContext.request.contextPath }/api/role/createRole" method = "post" modelAttribute="role" >
		<table>
			<tbody>
				<tr>
					<td >RoleId: </td>
					<td><frm:input value = "${r != null ? r.getRoleId(): roles!= null ? roles.get(roles.size()-1).getRoleId()+1: 1}" type = "text" path="roleId"/></td>
					<td><frm:errors path="roleId"/></td>
				</tr>
				<tr>
					<td>RoleName: </td>
					<td><frm:input  value="${r.getRoleName() }" type = "text" path="roleName"/></td>
					<td><frm:errors path="roleName"/></td>
				</tr>
									
			</tbody>
		</table>
	
	<input type="submit"/>
	</frm:form>
	<br>
	<c:if test="${roles.size() != null}">
	<table border="1">
		<thead>
			<tr>
				<th>RoleID</th>
				<th>Name</th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach items="${roles }" var="role">
				<tr>
					<td>${role.getRoleId() }</td>
					<td>${role.getRoleName() }</td>
					<td><a href="${pageContext.request.contextPath }/api/role/updateRole/${role.getRoleId() }">Update</a></td>
					<td><a href="${pageContext.request.contextPath }/api/role/deleteRole/${role.getRoleId() }">Delete</a></td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>
</c:if>
</div>


</body>
</html>