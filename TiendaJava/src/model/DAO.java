package model;

import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

/*
 * @author Miguel Angel Hernandez Rodriguez
 * @version 1.0
 * @param connection objecto encargado de realizar la conexion con mysql
 */

public class DAO {
    Connection connection = null;

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * @param connection realiza la conexion a mysql
     */

    public DAO(){
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/Tienda",
                    "root", "ta-088v3");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * @param datos datos del usuario que intenta iniciar sesion
     * @param sql variable que contiene la consulta
     * @param stmt se encarga de realizar la consulta
     * @param rs se encarga de recibir los datos recibidos por stmt
     * @connection variable encargada de conectarse a mysql
     * @return datos devuelve los datos del usuario encontrado (si existe)
     */

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

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * @param datos del usuario registrado
     * @param sql variable que contiene la consulta
     * @param stmt se encarga de realizar la consulta
     * @return datos devuelve los datos del usuario registrado que previamente se ha comprobado su exitenca
     * @see comprobarInicio(UsuarioModel datos)
     */

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

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * @param busqueda variable que va a contener la coincidencia con los datos de la tabla
     * @param listView lista donde vamos a guardar los productos encontrados
     * @param contador es un label que va a mostrar la cantidad de productos que estan guardados en el carro pasamos a cada elemento del listView
     * @param usuario variable con los datos del usuario(se utilizan en la interfaz de MostrarProducto para comprobar si es admin)
     * @param sql contiene la sentencia sql
     * @param stmt se encarga de ejecutar la sentencia
     * @param connection se encarga de realizar la conexion con mysql
     * @param producto Modelo del producto para poder rellenar la lista "listView"
     * @param b variable auxiliar para recoger las imagenes de la BBDD
     * @param s variable auxiliar que convierte el blob a un stream de datos
     * @param a convierte el stream de datos a una imagen
     * @return listView lista con los productos encontrados en la lista
     */

    public ObservableList<ProductoModel> buscar(String busqueda, ObservableList<ProductoModel> listView, ArrayList<ProductoModel> carro, Label contador, UsuarioModel usuario) {
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
                producto.setTitulo(rs.getString("Titulo"));
                producto.setGenero(rs.getString("Genero"));
                producto.setAutor(rs.getString("Autor"));
                producto.setAño(rs.getInt("Año"));
                producto.setStock(rs.getInt("Stock"));
                producto.setPrecio(rs.getDouble("Precio"));
                producto.setTipo(rs.getString("Nombre"));
                producto.setUsuario(usuario);
                producto.setCarrito(carro);
                producto.setContador(contador);
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

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * @param text Titulo del producto
     * @param generoText Genero del producto
     * @param autorText autor del producto
     * @param anio  año de publicacion del producto
     * @param precio precio del producto
     * @param stock stock del producto
     * @param value tipo de producto
     * @param fileInputStream imagen convertida a blob
     * @param stmt ejecuta la sentencia sql
     * @param sql contiene la sentencia sql
     * @param connection realiza la conexion con el servidor de mysql
     */

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

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * @param lista lista para mostrar los usuarios
     * @param query sentencia sql
     * @param stmt se encarga de ejecutar las sentencias sql
     * @param connection se encarga de realizar la conexion con mysql
     * @param rs se encarga de recibir los datos de la setencia sql
     * @param usuario en este objeto guardaremos los datos de cada uno de los resultados la ejecucion de la consulta
     * @return lista
     */

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

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * @param modelo datos del usuario que se va a modificar
     * @param stmt ejecuta las sentencias sql
     * @param sql sentencia sql
     * @param connection se encarga de realizar la conexion con mysql
     */

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

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * @param usuario usuario que se va a eliminar de la base de datos
     * @param sql sentencia sql
     * @param stmt ejecuta las sentencias sql
     * @param connection se encarga la conexion con mysql
     */

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

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * @param options lista con los usuarios encontrados
     * @param busqueda parametro por el que se va a realizar la busqueda
     * @param filtro indica sobre que columna se va a realizar la busqueda
     * @param stmt se encarga de realizar las sentencias sql
     * @param rs recoge los datos encontrados por stmt
     * @return options
     */

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

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * @param lista lisa con las facturas del usuario
     * @param consulta modelo de usuario del que se consulta la factura
     * @param sql sentencia sql
     * @param stmt ejecuta las sentencias sql
     * @param connection se encarga de realizar la conexion con mysql
     * @param rs se encarga de recoger los datos de la ejecucion de la consulta sql
     * @param factura objeto para recoger los datos obtenidos de la consulta y añadir a lista
     * @return lista lista con las facturas del usuario
     */
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

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * @param factura modelo de la factura de la que se buscaran los productos asociados
     * @param lista lista con los productos asociados a la factura
     * @param sql setencia sql
     * @param stmt se encarga de realizar las sentencias sql
     * @param rs se encarga de recoger los datos obtenidos de la ejecucion de las sentencias sql
     * @param producto modelo de los produtos que estan asociado con factura
     * @param connection se encarga de gestionar la conexion con mysql
     * @return lista con los productos asociados a la factura
     */
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
    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * @param producto modelo del producto que se quiere modificar
     * @param factura modelo de factura
     * @param sql variable con las sentencias sql
     * @param stmt ejecuta las sentencias sql
     * @param connection se encarga de la conexion con mysql
     */
    public void devolucion(ProductoCompradoModel producto, FacturaModelFX factura) {
        String sql = "UPDATE ProductosFactura SET Cantidad=? WHERE IDFactura=? AND IDProducto=?";
        PreparedStatement stmt = null;
        try{
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,producto.getCantidad());
            stmt.setInt(2, factura.getIDFactura());
            stmt.setInt(3, producto.getIDProducto());
            stmt.executeUpdate();
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
        }

    }

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * @param productos lista de productos
     */
    public void comprarProductos(ObservableList<ProductoModel> productos, UsuarioModel usuario) {
        String sql = "INSERT INTO Factura VALUES(NULL,?,?,?)";
        PreparedStatement stmt =null;
        int key = 0;
        try{
            stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            stmt.setInt(2,usuario.getIDUsuario());
            stmt.setInt(3, 1);
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                 key = rs.getInt(1);
            }
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
        }
        CallableStatement cStmt=null;
        String query = "{CALL InsertarProducto(?,?,?,?,?)}";
        int resultado=0;
        try {
            for (int i=0;i<productos.size();i++) {
                cStmt = connection.prepareCall(query);
                cStmt.setInt(1,productos.get(i).getId());
                System.out.println(productos.get(i).getCantidad());
                cStmt.setInt(2,productos.get(i).getCantidad());
                cStmt.setDouble(3, productos.get(i).getPrecio());
                cStmt.setInt(4,key);
                cStmt.registerOutParameter(5, Types.INTEGER);
                cStmt.execute();
                resultado= cStmt.getInt(5);
                if (resultado==0){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alguien se te ha adelantado :(");
                    alert.setHeaderText(null);
                    alert.setContentText("Lamentamos informarte que el producto "+productos.get(i).getTitulo()+" no esta actualmente diponible");
                    alert.showAndWait();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (cStmt != null){
                try {
                    cStmt.close();
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

    public void eliminarProducto(ProductoModel producto) {
        String sql = "DELETE FROM Productos WHERE IDProducto=?";
        PreparedStatement stmt = null;
        try{
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, producto.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (stmt!=null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void EditarProducto(String titulo, String autor, String genero, int anio, double precio, int stock, String tip, FileInputStream fileInputStream, ProductoModel producto) {
        String sql = "UPDATE Productos SET Imagen=?, Titulo=?, Autor=?, Genero=?, Año=?, Precio=?, Stock=?, IDTipo=? WHERE IDProducto =?";
        PreparedStatement stmt = null;
        try{
            stmt = connection.prepareStatement(sql);
            stmt.setBlob(1,fileInputStream);
            stmt.setString(2,titulo);
            stmt.setString(3,autor);
            stmt.setString(4,genero);
            stmt.setInt(5,anio);
            stmt.setDouble(6,precio);
            stmt.setInt(7, stock);
            System.out.println(tip);
            switch(tip){
                case "Libros":
                    stmt.setInt(8,1);
                    break;
                case "Discos":
                    stmt.setInt(8, 2);
                    break;
                case "Peliculas":
                    stmt.setInt(8, 3);
                    break;
            }
            stmt.setInt(9, producto.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (stmt !=null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void editarPersona(UsuarioModel usuario) {
        String sql = "UPDATE Usuario SET Nombre=?, Apellidos=?, DNI=?, Telefono=?, Direccion=?, Email=?, Password=? WHERE IDUsuario=?";
        PreparedStatement stmt = null;
        try{
         stmt = connection.prepareStatement(sql);
         stmt.setString(1, usuario.getNombre());
         stmt.setString(2,usuario.getApellidos());
         stmt.setString(3, usuario.getDNI());
         stmt.setInt(4,usuario.getTelefono());
         stmt.setString(5,usuario.getDireccion());
         stmt.setString(6, usuario.getEmail());
         stmt.setString(7,usuario.getPassword());
         stmt.setInt(8,usuario.getIDUsuario());
         stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
