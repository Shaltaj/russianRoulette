
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Register player</title>
</head>
<body>
    <h1>Register new player</h1>

    <form action="/players/register" method="post" enctype="application/x-www-form-urlencoded">
        <p>
            Name: <input type="text" name="name">

        </p>
        <p>
            Login: <input type="text" name="login">

        </p>
        <p>
            Password: <input type="password" name="password">
        </p>
        <p>
            <input type="submit"/>
        </p>
    </form>

</body>
</html>
