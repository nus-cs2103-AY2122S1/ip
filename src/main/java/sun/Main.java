package sun;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sun.gui.MainWindow;

/**
 * A GUI for Sun using FXML.
 */
public class Main extends Application {

    private Sun Sun = new Sun();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSun(Sun);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
