import java.io.IOException;

import ashy.Ashy;
import ashy.MainWindow;
import ashy.util.Storage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private Storage storage = new Storage();
    private Ashy ashy = new Ashy();

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Ashy ChatBot");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(ashy);
            storage.loadCommands(ashy);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
