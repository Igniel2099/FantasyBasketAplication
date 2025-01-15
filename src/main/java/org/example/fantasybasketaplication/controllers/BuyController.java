package org.example.fantasybasketaplication.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.fantasybasketaplication.WindowsApp.BuyWindow;
import org.example.fantasybasketaplication.information.GetJsonInfo;
import org.example.fantasybasketaplication.information.Player;

import java.util.ArrayList;
import java.util.List;


public class BuyController extends FatherController {

    @FXML
    private ScrollPane mainScrollPane;


    public ScrollPane getMainScrollPane() {
        return mainScrollPane;
    }

    public void setMainScrollPane(ScrollPane mainScrollPane) {
        this.mainScrollPane = mainScrollPane;
    }

    /**
     * Este método inicializa todo lo que necesito cargarle a la ventana BuyWindow que es un grid que contiene muchos
     * HBox que contienen la información de los jugadores de mi base de datos (data.json), después de generar ese grid
     * añade el grid al scrollPanel obteniendo el contenido de dentro y añadiendo este al final.
     */
    public void initialize() {

        BuyWindow buyWindow = new BuyWindow();
        GetJsonInfo getJsonInfo = new GetJsonInfo();
        List<Player> listPlayers = getJsonInfo.getPersonsJson();

        List<HBox> listCardsPlayers = new ArrayList<>();

        for (Player player : listPlayers) {
            HBox hBox = buyWindow.createCardPlayer(player.getName(),player.getPosition(),player.getPrice() + "$",player.getPathPhotoName(),player.getPathPhotoTeam());
            listCardsPlayers.add(hBox);
        }

        GridPane gridPane = buyWindow.createGridPlayer(listCardsPlayers);

        VBox currentBox = (VBox) getMainScrollPane().getContent();
        currentBox.getChildren().addAll(gridPane);

    }


}
