package org.example.fantasybasketaplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BuyWindow extends Application {


    public Scene fxmlLoader(String pathScene,Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(pathScene));
        Parent root = loader.load();

        // Obtener el controlador y pasar el Stage principal
        StartController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);

        return new Scene(root);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Scene scene = fxmlLoader("Scenes/BuyWindow.fxml",primaryStage);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
