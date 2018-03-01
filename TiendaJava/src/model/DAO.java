package model;

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
}
