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

    @FXML
    public void seleccionarImagen(){
        FileChooser fileChooser = new FileChooser();
        imagen = fileChooser.showOpenDialog(tipo.getScene().getWindow());
        if (imagen!=null){
            ruta.setText(imagen.getAbsolutePath());
        }


    }

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
