package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.DAO;
import model.ProductoModel;
import model.UsuarioModel;

import java.io.IOException;
import java.util.ArrayList;

/*
 * @author Miguel Angel Hernandez Rodriguez
 * @version 1.0
 * @param imagen Archivo de imagen
 * @param Titulo Campo Titulo tabla Producto
 * @param Autor Campo Autor tabla Producto
 * @param Genero Campo Genero tabla Producto
 * @param Año Campo Año tabla Producto
 * @param Precio Campo Precio tabla Producto
 * @param boton boton que se encarga de añadir al carrito
 * @param caja caja que contiene las opciones de admin
 * @param editar ImagenView que se utiliza de icono
 * @param eliminar ImageView que se utiliza de icono
 * @param enunciado Label que muestra titulo de panel de administracion
 * @param usuario Modelo del usuario que esta usando la aplicacion
 * @param carro Lista con los productos que se estan guardando para comprar
 * @param producto modelo con el producto que se va a mostrar
 */
public class MostrarProductoController {
    @FXML
    ImageView imagen;
    @FXML
    Label Titulo;
    @FXML
    Label Autor;
    @FXML
    Label Año;
    @FXML
    Label Precio;
    @FXML
    Button boton;
    @FXML
    VBox caja;
    @FXML
    ImageView editar;
    @FXML
    ImageView eliminar;
    @FXML
    Label enunciado;

    UsuarioModel usuario;

    ArrayList<ProductoModel> carro;

    ProductoModel producto;

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * Rellena los datos
     * @param fxmlLoader objeto que se utiliza para cargar diferentes archivos FXML
     * @param root1 nodo principal del archivo FXML
     * @param stage Escenario en el que se muestra la interfaz
     * @param a objeto auxliar para pasar datos
     * @param b objeto auxliar para cerrar la interfaz
     * @param inicio controlador de la interfaz que se va a abrir para pasarle datos
     */

    @FXML
    public void editarProducto(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditarProducto.fxml"));
            Parent root1= null;
            root1 = (Parent)fxmlLoader.load();
            Stage stage= new Stage();
            stage.setScene(new Scene(root1));
            EditarProductoController a = (EditarProductoController) fxmlLoader.getController();
            a.opciones();
            a.datos(producto);
            stage.show();
            Stage b = (Stage) enunciado.getScene().getWindow();
            b.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * Rellena los datos
     * @param dao realiza las operaciones con la base de datos
     */

    @FXML
    public void eliminarProducto(){
        DAO dao = new DAO();
        dao.eliminarProducto(producto);
    }

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * Rellena los datos
     * @param productoModel modelo con el producto
     * @param carro lista con el carro del usuario
     */

    public void rellenarDatos(ProductoModel productoModel, ArrayList<ProductoModel> carro) {
        this.usuario=usuario;
        this.carro=carro;
        this.producto=productoModel;
        this.imagen.setImage(productoModel.getImagen());
        this.Titulo.setText(productoModel.getTitulo());
        this.Autor.setText(productoModel.getAutor());
        this.Año.setText(String.valueOf(productoModel.getAño()));
        this.Precio.setText(String.valueOf(productoModel.getPrecio()));
        if(productoModel.getUsuario()!=null) {
            this.usuario = productoModel.getUsuario();
            if(this.usuario.getAdmin()==0){
                caja.setMinHeight(0);
                caja.setMinWidth(0);
                caja.setPrefHeight(0);
                caja.setPrefWidth(0);
                caja.setPrefWidth(0);
                caja.setMaxHeight(0);
                caja.setMaxWidth(0);
                editar.setVisible(false);
                eliminar.setVisible(false);
                enunciado.setVisible(false);
            }
        }

        if(this.usuario==null){
            caja.setMinHeight(0);
            caja.setMinWidth(0);
            caja.setPrefHeight(0);
            caja.setPrefWidth(0);
            caja.setPrefWidth(0);
            caja.setMaxHeight(0);
            caja.setMaxWidth(0);
            editar.setVisible(false);
            eliminar.setVisible(false);
            enunciado.setVisible(false);
        }
    }
}
