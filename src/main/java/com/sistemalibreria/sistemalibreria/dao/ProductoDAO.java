package com.sistemalibreria.sistemalibreria.dao;

import com.sistemalibreria.sistemalibreria.config.DatabaseConfigLoader;
import com.sistemalibreria.sistemalibreria.model.Producto;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    private Connection conexion;

    public ProductoDAO() throws IOException, SQLException {
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

    public List<Producto> buscarProductoxNombre(String nombre) throws SQLException {
        List<Producto> listaProductos = new ArrayList<Producto>();
        String sentenciaSQL = "SELECT * FROM Producto WHERE nombre like ?";
        PreparedStatement stmt = this.conexion.prepareStatement(sentenciaSQL);
        stmt.setString(1, "%"+nombre+"%");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Producto producto = new Producto();
            producto.setId(rs.getInt("id"));
            producto.setNombre(rs.getString("nombre"));
            producto.setTipo(rs.getString("tipo"));
            producto.setEstado(rs.getString("estado"));
            producto.setPrecio(rs.getFloat("precio"));

            listaProductos.add(producto);
        }

        this.conexion.close();
        return listaProductos;
    }

    public Producto buscarProductoxId(int idProducto) throws SQLException {
        Producto producto = new Producto();
        String sentenciaSQL = "SELECT * FROM Producto WHERE id = ?";
        PreparedStatement stmt = this.conexion.prepareStatement(sentenciaSQL);
        stmt.setInt(1, idProducto);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            producto.setId(rs.getInt("id"));
            producto.setNombre(rs.getString("nombre"));
            producto.setTipo(rs.getString("tipo"));
            producto.setEstado(rs.getString("estado"));
            producto.setPrecio(rs.getFloat("precio"));
        }

        this.conexion.close();
        return producto;
    }

    public void eliminarProducto(int id) throws SQLException {
        String sentenciaSQL = "DELETE FROM Producto WHERE id = ?";
        PreparedStatement stmt = this.conexion.prepareStatement(sentenciaSQL);
        stmt.setInt(1,id);
        stmt.execute();
        this.conexion.close();
    }

    public void grabarProducto(String nombre, String tipo, float precio) throws SQLException {
        String sentenciaSQL = "INSERT INTO Producto (nombre, tipo, precio, estado) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = this.conexion.prepareStatement(sentenciaSQL);
        stmt.setString(1, nombre);
        stmt.setString(2, tipo);
        stmt.setFloat(3, precio);
        stmt.setString(4, "Disponible");

        stmt.execute();
        this.conexion.close();
    }

    public void editarProducto(String nombre, String tipo, float precio, String estado, int id) throws SQLException {
        String sentenciaSQL = "UPDATE Producto SET nombre = ?, tipo = ?, precio = ?, estado = ? WHERE id = ?";
        PreparedStatement stmt = this.conexion.prepareStatement(sentenciaSQL);
        stmt.setString(1, nombre);
        stmt.setString(2,tipo);
        stmt.setFloat(3,precio);
        stmt.setString(4, estado);
        stmt.setInt(5,id);

        stmt.execute();
        this.conexion.close();
    }
}
