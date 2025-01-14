package org.example.fantasybasketaplication;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;

public class StartController {

    private Stage primaryStage;

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }


    // Método para cambiar de escena
    private void cambiarEscena(String pathScene) throws IOException {
        Scene newScene = new BuyWindow().fxmlLoader(pathScene,getPrimaryStage());  // Usamos BuyWindow para cargar la nueva escena
        primaryStage.setScene(newScene);
    }


    @FXML
    public void handleStartClick(MouseEvent event) {
        System.out.println("¡Botón Start clickeado!");
    }

    @FXML
    public void handleExitClick(MouseEvent event) {
        System.out.println("¡Botón Exit clickeado!");
    }

    @FXML
    public void HandleHomeClick(MouseEvent event) {
        System.out.println("¡Botón de la Ventan Inicio clickeado!");
        try {
            cambiarEscena("Scenes/HomeWindow.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void HandleBuyClick(MouseEvent event) {
        System.out.println("¡Botón de la Ventana Buy clickeado!");
        try {
            cambiarEscena("Scenes/BuyWindow.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void HandleTeamClick(MouseEvent event) {
        System.out.println("¡Botón de la Ventana Team clickeado!");
        try {
            cambiarEscena("Scenes/TeamWindow.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
