<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <br><br><br>
    <center>
        <h4>Login Form</h4>
        <form action="loginservlet" method="post">  
            Name:    <input type="text" name="username"/><br/><br/>  
            Password:<input type="password" name="userpass"/><br/><br/>  
            <input type="submit" value="login"/>  
        </form>
    </center>    
    </body>
</html>