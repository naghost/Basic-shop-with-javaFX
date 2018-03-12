package model;

/*
 * @author Miguel Angel Hernandez Rodriguez
 * @version 1.0
 * @param IDUsuario Campo IDUsuario tabla Usuarios
 * @param Nombre Campo Nombre tabla Usuarios
 * @param Apellidos Campo Apellidos tabla Usuarios
 * @param DNI Campo DNI tabla Usuarios
 * @param Telefono Campo Telefono tabla Usuarios
 * @param Direccion Campo Direccion tabla Usuarios
 * @param Email Campo Email tabla Usuarios
 * @param Password Campo Password tabla Usuarios
 * @param Admin Campo Admin tabla Usuarios
 */
public class UsuarioModel {
	Integer IDUsuario;
	String Nombre;
	String Apellidos;
	String DNI;
	Integer Telefono;
	String Direccion;
	String Email;
	String Password;
	Integer Admin;
	
	public UsuarioModel() {
		
	}

	public Integer getIDUsuario() {
		return IDUsuario;
	}

	public void setIDUsuario(Integer iDUsuario) {
		IDUsuario = iDUsuario;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getApellidos() {
		return Apellidos;
	}

	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public Integer getTelefono() {
		return Telefono;
	}

	public void setTelefono(Integer telefono) {
		Telefono = telefono;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public Integer getAdmin() {
		return Admin;
	}

	public void setAdmin(Integer admin) {
		Admin = admin;
	}
	
}
