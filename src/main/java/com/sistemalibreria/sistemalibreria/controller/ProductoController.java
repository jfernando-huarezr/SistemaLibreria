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
                mostrarEditarProducto(request, response);
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
                eliminarProducto(request, response);
                break;
            }
        }
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) {
    }

    private void mostrarEditarProducto(HttpServletRequest request, HttpServletResponse response) {
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

    }
}