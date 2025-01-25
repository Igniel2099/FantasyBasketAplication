package org.example.fantasybasketaplication.controllers;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import org.example.fantasybasketaplication.WindowsApp.BuyWindow;
import org.example.fantasybasketaplication.information.GetCsvInfo;
import org.example.fantasybasketaplication.information.TableRowData;

import java.io.IOException;

public class PurchaseRegisterController extends FatherController {

    @FXML
    private TableView<TableRowData> tableView;
    @FXML
    private TableColumn<TableRowData, String> dineroColumn;
    @FXML
    private TableColumn<TableRowData, String> gastoColumn;
    @FXML
    private TableColumn<TableRowData, String> nombreJugadorColumn;

    public TableView<TableRowData> getTableView() {
        return tableView;
    }

    public void setTableView(TableView<TableRowData> tableView) {
        this.tableView = tableView;
    }

    public TableColumn<TableRowData, String> getDineroColumn() {
        return dineroColumn;
    }

    public void setDineroColumn(TableColumn<TableRowData, String> dineroColumn) {
        this.dineroColumn = dineroColumn;
    }

    public TableColumn<TableRowData, String> getGastoColumn() {
        return gastoColumn;
    }

    public void setGastoColumn(TableColumn<TableRowData, String> gastoColumn) {
        this.gastoColumn = gastoColumn;
    }

    public TableColumn<TableRowData, String> getNombreJugadorColumn() {
        return nombreJugadorColumn;
    }

    public void setNombreJugadorColumn(TableColumn<TableRowData, String> nombreJugadorColumn) {
        this.nombreJugadorColumn = nombreJugadorColumn;
    }

    public void handleBackClicked(MouseEvent event) {
        System.out.println("Back clicked");
        try{
            getPrimaryStage().setScene(new BuyWindow().fxmlLoader("/org/example/fantasybasketaplication/Scenes/BuyWindow.fxml",getPrimaryStage()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize() throws IOException {
        // Leer los datos del CSV

        ObservableList<TableRowData> dataList = GetCsvInfo.readCSV("registrosDeCompra.csv");

        // Asignar las columnas
        getDineroColumn().setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDineroActual()));
        getGastoColumn().setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGasto()));
        getNombreJugadorColumn().setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombreJugador()));

        // Asignar los datos a la tabla
        getTableView().setItems(dataList);
    }

}
