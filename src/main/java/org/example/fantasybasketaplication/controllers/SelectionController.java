package org.example.fantasybasketaplication.controllers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import javafx.scene.input.MouseEvent;
import org.example.fantasybasketaplication.information.GetJsonInfo;
import org.example.fantasybasketaplication.information.Player;

import javafx.scene.control.TableView;

import java.util.List;

public class SelectionController {

    private Stage primaryStage;

    private Player playerSelected;

    @FXML
    private TableView<Player> tableSelection;

    @FXML
    private TableColumn<Player, ImageView> columnPhotoName;
    @FXML
    private TableColumn<Player, ImageView> columnPhotoTeam;
    @FXML
    private TableColumn<Player, String> columnName;
    @FXML
    private TableColumn<Player, String> columnPosition;
    @FXML
    private TableColumn<Player, Double> columnPrice;
    @FXML
    private TableColumn<Player, String> columnTeam;

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Player getPlayerSelected() {
        return playerSelected;
    }

    public void setPlayerSelected(Player playerSelected) {
        this.playerSelected = playerSelected;
    }

    public TableView<Player> getTableSelection() {
        return tableSelection;
    }

    public void setTableSelection(TableView<Player> tableSelection) {
        this.tableSelection = tableSelection;
    }

    public TableColumn<Player, ImageView> getColumnPhotoName() {
        return columnPhotoName;
    }

    public void setColumnPhotoName(TableColumn<Player, ImageView> columnPhotoName) {
        this.columnPhotoName = columnPhotoName;
    }

    public TableColumn<Player, ImageView> getColumnPhotoTeam() {
        return columnPhotoTeam;
    }

    public void setColumnPhotoTeam(TableColumn<Player, ImageView> columnPhotoTeam) {
        this.columnPhotoTeam = columnPhotoTeam;
    }

    public TableColumn<Player, String> getColumnName() {
        return columnName;
    }

    public void setColumnName(TableColumn<Player, String> columnName) {
        this.columnName = columnName;
    }

    public TableColumn<Player, String> getColumnPosition() {
        return columnPosition;
    }

    public void setColumnPosition(TableColumn<Player, String> columnPosition) {
        this.columnPosition = columnPosition;
    }

    public TableColumn<Player, Double> getColumnPrice() {
        return columnPrice;
    }

    public void setColumnPrice(TableColumn<Player, Double> columnPrice) {
        this.columnPrice = columnPrice;
    }

    public TableColumn<Player, String> getColumnTeam() {
        return columnTeam;
    }

    public void setColumnTeam(TableColumn<Player, String> columnTeam) {
        this.columnTeam = columnTeam;
    }

    public void handleSeleccionarClicked(MouseEvent event) {
        System.out.println("botón de seleccionado clickeado");
        if (getPlayerSelected() != null) {
            System.out.println("seleccionado: " + getPlayerSelected());
            getPrimaryStage().close();
        }
    }



    @FXML
    public void initialize() {
        columnPhotoName.setCellValueFactory(player -> createImageViewCell(player.getValue().getPathPhotoName()));
        columnPhotoTeam.setCellValueFactory(player -> createImageViewCell(player.getValue().getPathPhotoTeam()));
        columnName.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getName()));
        columnPosition.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getPosition()));
        columnPrice.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getPrice()));
        columnTeam.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getTeam()));

        GetJsonInfo getJsonInfo = new GetJsonInfo();
        List<Player> listPlayer = getJsonInfo.getPlayersJson("savePlayers.json");

        // Datos de ejemplo
        ObservableList<Player> players = FXCollections.observableArrayList(listPlayer);

        tableSelection.setItems(players);

        // Agrega un manejador de eventos para las filas
        tableSelection.setRowFactory(tv -> {
            TableRow<Player> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                    Player clickedPlayer = row.getItem();
                    System.out.println("Fila clickeada: " + clickedPlayer);
                    // Llama a un método personalizado o maneja el clic aquí
                    handleRowClick(clickedPlayer);
                }
            });
            return row;
        });
    }

    private void handleRowClick(Player player) {
        System.out.println("Has seleccionado: " + player.getName());
        setPlayerSelected(player);
    }

    private ObservableValue<ImageView> createImageViewCell(String imagePath) {
        System.out.println(imagePath);
        ImageView imageView = new ImageView(new Image(imagePath));
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        imageView.setPreserveRatio(true);
        return new SimpleObjectProperty<>(imageView);
    }
}
