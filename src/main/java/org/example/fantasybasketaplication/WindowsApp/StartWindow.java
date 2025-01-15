package org.example.fantasybasketaplication.WindowsApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.fantasybasketaplication.controllers.StartController;

import java.io.IOException;


/**
 * La clase donde se lee la principal escena de la aplicación
 */
public class StartWindow extends Application  {

    /**
     * Este método te permite obtener el controlador de la ventana y mandarle a este mismo controlador
     * el Stage.
     * @param loader el loader de la escena
     * @param primaryStage el stage que tengo que pasarle al controlador
     */
    public void getController(FXMLLoader loader, Stage primaryStage) {
        StartController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);
    }

    /**
     * Este método te permite cargar el fxml como la escena de un stage y mandarle el stage al controlador
     * @param pathScene la dirección de la escena
     * @param primaryStage el primer escenario
     * @return me devuelve la escena
     * @throws IOException controlar las posibles excepciones del programa
     */
    public Scene fxmlLoader(String pathScene,Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(pathScene));
        Parent root = loader.load();

        // Obtener el controlador y pasar el Stage principal
        getController(loader, primaryStage);

        return new Scene(root);
    }

    /**
     * Este método inicia la aplicación leyendo el Fxml inicial
     * @param stage Este es el escenario principal donde se va ha ejecutar
     * @throws IOException controla las posibles excepciones
     */
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = fxmlLoader("/org/example/fantasybasketaplication/Scenes/StartWindow.fxml",stage);

        stage.setScene(scene);
        stage.show(); // mostrar el escenario
    }

    /**
     * En este main ejecuta la app
     * @param args posibles argumentos que pueda pasarle
     */
    public static void main(String[] args) {
        launch(args);
    }

}
