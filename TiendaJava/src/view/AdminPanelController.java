package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.ProductoModel;
import model.UsuarioModel;

import java.io.IOException;
import java.util.ArrayList;

public class AdminPanelController {
    @FXML
    ImageView imagen;

    ArrayList<ProductoModel> carrito;

    UsuarioModel usuario;

    @FXML
    public void Productos(){
        Stage stage = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CrearProducto.fxml"));
        Parent root1= null;
        try {
            root1 = (Parent) fxmlLoader.load();
            stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            CrearProductoController a = (CrearProductoController) fxmlLoader.getController();
            a.datos(carrito,usuario);
            a.opciones();
            Stage b = (Stage) imagen.getScene().getWindow();
            b.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void Usuarios(){
        Stage stage = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Usuarios.fxml"));
        Parent root1= null;
        try {
            root1 = (Parent) fxmlLoader.load();
            stage = new Stage();
            stage.setScene(new Scene(root1));
            UsuariosController usr =(UsuariosController)fxmlLoader.getController();
            usr.setUsuario(usuario);
            stage.show();


        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void Atras(){
        Stage stage = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Inicio.fxml"));
        Parent root1= null;
        try {
            root1 = (Parent) fxmlLoader.load();
            stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            InicioController inicio = (InicioController) fxmlLoader.getController();
            inicio.sesionIniciada(usuario);
            inicio.añadirCarro(carrito);

            Stage st =(Stage)imagen.getScene().getWindow();
            st.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void RecogerDatos(ArrayList<ProductoModel> carrito, UsuarioModel usuario){

        this.carrito= carrito;
        this.usuario= usuario;
    }
}
