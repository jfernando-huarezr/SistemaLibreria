<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

    <%@ include file="/partials/head.jsp" %>


<body>
    <h1>Login</h1>
    <form action="login" method="POST">
        <input type="hidden" name="opcionPOST" value="loginUsuario" />
        <label for="correo">Correo Electrónico: </label>
        <input id="correo" type="text" name="correo"/>
        <label for="password">Password: </label>
        <input id="password" type="password" name="password" />
        <button type="submit">Ingresar</button>
    </form>
    <br><br>

    <a href="login?opcionGET=mostrarCrearUsuario">Crear Usuario</a> <br><br>
    <a href="login?opcionGET=mostrarRecuperarContraseña">Recuperar Contraseña</a>
</body>

</html>