package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.*;

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
    UsuarioModelFX consulta;
    UsuarioModel usuario;


    @FXML
    public void initialize(){
        rellenarLista();
        if (usuario.getAdmin()==1){
            Edicion();
        }else{
            NoEditar();
        }

        Numero.setCellValueFactory(cellData -> cellData.getValue().IDFacturaProperty());
        FechaHora.setCellValueFactory(cellData -> cellData.getValue().fechaHoraProperty());
        Usuario.setCellValueFactory(cellData -> cellData.getValue().usuarioProperty());
        Estado.setCellValueFactory(cellData -> cellData.getValue().estadoProperty());

    }

    private void Edicion() {
    }

    private void NoEditar() {
        tabla.setEditable(false);
        Numero.setEditable(false);
        FechaHora.setEditable(false);
        Usuario.setEditable(false);
        Estado.setEditable(false);
    }

    public void rellenarLista(){
        DAO dao= new DAO();
        lista = dao.FacturaUsuario(lista,consulta);
        tabla.setItems(lista);
    }

    @FXML
    public void Mostrar(){}

    @FXML
    public void Buscar(){}

    public void setUsuario(UsuarioModel usuario){
        this.usuario = usuario;
    }

}
