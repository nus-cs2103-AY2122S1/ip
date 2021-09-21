package janet;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Janet using FXML.
 */
public class Main extends Application {

    private final Janet janet = new Janet();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Good Janet");
            fxmlLoader.<MainWindow>getController().setJanet(janet);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
