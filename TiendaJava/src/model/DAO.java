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

// Database connect
// Conectamos con la base de datos
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

        try {
            String sql ="INSERT INTO Productos VALUES(NULL, ?,?,?,?,?,?,?,?)";

            PreparedStatement stmt = connection.prepareStatement(sql);
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
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
