package org.example.fantasybasketaplication.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

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

    public void handlePivotClicked(MouseEvent event) {
        System.out.println("Pivot clicked");
        getContainerButtonPivot().getChildren().clear();

    }

    public void handleBaseClicked(MouseEvent event) {
        System.out.println("Base clicked");
        getContainerButtonBase().getChildren().clear();
    }

    public void handleEscoltaClicked(MouseEvent event) {
        System.out.println("Escolta clicked");
        getContainerButtonEscolta().getChildren().clear();
    }

    public void handleAleroClicked(MouseEvent event) {
        System.out.println("Alero clicked");
        getContainerButtonAlero().getChildren().clear();
    }

    public void handleAlaPivotClicked(MouseEvent event) {
        System.out.println("Ala Pivot clicked");
        getContainerButtonAlaPivot().getChildren().clear();
    }

}
