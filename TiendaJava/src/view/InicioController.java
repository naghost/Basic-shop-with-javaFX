package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.UsuarioModel;

import java.io.IOException;

import static com.sun.glass.ui.Cursor.setVisible;


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
    Stage stage;
    UsuarioModel datos;

    @FXML
    public void buscar(){
        System.out.println(texto.getText());

    }

    @FXML
    public void iniciar(){
        String resultado="";
        InicioSesionController controller = null;
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

    public void sesionIniciada(UsuarioModel datos) {


        this.datos = datos;
        boton.setVisible(false);
        Nombre.setText("Bienvenido "+datos.getNombre());
        usuario.setVisible(true);


        carrito.setVisible(true);
        salir.setVisible(true);
    }
}
