package org.example.fantasybasketaplication.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.example.fantasybasketaplication.WindowsApp.TeamWindow;
import org.example.fantasybasketaplication.information.GetJsonInfo;
import org.example.fantasybasketaplication.information.Player;
import org.example.fantasybasketaplication.information.SetJsonInfo;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class TeamController extends FatherController{

    private SetJsonInfo setJsonInfo = new SetJsonInfo();

    private GetJsonInfo getJsonInfo = new GetJsonInfo();

    @FXML
    private ImageView id_alero;

    @FXML
    private ImageView id_base;

    @FXML
    private ImageView id_ala_pivot;

    @FXML
    private ImageView id_escolta;

    @FXML
    private ImageView id_pivot;



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

    public SetJsonInfo getSetJsonInfo() {
        return setJsonInfo;
    }

    public void setSetJsonInfo(SetJsonInfo setJsonInfo) {
        this.setJsonInfo = setJsonInfo;
    }

    public GetJsonInfo getGetJsonInfo() {
        return getJsonInfo;
    }

    public void setGetJsonInfo(GetJsonInfo getJsonInfo) {
        this.getJsonInfo = getJsonInfo;
    }

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

    public ImageView getId_alero() {
        return id_alero;
    }

    public void setId_alero(ImageView id_alero) {
        this.id_alero = id_alero;
    }

    public ImageView getId_base() {
        return id_base;
    }

    public void setId_base(ImageView id_base) {
        this.id_base = id_base;
    }

    public ImageView getId_ala_pivot() {
        return id_ala_pivot;
    }

    public void setId_ala_pivot(ImageView id_ala_pivot) {
        this.id_ala_pivot = id_ala_pivot;
    }

    public ImageView getId_escolta() {
        return id_escolta;
    }

    public void setId_escolta(ImageView id_escolta) {
        this.id_escolta = id_escolta;
    }

    public ImageView getId_pivot() {
        return id_pivot;
    }

    public void setId_pivot(ImageView id_pivot) {
        this.id_pivot = id_pivot;
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

    /**
     * Este método crea un mapa de el id de un contenedor y la imagen que se corresponde con ese id para después
     * devolver la imagen del id que se esta buscando.
     * @param searchId identificador del contenedor del que quiero buscar su imagen relacionada
     * @return me devuelve la imagen relacionada con el identificador del contenedor
     */
    public ImageView selectImageTeamField(String searchId){
        HashMap<String,ImageView> mapImagesContainer = new HashMap<>();

        mapImagesContainer.put("containerButtonBase",getId_base());
        mapImagesContainer.put("containerButtonEscolta",getId_escolta());
        mapImagesContainer.put("containerButtonAlero",getId_alero());
        mapImagesContainer.put("containerButtonAlaPivot",getId_ala_pivot());
        mapImagesContainer.put("containerButtonPivot",getId_pivot());

        return  mapImagesContainer.get(searchId);
    }


    public String getPositionContainer(String searchId){
        HashMap<String,String> mapPositionContainer = new HashMap<>();

        mapPositionContainer.put("containerButtonBase","Base");
        mapPositionContainer.put("containerButtonEscolta","Escolta");
        mapPositionContainer.put("containerButtonAlero","Alero");
        mapPositionContainer.put("containerButtonAlaPivot","Ala-Pívot");
        mapPositionContainer.put("containerButtonPivot","Pívot");

        return  mapPositionContainer.get(searchId);
    }

    public StackPane getStackPaneWithPosition(String searchPosition){
        HashMap<String,StackPane> hashMapStackPane = new HashMap<>();
        hashMapStackPane.put("Base",getContainerButtonBase());
        hashMapStackPane.put("Escolta",getContainerButtonEscolta());
        hashMapStackPane.put("Alero",getContainerButtonAlero());
        hashMapStackPane.put("Ala-Pívot",getContainerButtonAlaPivot());
        hashMapStackPane.put("Pívot",getContainerButtonPivot());

        return hashMapStackPane.get(searchPosition);
    }


    /**
     * Este método cambia directamente la imagen del contenedor con la Imagen sacada del método selectImageTeamField
     * @param idSearch el identificador de la imagen relacionada que estoy buscando
     * @param controller el controlador que obtiene el jugador seleccionado y por ende su path de su respectiva imagen
     */
    public void changeImage(String idSearch, SelectionController controller){
        selectImageTeamField(idSearch).setImage(
                new Image(getClass().getResource(
                        controller.getPlayerSelected().getPathPhotoName()
                ).toExternalForm())
        );
    }



    public void actionClickedStackPane(StackPane buttonStackPane, String nameStackPane, MouseEvent mouseEvent){
        // Aquí saco el id del stack pane
        StackPane idStackPane = (StackPane) mouseEvent.getSource();
        String idSpStr = idStackPane.getId();


        TeamWindow teamWindow = new TeamWindow();
        Object controller = null;
        try {
            if (buttonStackPane.getChildren().getFirst() instanceof ImageView) {
                controller = teamWindow.startStageSelection(getPositionContainer(idSpStr));
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

                // Cambiar la imagen---------------------------------------------------------------
                selectImageTeamField(idSpStr).setImage(
                        new Image(getClass().getResource(
                                "/org/example/fantasybasketaplication/Images/imagenPerfil.png"
                        ).toExternalForm())
                );

                // Eliminar el anterior registro
                getSetJsonInfo().deleteJsonFromFile("teamPlayer.json","position",getPositionContainer(idSpStr));
            } else if (election.equals("Replace")) {
                // eliminar al registro anterior
                getSetJsonInfo().deleteJsonFromFile("teamPlayer.json","position",getPositionContainer(idSpStr));

                try{
                    SelectionController newController = teamWindow.startStageSelection(getPositionContainer(idSpStr));

                    VBox vBox = teamWindow.containerPlayerTeam(
                            newController.getPlayerSelected().getName(),
                            newController.getPlayerSelected().getPathPhotoName(),
                            newController.getPlayerSelected().getPosition());

                    buttonStackPane.getChildren().clear();
                    buttonStackPane.getChildren().add(vBox);

                    // Cambiar la imagen----------------------------------------------------------------------
                    changeImage(idSpStr,newController);

                    // escribirlo
                    getSetJsonInfo().addJsonToFile(getSetJsonInfo().convertFormatJson(
                                    newController.getPlayerSelected().getName(),
                                    newController.getPlayerSelected().getPosition(),
                                    newController.getPlayerSelected().getPrice(),
                                    newController.getPlayerSelected().getTeam(),
                                    newController.getPlayerSelected().getEstado(),
                                    newController.getPlayerSelected().getPathPhotoName(),
                                    newController.getPlayerSelected().getPathPhotoTeam()
                            ),"teamPlayer.json"
                    );


                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        } else if (controller instanceof SelectionController) {
            SelectionController selectionController = (SelectionController) controller;

            // le mando la posición al selectionController
            selectionController.setPosition(getPositionContainer(idSpStr));
            System.out.println("posición obtenida del container: " + getPositionContainer(idSpStr));
            System.out.println("posición obtenida del controlador: " + selectionController.getPosition());

            VBox vBox = teamWindow.containerPlayerTeam(
                    selectionController.getPlayerSelected().getName(),
                    selectionController.getPlayerSelected().getPathPhotoName(),
                    selectionController.getPlayerSelected().getPosition());

            buttonStackPane.getChildren().clear();
            buttonStackPane.getChildren().add(vBox);

            // Cambiar la imagen---------------------------------------------------------------
            changeImage(idSpStr,selectionController);

            // Escribir en un json
            getSetJsonInfo().addJsonToFile(getSetJsonInfo().convertFormatJson(
                    selectionController.getPlayerSelected().getName(),
                    selectionController.getPlayerSelected().getPosition(),
                    selectionController.getPlayerSelected().getPrice(),
                    selectionController.getPlayerSelected().getTeam(),
                    selectionController.getPlayerSelected().getEstado(),
                    selectionController.getPlayerSelected().getPathPhotoName(),
                    selectionController.getPlayerSelected().getPathPhotoTeam()
                ),"teamPlayer.json"
            );
        }
    }



    public void handlePivotClicked(MouseEvent event) {
        System.out.println("Pivot clicked");
        actionClickedStackPane(getContainerButtonPivot(), "pivot",event);
    }

    public void handleBaseClicked(MouseEvent event) {
        System.out.println("Base clicked");
        actionClickedStackPane(getContainerButtonBase(),"base", event);
    }

    public void handleEscoltaClicked(MouseEvent event) {
        System.out.println("Escolta clicked");
        actionClickedStackPane(getContainerButtonEscolta(), "escolta", event);
    }

    public void handleAleroClicked(MouseEvent event) {
        System.out.println("Alero clicked");
        actionClickedStackPane(getContainerButtonAlero(), "alero", event);
    }

    public void handleAlaPivotClicked(MouseEvent event) {
        System.out.println("Ala Pivot clicked");
        actionClickedStackPane(getContainerButtonAlaPivot(), "ala pivot", event);
    }

    @FXML
    public void initialize(){

        TeamWindow teamWindow = new TeamWindow();

        List<Player> playersList = getGetJsonInfo().getPlayersJson("teamPlayer.json");
        if (playersList != null) {

            for (Player player : playersList) {
                StackPane stackPane = getStackPaneWithPosition(player.getPosition());

                VBox vBox = teamWindow.containerPlayerTeam(
                        player.getName(),
                        player.getPathPhotoName(),
                        player.getPosition());

                stackPane.getChildren().clear();
                stackPane.getChildren().add(vBox);

                selectImageTeamField(stackPane.getId()).setImage(
                        new Image(getClass().getResource(
                                player.getPathPhotoName()
                        ).toExternalForm())
                );
            }
        }


        // tengo que hacer otro json en el que guardo lo seleccionado y lo recupero por posiciónes ya que he conseguido que me filtre
        // por posición, asi que solo necesito guardarlos normal y cada que inicie leer el archivo json poner cada cual en su respectiva
        // posición y después crear la carta de cada personaje y relacionarlo con la foto.
    }

}
