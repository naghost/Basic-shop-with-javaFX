package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.NumberStringConverter;
import model.DAO;
import model.UsuarioModel;
import model.UsuarioModelFX;

import java.util.ArrayList;


public class UsuariosController {

    ObservableList<UsuarioModelFX> lista = FXCollections.observableArrayList();
    @FXML
    TableView <UsuarioModelFX> tabla;
    @FXML
    TableColumn <UsuarioModelFX, String> Nombre;
    @FXML
    TableColumn <UsuarioModelFX, String> Apellido;
    @FXML
    TableColumn <UsuarioModelFX, String> DNI;
    @FXML
    TableColumn <UsuarioModelFX, Number> Telefono;
    @FXML
    TableColumn <UsuarioModelFX, String> Direccion;
    @FXML
    TableColumn <UsuarioModelFX, String> Email;
    @FXML
    TableColumn <UsuarioModelFX, String> Password;
    @FXML
    TableColumn <UsuarioModelFX, Number> Admin;
    @FXML
    ComboBox combobox;
    @FXML
    TextField Buscar;

    UsuarioModel usuario;

    @FXML
    private void initialize(){
        rellenarLista();
        tabla.setItems(lista);
        ArrayList<String> ls = new ArrayList<>();
        ObservableList<String> options = FXCollections.observableArrayList("Nombre", "Apellidos", "DNI", "Telefono", "Direccion", "Email", "Admin");
        combobox.setItems(options);

        Nombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        Apellido.setCellValueFactory(cellData -> cellData.getValue().apellidosProperty());
        DNI.setCellValueFactory(cellData -> cellData.getValue().DNIProperty());
        Telefono.setCellValueFactory(cellData -> cellData.getValue().telefonoProperty());
        Direccion.setCellValueFactory(cellData -> cellData.getValue().direccionProperty());
        Email.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        Password.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
        Admin.setCellValueFactory(cellData -> cellData.getValue().adminProperty());

        Nombre.setCellFactory(TextFieldTableCell.forTableColumn());
        Apellido.setCellFactory(TextFieldTableCell.forTableColumn());
        DNI.setCellFactory(TextFieldTableCell.forTableColumn());
        Telefono.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        Direccion.setCellFactory(TextFieldTableCell.forTableColumn());
        Email.setCellFactory(TextFieldTableCell.forTableColumn());
        Password.setCellFactory(TextFieldTableCell.forTableColumn());
        Admin.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));




    }

    public void rellenarLista(){
    DAO  dao= new DAO();
    lista = dao.mostrarTodosUsuarios(lista);
    }

    @FXML
    public void editarClienteString(TableColumn.CellEditEvent<UsuarioModelFX, String> event){
       UsuarioModelFX usuario = tabla.getSelectionModel().getSelectedItem();

        switch (event.getTableColumn().getText()){
            case "Nombre":
                usuario.setNombre(event.getNewValue());
                break;
            case "Apellidos":
                usuario.setApellidos(event.getNewValue());
                break;
            case "DNI":
                usuario.setDNI(event.getNewValue());
                break;
            case "Direccion":
                usuario.setDireccion(event.getNewValue());
                break;
            case "Email":
                usuario.setEmail(event.getNewValue());
                break;
            case "Password":
                usuario.setPassword(event.getNewValue());
                break;
        }

        DAO dao = new DAO();
        dao.editarUsuario(usuario);
    }
    @FXML
    public void editarClienteInt(TableColumn.CellEditEvent<UsuarioModelFX, Number> event){
        UsuarioModelFX usuario = tabla.getSelectionModel().getSelectedItem();

        switch (event.getTableColumn().getText()){
            case "Telefono":

                usuario.setTelefono(event.getNewValue().intValue());
                break;
            case "Admin":
                usuario.setAdmin(Integer.parseInt(String.valueOf(event.getNewValue())));
                break;
        }

        DAO dao = new DAO();
        dao.editarUsuario(usuario);
    }

    @FXML
    public void Buscar(){
        ObservableList<UsuarioModelFX> options = FXCollections.observableArrayList();
        String cb = (String) combobox.getValue();
        DAO dao = new DAO();
        options=dao.buscarUsuarios(options,Buscar.getText(), cb);

        tabla.getItems().clear();
        tabla.setItems(options);
    }

    @FXML
    public void mostrarUsuario(){

    }

    public void setUsuario(UsuarioModel usuario){
        this.usuario = usuario;
    }
}
