package model;

import com.sun.xml.internal.bind.v2.model.core.ID;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UsuarioModelFX {
    IntegerProperty IDUsuario;
    StringProperty Nombre;
    StringProperty Apellidos;
    StringProperty DNI;
    IntegerProperty Telefono;
    StringProperty Direccion;
    StringProperty Email;
    StringProperty Password;
    IntegerProperty Admin;

    public UsuarioModelFX(){
        IDUsuario = new SimpleIntegerProperty();
        Nombre = new SimpleStringProperty();
        Apellidos = new SimpleStringProperty();
        DNI = new SimpleStringProperty();
        Telefono = new SimpleIntegerProperty();
        Direccion = new SimpleStringProperty();
        Email = new SimpleStringProperty();
        Password = new SimpleStringProperty();
        Admin = new SimpleIntegerProperty();
    }

    public UsuarioModelFX(UsuarioModel usuario){
        IDUsuario = new SimpleIntegerProperty();
        Nombre = new SimpleStringProperty();
        Apellidos = new SimpleStringProperty();
        DNI = new SimpleStringProperty();
        Telefono = new SimpleIntegerProperty();
        Direccion = new SimpleStringProperty();
        Email = new SimpleStringProperty();
        Password = new SimpleStringProperty();
        Admin = new SimpleIntegerProperty();

        System.out.println(usuario.getIDUsuario());
        setIDUsuario(usuario.getIDUsuario());
        setNombre(usuario.getNombre());
        setApellidos(usuario.getApellidos());
        setDNI(usuario.getDNI());
        setTelefono(usuario.getTelefono());
        setDireccion(usuario.getDireccion());
        setEmail(usuario.getEmail());
        setPassword(usuario.getPassword());
        setAdmin(usuario.getAdmin());
    }

    public int getIDUsuario() {
        return IDUsuario.get();
    }

    public IntegerProperty IDUsuarioProperty() {
        return IDUsuario;
    }

    public void setIDUsuario(int IDUsuario) {
        this.IDUsuario.set(IDUsuario);
    }

    public String getNombre() {
        return Nombre.get();
    }

    public StringProperty nombreProperty() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre.set(nombre);
    }

    public String getApellidos() {
        return Apellidos.get();
    }

    public StringProperty apellidosProperty() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        this.Apellidos.set(apellidos);
    }

    public String getDNI() {
        return DNI.get();
    }

    public StringProperty DNIProperty() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI.set(DNI);
    }

    public int getTelefono() {
        return Telefono.get();
    }

    public IntegerProperty telefonoProperty() {
        return Telefono;
    }

    public void setTelefono(int telefono) {
        this.Telefono.set(telefono);
    }

    public String getDireccion() {
        return Direccion.get();
    }

    public StringProperty direccionProperty() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        this.Direccion.set(direccion);
    }

    public String getEmail() {
        return Email.get();
    }

    public StringProperty emailProperty() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email.set(email);
    }

    public String getPassword() {
        return Password.get();
    }

    public StringProperty passwordProperty() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password.set(password);
    }

    public int getAdmin() {
        return Admin.get();
    }

    public IntegerProperty adminProperty() {
        return Admin;
    }

    public void setAdmin(int admin) {
        this.Admin.set(admin);
    }
}
