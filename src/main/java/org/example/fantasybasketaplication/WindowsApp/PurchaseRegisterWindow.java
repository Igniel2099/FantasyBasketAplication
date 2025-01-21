package org.example.fantasybasketaplication.WindowsApp;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import org.example.fantasybasketaplication.controllers.PurchaseRegisterController;

public class PurchaseRegisterWindow extends StartWindow{

    @Override
    public void getController(FXMLLoader loader, Stage primaryStage) {
        PurchaseRegisterController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);
    }
}
