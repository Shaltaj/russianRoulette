<%--
  Created by IntelliJ IDEA.
  User: viktor_molodtsov
  Date: 04/09/2018
  Time: 23:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="bean" type="com.github.molodtsov.russianRoulette.web.PlayerListBean" scope="request"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Привет, ${bean.userAgent}</h1>
    <c:choose>
        <c:when test="${not empty bean.playerList}">
            <table border="1px">
                <tr>
                    <th>name</th>
                    <th>money</th>
                </tr>
                <c:forEach items="${bean.playerList}" var="player">
                    <tr>
                        <td>${player.name}</td>
                        <td>${player.money}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <p>There is no players</p>
        </c:otherwise>
    </c:choose>
    <a href="/players/register">Register player</a>
</body>
</html>
