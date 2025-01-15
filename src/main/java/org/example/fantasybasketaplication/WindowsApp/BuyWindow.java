package org.example.fantasybasketaplication.WindowsApp;


import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import org.example.fantasybasketaplication.controllers.BuyController;


public class BuyWindow extends StartWindow {
    @Override
    public void getController(FXMLLoader loader, Stage primaryStage) {
        BuyController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);
    }
}
