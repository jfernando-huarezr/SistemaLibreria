package com.sistemalibreria.sistemalibreria.dao;

import com.sistemalibreria.sistemalibreria.config.DatabaseConfigLoader;
import com.sistemalibreria.sistemalibreria.model.Usuario;

import java.io.IOException;
import java.sql.*;

public class UsuarioDAO {
    private Connection conexion;

    public UsuarioDAO() throws IOException, SQLException {
        DatabaseConfigLoader dbconfig = new DatabaseConfigLoader();
        String url = String.format("%s;databaseName=%s;user=%s;password=%s", dbconfig.getURL(), dbconfig.getName(), dbconfig.getUser(), dbconfig.getPassword());

        try {
            //cargar el driver del sqlServer
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            //aqui establezco la conexion, ya tengo una conexion abierta, usando el driver manager
            this.conexion = DriverManager.getConnection(url);

        } catch (ClassNotFoundException e){
            e.printStackTrace();
            throw new SQLException("Driver not found", e);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error establishing connection", e);
        }
    }

    public boolean validarUsuario(String correo, String password) throws SQLException {
        CallableStatement stmt = this.conexion.prepareCall("{call SP_validarUsuario(?,?)}");

        stmt.setString(1, correo);
        stmt.setString(2, password);

        ResultSet rs = stmt.executeQuery();
        boolean existe = false;

        while(rs.next()) {
            existe = true;
        }

        this.conexion.close();
        return existe;

    }

    public boolean validarCorreoExiste(String correo) throws SQLException {
        CallableStatement stmt = this.conexion.prepareCall("{call SP_validarCorreoExiste(?)}");

        stmt.setString(1, correo);

        ResultSet rs = stmt.executeQuery();
        boolean existe = false;

        while(rs.next()) {
            existe = true;
        }

        this.conexion.close();
        return existe;
    }

    public void insertarUsuario(Usuario usuario) throws SQLException {
        String correo = usuario.getCorreo();
        String nombre = usuario.getNombre();
        String apePaterno = usuario.getApePaterno();
        String apeMaterno = usuario.getApeMaterno();
        String direccion = usuario.getDireccion();
        String password = usuario.getPassword();

        CallableStatement stmt = this.conexion.prepareCall("{call SP_insertarUsuario(?,?,?,?,?,?)}");

        stmt.setString(1, correo);
        stmt.setString(2, nombre);
        stmt.setString(3, apePaterno);
        stmt.setString(4, apeMaterno);
        stmt.setString(5, direccion);
        stmt.setString(6, password);

        stmt.execute();

        this.conexion.close();
    }

    public String buscarContrasenhaxCorreo(String correo) throws SQLException {
        Usuario usuario = new Usuario();
        CallableStatement stmt = this.conexion.prepareCall("{call SP_validarCorreoExiste(?)}");
        stmt.setString(1,correo);

        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            usuario.setPassword(rs.getString("password"));
        }

        this.conexion.close();
        return usuario.getPassword();
    }
}
