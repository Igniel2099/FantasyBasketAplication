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

                // Cambiar la imagen---------------------------------------------------------------
                selectImageTeamField(idSpStr).setImage(
                        new Image(getClass().getResource(
                                "/org/example/fantasybasketaplication/Images/imagenPerfil.png"
                        ).toExternalForm())
                );
            } else if (election.equals("Replace")) {
                try{
                    SelectionController newController = teamWindow.startStageSelection();

                    VBox vBox = teamWindow.containerPlayerTeam(
                            newController.getPlayerSelected().getName(),
                            newController.getPlayerSelected().getPathPhotoName(),
                            newController.getPlayerSelected().getPosition());

                    buttonStackPane.getChildren().clear();
                    buttonStackPane.getChildren().add(vBox);

                    // Cambiar la imagen----------------------------------------------------------------------
                    changeImage(idSpStr,newController);


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

            // Cambiar la imagen---------------------------------------------------------------
            changeImage(idSpStr,selectionController);
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
        getId_alero().setImage(new Image(getClass().getResource("/org/example/fantasybasketaplication/Images/imagenPerfil.png").toExternalForm()));
        getId_base().setImage(new Image(getClass().getResource("/org/example/fantasybasketaplication/Images/imagenPerfil.png").toExternalForm()));
        getId_pivot().setImage(new Image(getClass().getResource("/org/example/fantasybasketaplication/Images/imagenPerfil.png").toExternalForm()));
        getId_ala_pivot().setImage(new Image(getClass().getResource("/org/example/fantasybasketaplication/Images/imagenPerfil.png").toExternalForm()));
        getId_escolta().setImage(new Image(getClass().getResource("/org/example/fantasybasketaplication/Images/imagenPerfil.png").toExternalForm()));

    }

}
