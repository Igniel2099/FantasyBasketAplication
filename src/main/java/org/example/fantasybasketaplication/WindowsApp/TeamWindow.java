package org.example.fantasybasketaplication.WindowsApp;


import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import org.example.fantasybasketaplication.controllers.TeamController;

public class TeamWindow extends StartWindow {
    @Override
    public void getController(FXMLLoader loader, Stage primaryStage) {
        TeamController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);
    }
}
