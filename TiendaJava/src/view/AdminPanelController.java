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

/*
 * @author Miguel Angel Hernandez Rodriguez
 * @version 1.0
 * @param Imagen se utiliza para poder cerrar la interfaz
 * @param carrito se utiliza para guardar el carro del usuario
 * @param usuario es el usuario registrado
 */

public class AdminPanelController {
    @FXML
    ImageView imagen;

    ArrayList<ProductoModel> carrito;

    UsuarioModel usuario;

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * @param stage Escenario en el que se mostrara la interfaz
     * @param fxmlLoader Carga las interfaces escritas en los archivos FXML
     * @param root1 Recoge el nodo primario de la interfaz escrita en el archivo FXML
     * @param a es una variable auxiliar que sirve para llamar al controlador de la interfaz y pasarle los datos de usuario y el carrito
     * @param b es una variable auxiliar que se utiliza para cerrar la interfaz
     */

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

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * @param stage Escenario en el que se mostrara la interfaz
     * @param fxmlLoader Carga las interfaces escritas en los archivos FXML
     * @param root1 Recoge el nodo primario de la interfaz escrita en el archivo FXML
     * @param usr es una variable auxiliar que sirve para llamar al controlador de la interfaz y pasarle los datos de usuario
     */

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

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * @param stage Escenario en el que se mostrara la interfaz
     * @param fxmlLoader Carga las interfaces escritas en los archivos FXML
     * @param root1 Recoge el nodo primario de la interfaz escrita en el archivo FXML
     * @param inicio es una variable auxiliar que sirve para llamar al controlador de la interfaz y pasarle los datos de usuario y el carrito
     * @param st es una variable auxiliar que se utiliza para cerrar la interfaz
     */

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

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * @param carrito ArrayList que contiene los productos en una lista
     * @param usuario Modelo del usuario que esta iniciado
     */

    public void RecogerDatos(ArrayList<ProductoModel> carrito, UsuarioModel usuario){

        this.carrito= carrito;
        this.usuario= usuario;
    }
}
