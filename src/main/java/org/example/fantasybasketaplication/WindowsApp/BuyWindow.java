package org.example.fantasybasketaplication.WindowsApp;


import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.fantasybasketaplication.controllers.BuyController;
import org.example.fantasybasketaplication.information.Player;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


public class BuyWindow extends StartWindow {
    @Override
    public void getController(FXMLLoader loader, Stage primaryStage) {
        BuyController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);
    }

    public String showConfirmationDialog() {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL); // Bloquea la ventana principal
        dialogStage.setTitle("Confirmación");

        // Mensaje
        Label label = new Label("¿Estas Seguro de esta Compra?");

        // Botones
        Button yesButton = new Button("Sí");
        Button noButton = new Button("No");

        // Manejar clics en botones
        AtomicReference<String> dato = new AtomicReference<>("");
        yesButton.setOnAction(e -> {
            dato.set("Sí");
            dialogStage.close();
        });

        noButton.setOnAction(e -> {
            dato.set("No");
            dialogStage.close();
        });

        // Layout
        HBox buttonBox = new HBox(10, yesButton, noButton);
        VBox dialogLayout = new VBox(20, label, buttonBox);
        dialogLayout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        buttonBox.setStyle("-fx-alignment: center;");

        Scene dialogScene = new Scene(dialogLayout, 250, 150);
        dialogStage.setScene(dialogScene);
        dialogStage.showAndWait(); // Mostrar el diálogo y esperar respuesta

        return dato.get();
    }

    public HBox createCardPlayer( BuyController buyController,String name, String position, String price, String pathPhotoName, String pathPhotoTeam){
        HBox hbox = new HBox();
        hbox.setPrefHeight(100);
        hbox.setPrefWidth(365);
        hbox.setStyle("-fx-background-color: #746060; -fx-background-radius: 20px;");
        hbox.setPadding(new Insets(10, 0, 10, 0));

        // Crear StackPane dentro del HBox
        StackPane stackPane = new StackPane();
        stackPane.setPrefHeight(120);
        stackPane.setPrefWidth(100);

        // Creo un Pane que va a ser el recuadro blanco de detrás de las imágenes
        Pane pane = new Pane();
        pane.setPrefHeight(120);
        pane.setPrefWidth(100);
        pane.setStyle("-fx-background-color: white; -fx-background-radius: 20px;");

        // Lo añado al StackPane el primero, para que se quede atrás del todo y le doy márgenes
        stackPane.getChildren().add(pane);
        StackPane.setMargin(pane, new Insets(0, 10, 0, 10) );

        ImageView imageView1 = new ImageView(new Image(getClass().getResource(pathPhotoName).toExternalForm()));

        imageView1.setFitHeight(120);
        imageView1.setFitWidth(100);
        imageView1.setPickOnBounds(true);
        imageView1.setPreserveRatio(true);

        // margenes de imagenView1
        stackPane.setMargin(imageView1, new Insets(0, 20, 0, 20));



        ImageView imageView2 = new ImageView(new Image(getClass().getResource(pathPhotoTeam).toExternalForm()));
        imageView2.setFitHeight(37);
        imageView2.setFitWidth(37);
        imageView2.setPickOnBounds(true);
        imageView2.setPreserveRatio(true);

        // Establecer la alineación de la segunda imagen dentro del StackPane
        StackPane.setAlignment(imageView2, javafx.geometry.Pos.BOTTOM_RIGHT);
        StackPane.setMargin(imageView2, new Insets(0, 20, 10, 0));

        stackPane.getChildren().addAll(imageView1, imageView2);

        // Crear VBox dentro del HBox
        VBox vbox = new VBox();
        vbox.setPrefHeight(138);
        vbox.setPrefWidth(214);

        // Crear las etiquetas dentro del VBox
        Label nameLabel = new Label(name);
        nameLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        VBox.setMargin(nameLabel, new Insets(10, 0, 0, 0));

        Label positionLabel = new Label(position);
        positionLabel.setStyle("-fx-background-color: #0035C6; -fx-background-radius: 20px; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
        VBox.setMargin(positionLabel, new Insets(3, 0, 0, 0));

        Label priceLabel = new Label(price);

        priceLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #fafafa;");
        positionLabel.setPadding(new Insets(0, 10, 0, 10));
        VBox.setMargin(priceLabel, new Insets(3, 0, 0, 0));

        // Crear un ImageView para el botón
        ImageView buttonImageView = new ImageView(new Image(getClass().getResource("/org/example/fantasybasketaplication/Images/botonComprar.png").toExternalForm()));
        buttonImageView.setUserData(name);
        buttonImageView.setFitHeight(20);
        buttonImageView.setFitWidth(80);
        buttonImageView.getStyleClass().add("button-buy");

        buttonImageView.setOnMouseClicked(buyController::handleButtonClick);

        VBox.setMargin(buttonImageView, new Insets(3, 0, 0, 0));


        vbox.getChildren().addAll(nameLabel, positionLabel, priceLabel, buttonImageView);

        // Agregar el StackPane y VBox al HBox
        hbox.getChildren().addAll(stackPane, vbox);

        return hbox;
    }

    public GridPane createGridPlayer(List<HBox> listCardsPlayers){
        int numRows = listCardsPlayers.size() % 2 == 0 ? listCardsPlayers.size() / 2 : listCardsPlayers.size() / 2 + 1;

        GridPane gridPane = new GridPane();
        gridPane.setHgap(40);
        gridPane.setVgap(20);

        gridPane.setPadding(new Insets(20, 20, 20, 20));

        int countCardsPlayers = 0;
        for (int i = 0; i < numRows; i++) {

            gridPane.add(listCardsPlayers.get(countCardsPlayers), 0, i );
            countCardsPlayers++;

            if (countCardsPlayers == listCardsPlayers.size()){
                break;
            }

            gridPane.add(listCardsPlayers.get(countCardsPlayers), 1, i );
            countCardsPlayers++;


        }

        return gridPane;
    }




}
