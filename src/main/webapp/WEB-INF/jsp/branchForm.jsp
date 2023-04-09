<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<h1>Branches Table</h1>
	<div class='table-table'>
	<c:if test="${branches.size() != null}">
		<table class= "table table-striped w-100 m-2" border="1" >
		
			<thead class="thead-dark">
				<tr>
					<th scope="col">BranchId</th>
					<th scope="col">Name</th>
					<th scope="col">AddressLine1</th>
					<th scope="col">AddressLine2</th>
					<th scope="col">City</th>
					<th scope="col">State</th>
					<th scope="col">Country</th>
					<th scope="col">ZipCode</th>
					<sec:authorize access="hasAuthority('ADMIN')">
						<th scope="col" colSpan="2">Action</th>
					</sec:authorize>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${branches }" var="br">
				<tr>
					<td>${br.getBranchId() }</td>
					<td>${br.getBranchName() }</td>
					<td>${br.getBranchAddress().getAddressLine1() }</td>
					<td>${br.getBranchAddress().getAddressLine2() }</td>
					<td>${br.getBranchAddress().getCity() }</td>
					<td>${br.getBranchAddress().getState() }</td>
					<td>${br.getBranchAddress().getCountry() }</td>
					<td>${br.getBranchAddress().getZipCode() }</td>
					<sec:authorize access="hasAuthority('ADMIN')">
						<td><a class="btn btn-success" href="${pageContext.request.contextPath }/api/branch/updateBranch/${br.getBranchId() }">Update</a></td>
						<td><a class="btn btn-danger" href="${pageContext.request.contextPath }/api/branch/deleteBranch/${br.getBranchId() }">Delete</a></td>
					</sec:authorize>
				</tr>
			</c:forEach>
		
		</tbody>
		</table>
		</c:if>
	</div>
	<div class='main-container flex-grow-1'></div>
</div>
<sec:authorize access="hasAuthority('ADMIN')">
<div class="form-container flex-grow-1">
<h1>Branches Form!</h1>
		<frm:form action= "${pageContext.request.contextPath }/api/branch/createBranch" method = "post" modelAttribute="branch" >
		<table class= "table w-100">
			<tbody>
				<tr>
					<td class="font-weight-bold">BranchId: </td>
					<td><frm:input class="form-control" value = "${b != null ? b.getBranchId(): branches.size()>0 ? branches.get(branches.size()-1).getBranchId()+1: 1}" type = "text" path="branchId"/></td>
					<td><frm:errors path="branchId"/></td>
				</tr>
				<tr>
					<td class="font-weight-bold" >BranchName: </td>
					<td><frm:input class="form-control" value = "${b.getBranchName() }" type = "text" path="branchName"/></td>
					<td><frm:errors path="branchName"/></td>
				</tr>
				<tr>
					<td class="font-weight-bold">Address Line1: </td>
					<td><frm:input class="form-control" value = "${b.getBranchAddress().getAddressLine1() }" type = "text" path="branchAddress.addressLine1"/></td>
					<td><frm:errors path="branchAddress.addressLine1"/></td>
				</tr>
				<tr>
					<td class="font-weight-bold">Address Line2: </td>
					<td><frm:input class="form-control" value = "${b.getBranchAddress().getAddressLine2() }" type = "text" path="branchAddress.addressLine2"/></td>
					<td><frm:errors path="branchAddress.addressLine2"/></td>
				</tr>
				<tr>
					<td class="font-weight-bold">City: </td>
					<td><frm:input class="form-control"  value = "${b.getBranchAddress().getCity() }" type = "text" path="branchAddress.city"/></td>
					<td><frm:errors path="branchAddress.city"/></td>
				</tr>
				<tr>
					<td class="font-weight-bold">State: </td>
					<td><frm:input class="form-control"  value = "${b.getBranchAddress().getState() }"  type = "text" path="branchAddress.state"/></td>
					<td><frm:errors path="branchAddress.state"/></td>
				</tr>
				<tr>
					<td class="font-weight-bold">Country: </td>
					<td><frm:input class="form-control"  value = "${b.getBranchAddress().getCountry() }"  type = "text" path="branchAddress.country"/></td>
					<td><frm:errors path="branchAddress.country"/></td>
				</tr>
				<tr>
					<td class="font-weight-bold">ZipCode: </td>
					<td><frm:input class="form-control"  value = "${b.getBranchAddress().getZipCode() }"  type = "text" path="branchAddress.zipCode"/></td>
					<td><frm:errors path="branchAddress.zipCode"/></td>
				</tr>
			
			</tbody>
		</table>
		<br></br>
			<input class= "btn btn-primary" type="submit"/>
		</frm:form>
</div>	
	</sec:authorize>
</div>
</body>

</html>