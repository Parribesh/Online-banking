<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>  
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/home.css">
<meta charset="UTF-8">
<title>Account Form</title>
</head>
<body>

<div class="page-main w-100 d-flex">
<div class =  'table-container d-flex flex-column w-100 '>
<jsp:include page = "./common/sidebar/sidebar.jsp"></jsp:include>

<h1>ACCOUNT TABLE!</h1>
<div class='table-table'>
<c:if test="${accounts.size() != null}">
	<table class= "table table-striped w-100 m-2" border="1" >
		<thead>
			<tr>
				<th>AccountId</th>
				<th>CustomerId</th>
				<th>AccountHolder</th>
				<th>Account Type</th>
				<th>Date Opened</th>
				<th>BranchId</th>
				<th>Balance</th>
				<th colSpan="2">Action</th>
			</tr>
		</thead>
		
		<tbody>
		
			<c:forEach items="${accounts }" var="ac">
				<tr>
					<td>${ac.getAccountId() }</td>
					<td>${ac.getAccountCustomer().getCustomerId() }</td>
					<td>${ac.getAccountHolder() }</td>
					<td>${ac.getAccountType() }</td>
					<td>${ac.getAccountDateOpened() }</td>
					<td>${ac.getAccountBranch().getBranchName() }</td>
					<td>${ac.getAccountBalance() }</td>
					<td><a  class="btn btn-success" href="${pageContext.request.contextPath }/api/account/updateAccount/${ac.getAccountId() }">Update</a></td>
					<td><a  class="btn btn-danger" href="${pageContext.request.contextPath }/api/account/deleteAccount/${ac.getAccountId() }">Delete</a></td>
				</tr>
			</c:forEach>
	 </tbody>
	</table>
</c:if>

</div>
<div class='main-container flex-grow-1'></div>
</div>
<div class="form-container flex-grow-1">
<c:if test="${isCustomer || hasAuthority}">
<h1>Account Form!</h1>
	<frm:form action= "${pageContext.request.contextPath }/api/account/createAccount" method = "post" modelAttribute="account" >
		<table class="w-75">
			<tbody class="w-75">
				<tr>
					<td >AccountId: </td>
					<td><frm:input class='form-control' value = "${a != null ? a.getAccountId(): accounts.size()>0 ? accounts.get(accounts.size()-1).getAccountId()+1: 1}" type = "text" path="accountId"/></td>
					<td><frm:errors path="accountId"/></td>
				</tr>
				<tr>
					<td >AccountHolder: </td>
					<td><frm:input class='form-control' value = "${a.getAccountHolder() }" type = "text" path="accountHolder"/></td>
					<td><frm:errors path="accountHolder"/></td>
				</tr>
				<tr>
					<td>Account Type: </td>
					<td><frm:select class='form-control' value = "${a.getAccountType()}" path="accountType">
							<frm:options items="${accountType }"></frm:options>
						</frm:select>
					</td>
					<td><frm:errors path="accountType"/></td>
				</tr>
				<tr>
					<td>Date Opened: </td>
					<td><frm:input class='form-control' value = "${a.getAccountDateOpened() }" type = "date" path="accountDateOpened"/></td>
					<td><frm:errors path="accountDateOpened"/></td>
				</tr>
				<tr>
					<td>BranchId </td>
					<td><frm:input class='form-control' value = "${a.getAccountBranch().getBranchId() }" type = "text" path="accountBranch"/></td>
					<td><frm:errors path="accountBranch"/></td>
				</tr>
				<tr>
					<td>Balance </td>
					<td><frm:input class='form-control' value = "${a.getAccountBalance() }" type = "text" path="accountBalance"/></td>
					<td><frm:errors path="accountBalance"/></td>
				</tr>
			
			
			</tbody>
		</table>
			<br></br>
			<input class='btn btn-primary' type="submit"/>
</frm:form>
</c:if>
<c:if test="${!isCustomer }">
	<h3>Not a Customer! Please fill the customer Form First.</h3>

</c:if>
<br></br>

</div>
</div>

</body>
</html>