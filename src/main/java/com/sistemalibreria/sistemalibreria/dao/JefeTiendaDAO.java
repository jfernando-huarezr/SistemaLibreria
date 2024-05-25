package com.sistemalibreria.sistemalibreria.dao;

import com.sistemalibreria.sistemalibreria.config.DatabaseConfigLoader;
import com.sistemalibreria.sistemalibreria.model.JefeTienda;
import com.sistemalibreria.sistemalibreria.model.JefeTienda;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JefeTiendaDAO {

    private Connection conexion;

    public JefeTiendaDAO() throws IOException, SQLException {
        DatabaseConfigLoader dbconfig = new DatabaseConfigLoader();
        String url = String.format("%s;databaseName=%s;user=%s;password=%s", dbconfig.getURL(), dbconfig.getName(), dbconfig.getUser(), dbconfig.getPassword());

        try {
            //cargar el driver del sqlServer
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            //aqui establezco la conexion, ya tengo una conexion abierta, usando el driver manager
            this.conexion = DriverManager.getConnection(url);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Driver not found", e);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error establishing connection", e);
        }
    }

    public void insertarJefeTienda(String nombre, String apellidoPaterno, String apellidoMaterno, String dni) throws SQLException {
        CallableStatement stmt = this.conexion.prepareCall("{call SP_insertarJefeTienda(?,?,?,?)}");

        stmt.setString(1, nombre);
        stmt.setString(2, apellidoPaterno);
        stmt.setString(3, apellidoMaterno);
        stmt.setString(4, dni);

        stmt.execute();

        this.conexion.close();

    }

    public List<JefeTienda> buscarJefeTiendaxApellidos(String apellidoPaterno, String apellidoMaterno) throws SQLException {
        List<JefeTienda> listaJefeTienda = new ArrayList<JefeTienda>();
        CallableStatement stmt = this.conexion.prepareCall("{call SP_buscarJefeTiendaxApellidos(?,?)}");

        stmt.setString(1, apellidoPaterno);
        stmt.setString(2, apellidoMaterno);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            JefeTienda jefeTienda = new JefeTienda();
            jefeTienda.setCodigo(rs.getInt("codigo"));
            jefeTienda.setNombre(rs.getString("nombre"));
            jefeTienda.setApellidoPaterno(rs.getString("apellidoPaterno"));
            jefeTienda.setApellidoMaterno(rs.getString("apellidoMaterno"));
            jefeTienda.setDni(rs.getString("dni"));

            listaJefeTienda.add(jefeTienda);
        }

        this.conexion.close();
        return listaJefeTienda;
    }
    
    public JefeTienda buscarJefeTiendaxCodigo(int codigo) throws SQLException {
        JefeTienda jefeTienda = new JefeTienda();
        String sentenciaSQL = "SELECT * FROM JefeTienda WHERE codigo = ?";
        PreparedStatement stmt = this.conexion.prepareStatement(sentenciaSQL);
        stmt.setInt(1, codigo);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            jefeTienda.setCodigo(rs.getInt("codigo"));
            jefeTienda.setNombre(rs.getString("nombre"));
            jefeTienda.setApellidoPaterno(rs.getString("apellidoPaterno"));
            jefeTienda.setApellidoMaterno(rs.getString("apellidoMaterno"));
            jefeTienda.setDni(rs.getString("dni"));
        }

        this.conexion.close();
        return jefeTienda;
    }

    public void editarJefeTienda(int codigo, String nombre, String apellidoPaterno, String apellidoMaterno, String dni) throws SQLException {
        String sentenciaSQL = "UPDATE JefeTienda SET nombre = ?, apellidoPaterno = ?, apellidoMaterno = ?, dni = ? WHERE codigo = ?";
        PreparedStatement stmt = this.conexion.prepareStatement(sentenciaSQL);
        stmt.setString(1, nombre);
        stmt.setString(2,apellidoPaterno);
        stmt.setString(3,apellidoMaterno);
        stmt.setString(4, dni);
        stmt.setInt(5,codigo);

        stmt.execute();
        this.conexion.close();
    }

    public void eliminarJefeTienda(int codigo) throws SQLException {
        String sentenciaSQL = "DELETE FROM JefeTienda WHERE codigo = ?";
        PreparedStatement stmt = this.conexion.prepareStatement(sentenciaSQL);
        stmt.setInt(1,codigo);
        stmt.execute();
        this.conexion.close();
    }

}