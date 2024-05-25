<%--
  Created by IntelliJ IDEA.
  User: fernando-u
  Date: 24/05/24
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <%@ include file="/partials/head.jsp" %>
<body>
    <h1>Nuevo Jefe de Tienda</h1>
    <form action="jefeTienda" method="POST">
        <input type="hidden" value="grabarJefeTienda" name="opcionPOST"/>

        <label for="nombre">Nombre: </label>
        <input type="text" id="nombre" name="nombre"/>

        <label for="apellidoPaterno">Apellido Paterno: </label>
        <input type="text" id="apellidoPaterno" name="apellidoPaterno"/>

        <label for="apellidoMaterno">Apellido Materno: </label>
        <input type="text" id="apellidoMaterno" name="apellidoMaterno"/>

        <label for="dni">DNI: </label>
        <input type="text" maxlength="8" id="dni" name="dni"/>

        <button>Grabar</button>
    </form>
</body>
</html>
