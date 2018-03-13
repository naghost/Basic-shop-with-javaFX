package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.ProductoModel;
import model.UsuarioModel;

import java.io.IOException;
import java.util.ArrayList;

/*
 * @author Miguel Angel Hernandez Rodriguez
 * @version 1.0
 * Rellena los datos
 * @param texto Textfield que contiene el campo con el que vamos a hacer la busqueda
 * @param Nombre Label que muestra el usuario conectado
 * @param boton utilizado para llamar a interfaz de iniciar sesion
 * @param usuario Imagenview utilizado como icono que muestra el area de usuario
 * @param carrito Imagenview utilizado como icono que muestra el area de carrito
 * @param salir Imagenview utilizado para salir
 * @param admin Imagen view utilizado como icono que muestra el area de administracion
 * @param imagenes contenedor de las imagenes que se mostrara o no segun si esta el usuario coenctado o no
 * @param contador contador de los productos en el carrito
 * @param stage escenario que se utiliza para cargar interfaces
 * @param datos modelo del usuario iniciado
 * @param carro ArrayList con los productos
 *
 */

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
    @FXML
    Label contador;

    Stage stage;

    UsuarioModel datos;

    ArrayList<ProductoModel> carro = new ArrayList<>();


    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * Rellena los datos
     * @param fxmlLoader objeto que se utiliza para cargar diferentes archivos FXML
     * @param root1 nodo principal del archivo FXML
     * @param controller controlador de la interfaz que se va a abrir para pasarle datos
     * @param aqui variable auxiliar para cerrar
     * @param alert objeto que se utiliza para mostrar mensajes emegentes
     */
    @FXML
    public void buscar(){
        if(!texto.getText().equals("")) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Busqueda.fxml"));
                Parent root1 = null;

                root1 = (Parent) fxmlLoader.load();
                stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.setTitle("MiguelZon - Busqueda: " + texto.getText());
                BusquedaController controller = (BusquedaController) fxmlLoader.getController();
                controller.setData(datos, carro);
                controller.anadir(texto.getText());
                stage.show();
                Stage aqui = (Stage) salir.getScene().getWindow();
                aqui.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacion");
            alert.setHeaderText(null);
            alert.setContentText("Necesitas rellenar el campo");

            alert.showAndWait();
        }


    }

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * Rellena los datos
     * @param fxmlLoader objeto que se utiliza para cargar diferentes archivos FXML
     * @param root1 nodo principal del archivo FXML
     * @param inicio controlador de la interfaz que se va a abrir para pasarle datos
     */

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

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * Rellena los datos
     * @param fxmlLoader objeto que se utiliza para cargar diferentes archivos FXML
     * @param root1 nodo principal del archivo FXML
     * @param a controlador de la interfaz que se va a abrir para pasarle datos
     * @param b variable auxiliar para cerrar la interfaz
     */

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
    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * Rellena los datos
     * @param usuario Arralist con los elementos del carro
     */
    public void añadirCarro(ArrayList<ProductoModel> usuario){
        this.carro=usuario;
        this.contador.setText(String.valueOf(this.carro.size()));
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

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * Rellena los datos
     * @param fxmlLoader objeto que se utiliza para cargar diferentes archivos FXML
     * @param root1 nodo principal del archivo FXML
     * @param a controlador de la interfaz que se va a abrir para pasarle datos
     * @param b variable auxiliar para cerrar la interfaz
     */

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

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * Rellena los datos
     * @param a variable auxiliar en la que se recoge la interaz para cerrarla
     */

    @FXML
    public void Salir(){
        Stage a = (Stage) imagenes.getScene().getWindow();
        a.close();


    }
}
