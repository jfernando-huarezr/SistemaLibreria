<%--
  Created by IntelliJ IDEA.
  User: fernando-u
  Date: 24/05/24
  Time: 18:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ include file="/partials/head.jsp" %>
</head>
<body>
    <h1>Gestion de Jefes de Tienda</h1>
    <form action="jefeTienda" method="GET">
        <input type="hidden" name="opcionGET" value="buscarJefeTienda"/>

        <label for="apellidoPaterno">Apellido Paterno: </label>
        <input id="apellidoPaterno" type="text" name="apellidoPaterno"/>

        <label for="apellidoMaterno">Apellido Materno: </label>
        <input id="apellidoMaterno" type="text" name="apellidoMaterno"/>
        <button>Buscar</button>
    </form>
    <br>
    <table>
        <tr>
            <th>Codigo</th>
            <th>Nombre</th>
            <th>Apellido Paterno</th>
            <th>Apellido Materno</th>
            <th>DNI</th>
            <th>Acciones</th>
        </tr>
        <c:forEach var="jefe" items="${listaJefeTienda}">
            <tr>
                <td>${jefe.codigo}</td>
                <td>${jefe.nombre}</td>
                <td>${jefe.apellidoPaterno}</td>
                <td>${jefe.apellidoMaterno}</td>
                <td>${jefe.dni}</td>
                <td class="acciones"><a href="jefeTienda?opcionGET=mostrarEditarJefeTienda&codigoJefeTienda=${jefe.codigo}">Editar</a> <a href="jefeTienda?opcionGET=eliminarJefeTienda&codigoJefeTienda=${jefe.codigo}"> Eliminar</a><td>
            </tr>
        </c:forEach>
    </table>

    <form action="jefeTienda" method="POST">
        <input type="hidden" value="mostrarNuevoJefeTienda" name="opcionPOST"/>
        <button>Nuevo</button>
    </form>
</body>
</html>
