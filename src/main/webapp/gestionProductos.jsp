<%--
  Created by IntelliJ IDEA.
  User: fernando-u
  Date: 17/05/24
  Time: 07:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ include file="/partials/head.jsp" %>
</head>
<body>
    <h1>Gestion de Productos</h1>
    <p>Criterios de Busqueda</p>
    <br>
    <br>
    <form action="producto" method="GET">
        <input type="hidden" name="opcionGET" value="buscarProducto"/>

        <label for="nombre">Nombre: </label>
        <input id="nombre" type="text" name="nombre"/><button>Buscar</button>
    </form>

    <br>
    <br>
    <table>
        <tr>
            <th>Id</th>
            <th>Nombre</th>
            <th>Tipo</th>
            <th>Precio</th>
            <th>Acciones</th>
        </tr>
        <c:forEach var="producto" items="${listaProductos}">
            <tr>
                <td>${producto.id}</td>
                <td>${producto.nombre}</td>
                <td>${producto.tipo}</td>
                <td>S/.${producto.precio}</td>
                <td><a href="producto?opcionGET=mostrarEditarProducto&idProducto=${producto.id}">Editar</a> <a href="producto?opcionGET=eliminarProducto&idProducto=${producto.id}"> Eliminar</a><td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <br>
    <form action="producto" method="POST">
        <input type="hidden" value="mostrarNuevoProducto" name="opcionPOST"/>
        <button>Nuevo</button>
    </form>

</body>
</html>
