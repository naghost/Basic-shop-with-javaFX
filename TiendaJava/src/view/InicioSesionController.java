package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.DAO;
import model.UsuarioModel;

import java.io.IOException;


/*
 * @author Miguel Angel Hernandez Rodriguez
 * @version 1.0
 * Rellena los datos
 * @param Iniciado variable que se encarga de verificar si se ha iniciado sesion
 * @param usuario TextField que va a recoger los datos de usuario
 * @param passwordField Textfield que va a recoger los datos de usuario
 * @param datos modelo de datos que va a rellenar el usuario
 * @param acontroller controlador de la interfaz inicial
 * @para bcontroller controlador de la interfaz de busqueda
 *
 */

public class InicioSesionController {

    boolean Iniciado=false;
    @FXML
    TextField usuario;
    @FXML
    PasswordField passwordField;

    UsuarioModel datos ;

    InicioController acontroller;

    BusquedaController bcontroller;

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * Rellena los datos
     * @param dao objeto que se encarga de las consultas en la base de datos
     * @param s variable auxiliar para recoger la interfaz y cerrarla
     * @param alert objeto que se utiliza para mostrar mensajes emegentes
     */

    @FXML
    public void Iniciado(){
        datos = new UsuarioModel();
        datos.setEmail(this.usuario.getText());
        datos.setPassword(this.passwordField.getText());

        DAO dao = new DAO();
        dao.comprobarInicio(datos);
        if (datos.getNombre() != null){
            if (acontroller != null) {
                acontroller.sesionIniciada(datos);
                Stage s = (Stage) usuario.getScene().getWindow();
                s.close();
            }else{
                bcontroller.sesionIniciada(datos);
                Stage s = (Stage) usuario.getScene().getWindow();
                s.close();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error inicio de sesion");
            alert.setHeaderText(null);
            alert.setContentText("El usuario no existe");

            alert.showAndWait();
        }
    }

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * Rellena los datos
     * @param fxmlLoader objeto que se utiliza para cargar diferentes archivos FXML
     * @param root1 nodo principal del archivo FXML
     * @param controller controlador de la interfaz que se va a abrir para pasarle datos
     * @param Incio variable auxiliar para recoger el controlador de la ventana y pasarle el controlador de la actual ventana
     */

    @FXML
    public void Registrar(){

        Stage stage;
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RegistrarUsuario.fxml"));
            Parent root1= null;
            root1 = (Parent)fxmlLoader.load();
            stage= new Stage();
            stage.setScene(new Scene(root1,usuario.getScene().getWidth(),usuario.getScene().getHeight()));
            stage.setTitle("MiguelZon - Registrar usuario");
            RegistrarUsuarioController inicio = (RegistrarUsuarioController) fxmlLoader.getController();
            inicio.interfaz(this, acontroller);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void interfaz(InicioController inicioController) {
        this.acontroller=inicioController;
    }

    public void interfaz(BusquedaController busquedaController) {
        this.bcontroller=busquedaController;
    }
}
