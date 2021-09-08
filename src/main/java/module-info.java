module duke {

    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
   // requires junitinterface;
//    requires junit.interface;
//    requires javafx.controls;
//      requires javafx.fxml;


    opens duke to javafx.fxml;
    exports duke;
}

