package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.DAO;
import model.ProductoModel;
import model.UsuarioModel;

import java.io.IOException;
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
    ImageView admin;
    @FXML
    ListView<ProductoModel> listView;
    @FXML
    HBox cajaUsuario;
    @FXML
    HBox cajaBoton;
    @FXML
    Button IniciarSesion;
    @FXML
    Label contador;

    ObservableList<ProductoModel> productos;

    UsuarioModel Usuario;
    
    ArrayList<ProductoModel> carro;

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
    public void mostrarCarrito(){
        Stage stage = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Carro.fxml"));
            Parent root1= null;
            root1 = (Parent)fxmlLoader.load();
            stage= new Stage();
            stage.setScene(new Scene(root1));
            CarroController a = (CarroController) fxmlLoader.getController();
            a.llenar(carro,Usuario);
            stage.show();
            Stage b = (Stage) admin.getScene().getWindow();
            b.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void mostrarAdmin(){
        Stage stage = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminPanel.fxml"));
            Parent root1= null;
            root1 = (Parent)fxmlLoader.load();
            stage= new Stage();
            stage.setScene(new Scene(root1));
            AdminPanelController a = (AdminPanelController) fxmlLoader.getController();
            a.RecogerDatos(carro,Usuario);
            stage.show();
            Stage b = (Stage) admin.getScene().getWindow();
            b.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void mostrarUsuario(){
        Stage stage =null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UsuarioPane.fxml"));
            Parent root1= null;
            root1 = (Parent)fxmlLoader.load();
            stage= new Stage();
            stage.setScene(new Scene(root1));
            UsuarioPaneController a = (UsuarioPaneController) fxmlLoader.getController();
            a.setDatos(carro,Usuario);
            stage.show();
            Stage b = (Stage) buscar.getScene().getWindow();
            b.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void salir(){}

    @FXML
    public void iniciarSesion(){

            String resultado="";
            Stage stage= null;
            try {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InicioSesion.fxml"));
                Parent root1= null;
                root1 = (Parent)fxmlLoader.load();
                stage= new Stage();
                stage.setScene(new Scene(root1));
                stage.setTitle("MiguelZon - Iniciar sesion");
                InicioSesionController inicio = (InicioSesionController) fxmlLoader.getController();
                inicio.interfaz(this);

                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }


    }

    public void anadir(String busqueda){
        productos = null;
        productos = FXCollections.observableArrayList();

        DAO dao = new DAO();
        productos = dao.buscar(busqueda, productos, carro, contador);
        listView.getItems().clear();
        listView.setItems(productos);
        listView.refresh();
        System.out.println(carro.size());
    }
    
    public void setData(UsuarioModel usuario,ArrayList<ProductoModel> carro){
        this.carro=carro;
        contador.setText(String.valueOf(carro.size()));
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
        cajaBoton.setPrefHeight(50);

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
        admin.setVisible(false);
        contador.setVisible(false);
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

        contador.setVisible(true);
        usuario.setVisible(true);
        salir.setVisible(true);
        carrito.setVisible(true);
        nombre.setVisible(true);
        IniciarSesion.setVisible(false);
        if (Usuario.getAdmin()==1){
            admin.setVisible(true);
        }else {
            admin.setVisible(false);
        }

        nombre.setText(Usuario.getNombre());
    }

    public void sesionIniciada(UsuarioModel datos) {
        this.Usuario=datos;
        mostrar();
    }
}
