package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.NumberStringConverter;
import model.*;

public class MostrarFacturaController {

    ObservableList<ProductoCompradoModel> lista = FXCollections.observableArrayList();
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
    @FXML
    Label Numero;
    @FXML
    Label FechaHora;
    @FXML
    Label Estado;
    @FXML
    TableView<ProductoCompradoModel> tabla;
    @FXML
    TableColumn<ProductoCompradoModel, String> NombreProducto;
    @FXML
    TableColumn<ProductoCompradoModel, Number> Cantidad;
    @FXML
    TableColumn<ProductoCompradoModel, Number> Precio;

    UsuarioModelFX Usuario;

    FacturaModelFX Factura;

    UsuarioModel UsuarioActual;

    @FXML
    public void initialize(){
    NombreProducto.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
    Cantidad.setCellValueFactory(cellData -> cellData.getValue().cantidadProperty());
    Precio.setCellValueFactory(cellData -> cellData.getValue().precioProperty());
    }

    @FXML
    public void devolucion(TableColumn.CellEditEvent<ProductoCompradoModel, Number> event){
        int oValue = event.getOldValue().intValue();
        int nValue = event.getNewValue().intValue();
        ProductoCompradoModel producto = tabla.getSelectionModel().getSelectedItem();
        if(oValue<nValue){
            tabla.getSelectionModel().getSelectedItem().setCantidad(oValue);
            System.out.println(tabla.getSelectionModel().getSelectedItem().getCantidad());
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("El valor nuevo no puede ser mayor que el nuevo");
            error.show();

        }else {
            DAO dao = new DAO();
            producto.setCantidad(nValue);
            dao.devolucion(producto, Factura);
        }

    }

    public void setUsuarioFactura(UsuarioModelFX usuario, FacturaModelFX factura, UsuarioModel usuarioModel){
        this.Usuario=usuario;
        this.Factura = factura;
        this.UsuarioActual =usuarioModel;
        setTableValues();
        setDateValues();
        setEditable();
    }

    private void setEditable() {
        if (UsuarioActual.getAdmin() == 1){
            Cantidad.setCellFactory(TextFieldTableCell.forTableColumn((new NumberStringConverter())));
        }
    }

    private void setDateValues() {
        Nombre.setText(Usuario.getNombre());
        Apellidos.setText(Usuario.getApellidos());
        Direccion.setText(Usuario.getDireccion());
        DNI.setText(Usuario.getDNI());
        Telefono.setText(String.valueOf(Usuario.getTelefono()));
        Email.setText(Usuario.getEmail());
        Numero.setText(String.valueOf(Factura.getIDFactura()));
        FechaHora.setText(String.valueOf(Factura.getFechaHora()));
        Estado.setText(Factura.getEstado());
    }

    private void setTableValues() {
        DAO dao = new DAO();
        lista = dao.buscarProductoFactura(Factura, lista);
        tabla.setItems(lista);
    }
}
