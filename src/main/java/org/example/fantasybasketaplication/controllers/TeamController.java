package org.example.fantasybasketaplication.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.example.fantasybasketaplication.WindowsApp.TeamWindow;

import java.io.IOException;
import java.util.HashMap;

public class TeamController extends FatherController{

    @FXML
    private StackPane containerButtonBase;

    @FXML
    private StackPane containerButtonEscolta;

    @FXML
    private StackPane containerButtonAlero;

    @FXML
    private StackPane containerButtonAlaPivot;

    @FXML
    private StackPane containerButtonPivot;

    public StackPane getContainerButtonBase() {
        return containerButtonBase;
    }

    public void setContainerButtonBase(StackPane containerButtonBase) {
        this.containerButtonBase = containerButtonBase;
    }

    public StackPane getContainerButtonEscolta() {
        return containerButtonEscolta;
    }

    public void setContainerButtonEscolta(StackPane containerButtonEscolta) {
        this.containerButtonEscolta = containerButtonEscolta;
    }

    public StackPane getContainerButtonAlero() {
        return containerButtonAlero;
    }

    public void setContainerButtonAlero(StackPane containerButtonAlero) {
        this.containerButtonAlero = containerButtonAlero;
    }

    public StackPane getContainerButtonAlaPivot() {
        return containerButtonAlaPivot;
    }

    public void setContainerButtonAlaPivot(StackPane containerButtonAlaPivot) {
        this.containerButtonAlaPivot = containerButtonAlaPivot;
    }

    public StackPane getContainerButtonPivot() {
        return containerButtonPivot;
    }

    public void setContainerButtonPivot(StackPane containerButtonPivot) {
        this.containerButtonPivot = containerButtonPivot;
    }


    public String selectImage(String nameStackPane){
        HashMap<String,String> hashMapImages = new HashMap<>();
        hashMapImages.put("base","/org/example/fantasybasketaplication/Images/contentBase.png");
        hashMapImages.put("alero","/org/example/fantasybasketaplication/Images/contentAlero.png");
        hashMapImages.put("ala pivot","/org/example/fantasybasketaplication/Images/contentAlaPivot.png");
        hashMapImages.put("escolta","/org/example/fantasybasketaplication/Images/contentEscolta.png");
        hashMapImages.put("pivot","/org/example/fantasybasketaplication/Images/contentPivot.png");

        return hashMapImages.get(nameStackPane);
    }

    public void actionClickedStackPane(StackPane buttonStackPane, String nameStackPane){
        TeamWindow teamWindow = new TeamWindow();
        Object controller = null;
        try {
            if (buttonStackPane.getChildren().getFirst() instanceof ImageView) {
                controller = teamWindow.startStageSelection();
            } else if (buttonStackPane.getChildren().getFirst() instanceof VBox) {
                controller = teamWindow.startStageEdit();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (controller instanceof EditController){
            EditController editController = (EditController) controller;
            String election = editController.getResult();
            System.out.println(election);
            if (election.equals("Delete")){
                ImageView imageView = new ImageView( new Image(getClass().getResource(selectImage(nameStackPane)).toExternalForm()));
                buttonStackPane.getChildren().clear();
                buttonStackPane.getChildren().add(imageView);
            } else if (election.equals("Replace")) {
                try{
                    SelectionController newController = teamWindow.startStageSelection();

                    VBox vBox = teamWindow.containerPlayerTeam(
                            newController.getPlayerSelected().getName(),
                            newController.getPlayerSelected().getPathPhotoName(),
                            newController.getPlayerSelected().getPosition());

                    buttonStackPane.getChildren().clear();
                    buttonStackPane.getChildren().add(vBox);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        } else if (controller instanceof SelectionController) {
            SelectionController selectionController = (SelectionController) controller;

            VBox vBox = teamWindow.containerPlayerTeam(
                    selectionController.getPlayerSelected().getName(),
                    selectionController.getPlayerSelected().getPathPhotoName(),
                    selectionController.getPlayerSelected().getPosition());

            buttonStackPane.getChildren().clear();
            buttonStackPane.getChildren().add(vBox);
        }
    }



    public void handlePivotClicked(MouseEvent event) {
        System.out.println("Pivot clicked");
        actionClickedStackPane(getContainerButtonPivot(), "pivot");
    }

    public void handleBaseClicked(MouseEvent event) {
        System.out.println("Base clicked");
        actionClickedStackPane(getContainerButtonBase(),"base");
    }

    public void handleEscoltaClicked(MouseEvent event) {
        System.out.println("Escolta clicked");
        actionClickedStackPane(getContainerButtonEscolta(), "escolta");
    }

    public void handleAleroClicked(MouseEvent event) {
        System.out.println("Alero clicked");
        actionClickedStackPane(getContainerButtonAlero(), "alero");
    }

    public void handleAlaPivotClicked(MouseEvent event) {
        System.out.println("Ala Pivot clicked");
        actionClickedStackPane(getContainerButtonAlaPivot(), "ala pivot");
    }

}
