package org.example.fantasybasketaplication.WindowsApp;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import org.example.fantasybasketaplication.controllers.HomeController;

public class HomeWindow extends StartWindow {

    @Override
    public void getController(FXMLLoader loader, Stage primaryStage) {
        HomeController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);
    }
}
