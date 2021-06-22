<%-- 
    Document   : register
    Created on : Jun 19, 2021, 11:23:12 PM
    Author     : emily
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <form method="post" action="ShoppingList">
            <label>Username: </label>
            <input type="text" name="user" value="">
            <input type="hidden" name="action" value="register">
            <input type="submit" value="Register name">
        </form>
    <c:if test="${userNull}">
        <div>Please enter a username.</div>
    </c:if>

    <c:if test="${itemNull}">
        <div>Please enter an item to be added.</div>
    </c:if>
</body>
</html>
