package com.sistemalibreria.sistemalibreria.controller;

import com.sistemalibreria.sistemalibreria.dao.JefeTiendaDAO;
import com.sistemalibreria.sistemalibreria.model.JefeTienda;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "JefeTiendaController", value = "/jefeTienda")
public class JefeTiendaController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opcion = request.getParameter("opcionGET");
        
        switch (opcion) {
            case "buscarJefeTienda" : {
                try {
                    buscarJefeTienda(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            }

            case "mostrarEditarJefeTienda" : {
                try {
                    mostrarEditarJefeTienda(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            }

            case "eliminarJefeTienda" : {
                try {
                    eliminarJefeTienda(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
    }

    private void eliminarJefeTienda(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int codigoJefeTienda = Integer.parseInt(request.getParameter("codigoJefeTienda"));
        JefeTiendaDAO objJefeTiendaDAO = new JefeTiendaDAO();

        objJefeTiendaDAO.eliminarJefeTienda(codigoJefeTienda);

        String paginaDestino = "/gestionJefeTienda.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);
    }

    private void mostrarEditarJefeTienda(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int codigoJefeTienda = Integer.parseInt(request.getParameter("codigoJefeTienda"));
        JefeTiendaDAO objJefeTiendaDAO = new JefeTiendaDAO();
        JefeTienda jefeTienda = objJefeTiendaDAO.buscarJefeTiendaxCodigo(codigoJefeTienda);

        request.setAttribute("jefeTienda", jefeTienda);

        String paginaDestino = "/editarJefeTienda.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);
    }

    private void buscarJefeTienda(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String apellidoPaterno = request.getParameter("apellidoPaterno");
        String apellidoMaterno = request.getParameter("apellidoMaterno");

        JefeTiendaDAO objJefeTiendaDAO = new JefeTiendaDAO();
        List<JefeTienda> listaJefeTienda = objJefeTiendaDAO.buscarJefeTiendaxApellidos(apellidoPaterno, apellidoMaterno);

        request.setAttribute("listaJefeTienda", listaJefeTienda);
        String paginaDestino = "/gestionJefeTienda.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opcion = request.getParameter("opcionPOST");

        switch (opcion) {
            case "mostrarNuevoJefeTienda" : {
                mostrarNuevoJefeTienda(request, response);
                break;
            }

            case "grabarJefeTienda" : {
                try {
                    grabarJefeTienda(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            }

            case "actualizarJefeTienda" : {
                try {
                    actualizarJefeTienda(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
    }

    private void actualizarJefeTienda(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        String nombre = request.getParameter("nombre");
        String apellidoPaterno = request.getParameter("apellidoPaterno");
        String apellidoMaterno = request.getParameter("apellidoMaterno");
        String dni = request.getParameter("dni");

        JefeTiendaDAO objJefeTiendaDAO = new JefeTiendaDAO();
        objJefeTiendaDAO.editarJefeTienda(codigo, nombre, apellidoPaterno, apellidoMaterno, dni);

        String paginaDestino = "/gestionJefeTienda.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);
    }

    private void grabarJefeTienda(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String nombre = request.getParameter("nombre");
        String apellidoPaterno = request.getParameter("apellidoPaterno");
        String apellidoMaterno = request.getParameter("apellidoMaterno");
        String dni = request.getParameter("dni");
        
        JefeTiendaDAO objJefeTiendaDAO = new JefeTiendaDAO();
        objJefeTiendaDAO.insertarJefeTienda(nombre, apellidoPaterno, apellidoMaterno, dni);

        String paginaDestino = "/gestionJefeTienda.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);
        
    }

    private void mostrarNuevoJefeTienda(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paginaDestino = "/nuevoJefeTienda.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);
    }


}