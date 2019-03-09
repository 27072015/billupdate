<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table>
<form action="customerdetails" method="post">
<table>
<tr>Enter CustomerId</tr>
<tr><input type="text" name="customerID"></tr>
<tr><input type="submit" name="submit"></tr>
</form>
</table>
<div>FirstName=${customer.firstName }</div>
<div>lastName=${customer.lastName }</div>
<div>emailID=${customer.emailID }</div>
<div>dateOfBirth=${customer.dateOfBirth}</div>
<div>homeAddressCity=${customer.address.homeAddressCity }</div>
<div>homeAddressState=${customer.address.homeAddressState}</div>
<div>billingAddressCity=${customer.address.billingAddressCity}</div>
<div>billingAddressState=${customer.address.billingAddressState}</div>
<div>homeAddresspinCode=${customer.address.homeAddresspinCode}</div>
<div>billingAddresspinCode=${customer.address.billingAddresspinCode}</div>
</body>
</html>