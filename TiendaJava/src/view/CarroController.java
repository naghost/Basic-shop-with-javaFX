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


    @FXML
    public void initialize(){
        Lista.setCellFactory((lv) -> {
            return FilaCarroController.newInstance();

        });
    }

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

    public void SumaTotal(){
        Double Contador = 0.0;
        for(int i=0;i<carro.size();i++){
          Contador= Contador+carro.get(i).getPrecio()*carro.get(i).getCantidad();
        }
        Precio.setText("Precio: "+String.valueOf(Contador)+" €");
    }

}
