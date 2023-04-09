<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>  
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %> 
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/home.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<jsp:include page = "./common/sidebar/sidebar.jsp"></jsp:include>

<div class="page-main w-100 d-flex">
<div class =  'table-container d-flex flex-column w-100 '>
<sec:authorize access="hasAuthority('ADMIN')"> <span>Welcome Admin!</span> </sec:authorize>

	<div class='table-table'>
		<table class="table table-striped m-0 " border="1">
				<c:if test="${users != null }">
					<thead class="thead-dark">
						<tr>
							<th>UserId </th>
							<th>Username </th>
							<!-- <th>Password </th> -->
							<th>Email </th>
							<th>Mobile </th>
							<th>Role </th>
							<th colspan="2">Actions</th>
						</tr>
					</thead>
			<tbody>
				</c:if>
				
				<c:forEach items="${users}" var="user">
					<tr>
						<td>${user.getUserId()}</td>
						<td>${user.getUsername() }</td>
						<%-- <td>${user.getUserPassword() }</td> --%>
						<td>${user.getUserEmail() }</td>
						<td>${user.getUserMobile() }</td>
							<td>
								<c:forEach items="${user.getUserRoles()}" var ="role">
									${role.getRoleName()}
								</c:forEach>
							</td> 
						<td><a class="btn btn-success" href="${pageContext.request.contextPath }/api/user/updateUser/${user.getUserId()}" >Update</a></td>
						<sec:authorize access="hasAuthority('ADMIN')"> <td><a class="btn btn-danger" href="${pageContext.request.contextPath }/api/user/deleteUser/${user.getUserId()}">Delete</a></td>	 </sec:authorize>
					</tr>
				</c:forEach>		
			</tbody>
		</table>
	</div>
	<div class='main-container flex-grow-1'></div>
</div>
<div class="form-container flex-grow-1">
<h1>Please Register!</h1>
	<frm:form action= "${pageContext.request.contextPath }/api/user/createUser" method = "post" modelAttribute="user" >
		<table class="table mb-4 w-100">
			<tbody>
			<tr>
					<td >UserId: </td>
					<td><frm:input class="form-control" value = "${u != null ? u.getUserId(): users.size()>0 ? users.get(users.size()-1).getUserId()+1: 1}" type = "text" path="userId"/></td>
					
				</tr>
				<tr>
					<td >Username: </td>
					<td><frm:input class="form-control"  value = "${u.getUsername() }" type = "text" path="username"/></td>
					
				</tr>
					<tr class="error"><td></td><td class= "text-right"><frm:errors path="username"/></td></tr>
			
				<tr>
					<td>Password: </td>
					<td><frm:input class="form-control"   type = "text" path="userPassword"/></td>
					
				</tr>
		
					<tr class="error"><td></td><td class= "text-right"><frm:errors path="userPassword"/></td></tr>
			
				<tr>
				<tr>
					<td>Email: </td>
					<td><frm:input class="form-control"  value = "${u.getUserEmail() }" type = "text" path="userEmail"/></td>
					
				</tr>
			
					<tr class="error"><td></td><td class= "text-right"><frm:errors path="userEmail"/></td></tr>
			
				<tr>
				<tr>
					<td>Mobile: </td>
					<td><frm:input class="form-control"  value = "${u.getUserMobile() }" type = "text" path="userMobile"/></td>
					
				</tr>

					<tr class="error"><td><td class= "text-right"><frm:errors path="userMobile"/></td></tr>
			
				<tr>
				<tr>
					<td>Role: </td>
					<%-- <td><frm:radiobuttons path ="userRoles" items="${roles}"/></td> --%>
					<td>
						<c:forEach items="${roles}" var="r">
							<c:choose>
								<c:when test="${r.getRoleId().equals(u.getUserRoles().get(0).getRoleId()) }">
									<frm:radiobutton class=" m-3" checked="true" path="userRoles" value="${r.getRoleId()}"/> ${r.getRoleName()}
								</c:when>
								<c:otherwise>
									<frm:radiobutton class=" m-3" path="userRoles" value="${r.getRoleId()}"/> ${r.getRoleName()}
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</td>
					<td><frm:errors path="userRoles"/></td>
				</tr>			
			
			</tbody>
		</table>
		
		
	
	<input class="btn btn-primary" type="submit"/>
	</frm:form>
	
	<br>
</div>	
	
</div>

</body>
</html>