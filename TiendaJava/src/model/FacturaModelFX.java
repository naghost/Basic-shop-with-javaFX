package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/*
 * @author Miguel Angel Hernandez Rodriguez
 * @version 1.0
 * @param IDFactura campo IDFactura tabla CompraFactura
 * @param fechaHora campo Fecha-Hora tabla CompraFactura
 * @param Usuario  campo IDUsuario tabla CompraFactura con union con tabla Usuario
 * @param Estado campo Estado tabla CompraFactura con union con tabla Estado
 */
public class FacturaModelFX {
    IntegerProperty IDFactura;
    StringProperty fechaHora;
    StringProperty Usuario;
    StringProperty Estado;

    public FacturaModelFX(){
    IDFactura = new SimpleIntegerProperty();
    fechaHora = new SimpleStringProperty();
    Usuario = new SimpleStringProperty();
    Estado = new SimpleStringProperty();
    }

    public int getIDFactura() {
        return IDFactura.get();
    }

    public IntegerProperty IDFacturaProperty() {
        return IDFactura;
    }

    public void setIDFactura(int IDFactura) {
        this.IDFactura.set(IDFactura);
    }

    public String getFechaHora() {
        return fechaHora.get();
    }

    public StringProperty fechaHoraProperty() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora.set(fechaHora);
    }

    public String getEstado() {
        return Estado.get();
    }

    public StringProperty estadoProperty() {
        return Estado;
    }

    public void setEstado(String estado) {
        this.Estado.set(estado);
    }

    public String getUsuario() {
        return Usuario.get();
    }

    public StringProperty usuarioProperty() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        this.Usuario.set(usuario);
    }
}
