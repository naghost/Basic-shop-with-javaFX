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

/*
 * @author Miguel Angel Hernandez Rodriguez
 * @version 1.0
 * @param nombre Label que tiene el nombre de usuario iniciado
 * @param busqueda TextField que se utiliza para realizar busquedas de productos en la base de datos
 * @param usuario ImageView que muestra el icono del area de usuarios
 * @param carrito ImageView que muestra el icono del area de compras
 * @param salir ImagenView que muestra el icono de volver atras en la interfaz
 * @param buscar ImagenView que muestra el icono que hace de boton de busqueda
 * @param admin ImagenView que muestra el icono del area de administracion
 * @param listView Es una lista con los productos que se van a comprar
 * @param cajaUsuario se utiliza para mostrar las opciones de usuario
 * @param cajaBoton se utiliza para mostrar las opciones de iniciar sesion
 * @param IniciarSesion boton que se utiliza para iniciar sesion
 * @param contador label que lleva la cuenta de los articulos que hay en el carro
 * @param Usuario datos del usuario que tiene la sesion iniciada
 * @param productos lista observable controla los elementos mostrados en el listview
 * @param carro lista con los productos que se van a comprar
 */

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


    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * @param listView se le indica al lisview que tipo de controlador va a utilizar
     * @return FilaProductoController.newInstance
     */
    @FXML
    public void initialize(){
        listView.setCellFactory((lv) -> {
            return FilaProductoController.newInstance();
        });

    }

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * @param busqueda TextField que se utiliza para realizar busquedas de productos en la base de datos
     * @see anadir()
     */

    @FXML
    public void actualizarBusqueda(){
        anadir(busqueda.getText());
    }

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
    public void Salir(){
        Stage a = (Stage) busqueda.getScene().getWindow();
        a.close();
    }

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * @param stage Escenario en el que se mostrara la interfaz
     * @param fxmlLoader Carga las interfaces escritas en los archivos FXML
     * @param root1 Recoge el nodo primario de la interfaz escrita en el archivo FXML
     * @param inicio es una variable auxiliar que sirve para llamar al controlador de la interfaz y pasarle los datos de la busqueda
     */

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

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * @param busqueda String que se utiliza para realizar busquedas de productos en la base de datos
     * @param listView Es una lista con los productos que se van a comprar
     * @param productos lista observable controla los elementos mostrados en el listview
     * @param carro lista con los productos que se van a comprar
     * @param contador label que lleva la cuenta de los articulos que hay en el carro
     * @param Usuario datos del usuario que tiene la sesion iniciada
     */

    public void anadir(String busqueda){
        productos = null;
        productos = FXCollections.observableArrayList();

        DAO dao = new DAO();
        productos = dao.buscar(busqueda, productos, carro, contador, Usuario);
        listView.getItems().clear();
        listView.setItems(productos);
        listView.refresh();
        System.out.println(carro.size());
    }

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     *
     * @param Usuario datos del usuario que tiene la sesion iniciada
     * @param carro lista con los productos que se van a comprar
     * @see ocultar()
     * @see mostrar()
     */
    
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

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * @param cajaBoton se utiliza para mostrar las opciones de iniciar sesion
     * @param cajaUsuario se utiliza para mostrar las opciones de usuario
     * @param usuario ImageView que muestra el icono del area de usuarios
     * @param carrito ImageView que muestra el icono del area de compras
     * @param salir ImagenView que muestra el icono de volver atras en la interfaz
     * @param buscar ImagenView que muestra el icono que hace de boton de busqueda
     * @param admin ImagenView que muestra el icono del area de administracion
     * @param IniciarSesion boton que se utiliza para iniciar sesion
     */

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

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * @param cajaBoton se utiliza para mostrar las opciones de iniciar sesion
     * @param cajaUsuario se utiliza para mostrar las opciones de usuario
     * @param usuario ImageView que muestra el icono del area de usuarios
     * @param carrito ImageView que muestra el icono del area de compras
     * @param salir ImagenView que muestra el icono de volver atras en la interfaz
     * @param buscar ImagenView que muestra el icono que hace de boton de busqueda
     * @param admin ImagenView que muestra el icono del area de administracion
     * @param IniciarSesion boton que se utiliza para iniciar sesion
     */

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


    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * @param Usuario datos del usuario que tiene la sesion iniciada
     * @see mostrar()
     */

    public void sesionIniciada(UsuarioModel datos) {
        this.Usuario=datos;
        mostrar();
    }
}
