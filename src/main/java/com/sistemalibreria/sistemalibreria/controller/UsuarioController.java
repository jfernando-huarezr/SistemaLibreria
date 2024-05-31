package com.sistemalibreria.sistemalibreria.controller;

import com.sistemalibreria.sistemalibreria.dao.UsuarioDAO;
import com.sistemalibreria.sistemalibreria.model.Usuario;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UsuarioController", value = "/usuario")
public class UsuarioController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opcion = request.getParameter("opcionGET");

        switch (opcion) {
            case "regresarLogin" : {
                regresarLogin(request, response);
                break;
            }
        }
    }

    private void regresarLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paginaDestino = "/index.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opcion = request.getParameter("opcionPOST");

        switch (opcion) {
            case "crearNuevoUsuario" : {
                try {
                    crearNuevoUsuario(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            }

            case "recuperarContrasenha" : {
                try {
                    recuperarContrasenha(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            }

        }
    }

    private void recuperarContrasenha(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String correo = request.getParameter("correo");
        String paginaDestino = "/recuperarContrasenha.jsp";
        UsuarioDAO objUsuarioDAO = new UsuarioDAO();
        boolean existe = objUsuarioDAO.validarCorreoExiste(correo);

        if (existe) {
            UsuarioDAO objContrasenhaUsuarioDAO = new UsuarioDAO();
            String contrasenha = objContrasenhaUsuarioDAO.buscarContrasenhaxCorreo(correo);
            request.setAttribute("contraseña", contrasenha);
        }

        request.setAttribute("existe", existe);
        request.setAttribute("correo", correo);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);
    }

    private void crearNuevoUsuario(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        Usuario objUsuario = new Usuario();
        UsuarioDAO objUsuarioDAO = new UsuarioDAO();
        String paginaDestino;

        objUsuario.setNombre(request.getParameter("nombre"));
        objUsuario.setCorreo(request.getParameter("correo"));
        objUsuario.setPassword(request.getParameter("password"));
        objUsuario.setApeMaterno(request.getParameter("apeMaterno"));
        objUsuario.setApePaterno(request.getParameter("apePaterno"));
        objUsuario.setDireccion(request.getParameter("direccion"));

        boolean existe = objUsuarioDAO.validarCorreoExiste(objUsuario.getCorreo());

        if (existe) {
            request.setAttribute("objUsuario", objUsuario);
            paginaDestino = "/nuevoUsuario.jsp";
        } else {
            UsuarioDAO objInsertarUsuarioDAO = new UsuarioDAO();
            objInsertarUsuarioDAO.insertarUsuario(objUsuario);
            paginaDestino = "/index.jsp";
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);
    }
}