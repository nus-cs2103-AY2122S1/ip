package duchess.gui;

import java.io.IOException;

import duchess.main.Duchess;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    /** The Duchess object to serve the user.*/
    private Duchess duchess = new Duchess();

    /**
     * Starts the setting of the stage and showing the window to the user.
     * @param stage The Main window to show the user.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuchess(duchess);
            stage.setTitle("Duchess");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
