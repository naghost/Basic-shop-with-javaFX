package model;

import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class DAO {
    Connection connection = null;

    public DAO(){

        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/Tienda",
                    "root", "ta-088v3");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UsuarioModel comprobarInicio(UsuarioModel datos) {
        String sql = "SELECT * FROM Usuario WHERE Email='"+datos.getEmail()+"' AND Password='"+datos.getPassword()+"'";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs =stmt.executeQuery(sql);
            while (rs.next()){
                datos.setIDUsuario(rs.getInt("IDUsuario"));
                datos.setNombre(rs.getString("Nombre"));
                datos.setApellidos(rs.getString("Apellidos"));
                datos.setDNI(rs.getString("DNI"));
                datos.setTelefono(rs.getInt("Telefono"));
                datos.setDireccion(rs.getString("Direccion"));
                datos.setEmail(rs.getString("Email"));
                datos.setPassword(rs.getString("Password"));
                datos.setAdmin(rs.getInt("Admin"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection!=null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return datos;

    }

    public UsuarioModel registrarPersona(UsuarioModel usuario) {
        String sql = "INSERT INTO Usuario VALUES (NULL,'"
                +usuario.getNombre()
                +"', '"+usuario.getApellidos()
                +"', '"+usuario.getDNI()
                +"', "+usuario.getTelefono()
                +", '"+usuario.getDireccion()
                +"', '"+usuario.getEmail()
                +"', '"+usuario.getPassword()
                +"', "+usuario.getAdmin()+")";
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            usuario =comprobarInicio(usuario);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection!=null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return usuario;


    }

    public ObservableList<ProductoModel> buscar(String busqueda, ObservableList<ProductoModel> listView) {
        String sql = "SELECT Productos.IDProducto, Productos.Imagen, Productos.Titulo, Productos.Autor, Productos.Genero, Productos.Año, Productos.Precio, Productos.Stock, Tipo.Nombre FROM Productos INNER JOIN Tipo ON Productos.IDTipo = Tipo.IDTipo WHERE UPPER(Titulo) LIKE UPPER('%"+busqueda+"%')";
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ProductoModel producto;
            while (rs.next()){
                producto = new ProductoModel();
                producto.setId(rs.getInt("IDProducto"));
                Blob b = rs.getBlob("Imagen");
                InputStream s = b.getBinaryStream();
                BufferedImage a = ImageIO.read(s);
                producto.setImagen(SwingFXUtils.toFXImage(a,null));
                System.out.println(a);
                producto.setTitulo(rs.getString("Titulo"));
                producto.setGenero(rs.getString("Genero"));
                producto.setAutor(rs.getString("Autor"));
                producto.setAño(rs.getInt("Año"));
                producto.setPrecio(rs.getDouble("Precio"));
                producto.setTipo(rs.getString("Nombre"));

                listView.add(producto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection!=null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


        return listView;
    }

    public void RegistrarProducto(String text, String autorText, String generoText, int anio, double precio, int stock, String value, FileInputStream fileInputStream) {
        PreparedStatement stmt =null;
        try {
            String sql ="INSERT INTO Productos VALUES(NULL, ?,?,?,?,?,?,?,?)";

            stmt = connection.prepareStatement(sql);
            stmt.setBlob(1, fileInputStream);
            stmt.setString(2, text);
            stmt.setString(3,autorText);
            stmt.setString(4, generoText);
            stmt.setInt(5, anio);
            stmt.setDouble(6,precio);
            stmt.setInt(7, stock);
            switch(value){
                case "Libros":
                    stmt.setInt(8,1);
                    break;
                case "Musica":
                    stmt.setInt(8, 2);
                    break;
                case "Peliculas":
                    stmt.setInt(8, 3);
                    break;
            }
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection!=null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt!=null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public ObservableList<UsuarioModelFX> mostrarTodosUsuarios(ObservableList<UsuarioModelFX> lista) {
        String query = "SELECT * FROM Usuario";
        Statement stmt = null;
        try{
            stmt=connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            UsuarioModelFX usuario;
            while (rs.next()){
                usuario = new UsuarioModelFX();

                usuario.setIDUsuario(rs.getInt(1));
                usuario.setNombre(rs.getString(2));
                usuario.setApellidos(rs.getString(3));
                usuario.setDNI(rs.getString(4));
                usuario.setTelefono(rs.getInt(5));
                usuario.setDireccion(rs.getString(6));
                usuario.setEmail(rs.getString(7));
                usuario.setPassword(rs.getString(8));
                usuario.setAdmin(rs.getInt(9));
                lista.add(usuario);
            }
        } catch (SQLException e) {

        }finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return lista;
    }

    public void editarUsuario(UsuarioModelFX modelo) {
        String sql = "UPDATE Usuario SET Nombre = ?, Apellidos = ?, DNI = ?, Telefono = ?, Direccion = ?, Email = ?, Password = ?, Admin = ? WHERE IDUsuario = ?";
        PreparedStatement stmt = null;
        try{
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, modelo.getNombre());
            System.out.println(modelo.getNombre());
            stmt.setString(2, modelo.getApellidos());
            stmt.setString(3, modelo.getDNI());
            stmt.setInt(4, modelo.getTelefono());
            stmt.setString(5, modelo.getDireccion());
            stmt.setString(6, modelo.getEmail());
            stmt.setString(7, modelo.getPassword());
            stmt.setInt(8, modelo.getAdmin());
            stmt.setInt(9, modelo.getIDUsuario());
            int count = stmt.executeUpdate();
            System.out.println(count);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void eliminarUsuario(UsuarioModelFX usuario) {
        String sql = "DELETE FROM Usuario WHERE IDUsuario= ?";
        PreparedStatement stmt = null;
        try{
            stmt= connection.prepareStatement(sql);
            stmt.setInt(1, usuario.getIDUsuario());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<UsuarioModelFX> buscarUsuarios(ObservableList<UsuarioModelFX> options, String busqueda, String filtro) {
        PreparedStatement stmt = null;
        String sql = null;

        switch (filtro){
            case "Nombre":
                sql = "SELECT * FROM Usuario WHERE UPPER(Nombre) LIKE UPPER(?)";
                break;
            case "Apellidos":
                sql = "SELECT * FROM Usuario WHERE UPPER(Apellidos) LIKE UPPER(?)";
                break;
            case "DNI":
                sql = "SELECT * FROM Usuario WHERE UPPER(DNI) LIKE UPPER(?)";
                break;
            case "Direccion":
                sql = "SELECT * FROM Usuario WHERE UPPER(Direccion) LIKE UPPER(?)";
                break;
            case "Email":
                sql = "SELECT * FROM Usuario WHERE UPPER(Email) LIKE UPPER(?)";
                break;
            case "Telefono":
                sql = "SELECT * FROM Usuario WHERE UPPER(Telefono) LIKE UPPER(?)";
                break;
            case "Admin":
                sql = "SELECT * FROM Usuario WHERE UPPER(Admin) LIKE UPPER(?)";
                break;
        }

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1,"%"+busqueda+"%");

            ResultSet rs= stmt.executeQuery();
            UsuarioModelFX usuario = null;
            while (rs.next()){
                usuario = new UsuarioModelFX();
                usuario.setIDUsuario(rs.getInt(1));
                usuario.setNombre(rs.getString(2));
                usuario.setApellidos(rs.getString(3));
                usuario.setDNI(rs.getString(4));
                usuario.setTelefono(rs.getInt(5));
                usuario.setDireccion(rs.getString(6));
                usuario.setEmail(rs.getString(7));
                usuario.setPassword(rs.getString(8));
                usuario.setAdmin(rs.getInt(9));
                options.add(usuario);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return options;
    }

    public ObservableList<FacturaModelFX> FacturaUsuario(ObservableList<FacturaModelFX> lista, UsuarioModelFX consulta) {
        String sql = "SELECT Factura.IDFactura, Factura.FechaHora, Usuario.Nombre, Estado.Nombre FROM Factura INNER JOIN Usuario ON Factura.IDUsuario=Usuario.IDUsuario INNER JOIN Estado ON Factura.IDEstado=Estado.IDEstado WHERE Factura.IDUsuario=?";
        PreparedStatement stmt = null;

        try{
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,consulta.getIDUsuario());
            ResultSet rs = stmt.executeQuery();
            FacturaModelFX factura = null;

            while (rs.next()){
                factura = new FacturaModelFX();
                factura.setIDFactura(rs.getInt(1));
                factura.setFechaHora(rs.getString(2));
                factura.setUsuario(rs.getString(3));
                factura.setEstado(rs.getString(4));
                lista.add(factura);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public ObservableList<ProductoCompradoModel> buscarProductoFactura(FacturaModelFX factura, ObservableList<ProductoCompradoModel> lista) {
        String sql = "SELECT Productos.Titulo, ProductosFactura.Cantidad, ProductosFactura.Precio, ProductosFactura.IDProducto FROM ProductosFactura INNER JOIN Productos ON ProductosFactura.IDProducto = Productos.IDProducto WHERE ProductosFactura.IDFactura=? ";
        PreparedStatement stmt = null;
        try{
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, factura.getIDFactura());
            ResultSet rs = stmt.executeQuery();
            ProductoCompradoModel producto;
            while (rs.next()){
                producto = new ProductoCompradoModel();
                producto.setNombre(rs.getString(1));
                producto.setCantidad(rs.getInt(2));
                producto.setPrecio(rs.getDouble(3));
                producto.setIDProducto(rs.getInt(4));
                lista.add(producto);
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return lista;
    }

    public void devolucion(ProductoCompradoModel producto, FacturaModelFX factura) {
        String sql = "UPDATE ProductosFactura SET Cantidad=? WHERE IDFactura=? AND IDProducto=?";
        PreparedStatement stmt = null;
        try{
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,producto.getCantidad());
            stmt.setInt(2, factura.getIDFactura());
            stmt.setInt(3, producto.getIDProducto());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
