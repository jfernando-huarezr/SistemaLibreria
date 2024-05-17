<%--
  Created by IntelliJ IDEA.
  User: fernando-u
  Date: 17/05/24
  Time: 07:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>

    <%@ include file="/partials/head.jsp" %>

<body>
    <h1>Editar Producto ${producto.id}</h1>
    <form action="producto" method="POST">
        <input type="hidden" value="actualizarProducto" name="opcionPOST"/>
        <input type="hidden" name="id" value="${producto.id}"/>

        <label for="nombre">Nombre: </label>
        <input type="text" id="nombre" name="nombre" value="${producto.nombre}"/>

        <label for="tipo">Tipo: </label>
        <input type="text" id="tipo" name="tipo" value="${producto.tipo}"/>

        <label for="precio">Precio: </label>
        <input type="number" id="precio" name="precio" value="${producto.precio}"/>

        <label for="estado">Estado: </label>
        <select id="estado" name="estado">
            <option value="Disponible" ${producto.estado == 'Disponible' ? 'selected' : ''}>Disponible</option>
            <option value="En espera" ${producto.estado == 'En espera' ? 'selected' : ''}>En espera</option>
            <option value="Agotado" ${producto.estado == 'Agotado' ? 'selected' : ''}>Agotado</option>
        </select>
        <button>Actualizar</button>
    </form>
</body>
</html>
