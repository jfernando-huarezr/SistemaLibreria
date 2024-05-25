<%--
  Created by IntelliJ IDEA.
  User: fernando-u
  Date: 24/05/24
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>

<%@ include file="/partials/head.jsp" %>

<body>
    <h1>Editar Jefe de Tienda ${jefeTienda.codigo}</h1>
    <form action="jefeTienda" method="POST">
        <input type="hidden" value="actualizarJefeTienda" name="opcionPOST"/>
        <input type="hidden" name="codigo" value="${jefeTienda.codigo}"/>
    
        <label for="nombre">Nombre: </label>
        <input type="text" id="nombre" name="nombre" value="${jefeTienda.nombre}"/>
    
        <label for="apellidoPaterno">Tipo: </label>
        <input type="text" id="apellidoPaterno" name="apellidoPaterno" value="${jefeTienda.apellidoPaterno}"/>

        <label for="apellidoMaterno">Tipo: </label>
        <input type="text" id="apellidoMaterno" name="apellidoMaterno" value="${jefeTienda.apellidoMaterno}"/>

        <label for="dni">Tipo: </label>
        <input type="text" id="dni" name="dni" value="${jefeTienda.dni}"/>
    

        <button>Actualizar</button>
    </form>
</body>
</html>
