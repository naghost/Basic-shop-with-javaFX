package model;

import javafx.beans.property.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import java.util.ArrayList;

/*
 * @author Miguel Angel Hernandez Rodriguez
 * @version 1.0
 * @param id Campo IDProducto tabla Producto
 * @param Imagen Campo Imagen tabla Producto
 * @param Titulo Campo Titulo tabla Producto
 * @param Autor Campo Autor tabla Producto
 * @param Genero Campo Genero tabla Producto
 * @param Año Campo Año tabla Producto
 * @param Precio Campo Precio tabla Producto
 * @param Stock Campo Stock tabla Producto
 * @param Tipo Campo IDTipo tabla Producto con asociacion Tipo
 * @param cantidad se encarga de controlar la cantidad que hay en el carro de este producto
 * @param carrito Lista que contiene los productos del usuario (es necesario pasarlos por aqui para que los tenga el controlador de la vista)
 * @param contador es un label que aparece al lado de la imagen del carro que cuenta la cantidad de productos que se han añadido al carro
 * @param usuario es el modelo del usuario iniciado
 */
public class ProductoModel {
    IntegerProperty id = new SimpleIntegerProperty();
    Image Imagen;
    StringProperty Titulo = new SimpleStringProperty();
    StringProperty Autor = new SimpleStringProperty();
    StringProperty Genero = new SimpleStringProperty();
    IntegerProperty Año = new SimpleIntegerProperty();
    DoubleProperty Precio = new SimpleDoubleProperty();
    IntegerProperty Stock = new SimpleIntegerProperty();
    StringProperty Tipo = new SimpleStringProperty();
    IntegerProperty cantidad = new SimpleIntegerProperty(1);
    StringProperty Descripcion = new SimpleStringProperty();
    ArrayList<ProductoModel> carrito;
    Label contador;
    UsuarioModel usuario;

    public UsuarioModel getUsuario() { return usuario; }

    public void setUsuario(UsuarioModel usuario) { this.usuario = usuario; }

    public String getDescripcion() { return Descripcion.get(); }

    public StringProperty descripcionProperty() { return Descripcion; }

    public void setDescripcion(String descripcion) { this.Descripcion.set(descripcion); }

    public int getCantidad() {
        return cantidad.get();
    }

    public IntegerProperty cantidadProperty() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad.set(cantidad);
    }

    public Label getContador() {
        return contador;
    }

    public void setContador(Label contador) {
        this.contador = contador;
    }

    public ArrayList<ProductoModel> getCarrito() {
        return carrito;
    }

    public void setCarrito(ArrayList<ProductoModel> carrito) {
        this.carrito = carrito;
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public Image getImagen() {
        return Imagen;
    }

    public void setImagen(Image imagen) {
        Imagen = imagen;
    }

    public String getTitulo() {
        return Titulo.get();
    }

    public StringProperty tituloProperty() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        this.Titulo.set(titulo);
    }

    public String getAutor() {
        return Autor.get();
    }

    public StringProperty autorProperty() {
        return Autor;
    }

    public void setAutor(String autor) {
        this.Autor.set(autor);
    }

    public String getGenero() {
        return Genero.get();
    }

    public StringProperty generoProperty() {
        return Genero;
    }

    public void setGenero(String genero) {
        this.Genero.set(genero);
    }

    public int getAño() {
        return Año.get();
    }

    public IntegerProperty añoProperty() {
        return Año;
    }

    public void setAño(int año) {
        this.Año.set(año);
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

    public int getStock() {
        return Stock.get();
    }

    public IntegerProperty stockProperty() {
        return Stock;
    }

    public void setStock(int stock) {
        this.Stock.set(stock);
    }

    public String getTipo() {
        return Tipo.get();
    }

    public StringProperty tipoProperty() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        this.Tipo.set(tipo);
    }
}
