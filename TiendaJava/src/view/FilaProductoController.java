package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.DiscosModel;
import model.LibrosModel;
import model.PeliculasModel;

import javax.swing.text.html.ImageView;


public class FilaProductoController {
    @FXML
    ImageView imagen;
    @FXML
    TextField titulo;
    @FXML
    TextField autor;
    @FXML
    TextField precio;
    @FXML
    Button boton;

    LibrosModel libro;

    PeliculasModel pelicula;

    DiscosModel disco;

    @FXML
    public void mostrarProducto(){

    }
}
