package abyss;

import java.io.IOException;

import abyss.controller.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Abyss using FXML.
 */
public class Main extends Application {

    private Abyss abyss = new Abyss();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setAbyss(abyss);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
