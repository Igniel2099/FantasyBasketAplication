module org.example.fantasybasketaplication {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.fantasybasketaplication to javafx.fxml;
    exports org.example.fantasybasketaplication;
}