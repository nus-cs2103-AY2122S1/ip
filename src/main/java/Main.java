import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import nyx.Nyx;
import nyx.ui.MainWindow;

/**
 * A GUI for Nyx using FXML.
 */
public class Main extends Application {
    private final Nyx nyx = new Nyx("data", "nyx.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("NYX");
            fxmlLoader.<MainWindow>getController().setNyx(nyx);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
