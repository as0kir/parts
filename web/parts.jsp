<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
  <title>Parts</title>
</head>
<body>
<table border=1>
  <thead>
  <tr>
    <th>PN</th>
    <th>Part Name</th>
    <th>Vendor</th>
    <th>Qty</th>
    <th>Shipped</th>
    <th>Peceived</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${parts}" var="part">
    <tr>
      <td><c:out value="${part.partName}" /></td>
      <td><c:out value="${part.partNumber}" /></td>
      <td><c:out value="${part.vendor}" /></td>
      <td><c:out value="${part.qty}" /></td>
      <td><fmt:formatDate pattern="MMM dd, yyyy" value="${part.shipped}" /></td>
      <td><fmt:formatDate pattern="MMM dd, yyyy" value="${part.receive}" /></td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>