package cygnus.gui;

import java.io.IOException;

import cygnus.Cygnus;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Cygnus using FXML.
 */
public class Main extends Application {

    private final Cygnus cygnus = new Cygnus();

    /**
     * Starts the application.
     *
     * @param stage The primary Stage of the application.
     */
    @Override
    public void start(Stage stage) {
        assert stage != null : "Primary stage is not initialized";
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setCygnus(cygnus);
            fxmlLoader.<MainWindow>getController().displayGreeting();
            stage.setTitle("Cygnus");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


