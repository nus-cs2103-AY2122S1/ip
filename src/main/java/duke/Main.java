package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private static Stage stage;
    private Duke duke = new Duke("duke.txt");
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

    @Override
    public void start(Stage stage) {
        try {
            this.stage = stage;
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.setTitle("Chadbot");
            stage.getIcons().add(dukeImage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exit() {
        stage.close();
    }
}
