package pib.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pib.Pib;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Pib pib = new Pib();

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Pib");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setPib(pib);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
