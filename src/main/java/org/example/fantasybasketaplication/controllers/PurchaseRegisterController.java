package org.example.fantasybasketaplication.controllers;

import javafx.scene.input.MouseEvent;
import org.example.fantasybasketaplication.WindowsApp.BuyWindow;
import org.example.fantasybasketaplication.WindowsApp.PurchaseRegisterWindow;

import java.io.IOException;

public class PurchaseRegisterController extends FatherController {

    public void handleBackClicked(MouseEvent event) {
        System.out.println("Back clicked");
        try{
            getPrimaryStage().setScene(new BuyWindow().fxmlLoader("/org/example/fantasybasketaplication/Scenes/BuyWindow.fxml",getPrimaryStage()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
