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

    private final String LOGO_DIR = "/images/fruits.png";

    private final String MAIN_WINDOW_DIR = "/view/MainWindow.fxml";

    private final String CSS_DIR = "/css/index.css";

    private final String GUI_TITLE = "Sync-Me Sebby";

    private Image logo = new Image(this.getClass().getResourceAsStream(LOGO_DIR));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(MAIN_WINDOW_DIR));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle(GUI_TITLE);
            stage.getIcons().add(logo);
            scene.getStylesheets().add(getClass().getResource(CSS_DIR).toExternalForm());
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
