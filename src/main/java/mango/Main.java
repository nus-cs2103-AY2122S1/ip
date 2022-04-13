package mango;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Mango using FXML.
 */
public class Main extends Application {

    private Mango mango = new Mango();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Mango the Task Bot");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setMango(mango);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
