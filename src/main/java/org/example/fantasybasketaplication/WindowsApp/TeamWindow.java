package org.example.fantasybasketaplication.WindowsApp;


import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.fantasybasketaplication.controllers.EditController;
import org.example.fantasybasketaplication.controllers.SelectionController;
import org.example.fantasybasketaplication.controllers.StartController;
import org.example.fantasybasketaplication.controllers.TeamController;

import java.io.IOException;

public class TeamWindow extends StartWindow {
    @Override
    public void getController(FXMLLoader loader, Stage primaryStage) {
        TeamController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);
    }

    public VBox containerPlayerTeam(String name,String pathPhoto,String position){ // Tengo que cambiarlo para que me acepte por parametro los datos para que pueda cambiarlos con cada llamada
        VBox vBox = new VBox();
        vBox.setPrefHeight(200.0);
        vBox.setPrefWidth(100.0);
        vBox.setStyle("-fx-background-color: #746060; -fx-background-radius: 25px;");

        // Crear el primer Label
        Label label1 = new Label(name);
        label1.setTextFill(Color.WHITE);
        label1.setFont(Font.font("System Bold", 16.0));
        VBox.setMargin(label1, new Insets(10.0, 0.0, 0.0, 25.0));

        // Crear el ImageView
        ImageView imageView = new ImageView(new Image(getClass().getResource(pathPhoto).toExternalForm()));
        imageView.setFitHeight(85.0);
        imageView.setFitWidth(85.0);
        imageView.setPreserveRatio(true);
        imageView.setPickOnBounds(true);
        VBox.setMargin(imageView, new Insets(0.0, 0.0, 0.0, 48.0));

        // Crear el segundo Label
        Label label2 = new Label(position);
        label2.setTextFill(Color.WHITE);
        label2.setStyle("-fx-background-color: blue; -fx-background-radius: 10px;");
        //label2.setContentDisplay(javafx.geometry.Pos.CENTER);
        VBox.setMargin(label2, new Insets(10, 0.0, 0.0, 65.0));
        label2.setPadding(new Insets(3.0, 10.0, 3.0, 10.0));

        // Agregar los elementos al VBox
        vBox.getChildren().addAll(label1, imageView, label2);

        return vBox;
    }


    public SelectionController startStageSelection() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/fantasybasketaplication/Scenes/SelectionWindow.fxml"));
        Parent root = loader.load();

        SelectionController controller = loader.getController();
        controller.setPrimaryStage(stage);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.showAndWait();

        return controller;
    }

    public EditController startStageEdit() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/fantasybasketaplication/Scenes/EditWindow.fxml"));
        Parent root = loader.load();

        EditController controller = loader.getController();
        controller.setPrimaryStage(stage);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.showAndWait();

        return controller;
    }




}
