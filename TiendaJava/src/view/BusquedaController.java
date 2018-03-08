package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.DAO;
import model.ProductoModel;
import model.UsuarioModel;

import java.util.ArrayList;


public class BusquedaController {
    @FXML
    Label nombre;
    @FXML
    TextField busqueda;
    @FXML
    ImageView usuario;
    @FXML
    ImageView carrito;
    @FXML
    ImageView salir;
    @FXML
    ImageView buscar;
    @FXML
    ListView<ProductoModel> listView;
    @FXML
    HBox cajaUsuario;
    @FXML
    HBox cajaBoton;
    @FXML
    Button IniciarSesion;

    ObservableList<ProductoModel> productos;

    UsuarioModel Usuario;
    
    ArrayList<ProductoModel> carro = new ArrayList<>();

    @FXML
    public void initialize(){
        listView.setCellFactory((lv) -> {
            return FilaProductoController.newInstance();
        });
    }
    @FXML
    public void actualizarBusqueda(){
        anadir(busqueda.getText());
    }

    @FXML
    public void mostrarCarrito(){}

    @FXML
    public void mostrarUsuario(){}

    @FXML
    public void salir(){}

    public void anadir(String busqueda){
        productos = null;
        productos = FXCollections.observableArrayList();

        DAO dao = new DAO();
        productos = dao.buscar(busqueda, productos);
        listView.getItems().clear();
        listView.setItems(productos);
        listView.refresh();
        System.out.println(productos.size());
    }
    
    public void setData(UsuarioModel usuario,ArrayList<ProductoModel> carro){
        this.carro=carro;
        if (usuario==null){
            ocultar();
        }else{
            this.Usuario=usuario;
            mostrar();
        }
    }

    private void ocultar() {
        cajaBoton.setMaxHeight(Double.MAX_VALUE);
        cajaBoton.setMaxWidth(Double.MAX_VALUE);
        cajaBoton.setPrefWidth(300);
        cajaBoton.setPrefHeight(100);

        cajaUsuario.setPrefHeight(0);
        cajaUsuario.setPrefWidth(0);
        cajaUsuario.setMaxWidth(0);
        cajaUsuario.setMaxHeight(0);
        cajaUsuario.setMinWidth(0);
        cajaUsuario.setMinHeight(0);

        usuario.setVisible(false);
        salir.setVisible(false);
        carrito.setVisible(false);
        nombre.setVisible(false);
        IniciarSesion.setVisible(true);
    }
    private void mostrar(){
        cajaBoton.setMaxHeight(0);
        cajaBoton.setMaxWidth(0);
        cajaBoton.setPrefWidth(0);
        cajaBoton.setPrefHeight(0);

        cajaUsuario.setPrefHeight(Control.USE_COMPUTED_SIZE);
        cajaUsuario.setPrefWidth(Control.USE_COMPUTED_SIZE);
        cajaUsuario.setMaxWidth(Control.USE_COMPUTED_SIZE);
        cajaUsuario.setMaxHeight(Control.USE_COMPUTED_SIZE);
        cajaUsuario.setMinWidth(Control.USE_COMPUTED_SIZE);
        cajaUsuario.setMinHeight(Control.USE_COMPUTED_SIZE);

        usuario.setVisible(true);
        salir.setVisible(true);
        carrito.setVisible(true);
        nombre.setVisible(true);
        IniciarSesion.setVisible(false);

        nombre.setText(Usuario.getNombre());
    }

}
