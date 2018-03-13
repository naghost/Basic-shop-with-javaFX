package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.ProductoModel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/*
 * @author Miguel Angel Hernandez Rodriguez
 * @version 1.0
 * Rellena los datos
 * @param empty booleano encargado de mostrar si el objeto esta vacio
 * @param imagen Archivo de imagen
 * @param titulo Campo Titulo tabla Producto
 * @param autor Campo Autor tabla Producto
 * @param año Campo Año tabla Producto
 * @param precio Campo Precio tabla Producto
 * @param boton Button que utiliza para añadir producto
 * @param root se utiliza para obtener el nodo principal del archivo FXML
 * @param model es el modelo en el que se basa la vista
 * @param carro es el carrito del usuario
 * @param contador es la variable que cambia si añades mas productos
 * @param LOG variable utilizada para mostrar excepciones
 *
 *
 */
public class FilaProductoController extends ListCell<ProductoModel> implements Initializable {
    @FXML
    ImageView imagen;
    @FXML
    Label titulo;
    @FXML
    Label autor;
    @FXML
    Label precio;
    @FXML
    Label año;
    @FXML
    Button boton;
    @FXML
    AnchorPane root;

    ProductoModel model;

    ArrayList<ProductoModel> carro;

    Label contador;


    private static final Logger LOG = Logger.getLogger(FilaProductoController.class.getName());


    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * @param loader se encarga de ejecutar una instancia de cada archivo FXML
     */
    public static FilaProductoController newInstance(){
        FXMLLoader loader = new FXMLLoader(FilaProductoController.class.getResource("FilaProducto.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loader.getController();

    }

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * @param stage Escenario en el que se mostrara la interfaz
     * @param fxmlLoader Carga las interfaces escritas en los archivos FXML
     * @param root1 Recoge el nodo primario de la interfaz escrita en el archivo FXML
     * @param inicio es una variable auxiliar que sirve para llamar al controlador de la interfaz y pasarle los datos de usuario y el carrito
     * @param a es una variable auxiliar que se utiliza para enviar los parametros del modelo que se mostrara en la itneraz y del carro del usuario
     *
     */
    @FXML
    public void mostrarProducto(){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MostrarProducto.fxml"));
            Parent root1= null;
            root1 = (Parent)fxmlLoader.load();
            Stage stage= new Stage();
            stage.setScene(new Scene(root1));
            MostrarProductoController a = (MostrarProductoController) fxmlLoader.getController();
            a.rellenarDatos(model,carro);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     *  asigna la interfaz al controlador
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    setGraphic(root);
    }


    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * Rellena los datos
     * @param empty booleano encargado de mostrar si el objeto esta vacio
     * @param imagen Archivo de imagen
     * @param titulo Campo Titulo tabla Producto
     * @param autor Campo Autor tabla Producto
     * @param año Campo Año tabla Producto
     * @param precio Campo Precio tabla Producto
     * @param boton Button que utiliza para añadir producto
     * @param root se utiliza para obtener el nodo principal del archivo FXML
     * @param model es el modelo en el que se basa la vista
     * @param carro es el carrito del usuario
     * @param contador es la variable que cambia si añades mas productos
     *
     *
     */
    protected void updateItem(ProductoModel item, boolean empty) {
        super.updateItem(item, empty); // <-- Important
        // make empty cell items invisible
        root.getChildrenUnmodifiable().forEach(c -> c.setVisible(!empty));
        // update valid cells with model data
        if (!empty && item != null && !item.equals(this.model)) {
            imagen.setImage(item.getImagen());
            titulo.textProperty().set(item.getTitulo());
            año.textProperty().set("Año: "+String.valueOf(item.getAño()));
            autor.textProperty().set("de "+item.getAutor());
            precio.textProperty().set("EUR "+String.valueOf(item.getPrecio()));
            carro=item.getCarrito();
            if (item.getStock()==0){
                boton.setDisable(true);
                boton.setText("Producto no disponible");
            }else {
                boton.setDisable(false);
                boton.setText("Comprar");
            }
            contador=item.getContador();

        }
        // keep a reference to the model item in the ListCell
        this.model = item;
    }

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * Rellena los datos
     * @param empty booleano encargado de mostrar si el objeto esta vacio
     * @param imagen Archivo de imagen
     * @param titulo Campo Titulo tabla Producto
     * @param autor Campo Autor tabla Producto
     * @param año Campo Año tabla Producto
     * @param precio Campo Precio tabla Producto
     * @param boton Button que utiliza para añadir producto
     * @param root se utiliza para obtener el nodo principal del archivo FXML
     * @param model es el modelo en el que se basa la vista
     * @param carro es el carrito del usuario
     * @param contador es la variable que cambia si añades mas productos
     * @param alert objeto que se encarga de mostrar mensajes emergentes
     *
     */
    @FXML
    public void añadirCarro(){
        boolean existe = false;
        for(int i=0;i<carro.size();i++){
            if(carro.get(i).getId()== model.getId()){
                existe=true;
                if ((model.getCantidad()+1)<=model.getStock()) {
                    model.setCantidad(model.getCantidad() + 1);
                    carro.get(i).setStock(model.getStock());
                    carro.get(i).setPrecio(model.getPrecio());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Informacion");
                    alert.setHeaderText(null);
                    alert.setContentText("Producto añadido");

                    alert.showAndWait();
                }else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Informacion");
                    alert.setHeaderText(null);
                    alert.setContentText("No hay suficiente stock");

                    alert.showAndWait();
                }
            }
        }
        if(!existe){
            carro.add(model);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacion");
            alert.setHeaderText(null);
            alert.setContentText("Producto añadido");

            alert.showAndWait();
            }

        contador.setText(String.valueOf(carro.size()));


    }
}
