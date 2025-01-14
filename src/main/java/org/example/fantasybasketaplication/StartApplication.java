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
    /**
     * Este método inicia la aplicación leyendo el Fxml inicial
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scenes/StartWindow.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
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
