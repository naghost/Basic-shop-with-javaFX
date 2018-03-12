package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.ProductoModel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    Label año;
    @FXML
    Button boton;

    @FXML
    AnchorPane root;

    ProductoModel model;

    ArrayList<ProductoModel> carro;

    Label contador;


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
            año.textProperty().set("Año: "+String.valueOf(item.getAño()));
            autor.textProperty().set("de "+item.getAutor());
            precio.textProperty().set("EUR "+String.valueOf(item.getPrecio()));
            carro=item.getCarrito();
            System.out.println(item.getStock());
            if (item.getStock()==0){
                boton.setDisable(true);
                boton.setText("Producto no disponible");
            }else {
                boton.setDisable(false);
                boton.setText("Comprar");
            }
            contador=item.getContador();

        }
        // keep a reference to the model item in the ListCell
        this.model = item;
    }

    @FXML
    public void añadirCarro(){
        boolean existe = false;
        for(int i=0;i<carro.size();i++){
            if(carro.get(i).getId()== model.getId()){
                existe=true;
                if ((model.getCantidad()+1)<=model.getStock()) {
                    model.setCantidad(model.getCantidad() + 1);
                    carro.get(i).setStock(model.getStock());
                    carro.get(i).setPrecio(model.getPrecio());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Informacion");
                    alert.setHeaderText(null);
                    alert.setContentText("Producto añadido");

                    alert.showAndWait();
                }else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Informacion");
                    alert.setHeaderText(null);
                    alert.setContentText("No hay suficiente stock");

                    alert.showAndWait();
                }
            }
        }
        if(!existe){
            carro.add(model);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacion");
            alert.setHeaderText(null);
            alert.setContentText("Producto añadido");

            alert.showAndWait();
            }

        contador.setText(String.valueOf(carro.size()));


    }
}
