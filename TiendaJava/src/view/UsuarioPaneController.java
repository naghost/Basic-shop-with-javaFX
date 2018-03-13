package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.ProductoModel;
import model.UsuarioModel;
import model.UsuarioModelFX;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 */

public class UsuarioPaneController {

    @FXML
    ImageView imagen;

    ArrayList<ProductoModel> carrito;

    UsuarioModel usuario;

    @FXML
    public void editarPerfil(){
        UsuarioModelFX u = new UsuarioModelFX(usuario);

        Stage stage = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditarUsuario.fxml"));
        Parent root1= null;
        try {
            root1 = (Parent) fxmlLoader.load();
            stage = new Stage();
            stage.setScene(new Scene(root1));
            EditarUsuarioController usr =(EditarUsuarioController) fxmlLoader.getController();
            usr.datos(usuario);

            stage.show();


        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void consultarCompras(){
        UsuarioModelFX u = new UsuarioModelFX(usuario);

        Stage stage = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MostrarUsuario.fxml"));
        Parent root1= null;
        try {
            root1 = (Parent) fxmlLoader.load();
            stage = new Stage();
            stage.setScene(new Scene(root1));
            MostrarUsuarioController usr =(MostrarUsuarioController) fxmlLoader.getController();
            usr.setUsuario(usuario, u);

            stage.show();


        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void salir(){
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

    public void setDatos(ArrayList<ProductoModel> carrito, UsuarioModel usuario){
        this.carrito=carrito;
        this.usuario=usuario;
    }
}
