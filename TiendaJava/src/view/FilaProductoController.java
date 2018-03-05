package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.ProductoModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;


public class FilaProductoController extends ListCell<ProductoModel> implements Initializable {
    @FXML
    ImageView imagen;
    @FXML
    Label titulo;
    @FXML
    Label autor;
    @FXML
    Label precio;
    @FXML
    Button boton;

    @FXML
    AnchorPane root;

    ProductoModel model;


    private static final Logger LOG = Logger.getLogger(FilaProductoController.class.getName());

    public static FilaProductoController newInstance(){
        FXMLLoader loader = new FXMLLoader(FilaProductoController.class.getResource("FilaProducto.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loader.getController();

    }

    @FXML
    public void mostrarProducto(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    setGraphic(root);
    }

    protected void updateItem(ProductoModel item, boolean empty) {
        super.updateItem(item, empty); // <-- Important
        // make empty cell items invisible
        root.getChildrenUnmodifiable().forEach(c -> c.setVisible(!empty));
        // update valid cells with model data
        if (!empty && item != null && !item.equals(this.model)) {
            imagen.setImage(item.getImagen());
            titulo.textProperty().set(item.getTitulo());
            autor.textProperty().set(item.getAutor());
            precio.textProperty().set(String.valueOf(item.getPrecio()));

        }
        // keep a reference to the model item in the ListCell
        this.model = item;
    }

}
