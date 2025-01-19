package org.example.fantasybasketaplication.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.fantasybasketaplication.WindowsApp.BuyWindow;
import org.example.fantasybasketaplication.information.GetJsonInfo;
import org.example.fantasybasketaplication.information.Player;
import javafx.scene.input.MouseEvent;
import org.example.fantasybasketaplication.information.SetJsonInfo;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class BuyController extends FatherController {

    @FXML
    private ScrollPane mainScrollPane;

    @FXML
    private Label labelMoney;

    public Label getLabelMoney() {
        return labelMoney;
    }

    public void setLabelMoney(Label labelMoney) {
        this.labelMoney = labelMoney;
    }

    public ScrollPane getMainScrollPane() {
        return mainScrollPane;
    }

    public void setMainScrollPane(ScrollPane mainScrollPane) {
        this.mainScrollPane = mainScrollPane;
    }


    public Double doubleConvertLabel(String valueLabel){
        String nuevoDato = valueLabel.replaceAll("[.$]", "");
        return Double.parseDouble(nuevoDato);
    }

    /**
     * Este método crea el formato del label, le pone puntos en vez de tenerlo todo junto.
     * @param stringValueLabel Esta es la string que quiero darle el formato.
     * @return devuelvo la string con el formato de puntos y el $ al final.
     */
    public StringBuilder formatLabel(String stringValueLabel) {

        StringBuilder newString;
        String form;
        if (stringValueLabel.length() % 3 == 0) {

            newString = new StringBuilder();
            form = stringValueLabel;

        } else if (stringValueLabel.substring(2).length() % 3 == 0) {

            newString = new StringBuilder(stringValueLabel.substring(0,2) + ".");
            form = stringValueLabel.substring(2);

        }else{

            newString = new StringBuilder(stringValueLabel.substring(0,1) + ".");
            form = stringValueLabel.substring(1);

        }

        for (int i = 0; i < form.length();i++){
            if (i % 3 == 0 && i != 0) {
                newString.append(".");
            }
            newString.append(form.charAt(i));
        }
        return newString;
    }

    /**
     * Convierto un double en un string con un formato en específico
     * @param valueLabel Este es el double a convertir (puede tener )
     * @return me devuelve el string convertido
     */
    public String stringConvertLabel(Double valueLabel){

        DecimalFormat formatter = new DecimalFormat("#"); // Formato de número entero sin decimales
        String stringValueLabel = formatter.format(valueLabel);

        if (stringValueLabel.length() > 3){

            StringBuilder newString = formatLabel(stringValueLabel);

            System.out.println(newString);
            return newString.append("$").toString();

        }else{

            return stringValueLabel + "$";
        }
    }



    /**
     * Método controlador que maneja el evento onMouseClicked.
     * @param event El evento del clic.
     */
    public void handleButtonClick(MouseEvent event) {
        // Obtener el botón que disparó el evento
        ImageView source = (ImageView) event.getSource();


        // Mostrar pantalla de confirmación (ejemplo simplificado)
        System.out.println("¡Imagen clickeada! Dato asociado: " + source.getUserData());
        String confirmation = new BuyWindow().showConfirmationDialog();
        System.out.println("Respuesta: " + confirmation);
        if (!confirmation.equals("no")) {

            GetJsonInfo getJsonInfo = new GetJsonInfo();

            HashMap<String, Object> hashMapPlayer = getJsonInfo.getSearchPlayer((String) source.getUserData());

            SetJsonInfo setJsonInfo = new SetJsonInfo();
            // guardar en un json el jugador comprado.

            setJsonInfo.addJsonToFile(
                    setJsonInfo.convertFormatJson(
                        (String) hashMapPlayer.get("name"),
                        (String) hashMapPlayer.get("position"),
                        (Double) hashMapPlayer.get("price"),
                        (String) hashMapPlayer.get("team"),
                        (String) hashMapPlayer.get("pathPhotoName"),
                        (String) hashMapPlayer.get("pathPhotoTeam")
                    )
            );

            // Cambiar el label del money
            Double doubleLabelMoney = doubleConvertLabel(getLabelMoney().getText());
            Double price = (Double) hashMapPlayer.get("price");
            Double result = doubleLabelMoney - price;

            String stringResult = stringConvertLabel(result);

            getLabelMoney().setText(stringResult);

        }
    }

    /**
     * Este método inicializa todo lo que necesito cargarle a la ventana BuyWindow que es un grid que contiene muchos
     * HBox que contienen la información de los jugadores de mi base de datos (data.json), después de generar ese grid
     * añade el grid al scrollPanel obteniendo el contenido de dentro y añadiendo este al final.
     */
    public void initialize() {

        BuyWindow buyWindow = new BuyWindow();
        GetJsonInfo getJsonInfo = new GetJsonInfo();
        List<Player> listPlayers = getJsonInfo.getPlayersJson();

        List<HBox> listCardsPlayers = new ArrayList<>();

        for (Player player : listPlayers) {
            HBox hBox = buyWindow.createCardPlayer(this,player.getName(),player.getPosition(),player.getPrice() + "$",player.getPathPhotoName(),player.getPathPhotoTeam());
            listCardsPlayers.add(hBox);
        }

        GridPane gridPane = buyWindow.createGridPlayer(listCardsPlayers);

        VBox currentBox = (VBox) getMainScrollPane().getContent();
        currentBox.getChildren().addAll(gridPane);

    }


}
