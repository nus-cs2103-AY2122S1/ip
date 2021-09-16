package aisu.ui.gui;

import java.io.IOException;

import aisu.Aisu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Aisu using FXML.
 *
 * @author Liaw Xin Yan
 */
public class MainApp extends Application {

    private final Aisu aisu = new Aisu("data", "testGUI.txt");

    /**
     * Starts the Aisu application.
     *
     * @param stage the primary stage for this application, onto which the application scene can be set.
     */
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
