package org.example.fantasybasketaplication.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.fantasybasketaplication.WindowsApp.BuyWindow;
import org.example.fantasybasketaplication.WindowsApp.HomeWindow;
import org.example.fantasybasketaplication.WindowsApp.StartWindow;
import org.example.fantasybasketaplication.WindowsApp.TeamWindow;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * Esta clase es el padre de todos los controladores, aquí solo está lo que tienen en común todos los controladores
 */
public class FatherController {

    private Stage primaryStage;

    /**
     * Este es el método para obtener el primaryStage
     * @return me devuelve el primaryStage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * En este método puedo cambiar el stage
     * @param primaryStage el stage por el cúal lo quiero cambiar
     */
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Este método sirve para cambiar de escena lo que hace primero es reconocer que Ventana es la que se quiere cambiar
     * y con ello se identifica su clase, después se obtiene la escena en cuestión y cambia la escena del escenario
     * @param pathScene la ruta en donde se aloja la escena
     * @param window la ventana a la que quiero cambiar
     */
    public void cambiarEscena(String pathScene,String window){

        try {
            Scene scene = switch (window) {
                case "StartWindow" -> new StartWindow().fxmlLoader(pathScene, getPrimaryStage());
                case "HomeWindow" -> new HomeWindow().fxmlLoader(pathScene, getPrimaryStage());
                case "BuyWindow" -> new BuyWindow().fxmlLoader(pathScene, getPrimaryStage());
                case "TeamWindow" -> new TeamWindow().fxmlLoader(pathScene, getPrimaryStage());
                default -> throw new IOException("No se reconoce la ventana: " + window);
            };

            primaryStage.setScene(scene);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Este es el método que recoge la acción cuando se hace click en el botón de inicio de la pantalla principal
     * @param event el evento en cuestión
     */
    @FXML
    public void handleStartClick(MouseEvent event) {
        System.out.println("¡Botón Start clickeado!");
        System.out.println("otras");
        cambiarEscena("/org/example/fantasybasketaplication/Scenes/HomeWindow.fxml","HomeWindow");
    }

    /**
     * Este es el método que recoge la acción cuando se hace click en el botón de salida de la pantalla principal
     */
    @FXML
    public void handleExitClick() {
        System.out.println("¡Botón Exit clickeado!");
    }

    /**
     * Este es el método que recoge la acción cuando se hace click en el botón de Home del bar menu de alguna pantalla
     */
    @FXML
    public void HandleHomeClick() {
        System.out.println("¡Botón de la Ventan Inicio clickeado!");
        cambiarEscena("/org/example/fantasybasketaplication/Scenes/HomeWindow.fxml","HomeWindow");
    }

    /**
     * Este es el método que recoge la acción cuando se hace click en el botón de Buy del bar menu de alguna pantalla
     */
    @FXML
    public void HandleBuyClick() {
        System.out.println("¡Botón de la Ventana Buy clickeado!");
        cambiarEscena("/org/example/fantasybasketaplication/Scenes/BuyWindow.fxml","BuyWindow");
    }

    /**
     * Este es el método que recoge la acción cuando se hace click en el botón de Team del bar menu de alguna pantalla
     */
    @FXML
    public void HandleTeamClick() {
        System.out.println("¡Botón de la Ventana Team clickeado!");
        cambiarEscena("/org/example/fantasybasketaplication/Scenes/TeamWindow.fxml","TeamWindow");
    }
}
