<%--
  Created by IntelliJ IDEA.
  User: fernando-u
  Date: 30/05/24
  Time: 18:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<%@ include file="/partials/head.jsp" %>


<body>
    <h1>Nuevo usuario</h1>
    <form action="usuario" method="POST">
        <input type="hidden" name="opcionPOST" value="crearNuevoUsuario" />
        <label for="correo">Correo: </label>
        <input id="correo" required maxlength="100" type="text" name="correo" value="${objUsuario.correo}" />
        <label for="nombre">Nombre: </label>
        <input id="nombre" required maxlength="40" type="text" name="nombre" value="${objUsuario.nombre}" />
        <label for="apePaterno">Apellido Paterno: </label>
        <input id="apePaterno" required maxlength="40" type="text" name="apePaterno" value="${objUsuario.apePaterno}" />
        <label for="apeMaterno">Apellido Materno: </label>
        <input id="apeMaterno" required maxlength="40" type="text" name="apeMaterno" value="${objUsuario.apeMaterno}" />
        <label for="direccion">Dirección: </label>
        <input id="direccion" required maxlength="40" type="text" name="direccion" value="${objUsuario.direccion}" />
        <label for="password">Password: </label>
        <input id="password" required maxlength="20" type="password" name="password" value="${objUsuario.password}" />

        <button type="submit">Grabar</button>

        <c:if test="${objUsuario != undefined}">
            <p><em>Ya existe un usuario con este correo electrónico. Elige uno diferente.</em></p>
        </c:if>
    </form>
</body>
</html>
