package sora;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import sora.controller.MainWindow;

/**
 * A GUI for Sora using FXML.
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            fxmlLoader.<MainWindow>getController().setDuke(new Sora(false));

            Scene scene = new Scene(ap);
            stage.setScene(scene);

            stage.setTitle("Sora");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialize and start the program in GUI interface.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
