package com.sistemalibreria.sistemalibreria.controller;

import com.sistemalibreria.sistemalibreria.dao.UsuarioDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opcion = request.getParameter("opcionGET");

        switch (opcion) {
            case "mostrarCrearUsuario" : {
                mostrarCrearUsuario(request, response);
                break;
            }

            case "mostrarRecuperarContraseña" : {
                mostrarRecuperarContraseña(request, response);
                break;
            }
        }
    }

    private void mostrarRecuperarContraseña(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //definir pagina donde debemos ir
        String paginaDestino = "/recuperarContrasenha.jsp";
        //redirigir sistema a pagina en particular
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);
    }

    private void mostrarCrearUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //definir pagina donde debemos ir
        String paginaDestino = "/nuevoUsuario.jsp";
        //redirigir sistema a pagina en particular
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opcion = request.getParameter("opcionPOST");
        switch (opcion) {
            case "loginUsuario" : {
                try {
                    verificarLoginUsuario(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
    }

    private void verificarLoginUsuario(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String correo = request.getParameter("correo");
        String password = request.getParameter("password");

        UsuarioDAO objUsuarioDAO = new UsuarioDAO();
        boolean existe = objUsuarioDAO.validarUsuario(correo, password);

        String paginaDestino = existe ? "/principal.jsp" : "/index.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);
    }
}