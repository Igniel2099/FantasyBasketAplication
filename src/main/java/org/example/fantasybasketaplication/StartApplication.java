package org.example.fantasybasketaplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * La clase donde se leé la principal escena de la aplicación
 */
public class StartApplication extends Application  {

    public Scene fxmlLoader(String pathScene,Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(pathScene));
        Parent root = loader.load();

        // Obtener el controlador y pasar el Stage principal
        StartController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);

        return new Scene(root);
    }

    /**
     * Este método inicia la aplicación leyendo el Fxml inicial
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = fxmlLoader("Scenes/StartWindow.fxml",stage);

        stage.setScene(scene);
        stage.show(); // mostrar el escenario
    }

    /**
     * En este main ejecuta la app
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

}
