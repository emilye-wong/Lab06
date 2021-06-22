<%-- 
    Document   : shoppingList
    Created on : Jun 19, 2021, 11:25:02 PM
    Author     : emily
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <form method="post" action="">
            <p> Hello, ${user}
            <a href="ShoppingList?action=logout">Logout</a></p>
            <h2>List</h2>
            <label>Add items: </label>
            <input type="text" name="item" value="">
            <input type="hidden" name="action" value="add">
            <input type="submit" value="Add">
        </form>

        <form method="post" action="">
            <c:forEach var="item" items="${itemsList}">
                <ul><li>
                        <input type="radio" name="items" value="${itemsName}">${itemsName}
                    </ul></li>
                </c:forEach>

            <c:if test="${deleteButton}">
                <input type="hidden" name="action" value="delete">
                <input type="submit" value="Delete">
            </c:if>
        </form>
    </body>
</html>
