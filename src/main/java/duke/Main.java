package duke;

import java.io.IOException;

import duke.controller.MainWindow;
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

    private Duke duke;

    {
        try {
            duke = new Duke("data/duke.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Image logo = new Image(this.getClass().getResourceAsStream("/images/telegram.png"));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Duke on Telegram");
            stage.getIcons().add(logo);
            scene.getStylesheets().add(getClass().getResource("/css/index.css").toExternalForm());
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
