package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.ProductoModel;
import model.UsuarioModel;

import java.util.ArrayList;
import java.util.Observable;

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

    UsuarioModel usuario;

    ArrayList<ProductoModel> carro;

    ObservableList<ProductoModel> Productos;

    @FXML
    public void initialize(){
        Lista.setCellFactory((lv) -> {
            return FilaCarroController.newInstance();

        });
    }

    @FXML
    public void comprar(){}

    @FXML
    public void atras(){}

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
    }


}
