package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import javax.swing.text.html.ImageView;

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
    ListView<FilaProductoController> listview;
    @FXML
    Label titulo;

    @FXML
    public void mostrarCarrito(){}

    @FXML
    public void mostrarUsuario(){}

    @FXML
    public void salir(){}

    @FXML
    public void actualizarBusqueda(){}

}
