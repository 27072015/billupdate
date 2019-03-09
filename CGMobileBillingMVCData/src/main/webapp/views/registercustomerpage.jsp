<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table>
<form:form action="registerCustomer" method="post" modelAttribute="customer">
<tr>
<td>FirstName</td>
<td><form:input path="firstName" size="30"/></td>
<td><form:errors path="firstName" cssClass="error"/></td></tr>

<td> lastName</td>
<td><form:input path="lastName" size="30"/></td>
<td><form:errors path="lastName" cssClass="error"/></td>

<td>emailID</td>
<td><form:input path="emailID" size="30"/></td>
<td><form:errors path="emailID" cssClass="error"/></td>

<td>dateOfBirth</td>
<td><form:input path="dateOfBirth" size="30"/></td>
<td><form:errors path="dateOfBirth" cssClass="error"/></td>


<td> homeAddressCity</td>
<td><form:input path="address.homeAddressCity" size="30"/></td>
<td><form:errors path="address.homeAddressCity" cssClass="error"/></td>

<td> homeAddressState</td>
<td><form:input path="address.homeAddressCity" size="30"/></td>
<td><form:errors path="address.homeAddressCity" cssClass="error"/></td>

<td>billingAddressCity</td>
<td><form:input path="address.billingAddressCity" size="30"/></td>
<td><form:errors path="address.billingAddressCity" cssClass="error"/></td>

<td>billingAddressState</td>
<td><form:input path="address.billingAddressState" size="30"/></td>
<td><form:errors path="address.billingAddressState" cssClass="error"/></td>

<td>homeAddresspinCode</td>
<td><form:input path="address.homeAddresspinCode" size="30"/></td>
<td><form:errors path="address.homeAddresspinCode" cssClass="error"/></td>

<td>billingAddresspinCode</td>
<td><form:input path="address.billingAddresspinCode" size="30"/></td>
<td><form:errors path="address.billingAddresspinCode" cssClass="error"/></td>
<td>submit button</td>
<td><input type="submit" value="submit"></td></form:form></table><br>
<div>Customer Id is ${customer}</div>

</body>
</html>