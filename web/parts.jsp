<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <title>Parts</title>
</head>
<body>
<fmt:setLocale value="en_EN" scope="session"/>
<form method="get" action="parts">
    <table cellspacing="1" cellpadding="0" border="0">
        <tr>
            <th colspan="2">Filter</th>
        </tr>
        <tr>
            <td>PN</td>
            <td><input name="part_number" type="text" value="${part_number}"></td>
        </tr>
        <tr>
            <td>Part Name</td>
            <td><input name="part_name" type="text" value="${part_name}"></td>
        </tr>
        <tr>
            <td>Vendor</td>
            <td><input name="vendor" type="text" value="${vendor}"></td>
        </tr>
        <tr>
            <td>Qty</td>
            <td><input name="qty" type="text" value="${qty}"></td>
        </tr>
        <tr>
            <td>Shipped</td>
            <td>after <input name="shipped_after" type="text" value="${shipped_after}"> before <input
                    name="shipped_before" type="text" value="${shipped_before}">
            </td>
        </tr>
        <tr>
            <td>Received</td>
            <td>after <input name="receive_after" type="text" value="${receive_after}"> before <input
                    name="receive_before" type="text" value="${receive_before}">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input name="order" type="hidden" value="${order}">
                <button type="submit">filter</button>
            </td>
        </tr>
    </table>
</form>
<div><br><br></div>
<table border="1" cellpadding="0" cellspacing="0">
    <thead>
    <tr>
        <th width="250"><a href="${order_part_number}">PN</a></th>
        <th width="250"><a href="${order_part_name}">Part Name</a></th>
        <th width="200"><a href="${order_vendor}">Vendor</a></th>
        <th width="100"><a href="${order_qty}">Qty</a></th>
        <th width="150"><a href="${order_shipped}">Shipped</a></th>
        <th width="150"><a href="${order_receive}">Received</a></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${parts}" var="part">
        <tr>
            <td><c:out value="${part.partNumber}"/></td>
            <td><c:out value="${part.partName}"/></td>
            <td><c:out value="${part.vendor}"/></td>
            <td><c:out value="${part.qty}"/></td>
            <td><fmt:formatDate pattern="MMM dd, yyyy" value="${part.shipped}"/></td>
            <td><fmt:formatDate pattern="MMM dd, yyyy" value="${part.receive}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<br>
<div>
    ${error}
</div>
</body>
</html>