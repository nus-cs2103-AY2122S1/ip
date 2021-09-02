package yoyo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import yoyo.gui.MainWindow;

/**
 * A GUI for Yoyo using FXML.
 */
public class Main extends Application {

    private Yoyo yoyo = new Yoyo();

    /**
     * Main entry point for GUI.
     *
     * @param stage Stage for setting the scene.
     */
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new MainWindow(yoyo));
        stage.setScene(scene);
        stage.setTitle("Yoyo");
        stage.show();
    }
}
