package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;

/*
 * @author Miguel Angel Hernandez Rodriguez
 * @version 1.0
 * Rellena los datos
 * @param lista lista con los usuarios que se van a mostrar
 * @param tabla tabla que mostrara los datos
 * @param Numero numero de la factura
 * @param FechaHora fecha de la factura
 * @param Usuario que realizo la compra
 * @param Estado estado del pedido
 * @param comboBox comboBox de la interfaz para seleccionar filtros
 * @param buscar Textfield para buscar en la interfaz
 * @param Nombre Label con el nombre de usuario
 * @param Apellidos Label con el apellido del usuario
 * @param Direccion Label con la direccion del usuario;
 * @param DNI Label con el dni del usuario;
 * @param Telefono Label con telefono del usuario
 * @param Email Label con el email del usuario
 * @param consulta modelo con el usuario del que se consult
 * @param usuario modelo con el usuario conectado
 */

public class MostrarUsuarioController {

    ObservableList<FacturaModelFX> lista = FXCollections.observableArrayList();
    @FXML
    TableView<FacturaModelFX> tabla;
    @FXML
    TableColumn<FacturaModelFX, Number> Numero;
    @FXML
    TableColumn <FacturaModelFX, String> FechaHora;
    @FXML
    TableColumn <FacturaModelFX, String> Usuario;
    @FXML
    TableColumn <FacturaModelFX, String> Estado;
    @FXML
    ComboBox comboBox;
    @FXML
    TextField buscar;
    @FXML
    Label Nombre;
    @FXML
    Label Apellidos;
    @FXML
    Label Direccion;
    @FXML
    Label DNI;
    @FXML
    Label Telefono;
    @FXML
    Label Email;

    UsuarioModelFX consulta;

    UsuarioModel usuario;

    @FXML
    public void initialize(){
        Numero.setCellValueFactory(cellData -> cellData.getValue().IDFacturaProperty());
        FechaHora.setCellValueFactory(cellData -> cellData.getValue().fechaHoraProperty());
        Usuario.setCellValueFactory(cellData -> cellData.getValue().usuarioProperty());
        Estado.setCellValueFactory(cellData -> cellData.getValue().estadoProperty());
    }

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * Rellena los datos
     * @param fxmlLoader objeto que se utiliza para cargar diferentes archivos FXML
     * @param root1 nodo principal del archivo FXML
     * @param f controlador de la interfaz que se va a abrir para pasarle datos
     */

    @FXML
    public void Mostrar(){
        FacturaModelFX seleccion = tabla.getSelectionModel().getSelectedItem();
        Stage stage = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MostrarFactura.fxml"));
        Parent root1= null;
        try {
            root1 = (Parent) fxmlLoader.load();
            stage = new Stage();
            stage.setScene(new Scene(root1));
            MostrarFacturaController f =(MostrarFacturaController) fxmlLoader.getController();
            f.setUsuarioFactura(consulta, seleccion, usuario);

            stage.show();


        }catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void NoEditar() {
        tabla.setEditable(false);
        Numero.setEditable(false);
        FechaHora.setEditable(false);
        Usuario.setEditable(false);
        Estado.setEditable(false);
    }

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * Rellena los datos
     * @param dao objeto que realiza operaciones con la base de datos
     */

    public void rellenarLista(){
        DAO dao= new DAO();
        lista = dao.FacturaUsuario(lista,consulta);
        tabla.setItems(lista);
    }

    public void setUsuario(UsuarioModel usuario){

        this.usuario = usuario;
    }

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * Rellena los datos
     * @param usuario usuario que esta actualizando el programa
     * @param seleccion usuario seleccionado
     */

    public void setUsuario(UsuarioModel usuario, UsuarioModelFX seleccion) {
        this.usuario=usuario;
        this.consulta=seleccion;
        rellenarLista();
        Permisos();
        DatosUsuario();
    }

    private void DatosUsuario() {
        this.Nombre.setText(consulta.getNombre());
        this.Apellidos.setText(consulta.getApellidos());
        this.Direccion.setText(consulta.getDireccion());
        this.DNI.setText(consulta.getDireccion());
        this.Telefono.setText(String.valueOf(consulta.getTelefono()));
        this.Email.setText(consulta.getEmail());
    }

    private void Permisos() {
            NoEditar();
    }
}
