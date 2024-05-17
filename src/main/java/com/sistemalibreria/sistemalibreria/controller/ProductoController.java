package com.sistemalibreria.sistemalibreria.controller;
import com.sistemalibreria.sistemalibreria.dao.ProductoDAO;
import com.sistemalibreria.sistemalibreria.model.Producto;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductoController", value = "/producto")
public class ProductoController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opcion = request.getParameter("opcionGET");

        switch (opcion) {
            case "mostrarEditarProducto" : {
                try {
                    mostrarEditarProducto(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            case "buscarProducto": {
                try {
                    buscarProducto(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            case "eliminarProducto": {
                try {
                    eliminarProducto(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        ProductoDAO objUsuarioDAO = new ProductoDAO();
        objUsuarioDAO.eliminarProducto(idProducto);

        //definir pagina donde debemos ir
        String paginaDestino = "/gestionProductos.jsp";
        //redirigir sistema a pagina en particular
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);
    }

    private void mostrarEditarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        ProductoDAO objProductoDAO = new ProductoDAO();
        Producto producto = objProductoDAO.buscarProductoxId(idProducto);

        request.setAttribute("producto", producto);
        String paginaDestino = "/editarProducto.jsp";
        RequestDispatcher view = request.getRequestDispatcher(paginaDestino);
        view.forward(request, response);
    }

    private void buscarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String nombre = request.getParameter("nombre");
        ProductoDAO objProductoDAO = new ProductoDAO();
        List<Producto> listaProductos = objProductoDAO.buscarProductoxNombre(nombre);

        request.setAttribute("listaProductos", listaProductos);
        String paginaDestino = "/gestionProductos.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opcion = request.getParameter("opcionPOST");

        switch (opcion) {
            case "grabarProducto": {
                try {
                    grabarProducto(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            case "actualizarProducto": {
                try {
                    actualizarProducto(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            }

            case "mostrarNuevoProducto": {
                mostrarNuevoProducto(request, response);
                break;
            }
        }

    }

    private void actualizarProducto(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String nombre = request.getParameter("nombre");
        String tipo = request.getParameter("tipo");
        float precio = Float.parseFloat(request.getParameter("precio"));
        String estado = request.getParameter("estado");
        int id = Integer.parseInt(request.getParameter("id"));

        ProductoDAO productoDAO = new ProductoDAO();
        productoDAO.editarProducto(nombre, tipo, precio, estado, id);

        String paginaDestino = "/gestionProductos.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);
    }

    private void grabarProducto(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String nombre = request.getParameter("nombre");
        String tipo = request.getParameter("tipo");
        float precio = Float.parseFloat(request.getParameter("precio"));

        ProductoDAO productoDAO = new ProductoDAO();
        productoDAO.grabarProducto(nombre, tipo, precio);

        String paginaDestiono = "/gestionProductos.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(paginaDestiono);
        dispatcher.forward(request, response);
    }

    private void mostrarNuevoProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paginaDestino = "/nuevoProducto.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);
    }
}