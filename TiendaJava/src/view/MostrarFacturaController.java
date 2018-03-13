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

/*
 * @author Miguel Angel Hernandez Rodriguez
 * @version 1.0
 * @param Nombre Textfield con el contenido del nombre del usuario que se modificara
 * @param Apellidos Textfield con el contenido de los apellidos del usuario que se modificara
 * @param DNI Textfield con el contenido del DNI del usuario que se modificara
 * @param Telefono Textfield con el contenido del telefono del usuario que se modificara
 * @param Direccion Textfield con el contenido de la del usuario que se modificara
 * @param Email Textfield con el contenido del email del usuario que se modificara
 * @param Numero numero de factura
 * @param FechaHora Fecha y hora de la factura
 * @param Estado Estado de la factura
 * @param tabla es la tabla que se muestra
 * @param NombreProducto columna con el nombre del producto
 * @param Cantidad columna que muestra la cantidad de ese producto que he comprado
 * @param Precio columna que muestra el precio al que se ha comprado el producto
 * @param Usuario modelo del usuario que se esta mostrando
 * @param Factura  modelo de la factura que se esta mostrando
 * @param UsuarioActual modelo del usuario que se esta mostrando ahora mismo
 */


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

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * este metodo asigna a cada columna su dato
     */

    @FXML
    public void initialize(){
    NombreProducto.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
    Cantidad.setCellValueFactory(cellData -> cellData.getValue().cantidadProperty());
    Precio.setCellValueFactory(cellData -> cellData.getValue().precioProperty());
    }


    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * @param event elemento seleccionado de la tabla
     * @param oValue valor anterior de la tabla
     * @param nValue valor anterior de la tabla
     * @param error objeto que utilizo para mostrar datos en FXML
     * @param dao objeto encargado de realizar las consultas
     * @param producto modelo del producto seleccionado
     */
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
