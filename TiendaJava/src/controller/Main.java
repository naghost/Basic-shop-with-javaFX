package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

import static com.sun.javafx.scene.control.skin.Utils.getResource;

/*
* @author Miguel Angel Hernandez Rodriguez
* @version 1.0
* @param primaryStage es el primer escenario para ejecutar la aplicacion
* @param rootLayout Layout que va a servir de modelo para la primera interfaz
*/
public class Main extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * @param primaryStage es el primer escenario para ejecutar la aplicacion
     * @param rootLayout Layout que va a servir de modelo para la primera interfaz
     */
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("MiguelZon");

        initRootLayout();

        showPersonOverview();
    }

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * @param loader se encarga de iniciar las interfaces de FXML
     * @param rootLayout cargamos el escenario
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * @author Miguel Angel Hernandez Rodriguez
     * @version 1.0
     * @param loader carga la interfaz de Inicio
     * @param personOverview carga la interfaz Inicio
     * @param rootLayout introducimos en la posicion central la interfaz cargada en personOverView
     * @see Inicio.fxml
     * @see InicioController.fxml
     */
    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/Inicio.fxml"));
            GridPane personOverview = (GridPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}
