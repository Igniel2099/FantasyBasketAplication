module org.example.fantasybasketaplication {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.fantasybasketaplication to javafx.fxml;
    exports org.example.fantasybasketaplication;
    exports org.example.fantasybasketaplication.controllers;
    opens org.example.fantasybasketaplication.controllers to javafx.fxml;
    exports org.example.fantasybasketaplication.WindowsApp;
    opens org.example.fantasybasketaplication.WindowsApp to javafx.fxml;
}