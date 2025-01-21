package org.example.fantasybasketaplication.controllers;


import javafx.fxml.FXML;
import javafx.scene.web.WebView;

public class HomeController extends FatherController {
    @FXML
    private WebView mywebView;

    public WebView getMywebView() {
        return mywebView;
    }

    public void setMywebView(WebView mywebView) {
        this.mywebView = mywebView;
    }

    @FXML
    public void initialize() {
        getMywebView().getEngine().load(getClass().getResource("/org/example/fantasybasketaplication/javaDocWeb/index.html").toExternalForm());
    }
}
