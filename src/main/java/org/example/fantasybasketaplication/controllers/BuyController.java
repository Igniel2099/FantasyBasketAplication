package org.example.fantasybasketaplication.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.fantasybasketaplication.WindowsApp.BuyWindow;
import org.example.fantasybasketaplication.WindowsApp.PurchaseRegisterWindow;
import org.example.fantasybasketaplication.information.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * Esta clase es un controlador de la escena buyWindow que se encarga de gestionar los botones y eventos que suceden en
 * la ventana de buyWindow, ademas de hacer una correcta inicialización de todos los datos.
 */
public class BuyController extends FatherController {

    public int contador = 0; // esto lo tengo que eliminar

    public List<Double> newList = new ArrayList<>();
    /**
     * El atributo mainScrollPane es el scroll principal de la aplicación en la que se contendrán el Vbox principal y
     * sus respectivos contenedores.
     */
    @FXML
    private ScrollPane mainScrollPane;

    /**
     * El atributo labelMoney es el label en el que se escribe el dinero que se maneja durante el programa
     */
    @FXML
    private Label labelMoney;

    @FXML
    private ImageView idGhrapic;

    public ImageView getIdGhrapic() {
        return idGhrapic;
    }

    public void setIdGhrapic(ImageView idGhrapic) {
        this.idGhrapic = idGhrapic;
    }



    /**
     * Getter del labelMoney
     * @return me devuelve un label
     */
    public Label getLabelMoney() {
        return labelMoney;
    }

    public void setLabelMoney(Label labelMoney) {
        this.labelMoney = labelMoney;
    }

    public void updateGraph(List<Double> transactions) {
        Image chartImage = createGraphImage(transactions, 169, 160);
        getIdGhrapic().setImage(chartImage);
    }

    // Crear la gráfica como una imagen a partir de las transacciones
    private Image createGraphImage(List<Double> transactions, int width, int height) {
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Dibujar fondo
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, width, height);

        // Configurar estilos para la línea
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(2);

        // Dibujar eje X e Y
        gc.setStroke(Color.GRAY);
        gc.setLineWidth(1);
        gc.strokeLine(50, height - 50, width - 50, height - 50); // Eje X
        gc.strokeLine(50, 50, 50, height - 50); // Eje Y

        // Dibujar puntos y líneas
        if (transactions != null && !transactions.isEmpty()) {
            double balance = 0.0;
            double maxY = transactions.stream().mapToDouble(Math::abs).max().orElse(1);
            double scaleX = (width - 100) / (double) transactions.size();
            double scaleY = (height - 100) / (2 * maxY);

            double previousX = 50;
            double previousY = height - 50 - (balance * scaleY);

            for (int i = 0; i < transactions.size(); i++) {
                balance += transactions.get(i);
                double x = 50 + (i * scaleX);
                double y = height - 50 - (balance * scaleY);

                // Dibujar línea
                gc.setStroke(Color.BLUE);
                gc.strokeLine(previousX, previousY, x, y);

                // Dibujar punto
                gc.setFill(Color.RED);
                gc.fillOval(x - 3, y - 3, 6, 6);

                // Actualizar coordenadas previas
                previousX = x;
                previousY = y;
            }
        }

        // Convertir el Canvas en una imagen
        return canvas.snapshot(null, null);
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
     * @param valueLabel Este es el double a convertir
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
        ImageView imageButton = (ImageView) event.getSource();


        // Mostrar pantalla de confirmación
        System.out.println("¡Imagen clickeada! Dato asociado: " + imageButton.getUserData());

        String confirmation = "";
        try {
            confirmation = new BuyWindow().showConfirmationDialog("Comprar");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Respuesta: " + confirmation);

        if (!confirmation.equals("No")) {

            GetJsonInfo getJsonInfo = new GetJsonInfo();

            HashMap<String, Object> hashMapPlayer = getJsonInfo.getSearchPlayer((String) imageButton.getUserData());
            System.out.println(hashMapPlayer.get("estado"));
            hashMapPlayer.put("estado","Comprado");
            SetJsonInfo setJsonInfo = new SetJsonInfo();
            // guardar en un json el jugador comprado.

            setJsonInfo.addJsonToFile(
                    setJsonInfo.convertFormatJson(
                        (String) hashMapPlayer.get("name"),
                        (String) hashMapPlayer.get("position"),
                        (Double) hashMapPlayer.get("price"),
                        (String) hashMapPlayer.get("team"),
                        (String) hashMapPlayer.get("estado"),
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

            // añadir el registro de compra al csv

            GetCsvInfo getCsvInfo = new GetCsvInfo();
            SetCsvInfo setCsvInfo = new SetCsvInfo();

            String lineas = getCsvInfo.readFromCsv();
            ArrayList<ArrayList<String>> datos = getCsvInfo.getInformationInArrayList(lineas);
            try{
                datos = setCsvInfo.setInformationInArrayList(datos, new ArrayList<>(Arrays.asList(stringResult,"-" + price, (String) hashMapPlayer.get("name"))));
                setCsvInfo.writeToCsv(datos);
            } catch (Exception e) {
                System.out.println("Error al guardar datos en el csv desde el BuyController: " + e.getMessage());
                e.printStackTrace();
            }

            // cambiar el botón de la imagen.
            imageButton.setImage(new Image(getClass().getResource("/org/example/fantasybasketaplication/Images/botonVender.png").toExternalForm()));

            // Tengo que cambiar el nombre del evento que clicka pa que lo venda.

            imageButton.setOnMouseClicked(this::handleSellClick);

            // Cambiar estado del json
            List<HashMap<String, Object>> listHashMapPlayers = getJsonInfo.getPlayersHashMap("data.json");
            for (int i = 0; i < listHashMapPlayers.size(); i++) {
                if (listHashMapPlayers.get(i).get("name").equals(hashMapPlayer.get("name"))){
                    listHashMapPlayers.set(i, hashMapPlayer);
                    break;
                }
            }
            System.out.println(listHashMapPlayers);
            setJsonInfo.writeJsonToFile(listHashMapPlayers,"data.json");

            // cambiar la imagen del grafico

            List<List<Double>> listDouble = new ArrayList<>(Arrays.asList(
                    new ArrayList<>(Arrays.asList(2000000.0, 180999.0)),
                    new ArrayList<>(Arrays.asList(2000000.0, 180999.0, 189097.0)),
                    new ArrayList<>(Arrays.asList(2000000.0, 180999.0, 189097.0, 170987.0)),
                    new ArrayList<>(Arrays.asList(2000000.0, 180999.0, 189097.0, 170987.0, 10980.0))
            ));

            updateGraph(listDouble.get(contador));

            contador++; // este es el contador que me cambia la lista
        }
    }

    public void handleSellClick(MouseEvent event) {
        System.out.println("Hice click en vender");
        ImageView imageButton = (ImageView) event.getSource();
        String confirmation = "";
        try {
            confirmation = new BuyWindow().showConfirmationDialog("Vender");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Respuesta: " + confirmation);
        if (!confirmation.equals("No")) {
            // eliminar el registro del json savePlayers
            SetJsonInfo setJsonInfo = new SetJsonInfo();
            setJsonInfo.deleteJsonFromFile("savePlayers.json",imageButton.getUserData().toString());

            // cambiar el estado del json data
            GetJsonInfo getJsonInfo = new GetJsonInfo();
            HashMap<String, Object> hashMapPlayer = getJsonInfo.getSearchPlayer((String) imageButton.getUserData());
            hashMapPlayer.put("estado","No Comprado");
            List<HashMap<String, Object>> listHashMapPlayers = getJsonInfo.getPlayersHashMap("data.json");

            for (int i = 0; i < listHashMapPlayers.size(); i++) {
                if (listHashMapPlayers.get(i).get("name").equals(hashMapPlayer.get("name"))){
                    listHashMapPlayers.set(i, hashMapPlayer);
                    break;
                }
            }
            System.out.println(listHashMapPlayers);
            setJsonInfo.writeJsonToFile(listHashMapPlayers,"data.json");

            // borrar el registro de ventas del csv
            GetCsvInfo getCsvInfo = new GetCsvInfo();

            String lineas = getCsvInfo.readFromCsv();

            ArrayList<ArrayList<String>> datos = getCsvInfo.getInformationInArrayList(lineas);
            int indexDelete = 0;
            for (int i = 0; i < datos.size(); i++) {
                if(datos.get(i).get(2).equals( (String) imageButton.getUserData())){
                    indexDelete = i;
                    break;
                }
            }
            String precio = datos.get(indexDelete).get(1).replaceAll("-","");
            Double precioDouble = Double.valueOf(precio);

            datos.remove(indexDelete);

            SetCsvInfo setCsvInfo = new SetCsvInfo();
            try {
                setCsvInfo.writeToCsv(datos);
            }catch (Exception e) {
                System.out.println("Error al guardar datos en el csv del BuyController: " + e.getMessage());
                e.printStackTrace();
            }
            // cambiar el número del label
            Double doubleLabelMoney = doubleConvertLabel(getLabelMoney().getText());
            Double result = doubleLabelMoney + precioDouble;
            String stringResult = stringConvertLabel(result);
            getLabelMoney().setText(stringResult);

            // cambiar el control

            imageButton.setImage(new Image(getClass().getResource("/org/example/fantasybasketaplication/Images/botonComprar.png").toExternalForm()));

            // Tengo que cambiar el nombre del evento que clicka pa que lo venda.

            imageButton.setOnMouseClicked(this::handleButtonClick);


        }

    }

    public void handleRegistroComprasClicked(MouseEvent event) {
        System.out.println("botón de registro de compra");
        try{
            getPrimaryStage().setScene(new PurchaseRegisterWindow().fxmlLoader("/org/example/fantasybasketaplication/Scenes/PurchaseRegisterWindow.fxml",getPrimaryStage()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Este método inicializa todo lo que necesito cargarle a la ventana BuyWindow que es un grid que contiene muchos
     * HBox que contienen la información de los jugadores de mi base de datos (data.json), después de generar ese grid
     * añade el grid al scrollPanel obteniendo el contenido de dentro y añadiendo este al final.
     */
    public void initialize() {
        // Obtener el valor real del dinero que tengo
        GetCsvInfo getCsvInfo = new GetCsvInfo();
        String lineas = getCsvInfo.readFromCsv();
        ArrayList<ArrayList<String>> datos = getCsvInfo.getInformationInArrayList(lineas);
        ArrayList<String> lastRow =datos.getLast();
        getLabelMoney().setText(lastRow.getFirst());

        // Crear las cartas y el gridPane

        BuyWindow buyWindow = new BuyWindow();
        GetJsonInfo getJsonInfo = new GetJsonInfo();
        List<Player> listPlayers = getJsonInfo.getPlayersJson();

        List<HBox> listCardsPlayers = new ArrayList<>();

        for (Player player : listPlayers) {

            HBox hBox = buyWindow.createCardPlayer(
                    this,
                    player.getName(),
                    player.getPosition(),
                    player.getPrice() + "$",
                    player.getEstado(),
                    player.getPathPhotoName(),
                    player.getPathPhotoTeam()
            );

            listCardsPlayers.add(hBox);
        }

        GridPane gridPane = buyWindow.createGridPlayer(listCardsPlayers);

        VBox currentBox = (VBox) getMainScrollPane().getContent();
        currentBox.getChildren().addAll(gridPane);

    }


}
