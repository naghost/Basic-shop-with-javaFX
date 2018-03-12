package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.DAO;
import model.ProductoModel;
import model.UsuarioModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
/*
 * @author Miguel Angel Hernandez Rodriguez
 * @version 1.0
 * @param Nombre Label que muestra nombre de usuario
 * @param Apellido Label que muestra el apellido del usuario
 * @param DNI Label que muestra el DNI del usuario
 * @param Telefono Label que muestra el telefono del usuario
 * @param Direccion Label que muestra la direccion del usuario
 * @param Email Label que muestra el email del usuario
 * @param Lista ListView con los productos del carrito
 * @param Precio Label que muestra la suma total de la compra
 * @param usuario Modelo del usuario iniciado
 * @param carro Lista con los productos seleccionados por el usuario
 * @param Productos Lista para asignarse al listview
 */

public class CarroController {
    @FXML
    Label Nombre;
    @FXML
    Label Apellidos;
    @FXML
    Label DNI;
    @FXML
    Label Telefono;
    @FXML
    Label Direccion;
    @FXML
    Label Email;
    @FXML
    ListView<ProductoModel> Lista;
    @FXML
    Label Precio;

    UsuarioModel usuario;

    ArrayList<ProductoModel> carro;

    ObservableList<ProductoModel> Productos;

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * @param Lista se le indica al LisView que tipo de controlador va a utilizar
     * @return FilaCarroController.newInstance()
     */

    @FXML
    public void initialize(){
        Lista.setCellFactory((lv) -> {
            return FilaCarroController.newInstance();

        });
    }

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * @param dao objeto encargado de todas las acciones con la base de datos
     * @param stage Escenario en el que se mostrara la interfaz
     * @param fxmlLoader Carga las interfaces escritas en los archivos FXML
     * @param root1 Recoge el nodo primario de la interfaz escrita en el archivo FXML
     * @param inicio es una variable auxiliar que sirve para llamar al controlador de la interfaz y pasarle los datos de usuario
     * @param st variable auxliar para cerrar la interfaz
     * @param alert objeto que se usa para sacar ventanas emegentes
     */

    @FXML
    public void comprar(){
        DAO dao = new DAO();
        dao.comprarProductos(Productos, usuario);
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
            Stage st =(Stage)Email.getScene().getWindow();
            st.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("¡Compra realizada!");
        alert.setHeaderText(null);
        alert.setContentText("Compra realizada puedes consultar la factura en el area de cliente");
        alert.showAndWait();
    }

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * @param stage Escenario en el que se mostrara la interfaz
     * @param fxmlLoader Carga las interfaces escritas en los archivos FXML
     * @param root1 Recoge el nodo primario de la interfaz escrita en el archivo FXML
     * @param inicio es una variable auxiliar que sirve para llamar al controlador de la interfaz y pasarle los datos de usuario y carrito
     * @param st variable auxliar para cerrar la interfaz
     */

    @FXML
    public void atras(){
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
            inicio.añadirCarro(carro);

            Stage st =(Stage)Email.getScene().getWindow();
            st.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * @param Nombre Label que muestra nombre de usuario
     * @param Apellido Label que muestra el apellido del usuario
     * @param DNI Label que muestra el DNI del usuario
     * @param Telefono Label que muestra el telefono del usuario
     * @param Direccion Label que muestra la direccion del usuario
     * @param Email Label que muestra el email del usuario
     * @param Lista ListView con los productos del carrito
     * @param Precio Label que muestra la suma total de la compra
     * @param usuario Modelo del usuario iniciado
     * @param carro Lista con los productos seleccionados por el usuario
     * @param Productos Lista para asignarse al listview
     * @see Sumatotal()
     */

    public void llenar(ArrayList<ProductoModel> carro, UsuarioModel usuario){
        this.carro= carro;
        this.usuario = usuario;
        this.Nombre.setText(this.usuario.getNombre());
        this.Apellidos.setText(this.usuario.getApellidos());
        this.DNI.setText(this.usuario.getDNI());
        this.Telefono.setText(String.valueOf(this.usuario.getTelefono()));
        this.Direccion.setText(this.usuario.getDireccion());
        this.Email.setText(this.usuario.getEmail());
        Productos = FXCollections.observableArrayList(this.carro);
        Lista.setItems(Productos);
        SumaTotal();

    }

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * @param carro Lista con los productos seleccionados por el usuario
     * @param Contador variable para mostrar todos los precios
     * @param Precio Label que muestra la suma total de la compra
     */

    public void SumaTotal(){
        Double Contador = 0.0;
        for(int i=0;i<carro.size();i++){
          Contador= Contador+carro.get(i).getPrecio()*carro.get(i).getCantidad();
        }
        Precio.setText("Precio: "+String.valueOf(Contador)+" €");
    }

}
