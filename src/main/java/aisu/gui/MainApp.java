package aisu.gui;

import java.io.IOException;

import aisu.Aisu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Aisu using FXML.
 */
public class MainApp extends Application {

    private Aisu aisu = new Aisu("data", "testGUI.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setAisu(aisu);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
