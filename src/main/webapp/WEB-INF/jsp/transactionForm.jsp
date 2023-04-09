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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/home.css">
<meta charset="UTF-8">
<title>Transactions Form: </title>
</head>
<body>

<div class="page-main w-100 d-flex">
<div class =  'table-container d-flex flex-column w-100 '>
<jsp:include page = "./common/sidebar/sidebar.jsp"></jsp:include>
<div class='row w-100 justify-content-around align-items-end'>
	<div class="ml-4">
		<h1>Transactions Table</h1>
	</div>
	<div class="flex-grow-1 ">
		<frm:form action ='${pageContext.request.contextPath }/api/transaction/filterTransaction' method='post' modelAttribute='dateModel'>
		<div class= "row justify-content-center align-items-center">
			<div class="mx-3">
				<div class="d-flex align-items-center">
					<label><b> From:</b> </label>
					<frm:input path="from" class='form-control ml-2' type='date' />
					<frm:errors path="from"/>
				</div>
			</div>
			
			<div class="mx-3">
				<div class="d-flex align-items-center">
					<label class=''><b> To:</b> </label>
					<frm:input path='to' class='form-control ml-2' type='date' />
					<frm:errors path="to"/>
				</div>
			</div>
			<div class="mx-3">
				<input type='submit'  class='btn btn-success px-4' value='Filter'/>
			</div>
			</div>
		</frm:form>	
	</div>
</div>

	<div class='table-table'>
	<c:if test="${transactions.size() != null}">
	<table class= "table table-striped w-100 m-2" border="1" >
		<thead class='thead-dark'>
			<tr >
				<th>Transaction Id </th>
				<th>Transaction Type </th>
				<th>Account No. </th>
				<th>Transaction Date </th>
				<th>Transaction Amount </th>
				<th>Status </th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items = "${transactions }" var = "trans">
			<tr>
				<td id="transactionType">${trans.getTransactionId() }</td>
				<td id="transactionType">${trans.getTransactionType() }</td>
				<td id="senderAccount">${trans.getSenderAccountId() }</td>
				<td id="senderBalance">${trans.getTransactionDate() }</td>
				<td id="transactionAmount">${trans.getAmount() }</td>
				<td id="transactionStatus">${trans.getStatus() }</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
		</c:if>
	</div>
	<div class='main-container flex-grow-1'></div>
</div>

<div class="form-container flex-grow-1">
<h1>Transactions Form!</h1>
	<frm:form action= "${pageContext.request.contextPath }/api/transaction/createTransaction" method = "post" modelAttribute="transaction" >
		<table>
			<tbody>
				<tr>
					<td >Sender Account Number: </td>
					<td><frm:input class="form-control" value = "" type = "text" path="senderAccountId"/></td>
					<td><frm:errors path="senderAccountId"/></td>
				</tr>
				
				<tr>
					<td>Receiver Account Number: </td>
					<td><frm:input class="form-control" value="" type = "text" path="receiverAccountId"/></td>
					<td><frm:errors path="receiverAccountId"/></td>
				</tr>
				<tr>
					<td>Amount: </td>
					<td><frm:input class="form-control" value="" type = "text" path="amount"/></td>
					<td><frm:errors path="amount"/></td>
				</tr>
				
				<tr>
					<td>Transaction Type: </td>
					<td>
						<frm:radiobuttons class ="m-2" values = "${ transactionType}" path="transactionType"/> 
						
					</td>
				</tr>
				
									
			</tbody>
		</table>
	<br></br>
	<input class="btn btn-primary" type="submit"/>
	</frm:form>
	<br>
</div>
</div>

<script>
	(document.getElementById("transactionType").innerText);
</script>
</body>

</html>