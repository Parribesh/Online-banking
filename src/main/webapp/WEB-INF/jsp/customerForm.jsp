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
<title>Customer Form</title>
</head>
<body>

<jsp:include page = "./common/sidebar/sidebar.jsp"></jsp:include>

<div class="page-main w-100 d-flex">
<div class =  'table-container d-flex flex-column w-100 '>
<sec:authorize access="hasAuthority('ADMIN')"> <span>Welcome Admin!</span> </sec:authorize>
<h1>Please Register!</h1>
	<div class='table-table'>
	<c:if test="${customers.size() != null}">
	<table class= "table" border="1">
		<thead>
			<tr>
				<th>CustomerId</th>
				<th>FirstName</th>
				<th>LastName</th>
				<th>Gender</th>
				<th>DOB</th>
				<th>Mobile Number</th>
				<th>Address</th>
				<th>SSN</th>
				<th>UserId</th>
				<th colSpan="2">Action</th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach items="${customers }" var="cs">
				<tr>
					<td>${cs.getCustomerId() }</td>
					<td>${cs.getCustomerFirstName() }</td>
					<td>${cs.getCustomerLastName() }</td>
					<td>${cs.getCustomerGender() }</td>
					<td>${cs.getCustomerDOB() }</td>
					<td>${cs.getCustomerMobileNumber() }</td>
					<td>${cs.getCustomerAddress().getAddressLine1() }, ${cs.getCustomerAddress().getCity() }, ${cs.getCustomerAddress().getState()}, ${cs.getCustomerAddress().getCountry() }</td>
					<td>${cs.getCustomerSSN() }</td>
					<td>${cs.getUser().getUserId() }</td>
					<td><a class='btn btn-success' href="${pageContext.request.contextPath }api/customer/updateCustomer/${cs.getCustomerId() }">Update</a></td>
					<td><a class='btn btn-danger' href="${pageContext.request.contextPath }/api/customer/deleteCustomer/${cs.getCustomerId() }">Delete</a></td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>
</c:if>
</div>
	<div class='main-container flex-grow-1'></div>
</div>

<sec:authorize access="hasAuthority('USER')">
		<div class="form-container flex-grow-1">
		<h1>Customer Form!</h1>
			<frm:form action= "${pageContext.request.contextPath }/api/customer/createCustomer" method = "post" modelAttribute="customer" >
				<table class= "table w-100">
					<tbody>
						<tr>
							<td >CustomerId: </td>
							<td><frm:input class= "form-control" value = "${c != null ? c.getCustomerId(): customers.size()>0 ? customers.get(customers.size()-1).getCustomerId()+1: 1}" type = "text" path="customerId"/></td>
							<td><frm:errors path="customerId"/></td>
						</tr>
						<tr>
							<td >FristName: </td>
							<td><frm:input class= "form-control" value = "${c.getCustomerFirstName() }" type = "text" path="customerFirstName"/></td>
							<td><frm:errors path="customerFirstName"/></td>
						</tr>
						<tr>
							<td>LastName: </td>
							<td><frm:input class= "form-control" value = "${c.getCustomerLastName() }" type = "text" path="customerLastName"/></td>
							<td><frm:errors path="customerLastName"/></td>
						</tr>
						<tr>
							<td>Gender: </td>
							<td><frm:input class= "form-control" value = "${c.getCustomerGender() }" type = "text" path="customerGender"/></td>
							<td><frm:errors path="customerGender"/></td>
						</tr>
						<tr>
							<td>DoB: </td>
							<td><frm:input class= "form-control" value = "${c.getCustomerDOB() }" type = "date" path="customerDOB"/></td>
							<td><frm:errors path="customerDOB"/></td>
						</tr>
						<tr>
							<td>Mobile:  </td>
							<td><frm:input class= "form-control" value = "${c.getCustomerMobileNumber() }" type = "text" path="customerMobileNumber"/></td>
							<td><frm:errors path="customerMobileNumber"/></td>
						</tr>
						<tr>
							<td>Address Line1: </td>
							<td><frm:input class= "form-control" value = "${c.getCustomerAddress().getAddressLine1() }" type = "text" path="customerAddress.addressLine1"/></td>
							<td><frm:errors path="customerAddress.addressLine1"/></td>
						</tr>
						<tr>
							<td>Address Line2: </td>
							<td><frm:input class= "form-control" value = "${c.getCustomerAddress().getAddressLine2() }" type = "text" path="customerAddress.addressLine2"/></td>
							<td><frm:errors path="customerAddress.addressLine2"/></td>
						</tr>
						<tr>
							<td>City: </td>
							<td><frm:input class= "form-control"  value = "${c.getCustomerAddress().getCity() }" type = "text" path="customerAddress.city"/></td>
							<td><frm:errors path="customerAddress.city"/></td>
						</tr>
						<tr>
							<td>State: </td>
							<td><frm:input class= "form-control"  value = "${c.getCustomerAddress().getState() }"  type = "text" path="customerAddress.state"/></td>
							<td><frm:errors path="customerAddress.state"/></td>
						</tr>
						<tr>
							<td>Country: </td>
							<td><frm:input class= "form-control"  value = "${c.getCustomerAddress().getCountry() }"  type = "text" path="customerAddress.country"/></td>
							<td><frm:errors path="customerAddress.country"/></td>
						</tr>
						<tr>
							<td>ZipCode: </td>
							<td><frm:input class= "form-control"  value = "${c.getCustomerAddress().getZipCode() }"  type = "text" path="customerAddress.zipCode"/></td>
							<td><frm:errors path="customerAddress.zipCode"/></td>
						</tr>
						<tr>
							<td>SSN:  </td>
							<td><frm:input class= "form-control" value = "${c.getCustomerSSN() }" type = "text" path="customerSSN"/></td>
							<td><frm:errors path="customerSSN"/></td>
						</tr>
					<tr>
							<td>userId:  </td>
							<td><frm:input disabled='true' class= "form-control" value = "${userId }" type = "text" path="user"/></td>
							<td><frm:errors path="user"/></td>
						</tr>
					
					</tbody>
				</table>
					<br></br>
					<input class="btn btn-primary" type="submit"/>
		</frm:form>
		
		</div>	
	</sec:authorize>
</div>


</body>
</html>