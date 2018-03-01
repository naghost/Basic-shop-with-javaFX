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

public class InicioSesionController {

    boolean Iniciado=false;
    @FXML
    TextField usuario;
    @FXML
    PasswordField passwordField;

    UsuarioModel datos ;

    InicioController acontroller;

    @FXML
    public void Iniciado(){
        datos = new UsuarioModel();
        System.out.println(this.usuario.getText());
        datos.setEmail(this.usuario.getText());
        datos.setPassword(this.passwordField.getText());

        DAO dao = new DAO();
        dao.comprobarInicio(datos);
        if (datos.getNombre() != null){
                acontroller.sesionIniciada(datos);
                Stage s  = (Stage) usuario.getScene().getWindow();
                s.close();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error inicio de sesion");
            alert.setHeaderText(null);
            alert.setContentText("El usuario no existe");

            alert.showAndWait();
        }
    }
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
}
