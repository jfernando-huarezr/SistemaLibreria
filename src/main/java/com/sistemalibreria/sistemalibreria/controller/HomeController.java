package com.sistemalibreria.sistemalibreria.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "HomeController", value = "/home")
public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opcion = request.getParameter("opcionGET");

        switch (opcion) {
            case "mostrarGestionProductos" : {
                mostrarGestionProductos(request, response);
                break;
            }
            case "mostrarGestionJefeTienda" : {
                mostrarGestionJefeTienda(request, response);
                break;
            }
        }

    }

    private void mostrarGestionJefeTienda(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paginaDestino = "/gestionJefeTienda.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);
    }

    private void mostrarGestionProductos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paginaDestino = "/gestionProductos.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}