package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.DAO;
import model.ProductoModel;
import model.UsuarioModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
/*
 * @author Miguel Angel Hernandez Rodriguez
 * @version 1.0
 * @param imagen Archivo de imagen
 * @param Titulo Campo Titulo tabla Producto
 * @param Autor Campo Autor tabla Producto
 * @param Genero Campo Genero tabla Producto
 * @param Año Campo Año tabla Producto
 * @param Precio Campo Precio tabla Producto
 * @param Stock Campo Stock tabla Producto
 * @param tipo Campo IDTipo tabla Producto con asociacion Tipo
 * @param ruta label que muestra la ruta del archivo
 * @param carrito ArrayList que tiene los productos seleccionados del usuario
 * @param usuario Usuario logueado
 */

public class EditarProductoController {
    @FXML
    TextField Titulo;
    @FXML
    TextField Autor;
    @FXML
    TextField Genero;
    @FXML
    TextField Año;
    @FXML
    TextField Precio;
    @FXML
    TextField Stock;
    @FXML
    ComboBox tipo;
    @FXML
    Label ruta;
    File imagen;
    UsuarioModel usuario;
    ProductoModel producto;

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * @param imagen Archivo de imagen
     * @param fileChooser objeto que crea una interfaz para seleccionar archivos
     */

    @FXML
    public void seleccionarImagen(){
        FileChooser fileChooser = new FileChooser();
        imagen = fileChooser.showOpenDialog(tipo.getScene().getWindow());
        if (imagen!=null){
            ruta.setText(imagen.getAbsolutePath());
        }


    }

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * @param producto objeto con la informacion del producto que se va a editar
     * @param Titulo Campo Titulo tabla Producto
     * @param Autor Campo Autor tabla Producto
     * @param Genero Campo Genero tabla Producto
     * @param Año Campo Año tabla Producto
     * @param Precio Campo Precio tabla Producto
     * @param Stock Campo Stock tabla Producto
     * @param tipo Campo IDTipo tabla Producto con asociacion Tipo
     */

    public void datos(ProductoModel producto){
        this.producto=producto;
        Titulo.setText(producto.getTitulo());
        Autor.setText(producto.getAutor());
        Genero.setText(producto.getGenero());
        Año.setText(String.valueOf(producto.getAño()));
        Precio.setText(String.valueOf(producto.getPrecio()));
        Stock.setText(String.valueOf(producto.getStock()));
        switch (producto.getTipo()){
            case "Libros":
                tipo.getSelectionModel().select(1);
                break;
            case "Peliculas":
                tipo.getSelectionModel().select(2);
                break;
            case "Discos":
                tipo.getSelectionModel().select(3);
                break;
        }
    }

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * @param imagen Archivo de imagen
     * @param Titulo Campo Titulo tabla Producto
     * @param Autor Campo Autor tabla Producto
     * @param Genero Campo Genero tabla Producto
     * @param Año Campo Año tabla Producto
     * @param Precio Campo Precio tabla Producto
     * @param Stock Campo Stock tabla Producto
     * @param tipo Campo IDTipo tabla Producto con asociacion Tipo
     * @param ruta label que muestra la ruta del archivo
     * @param carrito ArrayList que tiene los productos seleccionados del usuario
     * @param usuario Usuario logueado
     * @param anio @see @param Año
     * @param precio @see @param Precio
     * @param stock @see @param Stock
     * @param fis @see @param imagen
     * @param fileInputStream  @see @param imagen
     * @param dao objeto que se encarga de las operaciones en la BBDD
     * @param tip variable que define el tipo de producto que esç
     */
    @FXML
    public void guardarDatos(){
        if (Titulo.getText().equals("") || Autor.getText().equals("") || Genero.getText().equals("") || Año.getText().equals("") || Precio.getText().equals("") || Stock.getText().equals("") || tipo.getValue() == null || imagen==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error en el registro");
            alert.setHeaderText("Hay campos vacios");
            alert.showAndWait();
        }else {
            int anio;
            double precio;
            int stock;
            ByteArrayOutputStream bos = null;
            try {
                anio = Integer.parseInt(Año.getText());
                precio = Double.parseDouble(Precio.getText());
                stock = Integer.parseInt(Stock.getText());
                FileInputStream fis = new FileInputStream(imagen);
                FileInputStream fileInputStream = new FileInputStream(imagen);
                DAO dao = new DAO();
                String tip = (String) tipo.getValue();
                System.out.println(tipo.getValue());
                dao.EditarProducto(Titulo.getText(), Autor.getText(), Genero.getText(), anio, precio, stock, tip, fileInputStream, producto);
                fileInputStream.close();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error en el registro");
                alert.setHeaderText("Problemas con tipos de datos");
                alert.showAndWait();
            }

        }
    }
    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * @param tipo Campo IDTipo tabla Producto con asociacion Tipo
     * @param options lista con la que rellenamos la interfaz
     */
    public void opciones(){
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Libros",
                        "Peliculas",
                        "Discos"
                );
        tipo.setItems(options);
    }
}
