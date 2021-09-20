module duke {

    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;


    opens duke to javafx.fxml;
    exports duke;
}

