<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
	<div class= "sidebar-main ">
		<div class="mt-5 d-flex flex-column ">
		<div class='w-100 d-flex justify-content-center align-items-center '>
			<img src="${pageContext.request.contextPath }/images/bank.png" width="50px" height= "50px" alt="bank-logo" />
			<div style= "font-size: 30px" >Banking Profile</div>
		</div>
<hr>
		<div class= 'w-100 d-flex  align-items-center'>
			<sec:authorize access="isAuthenticated()">
    			Username: <b><span style="font-size: 1.5rem"><sec:authentication property="principal.username" /> </span></b>
			</sec:authorize>
		</div>
		</div>
		
		<nav class="sidebar mt-5">
			<a class= "link" href="${pageContext.request.contextPath }/api/user/userForm">Join</a>
			<a class= "link" href="${pageContext.request.contextPath }/api/account/accountForm">Accounts</a>
			<a class= "link" href="${pageContext.request.contextPath }/api/branch/branchForm">Branches</a>
			<a class= "link" href="${pageContext.request.contextPath }/api/transaction/transactionForm">Transfer</a>
			<a class= "link" href="${pageContext.request.contextPath }/api/customer/customerForm">Customer</a>  
			<a class= "link" href="${pageContext.request.contextPath }/logout">Logout</a> 
		</nav>
	</div>

