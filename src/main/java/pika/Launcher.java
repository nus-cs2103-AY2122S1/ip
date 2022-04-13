package pika;

import javafx.application.Application;
import javafx.fxml.FXML;

/**
 * A launcher class to workaround classpath issues.
 */

public class Launcher {

    @FXML
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
