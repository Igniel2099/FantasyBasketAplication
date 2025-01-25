package org.example.fantasybasketaplication.controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
import javafx.scene.paint.Color;

/**
 * Esta clase es un controlador de la escena buyWindow que se encarga de gestionar los botones y eventos que suceden en
 * la ventana de buyWindow, ademas de hacer una correcta inicialización de todos los datos.
 */
public class BuyController extends FatherController {
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
        Image chartImage = createGraphImage(transactions, 300, 300);
        getIdGhrapic().setImage(chartImage);
    }

    // Crear la gráfica como una imagen a partir de las transacciones
    private Image createGraphImage(List<Double> transactions, int width, int height) {
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Definir márgenes
        int marginLeft = 50; // Aumentado para espacio de etiquetas
        int marginBottom = 30;
        int marginTop = 40; // Espacio adicional para el título
        int marginRight = 20;

        // Dibujar fondo
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, width, height);

        // Dibujar título
        String title = "Balance del dinero gastado";
        gc.setFill(Color.BLACK);
        gc.setFont(new Font("Arial", 16)); // Fuente más grande para el título

        // Calcular el ancho del título para centrarlo
        Text text = new Text(title);
        text.setFont(gc.getFont());
        double textWidth = text.getBoundsInLocal().getWidth();
        gc.fillText(title, (width - textWidth) / 2, 20);

        // Configurar estilos para la cuadricula
        gc.setStroke(Color.LIGHTGRAY);
        gc.setLineWidth(1);

        // Encontrar el valor máximo absoluto para escalar el eje Y
        double maxY = transactions.stream().mapToDouble(Math::abs).max().orElse(1);

        // Divisiones del eje Y (en millones)
        int yDivisions = 5; // Número de divisiones en el eje Y
        double yStep = maxY / yDivisions;

        // Dibujar cuadricula y etiquetas del eje Y
        for (int i = 0; i <= yDivisions; i++) {
            double currentYValue = maxY - i * yStep;
            double yPosition = marginTop + (i * (height - marginTop - marginBottom) / yDivisions);

            // Línea horizontal
            gc.strokeLine(marginLeft, yPosition, width - marginRight, yPosition);

            // Etiqueta del eje Y
            String label = String.format("%.0fM", currentYValue / 1_000_000);
            gc.setFill(Color.BLACK);
            gc.setFont(new Font("Arial", 12));
            gc.fillText(label, marginLeft - 40, yPosition + 5); // Etiqueta alineada con la línea
        }

        // Configurar estilos para la línea del gráfico
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(2);

        // Dibujar eje X e Y
        gc.setStroke(Color.GRAY);
        gc.setLineWidth(1);
        gc.strokeLine(marginLeft, height - marginBottom, width - marginRight, height - marginBottom); // Eje X
        gc.strokeLine(marginLeft, marginTop, marginLeft, height - marginBottom); // Eje Y

        // Dibujar puntos y líneas
        if (transactions != null && !transactions.isEmpty()) {
            // Escala en X y Y
            double scaleX = (width - marginLeft - marginRight) / (double) transactions.size();
            double scaleY = (height - marginTop - marginBottom) / (2 * maxY);  // Aumentar la escala para mayor visibilidad

            // Coordenadas iniciales para el primer punto
            double previousX = marginLeft;
            double previousY = height - marginBottom - (transactions.get(0) * scaleY);

            for (int i = 1; i < transactions.size(); i++) {  // Comienza desde el segundo valor
                double currentBalance = transactions.get(i);
                double x = marginLeft + (i * scaleX);
                double y = height - marginBottom - (currentBalance * scaleY);

                // Dibujar línea entre puntos consecutivos
                gc.setStroke(Color.BLUE);
                gc.strokeLine(previousX, previousY, x, y);

                // Dibujar el punto en la gráfica
                gc.setFill(Color.RED);
                gc.fillOval(x - 3, y - 3, 6, 6);

                // Actualizar coordenadas para el siguiente punto
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
     * Cambia la imagen y el evento asociado a un {@link ImageView} según la acción especificada.
     * Este método modifica la imagen mostrada en el {@link ImageView} y también cambia el
     * evento asociado al clic del ratón en función de la acción proporcionada (ya sea "Comprar" o "Vender").
     * Si se pasa una acción diferente de estas, se lanzará una excepción {@link Exception}.
     *
     * @param imageView El {@link ImageView} cuya imagen y evento serán modificados.
     * @param action La acción que determina qué imagen y evento se deben establecer.
     *               Acepta los valores "Comprar" o "Vender".
     *
     * @throws Exception Si la acción proporcionada no es "Comprar" ni "Vender", se lanzará una excepción
     *                   con un mensaje indicando que la acción no está preparada.
     */
    public void changeImage(ImageView imageView, String action) throws Exception {
        if (!action.equals("Comprar") && !action.equals("Vender")) {
            throw new Exception("No esta preparado el cambio de imagen y de evento para esta acción: " + action);
        }

        String path = action.equals("Comprar")
                ? "/org/example/fantasybasketaplication/Images/botonVender.png"
                : "/org/example/fantasybasketaplication/Images/botonComprar.png";

        // Cambiar el nombre del evento asociado al clic del ratón según la acción.
        EventHandler<MouseEvent> mouseEvent = action.equals("Comprar")
                ? this::handleSellClick
                : this::handleButtonClick;

        imageView.setImage(new Image(getClass().getResource(path).toExternalForm()));
        imageView.setOnMouseClicked(mouseEvent);
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

            // cambiar imagen y cambiar evento del botón
            try{
                changeImage(imageButton,"Comprar");
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Cambiar estado del json
            getJsonInfo.changeStateJson("data.json",hashMapPlayer);

            // cambiar la imagen del gráfico
            updateGraph(getCsvInfo.getColumnDineroActual());
             // este es el contador que me cambia la lista
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

            // cambiar el control y la imagen
            try{
                changeImage(imageButton,"Vender");
            } catch (Exception e) {
                e.printStackTrace();
            }

            // cambiar el gráfico en la venta también
            updateGraph(getCsvInfo.getColumnDineroActual());
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
        GetCsvInfo getCsvInfo = new GetCsvInfo();
        // inicializar el grafico
        updateGraph(getCsvInfo.getColumnDineroActual());
        // Obtener el valor real del dinero que tengo
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
