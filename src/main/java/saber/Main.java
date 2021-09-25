package saber;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import saber.javafx.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Saber saber = new Saber(System.getProperty("user.dir"));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSaber(saber);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
