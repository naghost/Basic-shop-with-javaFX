package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import model.DAO;
import model.ProductoModel;



public class BusquedaController {
    @FXML
    Label nombre;
    @FXML
    ImageView usuario;
    @FXML
    ImageView carrito;
    @FXML
    ImageView salir;
    @FXML
    ImageView buscar;
    @FXML
    ListView<ProductoModel> listView = new ListView<>();

    ObservableList<ProductoModel> productos = FXCollections.observableArrayList();

    @FXML
    public void mostrarCarrito(){}

    @FXML
    public void mostrarUsuario(){}

    @FXML
    public void salir(){}

    public void anadir(String busqueda){
        listView.setCellFactory((lv) -> {
        return FilaProductoController.newInstance();
    });
        DAO dao = new DAO();
        productos = dao.buscar(busqueda, productos);
        listView.setItems(productos);
    }

}
