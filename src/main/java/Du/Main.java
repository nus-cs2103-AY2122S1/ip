package Du;
import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Du du = new Du("data/du.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.setFill(Color.RED);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDu(du);
            fxmlLoader.<MainWindow>getController().start();
            stage.show();
            stage.setOnCloseRequest(e -> Platform.exit());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}