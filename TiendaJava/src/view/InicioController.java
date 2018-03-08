package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.ProductoModel;
import model.UsuarioModel;

import java.io.IOException;
import java.util.ArrayList;


public class InicioController {
    @FXML
    TextField texto;
    @FXML
    Label Nombre;
    @FXML
    Button boton;
    @FXML
    ImageView usuario;
    @FXML
    ImageView carrito;
    @FXML
    ImageView salir;
    @FXML
    ImageView admin;
    @FXML
    HBox imagenes;

    Stage stage;

    UsuarioModel datos;

    ArrayList<ProductoModel> carro = new ArrayList<>();

    @FXML
    public void buscar(){
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Busqueda.fxml"));
        Parent root1= null;

            root1 = (Parent)fxmlLoader.load();
            stage= new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("MiguelZon - Busqueda: "+texto.getText());
            BusquedaController controller = (BusquedaController) fxmlLoader.getController();
            controller.anadir(texto.getText());
            controller.setData(datos,carro);
            stage.show();
            Stage aqui = (Stage) salir.getScene().getWindow();
            aqui.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    public void iniciar(){
        String resultado="";
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

    @FXML
    public void Administrador(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminPanel.fxml"));
            Parent root1= null;
            root1 = (Parent)fxmlLoader.load();
            stage= new Stage();
            stage.setScene(new Scene(root1));
            AdminPanelController a = (AdminPanelController) fxmlLoader.getController();
            a.RecogerDatos(carro,datos);
            stage.show();
            Stage b = (Stage) imagenes.getScene().getWindow();
            b.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void añadirCarro(ArrayList<ProductoModel> usuario){
        this.carro=usuario;
    }

    public void sesionIniciada(UsuarioModel datos) {


        this.datos = datos;
        Nombre.setText("Bienvenido "+datos.getNombre());
        boton.setVisible(false);
        boton.setMaxWidth(0);
        boton.setMaxHeight(0);

        usuario.setVisible(true);
        imagenes.setPrefWidth(Control.USE_COMPUTED_SIZE);
        imagenes.setPrefHeight(Control.USE_COMPUTED_SIZE);
        imagenes.setMinHeight(Control.USE_COMPUTED_SIZE);
        imagenes.setMinWidth(Control.USE_COMPUTED_SIZE);
        imagenes.setMaxWidth(Double.MAX_VALUE);
        imagenes.setMaxHeight(Double.MAX_VALUE);
        carrito.setVisible(true);
        salir.setVisible(true);
        if (this.datos.getAdmin()==1){
            admin.setVisible(true);
        }
    }

    @FXML
    public void panelUsuario(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UsuarioPane.fxml"));
            Parent root1= null;
            root1 = (Parent)fxmlLoader.load();
            stage= new Stage();
            stage.setScene(new Scene(root1));
            UsuarioPaneController a = (UsuarioPaneController) fxmlLoader.getController();
            a.setDatos(carro,datos);
            stage.show();
            Stage b = (Stage) imagenes.getScene().getWindow();
            b.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void Salir(){
        Stage a = (Stage) imagenes.getScene().getWindow();
        a.close();


    }
}
