
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:useBean id="formBean" beanName="formBean" scope="request" type="com.github.molodtsov.russianRoulette.web.RegisterPlayerFormBean"/>
<html>
<head>
    <title>Register player</title>
</head>
<body>
    <h1>Register new player</h1>

    <%--@elvariable id="formBean" type="com.github.molodtsov.russianRoulette.web.RegisterPlayerFormBean"--%>
    <form:form action="/players/register"
               method="post"
               enctype="application/x-www-form-urlencoded"
               modelAttribute="formBean">
        <p>
            Name: <form:input type="text" path="name"/>
            <form:errors path="name"/>

        </p>
        <p>
            Login: <form:input type="text" path="login"/>
            <form:errors path="login"/>

        </p>
        <p>
            Password: <form:input type="password" path="password"/>
            <form:errors path="password"/>
        </p>
        <p>
            <input type="submit"/>
        </p>
    </form:form>

</body>
</html>
