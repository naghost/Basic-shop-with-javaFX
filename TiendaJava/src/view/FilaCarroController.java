package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.ProductoModel;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class FilaCarroController extends ListCell<ProductoModel> implements Initializable, ChangeListener<String> {

    @FXML
    ImageView imagen;
    @FXML
    Label Titulo;
    @FXML
    Label Autor;
    @FXML
    Label Año;
    @FXML
    Label Precio;
    @FXML
    TextField Cantidad;
    @FXML
    AnchorPane root;

    ProductoModel model;

    ArrayList<ProductoModel> carro;

    Label contador;

    ObservableList<ProductoModel> productos;

    ListView<ProductoModel> listView;


    private static final Logger LOG = Logger.getLogger(FilaProductoController.class.getName());

    public static FilaCarroController newInstance(){
        FXMLLoader loader = new FXMLLoader(FilaProductoController.class.getResource("FilaCarro.fxml"));

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

    @FXML
    public void eliminarProducto(){

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
                Titulo.setText(item.getTitulo());
                Año.setText("Año: "+item.getAño());
                Autor.setText("de "+item.getAutor());
                Precio.setText("EUR "+item.getPrecio());
                Cantidad.setText(String.valueOf(item.getCantidad()));
                 Cantidad.textProperty().addListener(this);

        }
        // keep a reference to the model item in the ListCell
        this.model = item;


    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if (!newValue.matches("\\d*")) {
            Cantidad.setText(newValue.replaceAll("[^\\d]", ""));
        }else {
            if (!newValue.equals("")) {
                if (model.getStock() < Integer.parseInt(newValue)) {
                    Cantidad.setText(oldValue);
                }
            }
        }
    }
}
