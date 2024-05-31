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
    <h1>Recuperar contraseña</h1>
    <h2>Buscar usuario</h2>
    <form action="usuario" method="POST">
        <input type="hidden" name="opcionPOST" value="recuperarContrasenha"/>
        <label for="correo"><strong>Correo: </strong>
            <c:if test="${existe == false}">
                <span><em>Correo no existe.</em></span>
            </c:if>
        </label>
        <input id="correo" required maxlength="100" type="text" name="correo" value="${correo}" />
        <button type="submit">Recuperar</button>
    </form>

    <h2>Contraseña recuperada</h2>
    <p><strong>Contraseña: </strong>${contraseña}</p>

    <a href="usuario?opcionGET=regresarLogin">Regresar a Login</a>
</body>
</html>
