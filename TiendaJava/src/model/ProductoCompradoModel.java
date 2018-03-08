package model;

import javafx.beans.property.*;

public class ProductoCompradoModel {
    IntegerProperty IDProducto;
    StringProperty Nombre;
    IntegerProperty Cantidad;
    DoubleProperty Precio;

    public ProductoCompradoModel(){
    IDProducto = new SimpleIntegerProperty();
    Nombre = new SimpleStringProperty();
    Cantidad = new SimpleIntegerProperty();
    Precio = new SimpleDoubleProperty();
    }

    public int getIDProducto() {
        return IDProducto.get();
    }

    public IntegerProperty IDProductoProperty() {
        return IDProducto;
    }

    public void setIDProducto(int IDProducto) {
        this.IDProducto.set(IDProducto);
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

    public int getCantidad() {
        return Cantidad.get();
    }

    public IntegerProperty cantidadProperty() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        this.Cantidad.set(cantidad);
    }

    public double getPrecio() {
        return Precio.get();
    }

    public DoubleProperty precioProperty() {
        return Precio;
    }

    public void setPrecio(double precio) {
        this.Precio.set(precio);
    }
}
