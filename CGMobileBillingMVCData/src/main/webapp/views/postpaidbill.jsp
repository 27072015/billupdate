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
<form:form action="calculatepostpaidbill" method="post" modelAttribute="bill">
<table>
<tr>
<td>customerID</td>
<td><form:input path="customerID" size="30"/></td>
<td><form:errors path="customerID" cssClass="error"/></td></tr>
<tr>
<td>mobileNo</td>
<td><form:input path="mobileNo" size="30"/></td>
<td><form:errors path="mobileNo" cssClass="error"/></td></tr>
<tr>
<td>billMonth</td>
<td><form:input path="billMonth" size="30"/></td>
<td><form:errors path="billMonth" cssClass="error"/></td></tr>
<tr>
<td>noOfLocalSMS</td>
<td><form:input path="noOfLocalSMS" size="30"/></td>
<td><form:errors path="noOfLocalSMS" cssClass="error"/></td></tr>
<tr>
<td>noOfStdSMS</td>
<td><form:input path="noOfStdSMS" size="30"/></td>
<td><form:errors path="noOfStdSMS" cssClass="error"/></td></tr>
<tr>
<td>noOfLocalCalls</td>
<td><form:input path="noOfLocalCalls" size="30"/></td>
<td><form:errors path="noOfLocalCalls" cssClass="error"/></td></tr>
<tr>
<td>noOfStdCalls</td>
<td><form:input path="noOfStdCalls" size="30"/></td>
<td><form:errors path="noOfStdCalls" cssClass="error"/></td></tr>
<tr>
<td>internetDataUsageUnits</td>
<td><form:input path="internetDataUsageUnits" size="30"/></td>
<td><form:errors path="internetDataUsageUnits" cssClass="error"/></td></tr>

<td><input type="submit" value="submit"></td>
</table>
</form:form>
</body>
</html>