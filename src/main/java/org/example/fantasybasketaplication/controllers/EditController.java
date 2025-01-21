package org.example.fantasybasketaplication.controllers;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.fantasybasketaplication.WindowsApp.TeamWindow;

/**
 * Esta clase es el controlador de la Ventana EditWindow
 */
public class EditController {
    private Stage primaryStage;
    private String result;


    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void handleDeleteButtonClicked(MouseEvent event) {
        System.out.println("Delete button clicked");
        setResult("Delete");
        primaryStage.close();

    }

    public void handleReplaceButtonClicked(MouseEvent event) {
        System.out.println("Replace button clicked");
        setResult("Replace");
        primaryStage.close();
    }
}
