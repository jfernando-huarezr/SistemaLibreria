<%--
  Created by IntelliJ IDEA.
  User: fernando-u
  Date: 17/05/24
  Time: 07:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <%@ include file="/partials/head.jsp" %>

<body>
    <h1>Nuevo Producto</h1>
    <form action="producto" method="POST">
        <input type="hidden" value="grabarProducto" name="opcionPOST"/>

        <label for="nombre">Nombre: </label>
        <input type="text" id="nombre" name="nombre" value="${producto.nombre}"/>

        <label for="tipo">Tipo: </label>
        <input type="text" id="tipo" name="tipo" value="${producto.tipo}"/>

        <label for="precio">Precio: </label>
        <input type="number" id="precio" name="precio" value="${producto.precio}"/>


        <button>Grabar</button>
    </form>
</body>
</html>
